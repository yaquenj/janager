package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

import constants.TailwindColors;
import org.jetbrains.annotations.NotNull;
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
        loginQuestion_label.setText("<html><p>Welcome %s,<br>please enter your credentials:</p></html>".formatted(os_username));
        loginQuestion_label.setForeground(TailwindColors.SLATE_50);
        loginQuestion_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginQuestion_label.setMaximumSize(loginQuestion_label.getPreferredSize());

        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setOpaque(false);
        credentialsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(32)));

        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(32));

        JTextField username_textfield = getJTextField();
        JPasswordField password_textfield = getJPasswordField();

        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        usernameRow.setOpaque(false);
        usernameRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameRow.add(userIcon_label);
        usernameRow.add(username_textfield);
        usernameRow.setMaximumSize(usernameRow.getPreferredSize());

        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        passwordRow.setOpaque(false);
        passwordRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordRow.add(keyIcon_label);
        passwordRow.add(password_textfield);
        passwordRow.setMaximumSize(passwordRow.getPreferredSize());

        JButton loginButton = new JButton();
        loginButton.setSize(100, 50);
        loginButton.setText("Login");
        loginButton.setBackground(TailwindColors.SLATE_800);
        loginButton.setForeground(TailwindColors.SLATE_50);
        loginButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(loginButton);
        buttonRow.setMaximumSize(buttonRow.getPreferredSize());

        JLabel register_label = new HyperlinkLabel("Don't have an account?", () -> {
            IO.println("User wants to register");
        });
        register_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        register_label.setMaximumSize(register_label.getPreferredSize());

        //! Window code section
        setIconImage(icon_128.getImage());
//      setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        mainPanel.add(register_label);
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(buttonRow);
        mainPanel.add(Box.createVerticalGlue());

        WindowUtils.centerWindow(this, screenDimension, windowDimension);
        revalidate();
        repaint();
        setVisible(true);
        mainPanel.requestFocusInWindow();

    }
    
    private static @NotNull JTextField getJTextField() {
        JTextField username_textfield = new JTextField(16);
        username_textfield.setBackground(TailwindColors.SLATE_900);
        username_textfield.setForeground(TailwindColors.SLATE_400);
        username_textfield.setCaretColor(TailwindColors.SLATE_400);
        username_textfield.setBorder(null);
        username_textfield.setText("Username");
        username_textfield.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username_textfield.getText().equals("Username")) {
                    username_textfield.setText(null);
                    username_textfield.setForeground(TailwindColors.SLATE_50);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (username_textfield.getText().isEmpty()) {
                    username_textfield.setForeground(TailwindColors.SLATE_400);
                    username_textfield.setText("Username");
                }
            }
        });
        return username_textfield;
    }

    private static @NotNull JPasswordField getJPasswordField() {
        JPasswordField password_textfield = new JPasswordField(16);
        password_textfield.setBackground(TailwindColors.SLATE_900);
        password_textfield.setForeground(TailwindColors.SLATE_400);
        password_textfield.setCaretColor(TailwindColors.SLATE_400);
        password_textfield.setBorder(null);
        password_textfield.setText("Password");
        password_textfield.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (password_textfield.getText().equals("Password")) {
                    password_textfield.setText("");
                    password_textfield.setForeground(TailwindColors.SLATE_50);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(password_textfield.getPassword()).isEmpty()) {
                    password_textfield.setForeground(TailwindColors.SLATE_400);
                    password_textfield.setText("Password");
                }
            }
        });
        return password_textfield;
    }
}
