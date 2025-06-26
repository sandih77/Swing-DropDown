package gui.button;

import component.*;
import entity.Distrika;
import election.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String nomDistrika = distrikaDropDown.getSelectedItem() != null ?
                distrikaDropDown.getSelectedItem().toString() : "";
        String nomFaritra = faritraDropDown.getSelectedItem() != null ?
                faritraDropDown.getSelectedItem().toString() : "";
        String nomFaritany = faritanyDropDown.getSelectedItem() != null ?
                faritanyDropDown.getSelectedItem().toString() : "";

        List<Vote> votes = Vote.lireVotesDepuisFichier("data/Vote.txt");

        List<Vote> votesFiltres = new ArrayList<>();
        for (Vote v : votes) {
            if (nomDistrika.equalsIgnoreCase("Tous") || nomDistrika.isEmpty()) {
                votesFiltres.add(v);
            } else if (v.getDistrika().equalsIgnoreCase(nomDistrika)) {
                votesFiltres.add(v);
            }
        }

        if (resultTable != null) {
            DefaultTableModel model = (DefaultTableModel) resultTable.getModel();
            model.setRowCount(0);
            for (Vote v : votesFiltres) {
                model.addRow(new Object[]{
                        nomFaritany, nomFaritra,
                        v.getDistrika(), v.getCandidat(), v.getNbVotes()
                });
            }

            if (votesFiltres.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aucun résultat trouvé.");
            }
        }

        ElectionResult result = new ElectionResult();
        if (nomDistrika.equalsIgnoreCase("Tous") || nomDistrika.isEmpty()) {
            Map<String, String> gagnants = result.getGagnantParDistrika(votes);
            StringBuilder sb = new StringBuilder("Gagnants par Distrika :\n");
            for (Map.Entry<String, String> entry : gagnants.entrySet()) {
                sb.append("- ").append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
            if (gagnantArea != null) gagnantArea.setText(sb.toString());
        } else {
            Map<String, String> gagnantUnique = result.getGagnantParDistrika(votesFiltres);
            String gagnant = gagnantUnique.getOrDefault(nomDistrika, "Aucun vote");
            if (gagnantArea != null)
                gagnantArea.setText("Gagnant pour " + nomDistrika + " : " + gagnant);
        }
    }
}
