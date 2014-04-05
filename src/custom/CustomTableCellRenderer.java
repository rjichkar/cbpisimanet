package custom;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof String) {
            String msg = (String) value;
            if (msg.equalsIgnoreCase("trusted")) {
                // You can also customize the Font and Foreground this way
                cell.setFont(new Font("Serif", Font.BOLD, 12));
                cell.setForeground(new Color(0,128,0));
            } else if (msg.equalsIgnoreCase("not trusted")) {
                cell.setFont(new Font("Serif", Font.BOLD, 12));
                cell.setForeground(Color.red);
            } else {
                cell.setForeground(Color.black);
            }
        }
        return cell;
    }
}
