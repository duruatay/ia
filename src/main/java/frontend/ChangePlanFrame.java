package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePlanFrame extends JFrame {
    private JTextField currentNameField;
    private JTextField newNameField;
    private JTextField dayField;
    private JTextField startTimeField;
    private JTextField endTimeField;

    public ChangePlanFrame() {
        // Set frame properties
        setTitle("Change Plan");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        add(new JLabel()); // Empty label for spacing
        add(changeButton);

        // Add action listener to the button
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle changing the plan with the provided inputs
                changePlan();
            }
        });
    }

    private void changePlan() {
        // Retrieve inputs from the text fields
        String currentName = currentNameField.getText();
        String newName = newNameField.getText();
        String day = dayField.getText();
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();

        // Perform the logic to change the plan (you can modify this part based on your requirements)
        System.out.println("Plan changed: " + currentName + " to " + newName + ", " + day + ", " + startTime + " - " + endTime);

        // Clear the input fields after changing the plan
        currentNameField.setText("");
        newNameField.setText("");
        dayField.setText("");
        startTimeField.setText("");
        endTimeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChangePlanFrame().setVisible(true);
            }
        });
    }
}
