package component;

import entity.*;
import javax.swing.JComboBox;
import java.util.*;
import java.io.*;

public class DistrikaDropDown extends JComboBox {

    Distrika[] listDistrika;

    public Distrika[] getListDistrika() {
        return this.listDistrika;
    }

    public Distrika[] getDataDistrika(String chemin) {
        List<Distrika> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                String faritra = parts[0].trim();
                String nom = parts[1].trim();

                Distrika d = new Distrika(nom, faritra);
                list.add(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listDistrika = list.toArray(new Distrika[0]);

        return this.listDistrika;
    }

    public void filterByFaritanyAndFaritra(String nomFaritany, String nomFaritra, Faritra[] allFaritra) {
        this.removeAllItems();
        // this.addItem(new Distrika("Tous", "Tous"));

        for (Distrika d : listDistrika) {
            String faritraDistrika = d.getFaritra();

            // Retrouver l'objet Faritra correspondant au Distrika
            for (Faritra f : allFaritra) {
                if (f.getNom().equalsIgnoreCase(faritraDistrika)) {
                    String faritanyOfFaritra = f.getFaritany();

                    boolean matchFaritra = nomFaritra.equalsIgnoreCase("Tous") || f.getNom().equalsIgnoreCase(nomFaritra);
                    boolean matchFaritany = nomFaritany.equalsIgnoreCase("Tous") || faritanyOfFaritra.equalsIgnoreCase(nomFaritany);

                    if (matchFaritra && matchFaritany) {
                        this.addItem(d);
                    }

                    break;
                }
            }
        }
    }

    public void filterByFaritra(String nomFaritra) {
        this.removeAllItems();

        for (Distrika d : listDistrika) {
            if (nomFaritra.equalsIgnoreCase("Tous") || d.getFaritra().equalsIgnoreCase(nomFaritra)) {
                this.addItem(d);
            }
        }
    }

}
