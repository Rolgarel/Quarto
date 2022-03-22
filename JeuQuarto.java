public class JeuQuarto {

	public static void main(String[] args){
		int etatPartie = startGame();
		endGame(etatPartie);
	}
	
	
	public int startGame(){
		FenetreMenu menu = new FenetreMenu();
		
		Plateau plateau = new Plateau();
		boolean isOver = false;
		int etatPartie = 0;
		
		while(isOver==false){
			isOver = etreFinPartie();
		}
		
		return etatPartie;
	}
	
	public void endGame(int etatPartie) {
		
		final int EGALITE = 3;
		if(etatPartie == EGALITE){
			//....
		} else {
			//.....
		}
	}
	
	// Dans la classe main : récupérer tour du jeu
	/** initJoueurSuivant()
	 * permet de lancer le tour de l'autre joueur
	 * @return : entier = indice du joueur dans le tableau de joueur
	 */
	public int initJoueurSuivant(int indiceJoueurCourant){
		int indiceJoueur = 0;
		if(indiceJoueurCourant == 0){
			return 1;
		} else {
			return 0;
		}
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
	public boolean etreFinPartie(){
		return isOver;
	}
	
	/** estGagnant()
	 * permet de détecter si le joueur a gagné
	 * @return : booléen, true si le joueur a gagné, false sinon
	 */
	public boolean estGagnant(){
		return gagne;
	}
	
	/** etreEgalite()
	 * permet de détecter s'il y a match nul
	 * @return : booléen, true si match nul, false sinon
	 */
	public boolean etreEgalite(){
		return egalite;
	}
	
}
