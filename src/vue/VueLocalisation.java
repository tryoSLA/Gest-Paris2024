package vue;

import controleur.Lieu;
import controleur.Tableau;
import controleur.Ville;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueLocalisation extends JPanel implements ActionListener {

    //variables de class
    private JTable tableVille, tableLieu;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JButton btAnnuler_lieu = new JButton("Annuler");
    private JButton btAjouter_lieu = new JButton("Ajouter");
    private JButton btMiseAJour_lieu = new JButton("Mettre à jour");
    private JButton btSupprimer_lieu = new JButton("Supprimer");

    private JTextField txtIdVille = new JTextField();
    private JTextField txtLibelleVille = new JTextField();
    private Tableau unTableauVille;

    private JTextField txtIdLieu = new JTextField();
    private JTextField txtLibelleLieu = new JTextField();
    private JComboBox CbIdVille_Lieu = new JComboBox();
    private Tableau unTableauLieu;

    public VueLocalisation ()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);

        Modele.Combo("Libelle_ville", "Ville", CbIdVille_Lieu);

        //Construction de la Jtable
        String entete[] = {"Id", "Libelle"};
        String entete_lieu[] = {"Id", "Libelle", "Ville"};

        this.unTableauVille = new Tableau(this.recupererLesVilles(), entete);

        this.unTableauLieu = new Tableau(this.recupererLesLieux(), entete_lieu);

        this.tableVille = new JTable(unTableauVille)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        this.tableLieu = new JTable(unTableauLieu)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        this.tableVille.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tableVille.getSelectedRow();
                txtIdVille.setText(tableVille.getValueAt(ligne, 0).toString());
                txtLibelleVille.setText(tableVille.getValueAt(ligne, 1).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.tableLieu.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tableLieu.getSelectedRow();
                txtIdLieu.setText(tableLieu.getValueAt(ligne, 0).toString());
                txtLibelleLieu.setText(tableLieu.getValueAt(ligne, 1).toString());
                CbIdVille_Lieu.setSelectedItem(tableLieu.getValueAt(ligne,2).toString());

                int idville = Integer.parseInt(tableLieu.getValueAt(ligne, 2).toString());
                String libelleVille;
                libelleVille = Modele.selectWhereVille(idville);
                CbIdVille_Lieu.setSelectedItem(libelleVille);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableVille);
        uneScroll.setBounds(100, 20, 260, 150);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableVille.setEnabled(true);

        this.panelEdition.setBounds(100, 180, 260, 30);
        this.panelEdition.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();
        gridBC.gridx = gridBC.gridy = 0;
        gridBC.weightx = gridBC.weighty = 1;
        gridBC.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition.add(new JLabel("Libelle : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;

        this.panelEdition.add(txtLibelleVille, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;

        this.add(this.panelEdition);

        this.btAnnuler.setBounds(110, 230, 120, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(240, 230, 120, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(110, 260, 120, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(240, 260, 120, 20);
        this.add(btSupprimer);

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);


        JScrollPane uneScroll_lieu = new JScrollPane(this.tableLieu);
        uneScroll_lieu.setBounds(450, 20, 260, 150);
        uneScroll_lieu.setBackground(Color.black);
        this.add(uneScroll_lieu);
        this.tableLieu.setEnabled(true);

        this.panelEdition2.setBounds(450, 180, 260, 60);
        this.panelEdition2.setLayout(new GridBagLayout());
        GridBagConstraints gridBC2 = new GridBagConstraints();
        gridBC2.gridx = gridBC2.gridy = 0;
        gridBC2.weightx = gridBC2.weighty = 1;
        gridBC2.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition2.add(new JLabel("Libelle : "), gridBC2);
        gridBC2.gridx = 1;
        gridBC2.gridy = 0;

        this.panelEdition2.add(txtLibelleLieu, gridBC2);
        gridBC2.gridx = 2;
        gridBC2.gridy = 0;

        this.panelEdition2.add(new JLabel("Ville : "), gridBC2);
        gridBC2.gridx = 1;
        gridBC2.gridy = 2;

        this.panelEdition2.add(CbIdVille_Lieu, gridBC2);
        gridBC2.gridx = 2;
        gridBC2.gridy = 1;

        this.add(this.panelEdition2);

        this.btAnnuler_lieu.setBounds(450, 250, 120, 20);
        this.add(btAnnuler_lieu);
        this.btAjouter_lieu.setBounds(600, 250, 120, 20);
        this.add(btAjouter_lieu);
        this.btMiseAJour_lieu.setBounds(450, 280, 120, 20);
        this.add(btMiseAJour_lieu);
        this.btSupprimer_lieu.setBounds(600, 280, 120, 20);
        this.add(btSupprimer_lieu);

        //rendre les boutons cliquables
        this.btAnnuler_lieu.addActionListener(this);
        this.btAjouter_lieu.addActionListener(this);
        this.btSupprimer_lieu.addActionListener(this);
        this.btMiseAJour_lieu.addActionListener(this);

        this.setVisible(false);
    }

    //recuperation des donnees sous forme de matrice
    private Object[][] recupererLesVilles()
    {
        ArrayList<Ville> lesVilles = Modele.selectAllVilles();
        Object[][] donnees = new Object[lesVilles.size()][Ville.getNbChampVille()];
        int i = 0;
        for (Ville uneVille : lesVilles)
        {
            donnees[i][0] = uneVille.getId_ville();
            donnees[i][1] = uneVille.getLibelle_ville();
            i++;
        }
        return donnees;
    }
    private Object[][] recupererLesLieux()
    {
        ArrayList<Lieu> lesLieux = Modele.selectAllLieux();
        Object[][] donnees2 = new Object[lesLieux.size()][Lieu.getNbChampLieu()];
        int i = 0;
        for (Lieu unLieu : lesLieux)
        {
            donnees2[i][0] = unLieu.getId_ville();
            donnees2[i][1] = unLieu.getLibelle_lieu();
            donnees2[i][2] = unLieu.getId_ville();
            i++;
        }
        return donnees2;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            //int idVille = Integer.parseInt(txtIdVille.getText());

            Ville uneVille = new Ville(txtLibelleVille.getText());
            int id = Modele.insertVille(uneVille);
            Object data [] = {id, uneVille.getLibelle_ville()};
            this.unTableauVille.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtIdVille.setText("");
            txtLibelleVille.setText("");
        }
        else if (e.getSource() == this.btAnnuler)
        {
            txtIdVille.setText("");
            txtLibelleVille.setText("");
        }
        else if (e.getSource() == this.btSupprimer)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce pays ! \n Voulez-vous continuer ?","Warning",dialogButton);
            if(dialogResult == JOptionPane.NO_OPTION)
            {
                System.out.println("Rien n'a ete suppr");
            }
            else {
                int idVille = Integer.parseInt(txtIdVille.getText());
                Ville uneVille = new Ville(idVille,txtLibelleVille.getText());

                Modele.deleteVille(uneVille);
                JOptionPane.showMessageDialog(this, "Supression effectuée");
                txtIdVille.setText("");
                txtLibelleVille.setText("");

                int rowIndex = tableVille.getSelectedRow();
                unTableauVille.remove(rowIndex);
            }
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idVille = Integer.parseInt(txtIdVille.getText());

            Ville uneVille = new Ville(idVille,txtLibelleVille.getText());
            Modele.updateVille(uneVille);
            JOptionPane.showMessageDialog(this, "Modification réussie");
            Object data [] = {uneVille.getId_ville(), uneVille.getLibelle_ville()};
            int rowIndex = tableVille.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableauVille != null)
            {
                this.unTableauVille.update(rowIndex, data);
            }
        }
        else if (e.getSource() == this.btAjouter_lieu)
        {

            Lieu unLieu = new Lieu(txtLibelleLieu.getText(),Modele.selectIdWhereVille(CbIdVille_Lieu.getSelectedItem().toString()));
            int id = Modele.insertLieu(unLieu);
            Object data [] = {id, unLieu.getLibelle_lieu(),unLieu.getId_ville()};
            this.unTableauLieu.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtIdLieu.setText("");
            txtLibelleLieu.setText("");
            CbIdVille_Lieu.setSelectedItem("");
        }
        else if (e.getSource() == this.btAnnuler_lieu)
        {
            txtIdLieu.setText("");
            txtLibelleLieu.setText("");
            CbIdVille_Lieu.setSelectedItem("");
        }
        else if (e.getSource() == this.btSupprimer_lieu)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce pays ! \n Voulez-vous continuer ?","Warning",dialogButton);
            if(dialogResult == JOptionPane.NO_OPTION)
            {
                System.out.println("Rien n'a ete suppr");
            }
            else {
                int idLieu = Integer.parseInt(txtIdLieu.getText());
                Lieu unLieu = new Lieu(idLieu);

                Modele.deleteLieu(unLieu);
                JOptionPane.showMessageDialog(this, "Supression effectuée");
                txtIdLieu.setText("");
                txtLibelleLieu.setText("");
                CbIdVille_Lieu.setSelectedItem("");

                int rowIndex = tableLieu.getSelectedRow();
                unTableauLieu.remove(rowIndex);
            }
        }
        else if (e.getSource() == this.btMiseAJour_lieu)
        {
            int idLieu = Integer.parseInt(txtIdLieu.getText());

            Lieu unLieu = new Lieu(idLieu,txtLibelleLieu.getText(),Modele.selectIdWhereVille(CbIdVille_Lieu.getSelectedItem().toString()));
            Modele.updateLieu(unLieu);
            JOptionPane.showMessageDialog(this, "Modification réussie");
            Object data [] = {unLieu.getId_lieu(), unLieu.getLibelle_lieu(),unLieu.getId_ville()};
            int rowIndex = tableLieu.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableauLieu != null)
            {
                this.unTableauLieu.update(rowIndex, data);
            }
        }
    }
}