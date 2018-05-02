package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.*;
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
    private JPanel panelEdition3 = new JPanel();
    private JButton btChoisir = new JButton("Choisir");
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
    private JTextField txtLibelle = new JTextField();
    private JFileChooser choseImage = new JFileChooser();
    private JTextField txtImage = new JTextField();
    private TextArea txtDescription = new TextArea();
    private Tableau unTableau;

    public VueSports() {
        this.setBounds(20, 70, 850, 750);
        this.setLayout(null);
        //this.setBackground(Color.gray);


        //Construction de la Jtable
        String entete[] = {"Id", "Libelle", "Image", "Description"};

        this.unTableau = new Tableau(this.recupererLesSports(), entete);

        this.tableSports = new JTable(unTableau) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tableSports.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent m) {
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

        this.txtDescription.setBounds(10, 10, 100, 50);

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableSports);
        uneScroll.setBounds(20, 20, 820, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableSports.setEnabled(true);

        this.panelEdition.setBounds(20, 300, 800, 150);
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

        this.panelEdition.add(new JLabel("Description : "), gridBC);
        gridBC.gridx = 4;
        gridBC.gridy = 0;

        this.panelEdition.add(txtDescription, gridBC);
        gridBC.gridx = 5;
        gridBC.gridy = 0;


        this.panelEdition2.setBounds(20, 400, 400, 150);
        this.panelEdition2.setLayout(new GridBagLayout());
        GridBagConstraints gridBC2 = new GridBagConstraints();
        gridBC2.gridx = gridBC2.gridy = 0;
        gridBC2.weightx = gridBC2.weighty = 1;
        gridBC2.fill = GridBagConstraints.HORIZONTAL;

        this.panelEdition2.add(new JLabel("Image : "), gridBC2);
        gridBC2.gridx = 1;
        gridBC2.gridy = 0;

        this.panelEdition2.add(btChoisir, gridBC2);
        gridBC2.gridx = 2;
        gridBC2.gridy = 0;

        this.add(this.panelEdition);
        this.add(this.panelEdition2);


        this.btAnnuler.setBounds(100, 550, 150, 20);
        this.add(btAnnuler);
        this.btAjouter.setBounds(270, 550, 150, 20);
        this.add(btAjouter);
        this.btMiseAJour.setBounds(440, 550, 150, 20);
        this.add(btMiseAJour);
        this.btSupprimer.setBounds(610, 550, 150, 20);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAjouter) {
            String pathImage = choseImage.getSelectedFile().getAbsolutePath();
            String nameImage = choseImage.getSelectedFile().getName();
            String pathDestination = "C:\\wamp64\\www\\paris2024\\Web\\Images\\Sports\\"+nameImage;
            File source = new File(pathImage);
            File destination = new File(pathDestination);
            //System.out.println(pathImage + "_____" + nameImage + "___"+ source +"_____"+destination);
            source.renameTo(destination);

            //int idSport = Integer.parseInt(txtId.getText());

            Sports unSport = new Sports(txtLibelle.getText(), txtDescription.getText(), nameImage);
            int id = Modele.insertSports(unSport);
            Object data[] = {id, unSport.getLibelle(), unSport.getImage(), unSport.getDescription()};
            this.unTableau.add(data);

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtId.setText("");
            txtLibelle.setText("");
            txtImage.setText("");
            txtDescription.setText("");
        } else if (e.getSource() == this.btChoisir)
        {
            this.choseImage.showOpenDialog(this);
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
            int dialogResult = JOptionPane.showConfirmDialog(null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) athlete (ou sport) n'est lier a ce sport ! \n Voulez-vous continuer ?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.NO_OPTION)
            {
                System.out.println("Rien n'a ete suppr");
            } else {
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
        } else if (e.getSource() == this.btMiseAJour) {
            int idSport = Integer.parseInt(txtId.getText());

            Sports unSport = new Sports(idSport, txtLibelle.getText(), txtDescription.getText(), txtImage.getText());
            Modele.updateSport(unSport);
            JOptionPane.showMessageDialog(this, "Modification réussie");
            Object data[] = {unSport.getIdSports(), unSport.getLibelle(), unSport.getImage(), unSport.getDescription()};

            int rowIndex = tableSports.getSelectedRow();
            System.out.println(rowIndex);
            if (unTableau != null) {
                this.unTableau.update(rowIndex, data);
            }
        }
    }

    //recuperer les données sous formes d'une matrice
    private Object[][] recupererLesSports() {
        ArrayList<Sports> lesSports = Modele.selectAllSports();
        Object[][] donnees = new Object[lesSports.size()][Sports.getNbChampSports()];
        int i = 0;
        for (Sports unSports : lesSports) {
            donnees[i][0] = unSports.getIdSports();
            donnees[i][1] = unSports.getLibelle();
            donnees[i][2] = unSports.getImage();
            donnees[i][3] = unSports.getDescription();
            i++;
        }
        return donnees;
    }

}