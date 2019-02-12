package gui.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Alerts {

    public static void showAlert(String title, String content, String header, Enum TypePane) {
        JFrame frame = new JFrame(header);
        JOptionPane.showMessageDialog(frame, content, title, TypePane.ordinal());
    }
}
