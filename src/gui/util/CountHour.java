/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import model.enums.PathList;
import model.util.Path;

/**
 *
 * @author Herbert
 */
public class CountHour {
    
    

    public static void homerimetro(Date time, int periodo) {
        
        Locale.setDefault(Locale.US);
        
        Calendar cal1 = Calendar.getInstance();
        Date tFim = new Date();
        cal1.setTime(time);
        cal1.add(Calendar.SECOND, periodo);
        tFim = cal1.getTime();

        Date tInicio = new Date();
        Long totalTime =(tInicio.getTime() - tFim.getTime());

        if (totalTime < 0L) {
            totalTime = totalTime * -1;
        }
        totalTime = totalTime + Long.parseLong(Path.loadPath(PathList.HORIMETRO));
            
        Path.setPath( new Path (PathList.HORIMETRO, totalTime.toString()));
        

    }
    
    public static String formatHour(String totalT){
    String resultado;
    Long totalTime = Long.parseLong(totalT);
    resultado = String.format("%05d:%02d", TimeUnit.MILLISECONDS.toHours( totalTime ),
    TimeUnit.MILLISECONDS.toMinutes( totalTime ) % 60);
    return resultado;
    }
}
