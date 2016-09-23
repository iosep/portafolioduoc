/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

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
public class Login extends Application {

    private Scene login, admin, jefe, funcionario;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Investigadores Privados WFBS");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Bienvenido");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("RUN:");
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

        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        Label lb = new Label("otra pagina");
        grid2.add(lb, 0, 0);
        
        btn.setOnAction((ActionEvent e) -> {
            actiontarget.setId("actiontarget");
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                actiontarget.setText("RUN valido");
                admin = new Scene(grid2);
                primaryStage.setScene(admin);
                admin.getStylesheets().add(Login.class.getResource("Style.css").toExternalForm());
                primaryStage.setMaximized(true);
            } else {
                actiontarget.setText("RUN no valido");
            }
        });

        login = new Scene(grid);
        primaryStage.setScene(login);
        login.getStylesheets().add(Login.class.getResource("Style.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
