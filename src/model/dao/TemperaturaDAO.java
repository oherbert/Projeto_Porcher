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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Temperatura;

/**
 *
 * @author Herbert
 */
public class TemperaturaDAO {
    
    public boolean save (Temperatura t, String logSis){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = conn.prepareStatement("INSERT INTO RegistroTemperatura (temperaturaZ1,temperaturaZ2,estadoMaquina, logSistema) values (?,?,?,?)");
            stmt.setDouble(1, t.getTemperatuZ1());
            stmt.setDouble(2, t.getTemperatuZ2());
            stmt.setString(3, t.getEstadoMaquina());
            stmt.setString(4, logSis);
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
