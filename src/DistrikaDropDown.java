package component;

import entity.*;
import javax.swing.JComboBox;
import java.util.*;
import java.io.*;
import election.*;

public class DistrikaDropDown extends JComboBox {

    Distrika[] listDistrika;
    BureauVote[] listBVS;

    public Distrika[] getListDistrika() {
        return this.listDistrika;
    }

    public BureauVote[] getListBureauVotes() {
        return this.listBVS;
    }

    public Distrika[] getDataDistrika(String chemin) {
        List<Distrika> list = new ArrayList<>();
        List<BureauVote> listBureauVotes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\|");
                if (parts.length < 3) {
                    System.out.println("Ligne ignoree (format incorrect) : " + line);
                    continue;
                }

                String faritra = parts[0].trim();
                String[] nom = parts[1].split(":");
                if (nom.length < 2) {
                    System.out.println("Nom de distrika invalide : " + parts[1]);
                    continue;
                }

                String nomDistrika = nom[0].trim();
                String[] listBV = nom[1].split(",");

                for (String bv : listBV) {
                    BureauVote bureau = new BureauVote(bv.trim());
                    bureau.setDistrika(nomDistrika);
                    listBureauVotes.add(bureau);
                }

                int nbelu = 0;
                try {
                    nbelu = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Nombre d’electeurs invalide : " + parts[2]);
                }

                Distrika d = new Distrika(nomDistrika, faritra , nbelu);
                list.add(d);
                this.addItem(d);
                // System.out.println("Distrika ajoute: " + d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listBVS = listBureauVotes.toArray(new BureauVote[0]);
        this.listDistrika = list.toArray(new Distrika[0]);

        return this.listDistrika;
    }

    public void filterByFaritanyAndFaritra(String nomFaritany, String nomFaritra, Faritra[] allFaritra) {
        this.removeAllItems();

        for (Distrika d : listDistrika) {
            String faritraDistrika = d.getFaritra();

            // Trouver l'objet Faritra correspondant
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
