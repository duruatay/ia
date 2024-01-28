package frontend;

import backend.controller.PlanController;
import backend.model.Plan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FatherScheduleFrame extends JFrame {
    private final PlanController planController = PlanController.getInstance();
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] timeSlots = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
    ArrayList<JTextArea> textAreas;
    JPanel schedule;
    JPanel scheduleHeader;
    JPanel schedulePanel;

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
                new ChangePlanFrame().setVisible(true);
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

    protected void updateSchedulePanel() {
        schedulePanel.removeAll();  // Remove the existing schedulePanel components
        createHeaderPanel();
        createSchedule();
        schedulePanel.add(scheduleHeader, BorderLayout.NORTH);
        schedulePanel.add(schedule, BorderLayout.CENTER);
        validate();  // Validate the changes
        repaint();   // Repaint the frame
    }

    private void createSchedulePanel() {
        schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout());

        createHeaderPanel();
        createSchedule();

        schedulePanel.add(scheduleHeader, BorderLayout.NORTH);
        schedulePanel.add(schedule, BorderLayout.CENTER);
    }

    private void createHeaderPanel() {
        scheduleHeader = new JPanel(new GridLayout(1, 8));
        scheduleHeader.add(new JLabel("Time"));
        for (int time = 0; time < 7; time++) {
            scheduleHeader.add(new JLabel(days[time]));
        }
    }

    private void createSchedule() {
        schedule = new JPanel(new GridLayout(9, 8));

        setTextAreas();

        for (int time = 0; time < 9; time++) {
            schedule.add(new JLabel(timeSlots[time]));

            int dayIndex = time * 7;
            int dayLimit = dayIndex + 7;
            while (dayIndex < dayLimit) {
                schedule.add(textAreas.get(dayIndex));
                dayIndex++;
            }
        }
    }

    private void setTextAreas() {
        textAreas = new ArrayList<>();

        int count = 0;
        while(count < 63) {
            JTextArea ta = new JTextArea();
            ta.setEditable(false);
            ta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            textAreas.add(ta);
            count++;
        }

        fillTextAreas();
    }

    private void fillTextAreas() {
        List<Plan> plans = planController.getFatherPlans();

        for(Plan plan: plans) {
            int column = -1;
            for(int i = 0; i < days.length; i++) {
                if(days[i].equals(plan.getDay())) {
                    column = i;
                }
            }

            int startRow = -1;
            for(int i = 0; i < timeSlots.length; i++) {
                if(timeSlots[i].substring(0, 2).equals(plan.getStart().substring(0, 2))) {
                    startRow = i;
                }
            }

            int endRow = -1;
            for(int i = 0; i < timeSlots.length; i++) {
                if(timeSlots[i].substring(0, 2).equals(plan.getEnd().substring(0, 2))) {
                    endRow = i;
                }
            }

            for(int i = (startRow * 7) + column; i < (endRow * 7) + column; i += 7) {
                textAreas.get(i).setText(textAreas.get(i).getText() + "\n" + plan.getName());
            }
        }
    }

}

