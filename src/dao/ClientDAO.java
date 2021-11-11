package dao;

import java.sql.SQLException;
import java.util.List;

import modele.Client;

public interface ClientDAO extends DAO<Client>
{
   public List<Client> getByNomPrenom(String nom, String prenom) throws SQLException;
}