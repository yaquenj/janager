package frames;

import javax.swing.*;
import java.awt.*;

import constants.TailwindColors;
import utilities.HyperlinkLabel;
import utilities.WindowUtils;
import enums.Measurements;
import utilities.ImgIcon;


public class LoginFrame extends JFrame {
    public LoginFrame() {

        //? Variables
        var screenDimension = WindowUtils.getScreenSize();
        var windowDimension = WindowUtils.scaleDimensions( Measurements.HEIGHT, (int)(4 * screenDimension.getHeight() / 9), new Dimension(10, 16) );
        var icon_128 = new ImageIcon("media/Janager_icon_big.png");
        var os_username = System.getProperty("user.name");

        //? Window elements

        JLabel loginQuestion_label = new JLabel();
        loginQuestion_label.setText("<html><p text-align='center'>Welcome %s,<br>please enter your credentials:</p></html>".formatted(os_username));
        loginQuestion_label.setForeground(TailwindColors.SLATE_50);
        loginQuestion_label.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loginQuestion_label.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setOpaque(false);
        credentialsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(32)));

        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(32));

        JTextField username_textfield = new JTextField(16);
        username_textfield.setBackground(TailwindColors.SLATE_900);
        username_textfield.setForeground(TailwindColors.SLATE_50);
        username_textfield.setCaretColor(TailwindColors.SLATE_50);
        username_textfield.setBorder(null);

        JPasswordField password_textfield = new JPasswordField(16);
        password_textfield.setBackground(TailwindColors.SLATE_900);
        password_textfield.setForeground(TailwindColors.SLATE_50);
        password_textfield.setCaretColor(TailwindColors.SLATE_50);
        password_textfield.setBorder(null);

        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        usernameRow.setOpaque(false);
        usernameRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameRow.add(userIcon_label);
        usernameRow.add(username_textfield);

        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        passwordRow.setOpaque(false);
        passwordRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordRow.add(keyIcon_label);
        passwordRow.add(password_textfield);

        JButton loginButton = new JButton();
        loginButton.setSize(100, 50);
        loginButton.setText("Login");
        loginButton.setBackground(TailwindColors.SLATE_800);
        loginButton.setForeground(TailwindColors.SLATE_50);
        loginButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(loginButton);

        JLabel register_label = new HyperlinkLabel("Don't have an account?", () -> {
            IO.println("Jest");
        });
        register_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        //! Window code section
        setIconImage(icon_128.getImage());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Janager - a Java password manager!");
        setSize(windowDimension);
        setResizable(false);

        //? Add Elements to the window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(TailwindColors.SLATE_950);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        setContentPane(mainPanel);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(loginQuestion_label);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(credentialsPanel);
        credentialsPanel.add(usernameRow);
        credentialsPanel.add(Box.createVerticalStrut(6));
        credentialsPanel.add(passwordRow);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonRow);
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(register_label);
        mainPanel.add(Box.createVerticalGlue());

        WindowUtils.centerWindow(this, screenDimension, windowDimension);
        revalidate();
        repaint();
        setVisible(true);

    }
}
