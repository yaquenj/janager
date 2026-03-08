package frames;

import constants.TailwindColors;
import utilities.WindowUtils;

import javax.swing.*;
import java.awt.*;

import enums.Measurements;
import utilities.ImgIcon;

public class LoginFrame extends JFrame {
    public LoginFrame() {

        //? Variables
        var screen = WindowUtils.getScreenSize();
        var loginWindowSize = WindowUtils.scaleDimensions( Measurements.HEIGHT, (int)(4 * screen.getHeight() / 9), new Dimension(10, 16) );
        var icon_128 = new ImageIcon("media/Janager_icon_big.png");
        var os_username = System.getProperty("user.name");

        //? Window elements

        JLabel loginQuestion_label = new JLabel();
        loginQuestion_label.setText("Welcome %s, please enter your credentials:".formatted(os_username));

        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(32)));
        userIcon_label.setText("Username");

        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(32));
        keyIcon_label.setText("Password");




        //! Window code section
        setIconImage(icon_128.getImage());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocation((int)(screen.getWidth() / 2 - loginWindowSize.getWidth() / 2), (int)(screen.getHeight() / 2 - loginWindowSize.getHeight() / 2));
        setTitle("Jaanager - a Java password manager!");
        setSize(loginWindowSize);
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(TailwindColors.SLATE_950);
        //? Add Elements to the window
        setLayout(new FlowLayout());
        add(loginQuestion_label);
        add(userIcon_label);
        add(keyIcon_label);

    }
}
