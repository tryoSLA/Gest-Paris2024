package vue;

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
    private JTable tableVille;
    private JPanel panelEdition = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtIdVille = new JTextField();
    private JTextField txtLibelleVille = new JTextField();
    private Tableau unTableauVille;

    public VueLocalisation ()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);

        //Construction de la Jtable
        String entete[] = {"Id", "Libelle"};

        this.unTableauVille = new Tableau(this.recupererLesVilles(), entete);

        this.tableVille = new JTable(unTableauVille)
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


        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableVille);
        uneScroll.setBounds(20, 20, 260, 150);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableVille.setEnabled(true);

        this.panelEdition.setBounds(20, 180, 260, 30);
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

        this.btAnnuler.setBounds(10, 230, 120, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(140, 230, 120, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(10, 260, 120, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(140, 260, 120, 20);
        this.add(btSupprimer);

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            //int idVille = Integer.parseInt(txtIdVille.getText());

            Ville uneVille = new Ville(txtLibelleVille.getText());
            Modele.insertVille(uneVille);
            Object data [] = {uneVille.getId_ville(), uneVille.getLibelle_ville()};
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
    }
}