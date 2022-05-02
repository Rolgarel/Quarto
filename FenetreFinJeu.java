import javax.swing.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.nio.charset.StandardCharsets;


public class FenetreFinJeu extends JFrame {

	private int etatFinJeu;
	private Joueur [] joueurs;
	private String s;
    private FenetreJeu fenetreJeu;
	
	
	// Constructeur 
	
	public FenetreFinJeu (int etatFin , Joueur[] j, FenetreJeu fJ){
		super ("Fin de la partie");
        
        this.fenetreJeu = fJ;
		
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
						//System.out.println("You pressed OK");
						setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        fenetreJeu.dispose();
						FenetreMenu menu = new FenetreMenu();
						menu.setVisible(true);	
					} else {
                        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                        fenetreJeu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
		
		
		this.setContentPane(new JLabel(new ImageIcon("img_fond/screenFinJeu.png")));
		setLayout(new FlowLayout());
		
		JLabel msg = new JLabel(s, (int) JLabel.CENTER_ALIGNMENT);
		msg.setFont(new Font("Dialog", Font.BOLD, 50));
		msg.setPreferredSize(new Dimension(1000, 600));
		msg.setForeground(Color.white);
		add(msg);
		this.pack();
		
		setVisible(true);
	}
	
	
}
