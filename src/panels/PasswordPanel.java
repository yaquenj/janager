package panels;

import javax.swing.*;
import java.awt.*;

import constants.TailwindColors;
import utilities.*;

public class PasswordPanel extends JPanel {

    public PasswordPanel(Dimension windowDimension, String url, String username, String password, Runnable onBack, Runnable onLogout) {
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

        JLabel title_label = new JLabel("Password Details");
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
        urlIcon_label.setIcon(new ImgIcon("media/icons/iconsax-link.png").resizeIcon(iconSize)); // Using key square as placeholder for URL icon
        JTextField url_textfield = UIUtils.createTextField(fieldDimension, "URL");
        url_textfield.setText(url);
        url_textfield.setEditable(false);
        url_textfield.setForeground(TailwindColors.SLATE_50);

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
        username_textfield.setText(username);
        username_textfield.setEditable(false);
        username_textfield.setForeground(TailwindColors.SLATE_50);

        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        usernameRow.setOpaque(false);
        usernameRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameRow.add(userIcon_label);
        usernameRow.add(username_textfield);
        usernameRow.setMaximumSize(usernameRow.getPreferredSize());

        // Password Field
        JLabel keyIcon_label = new JLabel();
        keyIcon_label.setIcon(new ImgIcon("media/icons/iconsax-key-square.png").resizeIcon(iconSize));

        JPanel passwordFieldPanel = new JPanel(new BorderLayout(hGap / 2, 0));
        passwordFieldPanel.setOpaque(false);
        passwordFieldPanel.setPreferredSize(fieldDimension);
        passwordFieldPanel.setMaximumSize(fieldDimension);

        JPasswordField password_textfield = UIUtils.createPasswordField(fieldDimension, "Password");
        password_textfield.setText(password);
        password_textfield.setEditable(false);
        password_textfield.setForeground(TailwindColors.SLATE_50);
        password_textfield.setEchoChar('•'); // Hidden by default

        JButton toggleButton = new JButton("Show");
        toggleButton.setPreferredSize(new Dimension((int) (fieldDimension.width * 0.25), fieldDimension.height));
        toggleButton.setBackground(TailwindColors.SLATE_800);
        toggleButton.setForeground(TailwindColors.SLATE_50);
        toggleButton.setFont(toggleButton.getFont().deriveFont((float) iconSize * 0.35f));
        toggleButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(_ -> {
            if (password_textfield.getEchoChar() == (char) 0) {
                password_textfield.setEchoChar('•');
                toggleButton.setText("Show");
            } else {
                password_textfield.setEchoChar((char) 0);
                toggleButton.setText("Hide");
            }
        });

        passwordFieldPanel.add(password_textfield, BorderLayout.CENTER);
        passwordFieldPanel.add(toggleButton, BorderLayout.EAST);

        JPanel passwordRow = new JPanel(new FlowLayout(FlowLayout.LEFT, hGap, 0));
        passwordRow.setOpaque(false);
        passwordRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordRow.add(keyIcon_label);
        passwordRow.add(passwordFieldPanel);
        passwordRow.setMaximumSize(passwordRow.getPreferredSize());

        // Back Button
        JButton backButton = new JButton("Back to List");
        backButton.setPreferredSize(new Dimension((int) (fieldDimension.width * 0.5), (int) (fieldDimension.height * 1.1)));
        backButton.setBackground(TailwindColors.SLATE_800);
        backButton.setForeground(TailwindColors.SLATE_50);
        backButton.setFont(backButton.getFont().deriveFont((float) iconSize * 0.45f));
        backButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
        backButton.setFocusPainted(false);
        backButton.addActionListener(_ -> onBack.run());

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.setOpaque(false);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRow.add(backButton);
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
