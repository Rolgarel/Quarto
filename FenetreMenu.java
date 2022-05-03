import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;



public class FenetreMenu extends JFrame implements ActionListener{
	
	private JButton bRegles;
	private JButton bHumain;
	private JButton bOrdi;
	
	public FenetreMenu(){
	
		/* Définition caractéristiques de la fenêtre */
	
		super("Jeu de Quarto : Menu");
		
		int mainHeight = 500;
		int mainWidth = 2*mainHeight;
		int mainX = 100;
		int mainY = 200;
		
		setSize(mainWidth, mainHeight);
		setLocation(mainX, mainY);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Tailles de composants de la fenêtre */
		
			// dimensions communes
		int bWidth = 150 ;
		int bHeight = 40;
		int bSpace = 10;
		
			// dimensions et positions des blocs
		int blocBHeight = 2*bHeight + 3*bSpace; // 110
		int blocBWidth = 3*bWidth; // 450
		int blocBX = mainWidth/4;
		int blocBY = mainHeight/2;
				
			// positions des boutons + bloc des boutons choix de jeu
		int bRegleX = blocBWidth/2 - bWidth/2 ;
		int bRegleY = bSpace;
		
		int blocChoixWidth = 2*bWidth + bSpace;
		int blocChoixHeight = bHeight;
		int blocChoixX = blocBWidth/2 - blocChoixWidth/2 ;
		int blocChoixY = bRegleY + bHeight + bSpace;
		
		int bHumainX = 0;
		int bHumainY = 0;
		int bOrdiX = bHumainX + bWidth + bSpace;
		int bOrdiY = bHumainY;
		
		
			// dimensions et positions du titre du Jeu
		int titreJeuWidth = 721;
		int titreJeuHeight = 201;
		int titreJeuX = mainWidth/2 - titreJeuWidth/2;
		int titreJeuY = blocBY - titreJeuHeight - bSpace;
		
		
		
		
		/* Création des composants */
		
		bRegles = new JButton("Regles");
		bRegles.setBounds(bRegleX, bRegleY, bWidth, bHeight);
		bRegles.addActionListener(this);
		
		bHumain = new JButton("1 contre 1");
		bHumain.setBounds(bHumainX, bHumainY, bWidth, bHeight);
		bHumain.addActionListener(this);
		
		bOrdi = new JButton("1 contre ordi");
		bOrdi.setBounds(bOrdiX, bOrdiY, bWidth, bHeight);
		bOrdi.addActionListener(this);
		

		JLabel titreJeu = new JLabel(new ImageIcon("img_fond/quarto_logo.png"));
		titreJeu.setSize(titreJeuWidth, titreJeuHeight);
		titreJeu.setLocation(titreJeuX, titreJeuY);
		
		
		JLabel fondBlocB = new JLabel(new ImageIcon("img_fond/fond_bloc_boutons.png"));
		fondBlocB.setSize(blocBWidth, blocBHeight);
		fondBlocB.setLocation(0,0);
		
		
		
		
		/* Création des panels */
		
		Color bleuCiel = new Color(200,235,230);
	
		JPanel BlocChoixJeu = new JPanel();
		BlocChoixJeu.setLayout(null);
		BlocChoixJeu.setBounds(blocChoixX, blocChoixY, blocChoixWidth, blocChoixHeight);
		BlocChoixJeu.setBackground(bleuCiel);
		BlocChoixJeu.add(bHumain);
		BlocChoixJeu.add(bOrdi);
		
		
		JPanel BlocBoutons = new JPanel();
		BlocBoutons.setLayout(null);
		BlocBoutons.setBounds(blocBX, blocBY, blocBWidth, blocBHeight);
		BlocBoutons.setBackground(bleuCiel);
		BlocBoutons.add(BlocChoixJeu);
		BlocBoutons.add(fondBlocB);
		BlocBoutons.add(bRegles);
		
		
		JPanel BlocMain = new JPanel();
		BlocMain.setLayout(null);
		BlocMain.setBackground(bleuCiel);
		BlocMain.add(BlocBoutons);
		BlocMain.add(titreJeu);
		BlocMain.setBounds(0, 0, mainWidth, mainHeight);
		
		
		
		/* Rendre visible la fenêtre */
		
		add(BlocMain);
		setVisible(true);
	}
	
	

	/** actionPerformed
	 * Réagit à l'évènement "le bouton a été enfoncé"
	 * @param e action de cliquer sur un bouton
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == bRegles){
			FenetreRegles regles = new FenetreRegles();
		}
		if(e.getSource() == bHumain){
			FenetreJeu JeuHumain = new FenetreJeu(true);
			dispose();
		}
		if(e.getSource() == bOrdi){
			FenetreJeu JeuOrdi = new FenetreJeu(false);
			dispose();
		}
	}

}
