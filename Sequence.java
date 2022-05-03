public class Sequence {
	
    //Attributs
    private Piece[] sequence; //séquence de pièces donnée
    private boolean plein; //true si la séquence est complète, false sinon
    private boolean[] caracteristiquesDisponibles; //liste des disponiblilités des caractéristiques dans l'odre donné dans Piece, true si disponible, false sinon
    private boolean sequencePossible; //true si une victoire est possible sur cette ligne, false sinon
    private int nombreEspacesDisponibles; //nombre de pièces manquantes dans la séquence
    private int premierePiece; //indice de la première pièce de la séquence
    
    //Constructeur
	public Sequence(Piece[] s) {
        this.sequence = s;
        this.update();
	}
    
    // Getters
    public boolean estPlein() {
        return this.plein;
    }
    
    public int getNombreEspacesDisponibles() {
        return this.nombreEspacesDisponibles;
    }
    
    public boolean[] getCaracteristiquesDisponibles() {
        boolean[] res = new boolean[this.caracteristiquesDisponibles.length];
        for (int i = 0; i < this.caracteristiquesDisponibles.length; i++) {
            res[i] = this.caracteristiquesDisponibles[i];
        }
        return res;
    }
    
    public boolean getSequencePossible() {
        return sequencePossible;
    }
    
    public Piece[] getSequence() {
        return this.sequence;
    }
    
    public int getPremierePiece() {
        return premierePiece;
    }
    
    
    //Methodes
    
    /**update()
     * met à jour les variables de l'objet :
     * 	1) nombreEspacesDisponibles
     * 	2) plein
     * 	3) premierePiece
     * 	4) caracteristiquesDisponibles
     * 	5) sequencePossible
     */
    private void update() {
        nombreEspacesDisponibles = rechercheEspace();
        if (nombreEspacesDisponibles == 0) {
            plein = true;
        } else {
            plein = false;
        }
        if (plein == true) {
            premierePiece = 0;
            boolean[] b = {false, false, false, false};
            caracteristiquesDisponibles = b;
            sequencePossible = false;
        } else if (nombreEspacesDisponibles == sequence.length || nombreEspacesDisponibles == (sequence.length - 1)) {
            premierePiece = -1;
            boolean[] b = {true, true, true, true};
            sequencePossible = true;
            caracteristiquesDisponibles = b;
        } else {
            premierePiece = trouverPremierePiece();
            caracteristiquesDisponibles = rechercheCaracteristiques();
            sequencePossible = false;
            for (int i = 0; i < caracteristiquesDisponibles.length; i++) {
                if (caracteristiquesDisponibles[i] == true) {
                    sequencePossible = true;
                }
            }
        }
    }
    
    /**rechercheEspace()
     * Compte le nombre d'espaces disponibles dans la séquence donnée
     * @return: int res, le nombre d'espaces libres de la séquence
     */
    private int rechercheEspace() {
        int res = 0;
        for (int i = 0; i < this.sequence.length; i++) {
            if (this.sequence[i].isNull() == true) {
                res++;
            }
        }
        return res;
    }
    
    /**trouverPremierePiece()
     * Recherche l'indice de la première pièce non nulle dans la séquence donnée
     * @return: int res, l'indice de la première pièce non nulle
     */
    private int trouverPremierePiece() {
        int res = 100;
        for (int i = 0; i < this.sequence.length; i++) {
            if ((this.sequence[i].isNull() == false) && (i < res)) {
                res = i;
            }
        }
        return res;
    }
    
    
    /**rechercheCaracteristiques()
     * Recherche la disponiblité des caractéristiques dans la séquence donnée
     * afin de pouvoir y alligner 4 pièces avec une caractéristique en commun
     * @return: boolean[] res, la liste des disponiblités
     *          des caractéristiques dans la séquence
     */
    private boolean[] rechercheCaracteristiques() {
        boolean[] res = {true, true, true, true};
        for(int i = premierePiece + 1; i < this.sequence.length; i++) {
            if (this.sequence[i].isNull() == false) {
                if (this.sequence[premierePiece].estGrand() != this.sequence[i].estGrand()) {
                    res[0]  = false;
                }
                if (this.sequence[premierePiece].estRond() != this.sequence[i].estRond()) {
                    res[1]  = false;
                }
                if (this.sequence[premierePiece].getCouleur() != this.sequence[i].getCouleur()) {
                    res[2]  = false;
                }
                if (this.sequence[premierePiece].estCreux() != this.sequence[i].estCreux()) {
                    res[3]  = false;
                }
            }
        }
        return res;
    }
    
    /**estCompatible()
     * Recherche si la pièce donnée est compatible avec la séquence
     * afin de pouvoir y alligner 4 pièces avec une caractéristique en commun
     * @param: Piece p, une pièce donnée
     * @return: true si la pièce est compatible, false sinon
     */
    public boolean estCompatible(Piece p) {
        boolean res = false;
        if ((this.plein == false) && (p.isNull() == false)) {
            boolean[] compatibilite = {false, false, false, false};
            if (this.sequence[premierePiece].estGrand() == p.estGrand()) {
                compatibilite[0]  = true;
            }
            if (this.sequence[premierePiece].estRond() == p.estRond()) {
                compatibilite[1]  = true;
            }
            if (this.sequence[premierePiece].getCouleur() == p.getCouleur()) {
                compatibilite[2]  = true;
            }
            if (this.sequence[premierePiece].estCreux() == p.estCreux()) {
                compatibilite[3]  = true;
            }
            for (int i = 0; i < this.caracteristiquesDisponibles.length; i++) {
                if ((caracteristiquesDisponibles[i] == true) && (compatibilite[i] == true)) {
                    res = true;
                }
            }
        }
        return res;
    }
    
    /** toString()
	 * donne le nom des pièces composant la séquence
	 * le nom = 4 lettres qui donnent ses caractéristiques :
	 * 1ere lettre : g = grand, p = petit
	 * 2eme lettre : r = rond, c = carré
	 * 3eme lettre : a = rose, b = jaune
	 * 4eme lettre : c = creux, p = plein
     * si la pièce est une pièce nulle alors son nom est xxxx
	 */
    public String toString() {
        String s = "";
        for (int i = 0; i < this.sequence.length; i++) {
            if (sequence[i].isNull() == true) {
                s = s + "xxxx  ";
            } else {
                s = s + sequence[i].toString() + "  ";
            }
        }
        return s;
    }
    
    /**add()
     * Ajoute une pièce donnée dans le premier emplacement disponible si possible
     * @param: Piece p, une pièce donnée
     */
    public void add(Piece piece) {
        if (this.nombreEspacesDisponibles > 0) {
            boolean elementPlace = false;
            for (int i = 0; i < sequence.length; i++) {
                if ((elementPlace == false) && (sequence[i].isNull() == true)) {
                    sequence[i] = piece;
                    elementPlace = true;
                }
            }
            this.update();
        }
    }
    
    /**compare()
     * Compare 2 pièces données et regarde si elles sont non nulles
     * @param: Piece p1, une pièce donnée
     *         Piece p2, une autre pièce donnée
     * @return: true si les deux pièces sont non nulles et ont le mêmes caractéristiques, false sinon
     */
    private boolean compare(Piece p1, Piece p2) {
        boolean res = true;
        if ((p1.isNull() == true) ||  (p2.isNull() == true)) {
            res = false;
        } else if (p1.estGrand() != p2.estGrand()) {
            res = false;
        } else if (p1.estRond() != p2.estRond()) {
            res = false;
        } else if (p1.getCouleur() != p2.getCouleur()) {
            res = false;
        } else if (p1.estCreux() != p2.estCreux()) {
            res = false;
        }
        return res;
    }
    
    /**remove()
     * Retire une pièce donnée dans le premier emplacement où elle est présente si possible
     * @param: Piece p, une pièce donnée
     */
    public void remove(Piece piece) {
        boolean elementRetire = false;
        for (int i = 0; i < sequence.length; i++) {
            if ((elementRetire == false) && (compare(piece, sequence[i]))) {
                sequence[i] = new Piece();
                elementRetire = true;
            }
        }
        this.update();
    }
}

