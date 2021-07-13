/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestApi;

/**
 *
 * @author dark-uriel
 */
public class PlanillaJson {
    int Id_Planilla;
    int TipoPlanilla;
    String DescripcionPlanilla;
    int CodigoCliente;
    String CodigoUsuario;
    String CuentaDebito;
    String FechaCreacion;

    public PlanillaJson() {
    }

    public PlanillaJson(int Id_Planilla, int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String CodigoUsuario, String CuentaDebito, String FechaCreacion) {
        this.Id_Planilla = Id_Planilla;
        this.TipoPlanilla = TipoPlanilla;
        this.DescripcionPlanilla = DescripcionPlanilla;
        this.CodigoCliente = CodigoCliente;
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

    public String getDescripcionPlanilla() {
        return DescripcionPlanilla;
    }

    public void setDescripcionPlanilla(String DescripcionPlanilla) {
        this.DescripcionPlanilla = DescripcionPlanilla;
    }

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public void setCodigoCliente(int CodigoCliente) {
        this.CodigoCliente = CodigoCliente;
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

    @Override
    public String toString() {
        return "PlanillaJson{" + "Id_Planilla=" + Id_Planilla + ", TipoPlanilla=" + TipoPlanilla + ", DescripcionPlanilla=" + DescripcionPlanilla + ", CodigoCliente=" + CodigoCliente + ", CodigoUsuario=" + CodigoUsuario + ", CuentaDebito=" + CuentaDebito + ", FechaCreacion=" + FechaCreacion + '}';
    }
    
}
