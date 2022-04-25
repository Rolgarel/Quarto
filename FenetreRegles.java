import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.nio.charset.StandardCharsets;


public class FenetreRegles extends JFrame {
	
	//Constructeur :
	public FenetreRegles (){
		super ("Regles du jeu");
		this.setSize (500, 500);
		this.setLocation (100, 200);
		this.setLayout(null);
		
		JPanel fond = new JPanel ();
		fond.setLayout(null);
		fond.setBounds (0,0,500,500);
		fond.setBackground (new Color(197,196,174));
		this.add(fond);
		
		Font policeTitre = new Font ("Broadway",Font.BOLD,18);
		Font policeText = new Font ("Arial",Font.ITALIC,12);
		
		JLabel titre = new JLabel ("-------------Le Quarto-----------");
		titre.setBounds (100,20,450,20);
		titre.setFont(policeTitre);
		fond.add(titre);
		
		JLabel l1 = new JLabel (Main.encodeUTF8("2 joueurs, 16 cases, 16 pièces"));
		l1.setBounds (160,60,450,15);
		l1.setFont (policeText);
		fond.add(l1);
		
		JLabel l2 = new JLabel ("  Des rouges et des vertes");
		l2.setBounds (170,80,450,15);
		l2.setFont (policeText);
		fond.add(l2);
		
		JLabel l3 = new JLabel ("Des grandes et des petites");
		l3.setBounds (170,95,450,15);
		l3.setFont (policeText);
		fond.add(l3);
		
		JLabel l4 = new JLabel (Main.encodeUTF8(" Des rondes et des carrées"));
		l4.setBounds (170,110,450,15);
		l4.setFont (policeText);
		fond.add(l4);
		
		JLabel l5 = new JLabel ("Des creuses et des pleines");
		l5.setBounds (170,125,450,15);
		l5.setFont (policeText);
		fond.add(l5);
		
		
		JLabel l6 = new JLabel (Main.encodeUTF8("Déroulement de la partie:"));
		l6.setBounds (20,170,450,15);
		l6.setFont (policeText);
		fond.add(l6);
		
		JLabel l7 = new JLabel (Main.encodeUTF8("- Bob désigne une pièce qui n'est pas encore placée pour Yvette"));
		l7.setBounds (20,185,450,15);
		l7.setFont (policeText);
		fond.add(l7);
		
		JLabel l8 = new JLabel (Main.encodeUTF8("- Yvette place cette pièce sur une case vide du plateau"));
		l8.setBounds (20,200,450,15);
		l8.setFont (policeText);
		fond.add(l8);
		
		JLabel l9 = new JLabel (Main.encodeUTF8("- Yvette choisit une pièce qui n'est pas encore placée pour Bob"));
		l9.setBounds (20,215,450,15);
		l9.setFont (policeText);
		fond.add(l9);
		
		JLabel l10 = new JLabel (Main.encodeUTF8("- Bob place cette pièce sur une case vide du plateau"));
		l10.setBounds (20,230,450,15);
		l10.setFont (policeText);
		fond.add(l10);
		
		JLabel l11 = new JLabel (" -etc... ");
		l11.setBounds (20,245,450,15);
		l11.setFont (policeText);
		fond.add(l11);
		
		JLabel l12 = new JLabel (Main.encodeUTF8("Pour gagner, il suffit d'être la personne à placer la quatrième pièce"));
		l12.setBounds (20,300,450,15);
		l12.setFont (policeText);
		fond.add(l12);
		
		JLabel l13 = new JLabel (Main.encodeUTF8("d'une ligne, colonne ou diagoanle, où les quatre pièces ont au moins une"));
		l13.setBounds (20,315,450,15);
		l13.setFont (policeText);
		fond.add(l13);
		
		JLabel l14 = new JLabel (Main.encodeUTF8("des caractéristiques citées plus haut en commun (par exemple 4 pièces rondes)."));
		l14.setBounds (20,330,450,15);
		l14.setFont (policeText);
		fond.add(l14);
		
		JLabel l15 = new JLabel ("Bonne partie !");
		l15.setBounds (20,400,450,15);
		l15.setFont (policeText);
		fond.add(l15);
		
		setVisible (true);
	}
	
	
	
}
