package panels;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

import constants.TailwindColors;
import models.User;
import utilities.*;

public class ManagePasswordPanel extends JPanel {

    public ManagePasswordPanel(Dimension windowDimension, User entryToEdit, 
                               String title, String actionButtonText,
                               Consumer<User> onAction, Runnable onBack, Runnable onLogout) {
        //? Responsive dimensions for components
        var fieldDimension = new Dimension((int) (windowDimension.width * 0.62), (int) (windowDimension.height * 0.07));
        var iconSize = fieldDimension.height;
        var hGap = (int)(windowDimension.width * 0.02);

        //? Window elements
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topRow.setOpaque(false);
        topRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        topRow.setMaximumSize(new Dimension((int)(windowDimension.width * 0.9), (int)(windowDimension.height * 0.05)));

        JLabel logoutLabel = new HyperlinkLabel("Log out", onLogout);
        logoutLabel.setFont(logoutLabel.getFont().deriveFont((float) iconSize * 0.4f));
        topRow.add(logoutLabel);

        JLabel title_label = new JLabel(title);
        title_label.setForeground(TailwindColors.SLATE_50);
        title_label.setFont(title_label.getFont().deriveFont((float) iconSize * 0.5f));
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_label.setMaximumSize(new Dimension(windowDimension.width, title_label.getPreferredSize().height));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setOpaque(false);
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // URL Field
        JLabel urlIcon_label = new JLabel();
        urlIcon_label.setIcon(new ImgIcon("media/icons/iconsax-link.png").resizeIcon(iconSize));
        JTextField url_textfield = UIUtils.createTextField(fieldDimension, "URL");
        if (entryToEdit != null) {
            url_textfield.setText(entryToEdit.getLogin());
            url_textfield.setForeground(TailwindColors.SLATE_50);
        }

        JPanel urlRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        urlRow.setOpaque(false);
        urlRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        urlRow.add(urlIcon_label);
        urlRow.add(url_textfield);
        urlRow.setMaximumSize(urlRow.getPreferredSize());

        // Username Field
        JLabel userIcon_label = new JLabel();
        userIcon_label.setIcon((new ImgIcon("media/icons/iconsax-user-square.png").resizeIcon(iconSize)));
        JTextField username_textfield = UIUtils.createTextField(fieldDimension, "Username");
        if (entryToEdit != null) {
            username_textfield.setText(entryToEdit.getSalt());
            username_textfield.setForeground(TailwindColors.SLATE_50);
        }

        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        usernameRow.setOpaque(false);
        usernameRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameRow.add(userIcon_label);
        usernameRow.add(username_textfield);
        usernameRow.setMaximumSize(usernameRow.getPreferredSize());

        // Password Field
        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(iconSize));
        JPasswordField password_textfield = UIUtils.createPasswordField(fieldDimension, "Password");
        if (entryToEdit != null) {
            password_textfield.setText(entryToEdit.getPasswordHash());
            password_textfield.setForeground(TailwindColors.SLATE_50);
            password_textfield.setEchoChar('•');
        }

        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        passwordRow.setOpaque(false);
        passwordRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordRow.add(keyIcon_label);
        passwordRow.add(password_textfield);
        passwordRow.setMaximumSize(passwordRow.getPreferredSize());

        // Buttons Row
        JButton actionButton = new JButton(actionButtonText);
        actionButton.setPreferredSize(new Dimension((int) (fieldDimension.width * 0.4), (int) (fieldDimension.height * 1.1)));
        actionButton.setBackground(TailwindColors.SLATE_800);
        actionButton.setForeground(TailwindColors.SLATE_50);
        actionButton.setFont(actionButton.getFont().deriveFont((float) iconSize * 0.45f));
        actionButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        actionButton.setFocusPainted(false);
        actionButton.addActionListener(_ -> {
            int id = entryToEdit != null ? entryToEdit.getId() : -1;
            User user = new User(id, url_textfield.getText(), new String(password_textfield.getPassword()), username_textfield.getText());
            onAction.accept(user);
            onBack.run();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension((int) (fieldDimension.width * 0.4), (int) (fieldDimension.height * 1.1)));
        cancelButton.setBackground(TailwindColors.SLATE_800);
        cancelButton.setForeground(TailwindColors.SLATE_50);
        cancelButton.setFont(cancelButton.getFont().deriveFont((float) iconSize * 0.45f));
        cancelButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(_ -> onBack.run());

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, hGap, 0));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(actionButton);
        buttonRow.add(cancelButton);
        buttonRow.setMaximumSize(buttonRow.getPreferredSize());

        // Layout setup
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(TailwindColors.SLATE_950);
        setPreferredSize(windowDimension);
        setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        add(topRow);
        add(Box.createVerticalGlue());
        add(title_label);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.04)));
        add(detailsPanel);
        detailsPanel.add(urlRow);
        detailsPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.0125)));
        detailsPanel.add(usernameRow);
        detailsPanel.add(Box.createVerticalStrut((int) (windowDimension.height * 0.0125)));
        detailsPanel.add(passwordRow);
        add(Box.createVerticalStrut((int) (windowDimension.height * 0.04)));
        add(buttonRow);
        add(Box.createVerticalGlue());
    }
}
