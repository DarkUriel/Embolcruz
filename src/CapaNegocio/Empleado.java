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
public class Empleado {
    private String FechaCreacion, CI, Nombres, ApellidoPaterno, ApellidoMaterno, NumeroCuenta, NroTelefono,
            CorreoElectronico;
    private int Estado, Id_Empleado, Id_Categoria;
    DataBase db;

    public Empleado() {
        db = new DataBase();
    }

    public Empleado(int Id_Empleado, String FechaCreacion, String CI, String Nombres, String ApellidoPaterno, String ApellidoMaterno, String NumeroCuenta, String NroTelefono, String CorreoElectronico, int Estado, int Id_Categoria) {
        this.Id_Empleado = Id_Empleado;
        this.FechaCreacion = FechaCreacion;
        this.CI = CI;
        this.Nombres = Nombres;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.NumeroCuenta = NumeroCuenta;
        this.NroTelefono = NroTelefono;
        this.CorreoElectronico = CorreoElectronico;
        this.Estado = Estado;
        this.Id_Categoria = Id_Categoria;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int Id_Empleado) {
        this.Id_Empleado = Id_Empleado;
    }

    
    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(String NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }

    public String getNroTelefono() {
        return NroTelefono;
    }

    public void setNroTelefono(String NroTelefono) {
        this.NroTelefono = NroTelefono;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(int Id_Categoria) {
        this.Id_Categoria = Id_Categoria;
    }
    
    
    public boolean RegistrarEmpleado(String FechaCreacion, String CI, String Nombres, String ApellidoPaterno,
            String ApellidoMaterno, String NumeroCuenta, String NroTelefono, String CorreoElectronico,
            int Estado, int Id_Categoria) throws SQLException{
        return db.RegistrarEmpleado(FechaCreacion, CI, Nombres, ApellidoPaterno, ApellidoMaterno, NumeroCuenta, NroTelefono, CorreoElectronico, Estado, Id_Categoria);
    }
    public boolean ActualizarEmpleado(int Id_Empleado, String FechaCreacion, String CI, String Nombres, String ApellidoPaterno, String ApellidoMaterno, String NumeroCuenta, String NroTelefono, String CorreoElectronico, int Estado, int Id_Categoria) throws SQLException{
        return db.ModificarEmpleado(Id_Empleado, FechaCreacion, CI, Nombres, ApellidoPaterno, ApellidoMaterno, NumeroCuenta, NroTelefono, CorreoElectronico, Estado, Id_Categoria);
    }
    public boolean EliminarEmpleado(int id) throws SQLException{
        return db.EliminarEmpleado(id);
    }
}
