package gui.listener;

import component.FaritanyDropDown;
import component.FaritraDropDown;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaritanyListener implements ActionListener {

    FaritanyDropDown faritanyDropDown;
    FaritraDropDown faritraDropDown;

    public FaritanyListener(FaritanyDropDown faritanyDropDown, FaritraDropDown faritraDropDown) {
        this.faritanyDropDown = faritanyDropDown;
        this.faritraDropDown = faritraDropDown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object selected = faritanyDropDown.getSelectedItem();
        String nomFaritany = selected != null ? selected.toString() : "Tous";
        faritraDropDown.filterByFaritany(nomFaritany);
    }
}
