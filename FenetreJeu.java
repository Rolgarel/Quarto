import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.UIManager;



public class FenetreJeu extends JFrame implements ActionListener{
	
    // Taille des éléments graphiques
	int tailleHaut = 150;
	int taillePiece = 75;
	int taillePlateau = 9*taillePiece;
	int tailleBas = 100;
	int hauteurFenetre = tailleHaut + tailleBas + taillePlateau;
	int largeurFenetre = taillePlateau + (int)(6.5*taillePiece);
    
    // Eléments graphiques
    JLabel affEtape;
    JButton boutonConfirmer;
    JButton boutonRegles;
    JPanel panneauGlobal;
    JPanel panneauHaut;
    
    
    // Images
    Toolkit Tool = Toolkit.getDefaultToolkit();
    Image wallpaper = Tool.getImage("space.png");
    String[] imgPieces = new String[16]; 
    
    private BufferedImage ImagePreparation;
    private Graphics ImagePreparationGraphics;
    
    
    // Variables de jeu
    JeuQuarto jeu;
    int etape = -1;
    ClickPanel[] pieceA = new ClickPanel[16]; // pieces plateau
    ClickPanel[] pieceB = new ClickPanel[16]; // pieces zone de selection laterale
    

  
	
	// Constructeur
	public FenetreJeu(boolean isHuman) {
		
		/* Instanciation des variables du jeu */
		jeu = new JeuQuarto(isHuman);
		
		/*
        imgPieces[0] = "grac.png";
        imgPieces[1] = "grbc.png";
        imgPieces[2] = "grap.png";
        imgPieces[3] = "grbp.png";
        
        imgPieces[4] = "gcac.png";
        imgPieces[5] = "gcbc.png";
        imgPieces[6] = "gcap.png";
        imgPieces[7] = "gcbp.png";
        
        imgPieces[8] = "prac.png";
        imgPieces[9] = "prbc.png";
        imgPieces[10] = "prap.png";
        imgPieces[11] = "prbp.png";
        
        imgPieces[12] = "pcac.png";
        imgPieces[13] = "pcbc.png";
        imgPieces[14] = "pcap.png";
        imgPieces[15] = "pcbp.png";
        */
		
	
		/* Pour gérer compatibilité affichage */
		try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace(); 
        }
		
		
		
		/* Création fenêtre de jeu */
		
