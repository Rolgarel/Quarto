import javax.swing.*;
import java.awt.Color;


public class FenetreFinJeu  extends JFrame{

	int etatFinJeu;
	Joueur [] joueurs;
	String s;
	
	// Constructeur 
	
	public FenetreFinJeu (int etatFin , Joueur[] j) {
		super ("Fin de la partie");
		setSize (500, 200);
		setLocation (200, 200);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		etatFinJeu = etatFin;
		joueurs = j;
		s = "";
		
		JPanel fond = new JPanel();
		fond.setLayout(null);
		fond.setBounds (0,0,500,200);
		fond.setBackground (Color.yellow);
		add(fond);
		
		
		switch (etatFinJeu) {
			case 1 :
				System.out.println (joueurs[etatFinJeu-1].nom + "a gagné.");
				s += joueurs[etatFinJeu-1].nom + " a gagné.";
				break;
			
			case 2 :
				System.out.println (joueurs[etatFinJeu-1].nom + "a gagné.");
				s += joueurs[etatFinJeu-1].nom + " a gagné.";
				break;
				
			case 0 :
				System.out.println ("Personne n'a gagné.");
				s += " Personne n'a gagné.";
				break;
		}
		
		JLabel message = new JLabel (s);
		message.setBounds (30, 20, 400, 50);
		fond.add(message);
		
		
		setVisible(true);
	}

}
