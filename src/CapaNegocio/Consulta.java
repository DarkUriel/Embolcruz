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
public class Consulta {
    int TipoPlanilla, Id_Planilla,  CodigoCliente, Id_Detalle, Id_Empleado, Id_Categoria;
    String CodigoUsuario, CuentaDebito, FechaCreacion, DescripcionPlanilla, Nombres, Nombre, ApellidoPaterno;
    Double Importe;

    DataBase db;
    public Consulta() {
        db = new DataBase();
    }

    public Consulta(int TipoPlanilla, int Id_Planilla, int CodigoCliente, int Id_Detalle, int Id_Empleado, int Id_Categoria, String CodigoUsuario, String CuentaDebito, String FechaCreacion, String DescripcionPlanilla, String Nombres, String Nombre, String ApellidoPaterno, Double Importe) {
        this.TipoPlanilla = TipoPlanilla;
        this.Id_Planilla = Id_Planilla;
        this.CodigoCliente = CodigoCliente;
        this.Id_Detalle = Id_Detalle;
        this.Id_Empleado = Id_Empleado;
        this.Id_Categoria = Id_Categoria;
        this.CodigoUsuario = CodigoUsuario;
        this.CuentaDebito = CuentaDebito;
        this.FechaCreacion = FechaCreacion;
        this.DescripcionPlanilla = DescripcionPlanilla;
        this.Nombres = Nombres;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.Importe = Importe;
    }

    public int getTipoPlanilla() {
        return TipoPlanilla;
    }

    public void setTipoPlanilla(int TipoPlanilla) {
        this.TipoPlanilla = TipoPlanilla;
    }

    public int getId_Planilla() {
        return Id_Planilla;
    }

    public void setId_Planilla(int IdPlanilla) {
        this.Id_Planilla = IdPlanilla;
    }

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public int getId_Detalle() {
        return Id_Detalle;
    }

    public void setId_Detalle(int Id_Detalle) {
        this.Id_Detalle = Id_Detalle;
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

    public String getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(String CodigoUsuario) {
        this.CodigoUsuario = CodigoUsuario;
    }

    public String getCuentaDebito() {
        return CuentaDebito;
    }

    public void setCuentaDebito(String CuentaDebito) {
        this.CuentaDebito = CuentaDebito;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public String getDescripcionPlanilla() {
        return DescripcionPlanilla;
    }

    public void setDescripcionPlanilla(String DescripcionPlanilla) {
        this.DescripcionPlanilla = DescripcionPlanilla;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public Double getImporte() {
        return Importe;
    }

    public void setImporte(Double Importe) {
        this.Importe = Importe;
    }
    
    public Consulta[] ObtenerConsulta(int Id_Planilla) throws SQLException{
        return db.ObtenerPlanilla(Id_Planilla);
    }
    public int ObtenerContador(){
        return db.ContadorConsulta();
    }
}
