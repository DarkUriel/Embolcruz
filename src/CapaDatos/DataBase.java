/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaNegocio.CargaDetalles;
import CapaNegocio.Categoria;
import CapaNegocio.Consulta;
import CapaNegocio.Detalle;
import CapaNegocio.Empleado;
import CapaNegocio.Lista;
import CapaNegocio.Nodo;
import CapaNegocio.Planilla;
import CapaNegocio.Usuario;
import RestApi.DetalleJson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dark-uriel
 */
public class DataBase {
    Conector conector;
    Statement db;
    int ContadorCon;
    public DataBase() {
        conector = new Conector();
        db = conector.Conexion();
        ContadorCon = 0;
    }
    //Verifica si usuario existe en la base de datos
    public boolean Sesion(String Login, String Password) throws SQLException{
        String query ="SELECT * FROM Usuario WHERE Login = '" + Login + "' AND Password = '" + Password + "'";
        ResultSet rs = db.executeQuery(query);
        
        return rs.next();  
    }
    
    public boolean VerificarUsuario(String Login) throws SQLException{
        String query = "SELECT * FROM Usuario WHERE Login = '" + Login + "'";
        ResultSet rs = db.executeQuery(query);
        return rs.next();
    }
    
    //Registrar Nuevo Usuario
    public boolean RegistrarUsuario(String Login, String Password, int Estado) throws SQLException{
        String query = "INSERT INTO Usuario(Login, Password, Estado) VALUES ('" + Login + "', '" + Password + "', " + Estado + ")";
        if(Sesion(Login, Password) || VerificarUsuario(Login)){
            return false;
        }else{
            //ResultSet rs = db.executeQuery(query);
            db.executeUpdate(query);
            return true;
        }
    }
    
    public boolean ModificarUsuario(int Id, String Login, String Password, int Estado) throws SQLException{
        String query = "UPDATE Usuario SET Estado = "+ Estado + " , Login = '" + Login + "', Password = '" + Password + "' WHERE Id_Usuario = " + Id;
        if (Sesion(Login, Password) || VerificarUsuario(Login)) {
            return false;
        } else {
            db.executeUpdate(query);
            return true;
        }
    }
    
