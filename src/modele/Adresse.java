package modele;

import java.util.Objects;

public class Adresse {

    private String noRue;
    private String voie; 
    private String codePostal; 
    private String ville;  
    private String pays;

    //---------------CONSTRUCTEURS-------------------------------------------------------
    public Adresse(String noRue, String voie, String codePostal, String ville, String pays)
    {
        this.noRue = noRue;
        this.voie = voie;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    //---------------GETTERS/SETTERS----------------------------------------------------
    public String getNoRue() 
    {
        return this.noRue;
    }

    public void setNoRue(String noRue) 
    {
        if (noRue == null || noRue.equals(""))
        {
            throw new IllegalArgumentException("Numero de rue incorrect");
        }
        else
        {
            this.noRue = noRue;
        }
    }

    public String getVoie() 
    {
        return this.voie;
    }

    public void setVoie(String voie) 
    {
        if (voie == null || voie.equals(""))
        {
            throw new IllegalArgumentException("Voie incorrect");
        }
        else
        {
            this.voie = voie;
        }
    }

    public String getCodePostal() 
    {
        return this.codePostal;
    }

    public void setCodePostal(String codePostal) 
    {
        if (codePostal == null || codePostal.length() < 4)
        {
            throw new IllegalArgumentException("Code Postal incorrect");
        }
        else
        {
            this.codePostal = codePostal;
        }
    }

    public String getVille() 
    {
        return this.ville;
    }

    public void setVille(String ville) 
    {
        if (ville == null || ville.equals(""))
        {
            throw new IllegalArgumentException("Ville incorrect");
        }
        else
        {
            this.ville = ville;
        }
    }

    public String getPays() 
    {
        return this.pays;
    }

    public void setPays(String pays) 
    {
        if (pays == null || pays.equals(""))
        {
            throw new IllegalArgumentException("Pays incorrect");
        }
        else
        {
            this.pays = pays;
        }
    }

    @Override
    public boolean equals(Object object)
    {
        Adresse adresse = (Adresse) object;

        if (this.getCodePostal().equals(adresse.getCodePostal()) &&
            this.getNoRue().equals(adresse.getNoRue()) &&
            this.getVoie().equals(adresse.getVoie()) &&
            this.getPays().equals(adresse.getPays()) &&
            this.getVille().equals(adresse.getVille())) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return (this.getNoRue() + " " + this.getVoie() + " " + this.getVille() + " " + this.getCodePostal() + " " + this.getPays()); 
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(noRue, voie, codePostal, ville, pays);
    }
}
