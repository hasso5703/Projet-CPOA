package factory;

import dao.AbonnementDAO;
import dao.ClientDAO;
import dao.DureeDAO;
import dao.FormuleDAO;
import dao.MYSQLAbonnementDAO;
import dao.MYSQLClientDAO;
import dao.MYSQLDureeDAO;
import dao.MYSQLFormuleDAO;
import dao.MYSQLPeriodiciteDAO;
import dao.MYSQLRevueDAO;
import dao.PeriodiciteDAO;
import dao.RevueDAO;

public class MySQLDAOFactory extends DAOFactory
{

    @Override
    public AbonnementDAO getAbonnementDAO()
    {
        return MYSQLAbonnementDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO()
    {
        return MYSQLClientDAO.getInstance();
    }

    @Override
    public PeriodiciteDAO getPeriodiciteDAO()
    {
        return MYSQLPeriodiciteDAO.getInstance();
    }

    @Override
    public RevueDAO getRevueDAO()
    {
        return MYSQLRevueDAO.getInstance();
    }

	@Override
	public DureeDAO getDureeDAO() {
		return MYSQLDureeDAO.getInstance();
	}

	@Override
	public FormuleDAO getFormuleDAO() {
		return MYSQLFormuleDAO.getInstance();
	}
    
}