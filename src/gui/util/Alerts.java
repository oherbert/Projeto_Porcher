package gui.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Alerts {

	public static void showAlert(String title, String header, String content, Enum TypePane) {
            JFrame frame = new JFrame(content);
            JOptionPane.showMessageDialog(frame, header, title, TypePane.ordinal());
	}
}