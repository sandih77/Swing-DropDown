package gui;

import component.*;
import gui.button.*;
import gui.listener.*;

import javax.swing.*;
import java.awt.*;

public class Formulaire extends JPanel {

    InsertButton insertButton;
    JTextField votes;

    public Formulaire() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Localite localite = new Localite();
        DistrikaDropDown distrikaDropDown = localite.getDistrikaDropDown();

        DeputeDropDown deputeDropDown = new DeputeDropDown();
        deputeDropDown.getDataDepute("data/Depute.txt");

        BureauVoteDropDown bureauVoteDropDown = new BureauVoteDropDown();
        bureauVoteDropDown.setBureauVotes(distrikaDropDown.getListBureauVotes());

        localite.getFaritanyDropDown().addActionListener(
                new FaritanyListener(localite.getFaritanyDropDown(), localite.getFaritraDropDown())
        );
        localite.getFaritraDropDown().addActionListener(
                new FaritraListener(localite.getFaritraDropDown(), distrikaDropDown)
        );
        distrikaDropDown.addActionListener(
                new DistrikaListener(distrikaDropDown, bureauVoteDropDown, deputeDropDown)
        );

        votes = new JTextField(10);

        insertButton = new InsertButton(
                "Soumettre",
                distrikaDropDown,
                localite.getFaritraDropDown(),
                localite.getFaritanyDropDown(),
                deputeDropDown,
                votes
        );

        add(wrapPanel("Localité :", localite));
        add(wrapPanel("Député :", deputeDropDown));
        add(wrapPanel("Bureau de Vote :", bureauVoteDropDown));
        add(wrapPanel("Nombre de Votes :", votes, insertButton));
    }

    public JPanel wrapPanel(String labelText, JComponent... components) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 25));
        panel.add(label);
        for (JComponent comp : components) {
            panel.add(comp);
        }
        return panel;
    }
}
