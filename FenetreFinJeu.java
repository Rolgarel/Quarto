import javax.swing.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
		
		/* Close opetation :
		 * Une fenêtre popup s'ouvre pour confirmer l'arrêt du jeu.
		 * ATTENTION : vérifier que le bouton cancel reprend la partie qui était en cours
		 */
		
        addWindowListener(new WindowAdapter(){
				
				public void windowClosing(WindowEvent e){
					
					int result = JOptionPane.showConfirmDialog(null, "Vous allez revenir au menu", "Information", JOptionPane.OK_CANCEL_OPTION);
					
					if(result == JOptionPane.OK_OPTION){
						System.out.println("You pressed OK");
						setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
						FenetreMenu menu = new FenetreMenu();
						menu.setVisible(true);	
					}
					
				}
			}
		);
		
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
