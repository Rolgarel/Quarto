public class Plateau {
	//Attributs
	Piece [] listePieces;
	Piece [][] grille;
	int[] casesOccupees;
	
	//Constructeur
	public Plateau(boolean isInventPiece){
		listePieces = initPieces();
		int indice = 0;
		grille = new Piece [4][4];
		
		if(isInventPiece){
			for (int i=0 ; i< grille.length ;i++){
				for (int j=0 ;j<grille[i].length; j++){
					grille [i][j] = listePieces[indice];
					indice += 1;
				}
			}
		} else {
			for (int i=0 ; i< grille.length ;i++){
				for (int j=0 ;j<grille[i].length; j++){
					grille [i][j] = null;
					casesOccupees[indice] = -1;
					indice += 1;
				}
			}
		}
			
		
		
	}
	
	
	
	//Methodes
	
	/** initPieces()
	 * crée les pièces du jeu
	 * @return : un tableau de pièces 
	 */
	public Piece[] initPieces(){
		Piece[] pieces = new Piece[16];
		
		// Pieces grandes et rondes 
			// creuses
		pieces[0] = new Piece(true, true, true, true);
		pieces[1] = new Piece(true, true, false, true);
			// pleines
		pieces[2] = new Piece(true, true, true, false);
		pieces[3] = new Piece(true, true, false, false);
		
		// Pieces grandes et carrées
			// creuses
		pieces[4] = new Piece(true, false, true, true);
		pieces[5] = new Piece(true, false, false, true);
			//pleines
		pieces[6] = new Piece(true, false, true, false);
		pieces[7] = new Piece(true, false, false, false);
		
		// Pieces petites et rondes
			// creuses
		pieces[8] = new Piece(false, true, true, true);
		pieces[9] = new Piece(false, true, false, true);
			// pleines
		pieces[10] = new Piece(false, true, true, false);
		pieces[11] = new Piece(false, true, false, false);
		
		// Pieces petites et carrées
			// creuses
		pieces[12] = new Piece(false, false, true, true);
		pieces[13] = new Piece(false, false, false, true);
			// pleines
		pieces[14] = new Piece(false, false, true, false);
		pieces[15] = new Piece(false, false, false, false);


		return pieces;
	}
	
	
	/** isPlein()
	 * check si la grille du plateau est pleine
	 * @return : true si pleine, false sinon
	 */
	public boolean isPlein(){
		
		for(int i = 0; i < this.grille.length; i++){
			for(int j = 0; j < this.grille[0].length; j++){
				if(this.grille[i][j] == null){
					return false;
				}
			}
		}
		return true;
	} 
	
	

}
