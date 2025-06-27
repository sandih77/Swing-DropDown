package component;

import election.BureauVote;
import javax.swing.JComboBox;

public class BureauVoteDropDown extends JComboBox {

    BureauVote[] bureauVotes;

    public void setBureauVotes(BureauVote[] bureauVotes) {
        this.removeAllItems();
        this.bureauVotes = bureauVotes;
        for (BureauVote bv : bureauVotes) {
            this.addItem(bv.getNom());
        }
    }
}
