package liste_memoire;

import dao.AbonnementDAO;


import modele.Abonnement;
import modele.Client;
import modele.Duree;
import modele.Revue;



import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ListeMemoireAbonnementDAO implements AbonnementDAO {

    private static ListeMemoireAbonnementDAO instance;

    private List<Abonnement> donnees;


    public static ListeMemoireAbonnementDAO getInstance()
    {
        if (instance == null) {
            instance = new ListeMemoireAbonnementDAO();
        }

        return instance;
    }

    private ListeMemoireAbonnementDAO()
    {
        this.donnees = new ArrayList<Abonnement>();

        this.donnees.add(new Abonnement(1, LocalDate.parse("11/11/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            LocalDate.parse("11/12/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new Client(1), new Revue(1),new Duree(2)));
        this.donnees.add(new Abonnement(2, LocalDate.parse("03/11/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            LocalDate.parse("02/12/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")), new Client(2), new Revue(2),new Duree(2)));
    }


    @Override
    public boolean create(Abonnement objet)
    {
        if(this.donnees.size() != 0)
        {
            objet.setId(this.donnees.get(this.donnees.size()-1).getId()+1);
        }
        else
        {
            objet.setId(1);
        }

        boolean ok = this.donnees.add(objet);

        return ok;
    }

    @Override
    public boolean update(Abonnement objet)
    {
        int idx = -1;

        for (Abonnement abonnement : this.donnees) 
        {
            if (abonnement.getId() == objet.getId()) 
            {
                idx = this.donnees.indexOf(abonnement);    
            }    
        }

        if (idx == -1) 
        {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } 
        else 
        {

            this.donnees.set(idx, objet);
        }

        return true;
    }

    @Override
    public boolean delete(Abonnement objet)
    {
        int idx = -1;

        for (Abonnement abonnement : this.donnees) 
        {
            if (abonnement.getId() == objet.getId()) 
            {
                idx = this.donnees.indexOf(abonnement);    
            }    
        }

        if (idx == -1) 
        {
            throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
        } 
        else 
        {
            this.donnees.remove(idx);
        }

        return true;
    }

    @Override
    public Abonnement getById(int id)
    {
        int idx = -1;

		for (Abonnement abonnement : this.donnees) 
		{
			if (abonnement.getId() == id) 
			{
				idx = this.donnees.indexOf(abonnement);
			}
		}

		if (idx == -1) 
		{
			throw new IllegalArgumentException("Aucun objet ne possede cet identifiant");
		} 
		else 
		{
			return this.donnees.get(idx);
		}
    }

    @Override
    public List<Abonnement> findAll()
    {
        return this.donnees;
    }

    @Override
    public ArrayList<Abonnement> getByDate(LocalDate dateDebut, LocalDate dateFin)
    {
        ArrayList<Abonnement> listeAbonnement = new ArrayList<Abonnement>();
        
		for (Abonnement abonnement : this.donnees) 
		{
			if (abonnement.getDateDebut().equals(dateDebut) && abonnement.getDateFin().equals(dateFin)) 
			{
				listeAbonnement.add(this.donnees.get(this.donnees.indexOf(abonnement)));
			}
		}

		if (listeAbonnement.size() == 0) 
		{
			throw new IllegalArgumentException("Aucun objet ne possede ces dates");
		} 
		else 
		{
			return listeAbonnement;
		}
    }

    @Override
    public ArrayList<Abonnement> getByNomPrenom(String nom, String prenom) throws SQLException 
    {
        ArrayList<Abonnement> listeAbonnement = new ArrayList<Abonnement>();
        ListeMemoireClientDAO listeMemoireClient = ListeMemoireClientDAO.getInstance();

		for (Abonnement abonnement : this.donnees) 
		{
            int idClient = abonnement.getIdClient();
            Client client = listeMemoireClient.getById(idClient);

			if (client.getPrenom().equals(prenom) && client.getNom().equals(nom)) 
			{
				listeAbonnement.add(this.donnees.get(this.donnees.indexOf(abonnement)));
			}
		}

		if (listeAbonnement.size() == 0) 
		{
			throw new IllegalArgumentException("Aucun objet ne possede ce prenom et ce nom");
		} 
		else 
		{
			return listeAbonnement;
		}
    }
}
