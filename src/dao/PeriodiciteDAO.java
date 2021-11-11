package dao;

import java.sql.SQLException;
import java.util.List;

import modele.Periodicite;

public interface PeriodiciteDAO extends DAO<Periodicite>
{
    public List<Periodicite> getByLibelle(String libelle) throws SQLException;
}
