package controleur;

public class Athletes
{
    private static int nbChampAthletes = 12;
    private String nom, prenom, genre, photo, biographie, id_equipe_string;
    private int age,idAthletes, id_pays, id_sport, id_equipe;
    private float poids, taille;

    public Athletes()
    {
        this.idAthletes = 0;
        this.nom=this.prenom=this.genre=this.photo=this.biographie="";
        this.age= 0;
        this.id_pays= 0;
        this.id_sport= 0;
        this.id_equipe= 0;
        this.taille= 0.0f;
        this.poids= 0.0f;
    }

    public Athletes(int idAthletes, String nom, String prenom, int age,String genre, float taille, float poids,String photo, String biographie, int id_equipe, int id_sport,int id_pays)
    {
        this.idAthletes = idAthletes;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.photo = photo;
        this.biographie = biographie;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.id_equipe = id_equipe;
        this.id_sport = id_sport;
        this.id_pays = id_pays;
    }



    public Athletes(String nom, String prenom, int age,String genre, float taille, float poids,String photo, String biographie, int id_equipe, int id_sport,int id_pays)
    {
        this.idAthletes = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.photo = photo;
        this.biographie = biographie;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.id_equipe = id_equipe;
        this.id_sport = id_sport;
        this.id_pays = id_pays;
    }

    public Athletes(String nom, String prenom, int age,String genre, float taille, float poids,String photo, String biographie, String id_equipe_string, int id_sport,int id_pays)
    {
        this.idAthletes = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.photo = photo;
        this.biographie = biographie;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.id_equipe_string = id_equipe_string;
        this.id_sport = id_sport;
        this.id_pays = id_pays;
    }

    public Athletes(int id,String nom, String prenom, int age,String genre, float taille, float poids,String photo, String biographie, String id_equipe_string, int id_sport,int id_pays)
    {
        this.idAthletes = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.photo = photo;
        this.biographie = biographie;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
        this.id_equipe_string = id_equipe_string;
        this.id_sport = id_sport;
        this.id_pays = id_pays;
    }

    public static int getNbChampAthletes() {
        return nbChampAthletes;
    }

    public static void setNbChampAthletes(int nbChampAthletes) {
        Athletes.nbChampAthletes = nbChampAthletes;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiographie() {
        return biographie;
    }
    public String getBiographieClean()
    {
        String apo = "'";
        String AntiSlash = "\\'";
        String biographieClean = biographie.replace(apo,AntiSlash);
        return biographieClean;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdAthletes() {
        return idAthletes;
    }

    public void setIdAthletes(int idAthletes) {
        this.idAthletes = idAthletes;
    }

    public int getId_pays() {
        return id_pays;
    }

    public void setId_pays(int id_pays) {
        this.id_pays = id_pays;
    }

    public int getId_sport() {
        return id_sport;
    }

    public void setId_sport(int id_sport) {
        this.id_sport = id_sport;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public String getId_equipe_string() {
        return id_equipe_string;
    }

    public void setId_equipe_string(String id_equipe_string) {
        this.id_equipe_string = id_equipe_string;
    }
}
