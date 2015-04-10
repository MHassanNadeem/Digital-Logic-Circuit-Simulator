/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Hassan
 */
public class OrShape extends Area {

    Color shapeColor = Color.DARK_GRAY;
    Color labelColor = Color.blue;
    Rectangle rectShape;
    Ellipse2D.Double ellipseShape;
    Ellipse2D.Double ellipseShape2;
    int height = 60;
    int width = 60;

    public OrShape(int x, int y) {
        ellipseShape = new Ellipse2D.Double(x, y, width, height);
        rectShape = new Rectangle(x, y, width / 2, height);
        ellipseShape2 = new Ellipse2D.Double(x - (width / 4), y, (width / 2), height);

        this.add(new Area(ellipseShape));
        this.add(new Area(rectShape));
        this.subtract(new Area(ellipseShape2));
    }
}
