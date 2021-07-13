/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.DataBase;

/**
 *
 * @author dark-uriel
 */
public class CargaDetalles {
    private int Id_Empleado, Id_Categoria;
    Double Sueldo;
    String Nombres, ApellidoPaterno, NumeroCuenta, NroTelefono, CorreoElectronico, Nombre;
    DataBase db;
    
    public CargaDetalles() {
        db = new DataBase();
    }

    public CargaDetalles(int Id_Empleado, int Id_Categoria, Double Sueldo, String Nombres, String ApellidoPaterno, String NumeroCuenta, String NroTelefono, String CorreoElectronico, String Nombre) {
        this.Id_Empleado = Id_Empleado;
        this.Id_Categoria = Id_Categoria;
        this.Sueldo = Sueldo;
        this.Nombres = Nombres;
        this.ApellidoPaterno = ApellidoPaterno;
        this.NumeroCuenta = NumeroCuenta;
        this.NroTelefono = NroTelefono;
        this.CorreoElectronico = CorreoElectronico;
        this.Nombre = Nombre;
    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int Id_Empleado) {
        this.Id_Empleado = Id_Empleado;
    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(int Id_Categoria) {
        this.Id_Categoria = Id_Categoria;
    }

    public Double getSueldo() {
        return Sueldo;
    }

    public void setSueldo(Double Sueldo) {
        this.Sueldo = Sueldo;
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

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
