
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;

import javax.swing.UIManager;

public class FenetreJeu extends JFrame implements ActionListener{
	
    //taille des éléments graphiques
	int tailleHaut = 150;
	int taillePiece = 75;
	int taillePlateau = 9*taillePiece;
	int tailleBas = 100;
	int hauteurFenetre = tailleHaut + tailleBas + taillePlateau;
	int largeurFenetre = taillePlateau + (int)(6.5*taillePiece);
    
    //éléments graphiques
    JLabel affEtape;
    JButton boutonConfirmer;
    JButton boutonRegles;
    JPanel panneauGlobal;
    JPanel panneauHaut;
    
    //Images
    Toolkit Tool = Toolkit.getDefaultToolkit();
    Image wallpaper = Tool.getImage("space.png");
    
    private BufferedImage ImagePreparation;
    private Graphics ImagePreparationGraphics;
    
    //variables de jeu
    private int etape = -1;
    ClickPanel[] pieceA = new ClickPanel[16]; //pieces au niveau du plateau
    ClickPanel[] pieceB = new ClickPanel[16]; //pieces au niveau de la zone de selection laterale
	
	public FenetreJeu () {
		
		try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace(); 
        }
		
		setTitle("Partie");
		setSize(largeurFenetre,hauteurFenetre);
		setLocation(0,0);
		
        setLayout(null);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImagePreparation = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
        ImagePreparationGraphics = ImagePreparation.getGraphics();
        
        
        //Panneau plateau
        JPanel panneauPlateau = new JPanel();
		panneauPlateau.setBounds(0,tailleHaut,taillePlateau,taillePlateau);
		panneauPlateau.setLayout(null);
        //panneauPlateau.setOpaque(false); //rend  le JPanel invisible en affichant sont contenu
		panneauPlateau.setBackground(Color.blue); //couleur pour mettre en évidence le JPanel
		//Zone de pieces plateau
		for (int i = 0; i < 16; i++) {
            pieceA[i] = new ClickPanel((4+i-5*(i/4))*taillePiece,(1+i-3*(i/4))*taillePiece,taillePiece);
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
		//Bouton règles
		boutonRegles = new JButton("Règles");
		boutonRegles.setBounds(largeurFenetre - 10 - 150,10,150,40);
        boutonRegles.addActionListener(this);
        panneauHaut.add(boutonRegles);
        //Bouton confirmer
		boutonConfirmer = new JButton("Confirmer");
		boutonConfirmer.setBounds(largeurFenetre - 10 - 150,tailleHaut - 10 - 40,150,40);
        boutonConfirmer.addActionListener(this);
        panneauHaut.add(boutonConfirmer);
        //Zone de texte d'instruction
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
		//Zone de pieces laterales
		for (int i = 0; i < 16; i++) {
			pieceB[i] = new ClickPanel((taillePiece/2)+(int)(1.5*taillePiece*(i%4)),taillePiece+2*taillePiece*(i/4),taillePiece);
            pieceB[i].setImage("test1");//erreur
		}
		
        
        //Panneau global
        panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,largeurFenetre,hauteurFenetre);
		panneauGlobal.setLayout(null);
		panneauGlobal.setBackground(Color.black);
		panneauGlobal.add(panneauPlateau);
		panneauGlobal.add(panneauHaut);
		panneauGlobal.add(panneauBas);
		panneauGlobal.add(panneauLat);
        //JLabel wall = new JLabel(wallpaper);
		
		this.add(panneauGlobal);
        
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
        //clic sur le bouton règles
        if (e.getSource() == boutonRegles) {
            System.out.println("règles");
            pieceB[4].isSelected = !pieceB[4].isSelected;
            for (int i = 0; i < pieceA.length; i++) {
                pieceA[i].setImage("test2");
            }
        } 
        //clic sur le bouton confirmer
        else if (e.getSource() == boutonConfirmer) {
            System.out.println("confirmer");
            this.repaint();
        }
    }
    
    public void setEtape (int e) {
        this.etape = e;
        this.updateEtape();
    }
    
    public void updateEtape () {
        String s = "Tour du joueur ";
        if (etape < 0) {
            s = "Erreur";
        } else if ((etape%4) == 0) {
            s = s + "1 : Veuillez sélectionner une pièce pour le joueur 2";
        } else if ((etape%4) == 1) {
            s = s + "2 : Veuillez positionner la pièce";
        } else if ((etape%4) == 2) {
            s = s + "2 : Veuillez sélectionner une pièce pour le joueur 1";
        } else if ((etape%4) == 3) {
            s = s + "1 : Veuillez positionner la pièce";
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
}
