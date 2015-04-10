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
public class FileToolbar extends JToolBar {
    public FileToolbar(){
        JButton exitButton = new JButton(Images.close);
         exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
         
         JButton _new = new JButton(Images._new);
         _new.setToolTipText("New File");
         JButton open = new JButton(Images.open);
         open.setToolTipText("Open Design");
         JButton save = new JButton(Images.save);
         save.setToolTipText("Save Design");
         
         
         this.add(_new);
         this.add(open);
         this.add(save);
         this.add(exitButton);
         
         this.setFloatable(false);
    }
}
