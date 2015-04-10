/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
/**
 *
 * @author Hassan
 */
public class Images {
        static ImageIcon exit = new ImageIcon("images/menubar/quit.png");
        static ImageIcon close = new ImageIcon("images/menubar/close.png");
        static ImageIcon exitSmall = getScaledImageIcon(exit, 15, 15);
        static ImageIcon _new = new ImageIcon("images/menubar/new.png");
        static ImageIcon open = new ImageIcon("images/menubar/open.png");
        static ImageIcon save = new ImageIcon("images/menubar/save.png");
        
        static ImageIcon play = new ImageIcon("images/sim/play.png");
        static ImageIcon pause = new ImageIcon("images/sim/pause.png");
        static ImageIcon stop = new ImageIcon("images/sim/stop.png");
        
//        static ImageIcon IC = new ImageIcon("images/ic.png");
        static ImageIcon IC = getScaledImageIcon(new ImageIcon("images/ic2.png"), 150, 150);
    
    private static ImageIcon getScaledImageIcon(ImageIcon srcIcon, float _w, float _h) {
        float y = srcIcon.getIconHeight();
        float x = srcIcon.getIconWidth();
        
        float zoomX = _w/x;
        float zoomY = _h/y;
        float zoom;
        if(zoomX>zoomY)
            zoom = zoomY;
        else zoom = zoomX;
        
        int w = (int) (zoom * x);
        int h = (int) (zoom * y);
        
        Image srcImg = srcIcon.getImage();

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h,Color.WHITE, null);
        g2.dispose();
        return new ImageIcon(resizedImg);
    }

    private static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
