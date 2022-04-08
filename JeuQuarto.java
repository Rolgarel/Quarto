public class JeuQuarto {
	
	// Attributs du jeu :
	
	Plateau plateau;
	Joueur[] joueurs;
	
	
	
	// Constructeur 
	
	public JeuQuarto(boolean isHuman){
	
		plateau = new Plateau();  // les pièces sont créés avec le plateau
		joueurs = initJoueurs(isHuman);
			
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
	
	
	/* actionJoueur(int etape)
     * @param : int etape : indique l'action qui doit être faite (mais pas qui doit le faire)
     */
    
	
	
	/* gestionEndGame(int etatPartie)
	 * Affiche dans le terminal l'état de fin de jeu : joueur 1 gagnant, ou joueur 2, ou égalité
	 * @param : int etatPartie : 1 = joueur 1 gagnant, 2 = joueur 2 gagnant, 3 = égalité
	 */
	public void gestionEndGame(int etatPartie) { // dans fentreFinJeu ?
		switch (etatPartie) {
			case 1 :
				System.out.println (joueurs[etatPartie -1].nom + "a gagné.");
				
				break;
			
			case 2 :
				System.out.println (joueurs[etatPartie-1].nom + "a gagné.");
				break;
				
			case 3 :
				System.out.println ("Personne n'a gagné.");
				break;
		}
	}
	
	

	/** isOver(int etape)
	 * permet de détecter la fin de la partie
	 * @return : booléen, true si fin de partie, false sinon
	 */ 
	public boolean isOver(int etape){

		int tour = etape%4;

		if( (tour == 1 || tour == 3) && plateau.isGrillePleine() ){
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
