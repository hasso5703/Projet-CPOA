package modele;


import java.util.Objects;

import modele.Client;

public class Client
{
    private int id;
    private String nom;
    private String prenom;
    private Adresse adresse;

    //---------------CONSTRUCTEURS-----------------------------------------
    public Client(String nom, String prenom, Adresse adresse) 
    {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Client(int id, String nom, String prenom, Adresse adresse) 
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Client(int id) 
    {
        this.id = id;
    }

    //-----------------------GETTERS/SETTERS-------------------------------------
    public int getId() 
    {
        return this.id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNom() 
    {
        return this.nom;
    }

    public void setNom(String nom) 
    {
        if (nom == null || nom.equals(""))
        {
            throw new IllegalArgumentException("Nom incorrect");
        }
        else
        {
            this.nom = nom;
        }
    }

    public String getPrenom() 
    {
        return this.prenom;
    }

    public void setPrenom(String prenom)
    {
        if (prenom == null || prenom.equals(""))
        {
            throw new IllegalArgumentException("Prenom incorrect");
        }
        else
        {
            this.prenom = prenom;
        }
    }
    
    public Adresse getAdresse() 
    {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) 
    {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object object)
    {
        Client client = (Client) object;

        if (client != null &&
            this.id == client.id &&
            this.nom.equals(client.nom) &&
            this.prenom.equals(client.prenom) &&
            this.adresse.equals(client.adresse)) 
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
        return (this.getId() + " " + this.getNom() + " " + this.getPrenom() + " " + this.adresse.toString());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, nom, prenom, adresse);
    }
}
