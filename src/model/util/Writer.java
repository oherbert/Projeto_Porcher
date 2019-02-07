/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import model.enums.ConfigList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.entities.LeituraMaquina;

/**
 *
 * @author Herbert
 */
public class Writer {
    
    public static void write(LeituraMaquina leitura){
        
        File file = new File(Config.loadConfig(ConfigList.LOCALFOLDER) + "\\");
        File[] folders = file.listFiles(File::isDirectory);
        
        boolean checkFolder = false;
        
        for (File folder:folders){ // Verifica se exite a pasta do mês vigente
            if (folder.getName().equals(Utils.getMesAnoFormated_())){
                checkFolder = true;
                break;
            }
        }
        String filePath = (Config.loadConfig(ConfigList.LOCALFOLDER)+ "\\" + Utils.getMesAnoFormated_());
        
        if (checkFolder == false){ // Cria uma pasta do mês, se não houver
        boolean createFolder = new File(filePath).mkdir();
        }
        
        String path = filePath+ "\\" + Utils.getDataFormated_() + ".txt"; //cria ou seleciona um txt do dia
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            
            bw.write(leitura.toString());
            bw.newLine();
            
        } catch (IOException e) {
            System.out.println("Erro ao tentar Gravar o arquivo: "+ e.getMessage() );
        }
        
    }
    
}


