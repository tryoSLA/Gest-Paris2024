package controleur;

import vue.VueConnexion;

public class Main {

	private static VueConnexion uneConnexion ; 
	
	public static void rendreVisible (boolean action)
	{
		Main.uneConnexion.setVisible(action); 
	}
	
	public static void main(String[] args) {
		Main.uneConnexion = new VueConnexion();
	}

}
