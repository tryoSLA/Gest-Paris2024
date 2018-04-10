package controleur;

import java.util.Date;

public class Evenements
{
    private int idEvents;
    private static int nbChampPays = 3;
    private String titleEvents, descriptionEvents, photoEvents;
    private Date dateEvents;

    public Evenements()
    {
        this.idEvents = 0;
        this.titleEvents = "";
        this.descriptionEvents = "";
        this.photoEvents = "";
        this.dateEvents = new Date();
    }

    public Evenements(int idEvents, String titleEvents, String descriptionEvents, String photoEvents, Date dateEvents)
    {
        this.idEvents = idEvents;
        this.titleEvents = titleEvents;
        this.descriptionEvents = descriptionEvents;
        this.photoEvents = photoEvents;
        this.dateEvents = dateEvents;
    }

    public Evenements(String titleEvents, String descriptionEvents, String photoEvents, Date dateEvents)
    {
        this.idEvents = 0;
        this.titleEvents = titleEvents;
        this.descriptionEvents = descriptionEvents;
        this.photoEvents = photoEvents;
        this.dateEvents = dateEvents;
    }

    public int getIdEvents() {
        return idEvents;
    }

    public void setIdEvents(int idEvents) {
        this.idEvents = idEvents;
    }

    public static int getNbChampPays() {
        return nbChampPays;
    }

    public static void setNbChampPays(int nbChampPays) {
        Evenements.nbChampPays = nbChampPays;
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

    public Date getDateEvents() {
        return dateEvents;
    }

    public void setDateEvents(Date dateEvents) {
        this.dateEvents = dateEvents;
    }

    public String getTitleEvents() {
        return titleEvents;
    }

    public void setTitleEvents(String titleEvents) {
        this.titleEvents = titleEvents;
    }
}
