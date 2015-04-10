/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 *
 * @author Hassan
 */
public class LogicToggle extends Component {

    Font font = new Font("Serif", Font.PLAIN, 32);
    String label = "0";
    Color shapeColor = Color.gray;
    Color labelColor = Color.blue;
    Rectangle shape;
    int height = 32;
    int width = 32;
    boolean state = false;

    public LogicToggle(int x, int y) {
        this.x = x;
        this.y = y;
        shape = new Rectangle(x, y, width, height);
        outConnectors.add(new Connector(x + width, y + (height / 2), Types.Orientation.Horizontal, Types.IO.Output, this));
    }

    Rectangle getShape() {
        return shape;
    }

    String getLabel() {
        return label;
    }

    Font getFont() {
        return font;
    }

    Color getShapeColor() {
        return shapeColor;
    }

    Color getLabelColor() {
        return labelColor;
    }

    void toggle() {
        if (state) {
            labelColor = Color.blue;
            state = false;
            label = "0";
        } else {
            labelColor = Color.red;
            state = true;
            label = "1";
        }
    }

    Boolean getState() {
        return state;
    }

    void onClick() {
        toggle();
    }

    Rectangle getBounds() {
        return new Rectangle(x - padding, y - padding, width + 2 * padding, height + 2 * padding);
    }

    void draw(Graphics2D g2d) {
        if (getBounds().contains(Mouse.DeleteLocation)) {
            delete();
        }
        g2d.translate(tx, ty);
        Color colorBackup = g2d.getColor();
        Stroke strokeBackup = g2d.getStroke();

//        BasicStroke str = new BasicStroke(3.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
//        g2d.setStroke(str);
//        g2d.draw(new Line2D.Double(o.x + o.width, o.y + (o.height / 2), o.x + 60, o.y + (o.height / 2)));
        drawConnectors(g2d);
//g2d.setColor(Color.black);
//g2d.draw(shape);
        g2d.setColor(shapeColor);
        g2d.fill(shape);
        g2d.setColor(labelColor);
        g2d.setFont(font);
        g2d.drawString(label, x + 10, y + 25);

        g2d.setColor(colorBackup);
        g2d.setStroke(strokeBackup);
        g2d.translate(0, 0);
    }

    void delete() {
        System.out.println("Deleter");
        Component.drawable.remove(this);
    }

    String getName() {
        return "Logic Toggle";
    }
}
