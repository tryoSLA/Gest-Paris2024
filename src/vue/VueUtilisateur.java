package vue;

import controleur.Utilisateurs;
import controleur.Tableau;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueUtilisateur extends JPanel implements ActionListener
{
    //variables de class
    private JTable tableUtilisateur;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtPseudo = new JTextField();
    private JTextField txtMotsDePasse = new JTextField();
    private Tableau unTableau;

    public VueUtilisateur ()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);

        //Construction de la Jtable
        String entete[] = {"Id", "Libelle", "Image", "Description"};

        this.unTableau = new Tableau(this.recupererLesUtilisateur(), entete);

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
                txtEmail.setText(tableUtilisateur.getValueAt(ligne, 1).toString());
                txtPseudo.setText(tableUtilisateur.getValueAt(ligne, 2).toString());
                txtMotsDePasse.setText(tableUtilisateur.getValueAt(ligne, 3).toString());
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

        this.txtMotsDePasse.setBounds(10,10,100,50);

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableUtilisateur);
        uneScroll.setBounds(20, 20, 600, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableUtilisateur.setEnabled(true);
        this.panelEdition.setLayout(new GridLayout(3,8));
        //construction du panel d'édition d'un client
        this.panelEdition.setBounds(20, 290, 800, 200);
        this.panelEdition.add(new JLabel("Email : "));
        this.panelEdition.add(txtEmail);
        this.panelEdition.add(new JLabel(""));

        this.panelEdition2.setBounds(20, 500, 800, 83);
        this.panelEdition2.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();

        this.panelEdition2.add(new JLabel("Pseudo : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtPseudo, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(new JLabel("Description : "), gridBC);
        gridBC.gridx = 3;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtMotsDePasse, gridBC);
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

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);

        this.setVisible(false);
    }

    //recuperation des donnees sous forme de matrice
    private Object[][] recupererLesUtilisateur()
    {
        ArrayList<Utilisateur> lesUtilisateur = Modele.selectAllUtilisateur();
        Object[][] donnees = new Object[lesUtilisateur.size()][Utilisateur.getNbChampUtilisateur()];
        int i = 0;
        for (Utilisateur unUtilisateur : lesUtilisateur)
        {
            donnees[i][0] = unUtilisateur.getIdUtilisateur();
            donnees[i][1] = unUtilisateur.getLibelle();
            donnees[i][2] = unUtilisateur.getImage();
            donnees[i][3] = unUtilisateur.getDescription();
            i++;
        }
        return donnees;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            int idUtilisateur = Integer.parseInt(txtId.getText());

            Utilisateur unUtilisateur = new Utilisateur(idUtilisateur, txtEmail.getText(), txtMotsDePasse.getText(), txtPseudo.getText());
            Modele.insertUtilisateur(unUtilisateur);
            Object data [] = {unUtilisateur.getIdUtilisateur(), unUtilisateur.getLibelle(), unUtilisateur.getImage(), unUtilisateur.getDescription()};
            this.unTableau.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtId.setText("");
            txtEmail.setText("");
            txtPseudo.setText("");
            txtMotsDePasse.setText("");
        }
        else if (e.getSource() == this.btAnnuler)
        {
            txtId.setText("");
            txtEmail.setText("");
            txtPseudo.setText("");
            txtMotsDePasse.setText("");
        }
        else if (e.getSource() == this.btSupprimer)
        {
            int idUtilisateur = Integer.parseInt(txtId.getText());
            Utilisateur unUtilisateur = new Utilisateur(idUtilisateur, txtEmail.getText(), txtMotsDePasse.getText(), txtPseudo.getText());

            Modele.deleteUtilisateur(unUtilisateur);
            JOptionPane.showMessageDialog(this, "Supression effectuée");
            txtId.setText("");
            txtEmail.setText("");
            txtPseudo.setText("");
            txtMotsDePasse.setText("");

            int rowIndex = tableUtilisateur.getSelectedRow();
            unTableau.remove(rowIndex);
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idUtilisateur = Integer.parseInt(txtId.getText());

            Utilisateur unUtilisateur = new Utilisateur(idUtilisateur, txtEmail.getText(), txtMotsDePasse.getText(), txtPseudo.getText());
            Modele.updateUtilisateur(unUtilisateur);
            Object data [] = {unUtilisateur.getIdUtilisateur()+"", unUtilisateur.getLibelle(), unUtilisateur.getImage(), unUtilisateur.getDescription()};
            int rowIndex = tableUtilisateur.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null)
            {
                this.unTableau.update(rowIndex, data);
            }
        }
    }
}
