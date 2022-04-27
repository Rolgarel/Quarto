import java.nio.charset.StandardCharsets;

public class Main {
	
	public static void main(String[] args){
		//FenetreMenu menu = new FenetreMenu();
		//FenetreRegles regles = new FenetreRegles();
		FenetreJeu jeu = new FenetreJeu(true);
		
		/*
		Joueur[] joueurs = new Joueur[2];
		joueurs[0] = new Joueur("Joueur 1");
		joueurs[1] = new Joueur("Joueur 2");
		FenetreFinJeu gameOver = new FenetreFinJeu(2, joueurs);
		*/
		
	}
	
	
	// Méthode
	
	public static String encodeUTF8(String text){
		String result = "";
		byte[] textBytes = text.getBytes();
		result = new String(textBytes, StandardCharsets.UTF_8);
		return result;
	}

	

	
}
