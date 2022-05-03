import java.util.Arrays;


public class Joueur {
	
	// Attributs : 
	private String nom;
	private Piece pieceChoisie;
	private int caseChoisie;
    private boolean isBot;
	
	//Constructeur :
    public Joueur (String unNom, boolean bot){
		this.nom = unNom;
		this.pieceChoisie = new Piece();
		this.caseChoisie = -1;
        this.isBot = bot;
	}

	// Getters :
	public String getNom(){
		return this.nom;
	}
	
	public Piece getPieceChoisie(){
		return this.pieceChoisie;
	}
	
	public int getCaseChoisie(){
		return this.caseChoisie;
	}
    
    public boolean getIsBot() {
        return this.isBot;
    }
	
	
	
	// Setters
	public void setPieceChoisie(Piece piece){
		this.pieceChoisie = piece;
	}
	
	public void setCaseChoisie(int numCase){
		this.caseChoisie = numCase;
	}
	

	
	// Méthodes :
	
	/** estGagnant
	 * permet de détecter si le joueur a gagné
	 * @param plateau le plateau sur lequel se déroule la partie
	 * @return true si gagnant, false sinon
	 */
	public boolean estGagnant(Plateau plateau){
		
		int i=0;
		
		//test pour les lignes
		// i = le numére de ligne, il y en a 4 numérotées de 0 à 3 inclus
		while (plateau.ligneColonneGagnante(i, "l")==false && i<4){ // ligneColonneGagnante() OK
			i++;
		}
		if(i!=4){
			System.out.println("Ligne gagnante");
			return true;
		} else {
			// si i=4 on fait un autre test
			i = 0;
		}
		
		
		//test pour les colonnes
		// i = le numéro de colonne, il y en a 4  numérotées de 0 à 3 inclus
		while (plateau.ligneColonneGagnante(i, "c")==false && i<4){  // ligneColonneGagnante() OK
			i++;
		}
		if(i<4){
			return true;
		}// on fait un autre test

		
		
		//test pour les diagonales
		boolean penteCroiss = plateau.alignementGagnantD(true);
		boolean penteDecroiss = plateau.alignementGagnantD(false);
		if(penteCroiss || penteDecroiss){
			String s = (penteCroiss)? "Horizontale gagnante" : "Verticale gagnante";
			return true;
		}
		
		// Si tous les tests sont négatifs :
		return false;
	}
}
