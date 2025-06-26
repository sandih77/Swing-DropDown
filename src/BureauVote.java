package election;

public class BureauVote {
    String nom;
    String distrika;

    public BureauVote(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDistrika() {
        return this.distrika;
    }

    public void setDistrika(String distrika) {
        this.distrika = distrika;
    }

    @Override
    public String toString() {
        return nom;
    }
}
