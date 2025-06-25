package gui.button;

import component.*;
import javax.swing.*;
import java.io.*;
import entity.*;

public class InsertButton extends JButton {

    DistrikaDropDown distrikaDropDown;
    FaritraDropDown faritraDropDown;
    FaritanyDropDown faritanyDropDown;
    DeputeDropDown deputeDropDown;
    JTextField votes;

    public InsertButton(String label, DistrikaDropDown distrikaDropDown, FaritraDropDown faritraDropDown, FaritanyDropDown faritanyDropDown) {
        super(label);
        this.distrikaDropDown = distrikaDropDown;
        this.faritraDropDown = faritraDropDown;
        this.faritanyDropDown = faritanyDropDown;

        addActionListener(e -> saveVoteToFile("data/Vote.txt"));
    }

    public void saveVoteToFile(String fichier) {
        Distrika d = (Distrika) distrikaDropDown.getSelectedItem();
        Depute depute = (Depute) deputeDropDown.getSelectedItem();
        String voteStr = votes.getText().trim();

        int nbVotes;
        try {
            nbVotes = Integer.parseInt(voteStr);
            if (nbVotes < 0) {
                throw new NumberFormatException("Nombre négatif");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre de votes valide (entier positif).", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String line = String.join("|",
                d.getNom(),
                depute.getNom(),
                String.valueOf(nbVotes)
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true))) {
            writer.write(line);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Vote enregistré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement du vote : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
