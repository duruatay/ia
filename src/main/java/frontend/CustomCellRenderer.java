package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Set;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    private Set<Integer> selectedRows;
    private int row;
    private int divisionFactor;
    private Color color;

    public CustomCellRenderer() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.row = row;

        // Set the background color of the specified column to gray
        if (selectedRows.contains(row)) {
            component.repaint();
        } else {
            component.setBackground(table.getBackground());
        }

        return component;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (selectedRows.contains(row)) {
            int cellHeight = getHeight();

            int selectedRegionHeight = cellHeight / divisionFactor;

            // Set the transparency level (0.5f means 50% opaque)
            Graphics2D g2d = (Graphics2D) g.create();
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
            g2d.setComposite(alpha);

            // Draw the background color for the selected region
            g2d.setColor(color);
            g2d.fillRect(0, 0, getWidth(), selectedRegionHeight);

            g2d.dispose(); // Dispose of the temporary Graphics2D object
        }
    }
}
