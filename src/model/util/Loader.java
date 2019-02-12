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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.enums.PathList;

/**
 *
 * @author Herbert
 */
public class Loader {

    public static File[] folder() {

        File path = new File(Path.loadPath(PathList.LOCALFOLDER));
        File[] folders = path.listFiles(File::isDirectory);

        return folders;
    }

    public static File[] file(String selectedFolder) {

        File path = new File(Path.loadPath(PathList.LOCALFOLDER) + "\\" + selectedFolder);
        File[] folders = path.listFiles(File::isFile);

        return folders;
    }

    public static void fileContent(JScrollPane sPane, String dia, String mesAno) {

        String[] title = new String[]{"Data hora", "Temp Secagem", "Temp Vulcanização", "Estado"};

        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel(null, title);
        table.setModel(model);
        DefaultTableModel model2 = (DefaultTableModel) table.getModel();

        String path = Path.loadPath(PathList.LOCALFOLDER) + "\\" + mesAno + "\\" + dia + "_" + mesAno + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                String[] rowDatas = line.split(", ");

                model2.addRow(rowDatas);

                line = br.readLine();
            }
            table.setModel(model2);
            sPane.setViewportView(table);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tabela " + e.getMessage());
        }

    }
}
