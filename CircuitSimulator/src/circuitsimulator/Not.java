/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author Hassan
 */
public class Not extends Component {

    Color shapeColor = Color.gray;
    Polygon triangle;
    int bubbleDia = 10;
    Ellipse2D.Double bubble;
    Area shape;
    int height = 25;
    int width = 50;
    boolean state = false;
    static BasicStroke stroke = new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    public Not(int x, int y) {
        this.x = x;
        this.y = y;
        shape = new NotShape(x, y);

        bubble = new Ellipse2D.Double(x + width, y + height - bubbleDia / 2, bubbleDia, bubbleDia);
        shape.add(new Area(bubble));
        inConnectors.add(new Connector(x + bubbleDia - 8, y + height, Types.Orientation.Horizontal, Types.Position.LEFT, Types.IO.Input, this));
        outConnectors.add(new Connector(x + bubbleDia + width, y + height, Types.Orientation.Horizontal, Types.IO.Output, this));
    }

    Area getShape() {
        return shape;
    }

    Rectangle getBounds() {
        return new Rectangle(x - padding, y - padding, width + 2 * padding, height + 2 * padding);
    }

    Color getShapeColor() {
        return shapeColor;
    }

    Boolean getState() {
        return Primitives.NOT(inConnectors.get(0).getState());
    }

    void draw(Graphics2D g2d) {
        if (getBounds().contains(Mouse.DeleteLocation)) {
            delete();
        }
        g2d.translate(tx, ty);
        g2d.setColor(shapeColor);
        g2d.setStroke(stroke);
        g2d.draw(shape);
        drawConnectors(g2d);
    }

    void delete() {
        System.out.println("Deleter");
        Component.drawable.remove(this);
    }

    String getName() {
        return "Not";
    }
}
