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
	
	
	
	// Méthodes
	
	/** initJoueur()
	 * Initialise les joueurs
	 * @param : boolean isHumain : true si Humain VS Human, false si contre ordi
	 * @return : tableau Joueur[2]
	 */
	private Joueur[] initJoueurs(boolean isHuman){
		
		joueurs = new Joueur[2];
		joueurs[0] = new Joueur("Joueur 1");
		
		if(isHuman){
			joueurs[1] = new Joueur("Joueur 2");
		} else {
			joueurs[1] = new Joueur("Ordi");
		}
		
		return joueurs;
	} 
	

	/** isOver(int etape)
	 * permet de détecter la fin de la partie
	 * @return : booléen = true si fin de partie, false sinon
	 */ 
	public boolean isOver(int etape){

		int tour = etape%4;
		
		if( (tour == 1) && joueurs[1].estGagnant(plateau) ){
			System.out.println(joueurs[1].getNom()+ " a gagne : fin du jeu.");
			etatFinJeu = 2;
			return true;
		} else if(tour == 3 && joueurs[0].estGagnant(plateau) ){
			System.out.println(joueurs[0].getNom() + " a gagne : fin du jeu.");
			etatFinJeu = 1;
			return true;
		} else if(plateau.isGrillePleine()){
			System.out.println("Egalité : le plateau est plein");
			etatFinJeu = 0;
			return true;
		} else {
			System.out.println("Le jeu continue.");
			return false;
		}

	}

	
}
