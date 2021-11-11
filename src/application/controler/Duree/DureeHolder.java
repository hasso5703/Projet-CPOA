package application.controler.Duree;

import modele.Duree;

public class DureeHolder {


	private Duree duree;

    private final static DureeHolder INSTANCE = new DureeHolder();

    private DureeHolder() {}
    
    public static DureeHolder getInstance()
    {
        return INSTANCE;
    }

    public void setDuree(Duree duree)
    {
        this.duree = duree;
    }

    public Duree getDuree()
    {
        return this.duree;
    }
}
