package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import modele.Duree;

public class MYSQLDureeDAO implements DureeDAO {
	
	private Connexion db;
    private Connection laConnexion;
    
    private static MYSQLDureeDAO instance;
    
   
    public static DureeDAO getInstance() 
    {
        if (instance == null) {
            instance = new MYSQLDureeDAO();
        }
        return instance;
    }

    private MYSQLDureeDAO() 
    {
        db = new Connexion();
        laConnexion = db.creeConnexion();
    }

	@Override
	public boolean create(Duree objet) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO Duree(id_duree, libelle_formule) Values (?, ?)");
        requete.setInt(1, objet.getIdDuree());
        requete.setString(2, objet.getLibelleFormule());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
	}

	@Override
	public boolean update(Duree objet) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("UPDATE Duree SET id_duree = ?, libelle_formule = ?");
        requete.setInt(1, objet.getIdDuree());
        requete.setString(2, objet.getLibelleFormule());
        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
	}

	@Override
	public boolean delete(Duree objet) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Duree WHERE id_duree = ?");
        requete.setInt(1, objet.getIdDuree());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
	}

	@Override
	public Duree getById(int i) throws SQLException {
		db = new Connexion();
        laConnexion = db.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Duree WHERE id_duree = ?");
        requete.setInt(1, i);

        ResultSet res = requete.executeQuery();
        
        res.next();

        Duree duree = new Duree(res.getInt(1), res.getString(2));

        if (laConnexion != null)
            laConnexion.close();

        return duree;
	}

	@Override
	public List<Duree> findAll() throws SQLException {
		 List<Duree> listeDuree = new ArrayList<Duree>();

	        db = new Connexion();
	        laConnexion = db.creeConnexion();

	        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Duree");
	        
	        ResultSet res = requete.executeQuery();

	        while (res.next()) 
	        {
	            listeDuree.add(new Duree(res.getInt(1)));
	        }

	        if (laConnexion != null)
	        laConnexion.close();

	        return listeDuree;
	}
	
	

}
