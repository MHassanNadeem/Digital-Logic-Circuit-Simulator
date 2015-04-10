/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hassan
 */
public class SimulateToolbar extends JToolBar {

    JButton mousePos;
    JButton message;
    static SimulateToolbar ref;
    static boolean running = false;
    static Color inactiveColor = new Color(238,238,238);

    public SimulateToolbar() {
        ref = this;
        this.setLayout(new BorderLayout());
        JButton play = new JButton(Images.play);
        play.setToolTipText("Play");
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            }
        });


        JButton pause = new JButton(Images.pause);
        pause.setToolTipText("Pause");
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            }
        });


        JButton stop = new JButton(Images.stop);
        stop.setToolTipText("Stop");
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            }
        });

        mousePos = new JButton("Mouse Pos");
        mousePos.setEnabled(false);
        mousePos.setPreferredSize(new Dimension(150, 10));
        message = new JButton("Messages!!!");
        message.setEnabled(false);
//        mousePos.

//        addSeparator();
        JPanel simButtons = new JPanel();
        simButtons.add(play);
        simButtons.add(pause);
        simButtons.add(stop);
//        addSeparator();
        add(simButtons, BorderLayout.LINE_START);
        add(message, BorderLayout.CENTER);
        add(mousePos, BorderLayout.LINE_END);

        this.setFloatable(false);
//        setRollover(false);
    }

    public void changeMousePosition(int x, int y) {
//        System.out.println("X: "+x+" Y: "+y);
        mousePos.setText("X: " + x + " | Y: " + y);
    }

    public static void warningMessage() {
        Thread t = new Thread() {
            public void run() {
                running = true;
                ref.message.setText("SHORT!!!!!!!!!!");
                ref.message.setBackground(Color.red);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulateToolbar.class.getName()).log(Level.SEVERE, null, ex);
                }
                ref.message.setText("No Messages!!!");
                ref.message.setBackground(inactiveColor);
                running = false;
            }
        };
        t.start();
    }

    public static void shortMessage(ArrayList<Boolean> a) {
        if (running) {
            return;
        }
        Boolean e = a.get(0);
        for (Boolean b : a) {
            if (e != b) {
                warningMessage();
            }
        }
    }
}
