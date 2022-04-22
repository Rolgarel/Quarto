public class TestBot {
	
	public static void main (String[] args) {
		Plateau plateau = new Plateau();
        int pieceDepart = 0;
        BotAI bot = new BotAI(plateau, pieceDepart);
        int[] positions = {bot.getPositionSolution(), bot.getIndicePieceSolution()};
        dispIntTab(positions);
        System.out.println();
        placerPiece(plateau, positions[0], pieceDepart);
        int i = 0;
        while ((estGagnant(plateau) == false) && (plateau.isGrillePleine() == false)) {
        //for (int i = 0; i < 14; i++) { //erreur position dernière pièce
            System.out.println((i+1) + " : ");
            i++;
            int pieceCourrante = positions[1];
            bot = new BotAI(plateau, pieceCourrante);
            positions[0] = bot.getPositionSolution();
            positions[1] = bot.getIndicePieceSolution();
            dispIntTab(positions);
            System.out.println();
            placerPiece(plateau, positions[0], pieceCourrante);
            System.out.println("***************************************");
            if (estGagnant(plateau) == true) {
                System.out.println("Gagnant");
            } else if (plateau.isGrillePleine() == true) {
                System.out.println("Plein");
            }
        }
        //dispIntTab(bot.possiblilityGrille);
        //dispIntTab(bot.possiblilityListePieces);
        dispGrille(plateau.getGrille());
    }
    
    public static void placerPiece(Plateau plateau, int positionGrille, int positionListe) {
        Piece piece = plateau.getListePieces()[positionListe];
        plateau.getGrille()[positionGrille] = piece;
        piece.setPlace(true);
	}
    
    public static boolean estGagnant(Plateau plateau){
		
		int i=0;
		
		//test pour les lignes
		// i = le numére de ligne, il y en a 4 numérotées de 0 à 3 inclus
		while (plateau.ligneColonneGagnante(i, "l")==false && i<4){ // ligneColonneGagnante() OK
			i++;
		}
		if(i!=4){
			return true;
		} else {
			// si i=4 on fait un autre test
			i = 0;
		}
		
		//test pour les colonnes
		// i = le numéro de colonne, il y en a 4  numérotées de 0 à 3 inclus
		while (plateau.ligneColonneGagnante(i, "c")==false && i<4){  // ligneColonneGagnante() OK
			i++;
		}
		if(i<4){
			return true;
        }
		
		//test pour les diagonales
		boolean penteCroiss = plateau.alignementGagnantD(true);
		boolean penteDecroiss = plateau.alignementGagnantD(false);
		if(penteCroiss || penteDecroiss){
			return true;
		}
		
		// Si tous les tests sont négatifs :
		return false;
	}
    
    public static void dispGrille (Piece[] grille) {
        for (int i = 0; i < grille.length; i++) {
            if (grille[i].isNull() == false) {
                System.out.print(grille[i].toString() + "   ");
            } else {
                System.out.print("xxxx   ");
            }
            if ((i%4) == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
    
    public static void dispBooleanTab (boolean[] tab) {
        String s = "";
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == true) {
                s = s + "true  ";
            } else {
                s = s + "false ";
            }
        }
        System.out.println(s);
    }
    
    public static void dispIntTab (int[] tab) {
        String s = "";
        for (int i = 0; i < tab.length; i++) {
            s = s + tab[i] + "  ";
        }
        System.out.println(s);
    }
}