    public Usuario[] ObtenerUsuarios() throws SQLException{
        String query = "SELECT * FROM Usuario";
        Usuario[] user;
        int contador = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            contador++;
        }
        rs = db.executeQuery(query);
        user = new Usuario[contador];
        for (int i = 0; i < contador; i++) {
            rs.next();
            user[i] = new Usuario(rs.getInt("Id_Usuario"), rs.getString("Login"), rs.getString("Password"), rs.getInt("Estado"));
        }
        return user;
    }
    
    public boolean EliminarUsuario(int id) throws SQLException{
        String query ="DELETE FROM Usuario WHERE Id_Usuario = " + id;
        db.executeUpdate(query);
        String query2 = "SELECT * FROM Usuario WHERE Id_Usuario = " + id;
        ResultSet rs = db.executeQuery(query2);
        return !rs.next();
    }
    
    /*
    
    Metodos para la Administracion de Categorias
    
    */
    
    public boolean vCategoria(String Nombre, String Descripcion) throws SQLException{
        String query = "SELECT * FROM Categoria WHERE Nombre = '" + Nombre + "' AND Descripcion = '" + Descripcion +"'";
        ResultSet rs = db.executeQuery(query);
        return rs.next(); 
    }
    
    public boolean VerificarCategoria(int Id_Categoria ,String Nombre) throws SQLException{
        String query = "SELECT * FROM Categoria WHERE Nombre = '" + Nombre +"' AND Id_Categoria != " + Id_Categoria;
        ResultSet rs = db.executeQuery(query);
        return rs.next();
    }
    
    public boolean RegistrarCategoria(String Nombre, String Descripcion, int Estado, Double Sueldo) throws SQLException{
        String query = "INSERT INTO Categoria (Nombre, Descripcion, Estado, Sueldo) VALUES ('" + Nombre + "', '" + Descripcion + "', " + Estado + ", " + Sueldo + ")";
        if (vCategoria(Nombre, Descripcion)) {
            return false;
        } else {
            db.executeUpdate(query);
            return true;
        }
    }
    
    public Categoria[] ObtenerCategorias() throws SQLException{
        Categoria[] cat;
        String query = "SELECT * FROM Categoria";
        int cont = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            cont++;
        }
        rs = db.executeQuery(query);
        cat = new Categoria[cont];
        for (int i = 0; i < cont; i++) {
            rs.next();
            cat[i] = new Categoria(rs.getInt("Id_Categoria"), rs.getString("Nombre"), rs.getString("Descripcion"), rs.getInt("Estado"), rs.getDouble("Sueldo"));
        }
        return cat;
    }
    
    public boolean ModificarCategoria(int Id_Categoria, String Nombre, String Descripcion, int Estado, Double Sueldo) throws SQLException{
        String query = "UPDATE Categoria SET Estado = " + Estado + ", Nombre = '" + Nombre + "', Descripcion = '" + Descripcion + "', Sueldo = " + Sueldo + " WHERE Id_Categoria = " + Id_Categoria;
        if (VerificarCategoria(Id_Categoria ,Nombre)) {
            return false;
        } else {
            db.executeUpdate(query);
            return true;
        }
    }
    
    public boolean EliminarCategoria(int id) throws SQLException{
        String querycategoria = "DELETE FROM Empleado WHERE Id_Categoria = " + id;
        String query = "DELETE FROM Categoria WHERE Id_Categoria = " + id;
        db.executeUpdate(query);
        String query2 = "SELECT * FROM Categoria WHERE Id_Categoria = " + id;
        ResultSet rs = db.executeQuery(query2);
        return !rs.next();
    }
    
    /*
    
        Metodos para administrar Empleados
    
    */
    
    public boolean vEmpleado(String CI, String Nombres) throws SQLException{
        String query = "SELECT * FROM Empleado WHERE CI = '" + CI + "' AND Nombres = '" + Nombres +"'";
        ResultSet rs = db.executeQuery(query);
        return rs.next();
    }
    
    public boolean VerificarEmpleado(int Id_Empleado, String Nombres, String CI) throws SQLException{
        String query = "SELECT * FROM Empleado WHERE Nombres = '"+ Nombres + "' "
                + "AND CI = '" + CI + "' AND Id_Empleado != " + Id_Empleado;
        ResultSet rs = db.executeQuery(query);
        //Si existe en la base de datos Verdadero
        return rs.next();
    }
    
    public boolean RegistrarEmpleado(String FechaCreacion, String CI, String Nombres, String ApellidoPaterno,
            String ApellidoMaterno, String NumeroCuenta, String NroTelefono, String CorreoElectronico,
            int Estado, int Id_Categoria) throws SQLException{
        String query = "INSERT INTO Empleado (FechaCreacion, CI, Nombres, ApellidoPaterno, ApellidoMaterno, "
                + "NumeroCuenta, NroTelefono, CorreoElectronico, Estado, Id_Categoria) VALUES ('" + FechaCreacion + "',"
                + " '" + CI + "', '" + Nombres + "', '" + ApellidoPaterno + "', '" + ApellidoMaterno + "',"
                + " '" + NumeroCuenta + "', '" + NroTelefono + "', '" + CorreoElectronico + "'," + Estado + ", "
                + "" + Id_Categoria + ")";
        if (vEmpleado(CI, Nombres)) {
            return false;
        } else {
            db.executeUpdate(query);
            return true;
        }
    }
    
    public Empleado[] ObtenerEmpleados() throws SQLException{
        Empleado[] emp;
        String query = "SELECT * FROM Empleado";
        int cont = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            cont++;
        }
        rs = db.executeQuery(query);
        emp = new Empleado[cont];
        for (int i = 0; i < cont; i++) {
            rs.next();
            emp[i] = new Empleado(rs.getInt("Id_Empleado"), rs.getString("FechaCreacion"), rs.getString("CI"), rs.getString("Nombres"), rs.getString("ApellidoPaterno"), rs.getString("ApellidoMaterno"), rs.getString("NumeroCuenta"), rs.getString("NroTelefono"), rs.getString("CorreoElectronico"), rs.getInt("Estado"), rs.getInt("Id_Categoria"));
        }
        return emp;
    }
    
    public boolean ModificarEmpleado(int Id_Empleado, String FechaCreacion, String CI, String Nombres, String ApellidoPaterno, String ApellidoMaterno, String NumeroCuenta, String NroTelefono, String CorreoElectronico, int Estado, int Id_Categoria) throws SQLException{
        String query = "UPDATE Empleado SET FechaCreacion = '" + FechaCreacion +"', "
                + "CI = '" + CI + "', Nombres = '" + Nombres + "', "
                + "ApellidoPaterno = '" + ApellidoPaterno + "', "
                + "ApellidoMaterno = '" + ApellidoMaterno + "', "
                + "NumeroCuenta = '" + NumeroCuenta + "', NroTelefono = '" + NroTelefono + "', "
                + "CorreoElectronico = '" + CorreoElectronico + "', "
                + "Estado = " + Estado + ", Id_Categoria = " + Id_Categoria + " WHERE Id_Empleado = " + Id_Empleado;
        if (VerificarEmpleado(Id_Empleado, Nombres, CI)) {
            return false;
        } else {
            db.executeUpdate(query);
            return true;
        }
    }
    
    public boolean EliminarEmpleado(int id) throws SQLException{
        String empleado = "DELETE FROM Detalle WHERE Id_Empleado = " + id;
        db.executeUpdate(empleado);
        String query = "DELETE FROM Empleado WHERE Id_Empleado = " + id;
        db.executeUpdate(query);
        String query2 = "SELECT * FROM Empleado WHERE Id_Empleado = " + id;
        ResultSet rs = db.executeQuery(query2);
        return !rs.next();
    }
    
    /*
        Metodos para el llenado de planillas
        Utilizando arreglos o listas simples
    */
    
    
    public boolean vPlanilla(int TipoPlanilla, String FechaCreacion, String CodigoUsuario) throws SQLException{
        String query = "SELECT * FROM Planilla WHERE TipoPlanilla = " + TipoPlanilla + " AND "
                + "FechaCreacion = '" + FechaCreacion + "' AND CodigoUsuario = '" + CodigoUsuario +"'" ;
        ResultSet rs = db.executeQuery(query);
        return rs.next();
    }
    
    public int RegistrarPlanilla(int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String 
            CodigoUsuario, String CuentaDebito, String FechaCreacion) throws SQLException{
        String query = "INSERT INTO Planilla (TipoPlanilla, DescripcionPlanilla, CodigoCliente, CodigoUsuario, "
                + "CuentaDebito, FechaCreacion) VALUES (" + TipoPlanilla + ", '" + DescripcionPlanilla +"'"
                + ", " + CodigoCliente + " , '" + CodigoUsuario + "', '" + CuentaDebito + "', '" + FechaCreacion +"')";
        String ID = "SELECT * FROM Planilla ORDER BY Id_Planilla DESC LIMIT 1";
        int Id_Planilla = 0;
        db.executeUpdate(query);
        ResultSet rs = db.executeQuery(ID);
        rs.next();
        Id_Planilla = rs.getInt("Id_Planilla");
        return Id_Planilla;
    }
    
    public boolean ModificarPlanilla(int Id_Planilla, int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String 
            CodigoUsuario, String CuentaDebito, String FechaCreacion) throws SQLException{
        String query = "UPDATE Planilla SET TipoPlanilla = " + TipoPlanilla + ","
                + " DescripcionPlanilla = '" + DescripcionPlanilla + "',"
                + " CodigoCliente = " + CodigoCliente + " ,"
                + " CodigoUsuario = '" + CodigoUsuario + "',"
                + " CuentaDebito = '" + CuentaDebito + "',"
                + " FechaCreacion = '" + FechaCreacion + "' WHERE Id_Planilla = " + Id_Planilla;
        if(VerificarPlanilla(Id_Planilla, TipoPlanilla, FechaCreacion, CodigoUsuario)){
            return false;
        }else{
            db.executeUpdate(query);
            return true;
        }
    }
    
    public boolean VerificarPlanilla(int Id_Planilla, int TipoPlanilla, String FechaCreacion, String CodigoUsuario) throws SQLException{
        String query = "SELECT * FROM Planilla WHERE TipoPlanilla = " + TipoPlanilla + " AND "
                + "FechaCreacion = '" + FechaCreacion + "' AND CodigoUsuario = '" + CodigoUsuario +"'"
                + " AND Id_Planilla != " + Id_Planilla ;
        ResultSet rs = db.executeQuery(query);
        return rs.next();
    }
    
    public boolean EliminarPlanilla(int Id_Planilla) throws SQLException{
        String detalle = "DELETE FROM Detalle WHERE Id_Planilla = " + Id_Planilla;
        db.executeUpdate(detalle);
        String query = "DELETE FROM Planilla WHERE Id_Planilla = " + Id_Planilla;
        db.executeUpdate(query);
        String query2 = "SELECT * FROM Planilla WHERE Id_Planilla = " + Id_Planilla;
        ResultSet rs = db.executeQuery(query2);
        return !rs.next();
    }
    
    public Consulta[] ObtenerPlanilla(int Id_Planilla) throws SQLException{
        Consulta[] consulta;
        String query = "SELECT Planilla.TipoPlanilla, Planilla.FechaCreacion, Planilla.Id_Planilla, Planilla.CodigoCliente, Planilla.CodigoUsuario, Planilla.CuentaDebito, Planilla.DescripcionPlanilla, Detalle.Importe, Empleado.Nombres, Empleado.ApellidoPaterno, Categoria.Nombre, Detalle.Id_Detalle, Empleado.Id_Empleado, Categoria.Id_Categoria FROM Detalle INNER JOIN Planilla on Detalle.Id_Planilla = Planilla.Id_Planilla INNER JOIN Empleado ON Detalle.Id_Empleado = Empleado.Id_Empleado INNER JOIN Categoria ON Empleado.Id_Categoria = Categoria.Id_Categoria WHERE Planilla.Id_Planilla = " + Id_Planilla;
        ContadorCon = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            ContadorCon++;
        }
        rs = db.executeQuery(query);
        consulta = new Consulta[ContadorCon];
        for (int i = 0; i < ContadorCon; i++) {
            rs.next();
            consulta[i] = new Consulta(rs.getInt("TipoPlanilla"), rs.getInt("Id_Planilla"), rs.getInt("CodigoCliente"), rs.getInt("Id_Detalle"), rs.getInt("Id_Empleado"), rs.getInt("Id_Categoria"), rs.getString("CodigoUsuario"), rs.getString("CuentaDebito"), rs.getString("FechaCreacion"), rs.getString("DescripcionPlanilla"), rs.getString("Nombres"), rs.getString("Nombre"), rs.getString("ApellidoPaterno"), rs.getDouble("Importe"));
        }
        return consulta;
    }
    public int ContadorConsulta(){
        return ContadorCon;
    }
    
    public Planilla[] ComboboxPlanilla() throws SQLException{
        Planilla[] pl;
        String query = "SELECT * FROM Planilla";
        int cont = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            cont++;
        }
        rs = db.executeQuery(query);
        pl = new Planilla[cont];
        for (int i = 0; i < cont; i++) {
            rs.next();
            pl[i] = new Planilla(rs.getInt("Id_Planilla"), rs.getInt("TipoPlanilla"), rs.getString("DescripcionPlanilla"), rs.getInt("CodigoCliente"), rs.getString("CodigoUsuario"),rs.getString("CuentaDebito") , rs.getString("FechaCreacion"));
        }
        return pl;
    }
    
    public CargaDetalles[] CargarDetalles() throws SQLException{
        CargaDetalles[] cd;
        String query ="SELECT Id_Empleado, Nombres, ApellidoPaterno, NumeroCuenta, NroTelefono, CorreoElectronico, Nombre, Sueldo, Categoria.Id_Categoria FROM Empleado INNER JOIN Categoria ON Empleado.Id_Categoria = Categoria.Id_Categoria";
        int cont = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            cont++;
        }
        rs = db.executeQuery(query);
        cd = new CargaDetalles[cont];
        for (int i = 0; i < cont; i++) {
            rs.next();
            cd[i] = new CargaDetalles(rs.getInt("Id_Empleado"), rs.getInt("Id_Categoria"), rs.getDouble("Sueldo"), rs.getString("Nombres"), rs.getString("ApellidoPaterno"), rs.getString("NumeroCuenta"), rs.getString("NroTelefono"), rs.getString("CorreoElectronico"), rs.getString("Nombre"));
            
        }
        return cd;
    }
    
    
    //Metodo complicado para cargar la planilla
    
