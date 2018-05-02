package vue;

import controleur.Athletes;
import modele.Modele;

import javax.swing.*;
import java.awt.*;


public class VueAccueil extends JPanel {


    private JPanel panelEdition = new JPanel();

    public VueAccueil ()
    {
        this.setBounds(20, 70, 850, 650);
        this.setLayout(null);

        ImageIcon logo = new ImageIcon("src/images/Back_head.png");
        JLabel lbLogo = new JLabel(logo);
        lbLogo.setBounds(0, 0, 850, 350);
        this.add(lbLogo);



        int NbAtheletes = Modele.SelectNb("Athlete");
        int NbSports = Modele.SelectNb("Sport");
        int NbPays = Modele.SelectNb("Pays");
        int NbEvenements = Modele.SelectNb("Evenement");
        int NbUser = Modele.SelectNb("Utilisateur");

        this.panelEdition.setBounds(170, 350, 600, 300);
        this.panelEdition.setLayout(new GridLayout(3,5));
        this.panelEdition.add(new JLabel("Nombre d'athletes : " ));
        this.panelEdition.add(new JLabel(""+NbAtheletes));
        this.panelEdition.add(new JLabel("Nombre de sport : " ));
        this.panelEdition.add(new JLabel(""+NbSports));
        this.panelEdition.add(new JLabel("Nombre de pays : " ));
        this.panelEdition.add(new JLabel(""+NbPays ));
        this.panelEdition.add(new JLabel("Nombre d'événements : " ));
        this.panelEdition.add(new JLabel(""+NbEvenements));
        this.panelEdition.add(new JLabel("Nombre d'utilisateurs : " ));
        this.panelEdition.add(new JLabel(""+NbUser));







//        this.panelEdition.setLayout(new GridBagLayout());
//        GridBagConstraints gridBC = new GridBagConstraints();
//        gridBC.gridx = gridBC.gridy = 0;
//        gridBC.weightx = gridBC.weighty = 1;
//        gridBC.fill = GridBagConstraints.HORIZONTAL;
//
//        this.panelEdition.add(new JLabel("Nombre d'athletes : " ), gridBC);
//        gridBC.gridx = 1;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel(""+NbAtheletes), gridBC);
//        gridBC.gridx = 2;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel("Nombre de sport : " ), gridBC);
//        gridBC.gridx = 3;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel(""+NbSports), gridBC);
//        gridBC.gridx = 4;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel("Nombre de pays : " ), gridBC);
//        gridBC.gridx = 1;
//        gridBC.gridy = 1;
//
//        this.panelEdition.add(new JLabel(""+NbPays), gridBC);
//        gridBC.gridx = 2;
//        gridBC.gridy = 1;
//
//        this.panelEdition.add(new JLabel("Nombre d'événements : " ), gridBC);
//        gridBC.gridx = 3;
//        gridBC.gridy = 1;
//
//        this.panelEdition.add(new JLabel(""+NbEvenements), gridBC);
//        gridBC.gridx = 4;
//        gridBC.gridy = 1;
//
//        this.panelEdition.add(new JLabel("Nombre d'utilisateurs : " ), gridBC);
//        gridBC.gridx = 1;
//        gridBC.gridy = 2;
//
//        this.panelEdition.add(new JLabel(""+NbUser), gridBC);
//        gridBC.gridx = 2;
//        gridBC.gridy = 2;
//
//
//        this.panelEdition.add(new JLabel(""+NbUser), gridBC);
//        gridBC.gridx = 2;
//        gridBC.gridy = 2;

        this.add(this.panelEdition);
        this.setVisible(true);

    }



}
