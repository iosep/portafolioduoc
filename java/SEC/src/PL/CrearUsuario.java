/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.RolCTL;
import CTL.UsuarioCTL;
import FN.Formato;
import FN.Validar;
import O.RolO;
import O.UsuarioO;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
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
    private final RolCTL rctl = new RolCTL();

    public void display() {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nuevo Usuario");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 25, 25, 25));
        ColumnConstraints col0 = new ColumnConstraints(120);
        ColumnConstraints col1 = new ColumnConstraints(180);
        ColumnConstraints col2 = new ColumnConstraints(5);
        ColumnConstraints col3 = new ColumnConstraints(60);
        ColumnConstraints col4 = new ColumnConstraints(180);
        grid.getColumnConstraints().addAll(col0, col1, col2, col3, col4);

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nuevo Usuario");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 10, 2, 1);

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
        ChoiceBox cbRol = new ChoiceBox(rctl.getRolesFX());
        grid.add(cbRol, 1, 7);

        Label jefaLbl = new Label("Jefe:");
        grid.add(jefaLbl, 0, 8);
        ChoiceBox cbJefa = new ChoiceBox(uctl.getJefesFX());
        cbJefa.setOnAction(ev -> {
            msj.setText("");
        });
        grid.add(cbJefa, 1, 8);
        jefaLbl.setVisible(false);
        cbJefa.setVisible(false);

        cbRol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RolO>() {
            @Override
            public void changed(ObservableValue<? extends RolO> ov, RolO old_val, RolO new_val) {
                if (new_val != null && new_val.getNombre().equals("FUNCIONARIO")) {
                    msj.setText("");
                    jefaLbl.setVisible(true);
                    cbJefa.setVisible(true);
                } else {
                    msj.setText("");
                    jefaLbl.setVisible(false);
                    cbJefa.setVisible(false);
                }
            }
        });

        Label lblNombre = new Label("Nombre:");
        grid.add(lblNombre, 3, 4);
        TextField txtNombre = new TextField();
        txtNombre.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtNombre, 4, 4);

        Label lblApellido = new Label("Apellido:");
        grid.add(lblApellido, 3, 5);
        TextField txtApellido = new TextField();
        txtApellido.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtApellido, 4, 5);

        Label lblEmail = new Label("Email:");
        grid.add(lblEmail, 3, 6);
        TextField txtEmail = new TextField();
        txtEmail.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtEmail, 4, 6);

        Label lblSexo = new Label("Sexo:");
        grid.add(lblSexo, 3, 7);
        ChoiceBox cbSexo = new ChoiceBox(FXCollections.observableArrayList("MASCULINO", "FEMENINO"));
        cbSexo.setOnAction(ev -> {
            msj.setText("");
        });
        grid.add(cbSexo, 4, 7);

        Label lblFono = new Label("Teléfono:");
        grid.add(lblFono, 3, 8);
        TextField txtFono = new TextField();
        txtFono.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtFono, 4, 8);

        //grid.setGridLinesVisible(true);
        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 11);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText().trim())) {
                String rut = Formato.formatoRut(txtRun.getText());
                UsuarioO checkUser = uctl.getUsuarioByRut(rut);
                if (checkUser.getId() > 0) {
                    if (checkUser.getActivo() == 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.initOwner(window);
                        alert.setTitle("Confirmar Activar");
                        alert.setHeaderText(null);
                        alert.setContentText("Usuario " + rut + " fue Eliminado previamente, desea Re-Activar?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            if (uctl.activaUsuario(checkUser.getId())) {
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.initOwner(window);
                                alerta.setTitle("Activación Exitosa");
                                alerta.setHeaderText(null);
                                alerta.setContentText("Usuario RUT: " + rut + " fue Activado Exitosamente");
                                alerta.showAndWait();
                            }
                            window.close();
                        } else {
                            window.close();
                        }
                    } else if (checkUser.getActivo() == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(window);
                        alert.setTitle("Información");
                        alert.setHeaderText(null);
                        alert.setContentText("RUT Existente");
                        alert.showAndWait();
                    }
                } else if (pwBox.getText().equals(pwBox2.getText())) {
                    if (pwBox.getText().length() < 5) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Contraseña mínimo 5 caractéres");
                    } else if (cbRol.getValue() == null) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Seleccione Rol");
                    } else if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Ingrese Nombre y/o Apellido");
                    } else if (!v.validarEmail(txtEmail.getText())) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Email No Válido");
                    } else if (cbSexo.getValue() == null) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Seleccione Sexo");
                    } else if (!v.validarInteger(txtFono.getText()) || txtFono.getText().length() != 9) {
                        msj.setFill(Color.FIREBRICK);
                        msj.setText("Teléfono Ingrese 9 Números");
                    } else {
                        boolean boo = false;
                        String rut_jefe = "1";
                        if (((RolO) cbRol.getValue()).getNombre().equals("FUNCIONARIO")) {
                            if (cbJefa.getValue() == null) {
                                msj.setFill(Color.FIREBRICK);
                                msj.setText("Seleccione JEFE");
                            } else {
                                boo = true;
                                rut_jefe = ((UsuarioO) cbJefa.getValue()).getRut();
                            }
                        } else {
                            boo = true;
                        }
                        if (boo) {
                            int fono = Integer.parseInt(txtFono.getText());
                            String sSexo = "";
                            switch (cbSexo.getValue().toString()) {
                                case "MASCULINO":
                                    sSexo = "M";
                                    break;
                                case "FEMENINO":
                                    sSexo = "F";
                                    break;
                            }
                            if (uctl.addUsuarioCTL(new UsuarioO(rut, pwBox.getText().trim(),
                                    ((RolO) cbRol.getSelectionModel().getSelectedItem()).getId(),
                                    rut_jefe, txtNombre.getText().trim(), txtApellido.getText().trim(),
                                    txtEmail.getText(), sSexo, fono))) {
                                cbJefa.setItems(uctl.getJefesFX());
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
