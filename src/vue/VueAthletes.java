package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.jnlp.IntegrationService;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Athletes;
import modele.Modele;
import controleur.Tableau;
import controleur.Equipes;
import modele.Modele;

public class VueAthletes extends JPanel implements ActionListener {

    private JTable tableAthletes;
    private JPanel panelEdition = new JPanel();
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAge = new JTextField();
    private JTextField txtGenre = new JTextField();
    private JTextField txtTaille = new JTextField();
    private JTextField txtPoids = new JTextField();
    private JTextField txtPhoto = new JTextField();
    private JTextField txtBiographie = new JTextField();
    private JTextField txtEquipe = new JTextField();
    private JTextField txtSport = new JTextField();
    private JTextField txtPays = new JTextField();
    private Tableau unTableau;



    public VueAthletes()
    {
        this.setBounds(20, 70, 660, 400);
        this.setLayout(null);
        this.setBackground(Color.red);

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
                txtGenre.setText(tableAthletes.getValueAt(ligne, 3).toString());
                txtTaille.setText(tableAthletes.getValueAt(ligne, 4).toString());
                txtPoids.setText(tableAthletes.getValueAt(ligne, 5).toString());
                txtPhoto.setText(tableAthletes.getValueAt(ligne, 6).toString());
                txtBiographie.setText(tableAthletes.getValueAt(ligne, 7).toString());
                int idequipe = Integer.parseInt(tableAthletes.getValueAt(ligne, 8).toString());
                String libelleEquipe;
                libelleEquipe = Modele.selectWhereEquipes(idequipe);
                txtEquipe.setText(libelleEquipe);
                txtSport.setText(tableAthletes.getValueAt(ligne, 9).toString());
                txtPays.setText(tableAthletes.getValueAt(ligne, 10).toString());

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
        JScrollPane uneScroll = new JScrollPane(this.tableAthletes);
        uneScroll.setBounds(20, 20, 600, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableAthletes.setEnabled(true);

        //construction du panel d'édition d'un client
        this.panelEdition.setBounds(20, 290, 600, 60);
        this.panelEdition.setLayout(new GridLayout(2, 4));
        this.panelEdition.add(new JLabel("Nom : "));
        this.panelEdition.add(txtNom);
        this.panelEdition.add(new JLabel("Prénom : "));
        this.panelEdition.add(txtPrenom);
        this.panelEdition.add(new JLabel("Age : "));
        this.panelEdition.add(txtAge);
        this.panelEdition.add(new JLabel("Genre : "));
        this.panelEdition.add(txtGenre);
        this.panelEdition.add(new JLabel("Taille : "));
        this.panelEdition.add(txtTaille);
        this.panelEdition.add(new JLabel("Poids : "));
        this.panelEdition.add(txtPoids);
        this.panelEdition.add(new JLabel("Photo : "));
        this.panelEdition.add(txtPhoto);
        this.panelEdition.add(new JLabel("Biographie : "));
        this.panelEdition.add(txtBiographie);
        this.panelEdition.add(new JLabel("Equipe : "));
        this.panelEdition.add(txtEquipe);
        this.panelEdition.add(new JLabel("Sport : "));
        this.panelEdition.add(txtSport);
        this.panelEdition.add(new JLabel("Pays : "));
        this.panelEdition.add(txtPays);
        this.add(this.panelEdition);

        this.btAjouter.setBounds(100, 370, 100, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(220, 370, 100, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(340, 370, 100, 20);
        this.add(btSupprimer);

        //this.txtIdClient.setEditable(false);

        //rendre les boutons cliquables
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);


        this.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            int age = 0;
            int pays = 0;
            int equipe = 0;
            int sport = 0;
            float taille = 0.0f;
            float poids = 0.0f;
            try {
                age = Integer.parseInt(txtAge.getText());
                pays = Integer.parseInt(txtPays.getText());
                equipe = Integer.parseInt(txtEquipe.getText());
                sport = Integer.parseInt(txtSport.getText());
                taille = Float.parseFloat(txtTaille.getText());
                poids = Float.parseFloat(txtPoids.getText());
            }
            catch ( NumberFormatException exp)
            {
                JOptionPane.showMessageDialog(this, "Veillez entrer un age correct ! ");
            }
            Athletes unAthlete = new Athletes (txtNom.getText(), txtPrenom.getText(),age,txtGenre.getText(),taille,poids,txtPhoto.getText(),txtBiographie.getText(), equipe, sport, pays);
            Modele.insertAthlete(unAthlete);
            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAge.setText("");
            txtGenre.setText("");
            txtTaille.setText("");
            txtPoids.setText("");
            txtPhoto.setText("");
            txtBiographie.setText("");
            txtPays.setText("");
            txtSport.setText("");
            txtEquipe.setText("");
            txtAge.setText("");
            Object data [] = {unAthlete.getNom(), unAthlete.getPrenom(), unAthlete.getAge(), unAthlete.getGenre(), unAthlete.getTaille(),unAthlete.getPoids(),unAthlete.getPhoto(), unAthlete.getBiographie(), unAthlete.getId_equipe(), unAthlete.getId_sport(), unAthlete.getId_pays()};
            this.unTableau.add(data);
        }
//        else if (e.getSource() == this.btSupprimer)
//        {
//            int idclient = Integer.parseInt(txtIdClient.getText());
//            Client unClient = new Client(idclient, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
//            modele.deleteClient(unClient);
//            JOptionPane.showMessageDialog(this, "Supression effectuée");
//            txtNom.setText("");
//            txtPrenom.setText("");
//            txtAdresse.setText("");
//            int rowIndex = tableClients.getSelectedRow();
//            unTableau.remove(rowIndex);
//        }
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
