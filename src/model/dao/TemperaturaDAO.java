/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connections.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.LeituraMaquina;


/**
 *
 * @author Herbert
 */
public class TemperaturaDAO {
    
    public boolean save (LeituraMaquina leitura, String logSis){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = conn.prepareStatement("INSERT INTO RegistroTemperatura (temperaturaZ1,temperaturaZ2,estadoMaquina) values (?,?,?)");
            stmt.setDouble(1, leitura.getTemp1());
            stmt.setDouble(2, leitura.getTemp2());
            stmt.setString(3, leitura.getEstado());
            stmt.executeUpdate();
            return true;
        
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar: " + ex);
            return false;
        }
        
        finally{
        ConnectionFactory.closeConnection(conn,stmt);
        }
        
    }
    
}
