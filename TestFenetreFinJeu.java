


public class TestFenetreFinJeu {
	
	public static void main (String[] args) {
		Plateau p = new Plateau ();
		Joueur j1 = new Joueur ("Bob",p);
		Joueur j2 = new Joueur ("Yvette",p);
		Joueur [] j = {j1,j2};
		
		FenetreFinJeu f = new FenetreFinJeu (1, j);
	}
}

