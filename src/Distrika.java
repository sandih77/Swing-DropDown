package entity;

public class Distrika {

    String nom;
    String nomFaritra;
    int nbElu;

    public Distrika(String nom, String faritra, int nbElu) {
        this.nom = nom;
        this.nomFaritra = faritra;
        this.nbElu = nbElu;
    }

    public String getNom() {
        return this.nom;
    }

    public String getFaritra() {
        return this.nomFaritra;
    }

    public int getNbElu() {
        return this.nbElu;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
