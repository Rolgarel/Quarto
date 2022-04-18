import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

// STATUT : FenetreRegles finie	

public class FenetreRegles extends JFrame {
	//Attributs
	private JPanel fond;
	private JLabel titre;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	private JLabel l7;
	private JLabel l8;
	private JLabel l9;
	private JLabel l10;
	private JLabel l11;
	private JLabel l12;	
	private JLabel l13;	
	private JLabel l14;	
	private JLabel l15;
		
	
		
	//Constructeur
	public FenetreRegles (){
		super ("Regles du jeu");
		setSize (500, 500);
		setLocation (100, 200);
		//setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		fond = new JPanel ();
		fond.setLayout(null);
		fond.setBounds (0,0,500,500);
		fond.setBackground (new Color(197,196,174));
		add(fond);
		
		Font policeTitre = new Font ("Broadway",Font.BOLD,18);
		Font policeText = new Font ("Arial",Font.ITALIC,12);
		
		titre = new JLabel ("-------------Le Quarto-----------");
		titre.setBounds (100,20,450,20);
		titre.setFont(policeTitre);
		fond.add(titre);
		
		l1 = new JLabel ("2 joueurs, 16 cases, 16 pièces");
		l1.setBounds (160,60,450,15);
		l1.setFont (policeText);
		fond.add(l1);
		
		l2 = new JLabel ("  Des rouges et des vertes");
		l2.setBounds (170,80,450,15);
		l2.setFont (policeText);
		fond.add(l2);
		
		l3 = new JLabel ("Des grandes et des petites");
		l3.setBounds (170,95,450,15);
		l3.setFont (policeText);
		fond.add(l3);
		
		l4 = new JLabel (" Des rondes et des carrées");
		l4.setBounds (170,110,450,15);
		l4.setFont (policeText);
		fond.add(l4);
		
		l5 = new JLabel ("Des creuses et des pleines");
		l5.setBounds (170,125,450,15);
		l5.setFont (policeText);
		fond.add(l5);
		
		
		l6 = new JLabel ("Déroulement de la partie:");
		l6.setBounds (20,170,450,15);
		l6.setFont (policeText);
		fond.add(l6);
		
		l7 = new JLabel ("- Bob désigne une pièce qui n'est pas encore placée pour Yvette");
		l7.setBounds (20,185,450,15);
		l7.setFont (policeText);
		fond.add(l7);
		
		l8 = new JLabel ("- Yvette place cette pièce sur une case vide du plateau");
		l8.setBounds (20,200,450,15);
		l8.setFont (policeText);
		fond.add(l8);
		
		l9 = new JLabel ("- Yvette choisit une pièce qui n'est pas encore placée pour Bob");
		l9.setBounds (20,215,450,15);
		l9.setFont (policeText);
		fond.add(l9);
		
		l10 = new JLabel ("- Bob place cette pièce sur une case vide du plateau");
		l10.setBounds (20,230,450,15);
		l10.setFont (policeText);
		fond.add(l10);
		
		l11 = new JLabel (" -etc... ");
		l11.setBounds (20,245,450,15);
		l11.setFont (policeText);
		fond.add(l11);
		
		l12 = new JLabel ("Pour gagner, il suffit d'être la personne à placer la quatrième pièce");
		l12.setBounds (20,300,450,15);
		l12.setFont (policeText);
		fond.add(l12);
		
		l13 = new JLabel ("d'une ligne, colonne ou diagoanle, où les quatre pièces ont au moins une");
		l13.setBounds (20,315,450,15);
		l13.setFont (policeText);
		fond.add(l13);
		
		l14 = new JLabel ("des caractéristiques citées plus haut en commun (par exemple 4 pièces rondes).");
		l14.setBounds (20,330,450,15);
		l14.setFont (policeText);
		fond.add(l14);
		
		l15 = new JLabel ("Bonne partie !");
		l15.setBounds (20,400,450,15);
		l15.setFont (policeText);
		fond.add(l15);
		
		setVisible (true);
	}
	
}
