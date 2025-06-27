package election;

import entity.Distrika;
import entity.Depute;
import java.util.ArrayList;
import java.util.List;

public class ElectionResult {

    public ElectionResult() {}

    public List<String[]> getElusParDistrika(List<Vote> votes, List<Distrika> distrikaList) {
        List<String[]> elusParDistrika = new ArrayList<>();

        for (Distrika d : distrikaList) {
            String nomDistrika = d.getNom();
            int nbElu = d.getNbElu();

            List<Vote> votesDistrika = new ArrayList<>();
            for (Vote v : votes) {
                if (v.getDistrika().equalsIgnoreCase(nomDistrika)) {
                    votesDistrika.add(v);
                }
            }

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

            if (candidats.isEmpty()) {
                elusParDistrika.add(new String[]{nomDistrika, "Aucun vote"});
                continue;
            }

            for (int i = 0; i < voix.size() - 1; i++) {
                for (int j = i + 1; j < voix.size(); j++) {
                    if (voix.get(j) > voix.get(i)) {
                        int tmpV = voix.get(i);
                        voix.set(i, voix.get(j));
                        voix.set(j, tmpV);

                        String tmpC = candidats.get(i);
                        candidats.set(i, candidats.get(j));
                        candidats.set(j, tmpC);
                    }
                }
            }

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
