/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Hassan
 */
public class CircuitSimulator {

    static Window win;
//    static ArrayList<String> Categories = new ArrayList<>();
    static String[] Categories = {"(All Categories)", "Logic Gates", "Switches", "Digital ICs", "Debugging Tools", "Voltage Source"};
    static Object[][] data;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Main Started");
        getCategories();
        win = new Window();
//        ComponentsViewer.changeIC(Images.exit);
    }

    public static void getCategories() {
        try {
            FileInputStream fstream = new FileInputStream("Categories.csv");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            ArrayList<String[]> result = new ArrayList<String[]>();
            while ((strLine = br.readLine()) != null) {
                result.add(strLine.split(","));
            }
            int cols = result.get(0).length;
            int rows = result.size();
            System.out.println("Rows: " + rows + " Cols: " + cols);
            data = new Object[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = result.get(i)[j];
                    //     System.out.println(data[i][j]);
                }
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static Component classForName(String selectedComp, int x, int y) {
        System.out.println("asdfasdf:  "+selectedComp);
//        if(selectedComp==null) return;
        Component c = null;
        switch (selectedComp) {
            case "And - 2":
                c = new And(x, y);
                break;
            case "And - 3":
                c = new And(x, y, 3);
                break;
            case "And - 4":
//                c = new And(x, y, 4);
                c = new DFlipFlop(x, y);
                break;
            case "Or - 2":
                c = new Or(x, y);
                break;
            case "Or - 3":
                c = new Or(x, y, 3);
                break;
            case "Or - 4":
                c = new Or(x, y, 4);
                break;
            case "Not":
                c = new Not(x, y);
                break;
            case "XOR - 2":
                c = new Xor(x, y);
                break;
            case "XOR - 3":
                c = new Xor(x, y, 3);
                break;
            case "XOR - 4":
                c = new Xor(x, y, 4);
                break;
            case "XNOR - 2":
                c = new Xnor(x, y);
                break;
            case "XNOR - 3":
                c = new Xnor(x, y, 3);
                break;
            case "XNOR - 4":
                c = new Xnor(x, y, 4);
                break;
            case "NOR - 2":
                c = new Nor(x, y, 2);
                break;
            case "NOR - 3":
                c = new Nor(x, y, 3);
                break;
            case "NOR - 4":
                c = new Nor(x, y, 4);
                break;
            case "NAND - 2":
                c = new Nand(x, y, 2);
                break;
            case "NAND - 3":
                c = new Nand(x, y, 3);
                break;
            case "NAND - 4":
                c = new Nand(x, y, 4);
                break;
            case "Logic Toggle":
                c = new LogicToggle(x, y);
                break;
            case "Logic State":
                c = new LogicToggle(x, y);
                break;
            case "Logic Probe":
                c = new LogicProbe(x, y);
                break;
            case "Clock":
                c = new Clock(x, y);
                break;
            case "Display":
                c = new Display(x, y);
                break;
        }
        return c;
    }
}
