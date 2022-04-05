import java.util.Arrays;


public class Joueur {
	
	// Attributs
	String nom;
	Plateau plateau;
	
	//Constructeur
	public Joueur (String unNom, Plateau unPlateau){
		nom = unNom;
		plateau = unPlateau;
	}
	
	
	
	// Méthodes
	
	/** placerPiece()
	 * permet au joueur de placer une pièce sur le plateau
	 * @param : piece, deux entiers permetttant d'identifier l'endroit où l'on souhaite placer la piece
	 */
	public void placerPiece(Piece pieceChoisie){
		// MAJ attribut estPlace de la piece
		// MAJ plateau
		// MAJ listePiecesDispo
	}
	
	/** getPiece()
	 * permet de récupérer la pièce sur laquelle le joueur a cliqué
	 */
	public Piece getPiece(){ return null;}
	
	/** getPosition()
	 * permet de récupérer la position choisie et confirmée par le joueur
	 */
	public int[] getPosition(){return null;}

	/** choisirPiece()
	 * permet au joueur de chosir une pièce pour son adversaire, parmi celles jouables :
	 * 		- le joueur clique sur une pièce
			- son choix est fait quand il clique sur le bouton "confirmer"
	 */ 
	public Piece choisirPiece(Plateau piecesDispo){
		
		Piece pieceChoisie = null;
		do{
			do {
				pieceChoisie = FenetreJeu.getPieceChoisie();
			} while(contains(piecesDispo.listePieces, pieceChoisie));
		} while(!FenetreJeu.isBoutonConfirme());
		
		return pieceChoisie;
	}
	
	/** contains(Piece[], String)
	 * @return : true si la piece est dans le tableau
	 */
	public boolean contains(Piece[] pieces,  Piece unePiece) {
		return Arrays.toString(pieces).contains(unePiece.toString());
	}
	
	 
	
	/** estGagnant()
	 * permet de détecter si le joueur a gagné
	 * @return : true si gagnant, false sinon
	 */
	public boolean estGagnant(){
		boolean aGagne = false;
		boolean ligne = false;
		boolean colonne = false;
		boolean diagonale = false;
		int i =0;
		
		//test pour les lignes
		while ((i<4) && (plateau.alignementGagnantL(i)==false)){
			i++;
		}
		if (i>3){
			ligne = true;
		}
		
		//test pour les colonnes
		while ((i<4) && (plateau.alignementGagnantC(i)==false)){
			i++;
		}
		if (i>3){
			colonne = true;
		}
		
		//test pour les diagonales
		boolean penteCroiss = plateau.alignementGagnantD (true);
		boolean penteDecroiss = plateau.alignementGagnantD (false);
		if (penteCroiss || penteDecroiss){
			diagonale = true;
		}
		
		//test gagnant
		if (ligne || colonne || diagonale){
			aGagne = true;
		}
		
		return aGagne;
		
	}
}


