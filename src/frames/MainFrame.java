package frames;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import panels.ListPanel;
import panels.LoginPanel;
import panels.PasswordPanel;
import panels.RegisterPanel;
import panels.ManagePasswordPanel;
import models.User;
import utilities.WindowUtils;
import enums.Measurements;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private final Dimension loginDimension;
    private final Dimension listDimension;
    private ListPanel currentListPanel;
    private PasswordPanel currentPasswordPanel;
    private ManagePasswordPanel currentManagePasswordPanel;

    public MainFrame() {
        //? Variables
        var screenDimension = WindowUtils.getScreenSize();
        var windowDimension = WindowUtils.scaleDimensions(Measurements.HEIGHT, (int)(4 * screenDimension.getHeight() / 9), new Dimension(10, 16));
        this.loginDimension = windowDimension;
        this.listDimension = new Dimension(loginDimension.width * 2, loginDimension.height);
        var icon_128 = new ImageIcon("media/Janager_icon_big.png");

        //? Window configuration
        setIconImage(icon_128.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Janager - a Java password manager");
        setResizable(false);

        //? Panels setup
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(loginDimension,
            () -> showRegisterPanel(),
            () -> showListPanel()
        );
        RegisterPanel registerPanel = new RegisterPanel(loginDimension, () -> showLoginPanel());

        mainContainer.add(loginPanel, "login");
        mainContainer.add(registerPanel, "register");

        setContentPane(mainContainer);

        // Initial sizing
        mainContainer.setPreferredSize(loginDimension);
        pack();
        WindowUtils.centerWindow(this, screenDimension);
        setVisible(true);

        // Initially show login and request focus
        showLoginPanel();
        loginPanel.requestFocusInWindow();
    }

    private void showLoginPanel() {
        mainContainer.setPreferredSize(loginDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());
        cardLayout.show(mainContainer, "login");
    }

    private void showRegisterPanel() {
        mainContainer.setPreferredSize(loginDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());
        cardLayout.show(mainContainer, "register");
    }

    private void showListPanel() {
        //? Dummy entries for presentation
        List<User> entries = List.of(
            new User("https://github.com", "octopass123", "octocat"),
            new User("https://google.com", "google-secret-456", "user@gmail.com"),
            new User("https://jetbrains.com", "intellij-is-best-789", "developer")
        );

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        if (currentListPanel != null) mainContainer.remove(currentListPanel);

        currentListPanel = new ListPanel(listDimension, entries,
            user -> showPasswordPanel(user),
            () -> showCreatePasswordPanel(),
            user -> showEditPasswordPanel(user),
            user -> System.out.println("Remove requested for: " + user.getLogin()),
            () -> showLoginPanel()
        );
        mainContainer.add(currentListPanel, "list");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "list");
    }

    private void showPasswordPanel(User user) {
        if (currentPasswordPanel != null) mainContainer.remove(currentPasswordPanel);

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        currentPasswordPanel = new PasswordPanel(listDimension,
            user.getLogin(), user.getSalt(), user.getPasswordHash(),
            () -> cardLayout.show(mainContainer, "list"),
            () -> showLoginPanel()
        );
        mainContainer.add(currentPasswordPanel, "password");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "password");
    }

    private void showCreatePasswordPanel() {
        if (currentManagePasswordPanel != null) mainContainer.remove(currentManagePasswordPanel);

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        currentManagePasswordPanel = new ManagePasswordPanel(listDimension,
            null, "Create Password", "Create",
            user -> System.out.println("Create: " + user.getLogin()),
            () -> cardLayout.show(mainContainer, "list"),
            () -> showLoginPanel()
        );
        mainContainer.add(currentManagePasswordPanel, "manage");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "manage");
    }

    private void showEditPasswordPanel(User user) {
        if (currentManagePasswordPanel != null) mainContainer.remove(currentManagePasswordPanel);

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        currentManagePasswordPanel = new ManagePasswordPanel(listDimension,
            user, "Edit Password", "Update",
            updatedUser -> System.out.println("Update: " + updatedUser.getLogin()),
            () -> cardLayout.show(mainContainer, "list"),
            () -> showLoginPanel()
        );
        mainContainer.add(currentManagePasswordPanel, "manage");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "manage");
    }
}
