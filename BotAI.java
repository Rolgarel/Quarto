import java.util.ArrayList;

public class BotAI {
    
    //Attributs
    private Plateau currentState; //plateau correspondant à l'état actuel de la partie
    private int pieceDonnee; //indice de la pièce donnée par l'autre joueur
    private ArrayList<Piece> piecesDisponibles; //liste des pièces n'ayant pas encore été jouées
    private ArrayList<Integer> positionsDisponibles; //liste des positions de la grille qui sont vides
    private Sequence[] sL; //liste des séquençages de chaque ligne de la grille du plateau donné
    private Sequence[] sC; //liste des séquençages de chaque colone de la grille du plateau donné
    private Sequence[] sD; //liste des séquençages de chaque diagonale de la grille du plateau donné
    private int positionSolution; //position de la grille retenue pour la solution
    private int indicePieceSolution; //indice de la pièce retenue pour la solution
    private Piece pieceSolution; //pièce retenue pour la solution
    
    //Constructeur
	public BotAI(Plateau plateau, int indicePiece) {
		currentState = plateau;
        pieceDonnee = indicePiece;
        piecesDisponibles = recherchePiecesDisponibles();
        piecesDisponibles.remove(currentState.getListePieces()[pieceDonnee]);
        positionsDisponibles = recherchePositionsDisponibles();
        sL = sequencageLigne();
        sC = sequencageColone();
        sD = sequencageDiagonale();
        int[] solution = rechercheSolution();
        positionSolution = solution[0];
        indicePieceSolution = solution[1];
        if (indicePieceSolution > -1) {
            pieceSolution = currentState.getListePieces()[indicePieceSolution];
        } else {
            pieceSolution = new Piece();
        }
	}
    
    // Getters
    public int getPositionSolution() {
        return this.positionSolution;
    }
    
    public int getIndicePieceSolution() {
        return this.indicePieceSolution;
    }
    
    public Piece getPieceSolution() {
        return this.pieceSolution;
    }
    
    
    //Methodes
    
    /**recherchePiecesDisponibles()
     * Donne la liste des pièces disponibles pour le plateau donné
     * @return: ArrayList<Piece> res, la liste des pièces disponibles
     */
    private ArrayList<Piece> recherchePiecesDisponibles() {
        ArrayList<Piece> res = new ArrayList<Piece>();
        Piece[] liste = currentState.getListePieces();
        for (int i = 0; i < liste.length; i++) {
            if((liste[i].estPlace() == false) && (i != pieceDonnee)) {
                res.add(liste[i]);
            }
        }
        return res;
    }
    
