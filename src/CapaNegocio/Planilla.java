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
public class Planilla {
    private int TipoPlanilla, CodigoCliente, Id_Planilla;
    private String DescripcionPlanilla, CodigoUsuario, CuentaDebito, FechaCreacion;
    DataBase db;
    public Planilla() {
        db = new DataBase();
    }

    public Planilla(int Id_Planilla, int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String CodigoUsuario,
            String CuentaDebito, String FechaCreacion) {
        this.Id_Planilla = Id_Planilla;
        this.TipoPlanilla = TipoPlanilla;
        this.CodigoCliente = CodigoCliente;
        this.DescripcionPlanilla = DescripcionPlanilla;
        this.CodigoUsuario = CodigoUsuario;
        this.CuentaDebito = CuentaDebito;
        this.FechaCreacion = FechaCreacion;
    }

    public int getId_Planilla() {
        return Id_Planilla;
    }

    public void setId_Planilla(int Id_Planilla) {
        this.Id_Planilla = Id_Planilla;
    }

    
    public int getTipoPlanilla() {
        return TipoPlanilla;
    }

    public void setTipoPlanilla(int TipoPlanilla) {
        this.TipoPlanilla = TipoPlanilla;
    }

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
    }

    public String getDescripcionPlanilla() {
        return DescripcionPlanilla;
    }

    public void setDescripcionPlanilla(String DescripcionPlanilla) {
        this.DescripcionPlanilla = DescripcionPlanilla;
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
    
//    public Lista CargarPlanilla(int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String 
//            CodigoUsuario, String CuentaDebito, String FechaCreacion) throws SQLException{
//        return db.CargarPlanilla(TipoPlanilla, DescripcionPlanilla, CodigoCliente, CodigoUsuario, CuentaDebito, FechaCreacion);
//    }
    public boolean EliminarPlanilla(int Id_Planilla) throws SQLException{
        return db.EliminarPlanilla(Id_Planilla);
    }
    public boolean ModificarPlanilla(int Id_Planilla, int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String 
            CodigoUsuario, String CuentaDebito, String FechaCreacion) throws SQLException{
        return db.ModificarPlanilla(Id_Planilla, TipoPlanilla, DescripcionPlanilla, CodigoCliente, CodigoUsuario, CuentaDebito, FechaCreacion);
    }
    
}
