public class TestFenetreJeu {
	
	public static void main(String[] args) {
		//FenetreJeu f = new FenetreJeu(true);
		Plateau plateauRef = new Plateau();
		Piece[] uneListePieces = {
			plateauRef.getListePieces()[0],
			plateauRef.getListePieces()[1],
			plateauRef.getListePieces()[5],
			plateauRef.getListePieces()[6],
			
			plateauRef.getListePieces()[9],
			plateauRef.getListePieces()[10],
			plateauRef.getListePieces()[15],
		};
		int[] uneListeCases = {9, 1, 5, 0, 8, 13, 15}; 
		FenetreJeuTest testJeu = new FenetreJeuTest(uneListePieces, uneListeCases);
	}
}
