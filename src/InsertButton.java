package gui.button;

import component.*;
import javax.swing.JButton;

public class InsertButton extends JButton {
    DistrikaDropDown distrikaDropDown;
    FaritraDropDown faritraDropDown;
    FaritanyDropDown faritanyDropDown;

    public InsertButton(String label, DistrikaDropDown distrikaDropDown, FaritraDropDown faritraDropDown, FaritanyDropDown faritanyDropDown) {
        super(label);
        this.distrikaDropDown = distrikaDropDown;
        this.faritraDropDown = faritraDropDown;
        this.faritanyDropDown = faritanyDropDown;
    }

    public void saveVoteToFile(String fichier) {
        
    }
}