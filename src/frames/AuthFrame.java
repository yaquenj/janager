package frames;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import panels.LoginPanel;
import panels.RegisterPanel;
import utilities.WindowUtils;
import enums.Measurements;

public class AuthFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainContainer;

    public AuthFrame() throws InvalidKeySpecException, NoSuchAlgorithmException {
        //? Variables
        var screenDimension = WindowUtils.getScreenSize();
        var windowDimension = WindowUtils.scaleDimensions(Measurements.HEIGHT, (int)(4 * screenDimension.getHeight() / 9), new Dimension(10, 16));
        var icon_128 = new ImageIcon("media/Janager_icon_big.png");

        //? Window configuration
        setIconImage(icon_128.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Janager - a Java password manager");
        setSize(windowDimension);
        setResizable(false);

        //? Panels setup
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(windowDimension, () -> cardLayout.show(mainContainer, "register"));
        RegisterPanel registerPanel = new RegisterPanel(windowDimension, () -> cardLayout.show(mainContainer, "login"));

        mainContainer.add(loginPanel, "login");
        mainContainer.add(registerPanel, "register");

        setContentPane(mainContainer);

        WindowUtils.centerWindow(this, screenDimension, windowDimension);
        setVisible(true);

        // Initially show login and request focus
        cardLayout.show(mainContainer, "login");
        loginPanel.requestFocusInWindow();
    }
}
