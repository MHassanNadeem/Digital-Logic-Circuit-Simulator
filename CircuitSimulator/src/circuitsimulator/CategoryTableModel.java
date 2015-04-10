/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Hassan
 */
public class CategoryTableModel extends AbstractTableModel {

    String[] columnNames = {
        "Device",
        "Category",
        "Description"
    };
        Object[][] data = CircuitSimulator.data;
    
        public CategoryTableModel() {
System.out.println("saaaaaaaaaaaaaaaaaaaaaaaaaaaaaadfgaaaad");
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}
