package candidat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Groupe {

    String nom;
    Date dateFondation;

    public Groupe(String nom, Date d) {
        this.nom = nom;
        this.dateFondation = d;
    }

    public String getNom() {
        return this.nom;
    }

    public Date getDateFondation() {
        return this.dateFondation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateFondation(Date dateFondation) {
        this.dateFondation = dateFondation;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public static List<Groupe> chargerGroupesDepuisFichier(String cheminFichier) {
        List<Groupe> groupes = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.startsWith("#") || ligne.trim().isEmpty()) {
                    continue;
                }
                String[] parties = ligne.split("\\|");
                if (parties.length == 2) {
                    String nom = parties[0].trim();
                    Date dateFondation = sdf.parse(parties[1].trim());

                    Groupe g = new Groupe(nom, dateFondation);

                    groupes.add(g);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupes;
    }
}
