package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T>
{
    public boolean create(T objet) throws SQLException;
    public boolean update(T objet) throws SQLException;
    public boolean delete(T objet) throws SQLException;
    public T getById(int i) throws SQLException;
    public List<T> findAll() throws SQLException;
	
	
}
