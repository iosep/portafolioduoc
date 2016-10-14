/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.NivelCTL;
import FN.Validar;
import O.NivelO;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
public class CrearNivel {

    private final NivelCTL nivelCtl = new NivelCTL();
    static boolean vb = false;

    public void display() {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nuevo Nivel");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nuevo Nivel");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 8, 2, 1);

        Label nombreLbl = new Label("Nombre:");
        grid.add(nombreLbl, 0, 4);
        TextField nombreTxt = new TextField();
        nombreTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(nombreTxt, 1, 4);

        Label notaLbl = new Label("Nota:");
        grid.add(notaLbl, 0, 5);
        TextField notaTxt = new TextField();
        notaTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(notaTxt, 1, 5);

        Label descLbl = new Label("Descripción:");
        grid.add(descLbl, 0, 6);
        TextField descTxt = new TextField();
        descTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(descTxt, 1, 6);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 9, 1, 4);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (nombreTxt.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Nombre");
            } else if (notaTxt.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Nota");
            } else if (descTxt.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Descripción");
            } else if (v.validarInteger(notaTxt.getText().trim()) && Integer.parseInt(notaTxt.getText().trim()) >= 0 && Integer.parseInt(notaTxt.getText().trim()) <= 5) {
                Date now = new Date();
                vb = nivelCtl.addNivelCTL(new NivelO(nombreTxt.getText().trim(), Integer.parseInt(notaTxt.getText().trim()), descTxt.getText().trim(), now, null));
                if (vb) {
                    descTxt.clear();
                    notaTxt.clear();
                    nombreTxt.clear();
                    msj.setFill(Color.GREEN);
                    msj.setText("Nivel Creado Exitosamente");
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Error Al Crear Nivel");
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Nota Ingrese Solo Números entre 0 y 5");
            }
        });

        Scene display = new Scene(grid, 400, 400);
        window.setScene(display);
        display.getStylesheets().add(CrearNivel.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
