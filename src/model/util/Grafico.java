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
import model.enums.ConfigList;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Dimension;
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

public class Grafico extends ApplicationFrame {

    public Grafico(String applicationTitle, String chartTitle) {
        super(applicationTitle);

        JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(
                chartTitle,
                "Hora",
                "Temperatura",
                createDataset(),
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new Dimension(400, 400));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.ORANGE);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        plot.setRenderer(renderer);
        OutputStream png;

        try {
            png = new FileOutputStream("Grafico.png");
            ChartUtilities.writeChartAsPNG(png, xylineChart, 1500, 400);
        } catch (IOException e) {
            System.err.println("Erro ao construir png Grafico: " + e.getMessage());
        }
    }

    private XYDataset createDataset() {
        final TimeSeries z1 = new TimeSeries("Temperatura Secagem", Minute.class);
        final TimeSeries z2 = new TimeSeries("Temperatura Vulcanização", Minute.class);

        String path = Config.loadConfig(ConfigList.LOCALFOLDER) + Utils.getMesAnoFormated_() + "\\" + Utils.getDataFormated_() + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            Calendar cal = Calendar.getInstance();
            Date d1 =new Date(System.currentTimeMillis());
            
            cal.setTime(d1);
            cal.add(Calendar.HOUR_OF_DAY, -1);
            d1 = cal.getTime();
            
            while (line != null) {
                String[] cut = line.split(", ");
                try {

                    if (sdf.parse(cut[0]).after(d1)) {

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

}
