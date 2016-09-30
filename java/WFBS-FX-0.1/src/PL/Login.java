/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.UsuarioCTL;
import DAL.AaaInitialLoad;
import FN.Cifrar;
import FN.Validar;
import O.UsuarioO;
import javafx.application.Application;
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

    private final UsuarioCTL userCtl = new UsuarioCTL();
    private static UsuarioO logUser;

    @Override
    public void start(Stage primaryStage) {

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
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 9);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 11);

        btn.setOnAction(e -> {
            actiontarget.getStyleClass().add("action");
            Validar v = new Validar();
            if (v.validarRut(txtRun.getText())) {
                Cifrar c = new Cifrar();
                if (c.validatePassword(txtRun.getText(), pwBox.getText())) {
                    logUser = userCtl.getUsuarioByRut(txtRun.getText());
                    switch (logUser.getRol()) {
                        case 1:
                            Admin a = new Admin() {
                                @Override
                                public void start(Stage primaryStage) throws Exception {
                                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                }
                            };
                            a.start(primaryStage, logUser.getRut());
                            break;
                        case 2:
                            System.out.println("Jefa logeada");
                            break;
                        case 3:
                            System.out.println("Funcionaria logeada");
                            break;
                        default:
                            break;
                    }
                } else {
                    actiontarget.setText("Login Incorrecto");
                }
            } else {
                actiontarget.setText("RUT No Válido");
            }
        });

        Scene display = new Scene(grid, 1040, 640);
        primaryStage.setScene(display);
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
