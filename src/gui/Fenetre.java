package gui;

import candidat.*;
import component.*;
import entity.Depute;
import gui.button.SearchButton;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Fenetre extends JPanel {

    List<Groupe> allGroupes = Groupe.chargerGroupesDepuisFichier("data/Groupe.txt");
    List<Depute> allDeputes = Depute.chargerDeputes("data/Depute.txt");

    public Fenetre() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());

        Localite localite = new Localite();

        SearchButton searchButton = new SearchButton(
                "Rechercher",
                localite.getDistrikaDropDown(),
                localite.getFaritraDropDown(),
                localite.getFaritanyDropDown()
        );

        BureauVoteDropDown bureauVoteDropDown = new BureauVoteDropDown();
        bureauVoteDropDown.setBureauVotes(localite.getDistrikaDropDown().getListBureauVotes());

        topPanel.add(localite);
        // topPanel.add(bureauVoteDropDown);
        topPanel.add(searchButton);

        String[] colonnes = {"Faritany", "Faritra", "Distrika", "Candidat", "Bureau de Vote", "Nb de Votes"};

        DefaultTableModel model = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable resultTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        searchButton.setResultTable(resultTable);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = resultTable.rowAtPoint(evt.getPoint());
                    // System.out.println("Double-clic detecte sur la ligne : " + row);
                    afficherDetailsLigne(resultTable, row);
                }
            }
        });

        JTextArea gagnantArea = new JTextArea(10, 50);
        gagnantArea.setEditable(false);
        gagnantArea.setLineWrap(true);
        gagnantArea.setWrapStyleWord(true);
        JScrollPane gagnantScroll = new JScrollPane(gagnantArea);

        searchButton.setGagnantArea(gagnantArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(gagnantScroll, BorderLayout.SOUTH);
    }

    public void afficherDetailsLigne(JTable table, int rowIndex) {
        String faritany = table.getValueAt(rowIndex, 0).toString();
        String faritra = table.getValueAt(rowIndex, 1).toString();
        String distrika = table.getValueAt(rowIndex, 2).toString();
        String candidat = table.getValueAt(rowIndex, 3).toString();
        String bureau = table.getValueAt(rowIndex, 4).toString();
        String nbVotes = table.getValueAt(rowIndex, 5).toString();

        Groupe groupe = findGroupeByCandidat(allGroupes, candidat);
        String nomGroupe = (groupe != null) ? groupe.getNom() : "Inconnu";

        JDialog dialog = new JDialog();
        dialog.setTitle("Details du Vote");
        dialog.setSize(250, 250);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Faritany : " + faritany));
        panel.add(new JLabel("Faritra : " + faritra));
        panel.add(new JLabel("Distrika : " + distrika));
        panel.add(new JLabel("Candidat : " + candidat));
        panel.add(new JLabel("Groupe : " + nomGroupe));
        panel.add(new JLabel("Bureau : " + bureau));
        panel.add(new JLabel("Nombre de votes : " + nbVotes));

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    public Groupe findGroupeByCandidat(List<Groupe> list, String name) {
        for (Groupe f : list) {
            for (Depute d : allDeputes) {
                if (d.getNom().equalsIgnoreCase(name)) {
                    return f;
                }
            }
        }
        return null;
    }
}
