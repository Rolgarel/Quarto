import javax.swing.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.nio.charset.StandardCharsets;

/* STATUT : 
 * FenetreFinJeu encore en construction
 * tout ce qui doit être privé l'est
 */

public class FenetreFinJeu extends JFrame {

	private int etatFinJeu;
	private Joueur [] joueurs;
	private String s;
	
	
	// Constructeur 
	
	public FenetreFinJeu (int etatFin , Joueur[] j){
		super ("Fin de la partie");
		
		// Variables dimensions
		int mainHeight = 500;
		int mainWidth = 2*mainHeight;
		int mainX = 100;
		int mainY = 200;
		
		// Configuration de la fenêtre principale
		this.setSize (mainWidth, mainHeight);
		this.setLocation (mainX, mainY);
		this.setLayout(null);
		
		
		
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
		
		
		
		this.etatFinJeu = etatFin;
		this.joueurs = j;
		this.s = "";
		
		JPanel fond = new JPanel();
		fond.setLayout(null);
		fond.setBounds (0,0, mainWidth, mainHeight);
		add(fond);
		
		String text = "";
		byte[] textBytes = null;
		
		if(etatFinJeu==0){
			text = "Personne n'a gagné.";
			textBytes = text.getBytes();
			s += new String(textBytes, StandardCharsets.UTF_8);
		} else { // 1 ou 2
			text = joueurs[etatFinJeu-1].getNom() + " a gagné.";
			textBytes = text.getBytes();
			s += new String(textBytes, StandardCharsets.UTF_8);
		}
		System.out.println(s);
		
		
		this.setContentPane(new JLabel(new ImageIcon("screenFinJeu.png")));
		setLayout(new FlowLayout());
		
		JLabel msg = new JLabel(s, (int) JLabel.CENTER_ALIGNMENT);
		msg.setFont(new Font("Dialog", Font.BOLD, 50));
		msg.setPreferredSize(new Dimension(1000, 600));
		msg.setForeground(Color.white);
		add(msg);
		this.pack();
		
		setVisible(true);
	}
	
	/* Main
	 * A exécuter pour tester la fenêtre de fin de jeu
	 */
	public static void main(String[] args){
		Joueur[] joueurs = new Joueur[2];
		joueurs[0] = new Joueur("Joueur 1");
		joueurs[1] = new Joueur("Joueur 2");
		FenetreFinJeu gameOver = new FenetreFinJeu(2, joueurs);
	}

}
