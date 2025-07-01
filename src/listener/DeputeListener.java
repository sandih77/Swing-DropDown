package gui.listener;

import component.*;
import entity.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeputeListener implements ActionListener {

    DistrikaDropDown distrikaDropDown;
    DeputeDropDown deputeDropDown;

    public DeputeListener(DistrikaDropDown distrikaDropDown, DeputeDropDown deputeDropDown) {
        this.distrikaDropDown = distrikaDropDown;
        this.deputeDropDown = deputeDropDown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Distrika selected = (Distrika) distrikaDropDown.getSelectedItem();
        if (selected != null) {
            String nomDistrika = selected.getNom();
            deputeDropDown.filterByDistrika(nomDistrika);
        }
    }
}
