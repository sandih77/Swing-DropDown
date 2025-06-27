package component;

import entity.Faritra;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class FaritraDropDown extends JComboBox {

    Faritra[] listFaritra;

    public Faritra[] getListFaritra() {
        return this.listFaritra;
    }

    public Faritra[] getDataFaritra(String chemin) {
        List<Faritra> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                String faritany = parts[0].trim();
                String nom = parts[1].trim();

                Faritra faritra = new Faritra(nom, faritany);
                list.add(faritra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listFaritra = list.toArray(new Faritra[0]);

        // Ajouter "Tous" en premier dans la combo
        this.removeAllItems();
        this.addItem("Tous");
        for (Faritra f : listFaritra) {
            this.addItem(f);
        }

        return this.listFaritra;
    }

    public void filterByFaritany(String faritany) {
        this.removeAllItems();

        // Ajouter "Tous" en haut
        this.addItem("Tous");

        // Ajouter tous les Faritra si filtre vide ou "Tous"
        if (faritany == null || faritany.trim().isEmpty() || faritany.equalsIgnoreCase("Tous")) {
            for (Faritra f : listFaritra) {
                this.addItem(f);
            }
            return;
        }

        // Ajouter uniquement les Faritra correspondant au Faritany
        for (Faritra f : listFaritra) {
            if (f.getFaritany().equalsIgnoreCase(faritany.trim())) {
                this.addItem(f);
            }
        }
    }

}
