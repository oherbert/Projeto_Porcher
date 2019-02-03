/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author Herbert
 */
public class Temperatura {
    
    private Integer idRegistro;
    private Date hora_registro;
    private Double temperatuZ1;
    private Double temperatuZ2;
    private String estadoMaquina;

    public Temperatura( Double temperatuZ1, Double temperatuZ2, String estadoMaquina) {
        this.temperatuZ1 = temperatuZ1;
        this.temperatuZ2 = temperatuZ2;
        this.estadoMaquina = estadoMaquina;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getHora_registro() {
        return hora_registro;
    }

    public void setHora_registro(Date hora_registro) {
        this.hora_registro = hora_registro;
    }

    public Double getTemperatuZ1() {
        return temperatuZ1;
    }

    public void setTemperatuZ1(Double temperatuZ1) {
        this.temperatuZ1 = temperatuZ1;
    }

    public Double getTemperatuZ2() {
        return temperatuZ2;
    }

    public void setTemperatuZ2(Double temperatuZ2) {
        this.temperatuZ2 = temperatuZ2;
    }

    public String getEstadoMaquina() {
        return estadoMaquina;
    }

    public void setEstadoMaquina(String estadoMaquina) {
        this.estadoMaquina = estadoMaquina;
    }
    
    
    
}
