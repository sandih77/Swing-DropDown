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

                // System.out.println("Ligne lue : " + line);
                Faritra Faritra = new Faritra(nom, faritany);
                list.add(Faritra);
                this.addItem(Faritra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listFaritra = list.toArray(new Faritra[0]);
        return this.listFaritra;
    }

    public void filterByFaritany(String faritany) {
        this.removeAllItems();

        if (faritany == null || faritany.isEmpty() || faritany.equalsIgnoreCase("Tous")) {
            for (Faritra f : listFaritra) {
                this.addItem(f);
            }
            return;
        }

        for (Faritra f : listFaritra) {
            if (f.getFaritany().equalsIgnoreCase(faritany)) {
                this.addItem(f);
            }
        }
    }
}
