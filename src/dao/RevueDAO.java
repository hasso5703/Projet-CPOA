package dao;

import java.sql.SQLException;
import java.util.List;


import modele.Revue;

public interface RevueDAO extends DAO<Revue>
{
    public List<Revue> getByTitre(String titre) throws SQLException;
}
