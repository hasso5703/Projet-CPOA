package application.controler.dao;

import factory.DAOFactory;
import factory.Persistance;

public class DAO {
	
	 	private Persistance persistance = null;
	    private DAOFactory daoFactory = null;
	    private final static DAO INSTANCE = new DAO();

	    private DAO() {}

	    public static DAO getInstance()
	    {
	        return INSTANCE;
	    }

	    public DAOFactory getDaoFactory() {
	        return daoFactory;
	    }

	    public Persistance getPersistance() {
	        return persistance;
	    }

	    public void setDaoFactory(Persistance persistance)
	    {
	        this.persistance = persistance;
	        this.daoFactory = DAOFactory.getDAOFactory(persistance);
	    }

}
