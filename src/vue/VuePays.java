package vue;

import controleur.Pays;
import controleur.Tableau;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VuePays extends JPanel implements ActionListener
{
    //variables de class
    private JTable tablePays;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
    private JTextField txtLibelle = new JTextField();
    private JTextField txtImage = new JTextField();
    private TextArea txtDescription = new TextArea();
    private Tableau unTableau;

    public VuePays ()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);

        //Construction de la Jtable
        String entete[] = {"Id", "Libelle", "Image", "Description"};

        this.unTableau = new Tableau(this.recupererLesPays(), entete);

        this.tablePays = new JTable(unTableau)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        this.tablePays.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tablePays.getSelectedRow();
                txtId.setText(tablePays.getValueAt(ligne, 0).toString());
                txtLibelle.setText(tablePays.getValueAt(ligne, 1).toString());
                txtImage.setText(tablePays.getValueAt(ligne, 2).toString());
                txtDescription.setText(tablePays.getValueAt(ligne, 3).toString());
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

        this.txtDescription.setBounds(10,10,100,50);

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tablePays);
        uneScroll.setBounds(20, 20, 600, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tablePays.setEnabled(true);
        this.panelEdition.setLayout(new GridLayout(3,8));
        //construction du panel d'édition d'un client
        this.panelEdition.setBounds(20, 290, 800, 200);
        this.panelEdition.add(new JLabel("Libelle : "));
        this.panelEdition.add(txtLibelle);
        this.panelEdition.add(new JLabel(""));

        this.panelEdition2.setBounds(20, 500, 800, 83);
        this.panelEdition2.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();

        this.panelEdition2.add(new JLabel("Image : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtImage, gridBC);
        gridBC.gridx = 2;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(new JLabel("Description : "), gridBC);
        gridBC.gridx = 3;
        gridBC.gridy = 0;
        gridBC.weightx = 1;
        this.panelEdition2.add(txtDescription, gridBC);
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
    private Object[][] recupererLesPays()
    {
        ArrayList<Pays> lesPays = Modele.selectAllPays();
        Object[][] donnees = new Object[lesPays.size()][Pays.getNbChampPays()];
        int i = 0;
        for (Pays unPays : lesPays)
        {
            donnees[i][0] = unPays.getIdPays();
            donnees[i][1] = unPays.getLibelle();
            donnees[i][2] = unPays.getImage();
            donnees[i][3] = unPays.getDescription();
            i++;
        }
        return donnees;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btAjouter)
        {
            int idPays = Integer.parseInt(txtId.getText());

            Pays unPays = new Pays(idPays, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());
            Modele.insertPays(unPays);
            Object data [] = {unPays.getIdPays(), unPays.getLibelle(), unPays.getImage(), unPays.getDescription()};
            this.unTableau.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtId.setText("");
            txtLibelle.setText("");
            txtImage.setText("");
            txtDescription.setText("");
        }
        else if (e.getSource() == this.btAnnuler)
        {
            txtId.setText("");
            txtLibelle.setText("");
            txtImage.setText("");
            txtDescription.setText("");
        }
        else if (e.getSource() == this.btSupprimer)
        {
            int idPays = Integer.parseInt(txtId.getText());
            Pays unPays = new Pays(idPays, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());

            Modele.deletePays(unPays);
            JOptionPane.showMessageDialog(this, "Supression effectuée");
            txtId.setText("");
            txtLibelle.setText("");
            txtImage.setText("");
            txtDescription.setText("");

            int rowIndex = tablePays.getSelectedRow();
            unTableau.remove(rowIndex);
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idPays = Integer.parseInt(txtId.getText());

            Pays unPays = new Pays(idPays, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());
            Modele.updatePays(unPays);
            Object data [] = {unPays.getIdPays()+"", unPays.getLibelle(), unPays.getImage(), unPays.getDescription()};
            int rowIndex = tablePays.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null)
            {
                this.unTableau.update(rowIndex, data);
            }
        }
    }
}
