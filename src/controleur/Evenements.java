package controleur;

import java.util.Date;

public class Evenements
{
    private int idEvents,idVille, idType;
    private static int nbChampEvents = 7;
    private String titleEvents, descriptionEvents, photoEvents, dateEvents;
    //private Date dateEvents;

    public Evenements()
    {
        this.idEvents = 0;
        this.titleEvents = "";
        this.descriptionEvents = "";
        this.photoEvents = "";
        this.dateEvents = "";
    }

    public Evenements(int idEvents, String titleEvents, String descriptionEvents, String photoEvents, String dateEvents, int idVille, int idType)
    {
        this.idEvents = idEvents;
        this.titleEvents = titleEvents;
        this.descriptionEvents = descriptionEvents;
        this.photoEvents = photoEvents;
        this.dateEvents = dateEvents;
        this.idVille = idVille;
        this.idType = idType;
    }

    public Evenements(String titleEvents, String descriptionEvents, String photoEvents, String dateEvents, int idVille, int idType)
    {
        this.idEvents = 0;
        this.titleEvents = titleEvents;
        this.descriptionEvents = descriptionEvents;
        this.photoEvents = photoEvents;
        this.dateEvents = dateEvents;
        this.idVille = idVille;
        this.idType = idType;
    }

    public int getIdEvents() {
        return idEvents;
    }

    public void setIdEvents(int idEvents) {
        this.idEvents = idEvents;
    }

    public static int getNbChampEvents() {
        return nbChampEvents;
    }

    public static void setNbChampEvents(int nbChampEvents) {
        Evenements.nbChampEvents = nbChampEvents;
    }

    public String getDescriptionEvents() {
        return descriptionEvents;
    }

    public String getDescriptionEventsClean()
    {
        String apo = "'";
        String AntiSlash = "\\'";
        String DescriptionClean = descriptionEvents.replace(apo,AntiSlash);
        return DescriptionClean;
    }

    public void setDescriptionEvents(String descriptionEvents) {
        this.descriptionEvents = descriptionEvents;
    }

    public String getPhotoEvents() {
        return photoEvents;
    }

    public void setPhotoEvents(String photoEvents) {
        this.photoEvents = photoEvents;
    }

    public String getDateEvents() {
        return dateEvents;
    }

    public void setDateEvents(String dateEvents) {
        this.dateEvents = dateEvents;
    }

    public String getTitleEvents() {
        return titleEvents;
    }

    public void setTitleEvents(String titleEvents) {
        this.titleEvents = titleEvents;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
}
