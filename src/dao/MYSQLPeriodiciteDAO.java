package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;

import modele.Periodicite;

public class MYSQLPeriodiciteDAO implements PeriodiciteDAO {
    private static MYSQLPeriodiciteDAO instance;
    Connexion maBD;
    Connection laConnexion;

    public static PeriodiciteDAO getInstance() {
        if (instance == null) {
            instance = new MYSQLPeriodiciteDAO();
        }
        return instance;
    }

    private MYSQLPeriodiciteDAO() {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();
    }

    @Override
    public boolean create(Periodicite objet) throws SQLException {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO Periodicite(libelle) Values (?)");
        requete.setString(1, objet.getLibelle());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
    }

    @Override
    public boolean update(Periodicite objet) throws SQLException {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion
                .prepareStatement("UPDATE Periodicite SET libelle = ? WHERE id_periodicite = ?");
        requete.setString(1, objet.getLibelle());
        requete.setInt(2, objet.getId());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
    }

    @Override
    public boolean delete(Periodicite objet) throws SQLException {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Periodicite WHERE id_periodicite = ?");
        requete.setInt(1, objet.getId());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
    }

    @Override
    public Periodicite getById(int i) throws SQLException {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Periodicite WHERE id_periodicite = ?");
        requete.setInt(1, i);

        ResultSet res = requete.executeQuery();

        res.next();
        
        Periodicite periodicite = new Periodicite(res.getInt(1), res.getString(2));

        if (laConnexion != null)
            laConnexion.close();

        return periodicite;
    }

    @Override
    public List<Periodicite> getByLibelle(String libelle) throws SQLException
    {
        List<Periodicite> listePeriodicite = new ArrayList<Periodicite>();

        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Periodicite WHERE libelle = ?");
        requete.setString(1, libelle);
        
        ResultSet res = requete.executeQuery();

        while (res.next()) 
        {
            listePeriodicite.add(new Periodicite(res.getInt(1), res.getString(2)));
        }

        if (laConnexion != null) {
            laConnexion.close();
        }
        
        return listePeriodicite;
    }

    @Override
    public List<Periodicite> findAll() throws SQLException {
        List<Periodicite> listePeriodicite = new ArrayList<Periodicite>();

        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Periodicite");

        ResultSet res = requete.executeQuery();

        while (res.next()) {
            listePeriodicite.add(new Periodicite(res.getInt(1), res.getString(2)));
        }

        if (laConnexion != null)
            laConnexion.close();

        return listePeriodicite;
    }
}