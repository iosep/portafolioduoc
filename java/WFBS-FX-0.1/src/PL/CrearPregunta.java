/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.PreguntaCTL;
import FN.Validar;
import O.PreguntaO;
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
public class CrearPregunta {

    private final PreguntaCTL preguntaCtl = new PreguntaCTL();
    static boolean vb = false;

    public void display(int id_competencia) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nueva Pregunta");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nueva Pregunta");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        Label lblPregunta = new Label("Pregunta:");
        grid.add(lblPregunta, 0, 4);
        TextField txtPregunta = new TextField();
        grid.add(txtPregunta, 1, 4);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 7, 1, 4);

        final Text msj = new Text();
        grid.add(msj, 0, 6, 2, 1);

        btn.setOnAction(e -> {
            msj.getStyleClass().add("action");
            Validar v = new Validar();
            if (txtPregunta.getText().isEmpty()) {
                msj.setText("Ingrese Pregunta");
            } else {
                Date now = new Date();
                vb = preguntaCtl.addPreguntaCTL(new PreguntaO(txtPregunta.getText(), id_competencia, 1, now, null, null));
                if (vb) {
                    txtPregunta.clear();
                    msj.setText("Pregunta Creada Exitosamente");
                } else {
                    msj.setText("Error Al Crear Pregunta");
                }
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearPregunta.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
