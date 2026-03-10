package frames;

import javax.swing.*;
import java.awt.*;

import constants.TailwindColors;
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
        loginQuestion_label.setText("Welcome %s, please enter your credentials:".formatted(os_username));

        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setBackground(new Color(0,0,0,0));

        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(32)));

        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(32));

        JTextField username_textfield = new JTextField(16);
        username_textfield.setBackground(TailwindColors.SLATE_900);
        username_textfield.setBorder(null);

        JPasswordField password_textfield = new JPasswordField(16);
        password_textfield.setBackground(TailwindColors.SLATE_900);
        password_textfield.setBorder(null);

        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        usernameRow.setBackground(new Color(0,0,0,0));
        usernameRow.add(userIcon_label);
        usernameRow.add(username_textfield);

        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        passwordRow.setBackground(new Color(0,0,0,0));
        passwordRow.add(keyIcon_label);
        passwordRow.add(password_textfield);

        JButton loginButton = new JButton();
        loginButton.setSize(100, 50);
        loginButton.setText("Login");
        loginButton.setBackground(TailwindColors.SLATE_800);
        loginButton.setForeground(TailwindColors.SLATE_50);
        loginButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.add(loginButton);

        //! Window code section
        setIconImage(icon_128.getImage());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Janager - a Java password manager!");
        setSize(windowDimension);
        setResizable(false);
        WindowUtils.centerWindow(this, screenDimension, windowDimension);
        setVisible(true);
        getContentPane().setBackground(TailwindColors.SLATE_950);

        //? Add Elements to the window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0,0,0,0));
        setContentPane(mainPanel);

        mainPanel.add(loginQuestion_label);
        mainPanel.add(credentialsPanel);
        credentialsPanel.add(usernameRow);
        credentialsPanel.add(passwordRow);
        mainPanel.add(buttonRow);

    }
}
