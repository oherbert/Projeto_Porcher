/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Herbert
 */
public class Utils {
    
    
    public static String getDataHoraSistema (){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date timeStamp = new Date(System.currentTimeMillis());
        return sdf1.format(timeStamp);
    }
    
    public static String getDataSistema (){
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    Date timeStamp = new Date(System.currentTimeMillis());
    return sdf2.format(timeStamp);
    }
}
