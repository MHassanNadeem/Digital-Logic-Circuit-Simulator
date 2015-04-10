/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hassan
 */
public class DFlipFlop extends Component {

    int width = 31;
    int height = 41;
    Rectangle shape;
    Boolean state = false;
    Boolean clockState;
    Boolean stateToBe=false;

    public DFlipFlop(int x, int y) {
        this.x = x;
        this.y = y;

        shape = new Rectangle(x, y, width, height);
        outConnectors.add(new Connector(x + width, y + (height / 2), Types.Orientation.Horizontal, Types.IO.Output, this));
        inConnectors.add(new Connector(x, y + 10, Types.Orientation.Horizontal, Types.Position.LEFT, Types.IO.Input, this));
        inConnectors.add(new Connector(x, y + height - 10, Types.Orientation.Horizontal, Types.Position.LEFT, Types.IO.Input, this));

        Thread t = new Thread() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DFlipFlop.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    stateToBe = inConnectors.get(0).getState();
                }
            }
        };
        t.start();
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
//        state = Primitives.NAND(inConnectors);
        Boolean preClockState = clockState;
        clockState = inConnectors.get(1).getState();
        inConnectors.get(1).node.state = clockState;
        if (clockState != null && preClockState != null) {
//            System.out.println(clockState+"---"+preClockState);
            if (clockState == true && preClockState == false) {
//                System.out.println("I MADE IT!!!!!!!!!!!!");
                state = stateToBe;
            }
        }
    }

    Rectangle getShape() {
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
        g2d.draw(shape);
        g2d.translate(0, 0);
    }

    String getName() {
        return "And - 4";
    }
}
