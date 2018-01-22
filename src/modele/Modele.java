package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controleur.Pays;
import controleur.Sports;
import controleur.Athletes;
import controleur.Evenements;
import javax.print.DocFlavor;


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
	//*******************************   Function pour faire des entrée en  BDD  *******************************
	public static void ExecutionBdd (Bdd uneBdd, String requete)
	{
		try
		{
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			//Exécution de la BDD
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println(exp);
		}

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
	public static void insertPays(Pays unPays)
	{
		String requete = "INSERT INTO Pays values (" +
				"null," +
				" '"+unPays.getLibelle()+"'," +
				" '"+unPays.getImage()+"," +
				" '"+unPays.getDescription()+"');";

		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123");

		ExecutionBdd (uneBdd, requete);
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
	public static void insertSports(Sports unSports)
	{
		String requete = "INSERT INTO Sport values (" +
				"null," +
				" '"+unSports.getLibelle()+"'," +
				" '"+unSports.getImage()+"," +
				" '"+unSports.getDescription()+"');";

		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123");

		ExecutionBdd (uneBdd, requete);
	}
	/*
	********************************************************************************************************************
	---------------------------------------------		   Athletes		------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Athletes> selectAllAthletes ()
	{
		ArrayList<Athletes> listAthletes = new ArrayList<Athletes>();

		String requete =
				"SELECT Personne.Nom, Personne.Prenom, Personne.Age, Personne.Genre, " +
				"Pays.Libelle_pays, " +
				"Athlete.Photo, Athlete.Biographie, Athlete.Poids,Athlete.Taille " +
				"Athlete.id_sport, Athlete.id_sport, Athlete.id_sport," +
				"FROM 'athlete', 'personne'" +
				"WHERE Sport.id_sport = Athlete.id_sport AND Pays.id_pays = Athlete.id_pays AND  Personne.id_personne = Athlete.id_personne;";
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
				int idAthletes = rs.getInt("id_personne");
				String nomAthletes = rs.getString("Nom");
				String prenomAthletes = rs.getString("Prenom");
				int ageAthletes = rs.getInt("Age");
				String genreAthletes = rs.getString("Genre");
				String photoAthletes = rs.getString("Photo");
				String biographieAthletes = rs.getString("Biographie");
				float poidsAthletes = rs.getFloat("Poids");
				float tailleAthletes = rs.getFloat("Taille");
				int idSportAthletes = rs.getInt("id_sport");
				int idPaysAthletes = rs.getInt("id_pays");
				int idEquipeAthletes = rs.getInt("id_equipe");
				//Mise à jour de la liste
				listAthletes.add(new Athletes(idAthletes, nomAthletes, prenomAthletes, genreAthletes, photoAthletes, biographieAthletes,ageAthletes, tailleAthletes,poidsAthletes, idEquipeAthletes, idPaysAthletes, idSportAthletes));
			}

			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		}
		catch( SQLException exp)
		{
			System.out.println("Erreur : "+ requete);
			//exp.printStackTrace();
		}
		return listAthletes;
	}
	/*
	********************************************************************************************************************
	---------------------------------------------		   Event 		------------------------------------------------
	********************************************************************************************************************
	 */
	// Il faut rajouter id_ville et id_type_event
	public static ArrayList<Evenements> selectAllEvents ()
	{
		ArrayList<Evenements> listEvents = new ArrayList<Evenements>();

		String requete = "SELECT * FROM 'evenement'";
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
				int idEvents = rs.getInt("id_event");
				String titleEvents = rs.getString("Titre_event");
				String descEvents = rs.getString("Description_event");
				String photoEvents = rs.getString("Photo_sport");
				Date dateEvents = rs.getDate("Date_evenement");
				//Mise à jour de la liste
				listEvents.add(new Evenements(idEvents, titleEvents, descEvents, photoEvents, dateEvents));
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		}
		catch( SQLException exp)
		{
			System.out.println("Erreur : "+ requete);
			//exp.printStackTrace();
		}
		return listEvents;
	}
	public static void insertEvents(Evenements unEvents)
	{
		String requete = "INSERT INTO Sport values (" +
				"null," +
				" '"+unEvents.getTitleEvents()+"'," +
				" '"+unEvents.getDescriptionEvents()+"," +
				" '"+unEvents.getDateEvents()+"'," +
				" '"+unEvents.getPhotoEvents()+"');";

		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123");

		ExecutionBdd (uneBdd, requete);
	}


}
