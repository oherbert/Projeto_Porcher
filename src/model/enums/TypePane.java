/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enums;

/**
 *
 * @author Herbert
 */
public enum TypePane {
    
    ERRO(0), INFORMATION(1), WARNING(2), UNKNOW(3);
    
    private final int valor;
    
    TypePane(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
    
}
