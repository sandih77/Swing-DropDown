package entity;

public class Faritany {

    String nom;

    public Faritany(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public String toString() {
        return this.nom; 
    }
}
