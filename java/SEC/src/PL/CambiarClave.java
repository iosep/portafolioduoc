/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.RespuestaCTL;
import CTL.UsuarioCTL;
import FN.Validar;
import O.UsuarioO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
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
public class CambiarClave {

    private final UsuarioCTL usCtl = new UsuarioCTL();

    public void display(int idUser) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Cambiar Clave");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Cambiar Clave");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 7, 2, 1);

        Label lblRespuesta = new Label("Nueva Clave:");
        grid.add(lblRespuesta, 0, 4);
        PasswordField pwBox = new PasswordField();
        pwBox.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox, 1, 4);

        Label lblPuntos = new Label("Repita Nueva Clave:");
        grid.add(lblPuntos, 0, 5);
        PasswordField pwBox2 = new PasswordField();
        pwBox2.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox2, 1, 5);

        Button btn = new Button("CAMBIAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 8, 1, 4);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (!pwBox.getText().equals(pwBox2.getText())) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Contraseñas distintas");
            } else if (pwBox.getText().length() < 5) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Contraseña mínimo 5 caractéres");
            } else {
                UsuarioO u1 = new UsuarioO();
                u1.setId(idUser);
                u1.setClave(pwBox.getText().trim());
                if (usCtl.updatePass(u1)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Clave Actualizada");
                    alert.setHeaderText(null);
                    alert.setContentText("Clave Actualizada Exitosamente");
                    alert.showAndWait();
                    window.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("ERROR Clave Update");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR al Actualizar Clave");
                    alert.showAndWait();
                    window.close();
                }
            }

        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CambiarClave.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
