package entity;

import java.io.*;
import java.util.*;

public class Distrika {
    String nom;
    String nomFaritra;
    int nbElu;

    public Distrika(String nom, String faritra, int nbElu) {
        this.nom = nom;
        this.nomFaritra = faritra;
        this.nbElu = nbElu;
    }

    public String getNom() {
        return this.nom;
    }

    public String getFaritra() {
        return this.nomFaritra;
    }

    public int getNbElu() {
        return this.nbElu;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    // Ajoute cette méthode statique :
    public static List<Distrika> lireDepuisFichier(String cheminFichier) {
        List<Distrika> distrikas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue; // ignorer commentaires et lignes vides
                String[] parts = line.split("\\|"); // ou le séparateur que tu utilises dans ton fichier Distrika.txt
                if (parts.length < 3) continue; // vérifier la structure

                String faritra = parts[0].trim();

                String[] nomParts = parts[1].split(":");
                if (nomParts.length < 2) continue;

                String nomDistrika = nomParts[0].trim();

                int nbElu = 0;
                try {
                    nbElu = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Nombre d’élus invalide pour " + nomDistrika);
                }

                Distrika d = new Distrika(nomDistrika, faritra, nbElu);
                distrikas.add(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distrikas;
    }
}
