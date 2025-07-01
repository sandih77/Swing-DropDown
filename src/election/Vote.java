package election;

import java.io.*;
import java.util.*;

public class Vote {

    String distrika;
    String candidat;
    int nbVotes;

    public Vote(String distrika, String candidat, int nbVotes) {
        this.distrika = distrika;
        this.candidat = candidat;
        this.nbVotes = nbVotes;
    }

    public String getDistrika() {
        return distrika;
    }

    public String getCandidat() {
        return candidat;
    }

    public int getNbVotes() {
        return nbVotes;
    }

    @Override
    public String toString() {
        return distrika + " | " + candidat + " | " + nbVotes;
    }

    public static List<Vote> lireVotesDepuisFichier(String cheminFichier) {
        List<Vote> listeVotes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean premiereLigne = true;

            while ((ligne = reader.readLine()) != null) {
                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                String[] parties = ligne.split("\\|");
                if (parties.length == 3) {
                    String distrika = parties[0].trim();
                    String candidat = parties[1].trim();
                    int nbVotes = Integer.parseInt(parties[2].trim());

                    listeVotes.add(new Vote(distrika, candidat, nbVotes));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return listeVotes;
    }
}
