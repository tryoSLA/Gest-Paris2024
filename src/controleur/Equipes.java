package controleur;

public class Equipes
{
    private int idequipe;
    private static int nbChampPays = 3;
    private String libelleEquipe;
    private int nbJoueurequipe, idSportEquipe;

    public Equipes()
    {
        this.idequipe = 0;
        this.libelleEquipe = "";
        this.nbJoueurequipe = 0;
        this.idSportEquipe = 0;
    }

    public Equipes(int idequipe, String libelleEquipe, int nbJoueurequipe, int idSportEquipe)
    {
        this.idequipe = idequipe;
        this.libelleEquipe = libelleEquipe;
        this.nbJoueurequipe = nbJoueurequipe;
        this.idSportEquipe = idSportEquipe;
    }

    public Equipes(String libelleEquipe, int nbJoueurequipe, int idSportEquipe)
    {
        this.idequipe = 0;
        this.libelleEquipe = libelleEquipe;
        this.nbJoueurequipe = nbJoueurequipe;
        this.idSportEquipe = idSportEquipe;
    }

    public int getIdequipe() {
        return idequipe;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public static int getNbChampPays() {
        return nbChampPays;
    }

    public static void setNbChampPays(int nbChampPays) {
        Equipes.nbChampPays = nbChampPays;
    }

    public String getLibelleEquipe() {
        return libelleEquipe;
    }

    public void setLibelleEquipe(String libelleEquipe) {
        this.libelleEquipe = libelleEquipe;
    }

    public int getNbJoueurequipe() {
        return nbJoueurequipe;
    }

    public void setNbJoueurequipe(int nbJoueurequipe) {
        this.nbJoueurequipe = nbJoueurequipe;
    }

    public int getIdSportEquipe() {
        return idSportEquipe;
    }

    public void setIdSportEquipe(int idSportEquipe) {
        this.idSportEquipe = idSportEquipe;
    }
}
