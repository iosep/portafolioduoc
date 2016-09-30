/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.CompetenciaCTL;
import FN.Validar;
import O.CompetenciaO;
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
public class CrearCompetencia {

    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    static boolean vb = false;

    public boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nueva Competencia");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nueva Competencia");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        Label nombreLbl = new Label("Nombre:");
        grid.add(nombreLbl, 0, 4);
        TextField nombreTxt = new TextField();
        grid.add(nombreTxt, 1, 4);

        Label siglaLbl = new Label("Sigla:");
        grid.add(siglaLbl, 0, 5);
        TextField siglaTxt = new TextField();
        grid.add(siglaTxt, 1, 5);

        Label descLbl = new Label("Descripción:");
        grid.add(descLbl, 0, 6);
        TextField descTxt = new TextField();
        grid.add(descTxt, 1, 6);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 9, 1, 4);

        final Text msj = new Text();
        grid.add(msj, 0, 8, 2, 1);

        btn.setOnAction(e -> {
            msj.getStyleClass().add("action");
            Validar v = new Validar();
            if (nombreTxt.getText().isEmpty()) {
                msj.setText("Ingrese Nombre");
            } else if (siglaTxt.getText().isEmpty()) {
                msj.setText("Ingrese Sigla");
            } else if (descTxt.getText().isEmpty()) {
                msj.setText("Ingrese Descripción");
            } else {
                Date now = new Date();
                vb = compCtl.addCompetenciaCTL(new CompetenciaO(nombreTxt.getText(), descTxt.getText(), siglaTxt.getText(), 1, now, null, null));
                if (vb) {
                    nombreTxt.clear();
                    descTxt.clear();
                    siglaTxt.clear();
                    msj.setText("Competencia Creada Exitosamente");
                } else {
                    msj.setText("Error Al Crear Competencia");
                }
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearCompetencia.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

        return vb;
    }
}
