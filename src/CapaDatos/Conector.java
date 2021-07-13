/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dark-uriel
 */
public class Conector {
    String Url = "";
    String User = "";
    String Passmord = "";
    Connection conexion;
    public Conector(){
        Url = "jdbc:mysql://localhost/DBEmbolcruz";
        User = "root";
        Passmord = "";
    }
    
    public boolean Estado() throws SQLException{
//        return (statement != null)?true:false;
        try {
            conexion = DriverManager.getConnection(Url, User, Passmord);
            Statement statement = conexion.createStatement();
            return (statement != null);
        } catch (SQLException e) {
            return false;
        }
    }
    
     public Statement Conexion(){
        try {
            conexion = DriverManager.getConnection(Url, User, Passmord);
            Statement statement = conexion.createStatement();
            return statement;
        } catch (SQLException e) {
            return (Statement) e;
        } 
     }
}
