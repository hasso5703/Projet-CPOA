package application.controler.Abonnement;

import modele.Abonnement;

public class AbonnementHolder
{
    private Abonnement abonnement;

    private final static AbonnementHolder INSTANCE = new AbonnementHolder();

    private AbonnementHolder() {}
    
    public static AbonnementHolder getInstance()
    {
        return INSTANCE;
    }

    public void setAbonnement(Abonnement abonnement)
    {
        this.abonnement = abonnement;
    }

    public Abonnement getAbonnement()
    {
        return this.abonnement;
    }
}
