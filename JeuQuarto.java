public class JeuQuarto {
	
	// Attributs du jeu :
	
	Plateau plateau;
	Joueur[] joueurs;
	Plateau piecesDispo;
	
	
	
	
	// Constructeur 
	
	public JeuQuarto(boolean isHuman){
	
		plateau = new Plateau(false);		// les pièces sont créés avec le plateau
		piecesDispo = new Plateau(true);
		joueurs = initJoueurs(isHuman);
			
	}
	
	
	// Méthodes
	
	/* actionJoueur(int etape)
     * @param : int etape : indique l'action qui doit être faite (mais pas qui doit le faire)
     */
    public void actionJoueur(int etape){
 
		int tour = etape%4;
		switch(tour){
			case 0 :
				joueurs[0].choisirPiece(piecesDispo);
				break;
			case 1 :
				joueurs[1].placerPiece(joueurs[0].pieceChoisie, this.plateau.casesOccupees);
				break;
			case 2 : 
				joueurs[1].choisirPiece(piecesDispo);
				break;
			case 3 :
				joueurs[0].placerPiece(joueurs[1].pieceChoisie, this.plateau.casesOccupees);
				break;
		}
		
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
		
		if( (tour == 1) && joueurs[1].estGagnant() ){
			return true;
		} else if(tour == 3 && joueurs[0].estGagnant() ){
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	
	
	// Méthodes
	
	/** initJoueur()
	 * Initialise les joueurs
	 * @param : boolean isHumain : true si Humain VS Human, false si contre ordi
	 * @return : tableau Joueur[2]
	 */
	public Joueur[] initJoueurs(boolean isHuman){
		
		if(isHuman){
			joueurs = new Joueur[2];
			joueurs[0] = new Joueur("Joueur 1");
			joueurs[1] = new Joueur("Joueur 2");
		} else {
			joueurs = new Joueur[2];
			joueurs[0] = new Joueur("Joueur 1");
			joueurs[1] = new Joueur("Ordi");
		}
		
		return joueurs;
	} 
	

	/** getEtatFinJeu()
	 * renvoie :
	 * 		1 si joueur 1 gagne
	 * 		2 si joueur 2 gagne
	 * 		0 si egalite	
	 */	
	 
	// Joueur.estGagant() à implémenter
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
