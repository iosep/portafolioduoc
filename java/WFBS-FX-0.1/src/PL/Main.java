/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import DAL.CargarDatos;
import FN.Cifrar;
import FN.Validar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SEC - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label subtitle = new Label("Sistema de Encuestas por Competencia");
        subtitle.setId("subtitle");
        grid.add(subtitle, 0, 1, 4, 2);

        Label userName = new Label("RUT:");
        grid.add(userName, 0, 4);

        TextField txtRun = new TextField();
        grid.add(txtRun, 1, 4);

        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 5);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 5);

        //grid.setGridLinesVisible(true);
        Button btn = new Button("Ingresar");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 9);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 11);

        btn.setOnAction((ActionEvent e) -> {
            actiontarget.setId("actiontarget");
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                String rut = txtRun.getText();
                rut = rut.toUpperCase();
                rut = rut.replace(".", "");
                rut = rut.replace("-", "");
                rut = rut.substring(0, rut.length() - 1);
                Cifrar c = new Cifrar();
                int irut = Integer.parseInt(rut);
                if (c.validatePassword(irut, pwBox.getText())) {
                    actiontarget.setText("Login Correcto");
                    Admin a = new Admin();
                    a.start(primaryStage);
                } else {
                    actiontarget.setText("Login Incorrecto");
                }
            } else {
                actiontarget.setText("RUT No Válido");
            }
        });

        Scene main = new Scene(grid, 800, 600);
        primaryStage.setScene(main);
        main.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
