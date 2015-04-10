/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Hassan
 */
public abstract class Component {

    int x, y;
//    int width, height;
    int tx = 0, ty = 0;
    static Boolean pullState = false;
    ArrayList<Component> runnable = new ArrayList<>();
    ArrayList<Connector> inConnectors = new ArrayList<>();
    ArrayList<Connector> outConnectors = new ArrayList<>();
//    static ArrayList<Component> drawable = new ArrayList<>();
    static CopyOnWriteArrayList<Component> drawable = new CopyOnWriteArrayList<>();
    int gateInputs = 999;
    int padding = 8;

    public Component() {
    }

    abstract void draw(Graphics2D g2d);

    void drawConnectors(Graphics2D g2d) {
        drawOutConnectors(g2d);
        drawInConnectors(g2d);
    }

    void drawOutConnectors(Graphics2D g2d) {
        for (Connector con : outConnectors) {
            con.draw(g2d);
        }
    }

    void drawInConnectors(Graphics2D g2d) {
        for (Connector con : inConnectors) {
            con.draw(g2d);
        }
    }

    void delete() {
        Wire.delete(this.inConnectors, this.outConnectors);
        Component.drawable.remove(this);
        Mouse.resetDeleteLocation();
    }

    String getName() {
        return null;
    }

    Shape getShape() {
        return null;
    }

//    abstract Rectangle getBounds();
//    abstract void onClick();
    Boolean getState() {
        return null;
    }

    void onClick() {
    }
}
