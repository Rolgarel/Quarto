import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.UIManager;

// STATUT : Tout est implémenté et utilisé

public class FenetreJeu extends JFrame implements ActionListener{
	
    // Taille des éléments graphiques
	private int tailleHaut = 150;
	private int taillePiece = 75;
	private int taillePlateau = 9*taillePiece;
	private int tailleBas = 100;
	private int hauteurFenetre = tailleHaut + tailleBas + taillePlateau;
	private int largeurFenetre = taillePlateau + (int)(6.5*taillePiece);
	// panel central + panneau bas : W=1162 et H=775
    
    // Eléments graphiques
    private JLabel affEtape;
    private JButton boutonConfirmer;
    private JButton boutonRegles;
    private JPanel panneauGlobal;
    private JPanel panneauHaut;
    
    /* Couleurs des éléments graphiques 
     * piece a : A32A8B
     * piece b : FFD036
     * wallpaper = case B : D1BCE0
     * case A : 94859E
     */
    
    
    // Images
    private Toolkit Tool = Toolkit.getDefaultToolkit();
    private Image wallpaper = Tool.getImage("wallpaper.png");
    private String[] imgPieces = new String[16];
    private String[] nomImgPieces; 
    
    private BufferedImage ImagePreparation;
    private Graphics ImagePreparationGraphics;
    
    
    // Variables de jeu
    private JeuQuarto jeu;
    private int etape = -1;
    private ClickPanel[] pieceA = new ClickPanel[16]; // pieces plateau
    private ClickPanel[] pieceB = new ClickPanel[16]; // pieces zone de selection laterale
    private int[] coordClick = new int[2];
    private int selectA = -1;
    private int selectB = -1;
    
    
	
  
	
	// Constructeur
	public FenetreJeu(boolean isHuman) {
		
		/* Instanciation des variables du jeu */
		jeu = new JeuQuarto(isHuman);
		
		/* Images pièces */
		nomImgPieces = initImgPieces(); 
	
		/* Pour gérer compatibilité affichage */
		try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace(); 
        }
		
		
		
		/* Création fenêtre de jeu */
		
		// general parameters
		this.setTitle("Partie");
		this.setSize(largeurFenetre,hauteurFenetre);
		this.setLocation(0,0);
        this.setLayout(null);
        this.setResizable(false);
        
        
		/* Close opetation :
		 * Une fenêtre popup s'ouvre pour confirmer l'arrêt du jeu.
		 * ATTENTION : vérifier que le bouton cancel reprend la partie qui était en cours
		 */
		
        addWindowListener(new WindowAdapter(){
				
				public void windowClosing(WindowEvent e){
					
					int result = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir quitter le jeu ?", "Pause", JOptionPane.OK_CANCEL_OPTION);
					
					if(result == JOptionPane.YES_OPTION){
						//System.out.println("You pressed Yes");
						setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
						FenetreMenu menu = new FenetreMenu();
						menu.setVisible(true);
						
					} else { // bouton Cancel
						//System.out.println("You pressed Cancel");
						int etapeCourant = etape;
						ClickPanel[] pieceACourant = pieceA;
						ClickPanel[] pieceBCourant = pieceB;
						JeuQuarto jeuCourant = jeu;
						
						FenetreJeu fenetreJeu = new FenetreJeu(isHuman);
						fenetreJeu.setVisible(true);
						
						fenetreJeu.etape = etapeCourant;
						fenetreJeu.updateEtape();
						fenetreJeu.pieceA = pieceACourant;
						fenetreJeu.pieceB = pieceBCourant;
						fenetreJeu.jeu = jeuCourant;
						//System.out.println(fenetreJeu.etape);
					}
					
				}
			}
		);
		
		ImagePreparation = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
        ImagePreparationGraphics = ImagePreparation.getGraphics();
        
       
		/* Panneaux et leurs composants*/
		
		//Zone de pieces plateau
		for (int i = 0; i < 16; i++) {
			int x = (4+i-5*(i/4))*taillePiece;
			int y = tailleHaut+(1+i-3*(i/4))*taillePiece;
            this.pieceA[i] = new ClickPanel(x, y, taillePiece);
            this.pieceA[i].setImage("fondClickPanelA");
		}
        
		//Panneau haut
        panneauHaut = new JPanel();
		panneauHaut.setBounds(0,0,largeurFenetre,tailleHaut);
		panneauHaut.setLayout(null);
        panneauHaut.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
	
		
		// Bouton règles
		boutonRegles = new JButton("Regles");
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
		updateEtape();
		panneauEtape.add(affEtape);
		panneauHaut.add(panneauEtape);
        
		
		//Panneau bas
        JPanel panneauBas = new JPanel();
		panneauBas.setBounds(0,hauteurFenetre - tailleBas,largeurFenetre,tailleBas);
		panneauBas.setLayout(null);
        panneauBas.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		
		
