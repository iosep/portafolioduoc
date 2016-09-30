/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.ObservacionCTL;
import FN.Validar;
import O.ObservacionO;
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
public class CrearObservacion {

    private final ObservacionCTL observacionCtl = new ObservacionCTL();
    static boolean vb = false;

    public boolean display(int id_competencia) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nueva Observacion");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nueva Observacion");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        Label lblNivelInf = new Label("Nivel Inferior:");
        grid.add(lblNivelInf, 0, 4);
        TextField txtNivelInf = new TextField();
        grid.add(txtNivelInf, 1, 4);

        Label lblNivelSup = new Label("Nivel Superior:");
        grid.add(lblNivelSup, 0, 5);
        TextField txtNivelSup = new TextField();
        grid.add(txtNivelSup, 1, 5);

        Label lblMsjInf = new Label("Mensaje Nivel Inferior:");
        grid.add(lblMsjInf, 0, 6);
        TextField txtMsjInf = new TextField();
        grid.add(txtMsjInf, 1, 6);

        Label lblMsjSup = new Label("Mensaje Nivel Superior:");
        grid.add(lblMsjSup, 0, 7);
        TextField txtMsjSup = new TextField();
        grid.add(txtMsjSup, 1, 7);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 10, 1, 4);

        final Text msj = new Text();
        grid.add(msj, 0, 9, 2, 1);

        btn.setOnAction(e -> {
            msj.getStyleClass().add("action");
            Validar v = new Validar();
            if (txtNivelInf.getText().isEmpty()) {
                msj.setText("Ingrese Nivel Inferior");
            } else if (txtNivelSup.getText().isEmpty()) {
                msj.setText("Ingrese Nivel Superior");
            } else if (txtMsjInf.getText().isEmpty()) {
                msj.setText("Ingrese Mensaje Nivel Inferior");
            } else if (txtMsjSup.getText().isEmpty()) {
                msj.setText("Ingrese Mensaje Nivel Superior");
            } else if (v.validarInteger(txtNivelInf.getText()) && v.validarInteger(txtNivelSup.getText())) {
                Date now = new Date();
                vb = observacionCtl.addObservacionCTL(new ObservacionO(Integer.parseInt(txtNivelInf.getText()), Integer.parseInt(txtNivelSup.getText()), 
                        txtMsjInf.getText(), txtMsjSup.getText(), id_competencia, 1, now, null, null));
                if (vb) {
                    txtNivelInf.clear();
                    txtNivelSup.clear();
                    txtMsjInf.clear();
                    txtMsjSup.clear();
                    msj.setText("Observación Creada Exitosamente");
                } else {
                    msj.setText("Error Al Crear Observación");
                }
            } else {
                msj.setText("Niveles Ingrese Solo Números");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearObservacion.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

        return vb;
    }
}
