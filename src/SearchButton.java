package gui.button;

import component.*;
import entity.*;
import election.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class SearchButton extends JButton {

    DistrikaDropDown distrikaDropDown;
    FaritraDropDown faritraDropDown;
    FaritanyDropDown faritanyDropDown;
    JTable resultTable;
    JTextArea gagnantArea;

    public SearchButton(String label,
                        DistrikaDropDown distrikaDropDown,
                        FaritraDropDown faritraDropDown,
                        FaritanyDropDown faritanyDropDown) {
        super(label);
        this.distrikaDropDown = distrikaDropDown;
        this.faritraDropDown = faritraDropDown;
        this.faritanyDropDown = faritanyDropDown;

        addActionListener(e -> afficherVotesFiltres());
    }

    public void setResultTable(JTable table) {
        this.resultTable = table;
    }

    public void setGagnantArea(JTextArea area) {
        this.gagnantArea = area;
        this.gagnantArea.setEditable(false);
        this.gagnantArea.setLineWrap(true);
        this.gagnantArea.setWrapStyleWord(true);
    }

    public void afficherVotesFiltres() {
        // Récupération des noms sélectionnés
        String nomFaritany = faritanyDropDown.getSelectedItem() != null
                ? faritanyDropDown.getSelectedItem().toString()
                : "Tous";

        String nomFaritra = faritraDropDown.getSelectedItem() instanceof Faritra
                ? ((Faritra) faritraDropDown.getSelectedItem()).getNom()
                : faritraDropDown.getSelectedItem() != null
                    ? faritraDropDown.getSelectedItem().toString()
                    : "Tous";

        String nomDistrika = distrikaDropDown.getSelectedItem() instanceof Distrika
                ? ((Distrika) distrikaDropDown.getSelectedItem()).getNom()
                : distrikaDropDown.getSelectedItem() != null
                    ? distrikaDropDown.getSelectedItem().toString()
                    : "Tous";

        // Données
        List<Vote> allVotes = Vote.lireVotesDepuisFichier("data/Vote.txt");
        Faritra[] allFaritra = faritraDropDown.getListFaritra();
        Distrika[] allDistrika = distrikaDropDown.getListDistrika();

        // Filtrage
        List<Vote> votesFiltres = new ArrayList<>();
        for (Vote v : allVotes) {
            String vDistrika = v.getDistrika();
            Distrika d = findDistrikaByName(allDistrika, vDistrika);
            if (d == null) continue;

            String dFaritra = d.getFaritra();
            Faritra f = findFaritraByName(allFaritra, dFaritra);
            if (f == null) continue;

            boolean match = true;

            if (!"Tous".equalsIgnoreCase(nomDistrika) && !vDistrika.equalsIgnoreCase(nomDistrika))
                match = false;
            if (!"Tous".equalsIgnoreCase(nomFaritra) && !f.getNom().equalsIgnoreCase(nomFaritra))
                match = false;
            if (!"Tous".equalsIgnoreCase(nomFaritany) && !f.getFaritany().equalsIgnoreCase(nomFaritany))
                match = false;

            if (match) votesFiltres.add(v);
        }

        // Affichage dans la table
        if (resultTable != null) {
            DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
            model.setRowCount(0);
            for (Vote v : votesFiltres) {
                model.addRow(new Object[]{
                        nomFaritany, nomFaritra, v.getDistrika(),
                        v.getCandidat(), v.getNbVotes()
                });
            }

            if (votesFiltres.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aucun résultat trouvé.");
            }
        }

        // Affichage du gagnant
        if (gagnantArea != null) {
            ElectionResult result = new ElectionResult();
            List<Distrika> distrikasConcernes = new ArrayList<>();

            if ("Tous".equalsIgnoreCase(nomDistrika)) {
                for (Distrika d : allDistrika) {
                    boolean match = true;
                    Faritra f = findFaritraByName(allFaritra, d.getFaritra());
                    if (f == null) continue;
                    if (!"Tous".equalsIgnoreCase(nomFaritra) && !f.getNom().equalsIgnoreCase(nomFaritra))
                        match = false;
                    if (!"Tous".equalsIgnoreCase(nomFaritany) && !f.getFaritany().equalsIgnoreCase(nomFaritany))
                        match = false;
                    if (match) distrikasConcernes.add(d);
                }
            } else {
                Distrika selected = findDistrikaByName(allDistrika, nomDistrika);
                if (selected != null) distrikasConcernes.add(selected);
            }

            List<String[]> elus = result.getElusParDistrika(votesFiltres, distrikasConcernes);

            StringBuilder sb = new StringBuilder("Élus :\n");
            for (String[] entry : elus) {
                sb.append("- ").append(entry[0]).append(" : ").append(entry[1]).append("\n");
            }

            gagnantArea.setText(sb.toString());
        }
    }

    public Distrika findDistrikaByName(Distrika[] list, String name) {
        for (Distrika d : list) {
            if (d.getNom().equalsIgnoreCase(name)) return d;
        }
        return null;
    }

    public Faritra findFaritraByName(Faritra[] list, String name) {
        for (Faritra f : list) {
            if (f.getNom().equalsIgnoreCase(name)) return f;
        }
        return null;
    }
}
