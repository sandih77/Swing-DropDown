package gui;

import component.*;
import gui.button.*;
import gui.listener.*;

import javax.swing.*;
import java.awt.*;

public class Formulaire extends JFrame {

    InsertButton insertButton;
    JTextField votes;

    public Formulaire() {
        setTitle("Swing DropDown");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // 🔁 Composant central contenant tous les dropdowns (Faritany, Faritra, Distrika)
        Localite localite = new Localite();
        DistrikaDropDown distrikaDropDown = localite.getDistrikaDropDown();

        // 📌 Composants dropdown supplémentaires
        DeputeDropDown deputeDropDown = new DeputeDropDown();
        deputeDropDown.getDataDepute("data/Depute.txt");

        BureauVoteDropDown bureauVoteDropDown = new BureauVoteDropDown();
        bureauVoteDropDown.setBureauVotes(localite.getDistrikaDropDown().getListBureauVotes());

        // 🔁 Listeners entre composants
        localite.getFaritanyDropDown().addActionListener(
                new FaritanyListener(localite.getFaritanyDropDown(), localite.getFaritraDropDown())
        );

        localite.getFaritraDropDown().addActionListener(
                new FaritraListener(localite.getFaritraDropDown(), localite.getDistrikaDropDown())
        );

        localite.getDistrikaDropDown().addActionListener(
                new DistrikaListener(localite.getDistrikaDropDown(), bureauVoteDropDown, deputeDropDown)
        );

        // 📝 Zone de saisie de votes
        votes = new JTextField();

        // 🔘 Bouton d'insertion
        insertButton = new InsertButton(
                "Submit",
                localite.getDistrikaDropDown(),
                localite.getFaritraDropDown(),
                localite.getFaritanyDropDown(),
                deputeDropDown,
                votes
        );

        // 🎨 Organisation graphique
        JPanel container = new JPanel(new GridLayout(7, 1));

        container.add(localite);
        container.add(votes);
        container.add(insertButton);
        container.add(bureauVoteDropDown);
        container.add(new JLabel("Député :"));
        container.add(deputeDropDown);

        add(container);
    }
}
