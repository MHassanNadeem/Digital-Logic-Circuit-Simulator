/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 *
 * @author Hassan
 */
public class FileChooserDialog/* extends JFrame */ {

    static int t = 0;
    static String ext = ".isee";

    public enum SaveORLoad {

        Save,
        Load
    };

    public FileChooserDialog(SaveORLoad doo) {
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileFilter(new OpenFileFilter(ext));
        fileopen.setAcceptAllFileFilterUsed(false);
        int ret = 1;
        if (doo == SaveORLoad.Load) {
            ret = fileopen.showDialog(null, "Open File");
        } else {
            ret = fileopen.showDialog(null, "Save File");
        }
        if (ret == JFileChooser.APPROVE_OPTION) {
            File f = fileopen.getSelectedFile();
            System.out.println(f.getPath());
            String path = f.getPath();
            String filename = f.getName();
            System.out.println("You have selected: " + filename);
            if (doo == SaveORLoad.Load) {
                load(path);
            } else {
                save(path + ext);
            }
        }
    }

    public void save(String fileName) {
        ArrayList<ArrayList> comp = new ArrayList<>();
        ArrayList<ArrayList> wir = new ArrayList<>();
        ArrayList<ArrayList<ArrayList>> saveArray = new ArrayList<>();
        for (Component c : Component.drawable) {
            ArrayList obj = new ArrayList();
            obj.add(c.getName());
            obj.add(c.x);
            obj.add(c.y);
            comp.add(obj);
        }

        for (Wire wire : Wire.allTheWires) {
            if (wire == null) {
                continue;
            }
            ArrayList obj = new ArrayList();
            obj.add(wire.x);
            obj.add(wire.y);
            obj.add(wire.from.getBounds());
            obj.add(wire.to.getBounds());
            wir.add(obj);
        }

        saveArray.add(comp);
        saveArray.add(wir);
        try {
            FileOutputStream fileOut1 = new FileOutputStream(fileName);
            ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
            out1.writeObject(saveArray);
        } catch (IOException i) {
            i.printStackTrace();
        }
        Window.changeTitle(fileName);
    }

    public void load(String fileName) {
        Component.drawable.clear();
        Wire.allTheWires.clear();
        Node.allTheNodes.clear();
        ArrayList saveArray = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            saveArray = (ArrayList) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList comp = (ArrayList) saveArray.get(0);
        ArrayList wir = (ArrayList) saveArray.get(1);
        for (Object o : comp) {
            ArrayList obj = (ArrayList) o;
            String name = (String) obj.get(0);
            if(name==null) continue;
            int arg1 = (int) obj.get(1);
            int arg2 = (int) obj.get(2);
            Component c = CircuitSimulator.classForName(name, arg1, arg2);
            Component.drawable.add(c);
            if (c instanceof Clock) {
                new Thread(((Clock) c)).start();
            }
        }

        for (Object o : wir) {
            ArrayList obj = (ArrayList) o;
            int[] x = (int[]) obj.get(0);
            int[] y = (int[]) obj.get(1);
            Rectangle b1 = (Rectangle) obj.get(2);
            Rectangle b2 = (Rectangle) obj.get(3);
            Node n1 = null;
            Node n2 = null;
            for (Component c : Component.drawable) {
                for (Connector con : c.inConnectors) {
                    if (con.node.getBounds().intersects(b1)) {
                        System.out.println(b1);
                        System.out.println(con.node.getBounds());
                        n1 = con.node;
                    }
                    if (con.node.getBounds().intersects(b2)) {
                        n2 = con.node;
                    }
                }
                for (Connector con : c.outConnectors) {
                    if (con.node.getBounds().intersects(b1)) {
                        n1 = con.node;
                    }
                    if (con.node.getBounds().intersects(b2)) {
                        n2 = con.node;
                    }
                }
            }
            if (n1 != null && n2 != null) {
                new Wire(x, y, x.length, n1, n2);
            } else {
                System.out.println("FAIL!!!");
            }
        }

        Window.changeTitle(fileName);
    }
}
