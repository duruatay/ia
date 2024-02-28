package frontend;

import backend.controller.LoginController;
import backend.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private LoginController loginController;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        loginController = new LoginController();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // Set preferred size for smaller text fields
        Dimension textFieldSize = new Dimension(150, 30);
        usernameField.setPreferredSize(textFieldSize);
        passwordField.setPreferredSize(textFieldSize);

        JButton loginButton = getLoginButton();

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(loginButton);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton getLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                User user = loginController.login(username, password);

                if(user == null){
                    JOptionPane.showMessageDialog(LoginPage.this, "Login failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(user.isDaughter()) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                    DaughterScheduleFrame daughter = new DaughterScheduleFrame();
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                    FatherScheduleFrame father = new FatherScheduleFrame();
                    dispose();
                }

                // Clear fields after login attempt
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        return loginButton;
    }

}
