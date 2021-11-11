package LigneDeCommande;

import java.util.Scanner;

//import dao.Connexion;

public class Main {
	
	

	public static void main(String[] args) throws ClassNotFoundException {
	
		
		

		int reponseTable;
		int reponseTache;
		int reponseFin;
		int reponseAppliquer;
		//---REVUE------
		int idRevue;
		String titre;
		String description;
		float tarifNumero;
		String visuel;
		//--PERIODICITE---
		int idPeriodicite = 0;
		String libelle;
		//----CLIENT----
		int idClient;
		String nom;
		String prenom;
		String noRue;
		String voie;
		String codePostal;
		String ville;
		String pays;
		//-----ABONNEMENT-------
		int idAbonnement;
		int dateDebYear;
		int dateDebMonth;
		int dateDebDay;
		int dateFinYear;
		int dateFinMonth;
		int dateFinDay;
		//----------------------
		
		
		do 
		{
			do 
			{
				System.out.println("Avec quelle table voulez-vous travailler ? "
						+ "\n(1)Abonnement, (2)Client, (3)Périodicite, (4)Revue : ");
				
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				reponseTable = scanner.nextInt();
				
				
			} while (reponseTable != 1 && reponseTable != 2 && reponseTable != 3 && reponseTable != 4 );
			do 
			{
				System.out.println("Quelle tâche voulez-vous effectuer ? \n(1)Ajouter, (2)Modifier, (3)Afficher, (4)Supprimer, (5)Retour");
				
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				reponseTache = scanner.nextInt();
				
			
				
			} while (reponseTache != 1 && reponseTache != 2 && reponseTache != 3 && reponseTache != 4 && reponseTache != 5);
		
			if(reponseTache == 5)
			{
				reponseTable = 0;
			}
			
			if(reponseTable == 1) //abonnement
			{
				if(reponseTache == 1) //ajout
				{
					System.out.println("Vous voulez ajouter un Abonnement ! ");
					System.out.println("Entrez l'id Abonnement : (exemple : 4)");
					@SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
					idAbonnement = scanner.nextInt();
					
					//----------------dateDebut---------------
					@SuppressWarnings("resource")
					Scanner scanner2 = new Scanner(System.in);
					do {
						System.out.println("Entrez l'année de la date de début(exemple : 2021 )");
						
						dateDebYear = scanner2.nextInt();
					} while (dateDebYear==0 || dateDebYear>9999);
					
					do {
						System.out.println("Entrez le mois de la date de début(exemple : 05 )");
						dateDebMonth = scanner2.nextInt();
					} while(dateDebMonth < 1 || dateDebMonth > 12);
					
					do {
						System.out.println("Entrez le jour de la date de début(exemple : 24 )");
						dateDebDay = scanner2.nextInt();
					}while(dateDebDay<=0 || dateDebDay > 31);
					
					
					//-----------------dateFin----------------------
					do {
						System.out.println("Entrez l'année de la date de fin (exemple : 2021 )");
						dateFinYear = scanner2.nextInt();
					} while(dateFinYear<0 || dateFinYear>9999);
					
					do {
						System.out.println("Entrez le mois de la date de fin (exemple : 05 )");
						dateFinMonth = scanner2.nextInt();
					} while(dateFinMonth < 1 || dateFinMonth > 12);
					
					do {
						System.out.println("Entrez le jour de la date de fin (exemple : 24 )");
						dateFinDay = scanner2.nextInt();
					}while(dateFinDay<=0 || dateFinDay > 31);
					
					//----------------------------------------------
					System.out.println("Entrez l'id du client : (exemple : 120 )");
					idClient = scanner2.nextInt();
					//-----------------------------------------------
					System.out.println("Entrez l'id de la Revue : (exemple : 252 )");
					idRevue = scanner2.nextInt(); //
					//---------------------------------------------
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_Abonnement : "+idAbonnement+"\n"
										+ "date_debut : "+dateDebYear+"-"+dateDebMonth+"-"+dateDebDay+"\n"
												+ "date_fin : "+dateFinYear+"-"+dateFinMonth+"-"+dateFinDay+"\n"
														+ "id_client: "+idClient+"\n"
																+ "id_revue : "+idRevue+"\n"
						+ "Appliquer ou Modifier ? (1/0)\n");
						
						reponseAppliquer = scanner2.nextInt();
						
					} while (reponseAppliquer != 1 && reponseAppliquer != 0);
					if(reponseAppliquer == 1)
					{
						//Connexion.ajoutTableAbo(idAbonnement, dateDebYear, dateDebMonth, dateDebDay, dateFinYear, dateFinMonth, dateFinDay, idClient, idRevue);
					} 
					
				}
				else if(reponseTache == 2) // Modifier
				{
					@SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
					System.out.println("Vous voulez modifier un Abonnement ! ");
					System.out.println("Entrez l'id Abonnement : (exemple : 4)");
					//----------
					idAbonnement = scanner.nextInt();
					//----------------dateDebut---------------
					System.out.println("Entrez l'année de la date de début(exemple : 2021");
					dateDebYear = scanner.nextInt();
					
					System.out.println("Entrez le mois de la date de début(exemple : 05");
					dateDebMonth = scanner.nextInt();
					
					System.out.println("Entrez le jour de la date de début(exemple : 24");
					dateDebDay = scanner.nextInt();
					
					//-----------------dateFin----------------------
					System.out.println("Entrez l'année date de fin (exemple : 2022");
					dateFinYear = scanner.nextInt();
					
					System.out.println("Entrez le mois de la date de fin (exemple : 06 ");
					dateFinMonth = scanner.nextInt();
					
					System.out.println("Entrez le jour de la date de fin (exemple : 31");
					dateFinDay = scanner.nextInt();
					
					System.out.println("Entrez l'id du client : (exemple : 120");
					idClient = scanner.nextInt();
					//--------------------
					System.out.println("Entrez l'id de la Revue : (exemple : 252)");
					idRevue = scanner.nextInt(); //
					//----------------------
					@SuppressWarnings("resource")
					Scanner scanner3 = new Scanner(System.in);
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_Abonnement : "+idAbonnement+"\n"
										+ "date_debut : "+dateDebYear+"-"+dateDebMonth+"-"+dateDebDay+"\n"
												+ "date_fin : "+dateFinYear+"-"+dateFinMonth+"-"+dateFinDay+"\n"
														+ "id_client: "+idClient+"\n"
																+ "id_revue : "+idRevue+"\n"
						+ "Appliquer ou Modifier ? (a/m)\n");
						reponseAppliquer = scanner3.nextLine().charAt(0);
					} while (reponseAppliquer != 'a' && reponseAppliquer != 'o');
					if(reponseAppliquer == 'a')
					{
						//Connexion.modifTableAbonnement(idAbonnement, dateDebYear, dateDebMonth, dateDebDay, dateFinYear, dateFinMonth, dateFinDay, idClient, idRevue);
					}
				}
				else if(reponseTache == 3 ) //afficher
				{
					
					//Connexion.affiche("Abonnement");
				}
				else // Supprimer
				{
					System.out.println("Etrez l'id_Abonnement de la table que vous voulez supprimer");
				}
			}
			else if(reponseTable == 2) // Client
			{
				
				if(reponseTache == 1) //ajout
				{
					System.out.println("Vous voulez ajouter un client ! ");
					
					do {
						System.out.println("Entrez l'id client : (exemple : 5)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						idClient = scannerClient.nextInt();
						
					} while(idClient > 999_999_999);
					//----------------------
					do {
						System.out.println("Entrez le nom du client (maximum : 30 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						nom = scannerClient.nextLine();
						
					} while (nom.length()>30);
					
					do {
						System.out.println("Entrez le prénom du client (maximum : 30 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						prenom = scannerClient.nextLine();
						
					} while(prenom.length()>30);
					
					do {
						System.out.println("Entrez le numéro de rue (exemple : 5B / 1 bis / 3 ; max : 8 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						noRue = scannerClient.nextLine();
						
					}while(noRue.length()>8);
					
					do {
						System.out.println("Entrez la voie (exemple : rue de Londres ; max : 80 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						voie = scannerClient.nextLine(); 
						
					} while(voie.length()>80);
					
					do {
						System.out.println("Entrez le code postal (exemple : 57000 ; max : 10 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						codePostal = scannerClient.nextLine();
						
					} while(codePostal.length()>10);
					
					do {
						System.out.println("Entrez le nom de la ville (exemple : Metz ; max : 30 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						ville = scannerClient.nextLine(); 
						
					}while(ville.length()>30);
					
					do {
						System.out.println("Entrez le nom du pays (exemple : France ; max : 30 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						pays = scannerClient.nextLine(); 
						
					}while(pays.length()>30);
					
					
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_client : "+idClient+"\n"
								+ "nom : "+nom+"\n"
								+ "prenom : "+prenom+"\n"
								+ "no_rue: "+noRue+"\n"
								+ "voie : "+voie+"\n"
								+ "code_postal : "+codePostal+"\n"
								+ "ville : "+ville+"\n"
								+ "pays : "+pays+"\n"						
								+ "(1)Appliquer ou (0)Annuler ? (1/0)\n");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						reponseAppliquer = scannerClient.nextInt();
						
					} while (reponseAppliquer != 1 && reponseAppliquer != 0);
					if(reponseAppliquer == 1)
					{
						//Connexion.ajoutTableClient(idClient, nom, prenom, noRue, voie, codePostal, ville, pays);
					} 
				
				}
				else if(reponseTache == 2) //modif
				{
					System.out.println("Vous voulez modifier un client ! ");
					
					do {
						System.out.println("Entrez l'id client à modifier: (exemple : 5)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						idClient = scannerClient.nextInt();
						
					} while(idClient > 999_999_999);
					//----------------------
					do {
						System.out.println("Entrez le nom du client (maximum : 30 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						nom = scannerClient.nextLine();
						
					} while (nom.length()>30);
					
					do {
						System.out.println("Entrez le prénom du client (maximum : 30 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						prenom = scannerClient.nextLine();
						
					} while(prenom.length()>30);
					
					do {
						System.out.println("Entrez le numéro de rue (exemple : 5B / 1 bis / 3 ; max : 8 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						noRue = scannerClient.nextLine();
						
					}while(noRue.length()>8);
					
					do {
						System.out.println("Entrez la voie (exemple : rue de Londres ; max : 80 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						voie = scannerClient.nextLine(); 
						
					} while(voie.length()>80);
					
					do {
						System.out.println("Entrez le code postal (exemple : 57000 ; max : 10 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						codePostal = scannerClient.nextLine();
						
					} while(codePostal.length()>10);
					
					do {
						System.out.println("Entrez le nom de la ville (exemple : Metz ; max : 30 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						ville = scannerClient.nextLine(); 
						
					}while(ville.length()>30);
					
					do {
						System.out.println("Entrez le nom du pays (exemple : France ; max : 30 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						pays = scannerClient.nextLine(); 
						
					}while(pays.length()>30);
					
					
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_client : "+idClient+"\n"
								+ "nom : "+nom+"\n"
								+ "prenom : "+prenom+"\n"
								+ "no_rue: "+noRue+"\n"
								+ "voie : "+voie+"\n"
								+ "code_postal : "+codePostal+"\n"
								+ "ville : "+ville+"\n"
								+ "pays : "+pays+"\n"						
								+ "(1)Appliquer ou (0)Annuler ? (1/0)\n");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						reponseAppliquer = scannerClient.nextInt();
					
					} while (reponseAppliquer != 1 && reponseAppliquer != 0);
					if(reponseAppliquer == 1)
					{
						//Connexion.modifTableClient(idClient, nom, prenom, noRue, voie, codePostal, ville, pays);
					} 

				}
				else if(reponseTache == 3) //affiche
				{
					//Connexion.affiche("Client");
				}
				else // suppr
				{
					System.out.println("Etrez l'id_Client de la table que vous voulez supprimer");
				}
					
			}
			else if(reponseTable == 3 ) //periodicite
			{
				@SuppressWarnings("resource")
				Scanner scannerP = new Scanner(System.in);
				if(reponseTache == 1) //ajout
				{
						System.out.println("Vous voulez ajouter une périodicité ! ");
						
						do {
							System.out.println("Entrez l'id périodicité : (max : 99)");
							idPeriodicite = scannerP.nextInt();
							
						} while(idPeriodicite > 99);
					
						do {
							System.out.println("Entrez le libellé de la périodicité : (max : 20 caractères)");
						
							@SuppressWarnings("resource")
							Scanner scanner = new Scanner(System.in);
							libelle = scanner.nextLine();
							
						} while (libelle.length()>20);
						
						do {
							System.out.println("*************RECAPITULATIF************\n"
									+ "id_periodicite : "+idPeriodicite+"\n"
									+ "libelle : "+libelle+"\n"						
									+ "(1)Appliquer ou (0)Annuler ? (1/0)\n");
							@SuppressWarnings("resource")
							Scanner scannerClient = new Scanner(System.in);
							reponseAppliquer = scannerClient.nextInt();
							
						} while (reponseAppliquer != 1 && reponseAppliquer != 0);
						if(reponseAppliquer == 1)
						{
							//Connexion.ajoutTablePeriodicite(idPeriodicite, libelle);
						}

				}
				else if(reponseTache == 2) // modif
				{
					System.out.println("Vous voulez modifier une périodiicté  ! ");
					
					do {
						System.out.println("Entrez l'id périodicité à modifier : (max : 99)");
						idPeriodicite = scannerP.nextInt();
						
					} while(idPeriodicite > 99);
				
					do {
						System.out.println("Entrez le libellé de la périodicité : (max : 20 caractères)");
						@SuppressWarnings("resource")
						Scanner scanner = new Scanner(System.in);
						libelle = scanner.nextLine();
						
					} while (libelle.length()>20);
					
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_periodicite : "+idPeriodicite+"\n"
								+ "libelle : "+libelle+"\n"						
								+ "(1)Appliquer ou (0)Annuler ? (1/0)\n");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						reponseAppliquer = scannerClient.nextInt();
						
					} while (reponseAppliquer != 1 && reponseAppliquer != 0);
					if(reponseAppliquer == 1)
					{
						//Connexion.modifTablePeriodicite(idPeriodicite, libelle);
					}
				
				}
				else if(reponseTache == 3) //affiche 
				{
					//Connexion.affiche("Periodicite");
				}
				else // suppr
				{
					System.out.println("Etrez l'id_Periodicite de la table que vous voulez supprimer");
				}
			}
			else if(reponseTable == 4) // revue 
			{
				if(reponseTache == 1) //ajout
				{
					System.out.println("Vous voulez ajouter une revue ! ");
					
					do {
						System.out.println("Entrez l'id revue : (exemple : 5)");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						idRevue = scannerR.nextInt();
						
					} while(idRevue > 9999);
					//----------------------
					do {
						System.out.println("Entrez le titre de la revue (maximum : 40 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						titre = scannerR.nextLine();
						
					} while (titre.length()>30);
					
					do {
						System.out.println("Entrez la description (maximum : 400 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						description = scannerR.nextLine();
						
					} while(description.length()>400);
					
					
						System.out.println("Entrez le numéro de tarif (exemple : 0,25 (virgule))");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						tarifNumero = scannerR.nextFloat();
					
					do {
						System.out.println("Entrez le visuel (max : 200 caractères)");
						@SuppressWarnings("resource")
						Scanner scanner = new Scanner(System.in);
						visuel = scanner.nextLine(); 
						
					} while(visuel.length()>200);
					
					do {
						System.out.println("Entrez son id_périodicité (exemple : 5 ; max : 99)");
						@SuppressWarnings("resource")
						Scanner scanner = new Scanner(System.in);
						idPeriodicite = scanner.nextInt();
						
					} while(idPeriodicite>99);
					
					
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_revue : "+idRevue+"\n"
								+ "tiitre : "+titre+"\n"
								+ "description : "+description+"\n"
								+ "tarifNumero: "+tarifNumero+"\n"
								+ "visuel : "+visuel+"\n"
								+ "id_periodicite : "+idPeriodicite+"\n"					
								+ "(1)Appliquer ou (0)Annuler ? (1/0)\n");
						@SuppressWarnings("resource")
						Scanner scanner = new Scanner(System.in);
						reponseAppliquer = scanner.nextInt();
						
					} while (reponseAppliquer != 1 && reponseAppliquer != 0);
					if(reponseAppliquer == 1)
					{
						//Connexion.ajoutTableRevue(idRevue, titre, description, tarifNumero, visuel, idPeriodicite);
					} 
					
				}
				
				else if(reponseTache == 2) //modif
				{
					System.out.println("Vous voulez modifier une revue ! ");
					
					do {
						System.out.println("Entrez l'id revue de la colonne  à modifier: (exemple : 5)");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						idRevue = scannerR.nextInt();
						
					} while(idRevue > 9999); 
					//----------------------
					do {
						System.out.println("Entrez le titre de la revue (maximum : 40 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerClient = new Scanner(System.in);
						titre = scannerClient.nextLine();
						
					} while (titre.length()>40);
					
					do {
						System.out.println("Entrez la description de la revue (maximum : 400 caractères)");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						description = scannerR.nextLine();
						
					} while(description.length()>400);
					
					do {
						System.out.println("Entrez le numéro de tarif (exemple : 0,25 (virgule )");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						tarifNumero = scannerR.nextFloat();
						
					}while(tarifNumero>100);
					
					do {
						System.out.println("Entrez le visuel (max : 200 caractères )");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						visuel = scannerR.nextLine(); 
						
					} while(visuel.length()>200);
					
					do {
						System.out.println("Entrez l'id périodicité (exemple : 5 ; max : 99 )");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						idPeriodicite = scannerR.nextInt();
						
					} while(idPeriodicite>99);
					
					do {
						System.out.println("*************RECAPITULATIF************\n"
								+ "id_revue : "+idRevue+"\n"
								+ "titre : "+titre+"\n"
								+ "description : "+description+"\n"
								+ "tarif_numero: "+tarifNumero+"\n"
								+ "visuel : "+visuel+"\n"
								+ "id_periodicite : "+idPeriodicite+"\n"					
								+ "(1)Appliquer ou (0)Annuler ? (1/0)\n");
						@SuppressWarnings("resource")
						Scanner scannerR = new Scanner(System.in);
						reponseAppliquer = scannerR.nextInt();
					
					} while (reponseAppliquer != 1 && reponseAppliquer != 0);
					if(reponseAppliquer == 1)
					{
						//Connexion.modifTableRevue(idRevue, titre, description, tarifNumero, visuel, idPeriodicite);
					} 
					
				}
				else if(reponseTache == 3) //affiche
				{
				//	Connexion.affiche("Revue");
				}
				else //suppr
				{
					System.out.println("Etrez l'id_Revue de la table que vous voulez supprimer");
				}
			}
				
			
			
			System.out.println("Voulez-vous effectuer une autre tâche ? (1/0)");
			@SuppressWarnings("resource")
			Scanner scannerP = new Scanner(System.in);
			reponseFin = scannerP.nextInt();
			
		} while (reponseFin == 1);
		System.out.println("Au revoir ! ");
		
		
		
		
		
		/*
		ResultSet resultats = null;
		String requete = "SELECT * FROM client";
		try {
			Statement stmt = conn.createStatement();
			resultats = stmt.executeQuery(requete);
		}
		catch (SQLException sqle)
		{
			System.out.println("Pb select " + sqle.getMessage());
		}
		//----------------------------------------------
		//insertion d'un enregistrement dans la table client
		
		requete = "INSERT INTO client VALUES (45,'client 3','prenom 3')";
		try {
		   Statement stmt = conn.createStatement();
		   int nbMaj = stmt.executeUpdate(requete);
		   System.out.println("nb mise a jour = "+nbMaj);
		} catch (SQLException e) {
		   e.printStackTrace();
		}		
		//--------------------------------------------------------
		requete = "INSERT INTO client VALUES (45,'client 3','prenom 3')";
		try {
			ResultSetMetaData rsmd;
			rsmd = resultats.getMetaData();
			int nbCols = rsmd.getColumnCount();
			String nomCols = rsmd.getColumnName(1);
			System.out.println(nbCols);
			System.out.println(nomCols);
		} catch (SQLException e) {
		   e.printStackTrace();
		}
		*/
		
		
		
		//Connexion.ajoutTable("Periodicite","22","mensuel");
	}


	
}
