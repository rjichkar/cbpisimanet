package custom;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {

    private String[] columnNames;
    private Object[][] data;

    public CustomTableModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return data.length;
    }

    public Object getValueAt(int row, int column) {
        return data[row][column];
    }
}
