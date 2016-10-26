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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class ModificarUsuario {

    private final UsuarioCTL uctl = new UsuarioCTL();
    private final RolCTL rctl = new RolCTL();
    private UsuarioO u0;

    public void display(int idUser) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Modificar Usuario");
        u0 = uctl.getUsuarioById(idUser);

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
        Label subtitle = new Label("Modificar Usuario");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 10, 3, 1);

        Label rutLbl = new Label("RUT:");
        grid.add(rutLbl, 0, 4);
        TextField txtRun = new TextField(u0.getRut());
        txtRun.setEditable(false);
        grid.add(txtRun, 1, 4);
//FIX PASSWORD!
        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 5);
        PasswordField pwBox = new PasswordField();
        pwBox.setText("12345");
        pwBox.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox, 1, 5);
        pwBox.setVisible(false);
//FIX PASSWORD!
        Label pw2 = new Label("Repetir Contraseña:");
        grid.add(pw2, 0, 6);
        PasswordField pwBox2 = new PasswordField();
        pwBox2.setText("12345");
        pwBox2.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox2, 1, 6);
        pwBox2.setVisible(false);

        Label rolLbl = new Label("Rol:");
        grid.add(rolLbl, 0, 7);
        ChoiceBox cbRol = new ChoiceBox(rctl.getRolesFX());
        grid.add(cbRol, 1, 7);

        Label jefaLbl = new Label("Jefe:");
        grid.add(jefaLbl, 0, 8);
        ChoiceBox cbJefa = new ChoiceBox(FXCollections.observableArrayList(uctl.getJefesFX()));
        cbJefa.setOnAction(ev -> {
            msj.setText("");
        });
        grid.add(cbJefa, 1, 8);

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
        //load rol
        for (int i = 0; i < cbRol.getItems().size(); i++) {
            if (((RolO) cbRol.getItems().get(i)).getId() == u0.getRolid()) {
                cbRol.getSelectionModel().select(i);
            }
        }
        //set funcionario jefe
        //System.out.println("u0.rolNombre(): " + u0.getRol_nombre());
        if (!u0.getRol_nombre().equals("FUNCIONARIO")) {
            jefaLbl.setVisible(false);
            cbJefa.setVisible(false);
        } else {
            for (int i = 0; i < cbJefa.getItems().size(); i++) {
                if (((UsuarioO) cbJefa.getItems().get(i)).getRut().equals(u0.getRutjefe())) {
                    cbJefa.getSelectionModel().select(i);
                    //System.out.println("i cbJefa.items(): " + i);
                }
            }
        }

        Label lblNombre = new Label("Nombre:");
        grid.add(lblNombre, 3, 4);
        TextField txtNombre = new TextField(u0.getNombre());
        txtNombre.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtNombre, 4, 4);

        Label lblApellido = new Label("Apellido:");
        grid.add(lblApellido, 3, 5);
        TextField txtApellido = new TextField(u0.getApellido());
        txtApellido.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtApellido, 4, 5);

        Label lblEmail = new Label("Email:");
        grid.add(lblEmail, 3, 6);
        TextField txtEmail = new TextField(u0.getEmail());
        txtEmail.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtEmail, 4, 6);

        Label lblSexo = new Label("Sexo:");
        grid.add(lblSexo, 3, 7);
        ChoiceBox cbSexo = new ChoiceBox(FXCollections.observableArrayList("MASCULINO", "FEMENINO"));
        switch (u0.getSexo()) {
            case "M":
                cbSexo.getSelectionModel().select(0);
                break;
            case "F":
                cbSexo.getSelectionModel().select(1);
                break;
        }
        cbSexo.setOnAction(ev -> {
            msj.setText("");
        });
        grid.add(cbSexo, 4, 7);

        Label lblFono = new Label("Teléfono:");
        grid.add(lblFono, 3, 8);
        TextField txtFono = new TextField("" + u0.getFono());
        txtFono.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtFono, 4, 8);

        //grid.setGridLinesVisible(true);
        Button btn = new Button("MODIFICAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 11);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                if (cbRol.getValue() == null) {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Seleccione Rol");
                } else if (txtNombre.getText().trim().equals("") || txtApellido.getText().trim().equals("")) {
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
                        //FIX CLAVE
                        String clave = "12345";
                        boolean add = true;
                        if (pwBox.getLength() > 0 || pwBox2.getLength() > 0) {
                            add = false;
                            if (pwBox.getLength() >= 5 && pwBox2.getLength() >= 5) {
                                if (pwBox.getText().equals(pwBox2.getText())) {
                                    clave = pwBox.getText().trim();
                                    add = true;
                                } else {
                                    msj.setText("Contraseñas no coinciden");
                                }
                            } else {
                                msj.setText("Contraseña mínimo 5 caractéres");
                            }
                        }
                        if (add) {
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
                            String rut = Formato.formatoRut(txtRun.getText());
                            UsuarioO u1 = new UsuarioO(rut, clave,
                                    ((RolO) cbRol.getSelectionModel().getSelectedItem()).getId(),
                                    rut_jefe, txtNombre.getText().trim(), txtApellido.getText().trim(),
                                    txtEmail.getText(), sSexo, fono);
                            u1.setId(u0.getId());
                            if (uctl.updateUser(u1)) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.initOwner(window);
                                alert.setTitle("Usuario Modificado");
                                alert.setHeaderText(null);
                                alert.setContentText("Usuario Modificado Exitosamente");
                                alert.showAndWait();
                                window.close();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.initOwner(window);
                                alert.setTitle("ERROR - Modificar Usuario");
                                alert.setHeaderText(null);
                                alert.setContentText("ERROR al Modificar Usuario");
                                alert.showAndWait();
                                window.close();
                            }
                        }
                    }
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("RUT No Válido");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(ModificarUsuario.class.getResource("Style.css").toExternalForm());
        window.showAndWait();
    }
}
