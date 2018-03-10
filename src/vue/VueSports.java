package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import javax.swing.*;

import com.sun.org.apache.xpath.internal.operations.Mod;
import controleur.Sports;
import modele.Modele;
import controleur.Tableau;

public class VueSports extends JPanel implements ActionListener {

    private JTable tableSports;
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

    public VueSports()
    {
        this.setBounds(20, 70, 850, 790);
        this.setLayout(null);


        //Construction de la Jtable
        String entete[] = {"Id", "Libelle", "Image", "Description"};

        this.unTableau = new Tableau(this.recupererLesSports(), entete);

        this.tableSports = new JTable(unTableau){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        this.tableSports.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent m)
            {
                int ligne = tableSports.getSelectedRow();
                txtId.setText(tableSports.getValueAt(ligne, 0).toString());
                txtLibelle.setText(tableSports.getValueAt(ligne, 1).toString());
                txtImage.setText(tableSports.getValueAt(ligne, 2).toString());
                txtDescription.setText(tableSports.getValueAt(ligne, 3).toString());
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

        this.txtDescription.setBounds(10,10,100,50);

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableSports);
        uneScroll.setBounds(20, 20, 600, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableSports.setEnabled(true);
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

        //this.txtIdClient.setEditable(false);

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
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
            int idSport = Integer.parseInt(txtId.getText());

            Sports unSport = new Sports(idSport, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());
            Modele.insertSports(unSport);
            Object data [] = {unSport.getIdSports(), unSport.getLibelle(), unSport.getImage(), unSport.getDescription()};
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
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce sport ! \n Voulez-vous continuer ?","Warning",dialogButton);
            if(dialogResult == JOptionPane.NO_OPTION)
            {
                System.out.println("Rien n'a ete suppr");
            }
            else {
                int idSport = Integer.parseInt(txtId.getText());
                Sports unSport = new Sports(idSport, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());

                Modele.deleteSports(unSport);
                JOptionPane.showMessageDialog(this, "Supression effectuée");
                txtId.setText("");
                txtLibelle.setText("");
                txtImage.setText("");
                txtDescription.setText("");

                int rowIndex = tableSports.getSelectedRow();
                unTableau.remove(rowIndex);
            }
        }
        else if (e.getSource() == this.btMiseAJour)
        {
            int idSport = Integer.parseInt(txtId.getText());

            Sports unSport = new Sports(idSport, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());
            Modele.updateSport(unSport);
            JOptionPane.showMessageDialog(this, "Modification réussie");
            Object data [] = {unSport.getIdSports(), unSport.getLibelle(), unSport.getImage(), unSport.getDescription()};

            int rowIndex = tableSports.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null)
            {
                this.unTableau.update(rowIndex, data);
            }
        }
    }
    //recuperer les données sous formes d'une matrice
    private Object[][] recupererLesSports ()
    {
        ArrayList<Sports> lesSports = Modele.selectAllSports();
        Object[][] donnees = new Object[lesSports.size()][Sports.getNbChampSports()];
        int i = 0;
        for (Sports unSports : lesSports)
        {
            donnees[i][0] = unSports.getIdSports();
            donnees[i][1] = unSports.getLibelle();
            donnees[i][2] = unSports.getImage();
            donnees[i][3] = unSports.getDescription();
            i++;
        }
        return donnees;
    }
}
