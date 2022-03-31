public class JeuQuarto {
	
	// Attributs du jeu :
	
	Plateau plateau;
	Joueur[] joueurs;
	Plateau piecesDispo;
	Piece pieceChoisie;
	
	
	
	// Constructeur 
	
	public JeuQuarto(boolean isHuman){
	
		plateau = new Plateau(false);		// les pièces sont créés avec le plateau
		piecesDispo = new Plateau(true);
		joueurs = initJoueur(isHuman);
		pieceChoisie = new Piece();
			
	}
	
	
	// Méthodes
	
	/* actionJoueur(int etape)
     * @param : int etape : indique l'action qui doit être faite (mais pas qui doit le faire)
     */
    public void actionJoueur(int etape){
 
		int tour = etape%4;
		switch(tour){
			case 0 :
				pieceChoisie = joueurs[0].choisirPiece(piecesDispo);
				break;
			case 1 :
				joueurs[1].placerPiece(pieceChoisie);
				break;
			case 2 : 
				pieceChoisie = joueurs[1].choisirPiece(piecesDispo);
				break;
			case 3 :
				joueurs[0].placerPiece(pieceChoisie);
				break;
		}
		
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
	
	
	/** initJoueur()
	 * Initialise les joueurs
	 */
	public Joueur[] initJoueur(boolean isHuman){
		
		if(isHuman){
			Joueur[] joueurs = new Joueur[2];
			joueurs[0] = new Joueur("Joueur 1");
			joueurs[1] = new Joueur("Joueur 2");
		} else {
			Joueur[] joueurs = new Joueur[2];
			joueurs[0] = new Joueur("Joueur 1");
			joueurs[1] = new Joueur("Ordi");
		}
		
		return joueurs;
	} 
	
	/** initPartie()
	 * permet d'initialiser la partie (???)
	 */
	public void initPartie(){
		//...
	}
	
	/** isOver(int etape)
	 * permet de détecter la fin de la partie
	 * @return : booléen, true si fin de partie, false sinon
	 */ 
	public boolean isOver(int etape){

		int tour = etape%4;

		if( (tour == 1 || tour == 3) && plateau.isPlein() ){
			return true;
		}
		
		if( tour == 1 && joueurs[1].estGagnant() ){
			return true;
		} else if(tour == 3 && joueurs[0].estGagnant() ){
			return true;
		} else {
			return false;
		}
	
	}
	
	
	/** etreEgalite()
	 * permet de détecter s'il y a match nul
	 * @return : booléen, true si match nul, false sinon
	 */
	public boolean etreEgalite(){
		boolean egalite = false;
		return egalite;
	}
	// cette méthode ne sert pas finalement

	/** getEtatFinJeu()
	 * renvoie :
	 * 		1 si joueur 1 gagne
	 * 		2 si joueur 2 gagne
	 * 		0 si egalite	
	 */	
	public int getEtatFinJeu(){
		if(joueurs[0].estGagnant()){
			return 1;
		} else if(joueurs[1].estGagnant()){
			return 2;
		} else {
			return 0;
		}
	}
	

	
}
