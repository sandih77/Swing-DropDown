package entity;

import java.io.*;
import java.util.*;

import candidat.*;

public class Depute {

    String nom;
    String nomDistrika;
    String nomGroupe;
    String nomSuppleant;

    public Depute(String nom, String nomDistrika, String groupe) {
        this.nom = nom;
        this.nomDistrika = nomDistrika;
        this.nomGroupe = groupe;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDistrika() {
        return this.nomDistrika;
    }

    public String getGroupe() {
        return this.nomGroupe;
    }

    public void setGroupe(String g) {
        this.nomGroupe = g;
    }

    public String getSuppleant() {
        return this.nomSuppleant;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public static List<Depute> chargerDeputes(String cheminFichier) {
        List<Depute> Deputes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.startsWith("#") || ligne.trim().isEmpty()) {
                    continue;
                }
                String[] parties = ligne.split("\\|");
                if (parties.length == 3) {
                    String nom = parties[0].trim();
                    String distrika = parties[1].trim();
                    String groupe = parties[2].trim();
                    Deputes.add(new Depute(nom, distrika, groupe));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Deputes;
    }

}
