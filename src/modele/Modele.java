package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Pays;
import controleur.Sports;



public class Modele {
	
	public static String verifConnexion (String login, String mdp)
	{
		String requete = "Select count(*) as nb, role from Utilisateur" +	" where pseudo ='" + login + "' and mot_de_passe ='"+mdp+"' group by id_personne; ";
		System.out.println(requete);
		String droits =""; 
		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123"); 
		try{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);

			if (unRes.next())
			{
				droits = unRes.getString("role"); 
				int nb = unRes.getInt("nb");
				if (nb==0) droits ="";
			}
			unRes.close(); 
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch( SQLException exp)
		{
			System.out.println("Erreur : "+ requete);
			//exp.printStackTrace();
		}
		return droits;
	}
	/*
	********************************************************************************************************************
	---------------------------------------------		   Pays			------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Pays> selectAllPays ()
	{
		//list via le controleur des pays
		ArrayList<Pays> listPays = new ArrayList<Pays>();

		String requete = "SELECT id_pays, Libelle_pays, Description_pays, Image_pays FROM 'Pays'";
		System.out.println(requete);
		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123");
		try{
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next())
			{
				//récupération des Champs par valeur
				int idPays = rs.getInt("id_pays");
				String namePays = rs.getString("Libelle_pays");
				String descPays = rs.getString("Description_pays");
				String imagePays = rs.getString("Image_pays");
				//Mise à jour de la liste
				listPays.add(new Pays(idPays, namePays, descPays, imagePays));

			}

			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		}
		catch( SQLException exp)
		{
			System.out.println("Erreur : "+ requete);
			//exp.printStackTrace();
		}
		return listPays;
	}
	/*
	********************************************************************************************************************
	---------------------------------------------		   Sports		------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Sports> selectAllSports ()
	{
		ArrayList<Sports> listSports = new ArrayList<Sports>();

		String requete = "SELECT id_sports, Libelle_sport, Description_sport, Image_sport FROM 'Pays'";
		System.out.println(requete);
		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123");
		try{
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next())
			{
				//récupération des Champs par valeur
				int idSports = rs.getInt("id_sports");
				String nameSports = rs.getString("Libelle_sport");
				String descSports = rs.getString("Description_sport");
				String imageSports = rs.getString("Image_sport");
				//Mise à jour de la liste
				listSports.add(new Sports(idSports, nameSports, descSports, imageSports));

			}

			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		}
		catch( SQLException exp)
		{
			System.out.println("Erreur : "+ requete);
			//exp.printStackTrace();
		}
		return listSports;


	}

}

//	public static ArrayList<Client> selectAllClients ()
//	{
//		ArrayList<Client> lesClients = new ArrayList<Client>();
//		String requete = "SELECT * from client;";
//		Bdd uneBdd = new Bdd("localhost", "intervention", "root", "");
//		try
//		{
//			uneBdd.seConnecter();
//			Statement unStat = uneBdd.getMaConnexion().createStatement();
//			ResultSet unRes = unStat.executeQuery(requete);
//			while (unRes.next())
//			{
////				int idclient = unRes.getInt("idclient");
////				String nom = unRes.getString("nom");
////				String prenom = unRes.getString("prenom");
////				String adresse = unRes.getString("adresse");
////				Client unCLient = new Client (idclient, nom, prenom, adresse);
////				lesClients.add(unCLient)
//				
//				lesClients.add(new Client (unRes.getInt("idclient"), unRes.getString("nom"), unRes.getString("prenom"), 
//						unRes.getString("adresse")) );
//						
//			}
//			unStat.close();
//			unRes.close();
//			uneBdd.seDeConnecter();
//		}
//		catch (SQLException exp)
//		{
//			System.out.println("Erreur : "+ requete);
//		}
//		return lesClients;
//	}
//	
//	public static void insertClient (Client unClient)
//	{
//		String requete = "INSERT INTO client VALUES (null,  '"+unClient.getNom() +
//				"','"+unClient.getPrenom() +
//				"','" +unClient.getAdresse() +
//				"');";
//		Bdd uneBdd = new Bdd ("localhost", "intervention", "root", "");
//		try
//		{
//			uneBdd.seConnecter();
//			Statement unStat = uneBdd.getMaConnexion().createStatement();
//			unStat.execute(requete);
//			unStat.close();
//			uneBdd.seDeConnecter();
//		}
//		catch (SQLException exp)
//		{
//			System.out.println("Erreur : "+ requete);
//		}
//	}
//	
//	public static void deleteClient (Client unClient)
//	{
//		String requete = "DELETE FROM client WHERE idclient =" + unClient.getIdclient() + ";";
//		Bdd uneBdd = new Bdd ("localhost", "intervention", "root", "");
//		try
//		{
//			uneBdd.seConnecter();
//			Statement unStat = uneBdd.getMaConnexion().createStatement();
//			unStat.execute(requete);
//			unStat.close();
//			uneBdd.seDeConnecter();
//		}
//		catch (SQLException exp)
//		{
//			System.out.println("Erreur : "+ requete);
//		}
//	}
//	
//	public static void updateClient (Client unClient)
//	{
//		String requete = "UPDATE client SET nom = '"+ unClient.getNom() +
//				"',prenom = '" + unClient.getPrenom() +
//				"', adresse = '" +unClient.getAdresse() +
//				"' WHERE idclient =" + unClient.getIdclient() + ";";
//		
//		Bdd uneBdd = new Bdd ("localhost", "intervention", "root", "");
//		try
//		{
//			uneBdd.seConnecter();
//			Statement unStat = uneBdd.getMaConnexion().createStatement();
//			unStat.execute(requete);
//			unStat.close();
//			uneBdd.seDeConnecter();
//		}
//		catch (SQLException exp)
//		{
//			System.out.println("Erreur : "+ requete);
//		}
//	}
//}
