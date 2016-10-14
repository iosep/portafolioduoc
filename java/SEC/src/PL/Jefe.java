/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.UsuarioAreaCTL;
import CTL.UsuarioCTL;
import O.AreaO;
import O.UsuarioAreaO;
import O.UsuarioO;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Jefe {
    
    private final UsuarioCTL userCtl = new UsuarioCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private final UsuarioAreaCTL usArCtl = new UsuarioAreaCTL();
    private ListView lvAreas;
    private AreaO auxArea;
    private UsuarioO userJefe;
    Stage window = new Stage();
    
    public void start(String userRut) {
        Text titleMantenedores = new Text("SEC");
        titleMantenedores.getStyleClass().add("title");
        Label lblActiveUser = new Label("Usuario Activo:");
        Label lblLogRutUser = new Label("  " + userRut);
        Hyperlink hlCerrarSesion = new Hyperlink("Cerrar Sesión");
        hlCerrarSesion.setBorder(Border.EMPTY);
        hlCerrarSesion.setOnAction(e -> {
            Login login = new Login();
            login.start(window);
        });
        GridPane gridSesion = new GridPane();
        gridSesion.setAlignment(Pos.TOP_RIGHT);
        gridSesion.add(lblActiveUser, 0, 0);
        gridSesion.add(lblLogRutUser, 1, 0);
        gridSesion.add(hlCerrarSesion, 1, 1);
        gridSesion.getStyleClass().add("logout");
        HBox hbTopBox = new HBox();
        hbTopBox.getChildren().addAll(titleMantenedores, gridSesion);
        HBox.setHgrow(gridSesion, Priority.ALWAYS);
        
        userJefe = userCtl.getUsuarioByRut(userRut);
        lvAreas = new ListView(usArCtl.getUserAreaByRutJefe(userRut));
        lvAreas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
            @Override
            protected void updateItem(UsuarioAreaO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    auxArea = areaCtl.getAreaById(item.getArea_id());
                    setText(auxArea.getNombre());
                }
            }
        });
        lvAreas.setOrientation(Orientation.HORIZONTAL);
        lvAreas.setPrefHeight(50);
        
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        VBox vbJefe = new VBox();
        vbJefe.getChildren().addAll(lvAreas);
        BorderPane bpJefe = new BorderPane();
        bpJefe.setTop(hbTopBox);
        bpJefe.setCenter(vbJefe);
        Scene sJefe = new Scene(bpJefe);
        window.setTitle("SEC - Mantenedores");
        window.setScene(sJefe);
        this.window.setMaximized(!window.isMaximized());
        this.window.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKey);
        sJefe.getStylesheets().add(Admin.class.getResource("Style.css").toExternalForm());
        window.show();
        
    }
    
    private void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case F11:
                window.setFullScreen(!window.isFullScreen());
                break;
        }
    }
    
}
