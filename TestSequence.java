public class TestSequence {
	
	// Attributs :
	Sequence sequenceLigne;
	
	// Constructeur :
	public TestSequence(Piece[] listePieces){
		this.sequenceLigne = new Sequence(listePieces);
	}
	
	// Main :
	public static void main (String[] args) {
		
		Piece[] listePieces1 = {
				new Piece(true, true, true, true, 0),
				new Piece(true, true, false, true, 1),
				new Piece(),
				new Piece()
			};
		
		Piece[] listePieces2 = {
				new Piece(true, true, true, true, 0),
				new Piece(true, true, false, true, 1),
				new Piece(true, true, true, false, 2),
				new Piece(true, true, false, false, 3)
			};
		
		Piece[] listePiecesVide = new Piece[4];
			
		
		TestSequence testSequence1 = new TestSequence(listePieces1);
		testSequence1.testToString();
		testSequence1.testEstPlein_false();
		
		TestSequence testSequence2 = new TestSequence(listePieces2);
		testSequence2.testToString();
		testSequence2.testEstPlein_true();
		
		/*
		// Cas qui ne devrait pas arriver : génère une erreur NullPointerException
		TestSequence testSequenceVide = new TestSequence(listePiecesVide);
		testSequenceVide.testToString();
		testSequenceVide.testEstPlein_false();
		*/
		/*
		testSequence1.testGetNombreEspacesDisponibles(2); 


		Piece[] listePieces3 = {
				new Piece(true, true, true, true, 0),
				new Piece(true, true, true, true, 1),
				new Piece(),
				new Piece()
			};
		TestSequence testSequence3 = new TestSequence(listePieces3);
		testSequence3.testCaracteristiquesDisponibles_True_True();

		Piece[] listePieces4 = {
				new Piece(true, true, true, true, 0),
				new Piece(false, false, false, false, 1),
				new Piece(),
				new Piece()
			};
			
		TestSequence testSequence4 = new TestSequence(listePieces4);
		testSequence4.testCaracteristiquesDisponibles_True_False();
		
		Piece[] listePieces5 = {
				new Piece(false, false, false, false, 0),
				new Piece(false, false, false, false, 1),
				new Piece(),
				new Piece()
			};
		TestSequence testSequence5 = new TestSequence(listePieces5);
		testSequence5.testCaracteristiquesDisponibles_False_False();
		*/
		Piece[] listePieces6 = {
				new Piece(),
				new Piece(false, false, false, false, 0),
				new Piece(false, false, false, false, 1),
				new Piece()
			};
		TestSequence testSequence6 = new TestSequence(listePieces6);
		testSequence6.testGetPremierePiece(1);
		
		Piece[] listePieces7 = {
				new Piece(false, false, false, false, 0),
				new Piece(),
				new Piece(false, false, false, false, 1),
				new Piece()
			};
		TestSequence testSequence7 = new TestSequence(listePieces7);
		testSequence7.testGetPremierePiece(0);
		
		Piece[] listePieces8 = {
				new Piece(),
				new Piece(),
				new Piece(),
				new Piece(false, false, false, false, 1)
			};
		TestSequence testSequence8 = new TestSequence(listePieces8);
		testSequence8.testGetPremierePiece(3);
		
		Piece[] listePieces9 = {
				new Piece(),
				new Piece(),
				new Piece(false, false, false, false, 1),
				new Piece()
			};
		TestSequence testSequence9 = new TestSequence(listePieces9);
		testSequence9.testGetPremierePiece(2);
	}
	
	
	// Tests :
	
	public void testToString(){
		System.out.println("============================================");
		System.out.println("sequenceLigne = " + this.sequenceLigne.toString()); // OK
	}
	
	public void testEstPlein_false(){
		System.out.println("============================================");
		if(this.sequenceLigne.estPlein()){
			System.out.println("Erreur estPlein()");
		} else {
			System.out.println("estPlein() OK"); //OK
		}
	}
	
	public void testEstPlein_true(){
		System.out.println("============================================");
		if(!this.sequenceLigne.estPlein()){
			System.out.println("Erreur estPlein()");
		} else {
			System.out.println("estPlein() OK"); //OK
		}
	}
	
	public void testGetNombreEspacesDisponibles(int nombreAttendu){
		System.out.println("============================================");
		boolean egalite = this.sequenceLigne.getNombreEspacesDisponibles()==nombreAttendu; 
		System.out.println("Nombre d'espaces disponibles = " + nombreAttendu + " : " + egalite);
	}
	
	public void testCaracteristiquesDisponibles_True_True() {
		System.out.println("============================================");
		boolean[] caracteristiquesDisponibles = this.sequenceLigne.getCaracteristiquesDisponibles();
		System.out.println("Caracteristiques_disponibles True_True: Grand: " + caracteristiquesDisponibles[0]);
		System.out.println("Caracteristiques_disponibles True_True: Rond: " + caracteristiquesDisponibles[1]);
		System.out.println("Caracteristiques_disponibles True_True: Couleur: " + caracteristiquesDisponibles[2]);
		System.out.println("Caracteristiques_disponibles True_True: Creux: " + caracteristiquesDisponibles[3]);
	}
	
	public void testCaracteristiquesDisponibles_True_False() {
		System.out.println("============================================");
		boolean[] caracteristiquesDisponibles = this.sequenceLigne.getCaracteristiquesDisponibles();
		System.out.println("Caracteristiques_disponibles True_False: Grand: " + !caracteristiquesDisponibles[0]);
		System.out.println("Caracteristiques_disponibles True_False: Rond: " + !caracteristiquesDisponibles[1]);
		System.out.println("Caracteristiques_disponibles True_False: Couleur: " + !caracteristiquesDisponibles[2]);
		System.out.println("Caracteristiques_disponibles True_False: Creux: " + !caracteristiquesDisponibles[3]);
	}  
	
	public void testCaracteristiquesDisponibles_False_False() {
		System.out.println("============================================");
		boolean[] caracteristiquesDisponibles = this.sequenceLigne.getCaracteristiquesDisponibles();
		System.out.println("Caracteristiques_disponibles False_False: Grand: " + caracteristiquesDisponibles[0]);
		System.out.println("Caracteristiques_disponibles False_False: Rond: " + caracteristiquesDisponibles[1]);
		System.out.println("Caracteristiques_disponibles False_False: Couleur: " + caracteristiquesDisponibles[2]);
		System.out.println("Caracteristiques_disponibles False_False: Creux: " + caracteristiquesDisponibles[3]);
	}
	
	
	
	
	
	public void testGetPremierePiece(int indiceAttendu){
		boolean indicePremierePieceOk = this.sequenceLigne.getPremierePiece() == indiceAttendu;
		System.out.println("Premiere Piece = " + indiceAttendu + " : " + indicePremierePieceOk);
		System.out.println("============================================");
	}
	
	public void testUpdate_caracteristiquesDisponibles(){
		System.out.println("============================================");
	}
	public void testUpdate_sequencePossible(){
		System.out.println("============================================");
	}
}

