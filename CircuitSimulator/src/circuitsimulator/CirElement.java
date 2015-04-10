/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Hassan
 */
public class CirElement {

    static ArrayList<CirElement> drawable = new ArrayList<>();
    Point loc;

    public CirElement(int x, int y) {
        loc = new Point(x, y);
        drawable.add(this);
//        loc.x = x;
//        loc.y = y;
    }

    public Rectangle getBounds() {
        Rectangle bounds = new Rectangle(loc.x, loc.y, 50, 50);
        return bounds;
    }

    public Image getImage() {
        return Images.IC.getImage();
    }

    public int getX() {
        return loc.x;
    }

    public int getY() {
        return loc.y;
    }

    public void moveComponent(int x, int y) {
        loc.x = x;
        loc.y = y;
    }

    public void moveComponent(Point p) {
//        dx
        loc = p;
    }

    public boolean underMouse(Point p) {
        if (getBounds().contains(p)) {
            return true;
        }
        return false;
    }
}
