package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import controleur.Client;



public class Modele {
	
	public static String verifConnexion (String login, String mdp)
	{
		String requete = "Select count(*) as nb, droits from user" +
				" where login ='" + login + "' and mdp ='"+mdp+"' ; ";
		String droits =""; 
		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123"); 
		try{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next())
			{
				droits = unRes.getString("droits"); 
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
