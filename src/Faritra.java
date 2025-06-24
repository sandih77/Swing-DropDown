package entity;

public class Faritra {

    String nom;
    String nomFaritany;

    public Faritra(String nom, String faritany) {
        this.nom = nom;
        this.nomFaritany = faritany;
    }

    public String getNom() {
        return this.nom;
    }

    public String getFaritany() {
        return this.nomFaritany;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
