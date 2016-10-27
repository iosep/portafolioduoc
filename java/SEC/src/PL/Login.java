/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.UsuarioCTL;
import DAL.VariablesDAL;
import FN.Formato;
import FN.Validar;
import O.UsuarioO;
import REST.Conexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Login extends Application {

    private final UsuarioCTL userCtl = new UsuarioCTL();
    private static boolean load = true;
    HBox hbTop = new HBox();
    Label l1 = new Label("IP:");
    TextField tf1 = new TextField();
    Label l2 = new Label("Port:");
    TextField tf2 = new TextField();
    BorderPane bp = new BorderPane();
    Button bServer = new Button("Set Server");
    Stage primaryStage;

    public Login() {
        if (load) {
            load = false;
            hbTop.getChildren().addAll(l1, tf1, l2, tf2, bServer);
            hbTop.getStyleClass().add("vbox");
            bp.setTop(hbTop);
            bServer.setOnAction(v -> {
                Validar va = new Validar();
                if (va.validateIpv4(tf1.getText()) && va.validatePort(tf2.getText())) {
                    Conexion.setIp(tf1.getText());
                    Conexion.setPort(tf2.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Set Server");
                    alert.setHeaderText(null);
                    alert.setContentText("Set Server Exitoso");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(primaryStage);
                    alert.setTitle("ERROR Set Server");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR IP o Port No Válido");
                    alert.showAndWait();
                }
            });
        } else {
            bp.getChildren().clear();
        }
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        primaryStage.setTitle("SEC - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        bp.setCenter(grid);

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
                try {
                    String login = userCtl.logInCtl(Formato.formatoRut(txtRun.getText()), pwBox.getText());
                    if (login.equals("login exitoso")) {
                        UsuarioO user = userCtl.getUsuarioById(VariablesDAL.getIdUsuario());
                        switch (user.getRol_nombre()) {
                            case "ADMINISTRADOR":
                                Admin a = new Admin();
                                a.display(user.getRut());
                                primaryStage.close();
                                break;
                            case "JEFE":
                                Jefe j = new Jefe();
                                j.start(user.getRut());
                                primaryStage.close();
                                break;
                            case "FUNCIONARIO":
                                Funcionario f = new Funcionario();
                                f.start(user.getRut());
                                primaryStage.close();
                                break;
                            default:
                                break;
                        }
                    } else {
                        msj.setText(login);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                msj.setText("RUT No Válido");
            }
        });

        Scene display = new Scene(bp, 700, 500);
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
