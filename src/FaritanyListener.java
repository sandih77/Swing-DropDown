package gui.listener;

import component.FaritanyDropDown;
import component.FaritraDropDown;
import entity.Faritany;

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
        Faritany selected = (Faritany) faritanyDropDown.getSelectedItem();
        if (selected != null) {
            String nomFaritany = selected.getNom();
            faritraDropDown.filterByFaritany(nomFaritany);
        }
    }
}