		//Panneau lateral
        JPanel panneauLat = new JPanel();
		panneauLat.setBounds(taillePlateau,tailleHaut,largeurFenetre - taillePlateau,taillePlateau);
		panneauLat.setLayout(null);
        panneauLat.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		
		
		//Zone de pieces laterales
		for (int i = 0; i < 16; i++) {
			int x = taillePlateau + (taillePiece/2)+(int)(1.5*taillePiece*(i%4));
			int y = tailleHaut+taillePiece+2*taillePiece*(i/4);
			this.pieceB[i] = new ClickPanel(x, y,taillePiece);
			this.pieceB[i].setImage("fondClickPanelB", nomImgPieces[i]);
		}
		

		// Panneau global
        panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,largeurFenetre,hauteurFenetre);
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(Color.black);
		
		panneauGlobal.addMouseListener(new MouseAdapter() {
            
			@Override // MAJ de l'étape après appui sur le bouton confirmer
			public void mousePressed(MouseEvent e) {
					
				System.out.println(e.getX() + "," + e.getY());
				coordClick[0] = e.getX();
				coordClick[1] = e.getY();
				
				int tour = etape%4;
				switch(tour){
					case 0 :
						selectB = getClickedZoneB(coordClick[0], coordClick[1]);
						break;
					case 1 :
						selectA = getClickedZoneA(coordClick[0], coordClick[1]);
						break;
					case 2 : 
						selectB = getClickedZoneB(coordClick[0], coordClick[1]);
						break;
					case 3 :
						selectA = getClickedZoneA(coordClick[0], coordClick[1]);
						break;
				}
				repaint();
			}
		});
		
		panneauGlobal.add(panneauHaut);
		panneauGlobal.add(panneauBas);
		panneauGlobal.add(panneauLat);
		
		this.add(panneauGlobal);
        
        this.setVisible(true);
        
	}
	
	
	
	// Méthodes
	
	/* initImgPieces()
	 * Renvoie un tableau des noms des images des pièces dans l'ordre dans lequel elles sont créées
	 * @return : String[]
	 */
	public String[] initImgPieces(){
		String[] imgPieces = new String[16];
		
		imgPieces[0] = "grac";
        imgPieces[1] = "grbc";
        imgPieces[2] = "grap";
        imgPieces[3] = "grbp";
        
        imgPieces[4] = "gcac";
        imgPieces[5] = "gcbc";
        imgPieces[6] = "gcap";
        imgPieces[7] = "gcbp";
        
        imgPieces[8] = "prac";
        imgPieces[9] = "prbc";
        imgPieces[10] = "prap";
        imgPieces[11] = "prbp";
        
        imgPieces[12] = "pcac";
        imgPieces[13] = "pcbc";
        imgPieces[14] = "pcap";
        imgPieces[15] = "pcbp";
		
		return imgPieces;
	}	
	
	
	/* actionPerformed(ActionEvent e)
	 * permet de gérer les évènements de clIC sur les boutons "Confiemer" et "Règles"
	 * si "Règles" appuyé, affichage des règles dans une fenêtre extérieure
	 * si "Confirmer" appuyé, fin de l'étape du jeu en cours et lancement de la suivante
	 */
	public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == this.boutonRegles) {
            //System.out.println("règles");
			FenetreRegles regles = new FenetreRegles();
            
        } else if (e.getSource() == this.boutonConfirmer) {
			System.out.println("confirmer : etape " + this.etape);
			if(this.etape == -1) {
				this.setEtape(this.etape + 1);
			} else {
				this.repaint();  // MAJ de l'affichage
				deroulementTour(this.etape); // Jeu
			}
            
        }
        
    }

	/* deroulementTour(int etape)
	 * gère l'action du joueur et vérifie l'état du jeu
	 * @param : int etape = numéro de l'étape en cours
	 */
	public void deroulementTour(int etape){
		
		int tour = etape%4;	
		//
		if(tour == 0 || tour == 2){  // alors l'action = un choix de pièce
	
			int indiceJoueur = (tour==0)? 0 : 1;
			int casePieceChoisie = this.selectB;
			jeu.getJoueurs()[indiceJoueur].setPieceChoisie(jeu.getPlateau().getListePieces()[casePieceChoisie]);
			
			if((jeu.getJoueurs()[indiceJoueur].getPieceChoisie().isNull() || jeu.getJoueurs()[indiceJoueur].getPieceChoisie().estPlace())){
				affEtape.setText("Vous n'avez pas choisi de piece ou la piece est deja placee. Recommencez.");
			} else {
				setEtape(this.etape + 1);
			}
			
		} else { // alors l'action = un placement de pièce
			int indiceJoueurChoix = (tour==1)? 0 : 1;
			int numCase = selectA;
			if(jeu.getPlateau().getGrille()[numCase].isNull()){
				placerPiece(jeu.getJoueurs()[indiceJoueurChoix].getPieceChoisie(), numCase, indiceJoueurChoix);
				
				if(jeu.isOver(etape)){
					System.out.println("Etat fin de jeu : " + jeu.getEtatFinJeu());
					FenetreFinJeu finJeu = new FenetreFinJeu(jeu.getEtatFinJeu(), jeu.getJoueurs());
				} else {
					setEtape(this.etape + 1);
				}
				
			} else {
				affEtape.setText("La case choisie est deja occupee. Recommencez.");
			}

			
		}
			
	}
	
    
    /* placerPiece()
     * Place une pièce dans la grille du plateau : 
     * MAJ de la grille et de la piece dans la liste de pieces
     * @param : 
     * 		piece = Piece choisie par le joueur précédent
     * 		numCase = int numéro de la case choisie par le joueur
     * 		numJoueur = int indice du joueur précedent
     */ 
    public void placerPiece(Piece piece, int numCase, int numJoueur){
		
		this.jeu.getPlateau().getGrille()[numCase] = piece;
		this.jeu.getJoueurs()[(numJoueur==0)? 1 : 0].setCaseChoisie(-1);
		piece.setPlace(true);
		this.jeu.getJoueurs()[numJoueur].setPieceChoisie(new Piece());
		
		this.pieceA[numCase].setImage("fondClickPanelA", piece.toString());
		this.pieceB[piece.getPlace()].setImage("fondCLickPanelB");
		
	}
	
    
    
    /* setEtape(int e)
     * MAJ de l'étape
     */ 
    public void setEtape(int e) {
        this.etape = e;
        this.updateEtape();
    }
    
    /* uptageEtape()
     * MAJ de l'affichage de l'étape courante du jeu
     */
    public void updateEtape() {
		
        String s = "Tour du joueur ";
        if (etape < 0) {
            s = "La partie va commencer  : appuyez sur le bouton Confirmer";
        } else if ((etape%4) == 0) {
            s = s + "1 : Veuillez sélectionner une piece pour le " + jeu.getJoueurs()[1].getNom();
            selectA = -1;
            selectB = -1;
        } else if ((etape%4) == 1) {
            s = s + "2 : Veuillez positionner la piece";
        } else if ((etape%4) == 2) {
            s = s + "2 : Veuillez sélectionner une piece pour le " + jeu.getJoueurs()[0].getNom();
            selectA = -1;
            selectB = -1;
        } else if ((etape%4) == 3) {
            s = s + "1 : Veuillez positionner la piece";
        }
        affEtape.setText(s);
        
    }
    
    /* paint(Graphics g)
     * MAJ de la fenetre de jeu
     */ 
   public void paint(Graphics g){
    
    panneauHaut.repaint(); //correspond à la zone de commande
    
    this.requestFocusInWindow();
    
    if (pieceB[15] != null && wallpaper != null) {
        
        ImagePreparationGraphics.drawImage(wallpaper,0,tailleHaut,this);
        
        for (int i = 0; i < pieceA.length; i++) {
            if (pieceA[i].image != null) {
                ImagePreparationGraphics.drawImage(pieceA[i].image, pieceA[i].getPositionX(), pieceA[i].getPositionY(),this);
            }
        }
        for (int i = 0; i < pieceB.length; i++) {
            if (pieceB[i].image != null) {
                ImagePreparationGraphics.drawImage(pieceB[i].image, pieceB[i].getPositionX(), pieceB[i].getPositionY(),this);
            }
        }
        if (etape > -1) {
			ImagePreparationGraphics.setColor(Color.green);
			if (selectB > -1) {
				ImagePreparationGraphics.drawRect( pieceB[selectB].getPositionX(), pieceB[selectB].getPositionY(), taillePiece, taillePiece);
			}
			if (selectA > -1) {
				ImagePreparationGraphics.drawRect(pieceA[selectA].getPositionX(), pieceA[selectA].getPositionY(), taillePiece, taillePiece);
			}
		}
			g.drawImage(ImagePreparation,2,35,this);
        }
    }
    
  
	//retourne l'indice de la zone sélectionnée si elle appartient au plateau sinon retourne -1
	public int getClickedZoneA (int x, int y) {
		int res = -1;
		for (int i = 0; i < 16; i++) {
			if (pieceA[i].isIn(x, y)) {
				res = i;
			}
		}
		return res;
	}
	
	//retourne l'indice de la zone sélectionnée si elle appartient à la zone laterale sinon retourne -1
	public int getClickedZoneB (int x, int y) {
		int res = -1;
		for (int i = 0; i < 16; i++) {
			if (pieceB[i].isIn(x, y)) {
				res = i;
			}
		}
		return res;
	}
	
	
}
    
	

