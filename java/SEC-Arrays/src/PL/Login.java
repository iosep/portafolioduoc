/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.RolCTL;
import CTL.UsuarioCTL;
import DAL.AaaInitialLoad;
import FN.Cifrar;
import FN.Validar;
import O.RolO;
import O.UsuarioO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Login extends Application {

    private final UsuarioCTL userCtl = new UsuarioCTL();
    private final RolCTL rolCtl = new RolCTL();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
//carga de datos de prueba
        AaaInitialLoad aaa = new AaaInitialLoad();
        aaa.cargar();

        primaryStage.setTitle("SEC - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Sistema de Encuestas por Competencia");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 4, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 1, 11);

        Label userName = new Label("RUT:");
        grid.add(userName, 0, 4);
        TextField txtRun = new TextField();
        txtRun.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(txtRun, 1, 4);

        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 5);
        PasswordField pwBox = new PasswordField();
        pwBox.textProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        grid.add(pwBox, 1, 5);

        //grid.setGridLinesVisible(true);
        Button btn = new Button("Ingresar");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 9);

        btn.setOnAction(e -> {
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                Cifrar c = new Cifrar();
                if (c.validatePassword(txtRun.getText(), pwBox.getText())) {
                    UsuarioO logUser = userCtl.getUsuarioByRut(txtRun.getText());
                    RolO logRol = rolCtl.getRolById(logUser.getRol());
                    switch (logRol.getNombre()) {
                        case "Administrador":
                            Admin a = new Admin();
                            a.display(logUser.getRut());
                            primaryStage.close();
                            break;
                        case "Jefe":
                            System.out.println("Jefe logeado");
                            break;
                        case "Funcionario":
                            System.out.println("Funcionario logeado");
                            break;
                        default:
                            break;
                    }
                } else {
                    msj.setText("Login Incorrecto");
                }
            } else {
                msj.setText("RUT No Válido");
            }
        });

        Scene display = new Scene(grid, 800, 550);
        primaryStage.setScene(display);
        //primaryStage.setMaximized(!primaryStage.isMaximized());
        display.getStylesheets().add(Login.class.getResource("Style.css").toExternalForm());
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
