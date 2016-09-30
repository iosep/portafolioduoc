/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.UsuarioCTL;
import FN.Cifrar;
import FN.Validar;
import O.UsuarioO;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class CrearUsuario {
    
    private final UsuarioCTL uctl = new UsuarioCTL();
    static boolean vb = false;
    
    public boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nuevo Usuario");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nuevo Usuario");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);
        
        Label rutLbl = new Label("RUT:");
        grid.add(rutLbl, 0, 4);
        TextField txtRun = new TextField();
        grid.add(txtRun, 1, 4);
        
        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 5);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 5);
        
        Label pw2 = new Label("Repetir Contraseña:");
        grid.add(pw2, 0, 6);
        PasswordField pwBox2 = new PasswordField();
        grid.add(pwBox2, 1, 6);
        
        Label rolLbl = new Label("Rol:");
        grid.add(rolLbl, 0, 7);
        ChoiceBox cbRol = new ChoiceBox(FXCollections.observableArrayList("Admin", "Jefa", "Funcionaria"));
        grid.add(cbRol, 1, 7);
        
        Label jefaLbl = new Label("Jefa:");
        grid.add(jefaLbl, 0, 8);
        ChoiceBox cbJefa = new ChoiceBox(FXCollections.observableArrayList(uctl.getUsuariosByRol(2)));
        grid.add(cbJefa, 1, 8);
        jefaLbl.setVisible(false);
        cbJefa.setVisible(false);
        
        cbRol.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if (new_val.intValue() == 2) {
                jefaLbl.setVisible(true);
                cbJefa.setVisible(true);
            } else {
                jefaLbl.setVisible(false);
                cbJefa.setVisible(false);
            }
        });
        
        Label lblNombre = new Label("Nombre:");
        grid.add(lblNombre, 0, 9);
        TextField txtNombre = new TextField();
        grid.add(txtNombre, 1, 9);
        
        Label lblApellido = new Label("Apellido:");
        grid.add(lblApellido, 0, 10);
        TextField txtApellido = new TextField();
        grid.add(txtApellido, 1, 10);
        
        Label lblEmail = new Label("Email:");
        grid.add(lblEmail, 0, 11);
        TextField txtEmail = new TextField();
        grid.add(txtEmail, 1, 11);
        
        Label lblSexo = new Label("Sexo:");
        grid.add(lblSexo, 0, 12);
        ChoiceBox cbSexo = new ChoiceBox(FXCollections.observableArrayList("Hombre", "Mujer"));
        grid.add(cbSexo, 1, 12);
        
        Label lblFono = new Label("Teléfono:");
        grid.add(lblFono, 0, 13);
        TextField txtFono = new TextField();
        grid.add(txtFono, 1, 13);

        //grid.setGridLinesVisible(true);
        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 16, 1, 5);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 15, 2, 1);
        
        btn.setOnAction(e -> {
            actiontarget.getStyleClass().add("action");
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                if (pwBox.getText().equals(pwBox2.getText())) {
                    if (pwBox.getText().length() < 7) {
                        actiontarget.setText("Contraseña mínimo 8 caractéres");
                    } else if (cbRol.getValue() == null) {
                        actiontarget.setText("Seleccione Rol");
                    } else if (txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty()) {
                        actiontarget.setText("Ingrese Nombre y Apellido");
                    } else if (!v.validarEmail(txtEmail.getText())) {
                        actiontarget.setText("Email No Válido");
                    } else if (cbSexo.getValue() == null) {
                        actiontarget.setText("Seleccione Sexo");
                    } else if (!v.validarInteger(txtFono.getText())) {
                        actiontarget.setText("Teléfono Sólo Números");
                    } else {
                        String rutJefa = "";
                        boolean boo = false;
                        if (cbRol.getSelectionModel().getSelectedIndex() == 2) {
                            if (cbJefa.getValue() == null) {
                                actiontarget.setText("Seleccione Jefa");
                            } else {
                                rutJefa = cbJefa.getValue().toString();
                                boo = true;
                            }
                        } else {
                            boo = true;
                        }
                        if (boo) {
                            Cifrar c = new Cifrar();
                            String clave = c.hashPassword(pwBox.getText());
                            int fono = Integer.parseInt(txtFono.getText());
                            Date now = new Date();
                            String sSexo = "";
                            switch (cbSexo.getSelectionModel().getSelectedIndex()) {
                                case 0:
                                    sSexo = "H";
                                    break;
                                case 1:
                                    sSexo = "M";
                                    break;
                                default:
                                    break;
                            }
                            if (uctl.addUsuarioCTL(new UsuarioO(txtRun.getText(), clave, cbRol.getSelectionModel().getSelectedIndex() + 1, rutJefa,
                                    txtNombre.getText(), txtApellido.getText(), txtEmail.getText(), sSexo, fono, 1, now, null, null))) {
                                actiontarget.setText("Usuario Creado Exitosamente");
                                txtRun.clear();
                                pwBox.clear();
                                pwBox2.clear();
                                cbRol.setValue(null);
                                cbJefa.setValue(null);
                                txtNombre.clear();
                                txtApellido.clear();
                                txtEmail.clear();
                                cbSexo.setValue(null);
                                txtFono.clear();
                                vb = true;
                            } else {
                                actiontarget.setText("Error Al Crear Usuario");
                            }
                        }
                    }
                } else {
                    actiontarget.setText("Contraseñas no coinciden");
                }
            } else {
                actiontarget.setText("RUT No Válido");
            }
        });
        
        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearUsuario.class.getResource("Style.css").toExternalForm());
        window.showAndWait();
        
        return vb;
    }
}
