package gui.listener;

import component.BureauVoteDropDown;
import component.DeputeDropDown;
import component.DistrikaDropDown;
import election.BureauVote;
import entity.Distrika;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistrikaListener implements ActionListener {
    DistrikaDropDown distrikaDropDown;
    BureauVoteDropDown bureauVoteDropDown;
    DeputeDropDown deputeDropDown;

    public DistrikaListener(DistrikaDropDown distrikaDropDown, BureauVoteDropDown bureauVoteDropDown, DeputeDropDown deputeDropDown) {
        this.distrikaDropDown = distrikaDropDown;
        this.bureauVoteDropDown = bureauVoteDropDown;
        this.deputeDropDown = deputeDropDown;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object selected = distrikaDropDown.getSelectedItem();
        if (selected instanceof Distrika) {
            Distrika d = (Distrika) selected;
            String nomDistrika = d.getNom();

            bureauVoteDropDown.removeAllItems();
            for (BureauVote bv : distrikaDropDown.getListBureauVotes()) {
                if (bv.getDistrika().equalsIgnoreCase(nomDistrika)) {
                    bureauVoteDropDown.addItem(bv.getNom());
                }
            }

            if (deputeDropDown != null) {
                deputeDropDown.filterByDistrika(nomDistrika);
            }
        }
    }
}
