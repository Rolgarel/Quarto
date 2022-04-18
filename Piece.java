/* STATUT :
 * Variables toutes privées
 */


public class Piece {
	
	// Attributs
	private boolean grand;  
	private boolean rond;	
	private boolean couleur; 
	private boolean creux;
	private boolean pieceNull;
	private boolean enPlace;
	private int place;
	
	
	// Constructeur
	public Piece(boolean taille, boolean forme, boolean couleur, boolean creux, int place){
		this.grand = taille;
		this.rond = forme;
		this.couleur = couleur;
		this.creux = creux;
		this.enPlace = false;
		this.place = place;
		this.pieceNull =  false;
	}
	
	public Piece(){
		pieceNull = true;
	}
	
	// Getters
	public boolean isNull(){
		return this.pieceNull;
	}
	
	public int getPlace(){
		return this.place;
	}
	
	public boolean estPlace(){
		return this.enPlace;
	}
	
	
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
	
	
	// setter
	
	public void setPlace(boolean estPlace){
		this.enPlace = estPlace;
	}
	
	/** toString()
	 * donne le nom de la pièce
	 * le nom = 4 lettres qui donnent ses caractéristiques :
	 * 1ere lettre : g = grand, p = petit
	 * 2eme lettre : r = rond, c = carré
	 * 3eme lettre : a = (vert), b = (bleu)
	 * 4eme lettre : c = creux, p = plein
	 */
	public String toString(){
		
		String name = "";
		name += this.grand ? "g" : "p";
		name += this.rond ? "r" : "c";
		name += this.couleur ? "a" : "b";
		name += this.creux ? "c" : "p";

		return name;
	}
	
	

}
