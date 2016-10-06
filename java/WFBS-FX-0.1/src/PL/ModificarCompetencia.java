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
import javafx.application.Application;
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
public class ModificarCompetencia {

    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private static CompetenciaO c0;
    static boolean vb = false;

    public void display(int idComp) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Modificar Competencia");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Modificar Competencia");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 9, 3, 1);

        c0 = compCtl.getCompetenciaById(idComp);

        Label nombreLbl = new Label("Nombre:");
        grid.add(nombreLbl, 0, 4);
        TextField nombreTxt = new TextField(c0.getNombre());
        nombreTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(nombreTxt, 1, 4);

        Label siglaLbl = new Label("Sigla:");
        grid.add(siglaLbl, 0, 5);
        TextField siglaTxt = new TextField(c0.getSigla());
        siglaTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(siglaTxt, 1, 5);

        Label descLbl = new Label("Descripción:");
        grid.add(descLbl, 0, 6);
        TextField descTxt = new TextField(c0.getDescripcion());
        descTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(descTxt, 1, 6);

        Label lblNivelOptimo = new Label("Nivel Óptimo:");
        grid.add(lblNivelOptimo, 0, 7);
        TextField txtNivelOptimo = new TextField("" + c0.getNivelOptimo());
        txtNivelOptimo.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtNivelOptimo, 1, 7);

        Button btn = new Button("MODIFICAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 10, 1, 4);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (nombreTxt.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Nombre");
            } else if (siglaTxt.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Sigla");
            } else if (descTxt.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Descripción");
            } else if (!v.validarInteger(txtNivelOptimo.getText())) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Nivel Óptimo ingrese sólo números entre 0 y 5");
            } else {
                int no = Integer.parseInt(txtNivelOptimo.getText());
                if (no >= 0 && no <= 5) {
                    Date now = new Date();
                    vb = compCtl.addCompetenciaCTL(new CompetenciaO(nombreTxt.getText(), descTxt.getText(), siglaTxt.getText(), no, 1, now, null, null));
                    if (vb) {
                        /*nombreTxt.clear();
                        txtNivelOptimo.clear();
                        siglaTxt.clear();
                        descTxt.clear();
                        msj.setFill(Color.GREEN);
                        msj.setText("Competencia Creada Exitosamente");*/
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(window);
                        alert.setTitle("Competencia Modificada");
                        alert.setHeaderText(null);
                        alert.setContentText("Competencia Modificada Exitosamente");
                        alert.showAndWait();
                        window.close();
                    } else {
                        /*msj.setFill(Color.FIREBRICK);
                        msj.setText("Error Al Crear Competencia");*/
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.initOwner(window);
                        alert.setTitle("ERROR - Modificar Competencia");
                        alert.setHeaderText(null);
                        alert.setContentText("ERROR al Modificar Competencia");
                        alert.showAndWait();
                        window.close();
                    }
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Nivel Óptimo ingrese sólo números entre 0 y 5");
                }
            }
        });

        Scene display = new Scene(grid, 420, 400);
        window.setScene(display);
        display.getStylesheets().add(ModificarCompetencia.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
