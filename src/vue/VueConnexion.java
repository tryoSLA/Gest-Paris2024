package vue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;

import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener,KeyListener
{
	private JPanel unPanel = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btSeConnecter = new JButton("Se Connecter"); 
	private JTextField txtLogin = new JTextField(); 
	private JPasswordField pwdMdp = new JPasswordField();	
	
	
	public VueConnexion()
	{

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)(dimension.getHeight()/2 - 200);
		int width  = (int)(dimension.getWidth()/2 - 250);

		this.setTitle("Gest'Paris2024 - Connexion");
		this.setBounds(width, height, 500, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




		//construction du pannel 
		this.unPanel.setBounds(50, 200, 400, 120);
		this.unPanel.setLayout(new GridLayout(5,2));
		this.unPanel.add(new JLabel("Nom d'utilisateur : "));
		this.unPanel.add(this.txtLogin);
		this.unPanel.add(new JLabel(""));
		this.unPanel.add(new JLabel(""));
		this.unPanel.add(new JLabel("Mot de passe : "));
		this.unPanel.add(this.pwdMdp);
		this.unPanel.add(new JLabel(""));
		this.unPanel.add(new JLabel(""));
		this.unPanel.add(this.btAnnuler); 
		this.unPanel.add(this.btSeConnecter);
		
		this.unPanel.setVisible(true);
		this.add(this.unPanel);

		//Modification des boutons
		this.txtLogin.setBackground(null);
		this.txtLogin.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));
		this.pwdMdp.setBackground(null);
		this.pwdMdp.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));

		
				
		//ajout de l'image � la fenetre 		
		ImageIcon logo = new ImageIcon("src/images/JO.png");
		JLabel lbLogo = new JLabel(logo);
		lbLogo.setBounds(50, 20, 400, 120);
		this.add(lbLogo);
		
		//changer icone application 
		ImageIcon logopetit = new ImageIcon("src/images/Logo_paris_2024_simple.png");
		this.setIconImage(logopetit.getImage());
		
		//rendre les boutons cliquables 
		this.btAnnuler.addActionListener(this); 
		this.btSeConnecter.addActionListener(this);
		
		//rendre la touche entree ecoutable 
		
		this.txtLogin.addKeyListener(this);
		this.pwdMdp.addKeyListener(this);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 switch (e.getActionCommand())
		 {
		 case "Annuler" : 
			 this.txtLogin.setText("");
			 this.pwdMdp.setText("");
			 break; 
		 case "Se Connecter" : 
			 traitement();
			 break; 
		 }
		
	}
	
	private void traitement ()
	{
		 String login = this.txtLogin.getText(); 
		 String mdp = new String(this.pwdMdp.getPassword());
		 if (login.equals("") || mdp.equals(""))
		 {
			 JOptionPane.showMessageDialog(this,"Veuillez remplir les identifiants !");
		 }else {
		 
				 String droits = Modele.verifConnexion(login, mdp); 
				 if (droits.equals("")){
					 JOptionPane.showMessageDialog(this, "Erreur d'identifiants",
							 "Erreur", JOptionPane.ERROR_MESSAGE);
					 this.txtLogin.setText("");
					 this.pwdMdp.setText("");
				 }else {

					 //appel de la Jframe generale 
					 Main.rendreVisible(false);
					 new VueGeneral(droits);
				 }
		 }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			traitement();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


