public class JeuQuarto {
	
	static Plateau plateau;
	static Joueur[] joueurs;
	
	public static void main(String[] args){
		FenetreMenu menu = new FenetreMenu();
		
		int etatPartie = startGame();
		gestionEndGame(etatPartie);
	}
	
	
	public static int startGame(){
		
		plateau = new Plateau();  // les pièces sont créés avec le plateau
		boolean isOver = false;
		int etatPartie = 0;
		joueurs = initJoueur();
		int joueurCourant = 0;
		int tour = 0;
		
		
		while(isOver==false){
			
			if(tour==0){
				Piece pieceChoisie = joueurs[joueurCourant].choisirPiece();
				tour += 1;
				joueurCourant = initNewCurrentJoueur(joueurCourant);
			}
			
			joueurs[joueurCourant].placerPiece(pieceChoisie);
			isOver = etreFinPartie();
			
			if(!isOver){
				joueurs[joueurCourant].choisirPiece();
				tour += 1;
			}
			
			joueurCourant = initNewCurrentJoueur(joueurCourant);
			
		}
		etatPartie = checkEtatPartie();
		return etatPartie;
	}
	
	
	
	
	// Méthodes du main
	
	public static void gestionEndGame(int etatPartie) {
		
		final int EGALITE = 3;
		if(etatPartie == EGALITE){
			// message fin de jeu avec égalité
		} else {
			// message fin de jeu avec joueur 1 ou 2 gagnant
		}
	}
	
	// Dans la classe main : récupérer tour du jeu
	/** initNewCurrentJoueur()
	 * permet de lancer le tour de l'autre joueur
	 * @return : entier = indice du joueur dans le tableau de joueur
	 */
	public static int initNewCurrentJoueur(int indiceJoueurCourant){
		int indiceJoueur = 0;
		if(indiceJoueurCourant == 0){
			return 1;
		} else {
			return 0;
		}
	}
	
	/** initJoueur()
	 * Initialise les joueurs
	 */
	public static Joueur[] initJoueur(){
		Joueur[] joueurs = new Joueur[2];
		joueurs[1] = new Joueur("Joueur 1");
		joueurs[2] = new Joueur("Joueur 2");
		return joueurs;
	} 
	
	/** initPartie()
	 * permet d'initialiser la partie (???)
	 */
	public void initPartie(){
		//...
	}
	
	/** etreFinPartie()
	 * permet de détecter la fin de la partie
	 * @return : booléen, true si fin de partie, false sinon
	 */ 
	public static boolean etreFinPartie(){
		boolean isOver;
		if(joueurs[0].estGagnant() || joueurs[1].estGagnant() || etreEgalite()){
			isOver = true;
		} else {
			isOver = false;
		}
		return isOver;
	}
	
	
	/** etreEgalite()
	 * permet de détecter s'il y a match nul
	 * @return : booléen, true si match nul, false sinon
	 */
	public static boolean etreEgalite(){
		boolean egalite = false;
		return egalite;
	}
	// cette méthode ne sert pas finalement

	/** checkEtatPartie()
	 * renvoie :
	 * 		1 si joueur 1 gagne
	 * 		2 si joueur 2 gagne
	 * 		3 si egalite
	 */	
	public static int checkEtatPartie(){
		if(joueurs[0].estGagnant()){
			return 1;
		} else if(joueurs[1].estGagnant()){
			return 2;
		} else {
			return 3;
		}
	}

	
}
