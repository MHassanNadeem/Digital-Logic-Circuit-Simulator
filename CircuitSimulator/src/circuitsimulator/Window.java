/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Hassan
 */
public class Window extends JFrame {
    static Sheet sheet;
    static SimulateToolbar sim;
    static String fixedTitle = "iSee Circuits";
    static String fileName = "Untitled";
    static String title = fileName+" - "+fixedTitle;
    static Window ref;
    public Window(){
        ref = this;
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(new Dimension(600, 600));
        this.setJMenuBar(new MenuBar());
        this.add(new FileToolbar(), BorderLayout.NORTH);
        this.add(new Verticalbar(), BorderLayout.WEST);
        sheet = new Sheet(1000,1000);
        JScrollPane thePane = new JScrollPane(sheet);
        this.add(thePane, BorderLayout.CENTER);
        sim = new SimulateToolbar();
        this.add(sim, BorderLayout.SOUTH);
        
//        JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
//        this.add(new Toolbar(), BorderLayout.WEST);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setFocusable(true);
//        sheet.gridOff();
    }
    
    static void changeTitle(String s){
        title = s+" - "+fixedTitle;
        ref.setTitle(title);
    }
}
