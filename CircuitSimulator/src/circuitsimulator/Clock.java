/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Hassan
 */
public class Clock extends Component implements Runnable {

    Rectangle shape;
    Color shapeColor = Color.gray;
    int width = 32;
    int height = 32;
    int timePeriod = 1000;
    Boolean state = true;

    public Clock(int x, int y) {
        this.x = x;
        this.y = y;

        shape = new Rectangle(x, y, width, height);
        outConnectors.add(new Connector(x + width, y + (height / 2), Types.Orientation.Horizontal, Types.IO.Output, this));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(timePeriod);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
//            System.out.println("Runnings");
            toggleState();
        }
    }

    private void toggleState() {
        if (state == true) {
            state = false;
        } else {
            state = true;
        }

    }

    Boolean getState() {
        return state;
    }

    void onClick() {
        new Options();
    }

    Rectangle getShape() {
        return shape;
    }

    Rectangle getBounds() {
        return new Rectangle(x - padding, y - padding, width + 2 * padding, height + 2 * padding);
    }

    void draw(Graphics2D g2d) {
        if (getBounds().contains(Mouse.DeleteLocation)) {
            delete();
        }
        g2d.translate(tx, ty);

        Color colorBackup = g2d.getColor();
        Stroke strokeBackup = g2d.getStroke();

        drawOutConnectors(g2d);
        g2d.setColor(shapeColor);
        g2d.fill(shape);

        g2d.setColor(colorBackup);
        g2d.setStroke(strokeBackup);

        g2d.translate(0, 0);
    }

    String getName() {
        return "Clock";
    }

    class Options extends JFrame {

        int fieldSize = 100;
        JRadioButton periodButton;
        JRadioButton frequencyButton;
        JTextField period;
        JTextField frequency;
        JButton save;
        JButton cancel;
        JFrame ref;

        Options() {
            ref = this;
            this.setTitle("Clock Properties");
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            periodButton = new JRadioButton("Period (ms)");
            frequencyButton = new JRadioButton("Frequency (Hz)");
            frequencyButton.addActionListener(new radioListner());
            periodButton.addActionListener(new radioListner());
            ButtonGroup group = new ButtonGroup();
            group.add(periodButton);
            group.add(frequencyButton);
            periodButton.setSelected(true);
            save = new JButton("Save");
            cancel = new JButton("Cancel");
            save.addActionListener(new buttonListner());
            cancel.addActionListener(new buttonListner());
            period = new JTextField(String.valueOf(timePeriod));
//            period.addActionListener(new textFieldListener());
            period.getDocument().addDocumentListener(new periodFieldListener());
            Dimension d = period.getPreferredSize();
            period.setPreferredSize(new Dimension(fieldSize, d.height));
            frequency = new JTextField(periodToFrequency(timePeriod));
            frequency.getDocument().addDocumentListener(new freqFieldListener());
            frequency.setPreferredSize(new Dimension(fieldSize, d.height));
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.add(periodButton);
            panel.add(period);
//            panel.add(periodUnits);
            panel.add(frequencyButton);
            panel.add(frequency);
//            panel.add(freqUnits);
            panel.add(save);
            panel.add(cancel);
//            panel.add(new );
//            panel.add(units);
//             newContentPane.setOpaque(true);
            this.setContentPane(panel);
//period.setText("asdfaasdfdfasdfadsf");
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.pack();
        }

        String periodToFrequency(String p) {
            String ret = "Invalid Period";
            try {
                ret = String.valueOf(1000 / Float.parseFloat(p));
            } catch (Exception e) {
            }
            return ret;
        }

        String periodToFrequency(int p) {
            return String.valueOf(1000 / p);
        }

        String frequencyToPeriod(String p) {
            String ret = "Invalid Frequency";
            try {
                ret = String.valueOf(1000 / Float.parseFloat(p));
            } catch (Exception e) {
            }
            return ret;
        }

        public class radioListner implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }

        public class periodFieldListener implements DocumentListener {

            public void change(DocumentEvent e) {
                if (periodButton.isSelected()) {
                    frequency.setText(periodToFrequency(period.getText()));
                }
            }

            public void insertUpdate(DocumentEvent e) {
                change(e);
            }

            public void removeUpdate(DocumentEvent e) {
                change(e);
            }

            public void changedUpdate(DocumentEvent e) {
                change(e);
            }
        }

        public class freqFieldListener implements DocumentListener {

            public void change(DocumentEvent e) {
                if (frequencyButton.isSelected()) {
                    period.setText(frequencyToPeriod(frequency.getText()));
                }
            }

            public void insertUpdate(DocumentEvent e) {
                change(e);
            }

            public void removeUpdate(DocumentEvent e) {
                change(e);
            }

            public void changedUpdate(DocumentEvent e) {
                change(e);
            }
        }

        public class buttonListner implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == save) {
                    try {
                        Float temp;
                        temp = Float.parseFloat(period.getText());
                        timePeriod = Math.round(temp);
                    } catch (Exception excep) {
                    }
                }
                ref.dispose();
            }
        }
    }
}
