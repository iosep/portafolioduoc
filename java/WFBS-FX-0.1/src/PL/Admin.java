/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Admin extends Application {

    @Override
    public void start(Stage primaryStage) {

        HBox topMenu = new HBox();
        Button btnUsuario = new Button("Mantenedor Usuario");
        Button btnArea = new Button("Mantenedor Area");
        topMenu.getChildren().addAll(btnUsuario, btnArea);
        topMenu.getStyleClass().add("hbox");

        BorderPane bp = new BorderPane();
        bp.setTop(topMenu);

        Scene admin = new Scene(bp, 800, 600);

        primaryStage.setTitle("SEC - Mantenedores");
        primaryStage.setScene(admin);
        admin.getStylesheets().add(Admin.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }

}
