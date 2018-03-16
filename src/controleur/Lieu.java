package controleur;

public class Lieu {
    private static int nbChampLieu = 3;
    private int id_lieu, id_ville;
    private String Libelle_lieu;

    public Lieu()
    {
        this.id_lieu = 0;
        this.Libelle_lieu = "";
        this.id_ville = 0;
    }
    public Lieu(int id_lieu, String Libelle_lieu, int id_ville)
    {
        this.id_lieu = id_lieu;
        this.Libelle_lieu = Libelle_lieu;
        this.id_ville = id_ville;
    }
    public Lieu(String Libelle_lieu, int id_ville)
    {
        this.Libelle_lieu = Libelle_lieu;
        this.id_ville = id_ville;
    }

    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public int getId_ville() {
        return id_ville;
    }

    public void setId_ville(int id_ville) {
        this.id_ville = id_ville;
    }

    public String getLibelle_lieu() {
        return Libelle_lieu;
    }

    public void setLibelle_lieu(String libelle_lieu) {
        Libelle_lieu = libelle_lieu;
    }

    public static int getNbChampLieu() {
        return nbChampLieu;
    }

    public static void setNbChampLieu(int nbChampLieu) {
        Lieu.nbChampLieu = nbChampLieu;
    }
}
