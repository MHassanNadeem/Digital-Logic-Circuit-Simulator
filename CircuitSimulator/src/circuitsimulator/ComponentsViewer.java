/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Hassan
 */
public class ComponentsViewer extends JPanel {

    static JPanel preview;
    static Vector<String> listOfComponents = new Vector<>(0);
    static JList list;

    public ComponentsViewer() {
        preview = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
                String name = Mouse.selectedComponent();
                if (name != null) {
                    CircuitSimulator.classForName(name, 40, 15).draw(g2d);
                }
            }
        };
        preview.setPreferredSize(new Dimension(100, 20));
        preview.setSize(100,20);
        this.add(preview);
//        this.setBackground(Color.WHITE);
//        this.add(component);

//        Component[] com = {new Component(), new Component(), new Component()};
        list = new JList(listOfComponents);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(50, this.getPreferredSize().height - 1));
        System.out.println("My size is : " + this.getPreferredSize().height);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String name = (String) list.getSelectedValue();
                    System.out.println("Item Selected: " + name);
                    Mouse.setComponent(name);
                    Mouse.mode = Mouse.mode.Component;
                    Verticalbar.instance.setComponentActive();
                    preview.repaint();
                }
            }
        });


        JButton pick = new JButton("Pick Components");
        pick.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new Picker();
            }
        });

        this.add(new JLabel("       Component View"));
//        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(pick);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
//        this.add(new JButton("teseting2"));
        this.add(pane);
        this.setBorder(BorderFactory.createLineBorder(Color.red));
    }

    static void addToList(String s) {
        if (listOfComponents.contains(s)) {
            return;
        }
        listOfComponents.add(s);
        list.updateUI();
    }
}
