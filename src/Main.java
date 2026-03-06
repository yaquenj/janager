import javax.swing.*;
import java.awt.*;

import utilities.WindowUtils;

import static enums.Measurements.*;

public class Main {
    public static void main(String[] args) {

        var mainFrame = new JFrame();
        var loginWindowSize = WindowUtils.scaleDimensions( WIDTH, 1920, new Dimension(16, 9) );





        mainFrame.setSize(loginWindowSize);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }
}