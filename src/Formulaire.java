package gui;

import component.*;
import gui.button.*;
import gui.listener.*;
import java.awt.*;
import javax.swing.*;

public class Formulaire extends JFrame {

    public Formulaire() {
        setTitle("Swing DropDown");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Localite localite = new Localite();

        DeputeDropDown deputeDropDown = new DeputeDropDown();
        deputeDropDown.getDataDepute("data/Depute.txt");
        localite.getDistrikaDropDown().addActionListener(
                new DeputeListener(localite.getDistrikaDropDown(), deputeDropDown)
        );

        JPanel container = new JPanel(new FlowLayout());

        InsertButton insertButton = new InsertButton(
                "Submit",
                localite.getDistrikaDropDown(),
                localite.getFaritraDropDown(),
                localite.getFaritanyDropDown()
        );

        container.add(localite);
        container.add(insertButton);
        container.add(new JLabel("Député :"));
        container.add(deputeDropDown);

        add(container);
    }
}
