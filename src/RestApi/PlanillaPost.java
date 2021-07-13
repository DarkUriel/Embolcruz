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
public class PlanillaPost {
    int TipoPlanilla;
    String DescripcionPlanilla;
    int CodigoCliente;
    String CodigoUsuario;
    String CuentaDebito;
    String FechaCreacion;

    public PlanillaPost() {
    }

    public PlanillaPost(int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String CodigoUsuario, String CuentaDebito, String FechaCreacion) {
        this.TipoPlanilla = TipoPlanilla;
        this.DescripcionPlanilla = DescripcionPlanilla;
        this.CodigoCliente = CodigoCliente;
        this.CodigoUsuario = CodigoUsuario;
        this.CuentaDebito = CuentaDebito;
        this.FechaCreacion = FechaCreacion;
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
    
}
