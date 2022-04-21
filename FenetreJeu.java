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
	
    
    // Eléments graphiques
    private JLabel affEtape;
    private JButton boutonConfirmer;
    private JButton boutonRegles;
    private JPanel panneauHaut;
   
    private BufferedImage ImagePreparation;
    private Graphics ImagePreparationGraphics;
    
    
    // Variables de jeu
    private JeuQuarto jeu;
    private int etape = -1;
    private ClickPanel[] casesPlateau = new ClickPanel[16]; // pieces plateau
    private ClickPanel[] casesInventaire = new ClickPanel[16]; // pieces zone de selection laterale
    private int[] coordClick = new int[2];
    private int selectDansPlateau = -1;
    private int selectDansInventaire = -1;
    
    
	
  
	
	// Constructeur :
	public FenetreJeu(boolean isHuman) {
		
		/* Instanciation des variables du jeu */
		jeu = new JeuQuarto(isHuman);
	
		/* Pour gérer compatibilité affichage */
		try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace(); 
        }
		
		 // Taille des éléments graphiques
		int tailleHaut = 150;
		int taillePiece = 75;
		int taillePlateau = 9*taillePiece;
		int tailleBas = 100;
		int hauteurFenetre = tailleHaut + tailleBas + taillePlateau;
		int largeurFenetre = taillePlateau + (int)(6.5*taillePiece);
		// panel central + panneau bas : W=1162 et H=775
		
		/* Couleurs des éléments graphiques 
		 * piece a : A32A8B
		 * piece b : FFD036
		 * wallpaper = case B : D1BCE0
		 * case A : 94859E
		 */
		
		
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
						ClickPanel[] casesPlateauCourant = casesPlateau;
						ClickPanel[] casesInventaireCourant = casesInventaire;
						JeuQuarto jeuCourant = jeu;
						
						FenetreJeu fenetreJeu = new FenetreJeu(isHuman);
						fenetreJeu.setVisible(true);
						
						fenetreJeu.etape = etapeCourant;
						fenetreJeu.updateEtape();
						fenetreJeu.casesPlateau = casesPlateauCourant;
						fenetreJeu.casesInventaire = casesInventaireCourant;
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
            this.casesPlateau[i] = new ClickPanel(x, y, taillePiece);
            this.casesPlateau[i].setImage("fondClickPanelA");
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
			this.casesInventaire[i] = new ClickPanel(x, y,taillePiece);
			this.casesInventaire[i].setImage("fondClickPanelB", jeu.getPlateau().getListePieces()[i].toString());
		}
		

		// Panneau global
        JPanel panneauGlobal = new JPanel();
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
				boolean iscasesPlateau = true;
				if (tour == 0 || tour == 2){
					selectDansInventaire = getClickedZone (coordClick, !iscasesPlateau);
				} else if (tour == 1 || tour== 3) {
					selectDansPlateau = getClickedZone (coordClick, iscasesPlateau);
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
	
	
	
	// Méthodes :
	
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
			int casePieceChoisie = this.selectDansInventaire;
			jeu.getJoueurs()[indiceJoueur].setPieceChoisie(jeu.getPlateau().getListePieces()[casePieceChoisie]);
			
			if((jeu.getJoueurs()[indiceJoueur].getPieceChoisie().isNull() || jeu.getJoueurs()[indiceJoueur].getPieceChoisie().estPlace())){
				affEtape.setText("Vous n'avez pas choisi de piece ou la piece est deja placee. Recommencez.");
			} else {
				setEtape(this.etape + 1);
			}
			
		} else { // alors l'action = un placement de pièce
			int indiceJoueurChoix = (tour==1)? 0 : 1;
			int numCase = selectDansPlateau;
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
		piece.setEnPlace(true);
		this.jeu.getJoueurs()[numJoueur].setPieceChoisie(new Piece());
		
		this.casesPlateau[numCase].setImage("fondClickPanelA", piece.toString());
		this.casesInventaire[jeu.getPlateau().indexOf(piece)].setImage("fondCLickPanelB");
		
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
            selectDansPlateau = -1;
            selectDansInventaire = -1;
        } else if ((etape%4) == 1) {
            s = s + "2 : Veuillez positionner la piece";
        } else if ((etape%4) == 2) {
            s = s + "2 : Veuillez sélectionner une piece pour le " + jeu.getJoueurs()[0].getNom();
            selectDansPlateau = -1;
            selectDansInventaire = -1;
        } else if ((etape%4) == 3) {
            s = s + "1 : Veuillez positionner la piece";
        }
        affEtape.setText(s);
        
    }
    
    /* paint(Graphics g)
     * MAJ de la fenetre de jeu
     */ 
	public void paint(Graphics g){
    
		Toolkit Tool = Toolkit.getDefaultToolkit();
		Image wallpaper = Tool.getImage("wallpaper.png");
		
		panneauHaut.repaint(); //correspond à la zone de commande
		
		this.requestFocusInWindow();
		
		if (casesInventaire[15] != null && wallpaper != null) {
			
			ImagePreparationGraphics.drawImage(wallpaper,0,panneauHaut.getHeight(),this);
			
			for (int i = 0; i < casesPlateau.length; i++) {
				if (casesPlateau[i].getImage() != null) {
					ImagePreparationGraphics.drawImage(casesPlateau[i].getImage(), casesPlateau[i].getPositionX(), casesPlateau[i].getPositionY(),this);
				}
			}
			for (int i = 0; i < casesInventaire.length; i++) {
				if (casesInventaire[i].getImage() != null) {
					ImagePreparationGraphics.drawImage(casesInventaire[i].getImage(), casesInventaire[i].getPositionX(), casesInventaire[i].getPositionY(),this);
				}
			}
			if (etape > -1) {
				ImagePreparationGraphics.setColor(Color.green);
				if (selectDansInventaire > -1) {
					ImagePreparationGraphics.drawRect( casesInventaire[selectDansInventaire].getPositionX(), casesInventaire[selectDansInventaire].getPositionY(), casesInventaire[selectDansInventaire].getTaille(),  casesInventaire[selectDansInventaire].getTaille());
				}
				if (selectDansPlateau > -1) {
					ImagePreparationGraphics.drawRect(casesPlateau[selectDansPlateau].getPositionX(), casesPlateau[selectDansPlateau].getPositionY(),  casesPlateau[selectDansPlateau].getTaille(), casesPlateau[selectDansPlateau].getTaille());
				}
			}
			g.drawImage(ImagePreparation,2,35,this);
		}
    }

    
	
	public int getClickedZone (int [] coord, boolean isCasesPlateau) {
		int res = -1;
		for (int i = 0; i < 16; i++) {
			if (isCasesPlateau && casesPlateau[i].isIn(coord[0],coord[1]) ){
				res = i;
			} else if(!isCasesPlateau && casesInventaire[i].isIn(coord[0],coord[1])){
				res = i;
			}
		}
		return res;
	}
	
}
    
	

