public class Piece {
	
	// Attributs
	private boolean grand;  
	private boolean rond;	
	private boolean couleur; 
	private boolean creux;
	public boolean isNull;
	public boolean estPlace;
	public String codeImage;
	public int place;
	
	
	// Constructeur
	public Piece(boolean taille, boolean forme, boolean couleur, boolean creux, int place){
		this.grand = taille;
		this.rond = forme;
		this.couleur = couleur;
		this.creux = creux;
		this.estPlace = false;
		this.codeImage = this.trouverImage();
		this.place = place;
		this.isNull =  false;
	}
	
	public Piece(){
		isNull = true;
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
	private String trouverImage (){
		String taille = "";
		String forme = "";
		String couleur = "";
		String remplissage = "";
		if (this.grand){
			taille += "g";
		}else{
			taille += "p";
		}
		if (this.rond){
			forme += "r";
		}else{
			forme += "c";
		}
		if (this.couleur){
			couleur+= "a";
		}else{
			couleur += "b";
		}
		if (this.creux){
			remplissage += "c";
		}else{
			remplissage += "p";
		}
		String code = taille + forme + couleur + remplissage +".png";
		
		return code;
	}
	
	
	
	
	
	
	
	
	

}
