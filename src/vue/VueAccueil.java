package vue;

import modele.Modele;

import javax.swing.*;
import java.awt.*;


public class VueAccueil extends JPanel {


    private JPanel panelEdition = new JPanel();

    public VueAccueil ()
    {
        this.setBounds(20, 70, 850, 750);
        this.setLayout(null);

        ImageIcon logo = new ImageIcon("src/images/Back_head.png");
        JLabel lbLogo = new JLabel(logo);
        lbLogo.setBounds(0, 0, 850, 400);
        //this.add(lbLogo);



        int NbAthelete = Modele.SelectNbAthlete();

        this.panelEdition.setBounds(20, 900, 850, 150);
        this.panelEdition.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();
        gridBC.gridx = gridBC.gridy = 0;
        gridBC.weightx = gridBC.weighty = 1;
        gridBC.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition.add(new JLabel("Nb Athletes : " ), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;

        this.panelEdition.add(new JLabel("aa" ), gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;

        this.add(this.panelEdition);
        this.setVisible(true);

    }



}
