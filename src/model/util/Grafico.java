/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

/**
 *
 * @author Herbert
 */
import gui.util.Alerts;
import model.enums.PathList;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.enums.TypePane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class Grafico extends ApplicationFrame {

    public Grafico(String applicationTitle, String chartTitle, String dia_mes_ano, String nomeGrafico,Integer width ,Integer height,Integer hourLimit) {
        super(applicationTitle);

        JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(
                chartTitle,
                "Hora",
                "Temperatura",
                createDataset(dia_mes_ano, hourLimit),
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new Dimension(width, height));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.ORANGE);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        plot.setRenderer(renderer);
        OutputStream png;

        try {
            png = new FileOutputStream(nomeGrafico+".png");
            ChartUtilities.writeChartAsPNG(png, xylineChart, width, height);
        } catch (IOException e) {
            System.err.println("Erro ao construir png Grafico: " + e.getMessage());
        }
    }

    private XYDataset createDataset(String dia_mes_ano, Integer hourLimit ) {
        final TimeSeries z1 = new TimeSeries("Temperatura Secagem", Minute.class);
        final TimeSeries z2 = new TimeSeries("Temperatura Vulcanização", Minute.class);

        String [] data = dia_mes_ano.split("_");
        
        String path = Path.loadPath(PathList.LOCALFOLDER)+ "\\" + data[1] + "_" + data[2] + "\\" + dia_mes_ano + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            Calendar cal = Calendar.getInstance();
            Date d1 =new Date(System.currentTimeMillis());
            
            cal.setTime(d1);
            cal.add(Calendar.HOUR_OF_DAY, -(hourLimit));
            d1 = cal.getTime();
            
            while (line != null) {
                String[] cut = line.split(", ");
                try {

                    if (sdf.parse(cut[0]).after(d1) || hourLimit == -1000) {

                        z1.add(new Minute(sdf.parse(cut[0]), TimeZone.getDefault()), Double.parseDouble(cut[1]));
                        z2.add(new Minute(sdf.parse(cut[0]), TimeZone.getDefault()), Double.parseDouble(cut[2]));
                    }

                } catch (ParseException ex) {
                    Logger.getLogger("Erro ao tentar converter os dados do registro: " + ex.getMessage());
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao tentar carregar dados no grafico: " + e.getMessage());
        }

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(z1);
        dataset.addSeries(z2);
        return dataset;
    }

    public static void carregaGrafico(String dia_mes_ano, JLabel label, String nomeGrafico,Integer width, Integer height ,Integer hourLimit) {
        Grafico chart = new Grafico("Temperatura de Secagem dos fornos ICBT's",
                "Temperatura do Forno", dia_mes_ano, nomeGrafico, width, height, hourLimit);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(false);
        final String path = nomeGrafico+".png";

        try {
            java.awt.Image nGrafico = Toolkit.getDefaultToolkit().getImage(path);
            nGrafico.flush();
            label.setIcon(new ImageIcon(nGrafico));

        } catch (NullPointerException e) {
            Alerts.showAlert("Erro ao gerar o grafico", "O Sistema não pode achar o arquivo para construção do grafico ",
            "", TypePane.ERRO);
            System.out.println("Erro ao gerar o grafico");
        }
    }
    
}
