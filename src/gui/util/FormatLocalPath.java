/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.util;

/**
 *
 * @author Herbert
 */
public class FormatLocalPath {

    public static String OneBarToTwoBars(String path) {
        String newPath = path.replace("\\", "\\\\");
        System.out.println(newPath);
        return newPath;
    }

    public static String TwoBarsToOneBar(String path) {
        String newPath = path.replace("\\\\", "\\");
        System.out.println(newPath);
        return newPath;
    }

}
