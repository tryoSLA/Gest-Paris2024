package controleur;

public class Athletes
{
    private int idAthletes;
    private static int nbChampAthletes = 7;
    private String nom, prenom, genre, libellePays, image, libelleSports, biographie;
    private int age;
    private float poids, taille;

    public Athletes()
    {
        this.idAthletes = 0;
        this.nom=this.prenom=this.genre=this.libellePays=this.image=this.libelleSports=this.biographie="";
        this.age= 0;
        this.taille= 0.0f;
        this.poids= 0.0f;
    }

    public Athletes(int idAthletes, String nom, String prenom, String genre, String libellePays, String image, String libelleSports, String biographie, int age, float taille, float poids)
    {
        this.idAthletes = idAthletes;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.libellePays = libellePays;
        this.image = image;
        this.libelleSports = libelleSports;
        this.biographie = biographie;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
    }

    public Athletes(String nom, String prenom, String genre, String libellePays, String image, String libelleSports, String biographie, int age, float taille, float poids)
    {
        this.idAthletes = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.libellePays = libellePays;
        this.image = image;
        this.libelleSports = libelleSports;
        this.biographie = biographie;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {

        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBiographie() {

        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getLibelleSports() {

        return libelleSports;
    }

    public void setLibelleSports(String libelleSports) {
        this.libelleSports = libelleSports;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLibellePays() {

        return libellePays;
    }

    public void setLibellePays(String libellePays) {
        this.libellePays = libellePays;
    }

    public String getGenre() {

        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrenom() {

        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static int getNbChampAthletes() {

        return nbChampAthletes;
    }

    public static void setNbChampAthletes(int nbChampAthletes) {
        Athletes.nbChampAthletes = nbChampAthletes;
    }

    public int getIdAthletes() {

        return idAthletes;
    }

    public void setIdAthletes(int idAthletes) {
        this.idAthletes = idAthletes;
    }
}
