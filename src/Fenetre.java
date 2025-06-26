package gui;

import component.Localite;
import gui.button.SearchButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Fenetre extends JPanel {

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

        topPanel.add(localite);
        topPanel.add(searchButton);

        String[] colonnes = {"Faritany", "Faritra", "Distrika", "Candidat", "Nb de Votes"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        JTable resultTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        searchButton.setResultTable(resultTable);

        JTextArea gagnantArea = new JTextArea(5, 50);
        gagnantArea.setEditable(false);
        gagnantArea.setLineWrap(true);
        gagnantArea.setWrapStyleWord(true);
        JScrollPane gagnantScroll = new JScrollPane(gagnantArea);

        searchButton.setGagnantArea(gagnantArea);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(gagnantScroll, BorderLayout.SOUTH);
    }
}
