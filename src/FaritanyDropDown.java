package component;

import entity.Faritany;
import java.io.*;
import java.util.*;

public class FaritanyDropDown {

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

                list.add(new Faritany(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listFaritany = list.toArray(new Faritany[0]);
        return this.listFaritany;
    }
}
