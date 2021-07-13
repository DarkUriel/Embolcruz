/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.DataBase;
import java.sql.SQLException;

/**
 *
 * @author dark-uriel
 */
public class Usuario {
    String Login;
    String Password;
    int Estado, Id_Usuario;
    DataBase db;

    public Usuario() {
        db = new DataBase();
    }

    
    public Usuario( int Id_Usuario, String Login, String Password, int Estado) {
        this.Login = Login;
        this.Password = Password;
        this.Estado = Estado;
        this.Id_Usuario = Id_Usuario;
    }

    public Usuario(int random) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    public boolean IniciarSesion() throws SQLException{
        return db.Sesion(Login, Password);
    }
    public boolean RegistrarUsuario(String Login, String Password, int Estado) throws SQLException{
        return db.RegistrarUsuario(Login, Password, Estado);
    }
    public boolean ActualizarUsuario(int Id_Usuario, String Login, String Password, int Estado) throws SQLException{
        return db.ModificarUsuario(Id_Usuario, Login, Password, Estado);
    }
    
    public boolean EliminarUsuario(int id) throws SQLException{
        return db.EliminarUsuario(id);
    }

    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }
    
}
