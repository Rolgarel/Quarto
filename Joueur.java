public class Joueur {
	
	// Attributs
	private String nom;
	
	//Constructeur
	public Joueur (String unNom){
		nom = unNom;
	}
	
	
	
	// Méthodes
	
	/** placerPiece()
	 * permet au joueur de placer une pièce sur le plateau
	 * @param : piece, deux entiers permetttant d'identifier l'endroit où l'on souhaite placer la piece
	 */
	public void placerPiece(Piece piece, int colonne, int ligne){
		piece.setPlace(true);
	}
	
	/** choisirPiece()
	 * permet au joueur de chosir une pièce pour son adversaire
	 * 
	 */ 
	public void choisirPiece(){
		//...
	}

}
