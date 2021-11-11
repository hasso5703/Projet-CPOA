package dao;

import connexion.Connexion;


import modele.Adresse;
import modele.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MYSQLClientDAO implements ClientDAO
{
    private static MYSQLClientDAO instance;
    Connexion maBD;
    Connection laConnexion;

    public static ClientDAO getInstance() 
    {
        if (instance == null) 
        {
            instance = new MYSQLClientDAO();
        }
        return instance;
    }

    private MYSQLClientDAO() 
    {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();
    }

    @Override
    public boolean create(Client objet) throws SQLException
    {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("INSERT INTO Client(nom, prenom, no_rue, voie, code_postal, ville, pays) Values (? ,? ,? ,? ,? ,? ,?)");
        requete.setString(1, objet.getNom());
        requete.setString(2, objet.getPrenom());
        requete.setString(3, objet.getAdresse().getNoRue());
        requete.setString(4, objet.getAdresse().getVoie());
        requete.setString(5, objet.getAdresse().getCodePostal());
        requete.setString(6, objet.getAdresse().getVille());
        requete.setString(7, objet.getAdresse().getPays());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
    }

    @Override
    public boolean update(Client objet) throws SQLException 
    {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("UPDATE Client SET nom = ?, prenom = ?, no_rue = ?" +
                    ", voie = ?, code_postal = ?, ville = ?, pays = ? WHERE id_client =  ?");
        requete.setString(1, objet.getNom());
        requete.setString(2, objet.getPrenom());
        requete.setString(3, objet.getAdresse().getNoRue());
        requete.setString(4, objet.getAdresse().getVoie());
        requete.setString(5, objet.getAdresse().getCodePostal());
        requete.setString(6, objet.getAdresse().getVille());
        requete.setString(7, objet.getAdresse().getPays());
        requete.setInt(8, objet.getId());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
    }

    @Override
    public boolean delete(Client objet) throws SQLException 
    {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Client WHERE id_client = ?");
        requete.setInt(1, objet.getId());

        int res = requete.executeUpdate();

        if (laConnexion != null)
            laConnexion.close();

        return (res == 1);
    }

    @Override
    public Client getById(int i) throws SQLException 
    {
        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE id_client = ?");
        requete.setInt(1, i);

        ResultSet res = requete.executeQuery();
        res.next();

        Client client = new Client(res.getInt(1), res.getString(2), res.getString(3), new Adresse(res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)));


        if (laConnexion != null)
            laConnexion.close();

        return client;
    }

    @Override
    public ArrayList<Client> getByNomPrenom(String nom, String prenom) throws SQLException 
    {
        ArrayList<Client> listeClient = new ArrayList<Client>();

        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE nom = ? AND prenom = ?");
        requete.setString(1, nom);
        requete.setString(2, prenom);

        ResultSet res = requete.executeQuery();

        while (res.next()) 
        {
            listeClient.add(new Client(res.getInt(1), res.getString(2),res.getString(3), new Adresse(res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8))));
        }    

        if (laConnexion != null)
            laConnexion.close();
            
        return listeClient;
    }

    @Override
    public List<Client> findAll() throws SQLException 
    {
        List<Client> listeClient = new ArrayList<Client>();

        maBD = new Connexion();
        laConnexion = maBD.creeConnexion();

        PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client");

        ResultSet res = requete.executeQuery();
        
        while (res.next()) 
        {
            listeClient.add(new Client(res.getInt(1), res.getString(2), res.getString(3), new Adresse(res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8))));
        }    

        if (laConnexion != null)
            laConnexion.close();
            
        return listeClient;
    }
}