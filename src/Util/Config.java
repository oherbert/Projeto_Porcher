/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author Herbert
 */
public class Config {
    
    private String parametro;
    private String valor;

    public Config(String parametro, String valor) {
        this.parametro = parametro;
        this.valor = valor;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.parametro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Config other = (Config) obj;
        if (!Objects.equals(this.parametro, other.parametro)) {
            return false;
        }
        return true;
    }
    
    
    
    
    

    public static String loadConfig(Enum ConfigList) {

        String path = "Config.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] linha = line.split(": ");

                if (ConfigList.toString().equals(linha[0])) {
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
    
    public static void setConfig (Config config){
        String path = "Config.txt";
        Map<String, String> mapConfig = new TreeMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            
            while (line != null) {
                String[] linha = line.split(": ");
                mapConfig.put(linha[0], linha[1]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            mapConfig.put(config.getParametro(), config.getValor());
            
            for (String key: mapConfig.keySet() ){
            bw.write(key + " ,"+ mapConfig.get(key));
            bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao tentar alterar as configuraçoes : "+ e.getMessage() );
        }
        
    }
    }

