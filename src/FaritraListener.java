package gui.listener;

import component.DistrikaDropDown;
import component.FaritraDropDown;
import entity.Faritra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaritraListener implements ActionListener {

    private final FaritraDropDown faritraDropDown;
    private final DistrikaDropDown distrikaDropDown;

    public FaritraListener(FaritraDropDown faritraDropDown, DistrikaDropDown distrikaDropDown) {
        this.faritraDropDown = faritraDropDown;
        this.distrikaDropDown = distrikaDropDown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Faritra selectedFaritra = (Faritra) faritraDropDown.getSelectedItem();
        if (selectedFaritra != null) {
            String nomFaritra = selectedFaritra.getNom();
            // System.out.println("→ Faritra sélectionné : " + nomFaritra);

            distrikaDropDown.filterByFaritra(nomFaritra);
        }
    }
}
