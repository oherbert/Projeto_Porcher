package gui.util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Constraints {

    public static void setTextFieldInteger(JTextField txt) {
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char letter = e.getKeyChar();
                if (!Character.isDigit(letter)) {
                    e.consume();
                }
            }
        });
    }

    public static void setTextFieldMaxLength(JTextField txt, int max) {
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (txt.getText().length() >= max) {
                    e.consume();
                }

            }
        });

    }

    public static void setTextFieldDouble(JTextField txt) {
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char letter = e.getKeyChar();
                if (e.toString() != null && !txt.getText().matches("\\d*([\\.]\\d*)?")) {
                    e.consume();
                } else if (letter == '.') {
                } else if (!Character.isDigit(letter)) {
                    e.consume();
                }
            }
        });
    }

    public static void setTextFieldString(JTextField txt) {
        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char letter = e.getKeyChar();
                 if (!Character.isLetter(letter)) {
                    e.consume();
                }
            }
        });
    }

}
