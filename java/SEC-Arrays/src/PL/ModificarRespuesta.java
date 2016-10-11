/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.RespuestaCTL;
import FN.Validar;
import O.RespuestaO;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class ModificarRespuesta {

    private final RespuestaCTL respuestaCtl = new RespuestaCTL();
    private RespuestaO r0;

    public void display(int id_pregunta, int id_respuesta) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Modificar Respuesta");
        r0 = respuestaCtl.getRespuestaById(id_respuesta);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Modificar Respuesta");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 7, 2, 1);

        Label lblRespuesta = new Label("Respuesta:");
        grid.add(lblRespuesta, 0, 4);
        TextField txtRespuesta = new TextField(r0.getRespuesta());
        txtRespuesta.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtRespuesta, 1, 4);

        Label lblPuntos = new Label("Puntos:");
        grid.add(lblPuntos, 0, 5);
        TextField txtPuntos = new TextField("" + r0.getPuntos());
        txtPuntos.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtPuntos, 1, 5);

        Button btn = new Button("MODIFICAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 8, 1, 4);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (txtRespuesta.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Respuesta");
            } else if (txtPuntos.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Puntos");
            } else if (v.validarInteger(txtPuntos.getText().trim()) && Integer.parseInt(txtPuntos.getText().trim()) >= 0 && Integer.parseInt(txtPuntos.getText().trim()) <= 5) {
                Date now = new Date();
                if (respuestaCtl.addRespuestaCTL(new RespuestaO(txtRespuesta.getText().trim(), Integer.parseInt(txtPuntos.getText().trim()), id_pregunta, now, null))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Respuesta Modificada");
                    alert.setHeaderText(null);
                    alert.setContentText("Respuesta Modificada Exitosamente");
                    alert.showAndWait();
                    window.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("ERROR - Modificar Respuesta");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR al Modificar Respuesta");
                    alert.showAndWait();
                    window.close();
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Puntos Números entre 0 y 5");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(ModificarRespuesta.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
