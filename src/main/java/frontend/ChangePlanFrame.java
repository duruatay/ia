package frontend;

import backend.controller.PlanController;
import backend.service.PlanService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePlanFrame extends JFrame {
    private final PlanController planController = PlanController.getInstance();
    private JTextField currentNameField;
    private JTextField newNameField;
    private JTextField dayField;
    private JTextField startTimeField;
    private JTextField endTimeField;

    public ChangePlanFrame() {
        // Set frame properties
        setTitle("Change Plan");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create and set layout manager
        setLayout(new GridLayout(6, 2));

        // Create components
        JLabel currentNameLabel = new JLabel("Current Plan Name:");
        currentNameField = new JTextField();

        JLabel newNameLabel = new JLabel("New Plan Name:");
        newNameField = new JTextField();

        JLabel dayLabel = new JLabel("Day:");
        dayField = new JTextField();

        JLabel startTimeLabel = new JLabel("Start Time:");
        startTimeField = new JTextField();

        JLabel endTimeLabel = new JLabel("End Time:");
        endTimeField = new JTextField();

        JButton changeButton = new JButton("Change Plan");
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle changing the plan with the provided inputs
                if(planController.changePlan(currentNameField.getText(),
                                            newNameField.getText(),
                                            dayField.getText(),
                                            startTimeField.getText(),
                                            endTimeField.getText())){
                    JOptionPane.showMessageDialog(ChangePlanFrame.this, "Plan changed successfully!");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(ChangePlanFrame.this, "Plan could not be changed!");
                    dispose();
                }
            }
        });

        // Add components to the frame
        add(currentNameLabel);
        add(currentNameField);
        add(newNameLabel);
        add(newNameField);
        add(dayLabel);
        add(dayField);
        add(startTimeLabel);
        add(startTimeField);
        add(endTimeLabel);
        add(endTimeField);
        add(new JLabel());
        add(changeButton);

    }
}
