import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class WeeklyCalendar extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addEventButton;

    public WeeklyCalendar() {
        // Set the frame properties
        setTitle("Weekly Planner");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a default table model with 8 columns (hour labels + days of the week) and 24 rows (hours in a day)
        String[] columnNames = {"Hour", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[][] data = new String[24][7];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Populate the first column with hour labels
        for (int i = 0; i < 24; i++) {
            model.setValueAt(i + ":00 - " + (i + 1) + ":00", i, 0);
        }

        // Create the table with the default model
        table = new JTable(model);
        table.setRowHeight(40);
        table.getColumnModel().getColumn(3).setCellRenderer(new CustomCellRenderer(Set.of(4, 5, 6), 1, Color.PINK));

        // Create a scroll pane for the table
        scrollPane = new JScrollPane(table);

        // Create a button for adding events
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can implement the event handling for adding events here
                // For simplicity, let's just display a message for now
                JOptionPane.showMessageDialog(WeeklyCalendar.this, "Event added!");
            }
        });

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(addEventButton, BorderLayout.SOUTH);

        // Set the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeeklyCalendar();
            }
        });
    }

    private static class CustomCellRenderer extends DefaultTableCellRenderer {
        private Set<Integer> selectedRows;
        private int row;
        private int divisionFactor;
        private Color color;

        public CustomCellRenderer(Set<Integer> selectedRows, int divisionFactor, Color color) {
            this.selectedRows = selectedRows;
            this.divisionFactor = divisionFactor;
            this.color = color;
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
}
