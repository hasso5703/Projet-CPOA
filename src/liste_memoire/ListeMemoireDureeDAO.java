package liste_memoire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DureeDAO;
import modele.Duree;

public class ListeMemoireDureeDAO implements DureeDAO{

	private static ListeMemoireDureeDAO instance;

    private List<Duree> donnees;

    public static ListeMemoireDureeDAO getInstance()
    {
        if (instance == null) {
            instance = new ListeMemoireDureeDAO();
        }

        return instance;
    }
	
    private ListeMemoireDureeDAO() {

		this.donnees = new ArrayList<Duree>();

		this.donnees.add(new Duree(1, "semaine"));
		this.donnees.add(new Duree(1, "mois"));
		this.donnees.add(new Duree(2, "mois"));
		this.donnees.add(new Duree(6, "mois"));
		this.donnees.add(new Duree(1, "an"));
		this.donnees.add(new Duree(2, "ans"));
		
		
	}
	
	@Override
	public boolean create(Duree objet) throws SQLException {
		if(this.donnees.size() != 0)
		{
			objet.setIdDuree(this.donnees.get(this.donnees.size()-1).getIdDuree()+1);
		}
		else
		{
			objet.setIdDuree(1);
		}

		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Duree objet) throws SQLException {
		int idx = -1;
		for (Duree duree : this.donnees) {
			if (duree.getIdDuree() == objet.getIdDuree())
			{
				idx = this.donnees.indexOf(duree);
			}
		}
		if (idx == -1) 
		{
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} 
		else 
		{
			this.donnees.set(idx, objet);
			return true;
		}
	}

	@Override
	public boolean delete(Duree objet) throws SQLException {
		Duree supprime;
		int idx = -1;

		for (Duree duree : this.donnees) {
			if (duree.getIdDuree() == objet.getIdDuree())
			{
				idx = this.donnees.indexOf(duree);
			}
		}
		if (idx == -1) 
		{
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} 
		else 
		{
			supprime = this.donnees.remove(idx);
		}

		return objet.equals(supprime);
	}

	@Override
	public Duree getById(int i) throws SQLException {
		int idx = -1;

		for (Duree duree : this.donnees) 
		{
			if (duree.getIdDuree() == i) 
			{
				idx = this.donnees.indexOf(duree);
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
	public List<Duree> findAll() throws SQLException {
		return this.donnees;
	}

}
