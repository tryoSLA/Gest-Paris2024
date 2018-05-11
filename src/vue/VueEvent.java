package vue;

import controleur.Evenements;
import controleur.Tableau;
import javafx.scene.control.DatePicker;
import modele.Modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInput;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VueEvent extends JPanel implements ActionListener {
    //variables de class
    private JTable tableEvenements;
    private JPanel panelEdition = new JPanel();
    private JPanel panelEdition2 = new JPanel();
    private JButton btChoisir = new JButton("Choisir");
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btAjouter = new JButton("Ajouter");
    private JButton btMiseAJour = new JButton("Mettre à jour");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtId = new JTextField();
    private JFileChooser choseImage = new JFileChooser();
    private JTextField txtTitle = new JTextField();
    private TextArea txtDescription = new TextArea();
    private JTextField txtImage = new JTextField();
    private JTextField txtDate = new JTextField();
    private Tableau unTableau;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public VueEvent() {
        this.setBounds(20, 70, 850, 750);
        this.setLayout(null);

        //Construction de la Jtable
        String entete[] = {"Id", "Titre", "Description", "Date", "Image"};

        this.unTableau = new Tableau(this.recupererLesEvents(), entete);

        this.tableEvenements = new JTable(unTableau) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.tableEvenements.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent m) {
                int ligne = tableEvenements.getSelectedRow();
                txtId.setText(tableEvenements.getValueAt(ligne, 0).toString());
                txtTitle.setText(tableEvenements.getValueAt(ligne, 1).toString());
                txtDescription.setText(tableEvenements.getValueAt(ligne, 2).toString());
                txtDate.setText(tableEvenements.getValueAt(ligne, 3).toString());
                txtImage.setText(tableEvenements.getValueAt(ligne, 4).toString());
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

        this.txtDescription.setBounds(10, 10, 100, 50);

        //Affichage de la JTable dans une ScrollTable
        JScrollPane uneScroll = new JScrollPane(this.tableEvenements);
        uneScroll.setBounds(20, 20, 820, 250);
        uneScroll.setBackground(Color.black);
        this.add(uneScroll);
        this.tableEvenements.setEnabled(true);

        this.panelEdition.setBounds(20, 300, 800, 150);
        this.panelEdition.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();
        gridBC.gridx = gridBC.gridy = 0;
        gridBC.weightx = gridBC.weighty = 1;
        gridBC.fill = GridBagConstraints.HORIZONTAL;

        //this.txtLibelle.setSize(150,20);
        this.panelEdition.add(new JLabel("Titre : "), gridBC);
        gridBC.gridx = 1;
        gridBC.gridy = 0;

        this.panelEdition.add(txtTitle, gridBC);
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

        this.panelEdition2.add(new JLabel("Date : "), gridBC2);
        gridBC2.gridx = 3;
        gridBC2.gridy = 0;

        this.panelEdition2.add(txtDate, gridBC2);
        gridBC2.gridx = 4;
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

        //rendre les boutons cliquables
        this.btAnnuler.addActionListener(this);
        this.btAjouter.addActionListener(this);
        this.btSupprimer.addActionListener(this);
        this.btMiseAJour.addActionListener(this);
        this.btChoisir.addActionListener(this);

        this.setVisible(false);
    }

    //recuperation des donnees sous forme de matrice
    private Object[][] recupererLesEvents() {
        ArrayList<Evenements> lesEvents = Modele.selectAllEvents();
        Object[][] donnees = new Object[lesEvents.size()][Evenements.getNbChampEvents()];
        int i = 0;
        for (Evenements unEvent : lesEvents) {
            donnees[i][0] = unEvent.getIdEvents();
            donnees[i][1] = unEvent.getTitleEvents();
            donnees[i][2] = unEvent.getDescriptionEventsClean();
            donnees[i][3] = unEvent.getDateEvents();
            donnees[i][4] = unEvent.getPhotoEvents();
            i++;
        }
        return donnees;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAjouter) {
            String pathImage = choseImage.getSelectedFile().getAbsolutePath();
            String nameImage = choseImage.getSelectedFile().getName();
            String pathDestination = "C:\\wamp64\\www\\paris2024\\Web\\Images\\Evenement\\" + nameImage;
            File source = new File(pathImage);
            File destination = new File(pathDestination);
            //System.out.println(pathImage + "_____" + nameImage + "___"+ source +"_____"+destination);
            source.renameTo(destination);

            try {
                Date MaDate = format.parse(txtDate.getText());
                Evenements unEvent = new Evenements(txtTitle.getText(), txtDescription.getText(), txtImage.getText(), MaDate);
                Integer id = Modele.insertEvents(unEvent);
                Object data[] = {id, unEvent.getTitleEvents(), unEvent.getDateEvents(), unEvent.getDescriptionEventsClean(), unEvent.getPhotoEvents(), unEvent.getDateEvents()};
                this.unTableau.add(data);
                JOptionPane.showMessageDialog(this, "Insertion réussie");
            } catch (ParseException exp) {
                exp.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Insertion réussie");
            txtId.setText("");
            txtImage.setText("");
            txtDate.setText("");
            txtDescription.setText("");
        } else if (e.getSource() == this.btChoisir) {
            this.choseImage.showOpenDialog(this);
        } else if (e.getSource() == this.btAnnuler) {
            txtId.setText("");
            txtImage.setText("");
            txtDate.setText("");
            txtDescription.setText("");
        } else if (e.getSource() == this.btSupprimer) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Vous risquez d'endommager la base de données, \n vérifiez qu'aucun(e) entité n'est lié ! \n Voulez-vous continuer ?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.NO_OPTION) {
                System.out.println("Rien n'a ete suppr");
            } else {
                int idEvents = Integer.parseInt(txtId.getText());

                try {
                    Date MaDate = format.parse(txtDate.getText());
                    Evenements unEvent = new Evenements(txtTitle.getText(), txtDescription.getText(), txtImage.getText(), MaDate);
                    Modele.deleteEvents(unEvent);
                    JOptionPane.showMessageDialog(this, "Supression effectuée");
                } catch (ParseException exp) {
                    exp.printStackTrace();
                }

                JOptionPane.showMessageDialog(this, "Supression effectuée");
                txtId.setText("");
                txtImage.setText("");
                txtImage.setText("");
                txtDescription.setText("");

                int rowIndex = tableEvenements.getSelectedRow();
                unTableau.remove(rowIndex);
            }
        } else if (e.getSource() == this.btMiseAJour) {
            int idEvents = Integer.parseInt(txtId.getText());

            try {
                Date MaDate = format.parse(txtDate.getText());
                Evenements unEvent = new Evenements(txtTitle.getText(), txtDescription.getText(), txtImage.getText(), MaDate);
                Modele.updateEvents(unEvent);
                JOptionPane.showMessageDialog(this, "Modification réussie");
                Object data[] = {unEvent.getIdEvents() + "", unEvent.getTitleEvents(), unEvent.getDescriptionEventsClean(), unEvent.getDateEvents(), unEvent.getPhotoEvents()};
                int rowIndex = tableEvenements.getSelectedRow();
                System.out.println(rowIndex);
                if (unTableau != null) {
                    this.unTableau.update(rowIndex, data);
                }
            } catch (ParseException exp) {
                exp.printStackTrace();
            }
        }
    }
}
