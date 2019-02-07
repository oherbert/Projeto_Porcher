/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import model.enums.ConfigList;

/**
 *
 * @author Herbert
 */
public class Config {

    private ConfigList configList;
    private String valor;

    public Config(ConfigList configList, String valor) {
        this.configList = configList;
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.configList);
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
        if (!Objects.equals(this.configList, other.configList)) {
            return false;
        }
        return true;
    }

    public ConfigList getParametro() {
        return configList;
    }

    public String getValor() {
        return valor;
    }

    public static String loadConfig(ConfigList configList) {

        String path = "Config.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] linha = line.split(": ");

                if (configList.getPath().equals(linha[0])) {
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

    public static void setConfig(Config config) {
        String path = "Config.txt";
        Map<String, String> mapConfig = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] linha = line.split(": ");
                System.out.println(linha[0] + " e " + linha[1]);
                mapConfig.put(linha[0], linha[1]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao tentar criar o mapeamento das configurações " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            mapConfig.put(config.getParametro().getPath(), config.getValor());

            for (String key : mapConfig.keySet()) {
                bw.write(key + ": " + mapConfig.get(key));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao tentar alterar as configuraçoes : " + e.getMessage());
        }

    }
}
