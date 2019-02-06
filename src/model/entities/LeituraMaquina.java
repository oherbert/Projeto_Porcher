/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import model.util.Utils;
import java.util.Date;

/**
 *
 * @author Herbert
 */
public class LeituraMaquina {

    private Double temp1;
    private Double temp2;
    private String Estado;
    private Date currentData;

    public LeituraMaquina() {
    }

    public LeituraMaquina(Double temp1, Double temp2, String Estado) {
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.Estado = Estado;
    }

    public Double getTemp1() {
        return temp1;
    }

    public void setTemp1(Double temp1) {
        this.temp1 = temp1;
    }

    public Double getTemp2() {
        return temp2;
    }

    public void setTemp2(Double temp2) {
        this.temp2 = temp2;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Date getTimeStamp() {
        return currentData;
    }

    @Override
    public String toString() {
        return Utils.getDataHoraSistema() + ", " + temp1 + ", " + temp2 + ", " + Estado;
    }

}
