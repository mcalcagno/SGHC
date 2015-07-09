package uy.com.sghc.gui.frames.components;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

public class ImageDrawer implements Border{

    private   BufferedImage image ;

    public ImageDrawer(BufferedImage image) {
        this.image=image;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//        int x0 = x + (width-image.getWidth())/2;
//        int y0 = y + (height-image.getHeight())/2;
//        g.drawImage(image,width-image.getWidth()-20,height-image.getHeight()-20,null);
        g.drawImage(image,x,y,width,height,null);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    public boolean isBorderOpaque() {
        return true;
    }
}
