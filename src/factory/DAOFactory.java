package factory;

import dao.AbonnementDAO;
import dao.ClientDAO;
import dao.DureeDAO;
import dao.FormuleDAO;
import dao.PeriodiciteDAO;
import dao.RevueDAO;

public abstract class DAOFactory
{
    public static DAOFactory getDAOFactory(Persistance cible)
    {
        DAOFactory daoF = null;
        switch (cible) 
        {
            case MySQL:
                daoF = new MySQLDAOFactory();
                break;
                
            case ListeMemoire:
                daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }
    public abstract AbonnementDAO getAbonnementDAO();
    public abstract ClientDAO getClientDAO();
    public abstract PeriodiciteDAO getPeriodiciteDAO();
    public abstract RevueDAO getRevueDAO();
    public abstract DureeDAO getDureeDAO();
    public abstract FormuleDAO getFormuleDAO();
}