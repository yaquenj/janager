package utilities;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import constants.TailwindColors;

public class HyperlinkLabel extends JLabel {

    public HyperlinkLabel(String text, Runnable function) {
        setText("<html><a>" + text + "</a></html>");
        setForeground(TailwindColors.INDIGO_600);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                function.run();
                setForeground(TailwindColors.INDIGO_400);
                Timer timer = new Timer(50, event -> setForeground(TailwindColors.INDIGO_600));
                timer.setRepeats(false);
                timer.start();
            }

            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override public void mouseExited(MouseEvent e) {}
        });
    }
}
