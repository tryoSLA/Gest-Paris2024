package controleur;

public class Sports
{
    private int idSports;
    private static int nbChampSports = 3;
    private String libelle, description, image;

    public Sports()
    {
        this.idSports = 0;
        this.libelle=this.description=this.image="";
    }

    public Sports(int idSports, String libelle, String description, String image)
    {
        this.idSports = idSports;
        this.libelle = libelle;
        this.description = description;
        this.image = image;

    }

    public Sports(String libelle, String description, String image)
    {
        this.idSports = 0;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public int getIdSports() {
        return idSports;
    }

    public void setIdSports(int idSports) {
        this.idSports = idSports;
    }

    public static int getNbChampSports() {
        return nbChampSports;
    }

    public static void setNbChampSports(int nbChampSports) {
        Sports.nbChampSports = nbChampSports;
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
