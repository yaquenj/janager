package utilities;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class DialogUtils {

    public static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningDialog(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void showInfoDialog(String title, String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean showConfirmDialog(String title, String message) {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
