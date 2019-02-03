/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduino.util;

import org.jfree.data.time.Minute;

/**
 *
 * @author Herbert
 */
public class ZonasTemperatura {
    
    Double Z1;
    Double Z2;
    Minute min = new Minute();

    public ZonasTemperatura(Double Z1, Double Z2, Minute min) {
        this.Z1 = Z1;
        this.Z2 = Z2;
        this.min = min;
    }

    public Double getZ1() {
        return Z1;
    }

    public void setZ1(Double Z1) {
        this.Z1 = Z1;
    }

    public Double getZ2() {
        return Z2;
    }

    public void setZ2(Double Z2) {
        this.Z2 = Z2;
    }

    public Minute getMin() {
        return min;
    }

    public void setMin(Minute min) {
        this.min = min;
    }

    
    
}
