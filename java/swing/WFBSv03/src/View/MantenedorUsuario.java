/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Jefes;
import Controller.LoginController;
import Controller.ShaSalt;
import Controller.Usuario;
import Controller.UsuarioController;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author iosep
 */
public class MantenedorUsuario extends javax.swing.JInternalFrame {

    private static UsuarioController uc;
    private static LoginController lc;

    /**
     * Creates new form MantenedorUsuario
     */
    public MantenedorUsuario() {
        initComponents();
        uc = new UsuarioController();
        for (Jefes jefe : uc.listaJefesController()) {
            jComboBox1jefes.addItem(jefe.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1sexo = new javax.swing.ButtonGroup();
        buttonGroup1estado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextField1rutBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1buscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField3amaterno = new javax.swing.JTextField();
        jTextField5direccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6fono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton2mujer = new javax.swing.JRadioButton();
        jTextField7email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField1rut = new javax.swing.JTextField();
        jTextField4apaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2nombres = new javax.swing.JTextField();
        jRadioButton1hombre = new javax.swing.JRadioButton();
        jButton2guardar = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1jefes = new javax.swing.JComboBox<>();
        jRadioButton2Inactivo = new javax.swing.JRadioButton();
        jRadioButton1Activo = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        buttonCrearUsuario = new javax.swing.JButton();

        setTitle("Buscar - Modificar Usuario");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setText("RUT:");

        jButton1buscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1buscar.setText("BUSCAR");
        jButton1buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1buscar)
                    .addComponent(jTextField1rutBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1rutBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1buscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel6.setText("Sexo:");

        jLabel9.setText("Email:");

        jLabel10.setText("Contraseña:");

        jLabel7.setText("Dirección:");

        jLabel8.setText("Fono:");

        jLabel4.setText("Nombres:");

        jLabel3.setText("RUT:");

        buttonGroup1sexo.add(jRadioButton2mujer);
        jRadioButton2mujer.setText("Mujer");
        jRadioButton2mujer.setName("M"); // NOI18N

        jLabel11.setText("Confirmar Contraseña:");

        jTextField1rut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1rutActionPerformed(evt);
            }
        });

        jLabel5.setText("Apellidos:");

        buttonGroup1sexo.add(jRadioButton1hombre);
        jRadioButton1hombre.setText("Hombre");
        jRadioButton1hombre.setName("H"); // NOI18N

