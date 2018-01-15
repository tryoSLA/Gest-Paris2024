package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controleur.Main;

public class VueGeneral extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel();
	private JButton tabButton [] = new JButton[4];
	private final String tabNom [] = {"Pays", "Sport", "Athlètes", "Quitter"};

	 public VueGeneral(String droits)
	 {
		 this.setTitle("Gestion des interventions");
		 this.setLayout(null); 
		 this.setResizable(false); 
		 this.setBounds(200, 200, 700, 500); 
		 this.getContentPane().setBackground(Color.gray);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 this.panelMenu.setBounds(0, 20, 700, 30);
		 this.panelMenu.setLayout(new GridLayout(1,4));
		 
//		 this.panelMenu.add(this.btClients);
//		 this.panelMenu.add(this.btTechs);
//		 this.panelMenu.add(this.btInters);
//		 this.panelMenu.add(this.btQuitter);
					 
		for (int i = 0; i<4; i++)
		{
			this.tabButton[i] = new JButton(tabNom[i]);
			this.panelMenu.add(this.tabButton[i]);
			this.tabButton[i].addActionListener(this);
		}
		 this.panelMenu.setVisible(true);
		 this.add(this.panelMenu);
		 
//		 this.btClients.addActionListener(this);
//		 this.btInters.addActionListener(this);
//		 this.btTechs.addActionListener(this);
//		 this.btQuitter.addActionListener(this);
		 //Ajout des 3 pannels
		 
		 this.setVisible(true);
	 }

//	@Override
//	public void actionPerformed(ActionEvent e) 
//	{
//		if (e.getSource()==this.tabButton[3])
//		{
//			this.dispose();
//			Main.rendreVisible(true);
//		}
//		else if (e.getSource()==this.tabButton[0])
//		{
//			uneVueClient.setVisible(true);
//			uneVueInters.setVisible(false);
//			uneVueTech.setVisible(false);
//		}
//		else if (e.getSource()==this.tabButton[1])
//		{
//			uneVueClient.setVisible(false);
//			uneVueInters.setVisible(false);
//			uneVueTech.setVisible(true);
//		}
//		else if (e.getSource()==this.tabButton[2])
//		{
//			uneVueClient.setVisible(false);
//			uneVueInters.setVisible(true);
//			uneVueTech.setVisible(false);
//		}
//
//	}

}
