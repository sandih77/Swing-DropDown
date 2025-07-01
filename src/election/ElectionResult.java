package election;

import candidat.*;
import entity.*;
import java.util.*;

public class ElectionResult {

    public ElectionResult() {
    }

    public List<String[]> getElusParDistrika(List<Vote> votes,
            List<Distrika> distrikaList,
            List<Groupe> groupeList,
            List<Depute> DeputeList) {
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

            List<String> Deputes = new ArrayList<>();
            List<Integer> voix = new ArrayList<>();

            for (Vote v : votesDistrika) {
                String Depute = v.getCandidat();
                int index = Deputes.indexOf(Depute);
                if (index == -1) {
                    Deputes.add(Depute);
                    voix.add(v.getNbVotes());
                } else {
                    voix.set(index, voix.get(index) + v.getNbVotes());
                }
            }

            if (Deputes.isEmpty()) {
                elusParDistrika.add(new String[]{nomDistrika, "Aucun vote"});
                continue;
            }

            for (int i = 0; i < voix.size() - 1; i++) {
                for (int j = i + 1; j < voix.size(); j++) {
                    if (voix.get(j) > voix.get(i)) {
                        int tmpV = voix.get(i);
                        voix.set(i, voix.get(j));
                        voix.set(j, tmpV);

                        String tmpC = Deputes.get(i);
                        Deputes.set(i, Deputes.get(j));
                        Deputes.set(j, tmpC);
                    }
                }
            }

            String c1 = Deputes.get(0);
            String c2 = voix.size() > 1 ? Deputes.get(1) : null;

            Groupe g1 = findGroupeParDepute(DeputeList, groupeList, c1);
            Groupe g2 = c2 != null ? findGroupeParDepute(DeputeList, groupeList, c2) : null;

            int v1 = voix.get(0);
            int v2 = voix.size() > 1 ? voix.get(1) : 0;

            String resultat;

            if (nbElu == 1 || Deputes.size() == 1) {
                if (v1 == v2 && c2 != null) {
                    if (g1 != null && g2 != null) {
                        // System.out.println("Comparaison des groupes : " + g1.getNom() + " (" + g1.getDateFondation() + ") vs " + g2.getNom() + " (" + g2.getDateFondation() + ")");

                        // System.out.println("Départage entre candidats :");
                        // System.out.println("Candidat 1: " + c1 + ", Groupe: " + (g1 != null ? g1.getNom() : "null") + ", Date Fondation: " + (g1 != null ? g1.getDateFondation() : "null") + ", Votes: " + v1);
                        // System.out.println("Candidat 2: " + c2 + ", Groupe: " + (g2 != null ? g2.getNom() : "null") + ", Date Fondation: " + (g2 != null ? g2.getDateFondation() : "null") + ", Votes: " + v2);
                        int comparaison = comparerDatesFondation(g1, g2);
                        if (comparaison < 0) {
                            resultat = c1 + " (" + v1 + " votes)";
                        } else if (comparaison > 0) {
                            resultat = c2 + " (" + v2 + " votes)";
                        } else {
                            resultat = "egalite parfaite entre " + c1 + " et " + c2;
                        }
                    } else if (g1 != null) {
                        resultat = c1 + " (" + v1 + " votes)";
                    } else if (g2 != null) {
                        resultat = c2 + " (" + v2 + " votes)";
                    } else {
                        resultat = "egalite non resolue entre " + c1 + " et " + c2;
                    }
                } else {
                    resultat = c1 + " (" + v1 + " votes)";
                }
            } else {
                if (v2 * 2 > v1) {
                    resultat = c1 + " et " + c2 + " (" + v1 + " / " + v2 + " votes)";
                } else if ((v2 == v1) && nbElu == 1) {
                    if (g1 != null && g2 != null) {
                        // System.out.println("Comparaison des groupes : " + g1.getNom() + " (" + g1.getDateFondation() + ") vs " + g2.getNom() + " (" + g2.getDateFondation() + ")");

                        // System.out.println("Départage entre candidats :");
                        // System.out.println("Candidat 1: " + c1 + ", Groupe: " + (g1 != null ? g1.getNom() : "null") + ", Date Fondation: " + (g1 != null ? g1.getDateFondation() : "null") + ", Votes: " + v1);
                        // System.out.println("Candidat 2: " + c2 + ", Groupe: " + (g2 != null ? g2.getNom() : "null") + ", Date Fondation: " + (g2 != null ? g2.getDateFondation() : "null") + ", Votes: " + v2);
                        int comparaison = comparerDatesFondation(g1, g2);
                        if (comparaison < 0) {
                            resultat = c1 + " (" + v1 + " votes)";
                        } else if (comparaison > 0) {
                            resultat = c2 + " (" + v2 + " votes)";
                        } else {
                            resultat = "egalite parfaite entre " + c1 + " et " + c2;
                        }
                    } else if (g1 != null) {
                        resultat = c1 + " (" + v1 + " votes)";
                    } else if (g2 != null) {
                        resultat = c2 + " (" + v2 + " votes)";
                    } else {
                        resultat = "egalite non resolue entre " + c1 + " et " + c2;
                    }
                } else {
                    resultat = c1 + " et son suppleant (" + v1 + " votes)";
                }
            }

            elusParDistrika.add(new String[]{nomDistrika, resultat});
        }

        return elusParDistrika;
    }

    public Groupe findGroupeParDepute(List<Depute> DeputeList, List<Groupe> groupeList, String nomDepute) {
        for (Depute c : DeputeList) {
            if (c.getNom().equalsIgnoreCase(nomDepute)) {
                String nomGroupe = c.getGroupe();
                return findGroupeByName(groupeList, nomGroupe);
            }
        }
        return null;
    }

    public Groupe findGroupeByName(List<Groupe> list, String name) {
        for (Groupe f : list) {
            if (f.getNom().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }

    public int comparerDatesFondation(Groupe g1, Groupe g2) {
        if (g1 == null || g2 == null) {
            return 0;
        }
        return g1.getDateFondation().compareTo(g2.getDateFondation());
    }
}
