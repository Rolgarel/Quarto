import javax.swing.*;
import java.awt.*;


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
    
    public void setImage (String s) {
        image = Tool.getImage(s + ".png");
    }
    
    public boolean isIn (int x, int y) {
        boolean r = false;
        if ((x > this.positionX) && (x < (this.positionX + this.taille)) && (y > this.positionY) && (y < (this.positionY + this.taille))) {
            r = true;
        }
        return r;
    }
    
}
