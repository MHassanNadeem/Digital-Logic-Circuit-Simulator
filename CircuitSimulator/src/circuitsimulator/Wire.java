/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Hassan
 */
public class Wire extends Component {

    static CopyOnWriteArrayList<Wire> allTheWires = new CopyOnWriteArrayList<>();
    static float width = 3;
    static Color color = Color.green;
    static Color mouseOverColor = Color.red;
    static BasicStroke stroke = new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
//    ArrayList<Line2D.Double> allTheWires = new ArrayList<>();
    Node from;
    Node to;
    GeneralPath polyline;
    int[] x;
    int[] y;
    int npoints;

    public Wire(int[] _x, int[] _y, int _npoints, Node n1, Node n2) {
        from = n1;
        to = n2;
        n1.connectedTo.add(n2);
        n2.connectedTo.add(n1);
        npoints = _npoints;
        x = new int[npoints];
        y = new int[npoints];
        for (int i = 0; i < npoints; i++) {
            x[i] = _x[i];
            y[i] = _y[i];
        }
        x[0] = n1.x4ext;
        y[0] = n1.y4ext;
        x[npoints - 2] = n2.x4ext;
        y[npoints - 2] = n2.y4ext;
        polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, npoints);
        polyline.moveTo(x[0], y[0]);
        for (int index = 1; index < npoints; index++) {
            polyline.lineTo(x[index], y[index]);
        };
        allTheWires.add(this);
    }

    public Wire(Node c1, Node c2) {
        from = c1;
        to = c2;
    }

//    public Boolean getState() {
//        return from.getState();
//    }
    public void wireUnderProduction() {
//        Polygon p = new Polygon();
//        p.
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
//        if(polyline.intersect(new Point(Mouse.getLocX(),Mouse.getLocY()))) {
//            System.out.println("asdf");
//            g2d.setColor(mouseOverColor);
//        }
        g2d.setStroke(stroke);
        g2d.draw(polyline);
    }

    public static void delete(final ArrayList<Connector> in, final ArrayList<Connector> out) {
        Thread deleteThread = new Thread() {
            public void run() {
                for (Wire wire : allTheWires) {
                    for (Connector con : in) {
                        if (wire.from.getBounds().intersects(con.node.getBounds())) {
                            allTheWires.remove(wire);
                        }
                        if (wire.to.getBounds().intersects(con.node.getBounds())) {
                            allTheWires.remove(wire);
                        }
                    }
                    for (Connector con : out) {
                        if (wire.from.getBounds().intersects(con.node.getBounds())) {
                            allTheWires.remove(wire);
                        }
                        if (wire.to.getBounds().intersects(con.node.getBounds())) {
                            allTheWires.remove(wire);
                        }
                    }

                }
            }
        };
        deleteThread.start();
    }

    public static void DrawAll(Graphics2D g2d) {
        for (Wire wire : allTheWires) {
            wire.draw(g2d);
        }
    }

    String getName() {
        return "Wire";
    }
}
