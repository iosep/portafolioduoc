/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.UsuarioCTL;
import FN.Cifrar;
import FN.Formato;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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

    public void display() {
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

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 15, 2, 1);

        Label rutLbl = new Label("RUT:");
        grid.add(rutLbl, 0, 4);
        TextField txtRun = new TextField();
        txtRun.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtRun, 1, 4);

        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 5);
        PasswordField pwBox = new PasswordField();
        pwBox.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox, 1, 5);

        Label pw2 = new Label("Repetir Contraseña:");
        grid.add(pw2, 0, 6);
        PasswordField pwBox2 = new PasswordField();
        pwBox2.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox2, 1, 6);

        Label rolLbl = new Label("Rol:");
        grid.add(rolLbl, 0, 7);
        ChoiceBox cbRol = new ChoiceBox(FXCollections.observableArrayList("Admin", "Jefa", "Funcionaria"));
        grid.add(cbRol, 1, 7);

        Label jefaLbl = new Label("Jefa:");
        grid.add(jefaLbl, 0, 8);
        ChoiceBox cbJefa = new ChoiceBox(FXCollections.observableArrayList(uctl.getUsuariosByRol(2)));
        cbJefa.setOnAction(ev -> {
            msj.setText("");
        });
        grid.add(cbJefa, 1, 8);
        jefaLbl.setVisible(false);
        cbJefa.setVisible(false);

        cbRol.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if (new_val.intValue() == 2) {
                msj.setText("");
                jefaLbl.setVisible(true);
                cbJefa.setVisible(true);
            } else {
                msj.setText("");
                jefaLbl.setVisible(false);
                cbJefa.setVisible(false);
            }
        });

        Label lblNombre = new Label("Nombre:");
        grid.add(lblNombre, 0, 9);
        TextField txtNombre = new TextField();
        txtNombre.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtNombre, 1, 9);

        Label lblApellido = new Label("Apellido:");
        grid.add(lblApellido, 0, 10);
        TextField txtApellido = new TextField();
        txtApellido.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtApellido, 1, 10);

        Label lblEmail = new Label("Email:");
        grid.add(lblEmail, 0, 11);
        TextField txtEmail = new TextField();
        txtEmail.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtEmail, 1, 11);

        Label lblSexo = new Label("Sexo:");
        grid.add(lblSexo, 0, 12);
        ChoiceBox cbSexo = new ChoiceBox(FXCollections.observableArrayList("Hombre", "Mujer"));
        cbSexo.setOnAction(ev -> {
            msj.setText("");
        });
        grid.add(cbSexo, 1, 12);

        Label lblFono = new Label("Teléfono:");
        grid.add(lblFono, 0, 13);
        TextField txtFono = new TextField();
        txtFono.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtFono, 1, 13);

        //grid.setGridLinesVisible(true);
        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 16, 1, 5);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                if (pwBox.getText().equals(pwBox2.getText())) {
                    if (pwBox.getText().length() < 5) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Contraseña mínimo 5 caractéres");
                    } else if (cbRol.getValue() == null) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Seleccione Rol");
                    } else if (txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty()) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Ingrese Nombre y Apellido");
                    } else if (!v.validarEmail(txtEmail.getText())) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Email No Válido");
                    } else if (cbSexo.getValue() == null) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Seleccione Sexo");
                    } else if (!v.validarInteger(txtFono.getText())) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Teléfono Sólo Números");
                    } else if (txtFono.getText().length() != 9) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Teléfono Ingrese 9 Números");
                    } else {
                        String rutJefa = "";
                        boolean boo = false;
                        if (cbRol.getSelectionModel().getSelectedIndex() == 2) {
                            if (cbJefa.getValue() == null) {
                                msj.setFill(Color.FIREBRICK);
                                msj.setText("Seleccione Jefa");
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
                            Formato f = new Formato();
                            String rut = f.formatoRut(txtRun.getText());
                            if (uctl.addUsuarioCTL(new UsuarioO(rut, clave, cbRol.getSelectionModel().getSelectedIndex() + 1, rutJefa,
                                    txtNombre.getText(), txtApellido.getText(), txtEmail.getText(), sSexo, fono, 1, now, null, null))) {
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
                                msj.setFill(Color.GREEN);
                                msj.setText("Usuario Creado Exitosamente");
                            } else {
                                msj.setFill(Color.FIREBRICK);
                                msj.setText("Error Al Crear Usuario");
                            }
                        }
                    }
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Contraseñas no coinciden");
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("RUT No Válido");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearUsuario.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
