package frontend;

import backend.config.SessionInfo;
import backend.controller.PlanController;
import backend.model.Plan;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DaughterScheduleFrame extends JFrame {
    private final PlanController planController = PlanController.getInstance();
    private JPanel schedulePanel;
    private JScrollPane scrollPane;
    private JTable table;
    private String[] columnNames = {"Hour", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String[][] data = new String[24][7];
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);


    public DaughterScheduleFrame() {
        // Initialize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Daughters' Weekly Planner");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create the panel for menu.
        JMenuBar menuBar =new JMenuBar();
        JMenu menu = new JMenu("Menu");
        buildMenu(menu);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        createSchedulePanel();

        add(schedulePanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }


    private void buildMenu(JMenu menu) {
        JMenuItem addItem = new JMenuItem("add plan");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlanFrame(DaughterScheduleFrame.this, null).setVisible(true);
            }
        });

        JMenuItem changeItem = new JMenuItem("change plan");
        changeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePlanFrame(DaughterScheduleFrame.this, null).setVisible(true);
            }
        });

        JMenuItem planner1 = new JMenuItem("Father's Planner");
        planner1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change functionality
                new FatherScheduleFrame().setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        JMenuItem planner2 = new JMenuItem("Daughter's Planner");
        planner2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DaughterScheduleFrame().setVisible(true);
                setVisible(false);
                dispose();
            }
        });


        menu.add(planner1);
        menu.add(planner2);
        if (SessionInfo.DAUGHTER_SESSION) {
            menu.add(addItem);
            menu.add(changeItem);
        }

    }

    protected void updateSchedulePanel() {
        remove(schedulePanel);
        createSchedulePanel();
        add(schedulePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void createSchedulePanel() {
        schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout());
        createSchedule();
        schedulePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createSchedule() {
        for(int i = 0; i < 24; i++) {
            model.setValueAt(i + ":00 - " + (i + 1) + ":00", i, 0);
        }
        fillTableModel();

        table = new JTable(model);
        table.setRowHeight(40);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                String plansStr = (String) table.getModel().getValueAt(row, col);
                List<String> planNames = List.of(plansStr.split("\n"));

                new PlanFrame(planNames).setVisible(true);
            }
        });

        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineTableCellRenderer());
        }

        scrollPane = new JScrollPane(table);
    }

    private void fillTableModel() {
        List<Plan> plans = planController.getDaughterPlans();

        clearTableModel();

        for(Plan p: plans) {
            String startTime = p.getStart();
            String endTime = p.getEnd();
            String day = p.getDay();

            int startHour = Integer.parseInt(startTime.split(":")[0]);
            int endHour = Integer.parseInt(endTime.split(":")[0]);

            int col = -1;
            for(int i = 0; i < columnNames.length; i++) {
                if(day.equalsIgnoreCase(columnNames[i])) {
                    col = i;
                    break;
                }
            }

            for(int i = 0; i < model.getRowCount(); i++) {
                int val = Integer.parseInt(model.getValueAt(i, 0).toString().split(":")[0]);
                if(startHour == val){
                    int rowCount = endHour - startHour;

                    while(rowCount >= 0 && (i + rowCount) < model.getRowCount()) {
                        String existingCellValue = (model.getValueAt(i + rowCount, col) != null) ? model.getValueAt(i + rowCount, col).toString() : "";
                        String newCellVal = existingCellValue + (existingCellValue.isEmpty() ? "" : "\n") + p.getName();
                        model.setValueAt(newCellVal, i + rowCount, col);
                        rowCount--;
                    }
                }
            }
        }

    }

    private void clearTableModel() {
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 1; j < model.getColumnCount(); j++) {
                model.setValueAt("", i, j);
            }
        }
    }


}

