package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import javax.swing.*;

import controleur.Athletes;
import controleur.Pays;
import modele.Modele;
import controleur.Tableau;


public class VueAthletes extends JPanel implements ActionListener {

    private JTable tableAthletes;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAge = new JTextField();
    private JComboBox comboGenre = new JComboBox();
    private JTextField txtTaille = new JTextField();
    private JTextField txtPoids = new JTextField();
    private JTextField txtPhoto = new JTextField();
    private TextArea txtBiographie = new TextArea();
    public static JComboBox comboEquipe = new JComboBox();
    public static JComboBox comboSport = new JComboBox();
    public static JComboBox comboPays = new JComboBox();
    private Tableau unTableau;



    public VueAthletes()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);


        //Construction de la Jtable
        String entete[] = {"Nom", "Prénom", "Age", "Genre", "Taille","Poids","Photo", "Biographie", "Equipe", "Sport","Pays"};

        unTableau = new Tableau(this.recupererLesAthletes(), entete);

        this.tableAthletes = new JTable(unTableau){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        this.tableAthletes.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tableAthletes.getSelectedRow();
                txtNom.setText(tableAthletes.getValueAt(ligne, 0).toString());
                txtPrenom.setText(tableAthletes.getValueAt(ligne, 1).toString());
                txtAge.setText(tableAthletes.getValueAt(ligne, 2).toString());
                comboGenre.setSelectedItem(tableAthletes.getValueAt(ligne, 3).toString());
                txtTaille.setText(tableAthletes.getValueAt(ligne, 4).toString());
                txtPoids.setText(tableAthletes.getValueAt(ligne, 5).toString());
                txtPhoto.setText(tableAthletes.getValueAt(ligne, 6).toString());
                txtBiographie.setText(tableAthletes.getValueAt(ligne, 7).toString());
                int idequipe = Integer.parseInt(tableAthletes.getValueAt(ligne, 8).toString());
                String libelleEquipe;
                libelleEquipe = Modele.selectWhereEquipes(idequipe);
                comboEquipe.setSelectedItem(libelleEquipe);
                int idsport = Integer.parseInt(tableAthletes.getValueAt(ligne, 9).toString());
                String libelleSport;
                libelleSport = Modele.selectWhereSport(idsport);
                comboSport.setSelectedItem(libelleSport);
                int idpays = Integer.parseInt(tableAthletes.getValueAt(ligne, 10).toString());
                String libellePays;
                libellePays = Modele.selectWherePays(idpays);
                comboPays.setSelectedItem(libellePays);

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

        //*****Remplissage des combos******
        comboEquipe.addItem("");
        Modele.Combo("Libelle_sport", "Sport", comboSport);
        Modele.Combo("Libelle_equipe", "Equipe", comboEquipe);
        Modele.Combo("Libelle_pays", "Pays", comboPays);
        comboGenre.addItem("Homme");
        comboGenre.addItem("Femme");



        this.txtBiographie.setBounds(10,10,100,50);

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableAthletes);
        uneScroll.setBounds(20, 20, 600, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableAthletes.setEnabled(true);
        this.panelEdition.setLayout(new GridLayout(3,8));
        //construction du panel d'édition d'un client
        this.panelEdition.setBounds(20, 290, 800, 200);
        this.panelEdition.add(new JLabel("Nom : "));
        this.panelEdition.add(txtNom);
        this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Prénom : "));
        this.panelEdition.add(txtPrenom);
        this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Genre : "));
        this.panelEdition.add(comboGenre);

        //this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Age : "));
        this.panelEdition.add(txtAge);
        this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Taille : "));
        this.panelEdition.add(txtTaille);
        this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Poids : "));
        this.panelEdition.add(txtPoids);
        ///
        this.panelEdition.add(new JLabel("Sport : "));
        this.panelEdition.add(comboSport);
        this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Pays : "));
        this.panelEdition.add(comboPays);
        this.panelEdition.add(new JLabel(""));
        this.panelEdition.add(new JLabel("Equipe : "));
        this.panelEdition.add(comboEquipe);
        //
        this.panelEdition2.setBounds(20, 500, 800, 83);
        this.panelEdition2.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();

        this.panelEdition2.add(new JLabel("Photo : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtPhoto, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(new JLabel("Biographie : "), gridBC);
        gridBC.gridx = 3;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtBiographie, gridBC);
        gridBC.gridx = 4;
        gridBC.gridy = 0;
        gridBC.weightx = 2;


        this.add(this.panelEdition);
        this.add(this.panelEdition2);

        this.btAnnuler.setBounds(100, 600, 100, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(220, 600, 100, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(340, 600, 100, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(460, 600, 100, 20);
        this.add(btSupprimer);

        //this.txtIdClient.setEditable(false);

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);


        this.setVisible(false);

    }

    int age = 0;
    int idpays = 0;
    int idequipe = 0;
    int idsport = 0;
    String libellePays;
    String genre = "";
    float taille = 0.0f;
    float poids = 0.0f;
    try {
        age = Integer.parseInt(txtAge.getText());
        int ligne = tableAthletes.getSelectedRow();
        idequipe = Integer.parseInt(tableAthletes.getValueAt(ligne, 8).toString());
        idsport = Integer.parseInt(tableAthletes.getValueAt(ligne, 9).toString());
        idpays = Integer.parseInt(tableAthletes.getValueAt(ligne, 10).toString());
        genre = comboGenre.getSelectedItem().toString();
        taille = Float.parseFloat(txtTaille.getText());
        poids = Float.parseFloat(txtPoids.getText());
    }
    catch ( NumberFormatException exp)
    {
        JOptionPane.showMessageDialog(this, "Veillez entrer un age correct ! ");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == this.btAjouter)
        {
//

            Athletes unAthlete = new Athletes (txtNom.getText(), txtPrenom.getText(),age,genre,taille,poids,txtPhoto.getText(),txtBiographie.getText(), idequipe, idsport, idpays);
            Modele.insertAthlete(unAthlete);
            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAge.setText("");
            comboGenre.setSelectedItem("");
            txtTaille.setText("");
            txtPoids.setText("");
            txtPhoto.setText("");
            txtBiographie.setText("");
            comboPays.setSelectedItem("");
            comboSport.setSelectedItem("");
            comboEquipe.setSelectedItem("");
            txtAge.setText("");
            Object data [] = {unAthlete.getNom(), unAthlete.getPrenom(), unAthlete.getAge(), unAthlete.getGenre(), unAthlete.getTaille(),unAthlete.getPoids(),unAthlete.getPhoto(), unAthlete.getBiographie(), unAthlete.getId_equipe(), unAthlete.getId_sport(), unAthlete.getId_pays()};
            this.unTableau.add(data);
        }
        else if (e.getSource() == this.btAnnuler)
        {
            txtNom.setText("");
            txtPrenom.setText("");
            txtAge.setText("");
            comboGenre.setSelectedItem("");
            txtTaille.setText("");
            txtPoids.setText("");
            txtPhoto.setText("");
            txtBiographie.setText("");
            comboPays.setSelectedItem("");
            comboSport.setSelectedItem("");
            comboEquipe.setSelectedItem("");
            txtAge.setText("");
        }
        else if (e.getSource() == this.btSupprimer)
        {
            int idAthlete = Integer.parseInt(txtIdClient.getText());
            Athletes unAthlete = new Athletes (txtNom.getText(), txtPrenom.getText(),age,genre,taille,poids,txtPhoto.getText(),txtBiographie.getText(), idequipe, idsport, idpays);
            Modele.deleteAthlete(unAthlete);
            JOptionPane.showMessageDialog(this, "Supression effectuée");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAge.setText("");
            comboGenre.setSelectedItem("");
            txtTaille.setText("");
            txtPoids.setText("");
            txtPhoto.setText("");
            txtBiographie.setText("");
            comboPays.setSelectedItem("");
            comboSport.setSelectedItem("");
            comboEquipe.setSelectedItem("");
            txtAge.setText("");
            int rowIndex = tableAthletes.getSelectedRow();
            unTableau.remove(rowIndex);
        }
//        else if (e.getSource() == this.btMiseAJour)
//        {
//            int idClient = Integer.parseInt(txtIdClient.getText());
//            Client unClient = new Client(idClient, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
//            modele.updateClient(unClient);
//            JOptionPane.showMessageDialog(this, "Mise à jour effectuée");
//            Object data [] = {unClient.getIdclient()+"", unClient.getNom(), unClient.getPrenom(), unClient.getAdresse()};
//            int rowIndex = tableClients.getSelectedRow();
//            this.unTableau.update(rowIndex, data);
//        }

    }
    //recuperer les données sous formes d'une matrice
    private Object[][] recupererLesAthletes ()
    {
        ArrayList<Athletes> lesAthletes = Modele.selectAllAthletes();
        Object[][] donnees = new Object[lesAthletes.size()][Athletes.getNbChampAthletes()];
        int i = 0;
        for (Athletes unAthlete : lesAthletes)
        {
            donnees[i][0] = unAthlete.getNom();
            donnees[i][1] = unAthlete.getPrenom();
            donnees[i][2] = unAthlete.getAge();
            donnees[i][3] = unAthlete.getGenre();
            donnees[i][4] = unAthlete.getTaille();
            donnees[i][5] = unAthlete.getPoids();
            donnees[i][6] = unAthlete.getPhoto();
            donnees[i][7] = unAthlete.getBiographie();
            donnees[i][8] = unAthlete.getId_equipe();
            donnees[i][9] = unAthlete.getId_sport();
            donnees[i][10] = unAthlete.getId_pays();
            i++;
        }
        return donnees;
    }

//    public int ParseInt(String chaine)
//    {
//        int nb = 0;
//        try {
//            nb = Integer.parseInt(chaine.getText());
//        }
//        catch (NumberFormatException exp)
//        {
//            JOptionPane.showMessageDialog(this, "Veillez verifier les champs ! ");
//        }
//        return nb;
//    }




}

