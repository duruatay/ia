package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DaughterScheduleFrame extends JFrame {
    public DaughterScheduleFrame() {
        // Initialize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Daughters' Weekly Planner");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));

        // Create the panel for menu.
        JMenuBar menuBar =new JMenuBar();
        JMenu menu = new JMenu("Menu");
        buildMenu(menu);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Create the panel for the schedule.
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout());

        JPanel scheduleHeaders = createHeaderPanel();
        JPanel schedule = createSchedulePanel();

        schedulePanel.add(scheduleHeaders, BorderLayout.NORTH);
        schedulePanel.add(schedule, BorderLayout.CENTER);

        add(schedulePanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }

    private void buildMenu(JMenu menu) {
        JMenuItem addItem = new JMenuItem("add plan");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPlanFrame().setVisible(true);
            }
        });

        JMenuItem changeItem = new JMenuItem("change plan");
        changeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePlanFrame().setVisible(true);
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
            }
        });

        menu.add(addItem);
        menu.add(changeItem);
        menu.add(planner1);
        menu.add(planner2);
        menu.add(notifs);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 8));
        headerPanel.add(new JLabel("Time"));
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int time = 0; time < 7; time++) {
            headerPanel.add(new JLabel(days[time]));
        }
        return headerPanel;
    }

    private JPanel createSchedulePanel() {
        JPanel schedulePanel = new JPanel(new GridLayout(9, 8));
        String[] timeSlots = {"9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "3 PM", "4 PM", "5 PM"};

        for (int time = 0; time < 9; time++) {
            schedulePanel.add(new JLabel(timeSlots[time]));

            for (int day = 0; day < 7; day++) {
                JTextArea textArea = new JTextArea();
                textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                schedulePanel.add(textArea);
            }
        }
        return schedulePanel;
    }

}

