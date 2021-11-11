package liste_memoire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.FormuleDAO;
import modele.Duree;
import modele.Formule;
import modele.Revue;

public class ListeMemoireFormuleDAO implements FormuleDAO {
	
	private static ListeMemoireFormuleDAO instance;

    private List<Formule> donnees;


    public static ListeMemoireFormuleDAO getInstance()
    {
        if (instance == null) {
            instance = new ListeMemoireFormuleDAO();
        }

        return instance;
    }


    private ListeMemoireFormuleDAO() {

		this.donnees = new ArrayList<Formule>();

		this.donnees.add(new Formule(new Revue(1), new Duree(1), 0.25f));
		
	}
    
    
	@Override
	public boolean create(Formule objet) throws SQLException {
		if(this.donnees.size() != 0)
		{
			objet.setIdRevue(this.donnees.get(this.donnees.size()-1).getIdRevue()+1);
		}
		else
		{
			objet.setIdRevue(1);
		}

		boolean ok = this.donnees.add(objet);
		
		return ok;
		
	}

	@Override
	public boolean update(Formule objet) throws SQLException {
		int idx = -1;
		for (Formule formule : this.donnees) {
			if (formule.getIdRevue() == objet.getIdRevue())
			{
				idx = this.donnees.indexOf(formule);
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
	public boolean delete(Formule objet) throws SQLException {
		Formule supprime;
		int idx = -1;

		for (Formule formule : this.donnees) {
			if (formule.getIdRevue() == objet.getIdRevue())
			{
				idx = this.donnees.indexOf(formule);
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
	public Formule getById(int i) throws SQLException {
		int idx = -1;

		for (Formule formule : this.donnees) 
		{
			if (formule.getIdRevue() == i) 
			{
				idx = this.donnees.indexOf(formule);
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
	public List<Formule> findAll() throws SQLException {
		return this.donnees;
	}

}
