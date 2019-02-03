/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Herbert
 */
public class LoadConfig {

    public static String getConfig(String parametro) {

        String path = "Config.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] linha = line.split(": ");

                if (parametro.equals(linha[0])) {
                    return linha[1];
                } else {
                    line = br.readLine();
                }

            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
