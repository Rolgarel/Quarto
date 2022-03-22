
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.Color;

public class ClickPanel extends JPanel {
    
    private int positionX;
    private int positionY;
    private int taille;
    
    //Images
    Toolkit Tool = Toolkit.getDefaultToolkit();
    ImageIcon image;
    JLabel label = new JLabel(image);
    
    public boolean isSelected;
    private boolean isVisible;
    
    public ClickPanel (int x, int y, int t, boolean v) {
        super();
        this.setLayout(null);
        this.positionX = x;
        this.positionY = y;
        this.taille = t;
        this.updatePanel();
        this.setBackground(Color.gray);
        this.setIsVisible(v);
        this.add(label);
        this.setImage("test1");
        
    }
    
    public int getPositionX () {
        return this.positionX;
    }
    
    public int getPositionY () {
        return this.positionY;
    }
    
    public int getTaille () {
        return this.taille;
    }
    
    public void updatePanel () {
        this.setBounds(positionX,positionY,taille,taille);
        this.label.setBounds(0,0,taille,taille);
    }
    
    public void setPositionX (int x) {
        this.positionX = x;
        updatePanel();
    }
    
    public void setPositionY (int y) {
        this.positionY = y;
        updatePanel();
    }
    
    public void setTaille (int t) {
        this.taille = t;
        updatePanel();
    }
    
    public void setImage (String s) {
        if (image == null) {
            image = new ImageIcon();
        }
        image.setImage(Tool.getImage(s + ".png"));
        this.repaint();
    }
    
    public boolean isIn (int x, int y) {
        boolean r = false;
        if ((x > this.positionX) && (x < (this.positionX + this.taille)) && (y > this.positionY) && (y < (this.positionY + this.taille))) {
            r = true;
        }
        return r;
    }
    
    public void updateVisible () {
        if (isVisible == true) {
            this.label.setEnabled(true);
        } else {
            this.label.setEnabled(false);
        }
    }
    
    public void setIsVisible (boolean v) {
        this.isVisible = v;
        this.updateVisible();
    }
    
    public boolean getIsVisible () {
        return this.isVisible;
    }
}
