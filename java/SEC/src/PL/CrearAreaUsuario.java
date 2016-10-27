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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class CrearAreaUsuario {

    private final UsuarioAreaCTL usuarioAreaCtl = new UsuarioAreaCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private final UsuarioCTL userCtl = new UsuarioCTL();
    private static AreaO auxArea;
    private static UsuarioO auxUser;
    private static UsuarioAreaO auxUserArea;

    public void display(int idArea) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Usuario a Área");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(7);
        grid.setVgap(7);
        grid.setPadding(new Insets(0, 20, 20, 20));

        Text scenetitle = new Text("SEC - Asignar");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 4, 1);

        auxArea = areaCtl.getAreaById(idArea);
        Label lblUser = new Label("Área: " + auxArea.getNombre());
        lblUser.getStyleClass().add("subtitle");
        grid.add(lblUser, 0, 1, 3, 1);

        Label lblDisponible = new Label("Usuarios disponibles:");
//        lblDisponible.getStyleClass().add("subtitle");
        grid.add(lblDisponible, 0, 3);
        ListView listDisponible = new ListView();
        listDisponible.setItems(usuarioAreaCtl.getFuncionariosDisponiblesByAreaFX(idArea));
        listDisponible.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
            @Override
            protected void updateItem(UsuarioAreaO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getUsuarioRut());
                    /*
                    auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                    setText(auxUser.getNombre() + " " + auxUser.getApellido() + " " + auxUser.getRut());*/
                }
            }
        });
        grid.add(listDisponible, 0, 4);

        Label lblSeleccionada = new Label("Usuarios del Área:");
//        lblSeleccionada.getStyleClass().add("subtitle");
        grid.add(lblSeleccionada, 3, 3);
        ListView listSeleccion = new ListView();
        listSeleccion.setItems(usuarioAreaCtl.getUsuarioAreasByAreaIdFX(idArea));
        listSeleccion.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
            @Override
            protected void updateItem(UsuarioAreaO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getUsuarioRut());
                }
            }
        });
        grid.add(listSeleccion, 3, 4);

        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        Button btnAgregar = new Button("Agregar");
        Button btnQuitar = new Button("Quitar");
        vbButtons.getStyleClass().add("between");
        vbButtons.getChildren().addAll(btnAgregar, btnQuitar);
        grid.add(vbButtons, 2, 4);

        btnAgregar.setOnAction(e -> {
            if (listDisponible.getSelectionModel().getSelectedItem() != null) {
                auxUserArea = (UsuarioAreaO) listDisponible.getSelectionModel().getSelectedItem();
                if (usuarioAreaCtl.addUsuarioAreaCTL(auxUserArea)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Usuario a Área");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario Agregado");
                    alert.showAndWait();
                    listSeleccion.setItems(usuarioAreaCtl.getUsuarioAreasByAreaIdFX(idArea));
                    listSeleccion.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getUsuarioRut());
                            }
                        }
                    });
                    listDisponible.setItems(usuarioAreaCtl.getFuncionariosDisponiblesByAreaFX(idArea));
                    listDisponible.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getUsuarioRut());
                            }
                        }
                    });
                    listSeleccion.getSelectionModel().clearSelection();
                    listDisponible.getSelectionModel().clearSelection();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("Usuario a Área");
                    alert.setHeaderText(null);
                    alert.setContentText("Error!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(window);
                alert.setTitle("Usuario a Área");
                alert.setHeaderText(null);
                alert.setContentText("Seleccione un Usuario");
                alert.showAndWait();
            }
        });

        btnQuitar.setOnAction(e -> {
            if (listSeleccion.getSelectionModel().getSelectedItem() != null) {
                auxUserArea = (UsuarioAreaO) listSeleccion.getSelectionModel().getSelectedItem();
                if (usuarioAreaCtl.removeUserAreaCTL(auxUserArea.getUsuario_id(), auxUserArea.getArea_id())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Usuario a Área");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario Eliminado");
                    alert.showAndWait();
                    listSeleccion.setItems(usuarioAreaCtl.getUsuarioAreasByAreaIdFX(idArea));
                    listSeleccion.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getUsuarioRut());
                            }
                        }
                    });
                    listDisponible.setItems(usuarioAreaCtl.getFuncionariosDisponiblesByAreaFX(idArea));
                    listDisponible.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getUsuarioRut());
                            }
                        }
                    });
                    listSeleccion.getSelectionModel().clearSelection();
                    listDisponible.getSelectionModel().clearSelection();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("Usuario a Área");
                    alert.setHeaderText(null);
                    alert.setContentText("Error!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(window);
                alert.setTitle("Usuario a Área");
                alert.setHeaderText(null);
                alert.setContentText("Seleccione un Usuario");
                alert.showAndWait();
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearAreaUsuario.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
