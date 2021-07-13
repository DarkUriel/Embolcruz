/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embolcruz.Presentacion;

import CapaDatos.DataBase;
import CapaNegocio.CargaDetalles;
import CapaNegocio.Categoria;
import CapaNegocio.Consulta;
import CapaNegocio.Detalle;
import CapaNegocio.Empleado;
import CapaNegocio.Lista;
import CapaNegocio.Planilla;
import CapaNegocio.Usuario;
import RestApi.ConsumeRestApi;
import RestApi.PlanillaPost;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dark-uriel
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    DataBase db;
    Usuario[] user;
    Usuario usuario;
    Categoria[] category;
    Categoria[] category2;
    Categoria categoria;
    Empleado[] employee;
    Empleado empleado;
    
    //Planilla
    
    Planilla[] payroll;
    Planilla planilla;
    //Consulta
    Consulta[] consultation;
    Consulta consulta;
    int contadorConsulta;
    CargaDetalles[] cd;
    CargaDetalles cargad;
    
    Detalle d;
    //Lista
    
    Lista l;
    
    boolean estadoP = false;
    
    
    //// Enviar al Servidor
    
    PlanillaPost plpst;
    ConsumeRestApi api;

    public Menu() {
        initComponents();
        this.setLocationRelativeTo(this);
        pnl1.setVisible(false);
        pnl2.setVisible(false);
        pnl3.setVisible(false);
        pnl4.setVisible(false);
        db = new DataBase();
        contadorConsulta = 0;
        
    }

    public void ComboboxUsuarios() throws SQLException {
        user = db.ObtenerUsuarios();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"-- Seleccionar usuario existente --"}));

        //jComboBox1.addItem("-- Seleccionar usuario existente --");
        for (int i = 0; i < user.length; i++) {
            jComboBox1.addItem(user[i].getLogin() + " Estado: " + user[i].getEstado());
        }
    }
    public void ComboboxCategorias() throws SQLException{
        category = db.ObtenerCategorias();
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"-- Seleccionar categoria existente --"}));
        for (int i = 0; i < category.length; i++) {
            jComboBox2.addItem(category[i].getNombre() + " Estado: " + category[i].getEstado());
        }
    }
    public void ComboBoxEmpleados() throws SQLException{
        employee = db.ObtenerEmpleados();
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"-- Seleccionar empleado existente --"}));
        for (int i = 0; i < employee.length; i++) {
            jComboBox3.addItem(employee[i].getNombres() + " " + employee[i].getApellidoPaterno() + " " + employee[i].getApellidoMaterno());
        }
    }
    public void ComboboxCategorias2() throws SQLException{
        category2 = db.ObtenerCategorias();
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"-- Seleccionar categoria existente --"}));
        for (int i = 0; i < category2.length; i++) {
            jComboBox6.addItem(category2[i].getNombre());
        }
    }
    public void ComboboxPlanilla() throws SQLException{
        payroll = db.ComboboxPlanilla();
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"-- Seleccionar planilla existente --"}));
        for (int i = 0; i < payroll.length; i++) {
            jComboBox4.addItem("Tipo planilla: " + payroll[i].getTipoPlanilla() + " Fecha: " + payroll[i].getFechaCreacion());
        }
    }
    public void LimpiarCategoria(){
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextArea1.setText("");
        jTextField21.setText("");
    }

    public void LimpiarUsuario() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
    }
    public void LimpiarEmpleado(){
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        
    }
    public void LimpiarPlanilla(){
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextArea2.setText("");
        jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {{null, null, null, null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}}, new String [] {"Nombre", "Apellido Paterno", "Categoria", "Importe"}) {boolean[] canEdit = new boolean [] {false, false, false, false};public boolean isCellEditable(int rowIndex, int columnIndex) {return canEdit [columnIndex];}});
    }
    
    
    
    public boolean ComprobarCamposUsuario() {
        return !("".equals(jTextField2.getText()) || "".equals(jTextField3.getText()) || "".equals(jTextField4.getText()));
    }
    public boolean ComprobarCamposCategoria(){
        return !("".equals(jTextField6.getText()) || "".equals(jTextField7.getText()) || "".equals(jTextArea1.getText()) || "".equals(jTextField21.getText()));
    }
    public boolean ComprobarEmpleado(){
        return !("".equals(jTextField9.getText()) || "".equals(jTextField10.getText()) || "".equals(jTextField11.getText()) || "".equals(jTextField12.getText()) || "".equals(jTextField13.getText()) || "".equals(jTextField14.getText()) || "".equals(jTextField15.getText()));
    }
    
    public boolean ComprobarPlanilla(){
        return !(jComboBox5.getSelectedIndex() == 0 || jDateChooser2.getCalendar() == null || "".equals(jTextField18.getText()) || "".equals(jTextField19.getText()) || "".equals(jTextField20.getText()) || "".equals(jTextArea2.getText()));
    }

    public String ObtenerJDate(){
        Date fecha = jDateChooser1.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.format(fecha);
    }
    
    public String ObtenerjDate2(){
        Date fecha = jDateChooser2.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        return formato.format(fecha);
    }
    
    public void CargarJTable() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Categoria");
        modelo.addColumn("Importe");
        String cadena;
        cadena = "";
        cd = db.CargarDetalles();
