package controleur;

public class Pays
{
    private int idPays;
    private static int nbChampPays = 3;
    private String libelle, description, image;

    public Pays()
    {
        this.idPays=0;
        this.libelle=this.description=this.image="";
    }

    public Pays(int idPays, String libelle, String description, String image)
    {
        this.idPays = idPays;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public Pays(String libelle,String description, String image)
    {
        this.idPays = 0;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public static int getNbChampPays() {
        return nbChampPays;
    }

    public static void setNbChampPays(int nbChampPays) {
        Pays.nbChampPays = nbChampPays;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
