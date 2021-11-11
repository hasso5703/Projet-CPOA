package modele;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import modele.Abonnement;

public class Abonnement
{
    private int id;
    private int idClient;
    private int idRevue;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int idDureeChoisie;
    
    //-------CONSTRUCTEURS---------------------------------------------------------------

    public Abonnement(LocalDate dateDebut, LocalDate dateFin, Client client, Revue revue, Duree duree)
    {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idClient = client.getId();
        this.idRevue = revue.getId();
        this.idDureeChoisie = duree.getIdDuree();
    }

    public Abonnement(int id, LocalDate dateDebut, LocalDate dateFin, Client client, Revue revue, Duree duree)
    {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idClient = client.getId();
        this.idRevue = revue.getId();
        this.idDureeChoisie = duree.getIdDuree();
    }

    public Abonnement(int id) {
        this.id = id;
    }

    //------------------EQUALS---------------------------------------
    
    @Override
    public boolean equals(Object object)
    {
        Abonnement abo = (Abonnement)object;

        if (abo != null &&
            this.id == abo.id &&
            this.idClient == abo.idClient &&
            this.idRevue == abo.idRevue &&
            this.dateDebut.equals(abo.dateDebut) &&
            this.dateFin.equals(abo.dateFin)) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //-----------------TO STRING------------------------------------------------
    @Override
    public String toString()
    {
        DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ("\n" + this.getId() + " \nID Client : " + this.getIdClient() + " \nID Revue : " + this.getIdRevue() + " \nDate de debut : " + this.getDateDebut().format(formatage) + " \nDate de fin : " + this.getDateFin().format(formatage));
    }

    //-----------------HASHCODE-------------------------------------------------
    
    @Override
    public int hashCode()
    {
        return Objects.hash(id, idClient, idRevue, dateDebut, dateFin);
    }
    
    //-------------GETTERS/SETTERS----------------------------------------------------------------
    public int getId()
    {
        return id;
    }
    
    public int getIdDureeChoisie()
    {
        return idDureeChoisie;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getIdClient()
    {
        return idClient;
    }

    public void setIdClient(Client client)
    {
        if (client == null)
        {
            throw new IllegalArgumentException("Client non saisie");
        }
        else
        {
            this.idClient = client.getId();
        }
    }
    
    public void setIdDureeChoisie(Duree duree)
    {
        if (duree == null)
        {
            throw new IllegalArgumentException("duree non saisie");
        }
        else
        {
            this.idDureeChoisie = duree.getIdDuree();
        }
    }

    public int getIdRevue()
    {
        return idRevue;
    }

    public void setIdRevue(Revue revue)
    {
        if (revue == null)
        {
            throw new IllegalArgumentException("Revue non saisie");
        }
        else
        {
            this.idRevue = revue.getId();
        }    }

    public LocalDate getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut)
    {
        if (dateDebut == null)
        {
            throw new IllegalArgumentException("Date de debut non saisie");
        }
        else
        {
            this.dateDebut = dateDebut;
        }
    }

    public LocalDate getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin)
    {
        if (dateFin == null)
        {
            throw new IllegalArgumentException("Date de fin non saisie");
        }
        else if (this.getDateDebut().isAfter(dateFin))
        {
            throw new IllegalArgumentException("Date de fin apres la date de debut");
        }
        else
        {
            this.dateFin = dateFin;
        }
    } 

}
