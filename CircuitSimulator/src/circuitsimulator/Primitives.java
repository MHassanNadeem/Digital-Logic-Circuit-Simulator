/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.util.ArrayList;

/**
 *
 * @author Hassan
 */
public class Primitives {

    public static Boolean XOR(ArrayList<Connector> inConnectors) {
        Boolean state;
        int trueCount = 0;
        for (Connector inCon : inConnectors) {
            state = inCon.getState();
            if (state == null) {
                state = Component.pullState;
            }
            if (state == true) {
                trueCount++;
            }
        }
        if (trueCount % 2 == 0) {
            return false;
        }
        return true;
    }

    public static Boolean AND(ArrayList<Connector> inConnectors) {
        Boolean state;
        for (Connector inCon : inConnectors) {
            state = inCon.getState();
            if (state == null) {
                state = false;
            }
            if (state == false) {

                return false;
            }
        }
        return true;
    }

    public static Boolean OR(ArrayList<Connector> inConnectors) {
        Boolean state;
        for (Connector inCon : inConnectors) {
            state = inCon.getState();
            if (state == null) {
                state = Component.pullState;
            }
            if (state == true) {
                return true;
            }
        }
        return false;
    }

    public static Boolean NOT(Boolean in) {
        if(in == null) return null;
        if (in == false) {
            return true;
        } else {
            return false;
        }
    }
    public static Boolean NAND(ArrayList<Connector> inConnectors) {
        return NOT(AND(inConnectors));
    }
    public static Boolean NOR(ArrayList<Connector> inConnectors) {
        return NOT(OR(inConnectors));
    }
    public static Boolean XNOR(ArrayList<Connector> inConnectors) {
        return NOT(XOR(inConnectors));
    }
}
