package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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

public class VueAthletes extends JPanel implements ActionListener {

    private JTable tableClients;
    private JPanel panelEdition = new JPanel();
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtIdClient = new JTextField();
    private Tableau unTableau;



    public VueAthletes()
    {
        this.setBounds(20, 70, 660, 400);
        this.setLayout(null);
        this.setBackground(Color.red);

        //Construction de la Jtable
        String entete[] = {"Nom", "Prénom", "Age", "Genre", "Libelle_Pays", "Photo", "Biographie", "Poids", "Taille", "Libelle_sport"};

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
                txtIdClient.setText(tableAthletes.getValueAt(ligne, 0).toString());
                txtNom.setText(tableAthletes.getValueAt(ligne, 1).toString());
                txtPrenom.setText(tableAthletes.getValueAt(ligne, 2).toString());
                txtAdresse.setText(tableAthletes.getValueAt(ligne, 3).toString());
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
        this.panelEdition.add(new JLabel("ID Client : "));
        this.panelEdition.add(txtIdClient);
        this.panelEdition.add(new JLabel("Nom : "));
        this.panelEdition.add(txtNom);
        this.panelEdition.add(new JLabel("Prénom : "));
        this.panelEdition.add(txtPrenom);
        this.panelEdition.add(new JLabel("Adresse : "));
        this.panelEdition.add(txtAdresse);
        this.add(this.panelEdition);

        this.btAjouter.setBounds(100, 370, 100, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(220, 370, 100, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(340, 370, 100, 20);
        this.add(btSupprimer);

        this.txtIdClient.setEditable(false);

        //rendre les boutons cliquables
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);


        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            Athletes unAthlete = new Client (txtNom.getText(), txtPrenom.getText(),txtAdresse.getText());
            modele.insertclient(unClient);
            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAdresse.setText("");
            Object data [] = {unClient.getIdclient()+"", unClient.getNom(), unClient.getPrenom(), unClient.getAdresse()};
            this.unTableau.add(data);
        }
        else if (e.getSource() == this.btSupprimer)
        {
            int idclient = Integer.parseInt(txtIdClient.getText());
            Client unClient = new Client(idclient, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
            modele.deleteClient(unClient);
            JOptionPane.showMessageDialog(this, "Supression effectuée");
            txtNom.setText("");
            txtPrenom.setText("");
            txtAdresse.setText("");
            int rowIndex = tableClients.getSelectedRow();
            unTableau.remove(rowIndex);
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idClient = Integer.parseInt(txtIdClient.getText());
            Client unClient = new Client(idClient, txtNom.getText(), txtPrenom.getText(), txtAdresse.getText());
            modele.updateClient(unClient);
            JOptionPane.showMessageDialog(this, "Mise à jour effectuée");
            Object data [] = {unClient.getIdclient()+"", unClient.getNom(), unClient.getPrenom(), unClient.getAdresse()};
            int rowIndex = tableClients.getSelectedRow();
            this.unTableau.update(rowIndex, data);
        }

    }
    //recuperer les données sous formes d'une matrice
    private Object[][] recupererLesAthletes ()
    {
        ArrayList<Athletes> lesAthletes = Modele.selectAllClients();
        Object[][] donnees = new Object[lesAthletes.size()][Athletes.getNbChampAthletes()];
        int i = 0;
        for (Athletes unAthlete : lesAthletes)
        {
            donnees[i][0] = unAthlete.getNom()
            donnees[i][1] = unAthlete.getPrenom();
            donnees[i][2] = unAthlete.getAge();
            donnees[i][3] = unAthlete.getGenre();
            donnees[i][3] = unAthlete.getLibellePays();
            donnees[i][3] = unAthlete.getPhoto();
            i++;
        }
        return donnees;
    }
}
