package gui.listener;

import component.DistrikaDropDown;
import component.FaritraDropDown;
import entity.Faritra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaritraListener implements ActionListener {

    FaritraDropDown faritraDropDown;
    DistrikaDropDown distrikaDropDown;

    public FaritraListener(FaritraDropDown faritraDropDown, DistrikaDropDown distrikaDropDown) {
        this.faritraDropDown = faritraDropDown;
        this.distrikaDropDown = distrikaDropDown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object selectedFaritraObj = faritraDropDown.getSelectedItem();
        if (selectedFaritraObj == null) {
            distrikaDropDown.removeAllItems();
            return;
        }
        String selectedFaritra = selectedFaritraObj.toString();

        String selectedFaritany = "";

        if (faritraDropDown.getListFaritra() != null) {
            for (Faritra f : faritraDropDown.getListFaritra()) {
                if (f.getNom().equalsIgnoreCase(selectedFaritra)) {
                    selectedFaritany = f.getFaritany();
                    break;
                }
            }
        }

        distrikaDropDown.filterByFaritanyAndFaritra(selectedFaritany, selectedFaritra, faritraDropDown.getListFaritra());
    }
}
