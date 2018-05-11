package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.security.*;
import java.security.MessageDigest;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;


import controleur.*;
import sun.security.provider.MD5;

import javax.swing.*;


public class Modele {


	public static String verifConnexion(String login, String mdp) {
		String requete = "Select count(*) as nb, role from Utilisateur" + " where pseudo ='" + login + "' and mot_de_passe ='" + MD5(mdp) + "' group by id_personne; ";
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
			System.out.println(exp.getMessage());
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

	public static int insertPays(Pays unPays) {
		String requete = "INSERT INTO Pays values (" +
				"null," +
				" '" + unPays.getLibelle() + "'," +
				" '" + unPays.getImage() + "' ," +
				" '" + unPays.getDescription() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
		int key = Modele.selectLasInsertId(requete,uneBdd);
		return key;
	}

	public static void deletePays(Pays unPays) {
		String requete = "DELETE FROM pays WHERE id_pays = " + unPays.getIdPays() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

		ExecutionBdd(uneBdd, requete);
	}

	public static void updatePays(Pays unPays) {
		String requete = "UPDATE pays " +
				"SET Libelle_pays = '" + unPays.getLibelle() + "'," +
				"	Image_pays = '" + unPays.getImage() + "'," +
				"	Description_pays = '" + unPays.getDescriptionClean() + "' " +
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

	public static int selectIdWhereSport(String libelleSport) {
		int idsport = 0;

		String requete = "SELECT id_sport FROM sport WHERE libelle_sport = \"" + libelleSport + "\";";

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

	public static int insertSports(Sports unSports) {
		String requete = "INSERT INTO sport values (" +
				"null," +
				" '" + unSports.getLibelle() + "'," +
				" '" + unSports.getImage() + "' ," +
				" '" + unSports.getDescriptionClean() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
		int key = Modele.selectLasInsertId(requete,uneBdd);
		return key;
	}

	public static void updateSport(Sports unSport) {
		String requete =
				"UPDATE sport " +
						"SET Libelle_sport = '" + unSport.getLibelle() + "'," +
						"	Image_sport = '" + unSport.getImage() + "'," +
						"	Description_sport = '" + unSport.getDescriptionClean() + "' " +
						"WHERE id_sport = '" + unSport.getIdSports() + "'";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
	}

	public static void deleteSports(Sports unSports) {
		String requete =
				"DELETE FROM sport WHERE id_sport = " + unSports.getIdSports() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

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

	public static int insertAthleteAvecEquipe(Athletes unAthlete) {
		String requete =
				"CALL insert_athlete ('" + unAthlete.getNom() + "','" +
						unAthlete.getPrenom() + "'," +
						unAthlete.getAge() + ",'" +
						unAthlete.getGenre() + "'," +
						unAthlete.getTaille() + "," +
						unAthlete.getPoids() + ",'" +
						unAthlete.getPhoto() + "','" +
						unAthlete.getBiographieClean() + "'," +
						unAthlete.getId_pays() + "," +
						unAthlete.getId_equipe() + "," +
						unAthlete.getId_sport() + ");";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);

		Integer id = 0;
		String requete1 =
				"SELECT last_insert_id(id_personne) AS id FROM athlete ORDER BY id DESC LIMIT 1;";
		System.out.println(requete1);
		try {
			//Connexion à la base de donnée
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();

			//Exécution de la requète
			ResultSet rs = unStat.executeQuery(requete1);

			if (rs.next()) {
				//récupération des Champs par valeur
				id = rs.getInt("id");
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete1);
			//exp.printStackTrace();
		}
		return id;
	}

	public static int insertAthleteSansEquipe(Athletes unAthlete) {
		String requete =
				"CALL insert_athlete ( '" + unAthlete.getNom() + "','" +
						unAthlete.getPrenom() + "'," +
						unAthlete.getAge() + ",'" +
						unAthlete.getGenre() + "'," +
						unAthlete.getTaille() + "," +
						unAthlete.getPoids() + ",'" +
						unAthlete.getPhoto() + "','" +
						unAthlete.getBiographieClean() + "'," +
						unAthlete.getId_pays() + "," +
						unAthlete.getId_equipe_string() + "," +
						unAthlete.getId_sport() + ");";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);

		Integer id_ = 0;
		String requete1 =
				"SELECT last_insert_id(id_personne) AS id_ FROM athlete ORDER BY id_ DESC LIMIT 1;";
		System.out.println(requete1);
		try {
			uneBdd.seConnecter();
			Statement unStat1 = uneBdd.getMaConnexion().createStatement();
			ResultSet rs1 = unStat1.executeQuery(requete1);
			if (rs1.next()) {
				id_ = rs1.getInt("id_");
			}
			uneBdd.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur : " + requete1);
		}
		return id_;
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

		ExecutionBdd(uneBdd, requete);

		String requete2 = "DELETE FROM personne WHERE id_personne = " + unAthlete.getIdAthletes() + ";";
		Bdd uneBdd2 = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		System.out.println(requete2);
		ExecutionBdd(uneBdd2, requete2);
	}

	/*
    ********************************************************************************************************************
    ---------------------------------------------		  MD5		------------------------------------------------
    ********************************************************************************************************************
     */


    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /*
    ********************************************************************************************************************
    ---------------------------------------------		   Event 		------------------------------------------------
    ********************************************************************************************************************
     */
    public static ArrayList<Evenements> selectAllEvents() {
        ArrayList<Evenements> listEvents = new ArrayList<Evenements>();

        String requete = "SELECT * FROM evenement;";

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
                String photoEvents = rs.getString("Photo_evenement");
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

    public static int insertEvents(Evenements unEvents) {
        String requete = "INSERT INTO evenement values (" +
                "null," +
                " '" + unEvents.getTitleEvents() + "'," +
                " '" + unEvents.getDescriptionEventsClean() + "," +
                " '" + unEvents.getDateEvents() + "'," +
                " '" + unEvents.getPhotoEvents() + "');";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

        ExecutionBdd(uneBdd, requete);

		int key = Modele.selectLasInsertId(requete,uneBdd);
		return key;
    }

	public static void updateEvents(Evenements unEvents) {
		String requete = "UPDATE evenement " +
				"SET Titre_event = '" + unEvents.getTitleEvents() + "'," +
				"	Description_event = '" + unEvents.getDescriptionEventsClean() + "'," +
				"	Date_evenement = '" + unEvents.getDateEvents() + "', " +
				"	Photo_evenement = '" + unEvents.getPhotoEvents() + "' " +
				"WHERE id_event = '" + unEvents.getIdEvents() + "'";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
	}

	public static void deleteEvents(Evenements unEvents) {
		String requete = "DELETE FROM evenement WHERE id_event = " + unEvents.getIdEvents() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		ExecutionBdd(uneBdd, requete);
	}

	/*
	********************************************************************************************************************
	---------------------------------------------		   Equipe 		------------------------------------------------
	********************************************************************************************************************

	 */

	public static ArrayList<Equipes> selectAllEquipes ()
	{
		ArrayList<Equipes> listEquipes = new ArrayList<Equipes>();

		String requete = "SELECT * FROM equipe";

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
				int idEquipe = rs.getInt("id_equipe");
				String libelleEquipe = rs.getString("Libelle_equipe");
				int Nbjoueursequipe = rs.getInt("Nb_joueurs_equipe");
				int idSport = rs.getInt("id_sport");
				//Mise à jour de la liste
				listEquipes.add(new Equipes(idEquipe, libelleEquipe, Nbjoueursequipe, idSport));
			}
			//Fermeture de la connexion à la base de données
			uneBdd.seDeConnecter();
		}
		catch( SQLException exp)
		{
			System.out.println("Erreur : "+ requete);
			//exp.printStackTrace();
		}
		return listEquipes;
	}

	public static String selectWhereEquipe(int idEquipe) {
		String libelleEquipe = "";

		String requete = "SELECT Libelle_equipe FROM equipe WHERE id_equipe = " + idEquipe + ";";

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

	public static int insertEquipe(Equipes uneEquipe)
	{
		String requete = "INSERT INTO equipe values (" +
				"null," +
				" '" + uneEquipe.getLibelleEquipe() + "' ," +
				" '" + uneEquipe.getNbJoueurequipe() + "' ," +
				" '" + uneEquipe.getIdSportEquipe() + "');";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
		int key = Modele.selectLasInsertId(requete,uneBdd);
		return key;
	}

	public static void deleteEquipes(Equipes uneEquipe) {
		String requete =
				"DELETE FROM equipe WHERE id_equipe = " + uneEquipe.getIdequipe() + "; ";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

		ExecutionBdd(uneBdd, requete);
	}
	public static void updateEquipe(Equipes uneEquipe) {
		String requete =
				"UPDATE equipe " +
						"SET Libelle_equipe = '" + uneEquipe.getLibelleEquipe() + "'," +
						"	Nb_joueurs_equipe = '" + uneEquipe.getNbJoueurequipe() + "'," +
						"	id_sport = '" + uneEquipe.getIdSportEquipe() + "' " +
						"WHERE id_equipe = '" + uneEquipe.getIdequipe() + "'";

		Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

		ExecutionBdd(uneBdd, requete);
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
                listUsers.add(new Utilisateurs(idUser, nomUser, prenomUser, ageUser, genreUser, roleUser, pseudoUser, emailUser, mdpUser));
            }

            //Fermeture de la connexion à la base de données
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
            //exp.printStackTrace();
        }
        return listUsers;
    }

