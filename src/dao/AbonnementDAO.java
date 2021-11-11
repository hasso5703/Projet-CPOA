package dao;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

import modele.Abonnement;


public interface AbonnementDAO extends DAO<Abonnement>
{
    public List<Abonnement> getByDate(LocalDate dateDebut, LocalDate dateFin) throws SQLException;
    public List<Abonnement> getByNomPrenom(String nom, String prenom) throws SQLException;
}

