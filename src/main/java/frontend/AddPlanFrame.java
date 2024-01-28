package frontend;

import backend.config.SessionInfo;
import backend.controller.PlanController;
import backend.service.PlanService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlanFrame extends JFrame {
    private final PlanController planController = PlanController.getInstance();
    private JTextField nameField;
    private JTextField dayField;
    private JTextField startTimeField;
    private JTextField endTimeField;

    public AddPlanFrame(DaughterScheduleFrame daughterScheduleFrame, FatherScheduleFrame fatherScheduleFrame) {
        // Set frame properties
        setTitle("Add Plan");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create and set layout manager
        setLayout(new GridLayout(5, 2));

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel dayLabel = new JLabel("Day:");
        dayField = new JTextField();

        JLabel startTimeLabel = new JLabel("Start Time:");
        startTimeField = new JTextField();

        JLabel endTimeLabel = new JLabel("End Time:");
        endTimeField = new JTextField();

        JButton addButton = new JButton("Add Plan");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (planController.addPlan(nameField.getText(), dayField.getText(), startTimeField.getText(), endTimeField.getText())) {
                    JOptionPane.showMessageDialog(AddPlanFrame.this, "Plan added successfully!");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(AddPlanFrame.this, "Plan could not be added!");
                    dispose();
                }
                if(SessionInfo.DAUGHTER_SESSION) {
                    daughterScheduleFrame.updateSchedulePanel();
                } else {
                    fatherScheduleFrame.updateSchedulePanel();
                }
            }
        });

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(dayLabel);
        add(dayField);
        add(startTimeLabel);
        add(startTimeField);
        add(endTimeLabel);
        add(endTimeField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);
    }


}

