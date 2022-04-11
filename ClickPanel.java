import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ClickPanel {
    
    public int positionX;
    public int positionY;
    public int taille;
    
    //Images
    Toolkit Tool = Toolkit.getDefaultToolkit();
    Image image;
    
    public boolean isSelected;
    
    public ClickPanel (int x, int y, int t) {
        this.positionX = x;
        this.positionY = y;
        this.taille = t;    
    }
    
    
    // Méthodes
    
    /* setImage()
     * MAJ l'image du ClickPanel à partir de deux images
     * (en l'occurence, un fond et une pièce)
     * @param : 2 String = nom des images PNG sans leur extension
     */
    public void setImage (String nomImg1, String nomImg2) {
        BufferedImage img1;
        BufferedImage img2;
        BufferedImage combinedImg;
        try{
			img1 = ImageIO.read(getClass().getClassLoader().getResource(nomImg1 + ".png"));
			img2 = ImageIO.read(getClass().getClassLoader().getResource(nomImg2 + ".png"));
			combinedImg = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = combinedImg.createGraphics();
			g.drawImage(img1, 0, 0, null);
			g.drawImage(img2, 0, 0, null);
			g.dispose();
			this.image = combinedImg;			
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/* setImage()
	 * MAJ l'image du ClickPanel à partir d'une image
	 * (en l'occurence, un fond)
	 * @param : String = nom de l'image PNG sans son extension
	 */
	public void setImage(String img){
		this.image = Tool.getImage(img + ".png");
	}
    
    
    /* isIn
     * @param : 2 int pour les coordonnées du clic sur la fenêtre
     * @return : boolean : true si dans le ClickPanel, false sinon
     */ 
    public boolean isIn (int x, int y) {
        boolean r = false;
        if ((x > this.positionX) && (x < (this.positionX + this.taille)) && (y > this.positionY) && (y < (this.positionY + this.taille))) {
            r = true;
        }
        return r;
    }
    
}