        jButton2guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2guardar.setText("MODIFICAR");
        jButton2guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2guardarActionPerformed(evt);
            }
        });

        jLabel1.setText("Jefe/a:");

        jComboBox1jefes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1jefesActionPerformed(evt);
            }
        });

        buttonGroup1estado.add(jRadioButton2Inactivo);
        jRadioButton2Inactivo.setText("Inactivo");
        jRadioButton2Inactivo.setName("M"); // NOI18N

        buttonGroup1estado.add(jRadioButton1Activo);
        jRadioButton1Activo.setText("Activo");
        jRadioButton1Activo.setName("H"); // NOI18N

        jLabel12.setText("Estado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField1)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(36, 36, 36)
                                .addComponent(jRadioButton1Activo)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2Inactivo))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(40, 40, 40)
                                    .addComponent(jTextField1rut, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField2nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(36, 36, 36)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jTextField4apaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField3amaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jRadioButton1hombre)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButton2mujer))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField7email, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField6fono, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField5direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton2guardar)
                                        .addComponent(jComboBox1jefes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4apaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3amaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButton1hombre)
                    .addComponent(jRadioButton2mujer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField6fono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1jefes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jRadioButton1Activo)
                    .addComponent(jRadioButton2Inactivo))
                .addGap(18, 18, 18)
                .addComponent(jButton2guardar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        buttonCrearUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonCrearUsuario.setText("CREAR USUARIO");
        buttonCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCrearUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(buttonCrearUsuario)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(buttonCrearUsuario)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1buscarActionPerformed
        // TODO add your handling code here:
        lc = new LoginController();
        if (lc.validarRut(jTextField1rutBuscar.getText())) {
            String rut = jTextField1rutBuscar.getText();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            rut = rut.substring(0, rut.length() - 1);
            int irut = Integer.parseInt(rut);
            uc = new UsuarioController();
            Usuario userFound = uc.findUserController(irut);
            if (userFound != null) {
                rut = userFound.getRut() + "-" + userFound.getDv();
                jTextField1rut.setText(rut);
                jTextField2nombres.setText(userFound.getNombres());
                jTextField4apaterno.setText(userFound.getApaterno());
                jTextField3amaterno.setText(userFound.getAmaterno());
                jTextField5direccion.setText(userFound.getDireccion());
                String fono = "" + userFound.getFono();
                jTextField6fono.setText(fono);
                jTextField7email.setText(userFound.getEmail());
                switch (userFound.getSexo()) {
                    case 'H':
                        buttonGroup1sexo.setSelected(jRadioButton1hombre.getModel(), true);
                        break;
                    case 'M':
                        buttonGroup1sexo.setSelected(jRadioButton2mujer.getModel(), true);
                        break;
                }
                switch (userFound.getRol()) {
                    case 2:
                        jComboBox1jefes.removeAllItems();
                        jComboBox1jefes.addItem("Es Jefe/a");
                        break;
                    case 3:
                        jComboBox1jefes.removeAllItems();
                        uc = new UsuarioController();
                        ArrayList<Jefes> jefeslist = uc.listaJefesController();
                        for (Jefes jefe : jefeslist) {
                            jComboBox1jefes.addItem(jefe.toString());
                        }
                        for (Jefes jefe : jefeslist) {
                            if (jefe.getId() == userFound.getIdjefe()) {
                                jComboBox1jefes.setSelectedItem(jefe.getNombre());
                            }
                        }
                }
                switch (userFound.getActivo()) {
                    case 0:
                        jRadioButton2Inactivo.setSelected(true);
                        break;
                    case 1:
                        jRadioButton1Activo.setSelected(true);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(this, "USUARIO no encontrado", "ERROR", INFORMATION_MESSAGE);
                jTextField1rutBuscar.setText("");
                jTextField1rutBuscar.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "RUT no válido", "ERROR", INFORMATION_MESSAGE);
            jTextField1rutBuscar.setText("");
            jTextField1rutBuscar.requestFocus();
        }
    }//GEN-LAST:event_jButton1buscarActionPerformed

    private void jTextField1rutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1rutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1rutActionPerformed

    private void jComboBox1jefesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1jefesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1jefesActionPerformed

    private void jButton2guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2guardarActionPerformed
        // TODO add your handling code here:
        lc = new LoginController();
        if (lc.validarRut(jTextField1rut.getText())) {
            String rut = jTextField1rut.getText();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            rut = rut.substring(0, rut.length() - 1);
            String dv = rut.substring(rut.length());
            System.out.println(dv);
            int irut = Integer.parseInt(rut);
            uc = new UsuarioController();
            if (buttonGroup1sexo.getSelection() != null) {
                String sexo = "";
                if (jRadioButton1hombre.isSelected()) {
                    sexo = "H";
                } else if (jRadioButton2mujer.isSelected()) {
                    sexo = "M";
                }
                if (Arrays.equals(jPasswordField1.getPassword(), jPasswordField2.getPassword())) {
                    ShaSalt ss = new ShaSalt();
                    String pass = ss.hashPassword(jPasswordField1.getText());
                    //uc.crearUsuarioController(irut, dv, sexo, pass);
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseñas no coinciden", "ERROR", INFORMATION_MESSAGE);
                    jPasswordField1.setText("");
                    jPasswordField2.setText("");
                    jPasswordField1.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione sexo", "ERROR", INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "RUT no válido", "ERROR", INFORMATION_MESSAGE);
            jTextField1rut.setText("");
            jTextField1rut.requestFocus();
        }

    }//GEN-LAST:event_jButton2guardarActionPerformed

    private void buttonCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCrearUsuarioActionPerformed
        // TODO add your handling code here:
        CrearUsuario cu = new CrearUsuario();
        cu.setVisible(true);
        getParent().add(cu);
        cu.toFront();
    }//GEN-LAST:event_buttonCrearUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCrearUsuario;
    private javax.swing.ButtonGroup buttonGroup1estado;
    private javax.swing.ButtonGroup buttonGroup1sexo;
    private javax.swing.JButton jButton1buscar;
    private javax.swing.JButton jButton2guardar;
    private javax.swing.JComboBox<String> jComboBox1jefes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButton1Activo;
    private javax.swing.JRadioButton jRadioButton1hombre;
    private javax.swing.JRadioButton jRadioButton2Inactivo;
    private javax.swing.JRadioButton jRadioButton2mujer;
    private javax.swing.JTextField jTextField1rut;
    private javax.swing.JTextField jTextField1rutBuscar;
    private javax.swing.JTextField jTextField2nombres;
    private javax.swing.JTextField jTextField3amaterno;
    private javax.swing.JTextField jTextField4apaterno;
    private javax.swing.JTextField jTextField5direccion;
    private javax.swing.JTextField jTextField6fono;
    private javax.swing.JTextField jTextField7email;
    // End of variables declaration//GEN-END:variables
}