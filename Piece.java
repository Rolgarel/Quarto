/* pour créer dossier git
git bash here
terminal : git clone
gitlab -> quarto -> bouton clone -> copier https://...
terminal : git clone https://...
*/


public class Piece {
	
	// attributs
	private boolean grand;  
	private boolean rond;	
	private boolean couleur; 
	private boolean creux;
	private boolean estPlace;
	
	/** formes des pièces
	 * le nom donne les caractéristiques de pièces
	 * 1ere lettre : g = grand, p = petit
	 * 2eme lettre : r = rond, c = carré
	 * 3eme lettre : a = (vert), b = (bleu)
	 * 4eme lettre : c = creux, p = plein
	 **/
	
	
	// constructeur
	public Piece(boolean taille, boolean forme, boolean couleur, boolean creux){
		grand = taille;
		rond = forme;
		this.couleur = couleur;
		this.creux = creux;
		estPlace = false;
		
	};
	
	
	// getters
	
	public boolean estGrand(){
		return grand;
	}

	public boolean estRond(){
		return rond;
	}
	
	public boolean getCouleur(){
		return couleur;
	}
	
	public boolean estCreux(){
		return creux;
	}
	
	public boolean estPlace(){
		return estPlace;
	}
	
	// setter
	
	public void setPlace(boolean estPlace){
		this.estPlace = estPlace;
	}
	
	
	// Méthodes
	
	
	
	
	
	
	
	

}
