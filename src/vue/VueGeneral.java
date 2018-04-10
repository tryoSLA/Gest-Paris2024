package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Main;

public class VueGeneral extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel();
	private JButton tabButton [] = new JButton[7];
	private final String tabNom [] = {"Accueil", "Pays", "Sport", "Athlètes","Utilisateurs", "Localisation","Quitter"};

	private VueAthletes uneVueAthletes = new VueAthletes();
 	private VueSports uneVueSports = new VueSports();
 	private VuePays uneVuePays = new VuePays();
 	private VueUtilisateurs uneVueUtilisateurs = new VueUtilisateurs();
 	private VueLocalisation uneVueLocalisation = new VueLocalisation();
	private VueAccueil uneVueAccueil = new VueAccueil();

	 public VueGeneral(String droits)
	 {
		 this.setTitle("Gest'Paris2024");
		 this.setLayout(null); 
		 this.setResizable(false);

		 Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		 int height = (int)(dimension.getHeight()/2 - 440);
		 int width  = (int)(dimension.getWidth()/2-455);

		 this.setBounds(width,height, 910, 880);
		 //this.getContentPane().setBackground(Color.gray);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 this.add(this.uneVueAccueil);

		 this.panelMenu.setBounds(20, 20, 850, 40);
		 this.panelMenu.setLayout(new GridLayout(1,7));


		 //changer icone application
		 ImageIcon logopetit = new ImageIcon("src/images/Logo_paris_2024_simple.png");
		 this.setIconImage(logopetit.getImage());
					 
		for (int i = 0; i<7; i++)
		{
			this.tabButton[i] = new JButton(tabNom[i]);
			this.panelMenu.add(this.tabButton[i]);
			this.tabButton[i].addActionListener(this);
		}
		 this.panelMenu.setVisible(true);
		 this.add(this.panelMenu);
		 
		 //this.btPays.addActionListener(this);

		 //Ajout des 4 pannels
		 this.add(this.uneVueAthletes);
		 this.setVisible(true);
		 this.add(this.uneVueSports);
		 this.setVisible(true);
		 this.add(this.uneVuePays);
		 this.setVisible(true);
		 this.add(this.uneVueUtilisateurs);
		 this.setVisible(true);
		 this.add(this.uneVueLocalisation);
		 this.setVisible(true);

	 }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.tabButton[6])
		{
			int r = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if (r==0) {
				this.dispose();
				Main.rendreVisible(true);
			}
		}
		else if (e.getSource()==this.tabButton[1])
		{
			this.uneVuePays.setVisible(true);
			this.uneVueSports.setVisible(false);
			this.uneVueAthletes.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
            this.uneVueLocalisation.setVisible(false);
            this.uneVueAccueil.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[2])
		{
			this.uneVueSports.setVisible(true);
			this.uneVueAthletes.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
			this.uneVueAccueil.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[3])
		{
			this.uneVueAthletes.setVisible(true);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
			this.uneVueAccueil.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[4])
		{
			this.uneVueAthletes.setVisible(false);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
			this.uneVueUtilisateurs.setVisible(true);
			this.uneVueAccueil.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[5])
		{
			this.uneVueAthletes.setVisible(false);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(true);
			this.uneVueAccueil.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[0])
		{
			this.uneVueAthletes.setVisible(false);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
			this.uneVueAccueil.setVisible(true);
		}

	}

}
