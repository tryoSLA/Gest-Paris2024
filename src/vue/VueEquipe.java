//package vue;
//
//import controleur.Equipes;
//import controleur.Tableau;
//import modele.Modele;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.File;
//import java.util.ArrayList;
//
//public class VueEquipe {
//    //variables de class
//    private JTable tableEquipe;
//    private JPanel panelEdition = new JPanel();
//    private JButton btAnnuler = new JButton("Annuler");
//    private JButton btAjouter = new JButton("Ajouter");
//    private JButton btMiseAJour = new JButton("Mettre à jour");
//    private JButton btSupprimer = new JButton("Supprimer");
//
//    private JTextField txtId = new JTextField();
//
//    private JTextField txtLibelle = new JTextField();
//    private JTextField txtNbJoueur = new JTextField();
//    private TextArea txtSport = new TextArea();
//    private Tableau unTableau;
//
//    public VueEquipe()
//    {
//        this.setBounds(20, 70, 850, 750);
//        this.setLayout(null);
//
//        //Construction de la Jtable
//        String entete[] = {"Id", "Libelle", "Nombre de joueur","Sp"};
//
//        this.unTableau = new Tableau(this.recupererLesEquipes(), entete);
//
//        this.tableEquipe = new JTable(unTableau)
//        {
//            public boolean isCellEditable(int row, int column)
//            {
//                return false;
//            }
//        };
//
//        this.tableEquipe.addMouseListener(new MouseListener()
//        {
//            @Override
//            public void mouseClicked(MouseEvent m)
//            {
//                int ligne = tableEquipe.getSelectedRow();
//                txtId.setText(tableEquipe.getValueAt(ligne, 0).toString());
//                txtLibelle.setText(tableEquipe.getValueAt(ligne, 1).toString());
//                txtNbJoueur.setText(tableEquipe.getValueAt(ligne, 2).toString());
//                txtSport.setText(tableEquipe.getValueAt(ligne, 3).toString());
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//
//
//
//        //Affichage de la JTable dans une ScrollTable
//        JScrollPane uneScroll = new JScrollPane(this.tableEquipe);
//        uneScroll.setBounds(20, 20, 820, 250);
//        uneScroll.setBackground(Color.black);
//        this.add(uneScroll);
//        this.tableEquipe.setEnabled(true);
//
//        this.panelEdition.setBounds(20, 300, 800, 150);
//        this.panelEdition.setLayout(new GridBagLayout());
//        GridBagConstraints gridBC = new GridBagConstraints();
//        gridBC.gridx = gridBC.gridy = 0;
//        gridBC.weightx = gridBC.weighty = 1;
//        gridBC.fill = GridBagConstraints.HORIZONTAL;
//
//        //this.txtLibelle.setSize(150,20);
//
//        this.panelEdition.add(new JLabel("Libelle : "), gridBC);
//        gridBC.gridx = 1;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(txtLibelle, gridBC);
//        gridBC.gridx = 2;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel(""), gridBC);
//        gridBC.gridx = 3;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel("Nombre de joueurs: "), gridBC);
//        gridBC.gridx = 4;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(txtNbJoueur, gridBC);
//        gridBC.gridx = 5;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(new JLabel("Sport : "), gridBC);
//        gridBC.gridx = 6;
//        gridBC.gridy = 0;
//
//        this.panelEdition.add(txtSport, gridBC);
//        gridBC.gridx = 7;
//        gridBC.gridy = 0;
//
//
//        this.add(this.panelEdition);
//
//        this.btAnnuler.setBounds(100, 550, 150, 20);
//        this.add(btAnnuler);
//        this.btAjouter.setBounds(270, 550, 150, 20);
//        this.add(btAjouter);
//        this.btMiseAJour.setBounds(440, 550, 150, 20);
//        this.add(btMiseAJour);
//        this.btSupprimer.setBounds(610, 550, 150, 20);
//        this.add(btSupprimer);
//
//
//        //rendre les boutons cliquables
//        this.btAnnuler.addActionListener(this);
//        this.btAjouter.addActionListener(this);
//        this.btSupprimer.addActionListener(this);
//        this.btMiseAJour.addActionListener(this);
//        this.setVisible(false);
//    }
//
//    //recuperation des donnees sous forme de matrice
//    private Object[][] recupererLesEquipes()
//    {
//        ArrayList<Equipes> lesEquipes = Modele.selectAllEquipes();
//        Object[][] donnees = new Object[lesPays.size()][Pays.getNbChampPays()];
//        int i = 0;
//        for (Equipes uneEquipe : lesEquipes)
//        {
//            donnees[i][0] = uneEquipe.getIdequipe();
//            donnees[i][1] = uneEquipe.getLibelleEquipe();
//            donnees[i][2] = uneEquipe.getNbJoueurequipe();
//            donnees[i][3] = uneEquipe.getIdSportEquipe();
//            i++;
//        }
//        return donnees;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == this.btAjouter) {
//
//            Equipes uneEquipe = new Equipes(txtLibelle.getText(), Integer.parseInt(txtNbJoueur.getText()), Integer.parseInt(txtSport.getText()));
//            Modele.insertEquipe(uneEquipe);
//            Object data[] = {uneEquipe.getIdequipe(), uneEquipe.getLibelleEquipe(), uneEquipe.getNbJoueurequipe(), uneEquipe.getIdSportEquipe()};
//
//            this.unTableau.add(data);
//
//            JOptionPane.showMessageDialog(this, "Insertion réussie");
//            txtId.setText("");
//            txtLibelle.setText("");
//            txtNbJoueur.setText("");
//            txtSport.setText("");
//        } else if (e.getSource() == this.btAnnuler) {
//            txtId.setText("");
//            txtLibelle.setText("");
//            txtNbJoueur.setText("");
//            txtSport.setText("");
//        } else if (e.getSource() == this.btSupprimer) {
//            int dialogButton = JOptionPane.YES_NO_OPTION;
//            int dialogResult = JOptionPane.showConfirmDialog(null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce pays ! \n Voulez-vous continuer ?", "Warning", dialogButton);
//            if (dialogResult == JOptionPane.NO_OPTION) {
//                System.out.println("Rien n'a ete suppr");
//            } else {
//                int idEquipe = Integer.parseInt(txtId.getText());
//                Equipes uneEquipe = new Equipes(idEquipe, txtLibelle.getText(),Integer.parseInt(txtNbJoueur.getText()), Integer.parseInt(txtSport.getText()));
//
//
//
//                Modele.deleteEquipe(uneEquipe);
//                JOptionPane.showMessageDialog(this, "Supression effectuée");
//                txtId.setText("");
//                txtLibelle.setText("");
//                txtNbJoueur.setText("");
//                txtSport.setText("");
//
//                int rowIndex = tableEquipe.getSelectedRow();
//                unTableau.remove(rowIndex);
//            }
//        } else if (e.getSource() == this.btMiseAJour) {
//            int idEquipe = Integer.parseInt(txtId.getText());
//
//            Equipes uneEquipe = new Equipes(idEquipe, txtLibelle.getText(),Integer.parseInt(txtNbJoueur.getText()), Integer.parseInt(txtSport.getText()));
//            Modele.updateEquipe(uneEquipe);
//            JOptionPane.showMessageDialog(this, "Modification réussie");
//            Object data[] = {uneEquipe.getIdequipe(), uneEquipe.getLibelleEquipe(), uneEquipe.getNbJoueurequipe(), uneEquipe.getIdSportEquipe()};
//            int rowIndex = tableEquipe.getSelectedRow();
//            System.out.println(rowIndex);
//            if (unTableau != null) {
//                this.unTableau.update(rowIndex, data);
//            }
//        }
//    }
//}
