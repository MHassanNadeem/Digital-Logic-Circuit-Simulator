/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Hassan
 */
public class MenuBar extends JMenuBar {

    public MenuBar() {
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem _new = new JMenuItem("New");
        _new.setMnemonic(KeyEvent.VK_N);
               _new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Component.drawable.clear();
                Wire.allTheWires.clear();
                Node.allTheNodes.clear();
                Window.ref.changeTitle("Untitled");
            }
        });
        JMenuItem open = new JMenuItem("Open");
        open.setMnemonic(KeyEvent.VK_O);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new FileChooserDialog(FileChooserDialog.SaveORLoad.Load);
            }
        });
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_S);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new FileChooserDialog(FileChooserDialog.SaveORLoad.Save);
            }
        });
        JMenuItem saveAs = new JMenuItem("Save As  ");
        saveAs.setMnemonic(KeyEvent.VK_A);
        saveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new FileChooserDialog(FileChooserDialog.SaveORLoad.Save);
            }
        });
        JMenuItem exit = new JMenuItem("Exit", Images.close);
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setToolTipText("Exit application");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        file.add(_new);
        file.add(open);
        file.addSeparator();
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(exit);

        JMenu view = new JMenu("View");
//        JMenuItem grid = new JMenuItem("Grid");
        JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Grid");
        grid.setState(true);
        grid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CircuitSimulator.win.sheet.toggleGrid();
                CircuitSimulator.win.sheet.repaint();
            }
        });

        view.add(grid);

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        JMenuItem about = new JMenuItem("About");
        JMenuItem helpItem = new JMenuItem("Help");
        help.add(helpItem);
        help.addSeparator();
        help.add(about);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("About");
                JOptionPane.showMessageDialog(null, "Circuit Simulation Tool by: \n    Muhammad Hassan Nadeem \n    2015-10-0063",
                        "iSee Circuits", JOptionPane.PLAIN_MESSAGE);
            }
        });


        this.add(file);
        this.add(view);
        this.add(help);

    }
}
