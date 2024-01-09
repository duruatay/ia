package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFrame extends JFrame {

    private List<String> notifications;
    private JTextArea notificationsTextArea;

    public NotificationsFrame() {
        notifications = new ArrayList<>();
        initComponents();
        // Add a sample notification when the frame is created
        addNotification("NOTIFICATIONS");
    }

    private void initComponents() {
        setTitle("Notifications Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        notificationsTextArea = new JTextArea();
        notificationsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notificationsTextArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addNotification(String notification) {
        notifications.add(notification);
        updateNotificationsTextArea();
    }

    private void updateNotificationsTextArea() {
        StringBuilder sb = new StringBuilder();
        for (String notification : notifications) {
            sb.append(notification).append("\n");
        }
        notificationsTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotificationsFrame().setVisible(true);
        });
    }
}
