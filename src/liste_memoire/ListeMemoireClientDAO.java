package liste_memoire;

import dao.ClientDAO;
import modele.Adresse;
import modele.Client;


import java.util.ArrayList;
import java.util.List;


public class ListeMemoireClientDAO implements ClientDAO {

	private static ListeMemoireClientDAO instance;

	private List<Client> donnees;


	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}

	private ListeMemoireClientDAO() {

		this.donnees = new ArrayList<Client>();

		this.donnees.add(new Client(1, "Hasan", "BASBUNAR", new Adresse("5B", "rue du Madrid", "57320", "Bouzonville", "France")));
		this.donnees.add(new Client(2, "Romain", "DE MATOS", new Adresse("1 bis", "Rue des lilas", "57000", "Metz", "France")));
		this.donnees.add(new Client(3, "Arnaud", "DE POYEN", new Adresse("2", "Rue de londres", "57000", "Metz", "France")));
	}


	@Override
	public boolean create(Client objet)
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
	public boolean update(Client objet) 
	{
		int idx = -1;
		for (Client client : this.donnees) {
			if (client.getId() == objet.getId())
			{
				idx = this.donnees.indexOf(client);
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
	public boolean delete(Client objet) 
	{
		Client supprime;
		int idx = -1;

		for (Client client : this.donnees) {
			if (client.getId() == objet.getId())
			{
				idx = this.donnees.indexOf(client);
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
	public Client getById(int id) {

		int idx = -1;

		for (Client client : this.donnees) 
		{
			if (client.getId() == id) 
			{
				idx = this.donnees.indexOf(client);
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
	public List<Client> findAll() 
	{
		return this.donnees;
	}

	@Override
	public List<Client> getByNomPrenom(String nom, String prenom) 
	{
		List<Client> listeRevue = new ArrayList<Client>();

		for (Client client : this.donnees) 
		{
			if (client.getNom().equals(nom) && client.getPrenom().equals(prenom)) 
			{
				listeRevue.add(this.donnees.get(this.donnees.indexOf(client)));
			}
		}

		if (listeRevue.size() == 0) 
		{
			throw new IllegalArgumentException("Aucun objet ne possede ce nom ou prenom");
		} 
		else 
		{
			return listeRevue;
		}
	}
}
