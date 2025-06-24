package component;

import entity.Depute;
import java.io.*;
import java.util.*;
import javax.swing.JComboBox;

public class DeputeDropDown extends JComboBox {

    private Depute[] listDepute;

    public Depute[] getListDepute() {
        return this.listDepute;
    }

    public Depute[] getDataDepute(String chemin) {
        List<Depute> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length < 2) {
                    System.err.println("Format invalide : " + line);
                    continue;
                }

                String nom = parts[0].trim();

                String distrikaNbElu = parts[1].trim();
                String groupe = parts.length > 2 ? parts[2].trim() : "";

                String[] distrikaParts = distrikaNbElu.split(",");
                String distrika = distrikaParts[0].trim();

                Depute d = new Depute(nom, distrika, groupe);
                list.add(d);
                this.addItem(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listDepute = list.toArray(new Depute[0]);

        return this.listDepute;
    }

    public void filterByDistrika(String nomDistrika) {
        this.removeAllItems();

        for (Depute d : listDepute) {
            if (nomDistrika.equalsIgnoreCase("Tous") || d.getDistrika().equalsIgnoreCase(nomDistrika)) {
                this.addItem(d);
            }
        }
    }
}