		// general parameters
		setTitle("Partie");
		setSize(largeurFenetre,hauteurFenetre);
		setLocation(0,0);
        setLayout(null);
        this.setResizable(false);
        
        
		// Close opetation
        addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					FenetreMenu menu = new FenetreMenu();
					menu.setVisible(true);
					setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
				}
			}
		);
		
		ImagePreparation = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
        ImagePreparationGraphics = ImagePreparation.getGraphics();
        
       
		/* Panneaux et leurs composants*/
		
		//Panneau plateau
        JPanel panneauPlateau = new JPanel();
		panneauPlateau.setBounds(0,tailleHaut,taillePlateau,taillePlateau);
		panneauPlateau.setLayout(null);
        //panneauPlateau.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		panneauPlateau.setBackground(Color.blue); //couleur pour mettre en évidence le JPanel
		
		//Zone de pieces plateau
		for (int i = 0; i < 16; i++) {
			int x = (4+i-5*(i/4))*taillePiece;
			int y = (1+i-3*(i/4))*taillePiece;
            pieceA[i] = new ClickPanel(x, y, taillePiece);
            pieceA[i].setImage("test3");
            
		}
		panneauPlateau.addMouseListener(new MouseAdapter() {
            @Override 
            public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
            }
        });
        
		//Panneau haut
        panneauHaut = new JPanel();
		panneauHaut.setBounds(0,0,largeurFenetre,tailleHaut);
		panneauHaut.setLayout(null);
        panneauHaut.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		//panneauHaut.setBackground(Color.darkGray); //couleur pour mettre en évidence le JPanel
		
		// Bouton règles
		boutonRegles = new JButton("Règles");
		boutonRegles.setBounds(largeurFenetre - 10 - 150,10,150,40);
        boutonRegles.addActionListener(this);
        panneauHaut.add(boutonRegles);
		
		// Bouton confirmer
		boutonConfirmer = new JButton("Confirmer");
		boutonConfirmer.setBounds(largeurFenetre - 10 - 150,tailleHaut - 10 - 40,150,40);
        boutonConfirmer.addActionListener(this);
        panneauHaut.add(boutonConfirmer);
		
		// Zone de texte d'instruction
        JPanel panneauEtape = new JPanel();
		panneauEtape.setBounds(15,tailleHaut -30 -10,520,30);
		panneauEtape.setLayout(null);
		panneauEtape.setBackground(Color.white); //couleur de font du text
        
        affEtape = new JLabel();
		affEtape.setBounds(10,0,520,20);
		this.updateEtape();
		panneauEtape.add(affEtape);
		panneauHaut.add(panneauEtape);
        
		
		//Panneau bas
        JPanel panneauBas = new JPanel();
		panneauBas.setBounds(0,hauteurFenetre - tailleBas,largeurFenetre,tailleBas);
		panneauBas.setLayout(null);
        panneauBas.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		//panneauBas.setBackground(Color.darkGray); //couleur pour mettre en évidence le JPanel
		
		//Panneau lateral
        JPanel panneauLat = new JPanel();
		panneauLat.setBounds(taillePlateau,tailleHaut,largeurFenetre - taillePlateau,taillePlateau);
		panneauLat.setLayout(null);
        panneauLat.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		//panneauLat.setBackground(Color.gray); //couleur pour mettre en évidence le JPanel
		
		// Zone de pieces laterales
		for (int i = 0; i < 16; i++) {
			int x = (taillePiece/2)+(int)(1.5*taillePiece*(i%4));
			int y = taillePiece+2*taillePiece*(i/4);
			pieceB[i] = new ClickPanel(x, y, taillePiece);
			pieceB[i].setImage("test2");
            //pieceB[i].setImage(imgPieces[i]);
            //pieceB[i].setImage(jeu.plateau.listePieces[i].codeImage);
		}
		
		
        
		// Panneau global
        panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,largeurFenetre,hauteurFenetre);
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(Color.black);
		panneauGlobal.add(panneauPlateau);
		panneauGlobal.add(panneauHaut);
		panneauGlobal.add(panneauBas);
		panneauGlobal.add(panneauLat);
		
		this.add(panneauGlobal);
        
        setVisible(true);
        
	}
	
	
	/* actionPerformed(ActionEvent e)
	 * permet de gérer les évènements de clique sur les boutons "Confiemer" et "Règles"
	 * si "Règles" appuyé, affichage des règles dans une fenêtre extérieure
	 * si "Confirmer" appuyé, fin de l'étape du jeu en cours et lancement de la suivante
	 */
	public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == boutonRegles) {
            //System.out.println("règles");
           
            /*pieceB[4].isSelected = !pieceB[4].isSelected;
            for (int i = 0; i < pieceA.length; i++) {
                pieceA[i].setImage("test2");
            }*/
            
            FenetreRegles regles = new FenetreRegles();
            
        } else if (e.getSource() == boutonConfirmer) {
            //System.out.println("confirmer");
            
            // MAJ de l'affichage
            this.repaint();
            
            // Jeu
            jeu.actionJoueur(etape);
            
            if(jeu.isOver(etape)){
				int etatFinJeu = jeu.getEtatFinJeu();
				FenetreFinJeu finJeu = new FenetreFinJeu(etatFinJeu);
				this.setVisible(false);
			}
			
        }
        
    }

    
    
    
    
    public void setEtape (int e) {
        this.etape = e;
        this.updateEtape();
    }
    
    /* uptageEtape()
     * MAJ de l'étape courante du jeu
     */
    public void updateEtape () {
		
        String s = "Tour du joueur ";
        if (etape < 0) {
            s = "Erreur";
        } else if ((etape%4) == 0) {
            s = s + "1 : Veuillez sélectionner une pièce pour le " + jeu.joueurs[1].nom;
        } else if ((etape%4) == 1) {
            s = s + "2 : " + jeu.joueurs[1].nom + ",veuillez positionner la pièce";
        } else if ((etape%4) == 2) {
            s = s + "2 : Veuillez sélectionner une pièce pour le " + jeu.joueurs[0].nom;
        } else if ((etape%4) == 3) {
            s = s + "1 : " + jeu.joueurs[0].nom + ", veuillez positionner la pièce";
        }
        affEtape.setText(s);
        
    }
    
    
    
    
    
    
    public void paint(Graphics g){
    
    panneauHaut.repaint(); //doit etre zone de commande
    
    this.requestFocusInWindow();
    
    if (pieceB[15] != null) {
        
        ImagePreparationGraphics.drawImage(wallpaper,0,0,this);
        
        for (int i = 0; i < pieceA.length; i++) {
            ImagePreparationGraphics.setColor(Color.gray);
            ImagePreparationGraphics.fillRect(pieceA[i].positionX,tailleHaut + pieceA[i].positionY,taillePiece, taillePiece);
            if (pieceA[i].image != null) {
                ImagePreparationGraphics.drawImage(pieceA[i].image,pieceA[i].positionX,tailleHaut + pieceA[i].positionY,this);
            }
            if (pieceA[i].isSelected == true) {
                ImagePreparationGraphics.setColor(Color.green);
                ImagePreparationGraphics.drawRect(pieceA[i].positionX,tailleHaut + pieceA[i].positionY,taillePiece, taillePiece);
            }
        }
        for (int i = 0; i < pieceB.length; i++) {
            ImagePreparationGraphics.setColor(Color.gray);
            ImagePreparationGraphics.fillRect(taillePlateau + pieceB[i].positionX, tailleHaut + pieceB[i].positionY, taillePiece, taillePiece);
            if (pieceB[i].image != null) {
                ImagePreparationGraphics.drawImage(pieceB[i].image,taillePlateau + pieceB[i].positionX, tailleHaut + pieceB[i].positionY,this);
            }
            if (pieceB[i].isSelected == true) {
                ImagePreparationGraphics.setColor(Color.green);
                ImagePreparationGraphics.drawRect(taillePlateau + pieceB[i].positionX, tailleHaut + pieceB[i].positionY, taillePiece, taillePiece);
            }
        }
        
        //ImagePreparationGraphics.drawImage(Explosion,50,50,this);
        
        g.drawImage(ImagePreparation,0,0,this);
        }
    }
    
    /** getPieceChoisie()
     * Attend un click du joueur sur une piece et renvoie la pièce correspondante
     * @return : piece choisie par le joueur
     */
    public static Piece getPieceChoisie(){
		return null;
	}
    
    /**isBoutonConfirme()
	 * check si le bouton 
	 */
	public static boolean isBoutonConfirme(){
		return false;
	} 
  
    
    
    /* Le jeu est lancée depuis la fenêtre de jeu :
     * Il commence par l'instanciation du menu
     */
    public static void main(String[] args){
		FenetreMenu menu = new FenetreMenu();
	}
    
	
}
