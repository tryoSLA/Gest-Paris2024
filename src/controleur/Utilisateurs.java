package controleur;

public class Utilisateurs {
    private int id_personne;
    private static int nbChampUtilisateur = 9;
    private String nom, prenom, genre, email, pseudo, mot_de_passe, role;
    private int age;

    public Utilisateurs() {
        this.id_personne = 0;
        this.nom = this.prenom = this.genre = this.email = this.pseudo = this.mot_de_passe = this.role = "";
        this.age = 0;
    }

    public Utilisateurs(int id_personne, String nom, String prenom, int age,String genre, String role,String pseudo,String email, String mot_de_passe) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
        this.role = role;
        this.age = age;
    }

    public Utilisateurs(String nom, String prenom, int age,String genre, String role,String pseudo,String email,String mot_de_passe) {
        this.id_personne = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
        this.role = role;
        this.age = age;
    }

    public int getId_personne() {
        return id_personne;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public static int getNbChampUtilisateur() {
        return nbChampUtilisateur;
    }

    public static void setNbChampUtilisateur(int nbChampUtilisateur) {
        Utilisateurs.nbChampUtilisateur = nbChampUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}