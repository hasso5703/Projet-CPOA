package modele;

import java.util.Objects;

import modele.Revue;

public class Revue
{
  
  private int id;
  private int idPeriodicite;
  private double tarifNumero;
  private String titre;
  private String description;
  private String visuel;
  
  //------------------CONSTRUCTEURS------------------------------
  public Revue(int id, String titre, String description, double tarifNumero, String visuel, Periodicite periode)
  {
      this.setId(id);
      this.setTitre(titre);
      this.setDescription(description);
      this.setTarifNumero(tarifNumero);
      this.setVisuel(visuel);
      this.setIdPeriodicite(periode);
  }

  public Revue(Integer id)
  {
      this.setId(id);
  }

  public Revue(String titre, String description, double tarifNumero, String visuel, Periodicite periode)
  {
      this.setTitre(titre);
      this.setDescription(description);
      this.setTarifNumero(tarifNumero);
      this.setVisuel(visuel);
      this.setIdPeriodicite(periode);
  }
    
  //------------------------GETTERS/SETTERS----------------------------
  public int getId() 
  {
    return this.id;
  }

  public void setId(int id)
  {
		this.id = id;
	}
  
  public int getIdPeriodicite() 
  {
    return this.idPeriodicite;
  }

  public void setIdPeriodicite(Periodicite periodicite)
  {
      if (periodicite == null)
      {
          throw new IllegalArgumentException("Periodicité nulle");
      }
      else
      {
          this.idPeriodicite = periodicite.getId();
      }
  }

  public double getTarifNumero()
{
      return this.tarifNumero;
  }

  public void setTarifNumero(double tarifNumero)
  {
      if (tarifNumero < 0)
      {
          throw new IllegalArgumentException("Tarif négatif");
      }
      else
      {
          this.tarifNumero = tarifNumero;
      }
  }

  public String getDescription()
  {
      return this.description;
  }

  public void setDescription(String description)
  {
      if (description == null || description.equals(""))
      {
        throw new IllegalArgumentException("Description non saisie");
      }
      else
      {
          this.description = description;
      }
  }
  
  public String getTitre() 
  {
    return this.titre;
  }
  
  public void setTitre(String titre) 
  {
      if (titre == null || titre.equals(""))
      {
          throw new IllegalArgumentException("Titre non saisie");
      }
      else
      {
          this.titre = titre;
      }
  }
  
  public String getVisuel() 
  {
    return this.visuel;
  }
  
  public void setVisuel(String visuel) 
  {
    this.visuel = visuel;
  }
  
  //-----------------------------------------------------------
    @Override
    public boolean equals(Object object)
    {
        Revue revue = (Revue) object;

        if (revue != null &&
            this.id == revue.id &&
            this.description.equals(revue.description) &&
            this.idPeriodicite == revue.idPeriodicite &&
            this.tarifNumero == revue.tarifNumero &&
            this.titre.equals(revue.titre) &&
            this.visuel.equals(revue.visuel))
        {
          return true;
        }
        else
        {
          return false;
        }
    }

    @Override
    public String toString()
    {
        return ("ID : " + this.getId() + ", titre : " + this.getTitre() + ", description : " + this.getDescription() + ", tarif unitaire : " + this.getTarifNumero() + ", visuel : " + this.getVisuel() + ", ID périodicité : " + this.getIdPeriodicite());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, idPeriodicite, tarifNumero, titre, description, visuel);
    }
}
