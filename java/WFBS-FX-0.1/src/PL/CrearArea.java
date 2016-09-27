/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import FN.Validar;
import O.AreaO;
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
public class CrearArea {

    private final AreaCTL actl = new AreaCTL();
    static boolean vb = false;

    public boolean display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Crear Nueva Area");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Crear Nueva Area");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        Label nombreLbl = new Label("Nombre:");
        grid.add(nombreLbl, 0, 5);
        TextField nombreTxt = new TextField();
        grid.add(nombreTxt, 1, 5);

        Label siglaLbl = new Label("Sigla:");
        grid.add(siglaLbl, 0, 6);
        TextField siglaTxt = new TextField();
        grid.add(siglaTxt, 1, 6);

        Button btn = new Button("CREAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 8);

        final Text msj = new Text();
        grid.add(msj, 0, 9, 2, 1);

        btn.setOnAction(e -> {
            msj.getStyleClass().add("action");
            Validar v = new Validar();
            if (nombreTxt.getText().isEmpty()) {
                msj.setText("Ingrese Nombre");
            } else if (siglaTxt.getText().isEmpty()) {
                msj.setText("Ingrese Sigla");
            } else {
                Date now = new Date();
                vb = actl.addAreaCTL(new AreaO(nombreTxt.getText(), siglaTxt.getText(), 1, now, null, null));
                if (vb) {
                    nombreTxt.clear();
                    siglaTxt.clear();
                    msj.setText("Area Creada Exitosamente");
                } else {
                    msj.setText("Error Al Crear Area");
                }
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearArea.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

        return vb;
    }
}
