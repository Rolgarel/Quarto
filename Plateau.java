/* STATUT :
 * attributs tous privés
 */

public class Plateau {
	
	//Attributs
	private Piece [] listePieces;
	//Piece [][] grille;
	private Piece[] grille;
	
	//Constructeur
	public Plateau(){
		
		listePieces = initPieces();
		
		grille = new Piece[16];
		for (int i=0; i < grille.length; i++){
			grille[i] = new Piece();
		}
	
	}
	
	
	// Getters
	public Piece[] getListePieces(){
		return this.listePieces;
	}
	
	public Piece[] getGrille(){
		return this.grille;
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
		while(i<grille.length && grille[i].isNull()){
			i++;
		}
		return (i==16)? true:false;
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
			if(this.grille[iGrille].isNull()){
				return false;
			}
			iGrille = (s=="l")? iGrille+1 : iGrille+4;
			j++;
		}
		return true;
	}
	
	//Retourne la piece de la grille correspondant à l'indice i
	
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
	
	/** ligneColonneGagnante()
	 * @param : indice ligne ou colonne, String : c pour test sur colonnes, l pour lignes
	 * @return : true si ligne ou colonne gagnante, false sinon
	 */
	public boolean ligneColonneGagnante(int i, String s){
		
		if(i >= 4){
			return false;
		} // sinon on check la ligne
		
		boolean pleine = false;
		if(s=="l"){
			pleine = isLigneColonnePleine(i, "l");
		} else { // si s = "c"
			pleine = isLigneColonnePleine(i, "c");
		}
		String result = "";
		result = (s=="l")? "la ligne":"la colonne";
		System.out.println(result + " " + i + " est pleine : " + pleine);
		if(!pleine){
			return false;
		} // si la ligne/colonne est pleine, on check les caractéristiques des pièces
	
        Piece[] pieces = new Piece[4];
        if (s == "l") {
            for (int y = 0; y < 4; y++) {
                pieces[y] = grille[4*i + y];
            }
        } else { //si s == 'c'
            for (int y = 0; y < 4; y++) {
                pieces[y] = grille[4*y + i];
            }
        }
        return testCaracteristiques(pieces);
		
		
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
			if(this.grille[indice].isNull()){
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
        
        Piece[] pieces = new Piece[4];
        if (penteCroiss == true) {
            for (int y =  0; y < 4; y++) {
                pieces[y] = grille[12 - 3*y];
            }
        } else {
            for (int y =  0; y < 4; y++) {
                pieces[y] = grille[5*y];
            }
        }
        return testCaracteristiques(pieces);
		
	}
	
	
    
    /**testCaracteristiques
     * regarde si des pieces données ont une caractéristique commune
     * prend en paramètre une liste de pièces
     * @return: true si une caractéristique commune est trouvée entre les pièces
     */
    public boolean testCaracteristiques (Piece[] pieces) {
        boolean condition = false;
        if (pieces[0].isNull() == true) {
            return condition;
        }
        boolean[] semblable = {true, true, true, true};
        for (int i = 1; i < pieces.length; i++) {
            if (pieces[0].isNull() == true) {
                return condition;
            }
            if (pieces[0].estGrand() != pieces[i].estGrand()) {
                semblable[0]  = false;
            }
            if (pieces[0].estRond() != pieces[i].estRond()) {
                semblable[1]  = false;
            }
            if (pieces[0].getCouleur() != pieces[i].getCouleur()) {
                semblable[2]  = false;
            }
            if (pieces[0].estCreux() != pieces[i].estCreux()) {
                semblable[3]  = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (semblable[i] == true) {
                condition = true;
            }
        }
        return condition;
    }

}
