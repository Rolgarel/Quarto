import java.util.Arrays;


public class Joueur {
	
	// Attributs
	String nom;
	Piece pieceChoisie;
	boolean piecePlace;
	int caseChoisie;
	
	//Constructeur
	public Joueur (String unNom){
		this.nom = unNom;
		this.pieceChoisie = null;
		this.caseChoisie = -1;
	}
	
	
	
	// Méthodes
	
	/** choisirPiece()
	 * permet au joueur de chosir une pièce pour son adversaire, parmi celles jouables :
	 * 		- le joueur clique sur une pièce
			- son choix est fait quand il clique sur le bouton "confirmer"
	 */
	public void choisirPiece(Plateau piecesDispo){
		
		Piece[] listePieces = piecesDispo.listePieces;
		while(this.pieceChoisie == null && contains(listePieces, this.pieceChoisie)){}
		
	}
	
	
	// On pourrait trouver une seule méthode contains au lieu de faire une surcharge...
	
	/** contains(Piece[], String)
	 * @param : Piece[] et Piece
	 * @return : true si la piece est dans la liste de pièces, false sinon
	 */
	public boolean contains(Piece[] listePieces,  Piece unePiece) {
		return Arrays.toString(listePieces).contains(unePiece.toString());
	}
	
	/** contains(int[], int)
	 * @param : int[] et int
	 * @return : true si un entier est dans la liste de d'entier, false sinon
	 */
	public boolean contains(int[] listeInt,  int entier) {
		return Arrays.toString(listeInt).contains(Integer.toString(entier));
	}
	

	
	
	/** placerPiece()
	 * permet au joueur de placer une pièce sur le plateau
	 * MAJ de l'attribut 'estPlace' de la pièce placée par le joueur
	 * @param : Piece, int[] liste des cases déjà occupées
	 */
	public void placerPiece(Piece piece, int[] casesOccupees){
		
		while(this.caseChoisie < 0 && contains(casesOccupees, this.caseChoisie)){}
		piece.setPlace(true);
		
	}
	


	
	
	
	
	 
	
	/** estGagnant()
	 * permet de détecter si le joueur a gagné
	 * @return : booléen, true si le joueur a gagné, false sinon
	 */
	public boolean estGagnant(){
		// A COMPLETER
		return false;
	}

}
