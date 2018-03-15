package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

import com.sun.org.apache.xpath.internal.operations.Mod;
import controleur.Athletes;
import controleur.Pays;
import modele.Modele;
import controleur.Tableau;


public class VueAthletes extends JPanel implements ActionListener {

    private JTable tableAthletes;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JPanel panelEdition3 = new JPanel();
    private JFileChooser choseImage = new JFileChooser();
    private JButton btChoisir = new JButton("Choisir");
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
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
        String entete[] = {"Id","Nom", "Prénom", "Age", "Genre", "Taille","Poids","Photo", "Biographie", "Equipe", "Sport","Pays"};

        this.unTableau = new Tableau(this.recupererLesAthletes(), entete);
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
                txtId.setText(tableAthletes.getValueAt(ligne, 0).toString());
                txtNom.setText(tableAthletes.getValueAt(ligne, 1).toString());
                txtPrenom.setText(tableAthletes.getValueAt(ligne, 2).toString());
                txtAge.setText(tableAthletes.getValueAt(ligne, 3).toString());
                comboGenre.setSelectedItem(tableAthletes.getValueAt(ligne, 4).toString());
                txtTaille.setText(tableAthletes.getValueAt(ligne, 5).toString());
                txtPoids.setText(tableAthletes.getValueAt(ligne, 6).toString());
                txtPhoto.setText(tableAthletes.getValueAt(ligne, 7).toString());
                txtBiographie.setText(tableAthletes.getValueAt(ligne, 8).toString());
                int idequipe = Integer.parseInt(tableAthletes.getValueAt(ligne, 9).toString());
                String libelleEquipe;
                libelleEquipe = Modele.selectWhereEquipe(idequipe);
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

