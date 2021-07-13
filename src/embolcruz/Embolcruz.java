/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embolcruz;

import CapaDatos.Conector;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dark-uriel
 */
public class Embolcruz {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        Conector con = new Conector();
//        DataBase db = new DataBase();
//        System.out.println(db.ObtenerUsuarios());
        //System.out.println("Dato: " + db.Sesion("User", "60959417"));
        //System.out.println(db.RegistrarUsuario("Admin", "Admin", 1));
        //System.out.println(db.ModificarEstadoUsuario("Admin", "Admin", 3));
        //System.out.println(db.RegistrarCategoria("Administracion", "Administrar", 1));
//       Planilla p = new Planilla(1, 159, "Tercera Prueba", "785544", "741852963", "06-07-2021");
//       System.out.println(db.RegistroPlanilla(p));
//        Usuario[] arr = db.ObtenerUsuarios();
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("Usuario: " + i + " " + arr[i].getLogin() + " " + arr[i].getPassword() + " " + arr[i].getEstado());
//        }

        if (con.Estado()) {
            LogIn login = new LogIn();
            login.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la Base de Datos");
        }
    }
    
}
