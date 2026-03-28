package utilities;

import constants.TailwindColors;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UIUtils {

    public static @NotNull JTextField createTextField(Dimension dimension, String placeholder) {
        JTextField textfield = new JTextField();
        textfield.setPreferredSize(dimension);
        textfield.setBackground(TailwindColors.SLATE_900);
        textfield.setForeground(TailwindColors.SLATE_400);
        textfield.setCaretColor(TailwindColors.SLATE_400);
        textfield.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        textfield.setFont(textfield.getFont().deriveFont((float) dimension.height * 0.45f));
        textfield.setText(placeholder);
        textfield.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textfield.getText().equals(placeholder)) {
                    textfield.setText(null);
                    textfield.setForeground(TailwindColors.SLATE_50);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textfield.getText().isEmpty()) {
                    textfield.setForeground(TailwindColors.SLATE_400);
                    textfield.setText(placeholder);
                }
            }
        });
        return textfield;
    }

    public static @NotNull JPasswordField createPasswordField(Dimension dimension, String placeholder) {
        JPasswordField password_textfield = new JPasswordField();
        password_textfield.setPreferredSize(dimension);
        password_textfield.setBackground(TailwindColors.SLATE_900);
        password_textfield.setForeground(TailwindColors.SLATE_400);
        password_textfield.setCaretColor(TailwindColors.SLATE_400);
        password_textfield.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        password_textfield.setFont(password_textfield.getFont().deriveFont((float) dimension.height * 0.45f));
        password_textfield.setText(placeholder);
        password_textfield.setEchoChar((char) 0); // Initially show text
        password_textfield.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(password_textfield.getPassword()).equals(placeholder)) {
                    password_textfield.setText("");
                    password_textfield.setForeground(TailwindColors.SLATE_50);
                    password_textfield.setEchoChar('•'); // Mask after focus
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(password_textfield.getPassword()).isEmpty()) {
                    password_textfield.setForeground(TailwindColors.SLATE_400);
                    password_textfield.setText(placeholder);
                    password_textfield.setEchoChar((char) 0); // Unmask if empty
                }
            }
        });
        return password_textfield;
    }
}
