/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import FN.Validar;
import O.AreaO;
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
public class ModificarArea {

    private final AreaCTL areaCtl = new AreaCTL();
    private AreaO a0;

    public void display(int idArea) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Modificar Área");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Modificar Área");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 9, 2, 1);

        a0 = areaCtl.getAreaById(idArea);

        Label nombreLbl = new Label("Nombre:");
        grid.add(nombreLbl, 0, 5);
        TextField nombreTxt = new TextField(a0.getNombre());
        nombreTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(nombreTxt, 1, 5);

        Label siglaLbl = new Label("Sigla:");
        grid.add(siglaLbl, 0, 6);
        TextField siglaTxt = new TextField(a0.getSigla());
        siglaTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(siglaTxt, 1, 6);

        Label descLbl = new Label("Descripción:");
        grid.add(descLbl, 0, 7);
        TextField descTxt = new TextField(a0.getDescripcion());
        descTxt.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(descTxt, 1, 7);

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
            } else {
                AreaO a1 = new AreaO(nombreTxt.getText().trim(), siglaTxt.getText().trim(), descTxt.getText().trim());
                a1.setId(idArea);
                if (areaCtl.modificarArea(a1)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Área Modificada");
                    alert.setHeaderText(null);
                    alert.setContentText("Área Modificada Exitosamente");
                    alert.showAndWait();
                    window.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("ERROR - Modificar Área");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR al Modificar Área");
                    alert.showAndWait();
                    window.close();
                }
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(ModificarArea.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
