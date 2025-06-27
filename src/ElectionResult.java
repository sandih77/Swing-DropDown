package election;

import entity.Distrika;

import java.util.*;

public class ElectionResult {

    Map<String, Integer> resultatParCandidat;

    public ElectionResult() {
        this.resultatParCandidat = new HashMap<>();
    }

    public void calculerResultats(String cheminFichier) {
        List<Vote> votes = Vote.lireVotesDepuisFichier(cheminFichier);
        calculerResultatsDepuisListe(votes);
    }

    public void calculerResultatsDepuisListe(List<Vote> votes) {
        resultatParCandidat.clear();
        for (Vote vote : votes) {
            String candidat = vote.getCandidat();
            int nb = vote.getNbVotes();
            resultatParCandidat.put(candidat,
                    resultatParCandidat.getOrDefault(candidat, 0) + nb);
        }
    }

    public Map<String, Integer> getResultats() {
        return this.resultatParCandidat;
    }

    public Map.Entry<String, Integer> getGagnantAvecVotes() {
        String gagnant = "Aucun";
        int maxVotes = -1;

        for (Map.Entry<String, Integer> entry : resultatParCandidat.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                gagnant = entry.getKey();
            }
        }

        return new AbstractMap.SimpleEntry<>(gagnant, maxVotes);
    }

    public Map<String, String> getElusParDistrika(List<Vote> votes, Map<String, Distrika> distrikaMap) {
        Map<String, Map<String, Integer>> resultats = new HashMap<>();

        for (Vote v : votes) {
            String distrika = v.getDistrika();
            String candidat = v.getCandidat();
            int nbVotes = v.getNbVotes();

            resultats
                    .computeIfAbsent(distrika, k -> new HashMap<>())
                    .merge(candidat, nbVotes, Integer::sum);
        }

        Map<String, String> elusParDistrika = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : resultats.entrySet()) {
            String nomDistrika = entry.getKey();
            Map<String, Integer> candidats = entry.getValue();

            List<Map.Entry<String, Integer>> classement = new ArrayList<>(candidats.entrySet());
            classement.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

            int nbElu = distrikaMap.containsKey(nomDistrika)
                    ? distrikaMap.get(nomDistrika).getNbElu() : 1;

            if (classement.isEmpty()) {
                elusParDistrika.put(nomDistrika, "Aucun vote");
                continue;
            }

            String resultat;
            if (nbElu == 1 || classement.size() == 1) {
                resultat = classement.get(0).getKey() + " (" + classement.get(0).getValue() + " votes)";
            } else {
                int v1 = classement.get(0).getValue();
                int v2 = classement.get(1).getValue();

                if (v2 * 2 > v1) {
                    resultat = classement.get(0).getKey() + " et " + classement.get(1).getKey()
                            + " (" + v1 + " / " + v2 + " votes)";
                } else {
                    resultat = classement.get(0).getKey() + " et son suppleant" + " (" + v1 + " votes)";
                }
            }

            elusParDistrika.put(nomDistrika, resultat);
        }

        return elusParDistrika;
    }
}
