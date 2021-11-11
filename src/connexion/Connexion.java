package connexion;

import java.sql.*;

public class Connexion 
{
	protected Connection connect;
	protected Statement stmt;

	public Connection creeConnexion() 
	{
		 String url ="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/basbunar2u_base?serverTimezone=Europe/Paris";
		 String login = "basbunar2u_appli";
		 String pwd = "31912712"; 
		 Connection maConnexion = null;
		 try 
		 { 
		 maConnexion = DriverManager.getConnection(url, login, pwd);
		 this.connect = maConnexion;
		 } 
		 catch (SQLException sqle) 
		 {
		 System.out.println("Erreur : " + sqle.getMessage());
		 }
		 return maConnexion;
	}
	 

	public void createStatement( ) 
	{
		Statement Statut;
		try 
		{
			Statut = this.connect.createStatement();
			this.stmt= Statut;
		} 
		catch (SQLException e) 
		{
			System.out.println("\n************************************************\n"
			+"Erreur: " + e.getMessage()
			+"\n************************************************\n");
		}
	}
	
	
	public void choixFonction() 
	{
		System.out.println("Chosissez une fonction \n1 Ajouter\n2 Supprimer\n3 Modifier\n4 Menu principal\n5 Fermer");
	
	}
	
	public void closeConnection() 
	{
		try 
		{
			this.connect.close();
		}
		catch (SQLException e) 
		{
			System.out.println("\n************************************************\n"
			+"Erreur: " + e.getMessage()
			+"\n************************************************\n");
		}
	}
	
}
	
