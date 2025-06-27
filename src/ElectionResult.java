package election;

import entity.Distrika;
import java.util.ArrayList;
import java.util.List;

public class ElectionResult {

    public ElectionResult() {}

    public List<String[]> getElusParDistrika(List<Vote> votes, List<Distrika> distrikaList) {
        List<String[]> elusParDistrika = new ArrayList<>();

        for (Distrika d : distrikaList) {
            String nomDistrika = d.getNom();
            int nbElu = d.getNbElu();

            // Filtrer les votes correspondant à ce distrika
            List<Vote> votesDistrika = new ArrayList<>();
            for (Vote v : votes) {
                if (v.getDistrika().equalsIgnoreCase(nomDistrika)) {
                    votesDistrika.add(v);
                }
            }

            // Regrouper les voix par candidat (manuellement)
            List<String> candidats = new ArrayList<>();
            List<Integer> voix = new ArrayList<>();

            for (Vote v : votesDistrika) {
                String candidat = v.getCandidat();
                int index = candidats.indexOf(candidat);
                if (index == -1) {
                    candidats.add(candidat);
                    voix.add(v.getNbVotes());
                } else {
                    voix.set(index, voix.get(index) + v.getNbVotes());
                }
            }

            // Si aucun vote
            if (candidats.isEmpty()) {
                elusParDistrika.add(new String[]{nomDistrika, "Aucun vote"});
                continue;
            }

            // Trier les candidats par nombre de voix (tri à bulles ou simple)
            for (int i = 0; i < voix.size() - 1; i++) {
                for (int j = i + 1; j < voix.size(); j++) {
                    if (voix.get(j) > voix.get(i)) {
                        // swap voix
                        int tmpV = voix.get(i);
                        voix.set(i, voix.get(j));
                        voix.set(j, tmpV);

                        // swap candidats
                        String tmpC = candidats.get(i);
                        candidats.set(i, candidats.get(j));
                        candidats.set(j, tmpC);
                    }
                }
            }

            // Élire en fonction du nombre d’élus
            String resultat;
            if (nbElu == 1 || candidats.size() == 1) {
                resultat = candidats.get(0) + " (" + voix.get(0) + " votes)";
            } else {
                int v1 = voix.get(0);
                int v2 = voix.size() > 1 ? voix.get(1) : 0;

                if (v2 * 2 > v1) {
                    resultat = candidats.get(0) + " et " + candidats.get(1) +
                            " (" + v1 + " / " + v2 + " votes)";
                } else {
                    resultat = candidats.get(0) + " et son suppléant (" + v1 + " votes)";
                }
            }

            elusParDistrika.add(new String[]{nomDistrika, resultat});
        }

        return elusParDistrika;
    }
}
