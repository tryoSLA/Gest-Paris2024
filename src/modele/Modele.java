package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import controleur.*;
import javax.swing.*;


public class Modele {

	public static String verifConnexion(String login, String mdp) {
		String requete = "Select count(*) as nb, role from Utilisateur" + " where pseudo ='" + login + "' and mot_de_passe ='" + mdp + "' group by id_personne; ";
		System.out.println(requete);
		String droits = "";
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);

			if (unRes.next()) {
				droits = unRes.getString("role");
				int nb = unRes.getInt("nb");
				if (nb == 0) droits = "";
			}
			unRes.close();
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return droits;
	}

	//*******************************   Function pour faire des entrée en  BDD  *******************************
	public static void ExecutionBdd(Bdd uneBdd, String requete) {
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			//Exécution de la BDD
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println(exp);
		}

	}


	//*******************************   Combo sport/pays/equipe   *******************************
	public static void Combo(String champ, String table, JComboBox nomCombo) {
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			String requete = "SELECT " + champ + " FROM " + table + ";";
			System.out.println(requete);
			ResultSet Rs = unStat.executeQuery(requete);

			while (Rs.next()) {
				nomCombo.addItem(Rs.getString(champ));
			}
			Rs.close();
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : ");
			//exp.printStackTrace();
		}
	}


	/*
	********************************************************************************************************************
	---------------------------------------------		   Pays			------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Pays> selectAllPays() {
		//list via le controleur des pays
		ArrayList<Pays> listPays = new ArrayList<Pays>();

		String requete = "SELECT id_pays, Libelle_pays, Description_pays, Image_pays FROM Pays;";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next()) {
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
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return listPays;
	}

	public static String selectWherePays(int idPays) {
		String libellePays = "";

		String requete = "SELECT Libelle_pays FROM Pays WHERE id_pays = " + idPays + ";";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);

			if (rs.next()) {
				//récupération des Champs par valeur
				libellePays = rs.getString("Libelle_pays");

			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return libellePays;
	}

	public static int selectIdWherePays(String libellePays) {
		int idpays = 0;

		String requete = "SELECT id_pays FROM Pays WHERE libelle_pays = \"" + libellePays + "\";";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);

			if (rs.next()) {
				//récupération des Champs par valeur
				idpays = rs.getInt("id_pays");
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return idpays;
	}

	public static void insertPays(Pays unPays) {
		String requete = "INSERT INTO Pays values (" +
				"null," +
				" '" + unPays.getLibelle() + "'," +
				" '" + unPays.getImage() + "' ," +
				" '" + unPays.getDescription() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

		ExecutionBdd(uneBdd, requete);
		System.out.println(requete);
	}

	public static void deletePays(Pays unPays) {
		String requete = "DELETE FROM pays WHERE id_pays = " + unPays.getIdPays() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void updatePays(Pays unPays) {
		String requete = "UPDATE pays " +
				"SET Libelle_pays = '" + unPays.getLibelle() + "'," +
				"	Image_pays = '" + unPays.getImage() + "'," +
				"	Description_pays = '" + unPays.getDescription() + "' " +
				"WHERE id_pays = '" + unPays.getIdPays() + "'";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
	}

	/*
	********************************************************************************************************************
	---------------------------------------------		   Sports		------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Sports> selectAllSports() {
		ArrayList<Sports> listSports = new ArrayList<Sports>();

		String requete = "SELECT * FROM Sport;";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next()) {
				//récupération des Champs par valeur
				int idSports = rs.getInt("id_sport");
				String nameSports = rs.getString("Libelle_sport");
				String descSports = rs.getString("Description_sport");
				String imageSports = rs.getString("Image_sport");
				//Mise à jour de la liste
				listSports.add(new Sports(idSports, nameSports, descSports, imageSports));
			}

			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return listSports;
	}

	public static int selectIdWhereSport(String libelleSport) {
		int idsport = 0;

		String requete = "SELECT id_sport FROM sport WHERE libelle_sport = \"" + libelleSport + "\";";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);

			if (rs.next()) {
				//récupération des Champs par valeur
				idsport = rs.getInt("id_sport");
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return idsport;
	}

	public static void insertSports(Sports unSports) {
		String requete = "INSERT INTO sport values (" +
				"null," +
				" '" + unSports.getLibelle() + "'," +
				" '" + unSports.getImage() + "' ," +
				" '" + unSports.getDescription() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

		ExecutionBdd(uneBdd, requete);
	}

	public static String selectWhereSport(int idSport) {
		String libelleSport = "";

		String requete = "SELECT Libelle_sport FROM Sport WHERE id_sport = " + idSport + ";";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);

			if (rs.next()) {
				//récupération des Champs par valeur
				libelleSport = rs.getString("Libelle_sport");

			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return libelleSport;
	}

	public static void updateSport(Sports unSport) {
		String requete =
				"UPDATE sport " +
						"SET Libelle_sport = '" + unSport.getLibelle() + "'," +
						"	Image_sport = '" + unSport.getImage() + "'," +
						"	Description_sport = '" + unSport.getDescription() + "' " +
						"WHERE id_sport = '" + unSport.getIdSports() + "'";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
	}

	public static void deleteSports(Sports unSports) {
		String requete =
				"DELETE FROM sport WHERE id_sport = " + unSports.getIdSports() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);

	}

	/*
	********************************************************************************************************************
	---------------------------------------------		   Athletes		------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Athletes> selectAllAthletes() {
		ArrayList<Athletes> listAthletes = new ArrayList<Athletes>();

		String requete =
				"SELECT * FROM athletes_java;";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next()) {
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
				listAthletes.add(new Athletes(idAthletes, nomAthletes, prenomAthletes, ageAthletes, genreAthletes, tailleAthletes, poidsAthletes, photoAthletes, biographieAthletes, idEquipeAthletes, idPaysAthletes, idSportAthletes));
			}

			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return listAthletes;
	}

	public static void insertAthleteAvecEquipe(Athletes unAthlete) {
		String requete =
				"CALL insert_athlete ('" + unAthlete.getNom() + "','" +
						unAthlete.getPrenom() + "'," +
						unAthlete.getAge() + ",'" +
						unAthlete.getGenre() + "'," +
						unAthlete.getTaille() + "," +
						unAthlete.getPoids() + ",'" +
						unAthlete.getPhoto() + "','" +
						unAthlete.getBiographie() + "'," +
						unAthlete.getId_pays() + "," +
						unAthlete.getId_equipe() + "," +
						unAthlete.getId_sport() + ");";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void insertAthleteSansEquipe(Athletes unAthlete) {
		String requete =
				"CALL insert_athlete ( '" + unAthlete.getNom() + "','" +
						unAthlete.getPrenom() + "'," +
						unAthlete.getAge() + ",'" +
						unAthlete.getGenre() + "'," +
						unAthlete.getTaille() + "," +
						unAthlete.getPoids() + ",'" +
						unAthlete.getPhoto() + "','" +
						unAthlete.getBiographie() + "'," +
						unAthlete.getId_pays() + "," +
						unAthlete.getId_equipe_string() + "," +
						unAthlete.getId_sport() + ");";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void updateAthleteAvecEquipe(Athletes unAthlete) {
		String requete =
				"Call update_athlete (" + unAthlete.getIdAthletes() +
						",'" + unAthlete.getNom() +
						"','" + unAthlete.getPrenom() +
						"'," + unAthlete.getAge() +
						",'" + unAthlete.getGenre() +
						"'," + unAthlete.getTaille() +
						"," + unAthlete.getPoids() +
						",'" + unAthlete.getPhoto() +
						"','" + unAthlete.getBiographie() +
						"'," + unAthlete.getId_pays() +
						"," + unAthlete.getId_equipe() +
						"," + unAthlete.getId_sport() + ");";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void updateAthleteSansEquipe(Athletes unAthlete) {
		String requete =
				"Call update_athlete (" + unAthlete.getIdAthletes() +
						",'" + unAthlete.getNom() +
						"','" + unAthlete.getPrenom() +
						"'," + unAthlete.getAge() +
						",'" + unAthlete.getGenre() +
						"'," + unAthlete.getTaille() +
						"," + unAthlete.getPoids() +
						",'" + unAthlete.getPhoto() +
						"','" + unAthlete.getBiographie() +
						"'," + unAthlete.getId_pays() +
						"," + unAthlete.getId_equipe_string() +
						"," + unAthlete.getId_sport() + ");";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void deleteAthlete(Athletes unAthlete) {
		String requete =
				"DELETE FROM athlete WHERE id_personne = " + unAthlete.getIdAthletes() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);

		String requete2 = "DELETE FROM personne WHERE id_personne = " + unAthlete.getIdAthletes() + ";";
		Bdd uneBdd2 = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete2);
		ExecutionBdd(uneBdd2, requete2);
	}

	/*
	********************************************************************************************************************
	---------------------------------------------		   Event 		------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Evenements> selectAllEvents() {
		ArrayList<Evenements> listEvents = new ArrayList<Evenements>();

		String requete = "SELECT * FROM 'evenement'";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next()) {
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
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return listEvents;
	}

	public static void insertEvents(Evenements unEvents) {
		String requete = "INSERT INTO Sport values (" +
				"null," +
				" '" + unEvents.getTitleEvents() + "'," +
				" '" + unEvents.getDescriptionEvents() + "," +
				" '" + unEvents.getDateEvents() + "'," +
				" '" + unEvents.getPhotoEvents() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

		ExecutionBdd(uneBdd, requete);
	}
	/*
	********************************************************************************************************************
	---------------------------------------------		   Equipe 		------------------------------------------------
	********************************************************************************************************************

	 */


