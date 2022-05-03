public class JeuQuarto {
	
	// Attributs du jeu :
	
	private Plateau plateau;
	private Joueur[] joueurs;
	private int etatFinJeu = -1;
	
	
	
	// Constructeur 
	
	public JeuQuarto(boolean isHuman){
	
		plateau = new Plateau();  // les pièces sont créés avec le plateau
		joueurs = initJoueurs(isHuman);
		
	}

	
	// Getters
	
	public Plateau getPlateau(){
		return this.plateau;
	}
	
	public Joueur[] getJoueurs(){
		return this.joueurs;
	}
	
	public int getEtatFinJeu(){
		return this.etatFinJeu;
	}
	
	// Setter
	public void setPlateau(Plateau unPlateau){
		this.plateau = unPlateau;
	}
	
	
	// Méthodes
	
	/** initJoueurs
	 * Initialise les joueurs
	 * @param isHumain true si Humain VS Human, false si contre ordi
	 * @return joueurs un tableau de 2 Joueur
	 */
	private Joueur[] initJoueurs(boolean isHuman){
		
		joueurs = new Joueur[2];
		joueurs[0] = new Joueur("Joueur 1",false);
		
		if(isHuman){
			joueurs[1] = new Joueur("Joueur 2",false);
		} else {
            joueurs[1] = new Joueur("Ordi", true);
		}
		
		return joueurs;
	} 
	

	/** isOver
	 * permet de détecter la fin de la partie
	 * @param etape
	 * @return true si fin de partie, false sinon
	 */ 
	public boolean isOver(int etape){

		int tour = etape%4;
		
		if( (tour == 1) && joueurs[1].estGagnant(plateau) ){
			etatFinJeu = 2;
			return true;
		} else if(tour == 3 && joueurs[0].estGagnant(plateau) ){
			etatFinJeu = 1;
			return true;
		} else if(plateau.isGrillePleine()){
			etatFinJeu = 0;
			return true;
		} else {
			return false;
		}

	}

	
}
