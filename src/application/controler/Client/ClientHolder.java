package application.controler.Client;

import modele.Client;

public class ClientHolder
{
    private Client client;

    private final static ClientHolder INSTANCE = new ClientHolder();

    private ClientHolder() {}
    
    public static ClientHolder getInstance()
    {
        return INSTANCE;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Client getClient()
    {
        return this.client;
    }
}
