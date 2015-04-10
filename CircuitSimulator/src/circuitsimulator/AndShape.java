/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author Hassan
 */
public class AndShape extends Area {

    int width = 60;
    int height = 60;
    Rectangle rectShape;
    Ellipse2D.Double ellipseShape;
    int x;
    int y;
    static Area preview = null;

    public AndShape(int x, int y) {
        this.x = x;
        this.y = y;
        ellipseShape = new Ellipse2D.Double(x, y, width, height);
        rectShape = new Rectangle(x, y, width / 2, height);
        this.add(new Area(rectShape));
        this.add(new Area(ellipseShape));
    }

    public static Area getPreview() {
        if (preview == null) {
            preview = new AndShape(5,5);
        }
        return preview;
    }
}
