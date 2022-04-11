public class Plateau {
	
	//Attributs
	Piece [] listePieces;
	//Piece [][] grille;
	Piece[] grille;
	
	//Constructeur
	public Plateau(){
		
		listePieces = initPieces();
		
		grille = new Piece[16];
		for (int i=0; i < grille.length; i++){
			grille[i] = new Piece();
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
		pieces[0] = new Piece(true, true, true, true, 0);
		pieces[1] = new Piece(true, true, false, true, 1);
			// pleines
		pieces[2] = new Piece(true, true, true, false, 2);
		pieces[3] = new Piece(true, true, false, false, 3);
		
		// Pieces grandes et carrées
			// creuses
		pieces[4] = new Piece(true, false, true, true, 4);
		pieces[5] = new Piece(true, false, false, true, 5);
			//pleines
		pieces[6] = new Piece(true, false, true, false, 6);
		pieces[7] = new Piece(true, false, false, false, 7);
		
		// Pieces petites et rondes
			// creuses
		pieces[8] = new Piece(false, true, true, true, 8);
		pieces[9] = new Piece(false, true, false, true, 9);
			// pleines
		pieces[10] = new Piece(false, true, true, false, 10);
		pieces[11] = new Piece(false, true, false, false, 11);
		
		// Pieces petites et carrées
			// creuses
		pieces[12] = new Piece(false, false, true, true, 12);
		pieces[13] = new Piece(false, false, false, true, 13);
			// pleines
		pieces[14] = new Piece(false, false, true, false, 14);
		pieces[15] = new Piece(false, false, false, false, 15);


		return pieces;
	}
	
	
	
	// Méthodes
	
	
	/** isGrillePleine()
	 * check si la grille du plateau est pleine
	 * @return : boolean = true si pleine, false sinon
	 */
	public boolean isGrillePleine(){
		int i = 0;
		while(i<grille.length && grille[i].isNull){
			i++;
		}
		return (i==6)? true:false;
	}
	
	
	/* isLigneColonnePleine()
	 * Vérifie sur une ligne ou une colonne est pleine
	 * @param : 
	 * 		int i, indice de la ligne ou de la colonne
	 * 		String s, "l" si l'indice correspond à une ligne, "c" pour une colonne
	 * @return : true si la ligne/colonne i est pleine, false sinon
	 */
	public boolean isLigneColonnePleine(int i, String s){
		
		int iGrille = 0;
		
		if(s=="l"){
			switch(i){
				case 0 :
					iGrille = 0;
					break;
				case 1 :
					iGrille = 4;
					break;
				case 2 :
					iGrille = 8;
					break;
				case 3 :
					iGrille = 12;
					break;
			}
		} else { // si s = "c"
			iGrille = i;
		}
		
		for(int j=0; j < 4 && iGrille < 16; j++){
			if(this.grille[iGrille].isNull){
				return false;
			}
			iGrille = (s=="l")? iGrille+1 : iGrille+4;
			j++;
		}
		return true;
	}
	
	
	
	/** ligneColonneGagnante()
	 * @param : indice ligne ou colonne, String : c pour test sur colonnes, l pour lignes
	 * @return : true si ligne ou colonne gagnante, false sinon
	 */
	public boolean ligneColonneGagnante(int i, String s){
		
		if(i >= 4){
			return false;
		} // sinon on check la ligne
		
		boolean pleine = (s=="l")? isLigneColonnePleine(i, "l") : isLigneColonnePleine(i, "c");
		//String result = "";
		//result = (s=="l")? "la ligne":"la colonne";
		//System.out.println(result + " " + i + " est pleine : " + pleine);
		if(!pleine){
			return false;
		} // si la ligne/colonne est pleine, on check les caractéristiques des pièces
	
		int iGrille = 0;
		
		if(s == "l"){
			switch(i){
				case 0 :
					iGrille = 0;
					break;
				case 1 :
					iGrille = 4;
					break;
				case 2 :
					iGrille = 8;
					break;
				case 3 :
					iGrille = 12;
					break;
			}
		} else { // if s == "c"
			iGrille = i;
		}
		
		
		boolean taille0 = grille[iGrille].estGrand();
		boolean forme0 = grille[iGrille].estRond();
		boolean color0 = grille[iGrille].getCouleur();
		boolean remplissage0 = grille[iGrille].estCreux();
		
		iGrille = (s=="l")? iGrille+1 : iGrille+4;
		
		String commonCaract = ""; // donne le type du caractère partagé
		boolean caract0 = false; // initialisation de la variable
		
		// Identification du caractère commun :
		if(taille0 == grille[iGrille].estGrand()){
			commonCaract = "taille";
			caract0 = taille0;
		} else if(forme0 == grille[iGrille].estRond()){
			commonCaract = "forme";
			caract0 = forme0;
		} else if(color0 == grille[iGrille].getCouleur()){
			commonCaract = "color";
			caract0 = color0;
		} else if(remplissage0 == grille[iGrille].estCreux()){
			commonCaract = "remplissage";
			caract0 = remplissage0;
		} else { // aucune caractéristique commune.
			return false;
		}
		
		// Vérification du caractère commun sur les deux dernières pièces
		iGrille = (s=="l")? iGrille+1 : iGrille+4;
		
		for(int j=0; j<2 && iGrille < 16; j++){
			if(!hasSameCaract(commonCaract, caract0, grille[iGrille])){
				return false;
			}
			iGrille = (s=="l")? iGrille+1 : iGrille+4;
			
		}
		// si les deux autres pions partage la caractéristique commune: 
		return true;
		
		
	}
	
	
	/* hasSameCaract()
	 * Compare les caractéristiques qui pourrait être commune entre une pièce et une pièce de référence
	 * @param : 
	 * 		String typeCaract : nature de la caractéristique commune
	 * 		boolean ref : valeur de la caractéristique de réf
	 * 		Piece piece : pièce qui fait l'objet de la comparaison avec la référence
	 * @retrun : ture si la caractéristique commune est partagée, non sinon
	 */
	public boolean hasSameCaract(String typeCaract, boolean ref, Piece piece){
		if(typeCaract == "taille"){
			return ref == piece.estGrand();
		} else if(typeCaract == "forme"){
			return ref == piece.estRond();
		} else if(typeCaract == "color"){
			return ref == piece.getCouleur();
		} else if(typeCaract == "remplissage"){
			return ref == piece.estCreux();
		}
		// Si la caractérisique commune n'est pas partagée :
		return false;
	}
	
	/** isDiagonalePleine
	 * prend en param penteCroiss true pour la diag : / , et false pour la diag : \
	 * @return : true si pleine, false sinon
	 */
	
	public boolean isDiagonalePleine (boolean penteCroiss){
		
		int indice = 0;
		int increment = 0;
		
		indice = (penteCroiss)? 3:0;
		increment = (penteCroiss)? 4-1 : 4+1;
		
		for(int j=0; j<4 && indice < 16; j++){
			if(this.grille[indice].isNull){
				return false;
			}
			indice = indice + increment;
		}
		return true;
	
	}
	
	/**alignementGagnantD
	 * regarde si une diagonale est gagnante
	 * prend en param penteCroiss true pour la diag : / , et false pour la diag : \
	 * @return : true si gagnant, false sinon
	 */ 
	 
	public boolean alignementGagnantD (boolean penteCroiss){
		
		boolean pleine = isDiagonalePleine(penteCroiss);
		//String result = "";
		//result = (penteCroiss)? "l'horizontale":"la verticale";
		//System.out.println(result + " est pleine : " + pleine);
		if(!pleine){
			return false;
		} // si la diagonale est pleine, on check les caractéristiques des pièces
		
		int iGrille = 0;
		int increment = 0;
		
		iGrille = (penteCroiss)? 3:0;
		increment = (penteCroiss)? 4-1 : 4+1;
		
		boolean taille0 = grille[iGrille].estGrand();
		boolean forme0 = grille[iGrille].estRond();
		boolean color0 = grille[iGrille].getCouleur();
		boolean remplissage0 = grille[iGrille].estCreux();
	
		iGrille += increment;
		
		String commonCaract = ""; // donne le type du caractère partagé
		boolean caract0 = false; // initialisation de la variable
		
		// Identification du caractère commun :
		if(taille0 == grille[iGrille].estGrand()){
			commonCaract = "taille";
			caract0 = taille0;
		} else if(forme0 == grille[iGrille].estRond()){
			commonCaract = "forme";
			caract0 = forme0;
		} else if(color0 == grille[iGrille].getCouleur()){
			commonCaract = "color";
			caract0 = color0;
		} else if(remplissage0 == grille[iGrille].estCreux()){
			commonCaract = "remplissage";
			caract0 = remplissage0;
		} else { // aucune caractéristique commune.
			return false;
		}
		
		// Vérification du caractère commun sur les deux dernières pièces
		iGrille += increment;
		
		for(int j=0; j<2 && iGrille < 16; j++){
			if(!hasSameCaract(commonCaract, caract0, grille[iGrille])){
				return false;
			}
			iGrille += increment;
			
		}
		// si les deux autres pions partage la caractéristique commune: 
		return true;
		
	}
	
	//new//Retourne la piece de la grille correspondant à l'indice i
	
	public Piece getPieceByIndice(int indiceCase, boolean isGrille) {
		/*
		if(isGrille){
			int y = indiceCase/4; //numero de la ligne
			int x = indiceCase%4; //numero de la colone
			return grille[y][x];
		} else {
			return listePieces[indiceCase];
		}*/
		return new Piece();
		
		
	}
	
	public boolean isOccupee (int i) {
		boolean b = true;
		if(getPieceByIndice(i, true) == null) {
			b = false;
		}
		return b;
	}

}