    /**recherchePositionsDisponibles()
     * Donne la liste des positions sur la grille disponibles pour le plateau donné
     * @return: ArrayList<Integer> res, la liste des positions disponibles
     */
    private ArrayList<Integer> recherchePositionsDisponibles() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Piece[] grille = currentState.getGrille();
        for (int i = 0; i < grille.length; i++) {
            if(grille[i].isNull() == true) {
                res.add(i);
            }
        }
        return res;
    }
    
    /**sequencageLigne()
     * Donne la liste des séquences de chaque ligne de la grille pour le plateau donné
     * @return: Sequence[] res, la liste séquences des lignes
     */
    private Sequence[] sequencageLigne() {
        Sequence[] res = new Sequence[4];
        Piece[] grille = this.currentState.getGrille();
        for (int i = 0; i < 4; i++) {
            Piece[] s = new Piece[4];
            for (int j = 0; j < 4; j++) {
                s[j] = grille[j + 4*i];
            }
            res[i] = new Sequence(s);
        }
        
        return res;
    }
    
    /**sequencageColone()
     * Donne la liste des séquences de chaque colone de la grille pour le plateau donné
     * @return: Sequence[] res, la liste séquences des colones
     */
    private Sequence[] sequencageColone() {
        Sequence[] res = new Sequence[4];
        Piece[] grille = this.currentState.getGrille();
        for (int i = 0; i < 4; i++) {
            Piece[] s = new Piece[4];
            for (int j = 0; j < 4; j++) {
                s[j] = grille[i + 4*j];
            }
            res[i] = new Sequence(s);
        }
        
        return res;
    }
    
    /**sequencageDiagonale()
     * Donne la liste des séquences de chaque diagonale de la grille pour le plateau donné
     * @return: Sequence[] res, la liste séquences des diagonales
     */
    private Sequence[] sequencageDiagonale() {
        Sequence[] res = new Sequence[2];
        Piece[] grille = this.currentState.getGrille();
        Piece[] s = new Piece[4];
        for (int i = 0; i < 4; i++) {
            s[i] = grille[5*i];
        }
        res[0] = new Sequence(s);
        s = new Piece[4];
        for (int i = 0; i < 4; i++) {
            s[i] = grille[12 - 3*i];
        }
        res[1] = new Sequence(s);
        return res;
    }
    
    /**correspondance()
     * Donne la ligne, la colone et la diagonale correspondante à la case d'indice donné
     * @param: int indice, indice de la case voulue
     * @return: int[] res, ligne, colone et diagonale correspondantes
     */
    private int[] correspondance(int indice) {
        int ligne = indice/4;
        int colone = indice%4;
        int diagonale = -1;
        if ((indice%5) == 0) {
            diagonale = 0;
        } else if ((indice % 3) == 0) {
            diagonale = 1;
        }
        int[] res = {ligne, colone, diagonale};
        return res;
    }
    
    /**positionGagnante()
     * Détermine si la position donné permet de gangner la partie avec la pièce donnée
     * @param: int indice, indice de la position considérée
     * @return: true si la position est gagnante, false sinon
     */
    private boolean positionGagnante (int indice) {
        boolean res = false;
        Piece piece = currentState.getListePieces()[pieceDonnee];
        int[] c = correspondance(indice);
        if ((c[0] != -1) && (sL[c[0]].getSequencePossible() == true) && (sL[c[0]].getNombreEspacesDisponibles() == 1) && (sL[c[0]].estCompatible(piece))) {
            res = true;
        } else if ((c[1] != -1) && (sC[c[1]].getSequencePossible() == true) && (sC[c[1]].getNombreEspacesDisponibles() == 1) && (sC[c[1]].estCompatible(piece))) {
            res = true;
        } else if ((c[2] != -1) && (sD[c[2]].getSequencePossible() == true) && (sD[c[2]].getNombreEspacesDisponibles() == 1) && (sD[c[2]].estCompatible(piece))) {
            res = true;
        }
        return res;
    }
    
    /**positionGagnante()
     * Détermine si la position donné permet de gangner la partie avec une pièce donnée
     * @param: int indice, indice de la position considérée
     *         int indicePiece, l'indice de la pièce considérée
     * @return: true si la position est gagnante, false sinon
     */
    private boolean positionGagnante (int indice, int indicePiece) {
        boolean res = false;
        Piece piece = currentState.getListePieces()[indicePiece];
        int[] c = correspondance(indice);
        if ((c[0] != -1) && (sL[c[0]].getSequencePossible() == true) && (sL[c[0]].getNombreEspacesDisponibles() == 1) && (sL[c[0]].estCompatible(piece))) {
            res = true;
        } else if ((c[1] != -1) && (sC[c[1]].getSequencePossible() == true) && (sC[c[1]].getNombreEspacesDisponibles() == 1) && (sC[c[1]].estCompatible(piece))) {
            res = true;
        } else if ((c[2] != -1) && (sD[c[2]].getSequencePossible() == true) && (sD[c[2]].getNombreEspacesDisponibles() == 1) && (sD[c[2]].estCompatible(piece))) {
            res = true;
        }
        return res;
    }
    
    /**rechercheCoupGangant()
     * Recherche les positions sur la grille permettant un coup gagnant
     * @return: ArrayList<Integer> positionsRetenues, liste des positions correspondantes à un coup gagnant
     */
    private ArrayList<Integer> rechercheCoupGangant() {
        ArrayList<Integer> positionsRetenues = new ArrayList<Integer>();
        for (int i = 0; i < positionsDisponibles.size(); i++) {
            int indice = positionsDisponibles.get(i);
            if (positionGagnante(indice) == true) {
                positionsRetenues.add(indice);
            }
        }
        return positionsRetenues;
    }
    
    /**rechercheCoupGangant()
     * Recherche les positions sur la grille permettant un coup gagnant pour une pièce donnée
     * @param: int indicePiece, l'indice de la pièce considérée
     * @return: ArrayList<Integer> positionsRetenues, liste des positions correspondantes à un coup gagnant
     */
    private ArrayList<Integer> rechercheCoupGangant(int indicePiece) {
        ArrayList<Integer> positionsRetenues = new ArrayList<Integer>();
        for (int i = 0; i < positionsDisponibles.size(); i++) {
            int indice = positionsDisponibles.get(i);
            if (positionGagnante(indice, indicePiece) == true) {
                positionsRetenues.add(indice);
            }
        }
        return positionsRetenues;
    }
    
    /**randomSelect()
     * Donne l'indice d'un élément aléatoire d'un ensemble de taille donnée
     * @param: int size, taille de l'ensemble de départ
     * @return: int res, indice d'un élément de l'ensemble
     */
    private int randomSelect(int size) {
        int res = (int)(size*Math.random());
        return res;
    }
    
    /**addInSequence()
     * Ajoute une pièce au séquençage comme si elle était à la position donnée
     * @param: Piece piece, une pièce donnée
     *         int indice, l'indice d'une position donnée
     */
    private void addInSequence(Piece piece, int indice) {
        int[] c = correspondance(indice);
        sL[c[0]].add(piece);
        sC[c[1]].add(piece);
        if (c[2] != -1) {
            sD[c[2]].add(piece);
        }
    }
    
    /**removeFromSequence()
     * Retire une pièce du séquençage
     * @param: Piece piece, une pièce donnée
     */
    private void removeFromSequence(Piece piece) {
        for (int i = 0; i < 4; i++) {
            sL[i].remove(piece);
            sC[i].remove(piece);
        }
        sD[0].remove(piece);
        sD[1].remove(piece);
    }
    
    /**recherchePiecesDonnables()
     * Recherche les pièces pouvant être données sans perdre pour une position donnée de la pièce reçue
     * @param: int indice, indice d'une position donnée
     * @return: ArrayList<Piece> res, la liste des pièces donnables
     */
    private ArrayList<Piece> recherchePiecesDonnables(int indice) {
        ArrayList<Piece> res = new ArrayList<Piece>();
        Piece piece = currentState.getListePieces()[this.pieceDonnee];
        addInSequence(piece, indice);
        for (int i = 0; i < piecesDisponibles.size(); i++) {
            Piece p = piecesDisponibles.get(i);
            int indicePiece = p.getPlace();
            if (rechercheCoupGangant(indicePiece).isEmpty() == true) {
                res.add(p);
            }
        }
        removeFromSequence(piece);
        return res;
    }
    
    /**recherchePositionsFavorables()
     * Recherche les positions possibles n'entrainant pas une défaite inéluctable
     * @return: ArrayList<Integer> res, la liste des positions favorables
     */
    private ArrayList<Integer> recherchePositionsFavorables() {
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayList[] bilan = new ArrayList[positionsDisponibles.size()];
        for (int i = 0; i < bilan.length; i++) {
            bilan[i] = recherchePiecesDonnables(positionsDisponibles.get(i));
        }
        for(int i = 0; i < bilan.length; i++) {
            if (bilan[i].isEmpty() == false) {
                res.add(positionsDisponibles.get(i));
            }
        }
        return res;
    }
    
    /**recherchePositionsOptimales()
     * Recherche les positions imposant le plus de risque à l'adversaire
     * parmis une liste de positions données
     * @param: ArrayList<Integer> positions, une liste de positions
     * @return: ArrayList<Integer> res, la liste des positions favorables
     */
    private ArrayList<Integer> recherchePositionsOptimales(ArrayList<Integer> positions) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int optimum = 2000;
        ArrayList[] bilan = new ArrayList[positions.size()];
        for (int i = 0; i < bilan.length; i++) {
            bilan[i] = recherchePiecesDonnables(positions.get(i));
            if (optimum > bilan[i].size())  {
                optimum = bilan[i].size();
            }
        }
        for (int i = 0; i < bilan.length; i++) {
            if (optimum == bilan[i].size()) {
                res.add(positions.get(i));
            }
        }
        return res;
    }
    
    /**rechercheSolution()
     * Cherche un couple de solutions afin de maximiser les chances de
     * gagner la partie et de minimiser les pertes
     * @return: int[] res, l'indice de la case sélectionnée et de la pièce sélectionnée
     */
    private int[] rechercheSolution() {
        int[] res = {-1, -1};
        
        //Selection de la position choisie
        ArrayList<Integer> coupGagnant = rechercheCoupGangant();
        if (coupGagnant.isEmpty() == false) {
            res[0] = coupGagnant.get(randomSelect(coupGagnant.size()));
        } else {
            ArrayList<Integer> coupsFavorables = recherchePositionsFavorables();
            if (coupsFavorables.isEmpty() == false) {
                ArrayList<Integer> coupsOptimaux = recherchePositionsOptimales(coupsFavorables);
                if (coupsOptimaux.isEmpty() == false) {
                    res[0] = coupsOptimaux.get(randomSelect(coupsOptimaux.size()));
                } else {
                    res[0] = coupsFavorables.get(randomSelect(coupsFavorables.size()));
                }
            }
        }
        
        //Selection de la pièce à donner
        if ((res[0] >= 0) && (coupGagnant.isEmpty() == true)) {
            ArrayList<Piece> piecesDonnables = recherchePiecesDonnables(res[0]);
            res[1] = piecesDonnables.get(randomSelect(piecesDonnables.size())).getPlace();//
        }
        
        //Au cas où aucune solution n'est trouvée
        if ((res[0] < 0) && (positionsDisponibles.isEmpty() == false)) {
            res[0] = positionsDisponibles.get(randomSelect(positionsDisponibles.size()));
        }
        if ((res[1] < 0) && (piecesDisponibles.isEmpty() == false)) {
            res[1] = piecesDisponibles.get(randomSelect(piecesDisponibles.size())).getPlace();
        }
        
        return res;
    }
}

