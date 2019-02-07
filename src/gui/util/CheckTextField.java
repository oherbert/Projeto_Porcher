/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.util;

import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Herbert
 */
public class CheckTextField {

    public static Boolean emptyField(JTextField txt) {
        if ("".equals(txt.getText().trim()) || txt.getText() == null) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean emptyList(List<JTextField> lst) {
        for (JTextField txt : lst) {
            if ("".equals(txt.getText().trim()) || txt.getText() == null) {
                return true;
            }
        }
        return false;
    }

}
