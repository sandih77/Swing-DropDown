package gui;

import component.Localite;
import gui.button.SearchButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Fenetre extends JFrame {

    public Fenetre() {
        setTitle("Résultats Électoraux");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Container principal
        JPanel container = new JPanel(new BorderLayout());

        // Panel haut : filtres et bouton rechercher
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

        // Centre : tableau des résultats
        String[] colonnes = {"Faritany", "Faritra", "Distrika", "Candidat", "Nb de Votes"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        JTable resultTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        searchButton.setResultTable(resultTable);

        // Bas : zone de texte pour afficher les gagnants
        JTextArea gagnantArea = new JTextArea(5, 50);
        gagnantArea.setEditable(false);
        gagnantArea.setLineWrap(true);
        gagnantArea.setWrapStyleWord(true);
        JScrollPane gagnantScroll = new JScrollPane(gagnantArea);

        searchButton.setGagnantArea(gagnantArea);

        // Ajout des composants au container
        container.add(topPanel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(gagnantScroll, BorderLayout.SOUTH);

        add(container);
    }
}
