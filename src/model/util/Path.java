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
import model.enums.PathList;

/**
 *
 * @author Herbert
 */
public class Path {

    private PathList configList;
    private String valor;

    public Path(PathList configList, String valor) {
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
        final Path other = (Path) obj;
        if (!Objects.equals(this.configList, other.configList)) {
            return false;
        }
        return true;
    }

    public PathList getParametro() {
        return configList;
    }

    public String getValor() {
        return valor;
    }

    public static String loadPath(PathList configList) {

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

    public static void setPath(Path config) {
        String path = "Config.txt";
        Map<String, String> mapPath = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] linha = line.split(": ");
                mapPath.put(linha[0], linha[1]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao tentar criar o mapeamento das configurações " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            mapPath.put(config.getParametro().getPath(), config.getValor());

            for (String key : mapPath.keySet()) {
                bw.write(key + ": " + mapPath.get(key));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao tentar alterar as configuraçoes : " + e.getMessage());
        }

    }
}
