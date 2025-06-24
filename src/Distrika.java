package entity;

public class Distrika {

    String nom;
    String nomFaritra;

    public Distrika(String nom, String faritra) {
        this.nom = nom;
        this.nomFaritra = faritra;
    }

    public String getNom() {
        return this.nom;
    }

    public String getFaritra() {
        return this.nomFaritra;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
