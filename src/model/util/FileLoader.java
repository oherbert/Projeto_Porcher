/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.io.File;
import model.enums.ConfigList;

/**
 *
 * @author Herbert
 */
public class FileLoader {
    
    public static File[] loadFolder(){
    //List<String> folders = new ArrayList<>();
    
    File path = new File(Config.loadConfig(ConfigList.LOCALFOLDER));
    File[] folders = path.listFiles(File::isDirectory);
    
    
    return folders;
    }
    
    public static File[] loadFile(String selectedFolder){
    //List<String> folders = new ArrayList<>();
    
    File path = new File(Config.loadConfig(ConfigList.LOCALFOLDER)+ "\\"+ selectedFolder);
    File[] folders = path.listFiles(File::isFile);
    
    return folders;
    }
}