//    public Lista CargarPlanilla(int TipoPlanilla, String DescripcionPlanilla, int CodigoCliente, String 
//            CodigoUsuario, String CuentaDebito, String FechaCreacion) throws SQLException{
//        int Id_Planilla = RegistrarPlanilla(TipoPlanilla, DescripcionPlanilla, CodigoCliente, CodigoUsuario, CuentaDebito, FechaCreacion);
//        String query = "SELECT Empleado.NumeroCuenta, Categoria.Sueldo, Planilla.DescripcionPlanilla, Empleado.NroTelefono, CorreoElectronico, Planilla.CuentaDebito, Planilla.TipoPlanilla, Planilla.Id_Planilla, Empleado.Id_Empleado FROM Empleado INNER JOIN Categoria ON Empleado.Id_Categoria = Categoria.Id_Categoria INNER JOIN Planilla on Id_Planilla = " + Id_Planilla + " WHERE Empleado.Estado = 1";
//        ResultSet rs = db.executeQuery(query);
//        Lista l = new Lista();
//        Detalle d;
//        
//        //Examinar el bloque si funciona o no
//        
//        while(rs.next()){
//            d = new Detalle();
//            d.setCuentaBeneficiario(rs.getString("NumeroCuenta"));
//            d.setImporte(rs.getDouble("Sueldo"));
//            d.setGlosa(rs.getString("DescripcionPlanilla"));
//            d.setNumCelular(rs.getString("NroTelefono"));
//            d.setCorreoElectronico(rs.getString("CorreoElectronico"));
//            d.setOrigenFondos(rs.getString("CuentaDebito"));
//            d.setDestinoFondos(rs.getString("NumeroCuenta"));
//            d.setMotivoTransaccion(rs.getString("TipoPlanilla"));
//            d.setId_Planilla(Id_Planilla);
//            d.setId_Empleado(rs.getInt("Id_Empleado"));
//            l.Insertar(d);
//        }
//        return l;
//    }
    
    public boolean RegistrarDetalle(Planilla planilla, CargaDetalles[] cargadetalles) throws SQLException{
        String query ="INSERT INTO Planilla (TipoPlanilla, DescripcionPlanilla, CodigoCliente, CodigoUsuario, "
                + "CuentaDebito, FechaCreacion) VALUES (" + planilla.getTipoPlanilla() + ", '" + planilla.getDescripcionPlanilla() + "'"
                + "," + planilla.getCodigoCliente() + " , '" + planilla.getCodigoUsuario() +"',"
                + " '" + planilla.getCuentaDebito() + "', '" + planilla.getFechaCreacion() + "')";
        db.executeUpdate(query);
        String queryplanilla = "SELECT * FROM Planilla ORDER BY Id_Planilla DESC LIMIT 1";
        ResultSet rs = db.executeQuery(queryplanilla);
        rs.next();
        planilla.setId_Planilla(rs.getInt("Id_Planilla"));
        String insert = "";
        ResultSet dt = db.executeQuery("SELECT * FROM Detalle ORDER BY Id_Detalle DESC LIMIT 1");
        dt.next();
        System.out.println("longitud: " + cargadetalles.length);
        int contID = 0;

        for (int i = 0; i < cargadetalles.length; i++) {
            insert = "INSERT INTO Detalle (CuentaBeneficiario, Importe, Glosa, NumCelular, CorreoElectronico, OrigenFondos, DestinoFondos, MotivoTransaccion, Id_Planilla, Id_Empleado) VALUES ('" + cargadetalles[i].getNumeroCuenta() + "', " + cargadetalles[i].getSueldo() + ", '" + planilla.getDescripcionPlanilla() + "', '" + cargadetalles[i].getNroTelefono() + "', '" + cargadetalles[i].getCorreoElectronico() + "', '" + planilla.getCuentaDebito() + "', '" + cargadetalles[i].getNumeroCuenta() + "', '" + planilla.getTipoPlanilla() + "', " + planilla.getId_Planilla() + ", " + cargadetalles[i].getId_Empleado() + ")";
            db.executeUpdate(insert);
        }
        ResultSet id = db.executeQuery("SELECT * FROM Detalle ORDER BY Id_Detalle DESC LIMIT 1");
        id.next();
        int contID2 = id.getInt("Id_Detalle");
        return contID < contID2;
    }
    
    public boolean RegistroDetalle(Lista lista) throws SQLException{
        Nodo aux = new Nodo();
        aux = lista.getLista();
        String query = "";
        int cont = 0;
        ResultSet rs = db.executeQuery("SELECT * FROM Detalle ORDER BY Id_Detalle DESC LIMIT 1");
        rs.next();
        cont = rs.getInt("Id_Detalle");
        while (aux != null) {            
            query = "INSERT INTO Detalle (CuentaBeneficiario, Importe, Glosa, NumCelular, CorreoElectronico, "
                  + "OrigenFondos, DestinoFondos, MotivoTransaccion, Id_Planilla, Id_Empleado) VALUES ("
                  + "'" + aux.detalle.getCuentaBeneficiario() + "',"
                  + " " + aux.detalle.getImporte() + " , '" + aux.detalle.getGlosa() + "',"
                  + " '" + aux.detalle.getNumCelular() + "', '" + aux.detalle.getCorreoElectronico() + "',"
                  + " '" + aux.detalle.getOrigenFondos() + "', '" + aux.detalle.getDestinoFondos() + "',"
                  + " '" + aux.detalle.getMotivoTransaccion() + "', " + aux.detalle.getId_Planilla() + ", " + aux.detalle.getId_Empleado() + ")";
            db.executeUpdate(query);
            aux = aux.Siguiente;
        }
        rs = db.executeQuery("SELECT * FROM Detalle ORDER BY Id_Detalle DESC LIMIT 1");
        rs.next();
        return cont < rs.getInt("Id_Detalle");
    }
