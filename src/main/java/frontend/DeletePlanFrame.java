package frontend;

import backend.config.SessionInfo;
import backend.controller.PlanController;
import backend.model.Plan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeletePlanFrame extends JFrame {
    private PlanController planController;
    private List<Plan> plans;
    private JPanel inputPanel;
    private JLabel planNameLabel;
    private JTextField planNameField;
    private JPanel buttonsPanel;
    private JButton deletePlanButton;
    private JButton cancelButton;


    public DeletePlanFrame(List<String> planNames) {
        planController = PlanController.getInstance();
        plans = planController.getFilteredPlans(planNames);

        for(Plan plan: plans) {
            if(!planNames.contains(plan.getName())) {
                plans.remove(plan);
            }
        }

        setTitle("Delete Plan");
        setSize(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        createInputPanel();
        createButtonsPanel();

        add(inputPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void createInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        planNameLabel = new JLabel("Plan Name: ");
        planNameField = new JTextField(20);

        inputPanel.add(planNameLabel);
        inputPanel.add(planNameField);
    }

    private void createButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        deletePlanButton = new JButton("Delete Plan");
        deletePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String planName = planNameField.getText();
                for(Plan plan: plans) {
                    if(planName.equalsIgnoreCase(plan.getName())) {
                        if((SessionInfo.DAUGHTER_SESSION && plan.getOwner().equalsIgnoreCase("daughter"))
                                || (!SessionInfo.DAUGHTER_SESSION && plan.getOwner().equalsIgnoreCase("father"))) {
                            if(planController.deletePlan(plan)) {
                                plans.remove(plan);
                                JOptionPane.showMessageDialog(DeletePlanFrame.this, "Plan deleted successfully!");
                                dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(DeletePlanFrame.this, "Plan could not be deleted!");
                            dispose();
                        }
                    }
                }
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        buttonsPanel.add(deletePlanButton);
        buttonsPanel.add(cancelButton);
    }
}
