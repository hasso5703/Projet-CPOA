package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import modele.Duree;
import modele.Formule;
import modele.Revue;

public class MYSQLFormuleDAO implements FormuleDAO {

	private Connexion db;
    private Connection laConnexion;
    
    private static MYSQLFormuleDAO instance;
    
   
    public static FormuleDAO getInstance() 
    {
        if (instance == null) {
            instance = new MYSQLFormuleDAO();
        }
        return instance;
    }

    private MYSQLFormuleDAO() 
    {
        db = new Connexion();
        laConnexion = db.creeConnexion();
    }
	
	@Override
	public boolean create(Formule objet) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO Formule(id_revue, id_duree, reduction) Values (?, ?, ?)");
        requete.setInt(1, objet.getIdRevue());
        requete.setInt(1, objet.getIdDuree());
        requete.setFloat(2, objet.getReduction());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
	}

	@Override
	public boolean update(Formule objet) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("UPDATE Formule SET id_revue = ?, id_duree = ?, reduction = ?");
        requete.setInt(1, objet.getIdRevue());
        requete.setInt(2, objet.getIdDuree());
        requete.setFloat(3, objet.getReduction());
        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
	}

	@Override
	public boolean delete(Formule objet) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Formule WHERE id_revue = ?");
        requete.setInt(1, objet.getIdRevue());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
	}

	@Override
	public Formule getById(int i) throws SQLException {
		 db = new Connexion();
	        laConnexion = db.creeConnexion();

	        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Formule WHERE id_revue = ?");
	        requete.setInt(1, i);

	        ResultSet res = requete.executeQuery();
	        
	        res.next();

	        Formule formule = new Formule(new Revue(res.getInt(4)), new Duree(res.getInt(5)), res.getFloat(6));

	        if (laConnexion != null)
	            laConnexion.close();

	        return formule;
	}

	@Override
	public List<Formule> findAll() throws SQLException {
		 List<Formule> listeFormule = new ArrayList<Formule>();

	        db = new Connexion();
	        laConnexion = db.creeConnexion();

	        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Formule");
	        
	        ResultSet res = requete.executeQuery();

	        while (res.next()) 
	        {
	            listeFormule.add(new Formule(new Revue(res.getInt(1)), new Duree(res.getInt(2)), res.getFloat(3)));
	        }

	        if (laConnexion != null)
	        laConnexion.close();

	        return listeFormule;
	}

}
