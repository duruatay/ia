package frontend;

import backend.controller.PlanController;
import backend.model.Plan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlanFrame extends JFrame {
    private PlanController planController;
    private List<String> planNames;
    private JScrollPane plansScrollPane;
    private JPanel mainPlanPanel;
    private JPanel buttonsPanel;
    private JButton deletePlanButton;

    public PlanFrame(List<String> planNames) {
        this.planNames = planNames;
        planController = PlanController.getInstance();

        setTitle("Plan Details");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        createPlanScrollPane();
        createButtonsPanel();

        add(plansScrollPane, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void createPlanScrollPane() {
        plansScrollPane = new JScrollPane();
        plansScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPlanPanel = new JPanel();
        mainPlanPanel.setLayout(new BoxLayout(mainPlanPanel, BoxLayout.Y_AXIS));

        for(String planName: planNames) {
            Plan plan = planController.getPlanByName(planName);
            if(plan != null) {
                JLabel planNameLabel = new JLabel("Plan Name: " + planName);
                planNameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                planNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel startTimeLabel = new JLabel("Starting Time: " + plan.getStart());
                startTimeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                startTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel endTimeLabel = new JLabel("Ending Time: " +plan.getEnd());
                endTimeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                endTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JLabel spaceLabel = new JLabel("----------------");
                spaceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                spaceLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel planPanel = new JPanel();
                planPanel.setLayout(new BoxLayout(planPanel, BoxLayout.Y_AXIS));

                planPanel.add(planNameLabel);
                planPanel.add(startTimeLabel);
                planPanel.add(endTimeLabel);
                planPanel.add(spaceLabel);

                mainPlanPanel.add(planPanel);
            }

            plansScrollPane.setViewportView(mainPlanPanel);
        }
    }

    private void createButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        deletePlanButton = new JButton("Delete Plans");
        deletePlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new DeletePlanFrame(planNames).setVisible(true);
            }
        });

        buttonsPanel.add(deletePlanButton);
    }
}
