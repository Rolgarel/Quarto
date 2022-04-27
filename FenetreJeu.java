import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class FenetreJeu extends JFrame implements ActionListener {
	
    
    // Eléments graphiques
    private JLabel affEtape;
    private JButton boutonConfirmer;
    private JButton boutonRegles;
    private JPanel panneauHaut;
    private JLabel affTourJoueur;
    private JLabel affPieceChosie;
   
    private BufferedImage ImagePreparation;
    private Graphics ImagePreparationGraphics;
    
    private FenetreFinJeu finJeu;
    
    
    // Variables de jeu
    private JeuQuarto jeu;
    private int etape = -1;
    private boolean tourBot = false;
    private ClickPanel[] pieceA = new ClickPanel[16]; // pieces plateau
    private ClickPanel[] pieceB = new ClickPanel[16]; // pieces zone de selection laterale
    private int[] coordClick = new int[2];
    private int selectA = -1;
    private int selectB = -1;
    private int choixBotA = -1;
    private int choixBotB = -1;
    
    
	
  
	
	// Constructeur :
	public FenetreJeu(boolean isHuman){
		
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
		// tout l'écran : W=1162 et H=925
		
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
					
                    if (finJeu != null) {
                        finJeu.dispatchEvent(new WindowEvent(finJeu, WindowEvent.WINDOW_CLOSING));
                        
                    } else {
                    
                        int result = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir quitter le jeu ?", "Pause", JOptionPane.OK_CANCEL_OPTION);
					
                        if(result == JOptionPane.YES_OPTION){
                            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            FenetreMenu menu = new FenetreMenu();
                            menu.setVisible(true);
						
                        } else { // bouton Cancel
                            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        }
					
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
		panneauEtape.setBounds(15,tailleHaut -50 -10,600,30);
		panneauEtape.setLayout(null);
		panneauEtape.setBackground(Color.black); //couleur de font du text
        
        affEtape = new JLabel();
        affEtape.setFont(new Font("Dialog", Font.BOLD, 20));
		affEtape.setBounds(0,0,600,20);
		affEtape.setForeground(Color.white);
		updateEtape();
		panneauEtape.add(affEtape);
		panneauHaut.add(panneauEtape);
		
		affTourJoueur = new JLabel();
		affTourJoueur.setFont(new Font("Dialog", Font.BOLD, 40));
		affTourJoueur.setBounds(20,10, 500, 50);
		affTourJoueur.setForeground(Color.white);
		panneauHaut.add(affTourJoueur);
		
		
		// Affichage de la pièce choisie
		try{
			affPieceChosie = new JLabel();
			affPieceChosie.setBounds(panneauEtape.getX() + panneauEtape.getWidth() + 2*taillePiece, 40, taillePiece, taillePiece);
			File imgFile = new File("img_fond/caseVide.png");
			BufferedImage bufferedImage = ImageIO.read(imgFile);
			ImageIcon imageIcon = new ImageIcon(bufferedImage); 
			affPieceChosie.setIcon(imageIcon);
			panneauHaut.add(affPieceChosie);
		} catch (IOException e){
			e.printStackTrace();
		}
       
		
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
			this.pieceB[i].setImage("fondClickPanelB", jeu.getPlateau().getListePieces()[i].toString());
		}
		

		// Panneau global
        JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,largeurFenetre,hauteurFenetre);
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(Color.black);
		
		panneauGlobal.addMouseListener(new MouseAdapter() {
            
			@Override // MAJ de l'étape après appui sur le bouton confirmer
			public void mousePressed(MouseEvent e) {
					
				//System.out.println(e.getX() + "," + e.getY());
				coordClick[0] = e.getX();
				coordClick[1] = e.getY();
				
				int tour = etape%4;
				boolean isA = true;
				if ((tourBot == false) && (tour == 0 || tour == 2)){
					selectB = getClickedZone (coordClick, !isA);
				} else if ((tourBot == false) && (tour == 1 || tour== 3)) {
					selectA = getClickedZone (coordClick, isA);
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
			//System.out.println("confirmer : etape " + this.etape);
            if (this.etape == -10) {
                finJeu = new FenetreFinJeu(jeu.getEtatFinJeu(), jeu.getJoueurs(), this);
            } else if(this.etape == -1) {
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
        
		if(tour == 0 || tour == 2){  // alors l'action = un choix de pièce
	
			int indiceJoueur = (tour==0)? 0 : 1;
			int casePieceChoisie = this.selectB;
            Piece pieceChoisie = new Piece();
            if (casePieceChoisie != -1) {
                pieceChoisie = jeu.getPlateau().getListePieces()[casePieceChoisie];
            }
			jeu.getJoueurs()[indiceJoueur].setPieceChoisie(pieceChoisie);
			
			if((jeu.getJoueurs()[indiceJoueur].getPieceChoisie().isNull() || jeu.getJoueurs()[indiceJoueur].getPieceChoisie().estPlace())){
				affEtape.setText("Vous n'avez pas choisi de pièce ou la pièce est deja placée. Recommencez.");
			} else {
				try{
					File imgFile = new File("img_pieces/" + pieceChoisie.toString() + ".png");
					BufferedImage bufferedImage = ImageIO.read(imgFile);
					ImageIcon imageIcon = new ImageIcon(bufferedImage);
					affPieceChosie.setIcon(imageIcon);
				} catch (IOException e) {
					e.printStackTrace();
				} 
				setEtape(this.etape + 1);
			}
			
		} else { // alors l'action = un placement de pièce
			int indiceJoueurChoix = (tour==1)? 0 : 1;
			int numCase = this.selectA;
			System.out.print("numCase = " + numCase);
            if (numCase != -1) {
                if(jeu.getPlateau().getGrille()[numCase].isNull()){
                    placerPiece(jeu.getJoueurs()[indiceJoueurChoix].getPieceChoisie(), numCase, indiceJoueurChoix);
                    try{
                        File imgFile = new File("img_fond/caseVide.png");
                        BufferedImage bufferedImage = ImageIO.read(imgFile);
                        ImageIcon imageIcon = new ImageIcon(bufferedImage);
                        affPieceChosie.setIcon(imageIcon);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(jeu.isOver(etape)){
                        System.out.println("Etat fin de jeu : " + jeu.getEtatFinJeu());
                        setEtape(-10);
                        finJeu = new FenetreFinJeu(jeu.getEtatFinJeu(), jeu.getJoueurs(), this);
                    } else {
                        setEtape(this.etape + 1);
                    }
				
                } else {
                    affEtape.setText("La case choisie est deja occupée. Recommencez.");
                }
            } else {   
                affEtape.setText("Aucune case n'est sélectionnée. Recommencez.");
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
		
        if (etape == -10) {
            affEtape.setText("Partie terminée");
        } else {
            String s = " Instruction : ";
            if (etape < 0) {
                s = "La partie va commencer  : appuyez sur le bouton Confirmer";
            } else {
                int tour = etape%4;
                //System.out.println(tour);
                if ((tour == 0) || (tour == 3)) {
                    tourBot = jeu.getJoueurs()[0].getIsBot();
                } else {
                    tourBot = jeu.getJoueurs()[1].getIsBot();
                }
                if ((etape%4) == 0) {
                    if (tourBot == false) {
                        s = s + "Veuillez sélectionner une piece pour le " + jeu.getJoueurs()[1].getNom();
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[0].getNom());
                        selectA = -1;
                        selectB = -1;
                    } else {
                        if (etape == 0) {
                            choixBotB = pieceAleatoire();
                        }
                        s = "Veuillez appuyer sur le bouton Confirmer";
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[0].getNom());
                        selectA = -1;
                        selectB = choixBotB;
                    }
                } else if ((etape%4) == 1) {
                    if (tourBot == false) {
                        s = "Veuillez positionner la piece";
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[1].getNom());
                    } else {
                        choixIA(selectB);
                        s = s + "Veuillez appuyer sur le bouton Confirmer";
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[1].getNom());
                        selectA = choixBotA;
                    }
                } else if ((etape%4) == 2) {
                    if (tourBot == false) {
                        s = s + "Veuillez sélectionner une piece pour le " + jeu.getJoueurs()[0].getNom();
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[1].getNom());
                        selectA = -1;
                        selectB = -1;
                    } else {
                        s = s + "Veuillez appuyer sur le bouton Confirmer";
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[1].getNom());
                        selectA = -1;
                        selectB = choixBotB;
                    }
                } else if ((etape%4) == 3) {
                    if (tourBot == false) {
                        s = s + "Veuillez positionner la piece";
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[0].getNom());
                    } else {
                        choixIA(selectB);
                        s = s + "Veuillez appuyer sur le bouton Confirmer";
                        affTourJoueur.setText("Tour : " + jeu.getJoueurs()[0].getNom());
                        selectA = choixBotA;
                    }
                }
            }
            s = Main.encodeUTF8(s);
            affEtape.setText(s);
        }
    }
    
    /* paint(Graphics g)
     * MAJ de la fenetre de jeu
     */ 
	public void paint(Graphics g){
    
		Toolkit Tool = Toolkit.getDefaultToolkit();
		Image wallpaper = Tool.getImage("img_fond/backgroundJeu.png");
		
		panneauHaut.repaint(); //correspond à la zone de commande
		
		this.requestFocusInWindow();
		
		if (pieceB[15] != null && wallpaper != null) {
			
			ImagePreparationGraphics.drawImage(wallpaper,0,panneauHaut.getHeight(),this);
			
			for (int i = 0; i < pieceA.length; i++) {
				if (pieceA[i].getImage() != null) {
					ImagePreparationGraphics.drawImage(pieceA[i].getImage(), pieceA[i].getPositionX(), pieceA[i].getPositionY(),this);
				}
			}
			for (int i = 0; i < pieceB.length; i++) {
				if (pieceB[i].getImage() != null) {
					ImagePreparationGraphics.drawImage(pieceB[i].getImage(), pieceB[i].getPositionX(), pieceB[i].getPositionY(),this);
				}
			}
			if (etape > -1) {
				ImagePreparationGraphics.setColor(Color.green);
				if (selectB > -1) {
					ImagePreparationGraphics.drawRect( pieceB[selectB].getPositionX(), pieceB[selectB].getPositionY(), pieceB[selectB].getTaille(),  pieceB[selectB].getTaille());
				}
				if (selectA > -1) {
					ImagePreparationGraphics.drawRect(pieceA[selectA].getPositionX(), pieceA[selectA].getPositionY(),  pieceA[selectA].getTaille(), pieceA[selectA].getTaille());
				}
				
			}
			
			g.drawImage(ImagePreparation,2,35,this);
		}
    }

    
	/**getClickedZone()
     * Recherche si le joueur à cliqué sur un clickPanel dans une des deux zones possibles
     * @param: int [] coord, les coordonées du click dans la fenêtre
     *         boolean isA, true si la zone est la A
     * @return: int res, l'indice du clickPanel dans lequel le joueur à clické, -1 sinon
     */
	public int getClickedZone (int [] coord, boolean isA) {
		int res = -1;
		for (int i = 0; i < 16; i++) {
			if (isA && pieceA[i].isIn(coord[0],coord[1]) ){
				res = i;
			} else if(!isA && pieceB[i].isIn(coord[0],coord[1])){
				res = i;
			}
		}
		return res;
	}
    
    /**choixIA()
     * Recherche des solutions avec l'IA pour une pièce donnée
     * @param: int indicePiece, indice de la pièce donnée à l'IA
     */
    public void choixIA (int indicePiece) {
        Plateau plateau = jeu.getPlateau();
        BotAI ia = new BotAI(plateau, indicePiece);
        choixBotA = ia.getPositionSolution();
        choixBotB = ia.getIndicePieceSolution();
    }
    
    /**choixIA()
     * Donne l'indice d'une pièce aléatoire
     */
    public int pieceAleatoire () {
        int indicePiece = (int)(16*Math.random());
        return indicePiece;
    }
	
}
    
	

