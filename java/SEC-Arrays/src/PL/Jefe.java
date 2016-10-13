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
import PL.Admin;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        BorderPane bpJefe = new BorderPane(vbJefe);
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
