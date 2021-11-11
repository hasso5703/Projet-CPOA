package application.controler.Formule;

import modele.Formule;

public class FormuleHolder {
	
	private Formule formule;

    private final static FormuleHolder INSTANCE = new FormuleHolder();

    private FormuleHolder() {}
    
    public static FormuleHolder getInstance()
    {
        return INSTANCE;
    }

    public void setFormule(Formule formule)
    {
        this.formule = formule;
    }

    public Formule getFormule()
    {
        return this.formule;
    }

}
