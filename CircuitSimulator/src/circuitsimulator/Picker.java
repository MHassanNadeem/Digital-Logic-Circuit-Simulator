/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Hassan
 */
public class Picker extends JFrame {

    final int height = 500;
    final int width = 800;
    Picker picker;
    JPanel left;
    JPanel center;
    JPanel right;
    TableRowSorter<TableModel> sorter = null;
    RowFilter<TableModel, Object> keywordFilter = null;
    RowFilter<TableModel, Object> categoryFilter = null;
    ArrayList<RowFilter<TableModel, Object>> filters;
    String selected;

    public Picker() {

        this.setTitle("Components Picker");
        this.setSize(new Dimension(width, height));
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        JLabel searchLabel = new JLabel("Search: ");
        final JTextField searchField = new JTextField(10);
//        searchField.set
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                searchField.selectAll();
                System.out.println(searchQuery);
                if (searchQuery.length() == 0) {
                    filters.remove(keywordFilter);
                    sorter.setRowFilter(RowFilter.andFilter(filters));
                } else {
                    filters.remove(keywordFilter);
                    keywordFilter = RowFilter.regexFilter("(?i)" + searchQuery);
                    filters.add(keywordFilter);
                    sorter.setRowFilter(RowFilter.andFilter(filters));
                }
            }
        });

//        ArrayList<String> lol = new ArrayList<String>();
//        for (int i = 0; i < 5; i++) {
//            if (i == 1) {
//                lol.add("2");
//                continue;
//            }
//            lol.add("Item #" + i);
//        }
        final JList category = new JList(CircuitSimulator.Categories);
        JScrollPane categoryScrollPane = new JScrollPane(category);
        category.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String categoryQuery = (String) category.getSelectedValue();
                    System.out.println("Item Selected: " + categoryQuery);
                    if (categoryQuery.equals("(All Categories)")) {
                        filters.remove(categoryFilter);
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                    } else {
                        filters.remove(categoryFilter);
                        categoryFilter = RowFilter.regexFilter("(?i)" + categoryQuery);
                        filters.add(categoryFilter);
                        sorter.setRowFilter(RowFilter.andFilter(filters));
                    }
                }
            }
        });
        JPanel categoryPane = new JPanel();
//        categoryPane.setLayout();
        categoryPane.add(new JLabel("Categories: "));
        categoryPane.add(categoryScrollPane);
        categoryScrollPane.setPreferredSize(new Dimension(width / 8, 200));

        left = new JPanel();
//        left.setSize(10,800);
        left.setPreferredSize(new Dimension(width / 8, height));
        //   left.setAlignmentX(LEFT_ALIGNMENT);
        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));
        center = new JPanel();
        right = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2d = (Graphics2D) g;
                if (selected != null) {
                    CircuitSimulator.classForName(selected, 50, 25).draw(g2d);
                }
            }
        };
        GridLayout gl = new GridLayout(0, 1);
        right.setLayout(gl);

        JPanel searchPane = new JPanel();
        searchPane.add(searchLabel);
        searchPane.add(searchField);
        searchPane.add(new JButton("Clear"));
        searchPane.setPreferredSize(new Dimension(left.getWidth(), 30));
//        searchPane.setBackground(Color.red);
        left.add(searchPane);
        left.add(new JSeparator(SwingConstants.HORIZONTAL));
        left.add(new JLabel("Category: "));
        left.add(categoryScrollPane);


        JButton component = new JButton(Images.IC);
//        component.setFocusable(false);
        component.setContentAreaFilled(false);
        component.setPreferredSize(new Dimension(150, 150));
        component.setBorder(null);
//        this.setBackground(Color.WHITE);
        right.add(new JLabel("   ------------IC Preview------------"));
        right.add(component);


        this.add(left);
        this.add(addTable());
        this.add(right);
//        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JScrollPane addTable() {
        TableModel model = new CategoryTableModel();
        final JTable table = new JTable(model);
        // table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //adding filter
        RowFilter<TableModel, Object> compoundRowFilter = null;
        filters = new ArrayList<RowFilter<TableModel, Object>>();
        compoundRowFilter = RowFilter.andFilter(filters);
        sorter = new TableRowSorter<TableModel>(model);
        sorter.setRowFilter(compoundRowFilter);
        table.setRowSorter(sorter);
        ///

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                right.repaint();
                selected = (String) table.getValueAt(((JTable)e.getSource()).getSelectedRow(), 0);
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    System.out.println("row: " + row + " column: " + column);
                    System.out.println(table.getValueAt(row, 0));
                    ComponentsViewer.addToList((String) table.getValueAt(row, 0));
                }
            }
        });

        return scrollPane;
    }

    void changeICPreview() {
    }
}
