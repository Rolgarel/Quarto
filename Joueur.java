import java.util.Arrays;


public class Joueur {
	
	// Attributs
	String nom;
	
	//Constructeur
	public Joueur (String unNom){
		nom = unNom;
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
	 * @return : booléen, true si le joueur a gagné, false sinon
	 */
	public boolean estGagnant(){
		return false;
	}

}
