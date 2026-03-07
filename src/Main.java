import javax.swing.*;
import java.awt.*;

import constants.TailwindColors;
import utilities.WindowUtils;

import static enums.Measurements.*;


public class Main {
    public static void main(String[] args) {

        var mainFrame = new JFrame();
        var screen = WindowUtils.getScreenSize();
        var loginWindowSize = WindowUtils.scaleDimensions( HEIGHT, (int)(4 * screen.getHeight() / 9), new Dimension(10, 16) );
        var icon_128 = new ImageIcon("media/Janager_icon_big.png");




        mainFrame.setIconImage(icon_128.getImage());
        mainFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        mainFrame.setLocation((int)(screen.getWidth() / 2 - loginWindowSize.getWidth() / 2), (int)(screen.getHeight() / 2 - loginWindowSize.getHeight() / 2));
        mainFrame.setTitle("Jaanager - a Java password manager!");
        mainFrame.setSize(loginWindowSize);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        mainFrame.getContentPane().setBackground(TailwindColors.SLATE_950);

    }
}