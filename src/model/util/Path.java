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

    ////////////
    // Carrega o valor contido do endereço no arquivo de configuração
    public static String loadPath(PathList pathList) {

        String path = "Config.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] linha = line.split(": ");

                if (pathList.getPath().equals(linha[0])) {
                    return linha[1];
                } else {
                    line = br.readLine();
                }
            }

        } catch (IOException e) {
            System.out.println("ClassePath: Caminho não encontrado: " + e.getMessage());
        }
        return null;
    }

    ////////////////////////
    // Altera o valor do endereço no arquivo config.txt
    public static void setPath(Path config) {
        String path = "Config.txt";
        Map<String, String> mapPath = new TreeMap<>();

        ///Carrega Todas  configurações do arquivo config
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] linha = line.split(": ");
                mapPath.put(linha[0], linha[1]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("ClassePath: Erro ao tentar criar o mapeamento das configurações " + e.getMessage());
        }

        // Carrega o novo parametro no arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            mapPath.put(config.getParametro().getPath(), config.getValor());

            for (String key : mapPath.keySet()) {
                bw.write(key + ": " + mapPath.get(key));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("ClassePath: Erro ao tentar alterar as configuraçoes : " + e.getMessage());
        }

    }

    public static void InitialConfig() {
        String path = "Config.txt";
        Boolean exist = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            exist = true;
        } catch (IOException e) {
            System.out.println("ClassePath: Criando um novo aquivo config " + e.getMessage());
        }

        if (exist == false) {
            // Carrega o novo parametro no arquivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                bw.write("ArduinoCom: COM8\n");
                bw.write("CloudFolder: https://drive.google.com/drive/folders/1ad7ErOeUKliwyPDP4FTEFxfGunt6fUGo?usp=sharing\n");
                bw.write("Horimetro: 0\n");
                bw.write("LocalFolder: C:\\Users\\Herbert\\Google Drive\\Registros Projeto Porcher\n");
                bw.write("OffSetSecagem_1: 0\n");
                bw.write("OffSetSecagem_2: 0\n");
                bw.write("OffSetSecagem_3: 0\n");
                bw.write("OffSetSecagem_4: 0\n");
                bw.write("OffSetVulcanizacao_1: 0\n");
                bw.write("OffSetVulcanizacao_2: 0\n");
                bw.write("OffSetVulcanizacao_3: 0\n");
                bw.write("OffSetVulcanizacao_4: 0\n");
                bw.write("RangeTemperatura1: 10\n");
                bw.write("RangeTemperatura2: 10\n");
                bw.write("TemperaturaSet1: 157\n");
                bw.write("TemperaturaSet2: 167\n");
                bw.write("TempoGravacao: 180");

            } catch (IOException e) {
                System.out.println("ClassePath: Erro ao tentar alterar as configuraçoes : " + e.getMessage());
            }

        }

    }
}
