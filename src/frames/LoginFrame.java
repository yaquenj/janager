package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

        //? Responsive dimensions for components
        var fieldDimension = new Dimension((int) (windowDimension.width * 0.62), (int) (windowDimension.height * 0.07));
        var iconSize = fieldDimension.height;
        var hGap = (int)(windowDimension.width * 0.02);

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

        JTextField username_textfield = getJTextField(fieldDimension);
        JPasswordField password_textfield = getJPasswordField(fieldDimension);

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

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(loginButton);
        buttonRow.setMaximumSize(buttonRow.getPreferredSize());

        JLabel register_label = new HyperlinkLabel("Don't have an account?", () -> {
            IO.println("User wants to register");
        });
        register_label.setFont(register_label.getFont().deriveFont((float) iconSize * 0.4f));
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
        mainPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.04)));
        mainPanel.add(credentialsPanel);
        credentialsPanel.add(usernameRow);
        credentialsPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.0125)));
        credentialsPanel.add(passwordRow);
        mainPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.02)));
        mainPanel.add(register_label);
        mainPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.033)));
        mainPanel.add(buttonRow);
        mainPanel.add(Box.createVerticalGlue());

        WindowUtils.centerWindow(this, screenDimension, windowDimension);
        revalidate();
        repaint();
        setVisible(true);
        mainPanel.requestFocusInWindow();

    }

    private static @NotNull JTextField getJTextField(Dimension dimension) {
        JTextField username_textfield = new JTextField();
        username_textfield.setPreferredSize(dimension);
        username_textfield.setBackground(TailwindColors.SLATE_900);
        username_textfield.setForeground(TailwindColors.SLATE_400);
        username_textfield.setCaretColor(TailwindColors.SLATE_400);
        username_textfield.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        username_textfield.setFont(username_textfield.getFont().deriveFont((float) dimension.height * 0.45f));
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

    private static @NotNull JPasswordField getJPasswordField(Dimension dimension) {
        JPasswordField password_textfield = new JPasswordField();
        password_textfield.setPreferredSize(dimension);
        password_textfield.setBackground(TailwindColors.SLATE_900);
        password_textfield.setForeground(TailwindColors.SLATE_400);
        password_textfield.setCaretColor(TailwindColors.SLATE_400);
        password_textfield.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        password_textfield.setFont(password_textfield.getFont().deriveFont((float) dimension.height * 0.45f));
        password_textfield.setText("Password");
        password_textfield.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(password_textfield.getPassword()).equals("Password")) {
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
