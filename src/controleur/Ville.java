package controleur;

public class Ville {
    private static int nbChampVille = 2;
    private int id_ville;
    private String Libelle_ville;

    public Ville()
    {
        this.id_ville = 0;
        this.Libelle_ville = "";
    }
    public Ville(int id_ville, String Libelle_ville)
    {
        this.id_ville = id_ville;
        this.Libelle_ville = Libelle_ville;
    }
    public Ville(String Libelle_ville)
    {
        this.id_ville = 0;
        this.Libelle_ville = Libelle_ville;
    }

    public int getId_ville() {
        return id_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public String getLibelle_ville() {
        return Libelle_ville;
    }

    public void setLibelle_ville(String libelle_ville) {
        Libelle_ville = libelle_ville;
    }

    public static int getNbChampVille() {
        return nbChampVille;
    }

    public static void setNbChampVille(int nbChampVille) {
        Ville.nbChampVille = nbChampVille;
    }
}
