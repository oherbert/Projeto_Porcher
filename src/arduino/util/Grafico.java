/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.util;

/**
 *
 * @author Herbert
 */
import Util.LoadConfig;
import java.awt.Color; 
import java.awt.BasicStroke; 
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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

   public Grafico( String applicationTitle, String chartTitle ) {
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(
         chartTitle ,
         "Hora" ,
         "Temperatura" ,
         createDataset() ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new Dimension(400,400));
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.ORANGE );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      plot.setRenderer( renderer ); 
      OutputStream png;
       
      try {
           png = new FileOutputStream(LoadConfig.getConfig("ChartPath"));
            ChartUtilities.writeChartAsPNG(png, xylineChart,1000,400);
       } catch (IOException e) {
           System.err.println("Erro ao gerar Grafico: "+ e.getMessage());
       }
   }
   
   public Grafico( String applicationTitle, String chartTitle, List<ZonasTemperatura> Zt ) {
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(
         chartTitle ,
         "Hora" ,
         "Temperatura" ,
         createDataset(Zt) ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension());
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.ORANGE );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      OutputStream png;
       
      try {
           png = new FileOutputStream(LoadConfig.getConfig("ChartPath"));
           ChartUtilities.writeChartAsPNG(png, xylineChart,1000,400);
       } catch (IOException e) {
           System.err.println("Erro ao gerar Grafico: "+ e.getMessage());
       }
   }
      private XYDataset createDataset(){
      final TimeSeries z1 = new TimeSeries("Temperatura Secagem", Minute.class);
      final TimeSeries z2 = new TimeSeries("Temperatura Vulcanização", Minute.class);
   
      final TimeSeriesCollection dataset = new TimeSeriesCollection(); 
      return dataset;
   }
   
   
   private XYDataset createDataset( List<ZonasTemperatura> Zt){
      final TimeSeries z1 = new TimeSeries("Temperatura Secagem", Minute.class);
      final TimeSeries z2 = new TimeSeries("Temperatura Vulcanização", Minute.class);
      
    
      
      for (ZonasTemperatura zt: Zt) {
      z1.add(zt.getMin() , zt.getZ1());  
      z2.add(zt.getMin(), zt.getZ2()); 
   }
      
    
    
      
      
      final TimeSeriesCollection dataset = new TimeSeriesCollection();
      dataset.addSeries( z1 );          
      dataset.addSeries( z2 );          
      return dataset;
   }

   
} 