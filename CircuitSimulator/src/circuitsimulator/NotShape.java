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
public class NotShape extends Area {

    int width = 50;
    int height = 25;
    Polygon triangle;
    int x, y;

    public NotShape(int x, int y) {
        this.x = x;
        this.y = y;
        triangle = new Polygon();
        triangle.addPoint(x + width, y + height);
        triangle.addPoint(x, y + 2 * height);
        triangle.addPoint(x, y);
        triangle.addPoint(x + width, y + height);
        this.add(new Area(triangle));
    }
}
