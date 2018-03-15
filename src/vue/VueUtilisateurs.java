package vue;

import controleur.Pays;
import controleur.Tableau;
import controleur.Utilisateurs;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueUtilisateurs extends JPanel implements ActionListener {


    private JTable tableUtilisateur;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JPanel panelEdition3 = new JPanel();
    private JPanel panelEdition4 = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAge = new JTextField();
    private JComboBox cbGenre = new JComboBox();
    private JComboBox cbRole = new JComboBox();
    private JTextField txtPseudo = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtMdp = new JTextField();

    private Tableau unTableau;

    public VueUtilisateurs ()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);

        //Construction de la Jtable
        String entete[] = {"Id", "Nom", "Prenom", "Age","Genre","Rôle","Pseudo","Email","Mdp"};

        this.unTableau = new Tableau(this.recupererLesUtilisateurs(), entete);

        this.tableUtilisateur = new JTable(unTableau)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        this.tableUtilisateur.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tableUtilisateur.getSelectedRow();
                txtId.setText(tableUtilisateur.getValueAt(ligne, 0).toString());
                txtNom.setText(tableUtilisateur.getValueAt(ligne, 1).toString());
                txtPrenom.setText(tableUtilisateur.getValueAt(ligne, 2).toString());
                txtAge.setText(tableUtilisateur.getValueAt(ligne, 3).toString());
                cbGenre.setSelectedItem(tableUtilisateur.getValueAt(ligne, 4).toString());
                cbRole.setSelectedItem(tableUtilisateur.getValueAt(ligne, 5).toString());
                txtPseudo.setText(tableUtilisateur.getValueAt(ligne, 6).toString());
                txtEmail.setText(tableUtilisateur.getValueAt(ligne, 7).toString());
                txtMdp.setText(tableUtilisateur.getValueAt(ligne, 8).toString());
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

        //Remplissage combo

        cbGenre.addItem("Homme");
        cbGenre.addItem("Femme");

        cbRole.addItem("Admin");
        cbRole.addItem("User");

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableUtilisateur);
        uneScroll.setBounds(20, 20, 700, 150);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableUtilisateur.setEnabled(true);

        this.panelEdition2.setBounds(20, 200, 700, 83);
        this.panelEdition2.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();
        gridBC.gridx = gridBC.gridy = 0;
        gridBC.weightx = gridBC.weighty = 1;
        gridBC.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition2.add(new JLabel("Nom : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;

        this.panelEdition2.add(txtNom, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;

        this.panelEdition2.add(new JLabel(""), gridBC);
        gridBC.gridx = 3;
        gridBC.gridy = 0;

        this.panelEdition2.add(new JLabel("Prenom : "), gridBC);
        gridBC.gridx = 4;
        gridBC.gridy = 0;

        this.panelEdition2.add(txtPrenom, gridBC);
        gridBC.gridx = 5;
        gridBC.gridy = 0;

        this.panelEdition2.add(new JLabel(""), gridBC);
        gridBC.gridx = 6;
        gridBC.gridy = 0;

        this.panelEdition2.add(new JLabel("Age : "), gridBC);
        gridBC.gridx = 7;
        gridBC.gridy = 0;

        this.panelEdition2.add(txtAge, gridBC);
        gridBC.gridx = 8;
        gridBC.gridy = 0;


        this.panelEdition3.setBounds(20, 300, 700, 83);
        this.panelEdition3.setLayout(new GridBagLayout());
        GridBagConstraints gridBC1 = new GridBagConstraints();
        gridBC1.gridx = gridBC1.gridy = 0;
        gridBC1.weightx = gridBC1.weighty = 1;
        gridBC1.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition3.add(new JLabel("Genre : "), gridBC1);
        gridBC1.gridx = 1;
        gridBC1.gridy = 0;

        this.panelEdition3.add(cbGenre, gridBC1);
        gridBC1.gridx = 2;
        gridBC1.gridy = 0;

        this.panelEdition3.add(new JLabel(""), gridBC1);
        gridBC1.gridx = 3;
        gridBC1.gridy = 0;

        this.panelEdition3.add(new JLabel("Rôle : "), gridBC1);
        gridBC1.gridx = 4;
        gridBC1.gridy = 0;

        this.panelEdition3.add(cbRole, gridBC1);
        gridBC1.gridx = 5;
        gridBC1.gridy = 0;


        this.panelEdition4.setBounds(20, 400, 700, 83);
        this.panelEdition4.setLayout(new GridBagLayout());
        GridBagConstraints gridBC2 = new GridBagConstraints();
        gridBC2.gridx = gridBC2.gridy = 0;
        gridBC2.weightx = gridBC2.weighty = 1;
        gridBC2.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition4.add(new JLabel("Pseudo : "), gridBC2);
        gridBC2.gridx = 1;
        gridBC2.gridy = 0;
        this.panelEdition4.add(txtPseudo, gridBC2);
        gridBC2.gridx = 2;
        gridBC2.gridy = 0;
        this.panelEdition4.add(new JLabel(""), gridBC2);
        gridBC2.gridx = 3;
        gridBC2.gridy = 0;
        this.panelEdition4.add(new JLabel("Email: "), gridBC2);
        gridBC2.gridx = 4;
        gridBC2.gridy = 0;
        this.panelEdition4.add(txtEmail, gridBC2);
        gridBC2.gridx = 5;
        gridBC2.gridy = 0;
        this.panelEdition4.add(new JLabel(""), gridBC2);
        gridBC2.gridx = 6;
        gridBC2.gridy = 0;
        this.panelEdition4.add(new JLabel("Mdp: "), gridBC2);
        gridBC2.gridx = 7;
        gridBC2.gridy = 0;
        gridBC2.weightx = 1;
        this.panelEdition4.add(txtMdp, gridBC2);
        gridBC2.gridx = 8;
        gridBC2.gridy = 0;


        this.add(this.panelEdition);
        this.add(this.panelEdition2);
        this.add(this.panelEdition3);
        this.add(this.panelEdition4);

        this.btAnnuler.setBounds(100, 500, 100, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(220, 500, 100, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(340, 500, 100, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(460, 500, 100, 20);
        this.add(btSupprimer);

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);

        this.setVisible(false);
    }

    //recuperation des donnees sous forme de matrice
    private Object[][] recupererLesUtilisateurs()
    {
        ArrayList<Utilisateurs> lesUtilisateurs = Modele.selectAllUsers();
        Object[][] donnees = new Object[lesUtilisateurs.size()][Utilisateurs.getNbChampUtilisateur()];
        int i = 0;
        for (Utilisateurs unUtilisateur : lesUtilisateurs)
        {
            donnees[i][0] = unUtilisateur.getId_personne();
            donnees[i][1] = unUtilisateur.getNom();
            donnees[i][2] = unUtilisateur.getPrenom();
            donnees[i][3] = unUtilisateur.getAge();
            donnees[i][4] = unUtilisateur.getGenre();
            donnees[i][5] = unUtilisateur.getRole();
            donnees[i][6] = unUtilisateur.getPseudo();
            donnees[i][7] = unUtilisateur.getEmail();
            donnees[i][8] = unUtilisateur.getMot_de_passe();
            i++;
        }
        return donnees;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            //int idUser = Integer.parseInt(txtId.getText());
            //System.out.println("id = "+idUser);
            Utilisateurs unUtilisateur = new Utilisateurs(txtNom.getText(), txtPrenom.getText(), Integer.parseInt(txtAge.getText()),cbGenre.getSelectedItem().toString(),cbRole.getSelectedItem().toString(),txtPseudo.getText(),txtEmail.getText(),txtMdp.getText());
            Modele.insertUtilisateur(unUtilisateur);
            Object data [] = {unUtilisateur.getId_personne(),unUtilisateur.getNom(), unUtilisateur.getPrenom(), unUtilisateur.getAge(), unUtilisateur.getGenre(),unUtilisateur.getRole(),unUtilisateur.getPseudo(),unUtilisateur.getPseudo(),unUtilisateur.getMot_de_passe()};
            this.unTableau.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtId.setText("");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAge.setText("");
            cbGenre.setSelectedItem("");
            cbRole.setSelectedItem("");
            txtPseudo.setText("");
            txtEmail.setText("");
            txtMdp.setText("");
        }
        else if (e.getSource() == this.btAnnuler)
        {
            txtId.setText("");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAge.setText("");
            cbGenre.setSelectedItem("");
            cbRole.setSelectedItem("");
            txtPseudo.setText("");
            txtEmail.setText("");
            txtMdp.setText("");
        }
        else if (e.getSource() == this.btSupprimer)
        {
//            int dialogButton = JOptionPane.YES_NO_OPTION;
//            int dialogResult = JOptionPane.showConfirmDialog (null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce pays ! \n Voulez-vous continuer ?","Warning",dialogButton);
//            if(dialogResult == JOptionPane.NO_OPTION)
//            {
//                System.out.println("Rien n'a ete suppr");
//            }
//            else {
                int idUser = Integer.parseInt(txtId.getText());
                Utilisateurs unUtilisateur = new Utilisateurs(idUser, txtNom.getText(), txtPrenom.getText(), Integer.parseInt(txtAge.getText()),cbGenre.getSelectedItem().toString(),cbRole.getSelectedItem().toString(),txtPseudo.getText(),txtEmail.getText(),txtMdp.getText());

                Modele.deleteUtilisateur(unUtilisateur);
                JOptionPane.showMessageDialog(this, "Supression effectuée");
                txtId.setText("");
                txtNom.setText("");
                txtPrenom.setText("");
                txtAge.setText("");
                cbGenre.setSelectedItem("");
                cbRole.setSelectedItem("");
                txtPseudo.setText("");
                txtEmail.setText("");
                txtMdp.setText("");

                int rowIndex = tableUtilisateur.getSelectedRow();
                unTableau.remove(rowIndex);
//            }
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idUser = Integer.parseInt(txtId.getText());

            Utilisateurs unUtilisateur = new Utilisateurs(idUser, txtNom.getText(), txtPrenom.getText(), Integer.parseInt(txtAge.getText()),cbGenre.getSelectedItem().toString(),cbRole.getSelectedItem().toString(),txtPseudo.getText(),txtEmail.getText(),txtMdp.getText());
            Modele.updateUtilisateur(unUtilisateur);
            JOptionPane.showMessageDialog(this, "Modification réussie");
            Object data [] = {unUtilisateur.getId_personne(),unUtilisateur.getNom(), unUtilisateur.getPrenom(), unUtilisateur.getAge(), unUtilisateur.getGenre(),unUtilisateur.getRole(),unUtilisateur.getPseudo(),unUtilisateur.getEmail(),unUtilisateur.getMot_de_passe()};
            int rowIndex = tableUtilisateur.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null)
            {
                this.unTableau.update(rowIndex, data);
            }
        }
    }
}
