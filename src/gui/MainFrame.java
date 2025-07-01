package gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("ETU 004174");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Insertion de Votes", new Formulaire());
        tabs.addTab("Voir les resultats", new Fenetre());

        add(tabs);
    }
}
