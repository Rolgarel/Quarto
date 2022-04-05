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
	
	
	/** isGrillePleine()
	 * check si la grille du plateau est pleine
	 * @return : true si pleine, false sinon
	 */
/*
	public boolean isPlein(){
		
		for(int i = 0; i < this.grille.length; i++){
			for(int j = 0; j < this.grille[0].length; j++){
				if(this.grille[i][j] == null){
					return false;
				}
			}
		}
		return true;
	}*/

	public boolean isGrillePleine(){
		boolean plein = false;
		int i =0;
		while ((i<4) && (!isLignePleine(i))){
			i++;
		}
		if (i>3){
			plein = true;
		}
		return plein;
	}
	
	/** isLignePleine
	 * regarde si une ligne d'indice i est pleine
	 * @return : true si pleine, false sinon
	 */
	public boolean isLignePleine (int i){
		boolean plein = false;
		int j =0;
		while (j<4 && grille[i][j] != null){
			j++;
		}
		if(j>3){
			plein = true;
		}
		return plein ;
	}
	
	/** isColonnePleine
	 * regarde si une colonne d'indice i est pleine
	 * @return : true si pleine, false sinon
	 */
	public boolean isColonnePleine (int i){
		boolean plein = false;
		int j =0;
		while (j<4 && grille[j][i] != null){
			j++;
		}
		if(j>3){
			plein = true;
		}
		return plein;
	}
	
	/** isDiagonalePleine
	 * prend en param penteCroiss true pour la diag : / , et false pour la diag : \
	 * @return : true si pleine, false sinon
	 */
	public boolean isDiagonalePleine (boolean penteCroiss){
		boolean plein = false;
		if (penteCroiss){
			int i = 0;
			while (i<4 && grille[i][i] != null){
				i++;
			}
			if (i>3){
				plein = true;
			}
		}else{
			int i = 0;
			int j = 3;
			while (i<4 && j>=0 && grille[i][j] != null){
				i++;
				j--;
			}
			if (i>3){
				plein = true;
			}
		}
		return plein;
	}
	
	/**alignementGagnantL
	 * regarde si une ligne est gagnante
	 * prend en param l'indice i de la ligne
	 * @return : true si gagnant, false sinon
	 */ 
	public boolean alignementGagnantL (int i){
		boolean gagnant = false;
		int j =0;
		boolean taille = grille[i][j].estGrand();
		boolean forme = grille[i][j].estRond();
		boolean cou = grille[i][j].getCouleur();
		boolean remplissage = grille[i][j].estCreux();
		while ((j<3) && (grille[i][j] != null) && (grille[i][j+1].estGrand() == taille) && (grille[i][j+1].estRond() == forme) && (grille[i][j+1].getCouleur() == cou) && (grille[i][j+1].estCreux ()== remplissage)){
			j++;
		}
		if(j>2){
			gagnant = true;
		}
		return gagnant ;
		
	}
	
	/**alignementGagnantC
	 * regarde si une colonne est gagnante
	 * prend en param l'indice i de la colonne
	 * @return : true si gagnant, false sinon
	 */ 
	public boolean alignementGagnantC (int i){
		boolean gagnant = false;
		int j =0;
		boolean taille = grille[j][i].estGrand();
		boolean forme = grille[j][i].estRond();
		boolean cou = grille[j][i].getCouleur();
		boolean remplissage = grille[j][i].estCreux();
		while ((j<3) && (grille[j][i] != null) && (grille[j+1][i].estGrand() == taille) && (grille[j+1][i].estRond() == forme) && (grille[j+1][i].getCouleur() == cou) && (grille[j+1][i].estCreux ()== remplissage)){
			j++;
		}
		if(j>2){
			gagnant = true;
		}
		return gagnant ;
		
	}
	
	/**alignementGagnantD
	 * regarde si une diagonale est gagnante
	 * prend en param penteCroiss true pour la diag : / , et false pour la diag : \
	 * @return : true si gagnant, false sinon
	 */ 
	public boolean alignementGagnantD (boolean penteCroiss){
		boolean gagnant = false;
		if (penteCroiss){
			int i = 0;
			int j =0;
			boolean taille = grille[i][i].estGrand();
			boolean forme = grille[i][i].estRond();
			boolean cou = grille[i][i].getCouleur();
			boolean remplissage = grille[i][i].estCreux();
			while ((i<3) && (grille[i][i] != null) && (grille[i+1][i+1].estGrand() == taille) && (grille[i+1][i+1].estRond() == forme) && (grille[i+1][i+1].getCouleur() == cou) && (grille[i+1][i+1].estCreux ()== remplissage)){
				i++;
			}
			if (i>2){
				gagnant = true;
			}
		}else{
			int i = 0;
			int j = 3;
			boolean taille = grille[i][j].estGrand();
			boolean forme = grille[i][j].estRond();
			boolean cou = grille[i][j].getCouleur();
			boolean remplissage = grille[i][j].estCreux();
			while ((i<3) && (j>0) && (grille[i][j] != null) && (grille[i+1][j-1].estGrand() == taille) && (grille[i+1][j-1].estRond() == forme) && (grille[i+1][j-1].getCouleur() == cou) && (grille[i+1][j-1].estCreux ()== remplissage)){
				i++;
				j--;
			}
			if (i>2){
				gagnant = true;
			}
		}
		
		return gagnant ;
		
	}
	

}
