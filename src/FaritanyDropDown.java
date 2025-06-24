package component;

import entity.Faritany;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class FaritanyDropDown extends JComboBox {

    Faritany[] listFaritany;

    public Faritany[] getListFaritany() {
        return this.listFaritany;
    }

    public Faritany[] getDataFaritany(String chemin) {
        List<Faritany> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // System.out.println("Ligne lue : " + line);
                Faritany faritany = new Faritany(line);
                list.add(faritany);
                this.addItem(faritany);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listFaritany = list.toArray(new Faritany[0]);
        return this.listFaritany;
    }
}
