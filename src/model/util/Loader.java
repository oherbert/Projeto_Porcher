/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.enums.PathList;

/**
 *
 * @author Herbert
 */
public class Loader {
    
    public static File[] folder(){
    //List<String> folders = new ArrayList<>();
    
    File path = new File(Path.loadPath(PathList.LOCALFOLDER));
    File[] folders = path.listFiles(File::isDirectory);
    
    
    return folders;
    }
    
    public static File[] file(String selectedFolder){
    //List<String> folders = new ArrayList<>();
    
    File path = new File(Path.loadPath(PathList.LOCALFOLDER)+ "\\"+ selectedFolder);
    File[] folders = path.listFiles(File::isFile);
    
    return folders;
    }
    
    public static String[] fileContent (String path) {
        String[] rowDatas = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                rowDatas = line.split(",");                
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar a tabela " + e.getMessage());
        }
        
        return rowDatas;
}
}





