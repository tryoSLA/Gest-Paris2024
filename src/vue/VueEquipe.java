package vue;

import controleur.Equipes;
import controleur.Tableau;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueEquipe extends JPanel implements ActionListener {

    private JTable tableEquipe;

    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition1 = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
    private JTextField txtLibelle = new JTextField();
    private JTextField txtNbJoueur = new JTextField();
    private JComboBox comboSport = new JComboBox();
    private Tableau unTableau;

    public VueEquipe() {

        Modele.Combo("Libelle_sport", "Sport", comboSport);

        this.setBounds(20, 70, 850, 750);
        this.setLayout(null);


        //Construction de la Jtable
        String entete[] = {"Id", "Libelle", "Nombre de joueur", "Sport"};

        this.unTableau = new Tableau(this.recupererLesEquipes(), entete);

        this.tableEquipe = new JTable(unTableau) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tableEquipe.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent m) {
                int ligne = tableEquipe.getSelectedRow();
                txtId.setText(tableEquipe.getValueAt(ligne, 0).toString());
                txtLibelle.setText(tableEquipe.getValueAt(ligne, 1).toString());
                txtNbJoueur.setText(tableEquipe.getValueAt(ligne, 2).toString());
                int idsport = Integer.parseInt(tableEquipe.getValueAt(ligne, 3).toString());
                String libelleSport;
                libelleSport = Modele.selectWhereSport(idsport);
                comboSport.setSelectedItem(libelleSport);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableEquipe);
        uneScroll.setBounds(20, 20, 820, 150);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableEquipe.setEnabled(true);

        this.panelEdition.setBounds(20, 180, 800, 30);
        this.panelEdition.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();
        gridBC.gridx = gridBC.gridy = 0;
        gridBC.weightx = gridBC.weighty = 1;
        gridBC.fill = GridBagConstraints.HORIZONTAL;

        //this.txtLibelle.setSize(150,20);

        this.panelEdition.add(new JLabel("Libelle : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;

        this.panelEdition.add(txtLibelle, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;

        this.panelEdition.add(new JLabel(""), gridBC);
        gridBC.gridx = 3;
        gridBC.gridy = 0;

        this.panelEdition.add(new JLabel("Nombre de joueur dans l'équipe : "), gridBC);
        gridBC.gridx = 4;
        gridBC.gridy = 0;

        this.panelEdition.add(txtNbJoueur, gridBC);
        gridBC.gridx = 5;
        gridBC.gridy = 0;


        this.panelEdition1.setBounds(20, 220, 400, 30);
        this.panelEdition1.setLayout(new GridBagLayout());
        GridBagConstraints gridBC1 = new GridBagConstraints();
        gridBC1.gridx = gridBC1.gridy = 0;
        gridBC1.weightx = gridBC1.weighty = 1;
        gridBC1.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition1.add(new JLabel("Sport : "), gridBC1);
        gridBC1.gridx = 1;
        gridBC1.gridy = 0;

        this.panelEdition1.add(comboSport, gridBC1);
        gridBC1.gridx = 2;
        gridBC1.gridy = 0;
        

        this.add(this.panelEdition);
        this.add(this.panelEdition1);

        this.btAnnuler.setBounds(100, 300, 150, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(270, 300, 150, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(440, 300, 150, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(610, 300, 150, 20);
        this.add(btSupprimer);


        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);
        
        this.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAjouter) {


            Equipes uneEquipe = new Equipes(txtLibelle.getText(), Integer.parseInt(txtNbJoueur.getText()), Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()));
            Integer id = Modele.insertEquipe(uneEquipe);
            Object data[] = {id, uneEquipe.getLibelleEquipe(), uneEquipe.getNbJoueurequipe(), uneEquipe.getIdSportEquipe()};
            this.unTableau.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtId.setText("");
            txtLibelle.setText("");
            txtNbJoueur.setText("");
            comboSport.setSelectedItem("");
        }
        else if (e.getSource() == this.btAnnuler)
        {
            txtId.setText("");
            txtLibelle.setText("");
            txtNbJoueur.setText("");
            comboSport.setSelectedItem("");
        }
        else if (e.getSource() == this.btSupprimer)
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce sport ! \n Voulez-vous continuer ?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.NO_OPTION)
            {
                System.out.println("Rien n'a ete suppr");
            } else {
                int idEquipe = Integer.parseInt(txtId.getText());
                Equipes uneEquipe = new Equipes(idEquipe,txtLibelle.getText(), Integer.parseInt(txtNbJoueur.getText()), Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()));

                Modele.deleteEquipes(uneEquipe);
                JOptionPane.showMessageDialog(this, "Supression effectuée");
                txtId.setText("");
                txtLibelle.setText("");
                txtNbJoueur.setText("");
                comboSport.setSelectedItem("");

                int rowIndex = tableEquipe.getSelectedRow();
                unTableau.remove(rowIndex);
            }
        } else if (e.getSource() == this.btMiseAJour) {
            int idEquipe = Integer.parseInt(txtId.getText());

            Equipes uneEquipe = new Equipes(idEquipe,txtLibelle.getText(), Integer.parseInt(txtNbJoueur.getText()), Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()));
            Modele.updateEquipe(uneEquipe);
            JOptionPane.showMessageDialog(this, "Modification réussie");
            Object data[] = {uneEquipe.getIdequipe(), uneEquipe.getLibelleEquipe(), uneEquipe.getNbJoueurequipe(), uneEquipe.getIdSportEquipe()};

            int rowIndex = tableEquipe.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null) {
                this.unTableau.update(rowIndex, data);
            }
        }
    }

    //recuperer les données sous formes d'une matrice
    private Object[][] recupererLesEquipes() {
        ArrayList<Equipes> lesEquipes = Modele.selectAllEquipes();
        Object[][] donnees = new Object[lesEquipes.size()][Equipes.getNbChampEquipe()];
        int i = 0;
        for (Equipes uneEquipe : lesEquipes) {
            donnees[i][0] = uneEquipe.getIdequipe();
            donnees[i][1] = uneEquipe.getLibelleEquipe();
            donnees[i][2] = uneEquipe.getNbJoueurequipe();
            donnees[i][3] = uneEquipe.getIdSportEquipe();
            i++;
        }
        return donnees;
    }

}