//	public static ArrayList<Equipes> selectAllEquipes ()
//	{
//		ArrayList<Equipes> listEquipes = new ArrayList<Equipes>();
//
//		String requete = "SELECT * FROM 'equipe'";
//		System.out.println(requete);
//		Bdd uneBdd = new Bdd ("localhost","paris_2024", "user_paris2024","123");
//		try{
//			//Connexion à la base de donnée
//			uneBdd.seConnecter();
//			Statement unStat = uneBdd.getMaConnexion().createStatement();
//
//			//Exécution de la requète
//			ResultSet rs = unStat.executeQuery(requete);
//			while (rs.next())
//			{
//				//récupération des Champs par valeur
//				int idEquipe = rs.getInt("id_equipe");
//				String libelleEquipe = rs.getString("Libelle_equipe");
//				int Nbjoueursequipe = rs.getInt("Nb_joueurs_equipe");
//				int idSport = rs.getInt("id_sport");
//				//Mise à jour de la liste
//				listEquipes.add(new Equipes(idEquipe, libelleEquipe, Nbjoueursequipe, idSport));
//			}
//			//Fermeture de la connexion à la base de données
//			uneBdd.seDeConnecter();
//		}
//		catch( SQLException exp)
//		{
//			System.out.println("Erreur : "+ requete);
//			//exp.printStackTrace();
//		}
//		return listEquipes;
//	}

	public static String selectWhereEquipe(int idEquipe) {
		String libelleEquipe = "";

		String requete = "SELECT Libelle_equipe FROM equipe WHERE id_equipe = " + idEquipe + ";";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);

			if (rs.next()) {
				//récupération des Champs par valeur
				libelleEquipe = rs.getString("Libelle_equipe");

			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return libelleEquipe;
	}

	public static int selectIdWhereEquipe(String libelleEquipe) {
		int idequipe = 0;

		String requete = "SELECT id_equipe FROM Equipe WHERE libelle_equipe = \"" + libelleEquipe + "\";";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);

			if (rs.next()) {
				//récupération des Champs par valeur
				idequipe = rs.getInt("id_equipe");
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return idequipe;
	}


	/*
	********************************************************************************************************************
	---------------------------------------------       Utilisateurs    ------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Utilisateurs> selectAllUsers() {
		//list via le controleur des Users
		ArrayList<Utilisateurs> listUsers = new ArrayList<Utilisateurs>();

		String requete = "SELECT * FROM utilisateur_detaille;";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next()) {
				//récupération des Champs par valeur
				int idUser = rs.getInt("id_personne");
				String nomUser = rs.getString("Nom");
				String prenomUser = rs.getString("Prenom");
				int ageUser = rs.getInt("Age");
				String genreUser = rs.getString("Genre");
				String emailUser = rs.getString("email");
				String mdpUser = rs.getString("mot_de_passe");
				String pseudoUser = rs.getString("pseudo");
				String roleUser = rs.getString("role");
				//Mise à jour de la liste
				listUsers.add(new Utilisateurs(idUser, nomUser, prenomUser, ageUser, genreUser, roleUser,pseudoUser,emailUser, mdpUser));
			}

			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return listUsers;
	}

	public static void insertUtilisateur(Utilisateurs unUtilisateur) {
		String requete =
				"CALL insert_user ('" + unUtilisateur.getNom() + "','" +
						unUtilisateur.getPrenom() + "'," +
						unUtilisateur.getAge() + ",'" +
						unUtilisateur.getGenre() + "','" +
						unUtilisateur.getEmail() + "','" +
						unUtilisateur.getPseudo() + "','" +
						unUtilisateur.getMot_de_passe() + "','" +
						unUtilisateur.getRole() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void deleteUtilisateur(Utilisateurs unUtilisateur) {
		String requete =
				"DELETE FROM utilisateur WHERE id_personne = " + unUtilisateur.getId_personne() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);

		String requete2 = "DELETE FROM personne WHERE id_personne = " + unUtilisateur.getId_personne() + ";";
		Bdd uneBdd2 = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete2);
		ExecutionBdd(uneBdd2, requete2);
	}

	public static void updateUtilisateur(Utilisateurs unUtilisateur) {
		String requete =
				"Call update_user (" + unUtilisateur.getId_personne() + ",'" +
						unUtilisateur.getNom() + "','" +
						unUtilisateur.getPrenom() + "'," +
						unUtilisateur.getAge() + ",'" +
						unUtilisateur.getGenre() + "','" +
						unUtilisateur.getEmail() + "','" +
						unUtilisateur.getPseudo() + "','" +
						unUtilisateur.getMot_de_passe() + "','" +
						unUtilisateur.getRole() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);

	}


	/*
	********************************************************************************************************************
	---------------------------------------------		   Ville 		------------------------------------------------
	********************************************************************************************************************
	 */
	public static ArrayList<Ville> selectAllVilles() {
		ArrayList<Ville> listVille = new ArrayList<Ville>();

		String requete = "SELECT * FROM ville";
		System.out.println(requete);
		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete);
			while (rs.next()) {
				//récupération des Champs par valeur
				int idVille = rs.getInt("id_ville");
				String libelle_ville = rs.getString("Libelle_ville");

				//Mise à jour de la liste
				listVille.add(new Ville(idVille,libelle_ville));
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete);
			//exp.printStackTrace();
		}
		return listVille;
	}

	public static void insertVille(Ville uneVille) {
		String requete = "INSERT INTO ville values (null,'"
				+ uneVille.getLibelle_ville() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void updateVille(Ville uneVille) {
		String requete =
				"UPDATE ville " +
						"SET Libelle_ville = '" + uneVille.getLibelle_ville() +
						"' WHERE id_ville = " + uneVille.getId_ville() + "";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}

	public static void deleteVille(Ville uneVille) {
		String requete = "DELETE FROM ville WHERE id_ville = " + uneVille.getId_ville() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete);
		ExecutionBdd(uneBdd, requete);
	}
}