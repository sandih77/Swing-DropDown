package gui.button;

import component.*;
import javax.swing.JButton;

public class SearchButton extends JButton {

    DistrikaDropDown distrikaDropDown;
    FaritanyDropDown faritanyDropDown;
    FaritraDropDown faritraDropDown;

    public SearchButton(String label) {
        super(label);
    }

    public SearchButton(String label, DistrikaDropDown distrikaDropDown, FaritraDropDown faritraDropDown, FaritanyDropDown faritanyDropDown) {
        super(label);
        this.distrikaDropDown = distrikaDropDown;
        this.faritraDropDown = faritraDropDown;
        this.faritanyDropDown = faritanyDropDown;
    }
}
