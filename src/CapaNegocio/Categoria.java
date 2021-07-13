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
public class Categoria {
    private Double Sueldo;
    String Nombre, Descripcion;
    int Estado, Id_Categoria;
    DataBase db;
    public Categoria() {
        db = new DataBase();
    }

    
    public Categoria(int Id_Categoria, String Nombre, String Descripcion, int Estado, Double Sueldo) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this.Id_Categoria = Id_Categoria;
        this.Sueldo = Sueldo;
    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(int Id_Categoria) {
        this.Id_Categoria = Id_Categoria;
    }
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public Double getSueldo() {
        return Sueldo;
    }

    public void setSueldo(Double Sueldo) {
        this.Sueldo = Sueldo;
    }
    
    
    public boolean RegistrarCategoria(String Nombre, String Descripcion, int Estado, Double Sueldo) throws SQLException{
        return db.RegistrarCategoria(Nombre, Descripcion, Estado, Sueldo);
    }
    public boolean ActualizarCategoria(int Id_Categoria, String Nombre, String Descripcion, int Estado, Double Sueldo) throws SQLException{
        return db.ModificarCategoria(Id_Categoria, Nombre, Descripcion, Estado, Sueldo);
    }
    public boolean EliminarCategoria(int Id_Categoria) throws SQLException{
        return db.EliminarCategoria(Id_Categoria);
    }
    
}
