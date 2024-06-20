package LockerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LockerApp extends JFrame {
    private JTextField passcodeField;
    private JLabel titleLabel, statusLabel, logoLabel;
    private JButton enterButton, clearButton;
    private String savedPassword = "";

    public LockerApp() {
        setTitle("Locker Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setOpaque(false);
        JPanel logoTitlePanel = new JPanel();
        logoTitlePanel.setLayout(new BoxLayout(logoTitlePanel, BoxLayout.Y_AXIS));
        logoTitlePanel.setOpaque(false);
        try {
            BufferedImage logoImage = ImageIO.read(new File("image/rupp logo.png"));
            int logoWidth = 80;
            int logoHeight = 80;
            Image scaledLogoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
            ImageIcon logoIcon = new ImageIcon(scaledLogoImage);
            logoLabel = new JLabel(logoIcon);
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            logoTitlePanel.add(logoLabel);
        } catch (Exception e) {
            System.out.println("Error loading logo image: " + e.getMessage());
        }
        titleLabel = new JLabel("Locker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        logoTitlePanel.add(titleLabel);
        titlePanel.add(logoTitlePanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passcodeField = new JTextField(15);
        passcodeField.setFont(new Font("Arial", Font.PLAIN, 20));
        passcodeField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(passcodeField, gbc);
        statusLabel = new JLabel("Enter Password", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        centerPanel.add(statusLabel, gbc);
        JPanel numberPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        numberPanel.setOpaque(false);
        for (int i = 1; i <= 9; i++) {
            addButton(numberPanel, String.valueOf(i));
        }
        addButton(numberPanel, "0");
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        centerPanel.add(numberPanel, gbc);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setOpaque(false);
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passcodeField.setText("");
                statusLabel.setText("Enter Password");
            }
        });
        buttonPanel.add(clearButton);
        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.PLAIN, 18));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredPasscode = passcodeField.getText();
                if (enteredPasscode.isEmpty()) {
                    statusLabel.setText("Password cannot be blank");
                    return;
                }
                if (!enteredPasscode.matches("\\d+")) {
                    statusLabel.setText("Password must be numeric");
                    return;
                }
                if (savedPassword.isEmpty()) {
                    savedPassword = enteredPasscode;
                    statusLabel.setText("Password Set");
                } else {
                    if (enteredPasscode.equals(savedPassword)) {
                        statusLabel.setText("Correct Password");
                    } else {
                        statusLabel.setText("Incorrect Password");
                    }
                }
            }
        });
        buttonPanel.add(enterButton);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        centerPanel.add(buttonPanel, gbc);
        add(centerPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passcodeField.setText(passcodeField.getText() + text);
            }
        });
        panel.add(button);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LockerApp();
            }
        });
    }
}