//    public int RegistroPlanilla(Planilla planilla) throws SQLException{
//        String query = "INSERT INTO Planilla (TipoPlanilla, DescripcionPlanilla, CodigoCliente, CodigoUsuario, "
//                + "CuentaDebito, FechaCreacion) VALUES (" + planilla.getTipoPlanilla() + ", "
//                + "'" + planilla.getDescripcionPlanilla() + "', " + planilla.getCodigoCliente() + ", "
//                + "'" + planilla.getCodigoUsuario() + "', '" + planilla.getCuentaDebito() + "', '" + planilla.getFechaCreacion() + "')";
//        String ID = "SELECT * FROM Planilla ORDER BY Id_Planilla DESC LIMIT 1";
//        int id_planilla = 0;
//        try {
//            db.executeUpdate(query);
//            ResultSet rsid = db.executeQuery(ID);
//            rsid.next();
//            id_planilla = rsid.getInt("Id_Planilla");
//            return id_planilla;
//        } catch (SQLException e) {
//            return id_planilla;
//        }
//    }
//    public boolean RegistroDetalle(Lista lista) throws SQLException{
//        Nodo aux = new Nodo();
//        aux = lista.getLista();
//        String query = "";
//        int cont = 0;
//        ResultSet rs = db.executeQuery("SELECT * FROM Detalle");
//        rs.next();
//        cont = rs.getInt("Id_Detalle");
//        while (aux != null) {            
//            query = "INSERT INTO Detalle (CuentaBeneficiario, Importe, Glosa, NumCelular, CorreoElectronico, "
//                    + "OrigenFondos, DestinoFondos, MotivoTransaccion, Id_Planilla) VALUES ("
//                    + "'" + aux.detalle.getCuentaBeneficiario() + "',"
//                    + " " + aux.detalle.getImporte() + " , '" + aux.detalle.getGlosa() + "',"
//                    + " '" + aux.detalle.getNumCelular() + "', '" + aux.detalle.getCorreoElectronico() + "',"
//                    + " '" + aux.detalle.getOrigenFondos() + "', '" + aux.detalle.getDestinoFondos() + "',"
//                    + " '" + aux.detalle.getMotivoTransaccion() + "', " + aux.detalle.getId_Planilla() + ")";
//            db.executeUpdate(query);
//            aux = aux.Siguiente;
//        }
//        rs = db.executeQuery("SELECT * FROM Detalle");
//        rs.next();
//        return cont < rs.getInt("Id_Detalle");
//    }
    /////// Envio a la Api
    public DetalleJson[] EnviarDetalle(int Id_Planilla) throws SQLException{
        String query = "SELECT * FROM Detalle WHERE Id_Planilla = " + Id_Planilla;
        int cont = 0;
        ResultSet rs = db.executeQuery(query);
        while (rs.next()) {            
            cont++;
        }
        rs = db.executeQuery(query);
        DetalleJson[] detalle = new DetalleJson[cont];
        for (int i = 0; i < cont; i++) {
            rs.next();
            detalle[i] = new DetalleJson(rs.getDouble("Importe"), rs.getString("CuentaBeneficiario"), rs.getString("Glosa"), rs.getString("NumCelular"), rs.getString("CorreoElectronico"), rs.getString("OrigenFondos"), rs.getString("DestinoFondos"), rs.getString("MotivoTransaccion"), rs.getInt("Id_Planilla"));
        }
        return detalle;
    }
}
