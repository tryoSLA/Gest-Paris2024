package vue;

import controleur.Evenements;
import controleur.Tableau;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VueEvenement extends JPanel implements ActionListener
{
    //variables de class
    private JTable tableEvenements;
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

    public VueEvenement ()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);

        //Construction de la Jtable
        String entete[] = {"Id", "Libelle", "Image", "Description"};

        this.unTableau = new Tableau(this.recupererLesEvenements(), entete);

        this.tableEvenements = new JTable(unTableau)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        this.tableEvenements.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tableEvenements.getSelectedRow();
                txtId.setText(tableEvenements.getValueAt(ligne, 0).toString());
                txtEmail.setText(tableEvenements.getValueAt(ligne, 1).toString());
                txtPseudo.setText(tableEvenements.getValueAt(ligne, 2).toString());
                txtMotsDePasse.setText(tableEvenements.getValueAt(ligne, 3).toString());
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
        JScrollPane uneScroll = new JScrollPane(this.tableEvenements);
        uneScroll.setBounds(20, 20, 600, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableEvenements.setEnabled(true);
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
    private Object[][] recupererLesEvenements()
    {
        ArrayList<Evenements> lesEvenements = Modele.selectAllEvenements();
        Object[][] donnees = new Object[lesEvenements.size()][Evenements.getNbChampEvenements()];
        int i = 0;
        for (Evenements unEvenements : lesEvenements)
        {
            donnees[i][0] = unEvenements.getIdEvenements();
            donnees[i][1] = unEvenements.getLibelle();
            donnees[i][2] = unEvenements.getImage();
            donnees[i][3] = unEvenements.getDescription();
            i++;
        }
        return donnees;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            int idEvenements = Integer.parseInt(txtId.getText());

            Evenements unEvenements = new Evenements(idEvenements, txtEmail.getText(), txtMotsDePasse.getText(), txtPseudo.getText());
            Modele.insertEvenements(unEvenements);
            Object data [] = {unEvenements.getIdEvenements(), unEvenements.getLibelle(), unEvenements.getImage(), unEvenements.getDescription()};
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
            int idEvenements = Integer.parseInt(txtId.getText());
            Evenements unEvenements = new Evenements(idEvenements, txtEmail.getText(), txtMotsDePasse.getText(), txtPseudo.getText());

            Modele.deleteEvenements(unEvenements);
            JOptionPane.showMessageDialog(this, "Supression effectuée");
            txtId.setText("");
            txtEmail.setText("");
            txtPseudo.setText("");
            txtMotsDePasse.setText("");

            int rowIndex = tableEvenements.getSelectedRow();
            unTableau.remove(rowIndex);
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idEvenements = Integer.parseInt(txtId.getText());

            Evenements unEvenements = new Evenements(idEvenements, txtEmail.getText(), txtMotsDePasse.getText(), txtPseudo.getText());
            Modele.updateEvenements(unEvenements);
            Object data [] = {unEvenements.getIdEvenements()+"", unEvenements.getLibelle(), unEvenements.getImage(), unEvenements.getDescription()};
            int rowIndex = tableEvenements.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null)
            {
                this.unTableau.update(rowIndex, data);
            }
        }
    }
}
