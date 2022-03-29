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
	public void placerPiece(Piece pieceChoisie){}
	
	/** getPiece()
	 * permet de récupérer la pièce sur laquelle le joueur a cliqué
	 */
	public Piece getPiece(){ return null;}
	
	/** getPosition()
	 * permet de récupérer la position choisie et confirmée par le joueur
	 */
	public int[] getPosition(){return null;}

	/** choisirPiece()
	 * permet au joueur de chosir une pièce pour son adversaire :
	 * 		- le joueur clique sur une pièce
			- son choix est fait quand il clique sur le bouton "confirmer"
	 */ 
	public Piece choisirPiece(){
		
		Piece pieceChoisie = null;
		do{
			pieceChoisie = getPieceChoisie();
		} while(!FenetreJeu.isBoutonConfirme());
		
		return pieceChoisie;
	}
	
	/** getPieceChoisie()
	 * récupère la pièce sur laquelle le joueur a cliqué
	 * @return : Piece choisie par le joueur
	 */
	public Piece getPieceChoisie(){
		
		int[] coordPiece = FenetreJeu.coordClick;
		
		return Plateau.getPiece(coordPiece);
	}
	
	/**isBoutonConfirme()
	 * check si le bouton 
	 */
	public boolean isBoutonConfirme(){
		return false;
	} 
	 
	
	/** estGagnant()
	 * permet de détecter si le joueur a gagné
	 * @return : booléen, true si le joueur a gagné, false sinon
	 */
	public static boolean estGagnant(){
		return false;
	}

}
