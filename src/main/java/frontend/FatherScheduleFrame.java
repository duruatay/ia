package frontend;

import backend.controller.PlanController;
import backend.model.Plan;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FatherScheduleFrame extends JFrame {
    private final PlanController planController = PlanController.getInstance();
    private JPanel schedulePanel;
    private JScrollPane scrollPane;
    private JTable table;
    private String[] columnNames = {"Hour", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String[][] data = new String[24][7];
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);


    public FatherScheduleFrame() {
        // Initialize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fathers' Weekly Planner");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));

        // Create the panel for menu.
        JMenuBar menuBar =new JMenuBar();
        JMenu menu = new JMenu("Menu");
        buildMenu(menu);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Create the panel for the schedule.
        createSchedulePanel();

        add(schedulePanel, BorderLayout.CENTER);

        pack();
    }


    protected void updateSchedulePanel() {
        //schedulePanel.removeAll();
        //createHeaderPanel();
        //createSchedule();
        //schedulePanel.add(scheduleHeader, BorderLayout.NORTH);
        //schedulePanel.add(schedule, BorderLayout.CENTER);
        //validate();
        //repaint();
    }

    private void buildMenu(JMenu menu) {
        JMenuItem addItem = new JMenuItem("add plan");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlanFrame(null, FatherScheduleFrame.this).setVisible(true);
            }
        });

        JMenuItem changeItem = new JMenuItem("change plan");
        changeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePlanFrame(null, FatherScheduleFrame.this).setVisible(true);
            }
        });

        JMenuItem planner1 = new JMenuItem("Father's Planner");
        planner1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FatherScheduleFrame().setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        JMenuItem planner2 = new JMenuItem("Duru's Planner");
        planner2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DaughterScheduleFrame().setVisible(true);
                setVisible(false);
                dispose();
            }
        });

        JMenuItem notifs = new JMenuItem("Notifications");
        notifs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new NotificationsFrame().setVisible(true);
               setVisible(false);
               dispose();
            }
        });

        menu.add(addItem);
        menu.add(changeItem);
        menu.add(planner1);
        menu.add(planner2);
        menu.add(notifs);
    }

    private void createSchedulePanel() {
        schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout());
        createSchedule();
        schedulePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createSchedule() {
        for (int i = 0; i < 24; i++) {
            model.setValueAt(i + ":00 - " + (i + 1) + ":00", i, 0);
        }

        table = new JTable(model);
        table.setRowHeight(40);
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer());

        scrollPane = new JScrollPane(table);
    }
}

