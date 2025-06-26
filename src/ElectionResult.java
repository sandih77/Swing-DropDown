package election;

import java.util.*;
import java.io.*;

public class ElectionResult {

    private Map<String, Integer> resultatParCandidat;

    public ElectionResult() {
        this.resultatParCandidat = new HashMap<>();
    }

    public void calculerResultats(String cheminFichier) {
        List<Vote> votes = Vote.lireVotesDepuisFichier(cheminFichier);
        for (int i = 0; i < votes.size(); i++) {
            Vote vote = votes.get(i);
            String candidat = vote.getCandidat();
            int nb = vote.getNbVotes();

            if (!resultatParCandidat.containsKey(candidat)) {
                resultatParCandidat.put(candidat, nb);
            } else {
                int ancien = resultatParCandidat.get(candidat);
                resultatParCandidat.put(candidat, ancien + nb);
            }
        }
    }

    public Map<String, Integer> getResultats() {
        return resultatParCandidat;
    }

    public String getGagnant() {
        String gagnant = "Aucun vote";
        int maxVotes = -1;

        for (String candidat : resultatParCandidat.keySet()) {
            int nbVotes = resultatParCandidat.get(candidat);
            if (nbVotes > maxVotes) {
                maxVotes = nbVotes;
                gagnant = candidat;
            }
        }

        return gagnant;
    }

    public Map<String, String> getGagnantParDistrika(List<Vote> votes) {
        Map<String, Map<String, Integer>> resultatParDistrika = new HashMap<>();

        for (int i = 0; i < votes.size(); i++) {
            Vote vote = votes.get(i);
            String distrika = vote.getDistrika();
            String candidat = vote.getCandidat();
            int nb = vote.getNbVotes();

            if (!resultatParDistrika.containsKey(distrika)) {
                resultatParDistrika.put(distrika, new HashMap<>());
            }

            Map<String, Integer> candidats = resultatParDistrika.get(distrika);
            if (!candidats.containsKey(candidat)) {
                candidats.put(candidat, nb);
            } else {
                candidats.put(candidat, candidats.get(candidat) + nb);
            }
        }

        Map<String, String> gagnantsParDistrika = new HashMap<>();

        for (String distrika : resultatParDistrika.keySet()) {
            Map<String, Integer> candidats = resultatParDistrika.get(distrika);

            String gagnant = "Aucun";
            int maxVotes = -1;

            for (String candidat : candidats.keySet()) {
                int nbVotes = candidats.get(candidat);
                if (nbVotes > maxVotes) {
                    maxVotes = nbVotes;
                    gagnant = candidat;
                }
            }

            gagnantsParDistrika.put(distrika, gagnant);
        }

        return gagnantsParDistrika;
    }
}