    public static int insertUtilisateur(Utilisateurs unUtilisateur) {
    	String mdp = unUtilisateur.getMot_de_passe();
    	String hashMdp = MD5(mdp);
        String requete =
                "CALL insert_user ('" + unUtilisateur.getNom() + "','" +
                        unUtilisateur.getPrenom() + "'," +
                        unUtilisateur.getAge() + ",'" +
                        unUtilisateur.getGenre() + "','" +
                        unUtilisateur.getEmail() + "','" +
                        unUtilisateur.getPseudo() + "','" +
						hashMdp + "','" +
                        unUtilisateur.getRole() + "');";
		System.out.println(requete);
        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        int key = Modele.selectLasInsertId(requete, uneBdd);
        return key;
    }

    public static void deleteUtilisateur(Utilisateurs unUtilisateur) {
        String requete =
                "DELETE FROM utilisateur WHERE id_personne = " + unUtilisateur.getId_personne() + "; ";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

        ExecutionBdd(uneBdd, requete);

        String requete2 = "DELETE FROM personne WHERE id_personne = " + unUtilisateur.getId_personne() + ";";
        Bdd uneBdd2 = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        System.out.println(requete2);
        ExecutionBdd(uneBdd2, requete2);
    }

    public static void updateUtilisateur(Utilisateurs unUtilisateur) {
		String mdp = unUtilisateur.getMot_de_passe();
		String hashMdp = MD5(mdp);
        String requete =
                "Call update_user (" + unUtilisateur.getId_personne() + ",'" +
                        unUtilisateur.getNom() + "','" +
                        unUtilisateur.getPrenom() + "'," +
                        unUtilisateur.getAge() + ",'" +
                        unUtilisateur.getGenre() + "','" +
                        unUtilisateur.getEmail() + "','" +
                        unUtilisateur.getPseudo() + "','" +
						hashMdp + "','" +
                        unUtilisateur.getRole() + "');";
		System.out.println(requete);

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");

        ExecutionBdd(uneBdd, requete);

    }


