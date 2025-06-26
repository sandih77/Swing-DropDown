package gui.button;

import component.*;
import entity.Distrika;
import election.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
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
        String nomDistrika = distrikaDropDown.getSelectedItem() != null
                ? distrikaDropDown.getSelectedItem().toString() : "";
        String nomFaritra = faritraDropDown.getSelectedItem() != null
                ? faritraDropDown.getSelectedItem().toString() : "";
        String nomFaritany = faritanyDropDown.getSelectedItem() != null
                ? faritanyDropDown.getSelectedItem().toString() : "";

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
            // Cas : Tous les distrika → charger la map complète
            Map<String, Distrika> distrikaMap = DistrikaDropDown.getAllDistrikaAsMap(); // 🔧 tu dois créer cette méthode
            Map<String, String> elus = result.getElusParDistrika(votes, distrikaMap);

            StringBuilder sb = new StringBuilder("Élus par Distrika :\n");
            for (Map.Entry<String, String> entry : elus.entrySet()) {
                sb.append("- ").append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }

            if (gagnantArea != null) {
                gagnantArea.setText(sb.toString());
            }

        } else {
            // Cas : un seul distrika
            Distrika selectedDistrika = distrikaDropDown.getSelectedDistrika();
            Map<String, Distrika> oneDistrikaMap = new HashMap<>();
            oneDistrikaMap.put(nomDistrika, selectedDistrika);

            Map<String, String> elus = result.getElusParDistrika(votesFiltres, oneDistrikaMap);
            String texteElu = elus.getOrDefault(nomDistrika, "Aucun élu");

            if (gagnantArea != null) {
                gagnantArea.setText("Élu(s) pour " + nomDistrika + " : " + texteElu);
            }
        }
    }
}
