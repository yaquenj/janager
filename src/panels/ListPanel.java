package panels;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import constants.TailwindColors;
import models.Credential;
import utilities.*;

public class ListPanel extends JPanel {

    public ListPanel(Dimension windowDimension, int userId,
                     Consumer<Credential> onEntrySelected, Runnable onCreateRequest,
                     Consumer<Credential> onEditRequest, Consumer<Credential> onRemoveRequest,
                     Runnable onLogout) {
        //? Responsive dimensions for components
        var fieldDimension = new Dimension((int) (windowDimension.width * 0.7), (int) (windowDimension.height * 0.07));
        var iconSize = (int)(windowDimension.height * 0.07);
        var hGap = (int)(windowDimension.width * 0.02);

        List<Credential> entries = DbCredentialUtils.getUserCredentialsById(userId);

        //? Window elements
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setOpaque(false);
        mainContent.setBounds(0, 0, windowDimension.width, windowDimension.height);
        mainContent.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topRow.setOpaque(false);
        topRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        topRow.setMaximumSize(new Dimension((int)(windowDimension.width * 0.9), (int)(windowDimension.height * 0.05)));

        JLabel logoutLabel = new HyperlinkLabel("Log out", onLogout);
        logoutLabel.setFont(logoutLabel.getFont().deriveFont((float) iconSize * 0.4f));
        topRow.add(logoutLabel);

        JLabel title_label = new JLabel("Your Credentials:");
        title_label.setForeground(TailwindColors.SLATE_50);
        title_label.setFont(title_label.getFont().deriveFont((float) iconSize * 0.6f));
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT);
        title_label.setMaximumSize(new Dimension(windowDimension.width, title_label.getPreferredSize().height));

        JPanel listContent = new JPanel();
        listContent.setLayout(new BoxLayout(listContent, BoxLayout.Y_AXIS));
        listContent.setOpaque(false);
        listContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Credential entry : entries) {
            String url = entry.getUrl();
            String username = entry.getLogin();

            JPanel entryRow = new JPanel();
            entryRow.setLayout(new BoxLayout(entryRow, BoxLayout.X_AXIS));
            entryRow.setOpaque(false);
            entryRow.setMaximumSize(new Dimension((int)(windowDimension.width * 0.85), fieldDimension.height));
            entryRow.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton entryButton = new JButton(url + " (" + username + ")");
            entryButton.setPreferredSize(fieldDimension);
            entryButton.setMaximumSize(fieldDimension);
            entryButton.setBackground(TailwindColors.SLATE_900);
            entryButton.setForeground(TailwindColors.SLATE_300);
            entryButton.setFont(entryButton.getFont().deriveFont((float) iconSize * 0.4f));
            entryButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            entryButton.setFocusPainted(false);
            entryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            entryButton.setHorizontalAlignment(SwingConstants.LEFT);

            entryButton.addActionListener(_ -> onEntrySelected.accept(entry));

            JButton editButton = new JButton();
            editButton.setIcon(new ImgIcon("media/icons/iconsax-edit-square.png").resizeIcon((int)(iconSize * 0.6)));
            editButton.setPreferredSize(new Dimension(iconSize, iconSize));
            editButton.setMaximumSize(new Dimension(iconSize, iconSize));
            editButton.setBackground(TailwindColors.SLATE_800);
            editButton.setForeground(TailwindColors.SLATE_50);
            editButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
            editButton.setFocusPainted(false);
            editButton.addActionListener(_ -> onEditRequest.accept(entry));

            JButton removeButton = new JButton();
            removeButton.setIcon(new ImgIcon("media/icons/iconsax-trash-square.png").resizeIcon((int)(iconSize * 0.6)));
            removeButton.setPreferredSize(new Dimension(iconSize, iconSize));
            removeButton.setMaximumSize(new Dimension(iconSize, iconSize));
            removeButton.setBackground(TailwindColors.SLATE_800);
            removeButton.setForeground(TailwindColors.SLATE_50);
            removeButton.setBorder(BorderFactory.createLineBorder(TailwindColors.SLATE_700, 1, true));
            removeButton.setFocusPainted(false);
            removeButton.addActionListener(_ -> onRemoveRequest.accept(entry));

            entryRow.add(entryButton);
            entryRow.add(Box.createHorizontalStrut(hGap));
            entryRow.add(editButton);
            entryRow.add(Box.createHorizontalStrut(hGap));
            entryRow.add(removeButton);

            listContent.add(entryRow);
            listContent.add(Box.createVerticalStrut((int) (windowDimension.height * 0.015)));
        }

        JScrollPane scrollPane = new JScrollPane(listContent);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension((int)(windowDimension.width * 0.9), (int)(windowDimension.height * 0.7)));
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainContent.add(topRow);
        mainContent.add(Box.createVerticalGlue());
        mainContent.add(title_label);
        mainContent.add(Box.createVerticalStrut((int) (windowDimension.height * 0.04)));
        mainContent.add(scrollPane);
        mainContent.add(Box.createVerticalGlue());

        // Floating Add Button
        int fabSize = (int)(windowDimension.height * 0.12);
        int fabMargin = 30; // Even margin from bottom and right
        JButton addButton = new JButton("+");
        addButton.setBackground(TailwindColors.INDIGO_600);
        addButton.setForeground(TailwindColors.SLATE_50);
        addButton.setFont(addButton.getFont().deriveFont(Font.BOLD, (float) fabSize * 0.6f));
        addButton.setBorder(BorderFactory.createLineBorder(TailwindColors.INDIGO_700, 2));
        addButton.setFocusPainted(false);
        addButton.setBounds(windowDimension.width - fabSize - fabMargin, windowDimension.height - fabSize - fabMargin, fabSize, fabSize);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(_ -> onCreateRequest.run());

        //? Set Layout for this panel
        setLayout(null);
        setBackground(TailwindColors.SLATE_950);
        setPreferredSize(windowDimension);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, windowDimension.width, windowDimension.height);
        layeredPane.add(mainContent, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(addButton, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);
    }
}
