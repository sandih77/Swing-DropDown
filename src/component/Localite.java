package component;

import java.awt.*;
import javax.swing.*;
import gui.listener.*;

public class Localite extends JPanel {

    DistrikaDropDown distrikaDropDown;
    FaritraDropDown faritraDropDown;
    FaritanyDropDown faritanyDropDown;

    public Localite() {
        setLayout(new GridLayout(4, 2));
        distrikaDropDown = new DistrikaDropDown();
        faritraDropDown = new FaritraDropDown();
        faritanyDropDown = new FaritanyDropDown();

        distrikaDropDown.getDataDistrika("data/Distrika.txt");
        faritraDropDown.getDataFaritra("data/Faritra.txt");
        faritanyDropDown.getDataFaritany("data/Faritany.txt");

        faritanyDropDown.addActionListener(
                new FaritanyListener(faritanyDropDown, faritraDropDown)
        );

        faritraDropDown.addActionListener(
                new FaritraListener(faritraDropDown, distrikaDropDown)
        );

        add(new JLabel("Faritany :"));
        add(faritanyDropDown);

        add(new JLabel("Faritra :"));
        add(faritraDropDown);

        add(new JLabel("Distrika :"));
        add(distrikaDropDown);
    }

    public DistrikaDropDown getDistrikaDropDown() {
        return this.distrikaDropDown;
    }

    public FaritraDropDown getFaritraDropDown() {
        return this.faritraDropDown;
    }

    public FaritanyDropDown getFaritanyDropDown() {
        return this.faritanyDropDown;
    }
}
