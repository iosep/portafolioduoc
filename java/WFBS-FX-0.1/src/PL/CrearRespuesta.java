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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class CrearRespuesta {

    private final RespuestaCTL respuestaCtl = new RespuestaCTL();
    static boolean vb = false;

    public boolean display(int id_pregunta) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nueva Respuesta");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nueva Respuesta");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        Label lblRespuesta = new Label("Respuesta:");
        grid.add(lblRespuesta, 0, 4);
        TextField txtRespuesta = new TextField();
        grid.add(txtRespuesta, 1, 4);

        Label lblPuntos = new Label("Puntos:");
        grid.add(lblPuntos, 0, 5);
        TextField txtPuntos = new TextField();
        grid.add(txtPuntos, 1, 5);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 8, 1, 4);

        final Text msj = new Text();
        grid.add(msj, 0, 7, 2, 1);

        btn.setOnAction(e -> {
            msj.getStyleClass().add("action");
            Validar v = new Validar();
            if (txtRespuesta.getText().isEmpty()) {
                msj.setText("Ingrese Respuesta");
            } else if (txtPuntos.getText().isEmpty()) {
                msj.setText("Ingrese Puntos");
            } else if (v.validarInteger(txtPuntos.getText())) {
                Date now = new Date();
                vb = respuestaCtl.addRespuestaCTL(new RespuestaO(txtRespuesta.getText(), Integer.parseInt(txtPuntos.getText()), id_pregunta, 1, now, null, null));
                if (vb) {
                    txtRespuesta.clear();
                    txtPuntos.clear();
                    msj.setText("Respuesta Creada Exitosamente");
                } else {
                    msj.setText("Error Al Crear Respuesta");
                }
            } else {
                msj.setText("Puntos Ingrese Solo Números");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearRespuesta.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

        return vb;
    }
}
