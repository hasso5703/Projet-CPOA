package modele;

import java.util.Objects;

public class Duree {
	
	private int idDuree;
	private String libelleFormule;

	//----------------CONSTRUCTEURS------------------------------
	public Duree(int idDuree, String libelleFormule)
	{
		this.idDuree = idDuree;
		this.libelleFormule = libelleFormule;
	}
	
	public Duree(int idDuree)
	{
		this.idDuree = idDuree;
	}
	
	public Duree(String libelleFormule)
	{
		this.libelleFormule = libelleFormule;
	}
	
	//----------------GETTERS/SETTERS-----------------------------
	
	public int getIdDuree() {
		return idDuree;
	}
	public void setIdDuree(int idDuree) {
		this.idDuree = idDuree;
	}
	public String getLibelleFormule() {
		return libelleFormule;
	}
	public void setLibelleFormule(String libelleFormule) {
		this.libelleFormule = libelleFormule;
	}
	
	@Override
    public int hashCode()
    {
        return Objects.hash(idDuree, libelleFormule);
    }
    
	@Override
    public boolean equals(Object object)
    {
        Duree duree = (Duree)object;

        if (duree != null &&
            this.idDuree == duree.idDuree &&
            this.libelleFormule == duree.libelleFormule)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

	@Override
	public String toString() {
		return "Duree [idDuree=" + idDuree + ", libelleFormule=" + libelleFormule + "]";
	}

}
