/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Hassan
 */
public class Nor extends Component {

    Area shape;
    int height = 60;
    int width = 60;
    Color shapeColor = Color.DARK_GRAY;
    boolean state = false;
    static BasicStroke stroke = new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    Nor(int x, int y) {
        this(x, y, 2);
    }

    Nor(int x, int y, int n) {
        gateInputs = n;
        this.x = x;
        this.y = y;
        shape = new OrShape(x, y);
        shape.add(new Area(new Ellipse2D.Double(x + width, y - 5 + width / 2, 10, 10)));

        outConnectors.add(new Connector(x + width, y + (height / 2), Types.Orientation.Horizontal, Types.IO.Output, this));
        int number = n;
        int gap = height / (number + 1);
        int yt = y;
        for (int i = 0; i < number; i++) {
            yt += gap;
            inConnectors.add(new Connector(x + 5, yt, Types.Orientation.Horizontal, Types.Position.LEFT, Types.IO.Input, this));
        }
    }

    synchronized Boolean getState() {
        Thread stateUpdater = new Thread() {
            public void run() {
                updateState();
            }
        };
        try {
            return state;
        } finally {
            stateUpdater.start();
        }
    }

    void updateState() {
        state = Primitives.NOR(inConnectors);
    }

    Area getShape() {
        return shape;
    }

    Rectangle getBounds() {
        return new Rectangle(x - padding, y - padding, width + 2 * padding, height + 2 * padding);
    }

    @Override
    void draw(Graphics2D g2d) {
        if (getBounds().contains(Mouse.DeleteLocation)) {
            delete();
        }
        g2d.translate(tx, ty);
        drawConnectors(g2d);
        g2d.setColor(shapeColor);
        g2d.setStroke(stroke);
        g2d.draw(shape);
//        g2d.translate(0, 0);
    }

    String getName() {
        return "NOR - " + gateInputs;
    }
}
