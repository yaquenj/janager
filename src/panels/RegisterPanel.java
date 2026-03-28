package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import constants.TailwindColors;
import org.jetbrains.annotations.NotNull;
import utilities.HyperlinkLabel;
import enums.Measurements;
import utilities.ImgIcon;
import utilities.UIUtils;
import utilities.WindowUtils;

public class RegisterPanel extends JPanel {

    public RegisterPanel(Dimension windowDimension, Runnable onSwitchToLogin) {
        //? Responsive dimensions for components
        var fieldDimension = new Dimension((int) (windowDimension.width * 0.62), (int) (windowDimension.height * 0.07));
        var iconSize = fieldDimension.height;
        var hGap = (int)(windowDimension.width * 0.02);
        var os_username = System.getProperty("user.name");

        //? Window elements
        JLabel registerQuestion_label = new JLabel();
        registerQuestion_label.setText("<html><p>Welcome %s,<br>please register your account:</p></html>".formatted(os_username));
        registerQuestion_label.setForeground(TailwindColors.SLATE_50);
        registerQuestion_label.setFont(registerQuestion_label.getFont().deriveFont((float) iconSize * 0.5f));
        registerQuestion_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerQuestion_label.setMaximumSize(registerQuestion_label.getPreferredSize());

        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setOpaque(false);
        credentialsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(iconSize)));

        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(iconSize));

        JLabel confirmKeyIcon_label = new JLabel();
        confirmKeyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(iconSize));

        JTextField username_textfield = UIUtils.createTextField(fieldDimension, "Username");
        JPasswordField password_textfield = UIUtils.createPasswordField(fieldDimension, "Password");
        JPasswordField confirm_password_textfield = UIUtils.createPasswordField(fieldDimension, "Confirm Password");

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

        JPanel confirmPasswordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        confirmPasswordRow.setOpaque(false);
        confirmPasswordRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPasswordRow.add(confirmKeyIcon_label);
        confirmPasswordRow.add(confirm_password_textfield);
        confirmPasswordRow.setMaximumSize(confirmPasswordRow.getPreferredSize());

        JButton registerButton = new JButton();
        registerButton.setPreferredSize(new Dimension((int) (fieldDimension.width * 0.5), (int) (fieldDimension.height * 1.1)));
        registerButton.setText("Register");
        registerButton.setBackground(TailwindColors.SLATE_800);
        registerButton.setForeground(TailwindColors.SLATE_50);
        registerButton.setFont(registerButton.getFont().deriveFont((float) iconSize * 0.45f));
        registerButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        registerButton.setFocusPainted(false);
//        registerButton.setAction();

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(registerButton);
        buttonRow.setMaximumSize(buttonRow.getPreferredSize());

        JLabel login_label = new HyperlinkLabel("Already have an account? Login here", onSwitchToLogin);
        login_label.setFont(login_label.getFont().deriveFont((float) iconSize * 0.4f));
        login_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        login_label.setMaximumSize(login_label.getPreferredSize());

        //? Set Layout for this panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(TailwindColors.SLATE_950);
        setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        add(Box.createVerticalGlue());
        add(registerQuestion_label);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.04)));
        add(credentialsPanel);
        credentialsPanel.add(usernameRow);
        credentialsPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.0125)));
        credentialsPanel.add(passwordRow);
        credentialsPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.0125)));
        credentialsPanel.add(confirmPasswordRow);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.02)));
        add(login_label);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.033)));
        add(buttonRow);
        add(Box.createVerticalGlue());
    }

}
