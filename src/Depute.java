package entity;

public class Depute {

    String nom;
    String nomDistrika;
    String nomGroupe;
    String nomSuppleant;

    public Depute(String nom, String nomDistrika, String groupe) {
        this.nom = nom;
        this.nomDistrika = nomDistrika;
        this.nomGroupe = groupe;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDistrika() {
        return this.nomDistrika;
    }

    public String getGroupe() {
        return this.nomGroupe;
    }

    public void setGroupe(String g) {
        this.nomGroupe = g;
    }

    public String getSuppleant() {
        return this.nomSuppleant;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
