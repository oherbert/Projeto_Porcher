/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import model.entities.LeituraMaquina;

/**
 *
 * @author Herbert
 */
public class Gravador {
    
    public static void write(LeituraMaquina leitura){
        
        String path = "C:\\Users\\Herbert\\Google Drive\\Registros Projeto Porcher" + Utils.getDataSistema();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            
            bw.write(leitura.toString());
            bw.newLine();
            
        } catch (IOException e) {
            System.out.println("Erro ao tentar Gravar o arquivo: "+ e.getMessage() );
        }
        
    }
    
}


