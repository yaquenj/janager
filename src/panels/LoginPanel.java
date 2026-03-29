package panels;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import constants.TailwindColors;
import utilities.*;

public class LoginPanel extends JPanel {

    public LoginPanel(Dimension windowDimension, Runnable onSwitchToRegister) {
        //? Responsive dimensions for components
        var fieldDimension = new Dimension((int) (windowDimension.width * 0.62), (int) (windowDimension.height * 0.07));
        var iconSize = fieldDimension.height;
        var hGap = (int)(windowDimension.width * 0.02);
        var os_username = System.getProperty("user.name");

        //? Window elements
        JLabel loginQuestion_label = new JLabel();
        loginQuestion_label.setText("<html><p>Welcome %s,<br>please enter your credentials:</p></html>".formatted(os_username));
        loginQuestion_label.setForeground(TailwindColors.SLATE_50);
        loginQuestion_label.setFont(loginQuestion_label.getFont().deriveFont((float) iconSize * 0.5f));
        loginQuestion_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginQuestion_label.setMaximumSize(loginQuestion_label.getPreferredSize());

        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setOpaque(false);
        credentialsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(iconSize)));

        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(iconSize));

        JTextField username_textfield = UIUtils.createTextField(fieldDimension, "Username");
        JPasswordField password_textfield = UIUtils.createPasswordField(fieldDimension, "Password");

        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        usernameRow.setOpaque(false);
        usernameRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameRow.add(userIcon_label);
        usernameRow.add(username_textfield);
        usernameRow.setMaximumSize(usernameRow.getPreferredSize());

        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        passwordRow.setOpaque(false);
        passwordRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordRow.add(keyIcon_label);
        passwordRow.add(password_textfield);
        passwordRow.setMaximumSize(passwordRow.getPreferredSize());

        JButton loginButton = new JButton();
        loginButton.setPreferredSize(new Dimension((int) (fieldDimension.width * 0.5), (int) (fieldDimension.height * 1.1)));
        loginButton.setText("Login");
        loginButton.setBackground(TailwindColors.SLATE_800);
        loginButton.setForeground(TailwindColors.SLATE_50);
        loginButton.setFont(loginButton.getFont().deriveFont((float) iconSize * 0.45f));
        loginButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(_ -> {
            try {

                boolean success = AuthUtils.loginUser(username_textfield.getText(), password_textfield.getPassword());

                if (success) {
                    DialogUtils.showInfoDialog("Login successful", "You have logged in!");
                }

            } catch (InvalidKeySpecException ex) {
                System.err.println("Couldn't login due to InvalidKeySpecException error (ref: janager.src.panels.LoginPanel.loginButton): " + ex.getMessage());
                DialogUtils.showErrorDialog("Login error", "Couldn't login due to InvalidKeySpecException error (ref: janager.src.panels.LoginPanel.loginButton): " + ex.getMessage());
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Couldn't login due to NoSuchAlgorithmException error (ref: janager.src.panels.LoginPanel.loginButton): " + ex.getMessage());
                DialogUtils.showErrorDialog("Login error", "Couldn't login due to NoSuchAlgorithmException error (ref: janager.src.panels.LoginPanel.loginButton): " + ex.getMessage());
            }
        });

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(loginButton);
        buttonRow.setMaximumSize(buttonRow.getPreferredSize());

        JLabel register_label = new HyperlinkLabel("Don't have an account?", onSwitchToRegister);
        register_label.setFont(register_label.getFont().deriveFont((float) iconSize * 0.4f));
        register_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        register_label.setMaximumSize(register_label.getPreferredSize());

        //? Set Layout for this panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(TailwindColors.SLATE_950);
        setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        add(Box.createVerticalGlue());
        add(loginQuestion_label);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.04)));
        add(credentialsPanel);
        credentialsPanel.add(usernameRow);
        credentialsPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.0125)));
        credentialsPanel.add(passwordRow);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.02)));
        add(register_label);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.033)));
        add(buttonRow);
        add(Box.createVerticalGlue());
    }

}
