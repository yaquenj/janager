package frames;

import javax.swing.*;
import java.awt.*;

import models.Credential;
import panels.ListPanel;
import panels.LoginPanel;
import panels.CredentialPanel;
import panels.RegisterPanel;
import panels.ManageCredentialPanel;
import models.User;
import utilities.*;
import enums.Measurements;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private final Dimension loginDimension;
    private final Dimension listDimension;
    private User loggedUser;
    private char[] masterPassword;
    private ListPanel currentListPanel;
    private CredentialPanel currentCredentialPanel;
    private ManageCredentialPanel currentManageCredentialPanel;

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
            (user, password) -> {
                this.loggedUser = user;
                this.masterPassword = password;
                showListPanel();
            }
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
        this.loggedUser = null;
        if (this.masterPassword != null) {
            java.util.Arrays.fill(this.masterPassword, '\0');
            this.masterPassword = null;
        }
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
        if (loggedUser == null) {
            showLoginPanel();
            return;
        }

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        if (currentListPanel != null) mainContainer.remove(currentListPanel);

        currentListPanel = new ListPanel(listDimension, loggedUser.getId(),
            credential -> showCredentialPanel(credential),
            () -> showCreateCredentialPanel(),
            credential -> showEditCredentialPanel(credential),
            credential -> {
                if (DialogUtils.showConfirmDialog("Delete Credential", "Are you sure you want to delete this credential?")) {
                    DbCredentialUtils.deleteCredential(credential.getCredentialId());
                    showListPanel();
                }
            },
            () -> showLoginPanel()
        );
        mainContainer.add(currentListPanel, "list");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "list");
    }

    private void showCredentialPanel(Credential credential) {
        if (currentCredentialPanel != null) mainContainer.remove(currentCredentialPanel);

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        currentCredentialPanel = new CredentialPanel(listDimension,
            credential.getUrl(), credential.getLogin(), credential.getEncryptedPassword(), masterPassword,
            () -> cardLayout.show(mainContainer, "list"),
            () -> showLoginPanel()
        );
        mainContainer.add(currentCredentialPanel, "password");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "password");
    }

    private void showCreateCredentialPanel() {
        if (currentManageCredentialPanel != null) mainContainer.remove(currentManageCredentialPanel);

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        currentManageCredentialPanel = new ManageCredentialPanel(listDimension,
            loggedUser.getId(), null, "Create Credential", "Create", masterPassword,
            credential -> DbCredentialUtils.createCredential(credential),
            () -> showListPanel(),
            () -> showLoginPanel()
        );
        mainContainer.add(currentManageCredentialPanel, "manage");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "manage");
    }

    private void showEditCredentialPanel(Credential credential) {
        if (currentManageCredentialPanel != null) mainContainer.remove(currentManageCredentialPanel);

        mainContainer.setPreferredSize(listDimension);
        pack();
        WindowUtils.centerWindow(this, WindowUtils.getScreenSize());

        currentManageCredentialPanel = new ManageCredentialPanel(listDimension,
            loggedUser.getId(), credential, "Edit Credential", "Update", masterPassword,
            updatedCredential -> DbCredentialUtils.updateCredential(credential.getCredentialId(), updatedCredential),
            () -> showListPanel(),
            () -> showLoginPanel()
        );
        mainContainer.add(currentManageCredentialPanel, "manage");
        mainContainer.revalidate();
        mainContainer.repaint();
        cardLayout.show(mainContainer, "manage");
    }
}