        this.panelEdition2.add(new JLabel("Biographie : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtBiographie, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;
        gridBC.weightx = 2;

        this.panelEdition3.setBounds(20, 550, 400, 150);
        this.panelEdition3.setLayout(new GridBagLayout());
        GridBagConstraints gridBC2 = new GridBagConstraints();
        gridBC2.gridx = gridBC2.gridy = 0;
        gridBC2.weightx = gridBC2.weighty = 1;
        gridBC2.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition3.add(new JLabel("Image : "), gridBC2);
        gridBC2.gridx = 1;
        gridBC2.gridy = 0;

        this.panelEdition3.add(btChoisir, gridBC2);
        gridBC2.gridx = 2;
        gridBC2.gridy = 0;



        this.add(this.panelEdition);
        this.add(this.panelEdition2);
        this.add(this.panelEdition3);

        this.btAnnuler.setBounds(100, 700, 100, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(220, 700, 100, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(340, 700, 100, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(460, 700, 100, 20);
        this.add(btSupprimer);

        //this.txtIdClient.setEditable(false);

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);
        this.btChoisir.addActionListener(this);


        this.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            String pathImage = choseImage.getSelectedFile().getAbsolutePath();
            String nameImage = choseImage.getSelectedFile().getName();
            String pathDestination = "C:\\wamp64\\www\\paris2024\\Web\\Images\\Athlete\\"+nameImage;
            File source = new File(pathImage);
            File destination = new File(pathDestination);
            //System.out.println(pathImage + "_____" + nameImage + "___"+ source +"_____"+destination);
            source.renameTo(destination);

            if (comboEquipe.getSelectedItem().toString().equals(""))
            {
                String equipe = "NULL";
                Athletes unAthlete = new Athletes (txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()),comboGenre.getSelectedItem().toString(),Float.parseFloat(txtTaille.getText()),Float.parseFloat(txtPoids.getText()),nameImage,txtBiographie.getText(),equipe, Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()), Modele.selectIdWherePays(comboPays.getSelectedItem().toString()));
                Modele.insertAthleteSansEquipe(unAthlete);
                Object data [] = {unAthlete.getIdAthletes(),unAthlete.getNom(), unAthlete.getPrenom(), unAthlete.getAge(), unAthlete.getGenre(), unAthlete.getTaille(),unAthlete.getPoids(),unAthlete.getPhoto(), unAthlete.getBiographie(), unAthlete.getId_equipe(), "NULL", unAthlete.getId_pays()};
                this.unTableau.add(data);
            }else{
                Athletes unAthlete = new Athletes (txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()),comboGenre.getSelectedItem().toString(),Float.parseFloat(txtTaille.getText()),Float.parseFloat(txtPoids.getText()),nameImage,txtBiographie.getText(), Modele.selectIdWhereEquipe(comboEquipe.getSelectedItem().toString()), Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()), Modele.selectIdWherePays(comboPays.getSelectedItem().toString()));
                Modele.insertAthleteAvecEquipe(unAthlete);
                Object data [] = {unAthlete.getIdAthletes(),unAthlete.getNom(), unAthlete.getPrenom(), unAthlete.getAge(), unAthlete.getGenre(), unAthlete.getTaille(),unAthlete.getPoids(),unAthlete.getPhoto(), unAthlete.getBiographie(), unAthlete.getId_equipe(), unAthlete.getId_sport(), unAthlete.getId_pays()};
                this.unTableau.add(data);
            }

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

        }
        else if (e.getSource() == this.btChoisir)
        {
            this.choseImage.showOpenDialog(this);
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
            int idAthlete = Integer.parseInt(txtId.getText());
            Athletes unAthlete = new Athletes (idAthlete,txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()),comboGenre.getSelectedItem().toString(),Float.parseFloat(txtTaille.getText()),Float.parseFloat(txtPoids.getText()),txtPhoto.getText(),txtBiographie.getText(), Modele.selectIdWhereEquipe(comboEquipe.getSelectedItem().toString()), Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()), Modele.selectIdWherePays(comboPays.getSelectedItem().toString()));
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
        else if (e.getSource() == this.btMiseAJour)
        {
            int idAthlete = Integer.parseInt(txtId.getText());

            if (comboEquipe.getSelectedItem().toString().equals(""))
            {
                String equipe = "NULL";
                Athletes unAthlete = new Athletes (idAthlete,txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()),comboGenre.getSelectedItem().toString(),Float.parseFloat(txtTaille.getText()),Float.parseFloat(txtPoids.getText()),txtPhoto.getText(),txtBiographie.getText(),equipe, Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()), Modele.selectIdWherePays(comboPays.getSelectedItem().toString()));
                Modele.updateAthleteSansEquipe(unAthlete);
                Object data [] = {unAthlete.getIdAthletes(),unAthlete.getNom(), unAthlete.getPrenom(), unAthlete.getAge(), unAthlete.getGenre(), unAthlete.getTaille(),unAthlete.getPoids(),unAthlete.getPhoto(), unAthlete.getBiographie(), unAthlete.getId_equipe(), unAthlete.getId_sport(), unAthlete.getId_pays()};
                int rowIndex = tableAthletes.getSelectedRow();
                System.out.println(rowIndex);
                if (unTableau != null)this.unTableau.update(rowIndex, data);
            }else{
                Athletes unAthlete = new Athletes (idAthlete,txtNom.getText(), txtPrenom.getText(),Integer.parseInt(txtAge.getText()),comboGenre.getSelectedItem().toString(),Float.parseFloat(txtTaille.getText()),Float.parseFloat(txtPoids.getText()),txtPhoto.getText(),txtBiographie.getText(), Modele.selectIdWhereEquipe(comboEquipe.getSelectedItem().toString()), Modele.selectIdWhereSport(comboSport.getSelectedItem().toString()), Modele.selectIdWherePays(comboPays.getSelectedItem().toString()));
                Modele.updateAthleteAvecEquipe(unAthlete);
                Object data [] = {unAthlete.getIdAthletes(),unAthlete.getNom(), unAthlete.getPrenom(), unAthlete.getAge(), unAthlete.getGenre(), unAthlete.getTaille(),unAthlete.getPoids(),unAthlete.getPhoto(), unAthlete.getBiographie(), unAthlete.getId_equipe(), unAthlete.getId_sport(), unAthlete.getId_pays()};
                int rowIndex = tableAthletes.getSelectedRow();
                System.out.println(rowIndex);
                if (unTableau != null)
                this.unTableau.update(rowIndex, data);
            }
        }
    }
    //recuperer les données sous formes d'une matrice
    private Object[][] recupererLesAthletes ()
    {
        ArrayList<Athletes> lesAthletes = Modele.selectAllAthletes();
        Object[][] donnees = new Object[lesAthletes.size()][Athletes.getNbChampAthletes()];
        int i = 0;
        for (Athletes unAthlete : lesAthletes)
        {
            donnees[i][0] = unAthlete.getIdAthletes();
            donnees[i][1] = unAthlete.getNom();
            donnees[i][2] = unAthlete.getPrenom();
            donnees[i][3] = unAthlete.getAge();
            donnees[i][4] = unAthlete.getGenre();
            donnees[i][5] = unAthlete.getTaille();
            donnees[i][6] = unAthlete.getPoids();
            donnees[i][7] = unAthlete.getPhoto();
            donnees[i][8] = unAthlete.getBiographie();
            donnees[i][9] = unAthlete.getId_equipe();
            donnees[i][10] = unAthlete.getId_sport();
            donnees[i][11] = unAthlete.getId_pays();
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

