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
public class ModificarPregunta {

    private final PreguntaCTL preguntaCtl = new PreguntaCTL();
    private PreguntaO p0;

    public void display(int id_competencia, int id_pregunta) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Modificar Pregunta");
        p0 = preguntaCtl.getPreguntaById(id_pregunta);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Modificar Pregunta");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 6, 2, 1);

        Label lblPregunta = new Label("Pregunta:");
        grid.add(lblPregunta, 0, 4);
        TextField txtPregunta = new TextField(p0.getPregunta());
        txtPregunta.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtPregunta, 1, 4);

        Button btn = new Button("MODIFICAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 7, 1, 4);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (txtPregunta.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Pregunta");
            } else {
                PreguntaO p1 = new PreguntaO(txtPregunta.getText().trim(), id_competencia);
                p1.setId(id_pregunta);
                if (preguntaCtl.modificarPreguntaCTL(p1)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Pregunta Modificada");
                    alert.setHeaderText(null);
                    alert.setContentText("Pregunta Modificada Exitosamente");
                    alert.showAndWait();
                    window.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("ERROR - Modificar Pregunta");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR al Modificar Pregunta");
                    alert.showAndWait();
                    window.close();
                }
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(ModificarPregunta.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
