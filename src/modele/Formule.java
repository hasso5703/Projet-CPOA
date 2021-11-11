package modele;


import java.util.Objects;

public class Formule {
	
	private int idRevue;
	private int idDuree;
	private float reduction;
	
	//-------------CONSTRUCTEURS------------------------------
	public Formule(Revue revue, Duree duree, float reduction) 
	{
		this.idRevue = revue.getId();
		this.idDuree = duree.getIdDuree();
		this.reduction = getReduction();
	}
	
	public Formule(float reduc) 
	{
		this.reduction = reduc;
	}
	
	//--------------GETTERS/SETTERS------------------------------
	
	public int getIdRevue() {
		return idRevue;
	}
	public void setIdRevue(Revue revue) {
		if (revue == null)
        {
            throw new IllegalArgumentException("Revue non saisie");
        }
        else
        {
            this.idRevue = revue.getId();
        }
	}
	public int getIdDuree() {
		return idDuree;
	}
	public void setIdDuree(Duree duree) {
		if (duree == null)
        {
            throw new IllegalArgumentException("Duree non saisie");
        }
        else
        {
            this.idRevue = duree.getIdDuree();
        }
	}
	public float getReduction() {
		return reduction;
	}
	public void setReduction(Formule formule) {
		if ( formule == null)
        {
            throw new IllegalArgumentException("Formule non saisie");
        }
        else
        {
            this.reduction = formule.getReduction();
        }
	}
	
	//----------------------------------------------------------------------
	@Override
    public boolean equals(Object object)
    {
        Formule formule = (Formule)object;

        if (formule != null &&
            this.idRevue == formule.idRevue &&
            this.idDuree == formule.idDuree &&
            this.reduction == formule.reduction)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	

    @Override
    public int hashCode()
    {
        return Objects.hash(idRevue, idDuree, reduction);
    }

	@Override
	public String toString() {
		return "Formule [idRevue=" + idRevue + ", idDuree=" + idDuree + ", reduction=" + reduction + "]";
	}


	public void setIdRevue(int i) {
		 this.idRevue = i;
		
	}

	public void setReduction(float parseFloat) {
		this.reduction = getReduction();
		
	}


    
    
   

	
}
