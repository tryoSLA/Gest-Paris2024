package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Main;

public class VueGeneral extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel();
	private JButton tabButton [] = new JButton[6];
	private final String tabNom [] = {"Pays", "Sport", "Athlètes","Utilisateurs", "Localisation","Quitter"};

	private VueAthletes uneVueAthletes = new VueAthletes();
 	private VueSports uneVueSports = new VueSports();
 	private VuePays uneVuePays = new VuePays();
 	private VueUtilisateurs uneVueUtilisateurs = new VueUtilisateurs();
 	private VueLocalisation uneVueLocalisation = new VueLocalisation();

	 public VueGeneral(String droits)
	 {
		 this.setTitle("Gestion des pays");
		 this.setLayout(null); 
		 this.setResizable(true);
		 this.setBounds(200, 200, 1000, 1000);
		 this.getContentPane().setBackground(Color.gray);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 this.panelMenu.setBounds(0, 20, 800, 30);
		 this.panelMenu.setLayout(new GridLayout(1,6));


		 //changer icone application
		 ImageIcon logopetit = new ImageIcon("src/images/Logo_paris_2024_simple.png");
		 this.setIconImage(logopetit.getImage());
					 
		for (int i = 0; i<6; i++)
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
		if (e.getSource()==this.tabButton[5])
		{
			this.dispose();
			Main.rendreVisible(true);
		}
		else if (e.getSource()==this.tabButton[0])
		{
			this.uneVuePays.setVisible(true);
			this.uneVueSports.setVisible(false);
			this.uneVueAthletes.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
            this.uneVueLocalisation.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[1])
		{
			this.uneVueSports.setVisible(true);
			this.uneVueAthletes.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[2])
		{
			this.uneVueAthletes.setVisible(true);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
		}
		else if (e.getSource()==this.tabButton[3])
		{
			this.uneVueAthletes.setVisible(false);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueLocalisation.setVisible(false);
			this.uneVueUtilisateurs.setVisible(true);
		}
		else if (e.getSource()==this.tabButton[4])
		{
			this.uneVueAthletes.setVisible(false);
			this.uneVueSports.setVisible(false);
			this.uneVuePays.setVisible(false);
			this.uneVueUtilisateurs.setVisible(false);
			this.uneVueLocalisation.setVisible(true);
		}

	}

}
