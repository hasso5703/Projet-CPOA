package application.controler.Revue;

import modele.Revue;

public class RevueHolder
{
    private Revue revue;

    private final static RevueHolder INSTANCE = new RevueHolder();

    public RevueHolder() {}
    
    public static RevueHolder getInstance()
    {
        return INSTANCE;
    }

    public void setRevue(Revue revue)
    {
        this.revue = revue;
    }

    public Revue getRevue()
    {
        return this.revue;
    }
}