//            modelo.addRow(new Object[]{a, aux.dato.Importe, aux.dato.NumCelular});
        for (int i = 0; i < cd.length; i++) {
            modelo.addRow(new Object[]{cd[i].getNombres(), cd[i].getApellidoPaterno(), cd[i].getNombre(), cd[i].getSueldo()});
        }
        jTable1.setEnabled(false);
        jTable1.setModel(modelo);
        estadoP = true;
    }
    
    public void CargarjtableGeneral(int id_Planilla) throws SQLException{
        consulta = new Consulta();
        consultation = consulta.ObtenerConsulta(id_Planilla);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Categoria");
        modelo.addColumn("Importe");
        for (int i = 0; i < consultation.length; i++) {
            modelo.addRow(new Object[]{consultation[i].getNombres(), consultation[i].getApellidoPaterno(), consultation[i].getNombre(), consultation[i].getImporte()});
        }
        jTable1.setEnabled(false);
        jTable1.setModel(modelo);
        estadoP = true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        pnl2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        pnl3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        pnl4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");

        jPanel1.setBackground(new java.awt.Color(1, 7, 87));

        jPanel2.setBackground(new java.awt.Color(95, 119, 254));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 528));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CapaNegocio/Resources/empresario.png"))); // NOI18N

        jButton2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton2.setText("Administrar Categoria");
        jButton2.setBorder(new javax.swing.border.MatteBorder(null));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton3.setText("Administrar Empleado");
        jButton3.setBorder(new javax.swing.border.MatteBorder(null));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton4.setText("Generar Planilla");
        jButton4.setBorder(new javax.swing.border.MatteBorder(null));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setText("Administrar Usuario");
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 112, 192));

        pnl1.setBackground(new java.awt.Color(231, 243, 253));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccionar usuario existente --" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("Administración de Usuarios");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setText("Id Usuario");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setText("Login");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setText("Password");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setText("Estado");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jButton5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton5.setText("Agregar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton6.setText("Actualizar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton7.setText("Eliminar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(266, 266, 266))
            .addGroup(pnl1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addGroup(pnl1Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        pnl2.setBackground(new java.awt.Color(231, 243, 253));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(1, 1, 1));
        jLabel7.setText("Administrar categoría");

        jComboBox2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccionar categoría existente --" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(1, 1, 1));
        jLabel8.setText("Id Categoría");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(1, 1, 1));
        jLabel9.setText("Nombre");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(1, 1, 1));
        jLabel10.setText("Descripción");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(1, 1, 1));
        jLabel11.setText("Estado");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jTextField7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton8.setText("Agregar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton9.setText("Actualizar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton10.setText("Eliminar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(1, 1, 1));
        jLabel25.setText("Sueldo");

        jTextField21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel7))
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField6)
                                    .addComponent(jScrollPane1)
                                    .addGroup(pnl2Layout.createSequentialGroup()
                                        .addComponent(jButton9)
                                        .addGap(51, 51, 51)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pnl2Layout.createSequentialGroup()
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(66, 66, 66)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(39, 39, 39)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(61, 61, 61))
        );

        pnl3.setBackground(new java.awt.Color(231, 243, 253));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(1, 1, 1));
        jLabel12.setText("Administrar Empleado");

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(1, 1, 1));
        jLabel13.setText("Id Empleado");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione empleado existente --" }));
        jComboBox3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(1, 1, 1));
        jLabel14.setText("Fecha de creacion");

        jDateChooser1.setBackground(new java.awt.Color(254, 254, 254));
        jDateChooser1.setForeground(new java.awt.Color(1, 1, 1));
        jDateChooser1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(1, 1, 1));
        jLabel15.setText("Cedula de identidad");

        jTextField9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(1, 1, 1));
        jLabel16.setText("Num Telefono");

        jTextField10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(1, 1, 1));
        jLabel17.setText("Correo electronico");

        jTextField11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(1, 1, 1));
        jLabel18.setText("Estado");

        jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(1, 1, 1));
        jLabel19.setText("Nombre");

        jTextField12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(1, 1, 1));
        jLabel20.setText("Apellido Paterno");

        jTextField13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(1, 1, 1));
        jLabel21.setText("Num Cuenta");

        jTextField14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(1, 1, 1));
        jLabel22.setText("Apellido Materno");

        jTextField15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton11.setText("Agregar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Actualizar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Eliminar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTextField16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jTextField16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione categoria existente --" }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl3Layout.createSequentialGroup()
                                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(22, 22, 22)
                                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField11)
                                    .addComponent(jTextField9)
                                    .addComponent(jTextField8)
                                    .addComponent(jTextField12)
                                    .addComponent(jTextField13)
                                    .addComponent(jTextField14)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnl3Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl3Layout.createSequentialGroup()
                                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnl3Layout.createSequentialGroup()
                                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(58, 58, 58))
        );

        pnl4.setBackground(new java.awt.Color(231, 243, 253));

        jLabel23.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(1, 1, 1));
        jLabel23.setText("Gestión Planilla");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione planilla existente --" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(1, 1, 1));
        jLabel24.setText("Fecha");

        jTextField17.setEditable(false);
        jTextField17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Id", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Tipo de planilla --", "Sueldo.", "Bono.", "Otro." }));

        jTextField18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Codigo cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jTextField19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Codigo usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jTextField20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cuenta debito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N
        jScrollPane2.setViewportView(jTextArea2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido Paterno", "Categoria", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton14.setText("Registrar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Actualizar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Eliminar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Enviar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Cargar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnl4Layout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(jLabel23))
                            .addGroup(pnl4Layout.createSequentialGroup()
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)))
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField18)
                            .addComponent(jTextField19)
                            .addComponent(jTextField20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15)
                        .addGap(18, 18, 18)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl4Layout.createSequentialGroup()
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnl4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (!this.jButton2.isSelected()) {
            this.jButton1.setSelected(false);
            this.jButton2.setSelected(true);
            this.jButton3.setSelected(false);
            this.jButton4.setSelected(false);
            pnl1.setVisible(false);
            pnl2.setVisible(true);
            pnl3.setVisible(false);
            pnl4.setVisible(false);
            try {
                ComboboxCategorias();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (!this.jButton3.isSelected()) {
            this.jButton1.setSelected(false);
            this.jButton2.setSelected(false);
            this.jButton3.setSelected(true);
            this.jButton4.setSelected(false);
            pnl1.setVisible(false);
            pnl2.setVisible(false);
            pnl3.setVisible(true);
            pnl4.setVisible(false);
            try {
                ComboboxCategorias2();
                ComboBoxEmpleados();
            } catch (SQLException e) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (!this.jButton4.isSelected()) {
            this.jButton1.setSelected(false);
            this.jButton2.setSelected(false);
            this.jButton3.setSelected(false);
            this.jButton4.setSelected(true);
            pnl1.setVisible(false);
            pnl2.setVisible(false);
            pnl3.setVisible(false);
            pnl4.setVisible(true);
            try {
                ComboboxPlanilla();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!this.jButton1.isSelected()) {
            this.jButton1.setSelected(true);
            this.jButton2.setSelected(false);
            this.jButton3.setSelected(false);
            this.jButton4.setSelected(false);
            pnl1.setVisible(true);
            pnl2.setVisible(false);
            pnl3.setVisible(false);
            pnl4.setVisible(false);
            try {
                ComboboxUsuarios();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        LimpiarUsuario();
        if (jComboBox1.getSelectedIndex() != 0) {
            jTextField1.setText(user[jComboBox1.getSelectedIndex() - 1].getId_Usuario() + "");
            jTextField2.setText(user[jComboBox1.getSelectedIndex() - 1].getLogin());
            jTextField3.setText(user[jComboBox1.getSelectedIndex() - 1].getPassword());
            jTextField4.setText(user[jComboBox1.getSelectedIndex() - 1].getEstado() + "");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (ComprobarCamposUsuario()) {
            int estado = 0;
            try {
                estado = Integer.parseInt(jTextField4.getText());
                usuario = new Usuario();
                if (usuario.RegistrarUsuario(jTextField2.getText(), jTextField3.getText(), estado)) {
                    LimpiarUsuario();
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboboxUsuarios();

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al registrar nuevo usuario. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (ComprobarCamposUsuario()) {
            int estado = 0;
            try {
                estado = Integer.parseInt(jTextField4.getText());
                usuario = new Usuario();
                if (usuario.ActualizarUsuario(Integer.parseInt(jTextField1.getText()), jTextField2.getText(), jTextField3.getText(), estado)) {
                    LimpiarUsuario();
                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboboxUsuarios();

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar datos del usuario. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(jTextField1.getText());
            if(JOptionPane.showConfirmDialog(null, "Esta Seguro que desea eliminar al usuario?", "Eliminar Usuario", JOptionPane.DEFAULT_OPTION) == 0){
                usuario = new Usuario();
                if(usuario.EliminarUsuario(id)){
                    LimpiarUsuario();
                    JOptionPane.showMessageDialog(null, "Usuario Eliminado correctamente");
                    ComboboxUsuarios();
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar al usuario.");
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario para eliminar.");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        LimpiarCategoria();
        if (jComboBox2.getSelectedIndex() != 0) {
            jTextField5.setText(category[jComboBox2.getSelectedIndex() - 1].getId_Categoria() + "");
            jTextField6.setText(category[jComboBox2.getSelectedIndex() - 1].getNombre());
            jTextField7.setText(category[jComboBox2.getSelectedIndex() - 1].getEstado() + "");
            jTextArea1.setText(category[jComboBox2.getSelectedIndex() - 1].getDescripcion());
            jTextField21.setText(category[jComboBox2.getSelectedIndex() - 1].getSueldo() + "");
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if (ComprobarCamposCategoria()) {
            int estado = 0;
            Double sueldo = 0.0;
            try {
                estado = Integer.parseInt(jTextField7.getText());
                sueldo = Double.parseDouble(jTextField21.getText());
                categoria = new Categoria();
                if (categoria.RegistrarCategoria(jTextField6.getText(), jTextArea1.getText(), estado, sueldo)) {
                    LimpiarCategoria();
                    JOptionPane.showMessageDialog(null, "Categoria registrada correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboboxCategorias();

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al registrar la nueva categoria. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (ComprobarCamposCategoria()) {
            int estado = 0;
            Double sueldo = 0.0;
            try {
                estado = Integer.parseInt(jTextField7.getText());
                sueldo = Double.parseDouble(jTextField21.getText());
                categoria = new Categoria();
                if (categoria.ActualizarCategoria(Integer.parseInt(jTextField5.getText()), jTextField6.getText(), jTextArea1.getText(), estado, sueldo)) {
                    LimpiarCategoria();
                    JOptionPane.showMessageDialog(null, "Categoria actualizada correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboboxCategorias();

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar datos de la categoria. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(jTextField5.getText());
            if(JOptionPane.showConfirmDialog(null, "Esta Seguro que desea eliminar la categoria?", "Eliminar Categoria", JOptionPane.DEFAULT_OPTION) == 0){
                categoria = new Categoria();
                if(categoria.EliminarCategoria(id)){
                    LimpiarCategoria();
                    JOptionPane.showMessageDialog(null, "Categoria Eliminada correctamente");
                    ComboboxCategorias();
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoria.");
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una categoria para eliminar.");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        LimpiarEmpleado();
        if (jComboBox3.getSelectedIndex() != 0) {
            jTextField8.setText(employee[jComboBox3.getSelectedIndex() - 1].getId_Empleado()+ "");
            jTextField9.setText(employee[jComboBox3.getSelectedIndex() - 1].getCI());
            jTextField10.setText(employee[jComboBox3.getSelectedIndex() - 1].getNroTelefono() + "");
            jTextField11.setText(employee[jComboBox3.getSelectedIndex() - 1].getCorreoElectronico() + "");
            jTextField12.setText(employee[jComboBox3.getSelectedIndex() - 1].getNombres() + "");
            jTextField13.setText(employee[jComboBox3.getSelectedIndex() - 1].getApellidoPaterno() + "");
            jTextField14.setText(employee[jComboBox3.getSelectedIndex() - 1].getNumeroCuenta() + "");
            jTextField15.setText(employee[jComboBox3.getSelectedIndex() - 1].getApellidoMaterno() + "");
            jTextField16.setText(employee[jComboBox3.getSelectedIndex() - 1].getEstado() + "");
            String fecha = employee[jComboBox3.getSelectedIndex() -1].getFechaCreacion();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                cal.setTime(sdf.parse(fecha));
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jDateChooser1.setCalendar(cal);
            int dato = employee[jComboBox3.getSelectedIndex() - 1].getId_Categoria();
            String nom ="";
            for (int i = 0; i < category2.length; i++) {
                if(category2[i].getId_Categoria() == dato){
                    nom = category2[i].getNombre();
                    break;
                }
            }
            jComboBox6.setSelectedItem(nom);
        }else{
            jComboBox6.setSelectedIndex(0);
            jDateChooser1.setCalendar(null);
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if (ComprobarEmpleado()) {
            int estado = 0;
            try {
                estado = Integer.parseInt(jTextField16.getText());
                String fecha = ObtenerJDate();
                empleado = new Empleado();
                if (empleado.RegistrarEmpleado(fecha, jTextField9.getText(), jTextField12.getText(), jTextField13.getText(), jTextField15.getText(), jTextField14.getText(), jTextField10.getText(), jTextField11.getText(), estado, category2[jComboBox6.getSelectedIndex() - 1].getId_Categoria())) {
                    LimpiarEmpleado();
                    JOptionPane.showMessageDialog(null, "Empleado registrado correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboBoxEmpleados();
                    ComboboxCategorias2();
                    jComboBox6.setSelectedIndex(0);
                    jDateChooser1.setCalendar(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al registrar el nuevo empleado. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if (ComprobarEmpleado()) {
            int estado = 0;
            try {
                estado = Integer.parseInt(jTextField16.getText());
                String fecha = ObtenerJDate();
                empleado = new Empleado();
                if (empleado.ActualizarEmpleado(Integer.parseInt(jTextField8.getText()) ,fecha, jTextField9.getText(), jTextField12.getText(), jTextField13.getText(), jTextField15.getText(), jTextField14.getText(), jTextField10.getText(), jTextField11.getText(), estado, category2[jComboBox6.getSelectedIndex() - 1].getId_Categoria())) {
                    LimpiarEmpleado();
                    JOptionPane.showMessageDialog(null, "Datos empleado actualizado correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboboxCategorias2();
                    ComboBoxEmpleados();
                    jComboBox6.setSelectedIndex(0);
                    jDateChooser1.setCalendar(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar datos del empleado. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(jTextField8.getText());
            if(JOptionPane.showConfirmDialog(null, "Esta Seguro que desea eliminar la categoria?", "Eliminar Categoria", JOptionPane.DEFAULT_OPTION) == 0){
                empleado = new Empleado();
                if(empleado.EliminarEmpleado(id)){
                    LimpiarEmpleado();
                    JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente");
                    ComboBoxEmpleados();
                    jComboBox6.setSelectedIndex(0);
                    jDateChooser1.setCalendar(null);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar datos de empleado");
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para eliminar registros.");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        //Metodo para cargar planilla
        if(ComprobarPlanilla()){
            try {
                int codigo = Integer.parseInt(jTextField18.getText());
                CargarJTable();
                estadoP = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos con datos validos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        LimpiarPlanilla();
        if(jComboBox4.getSelectedIndex() != 0){
            jTextField17.setText(payroll[jComboBox4.getSelectedIndex() - 1].getId_Planilla() + "");
            jTextField18.setText(payroll[jComboBox4.getSelectedIndex() - 1].getCodigoCliente()+ "");
            jTextField19.setText(payroll[jComboBox4.getSelectedIndex() - 1].getCodigoUsuario());
            jTextField20.setText(payroll[jComboBox4.getSelectedIndex() - 1].getCuentaDebito());
            jTextArea2.setText(payroll[jComboBox4.getSelectedIndex() - 1].getDescripcionPlanilla());
            jComboBox5.setSelectedIndex(payroll[jComboBox4.getSelectedIndex() -1 ].getTipoPlanilla());
            
            String fecha = payroll[jComboBox4.getSelectedIndex() -1].getFechaCreacion();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                cal.setTime(sdf.parse(fecha));
            } catch (ParseException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            jDateChooser2.setCalendar(cal);
            int dato = payroll[jComboBox4.getSelectedIndex() - 1].getId_Planilla();
            String nom ="";
            for (int i = 0; i < payroll.length; i++) {
                if(payroll[i].getId_Planilla()== dato){
                    nom = payroll[i].getTipoPlanilla() + "";
                    break;
                }
            }
            jComboBox5.setSelectedItem(nom);
            try {
                CargarjtableGeneral(payroll[jComboBox4.getSelectedIndex() - 1].getId_Planilla());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Problema al cargar detalles");
            }
            //estadoP = true;
        }else{
            jComboBox5.setSelectedIndex(0);
            jDateChooser2.setCalendar(null);
            estadoP = false;
        }
//        if(jComboBox4.getSelectedIndex() != 0){
//            jTextField17.setText(consultation[jComboBox4.getSelectedIndex() - 1].getId_Planilla()+ "");
//            jTextField18.setText(consultation[jComboBox4.getSelectedIndex() - 1].getCodigoCliente() + "");
//            jTextField19.setText(consultation[jComboBox4.getSelectedIndex() - 1].getCodigoUsuario());
//            jTextField20.setText(consultation[jComboBox4.getSelectedIndex() - 1].getCuentaDebito());
//            jTextArea2.setText(consultation[jComboBox4.getSelectedIndex() - 1].getDescripcionPlanilla());
//            
//            String fecha = consultation[jComboBox4.getSelectedIndex() -1].getFechaCreacion();
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//            try {
//                cal.setTime(sdf.parse(fecha));
//            } catch (ParseException ex) {
//                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            jDateChooser2.setCalendar(cal);
//            int dato = consultation[jComboBox4.getSelectedIndex() - 1].getId_Planilla();
//            String nom ="";
//            for (int i = 0; i < consultation.length; i++) {
//                if(consultation[i].getId_Planilla()== dato){
//                    nom = consultation[i].getTipoPlanilla() + "";
//                    break;
//                }
//            }
//            jComboBox5.setSelectedItem(nom);
//        }else{
//            jComboBox5.setSelectedIndex(0);
//            jDateChooser2.setCalendar(null);
//        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        if(ComprobarPlanilla() && estadoP == true){
            planilla = new Planilla(1, jComboBox5.getSelectedIndex(), jTextArea2.getText(), Integer.parseInt(jTextField18.getText()), jTextField19.getText(), jTextField20.getText(), ObtenerjDate2());
            try {
                d = new Detalle();
                if(d.RegistrarDetalle(planilla, cd)){
                    JOptionPane.showMessageDialog(null, "Planilla registrada Correctamente");
                    LimpiarPlanilla();
                    jComboBox5.setSelectedIndex(0);
                    jDateChooser2.setCalendar(null);
                    ComboboxPlanilla();
                    estadoP = false;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un problema al registrar la planilla");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Verifique que todos los datos ingresados sean validos");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        //Enviar Planilla
        if(ComprobarPlanilla()){
            try {
                int codigocliente = Integer.parseInt(jTextField18.getText());
                plpst = new PlanillaPost(Integer.parseInt(jTextField17.getText()), jTextArea2.getText(), codigocliente, jTextField19.getText(), jTextField20.getText(), ObtenerjDate2());
                api = new ConsumeRestApi();
                JOptionPane.showMessageDialog(null, "Enviando Planilla al servidor, esto puede tardar un poco");
                if(api.EnviarPlanilla(plpst, db.EnviarDetalle(Integer.parseInt(jTextField17.getText())))){
                    JOptionPane.showMessageDialog(null, "Planilla enviada correctamente.");
                }else{
                    JOptionPane.showMessageDialog(null, "Hubo un problema al enviar la planilla");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al enviar planilla al servidor algunos datos no son validos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una planilla existente para eviar al servidor");
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        //Actualizar Planilla
        if (ComprobarPlanilla()) {
            
            try {
                
                String fecha = ObtenerjDate2();
                planilla = new Planilla();
                if (planilla.ModificarPlanilla(Integer.parseInt(jTextField17.getText()), jComboBox5.getSelectedIndex(), jTextArea2.getText(), Integer.parseInt(jTextField18.getText()), jTextField19.getText(), jTextField20.getText(), fecha)) {
                    LimpiarPlanilla();
                    JOptionPane.showMessageDialog(null, "Datos planilla actualizado correctamente");
                    //pnl1.removeAll();
                    //pnl1.revalidate();
                    //pnl1.repaint();
                    ComboboxPlanilla();
                    jComboBox6.setSelectedIndex(0);
                    jDateChooser1.setCalendar(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un problema al actualizar datos de la planilla. Porfavor "
                            + "verifique si los datos ingresados son los correctos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos validos.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        //Eliminar Planilla
        try {
            int id = Integer.parseInt(jTextField17.getText());
            if(JOptionPane.showConfirmDialog(null, "Esta Seguro que desea eliminar la planilla?", "Eliminar Planilla", JOptionPane.DEFAULT_OPTION) == 0){
                planilla = new Planilla();
                if(planilla.EliminarPlanilla(id)){
                    LimpiarPlanilla();
                    JOptionPane.showMessageDialog(null, "Planilla eliminada correctamente");
                    ComboboxPlanilla();
                    jComboBox5.setSelectedIndex(0);
                    jComboBox4.setSelectedIndex(0);
                    jDateChooser2.setCalendar(null);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar datos de la planilla");
                }
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una planilla para eliminar registros.");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    // End of variables declaration//GEN-END:variables
}
