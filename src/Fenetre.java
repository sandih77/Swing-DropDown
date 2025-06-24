package gui;

import component.Localite;
import gui.button.SearchButton;
import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    public Fenetre() {
        setTitle("Swing DropDown");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel container = new JPanel(new FlowLayout());

        Localite localite = new Localite();
        SearchButton searchButton = new SearchButton(
            "Rechercher",
            localite.getDistrikaDropDown(),
            localite.getFaritraDropDown(),
            localite.getFaritanyDropDown()
        );

        container.add(localite);
        container.add(searchButton);

        add(container);
    }
}
