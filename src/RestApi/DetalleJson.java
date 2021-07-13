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
public class DetalleJson {
    private Double Importe;
    private String CuentaBeneficiario, Glosa, NumCelular, CorreoElectronico, OrigenFondos, DestinoFondos,
            MotivoTransaccion;
    private int Id_Planilla;

    public DetalleJson() {
    }

    public DetalleJson(Double Importe, String CuentaBeneficiario, String Glosa, String NumCelular, String CorreoElectronico, String OrigenFondos, String DestinoFondos, String MotivoTransaccion, int Id_Planilla) {
        this.Importe = Importe;
        this.CuentaBeneficiario = CuentaBeneficiario;
        this.Glosa = Glosa;
        this.NumCelular = NumCelular;
        this.CorreoElectronico = CorreoElectronico;
        this.OrigenFondos = OrigenFondos;
        this.DestinoFondos = DestinoFondos;
        this.MotivoTransaccion = MotivoTransaccion;
        this.Id_Planilla = Id_Planilla;
    }

    public Double getImporte() {
        return Importe;
    }

    public void setImporte(Double Importe) {
        this.Importe = Importe;
    }

    public String getCuentaBeneficiario() {
        return CuentaBeneficiario;
    }

    public void setCuentaBeneficiario(String CuentaBeneficiario) {
        this.CuentaBeneficiario = CuentaBeneficiario;
    }

    public String getGlosa() {
        return Glosa;
    }

    public void setGlosa(String Glosa) {
        this.Glosa = Glosa;
    }

    public String getNumCelular() {
        return NumCelular;
    }

    public void setNumCelular(String NumCelular) {
        this.NumCelular = NumCelular;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public String getOrigenFondos() {
        return OrigenFondos;
    }

    public void setOrigenFondos(String OrigenFondos) {
        this.OrigenFondos = OrigenFondos;
    }

    public String getDestinoFondos() {
        return DestinoFondos;
    }

    public void setDestinoFondos(String DestinoFondos) {
        this.DestinoFondos = DestinoFondos;
    }

    public String getMotivoTransaccion() {
        return MotivoTransaccion;
    }

    public void setMotivoTransaccion(String MotivoTransaccion) {
        this.MotivoTransaccion = MotivoTransaccion;
    }

    public int getId_Planilla() {
        return Id_Planilla;
    }

    public void setId_Planilla(int Id_Planilla) {
        this.Id_Planilla = Id_Planilla;
    }

    @Override
    public String toString() {
        return "DetalleJson{" + "Importe=" + Importe + ", CuentaBeneficiario=" + CuentaBeneficiario + ", Glosa=" + Glosa + ", NumCelular=" + NumCelular + ", CorreoElectronico=" + CorreoElectronico + ", OrigenFondos=" + OrigenFondos + ", DestinoFondos=" + DestinoFondos + ", MotivoTransaccion=" + MotivoTransaccion + ", Id_Planilla=" + Id_Planilla + '}';
    }
    
}
