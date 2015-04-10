/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import circuitsimulator.Types.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.geom.Area;

/**
 *
 * @author Hassan
 */
public class Display extends Component {

    static int width = 60;
    static int height = 80;
    static Color bgColor = Color.lightGray;
    Rectangle shape;
    Segments[] segments = new Segments[7];
    int noToDisplay = 0;

    public Display(int _x, int _y) {
        this.x = _x;
        this.y = _y;

        shape = new Rectangle(x, y, width, height);

        addConnectors();
        addSegments();
    }

    private void addConnectors() {
        int gap = 20;
        for (int i = 0; i < 4; i++) {
//            inConnectors.add(new Connector(x + width, y + (i * gap) + 10, Orientation.Horizontal,Position.RIGHT, IO.Input, this));
            inConnectors.add(new Connector(x, y + (i * gap) + 10, Orientation.Horizontal, Position.LEFT, IO.Input, this));
        }
    }

    private void addSegments() {
        segments[0] = new Segments(x + 5, y + 5, Orientation.Horizontal);
        segments[1] = new Segments(x + width - 19, y + 10, Orientation.Vertical);
        segments[2] = new Segments(x + width - 19, y + 40, Orientation.Vertical);
        segments[3] = new Segments(x + 5, y - 15 + height, Orientation.Horizontal);
        segments[4] = new Segments(x, y + 40, Orientation.Vertical);
        segments[5] = new Segments(x, y + 10, Orientation.Vertical);
        segments[6] = new Segments(x + 5, y + height / 2 - 5, Orientation.Horizontal);
    }

    private void allOn() {
        for (Segments seg : segments) {
            seg.state = true;
        }
    }

    private void allOff() {
        for (Segments seg : segments) {
            seg.state = false;
        }
    }

    Rectangle getShape() {
        return shape;
    }

    Rectangle getBounds() {
        return new Rectangle(x - padding, y - padding, width + 2 * padding, height + 2 * padding);
    }

    void updateState() {
        updateNumber();
//        System.out.println(noToDisplay);
        switch (noToDisplay) {
            case 0:
                allOn();
                segments[6].state = false;
                break;
            case 1:

                allOff();
                segments[1].state = true;
                segments[2].state = true;
                break;
            case 2:
                allOn();
                segments[2].state = false;
                segments[5].state = false;
                break;
            case 3:
                allOn();
                segments[4].state = false;
                segments[5].state = false;
                break;
            case 4:
                allOn();
                segments[0].state = false;
                segments[3].state = false;
                segments[4].state = false;
                break;
            case 5:
                allOn();
                segments[1].state = false;
                segments[4].state = false;
                break;
            case 6:
                allOn();
                segments[1].state = false;
                break;
            case 7:
                allOff();
                segments[0].state = true;
                segments[1].state = true;
                segments[2].state = true;
                break;
            case 8:
                allOn();
                break;
            case 9:
                allOn();
                segments[4].state = false;
                break;
            case 10:
                allOn();
                segments[3].state = false;
                break;
            case 11:
                allOn();
                segments[0].state = false;
                segments[1].state = false;
                break;
            case 12:
                allOn();
                segments[1].state = false;
                segments[2].state = false;
                segments[6].state = false;
                break;
            case 13:
                allOn();
                segments[0].state = false;
                segments[5].state = false;
                break;
            case 14:
                allOn();
                segments[1].state = false;
                segments[2].state = false;
                break;
            case 15:
                allOn();
                segments[1].state = false;
                segments[2].state = false;
                segments[3].state = false;
                break;
        }
    }

    private void updateNumber() {
        Boolean state;
        int number = 0;
        int weight = 1;
        for (Connector inCon : inConnectors) {
            state = inCon.getState();
            if (state == null) {
                state = false;
            }
            if (state) {
                number += weight;
            }
            weight *= 2;
        }
        noToDisplay = number;
    }

    public void draw(Graphics2D g2d) {
        if (getBounds().contains(Mouse.DeleteLocation)) {
            delete();
        }
        updateState();
        g2d.translate(tx, ty);
        g2d.setColor(bgColor);
        g2d.fill(shape);
        for (Connector con : inConnectors) {
            con.draw(g2d);
        }

        for (Segments seg : segments) {
            seg.draw(g2d);
        }
    }

    String getName() {
        return "Display";
    }

    class Segments {

        boolean state = false;
        int length = 35;
        int width = 10;
        int x;
        int y;
        Color offColor = Color.gray;
        Color onColor = Color.red;
        Orientation orientation;
        Polygon p;

        public Segments(int _x, int _y, Orientation _orientation) {
            x = _x + 5;
            y = _y;
            orientation = _orientation;
            p = new Polygon();
            int temper = 5;

            if (orientation == Orientation.Horizontal) {
                p.addPoint(x + temper, y);
                p.addPoint(x + length - temper, y);
                p.addPoint(x + length, y + width / 2);
                p.addPoint(x + length - temper, y + width);
                p.addPoint(x + temper, y + width);
                p.addPoint(x, y + width / 2);
            } else {
                x -= 3;
                int length = 30;
                p.addPoint(x + width / 2, y);
                p.addPoint(x + width, y + temper);
                p.addPoint(x + width, y + length - temper);
                p.addPoint(x + width / 2, y + length);
                p.addPoint(x, y + length - temper);
                p.addPoint(x, y + temper);
            }
        }

        public void draw(Graphics2D g2d) {
            g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
            if (state) {
                g2d.setColor(onColor);
                g2d.fill(p);
            } else {
                g2d.setColor(offColor);
                g2d.drawPolygon(p);
            }
        }
        
    }
}
