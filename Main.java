import java.nio.charset.StandardCharsets;

public class Main {
	
	public static void main(String[] args){
		FenetreMenu menu = new FenetreMenu();
	}
	
	// Méthode
	
	/** encodeUTF8
	 * permet d'afficher les caractères spéciaux
	 * @param text message que l'on veut afficher
	 * @return result message affiché
	 */
	public static String encodeUTF8(String text){
		String result = "";
		byte[] textBytes = text.getBytes();
		result = new String(textBytes, StandardCharsets.UTF_8);
		return result;
	}
}