    /*
    ********************************************************************************************************************
    ---------------------------------------------		   Ville 		------------------------------------------------
    ********************************************************************************************************************
     */
    public static ArrayList<Ville> selectAllVilles() {
        ArrayList<Ville> listVille = new ArrayList<Ville>();

        String requete = "SELECT * FROM Ville";
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
                listVille.add(new Ville(idVille, libelle_ville));
            }
            //Fermeture de la connexion à la base de données
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
            //exp.printStackTrace();
        }
        return listVille;
    }

    public static int selectIdWhereVille(String libelleVille) {
        int idville = 0;

        String requete = "SELECT id_ville FROM ville WHERE libelle_ville = \"" + libelleVille + "\";";
        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        try {
            //Connexion à la base de donnée
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();

            //Exécution de la requète
            ResultSet rs = unStat.executeQuery(requete);

            if (rs.next()) {
                //récupération des Champs par valeur
                idville = rs.getInt("id_ville");
            }
            //Fermeture de la connexion à la base de données
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
            //exp.printStackTrace();
        }
        return idville;
    }

    public static String selectWhereVille(int idVille) {
        String libelleVille = "";

        String requete = "SELECT Libelle_ville FROM Ville WHERE id_ville = " + idVille + ";";
        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        try {
            //Connexion à la base de donnée
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();

            //Exécution de la requète
            ResultSet rs = unStat.executeQuery(requete);

            if (rs.next()) {
                //récupération des Champs par valeur
                libelleVille = rs.getString("Libelle_ville");

            }
            //Fermeture de la connexion à la base de données
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
            //exp.printStackTrace();
        }
        return libelleVille;
    }

    public static int insertVille(Ville uneVille) {
        String requete = "INSERT INTO ville values (null,'"
                + uneVille.getLibelle_ville() + "');";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        int key = Modele.selectLasInsertId(requete, uneBdd);
        return key;
    }

    public static void updateVille(Ville uneVille) {
        String requete =
                "UPDATE ville " +
                        "SET Libelle_ville = '" + uneVille.getLibelle_ville() +
                        "' WHERE id_ville = " + uneVille.getId_ville() + "";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        ExecutionBdd(uneBdd, requete);
    }

    public static void deleteVille(Ville uneVille) {
        String requete = "DELETE FROM ville WHERE id_ville = " + uneVille.getId_ville() + "; ";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        ExecutionBdd(uneBdd, requete);
    }

    /*
********************************************************************************************************************
---------------------------------------------		   Lieu 		------------------------------------------------
********************************************************************************************************************
 */
    public static ArrayList<Lieu> selectAllLieux() {
        ArrayList<Lieu> listLieu = new ArrayList<Lieu>();

        String requete = "SELECT * FROM Lieu";
        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        try {
            //Connexion à la base de donnée
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();

            //Exécution de la requète
            ResultSet rs = unStat.executeQuery(requete);
            while (rs.next()) {
                //récupération des Champs par valeur
                int idLieu = rs.getInt("id_lieu");
                String libelle_lieu = rs.getString("Libelle_lieu");
                int idVille = rs.getInt("id_ville");

                //Mise à jour de la liste
                listLieu.add(new Lieu(idLieu, libelle_lieu, idVille));
            }
            //Fermeture de la connexion à la base de données
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
            //exp.printStackTrace();
        }
        return listLieu;
    }


    public static int insertLieu(Lieu unLieu) {
        String requete = "INSERT INTO lieu values (null,'"
                + unLieu.getLibelle_lieu() + "',"
                + unLieu.getId_ville() + ");";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        int key = Modele.selectLasInsertId(requete, uneBdd);
        return key;
    }

    public static void updateLieu(Lieu unLieu) {
        String requete =
                "UPDATE lieu " +
                        "SET Libelle_lieu = '" + unLieu.getLibelle_lieu() +
                        "',id_ville = " + unLieu.getId_ville() +
                        " WHERE id_ville = " + unLieu.getId_lieu() + "";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        ExecutionBdd(uneBdd, requete);
    }

    public static void deleteLieu(Lieu unLieu) {
        String requete = "DELETE FROM lieu WHERE id_lieu = " + unLieu.getId_lieu() + "; ";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        ExecutionBdd(uneBdd, requete);
    }

	/*
	********************************************************************************************************************
	---------------------------------------------       Accueil   ------------------------------------------------
	********************************************************************************************************************
	 */

    public static int SelectNb(String table) {
        int Nb = 0;
        String requete = "SELECT COUNT(*) AS Nb FROM " + table + ";";

        Bdd uneBdd = new Bdd("localhost", "paris_2024", "user_paris2024", "123");
        try {
            //Connexion à la base de donnée
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();

            //Exécution de la requète
            ResultSet rs = unStat.executeQuery(requete);

            if (rs.next()) {
                //récupération des Champs par valeur
                Nb = rs.getInt("Nb");
            }
            //Fermeture de la connexion à la base de données
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
            //exp.printStackTrace();
        }
        return Nb;
    }


    public static int selectLasInsertId(String requete, Bdd uneBdd) {
        int key = 0;
        try {
            uneBdd.seConnecter();
            Statement unStat2 = uneBdd.getMaConnexion().createStatement();
            unStat2.execute(requete, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = unStat2.getGeneratedKeys();
            keys.next();
            key = keys.getInt(1);
            unStat2.close();
            uneBdd.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur : " + requete);
        }
        return key;
    }


}