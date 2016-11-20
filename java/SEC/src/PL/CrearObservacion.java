/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.ObservacionCTL;
import FN.Validar;
import O.ObservacionO;
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
public class CrearObservacion {

    private final ObservacionCTL obCtl = new ObservacionCTL();

    public void display(int id_competencia) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
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

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 9, 2, 1);

        Label lblNivelInf = new Label("Nivel Inferior:");
        grid.add(lblNivelInf, 0, 4);
        TextField txtNivelInf = new TextField();
        txtNivelInf.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtNivelInf, 1, 4);

        Label lblNivelSup = new Label("Nivel Superior:");
        grid.add(lblNivelSup, 0, 5);
        TextField txtNivelSup = new TextField();
        txtNivelSup.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtNivelSup, 1, 5);

        Label lblMsjInf = new Label("Mensaje Nivel Inferior:");
        grid.add(lblMsjInf, 0, 6);
        TextField txtMsjInf = new TextField();
        txtMsjInf.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtMsjInf, 1, 6);

        Label lblMsjSup = new Label("Mensaje Nivel Superior:");
        grid.add(lblMsjSup, 0, 7);
        TextField txtMsjSup = new TextField();
        txtMsjSup.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtMsjSup, 1, 7);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 10, 1, 4);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (txtNivelInf.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Nivel Inferior");
            } else if (txtNivelSup.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Nivel Superior");
            } else if (txtMsjInf.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Mensaje Nivel Inferior");
            } else if (txtMsjSup.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Mensaje Nivel Superior");
            } else if (v.validarInteger(txtNivelInf.getText().trim()) && v.validarInteger(txtNivelSup.getText().trim())
                    && Integer.parseInt(txtNivelInf.getText().trim()) >= 0 && Integer.parseInt(txtNivelInf.getText().trim()) <= 5
                    && Integer.parseInt(txtNivelSup.getText().trim()) <= 5 && Integer.parseInt(txtNivelSup.getText().trim()) >= 0) {
                if (Integer.parseInt(txtNivelSup.getText().trim()) > Integer.parseInt(txtNivelInf.getText().trim())) {
                    if (obCtl.addObservacionCTL(new ObservacionO(Integer.parseInt(txtNivelInf.getText().trim()), Integer.parseInt(txtNivelSup.getText().trim()),
                            txtMsjInf.getText().trim(), txtMsjSup.getText().trim(), id_competencia))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(window);
                        alert.setTitle("Observación Creada");
                        alert.setHeaderText(null);
                        alert.setContentText("Observación Creada Exitosamente");
                        alert.showAndWait();
                        window.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.initOwner(window);
                        alert.setTitle("Observación Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error Al Crear Observación");
                        alert.showAndWait();
                        window.close();
                    }
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Nivel Superior mayor a Nivel Inferior");
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Niveles Ingrese Solo Números entre 0 y 5");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearObservacion.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
