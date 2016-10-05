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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    static boolean vb = false;

    public boolean display(int idArea) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Usuario a Área");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC - Asignar");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 4, 1);

        auxArea = areaCtl.getAreaById(idArea);
        Label lblUser = new Label("Área: " + auxArea.getNombre());
        lblUser.getStyleClass().add("subtitle");
        grid.add(lblUser, 0, 1, 3, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 5, 2, 1);

        Label lblDisponible = new Label("Usuarios disponibles:");
//        lblDisponible.getStyleClass().add("subtitle");
        grid.add(lblDisponible, 0, 3);
        ListView listDisponible = new ListView();
        listDisponible.setItems(usuarioAreaCtl.getUsuariosDisponiblesByAreaFX(idArea));
        listDisponible.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
            @Override
            protected void updateItem(UsuarioAreaO item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                    setText(auxUser.getRut());
                }
            }
        });
        grid.add(listDisponible, 0, 4);
        listDisponible.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        Label lblSeleccionada = new Label("Usuarios del Área:");
//        lblSeleccionada.getStyleClass().add("subtitle");
        grid.add(lblSeleccionada, 3, 3);
        ListView listSeleccion = new ListView();
        listSeleccion.setItems(usuarioAreaCtl.getUsuarioAreasByAreaIdFX(idArea));
        listSeleccion.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
            @Override
            protected void updateItem(UsuarioAreaO item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                    setText(auxUser.getRut());
                }
            }
        });
        grid.add(listSeleccion, 3, 4);
        listSeleccion.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });

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
                    msj.setText("Usuario Agregado Exitosamente");
                    listSeleccion.setItems(usuarioAreaCtl.getUsuarioAreasByAreaIdFX(idArea));
                    listSeleccion.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                                setText(auxUser.getRut());
                            }
                        }
                    });
                    listDisponible.setItems(usuarioAreaCtl.getUsuariosDisponiblesByAreaFX(idArea));
                    listDisponible.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                                setText(auxUser.getRut());
                            }
                        }
                    });
                    listSeleccion.getSelectionModel().clearSelection();
                    listDisponible.getSelectionModel().clearSelection();
                } else {
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setText("Seleccione un Usuario");
            }
        });

        btnQuitar.setOnAction(e -> {
            if (listSeleccion.getSelectionModel().getSelectedItem() != null) {
                auxUserArea = (UsuarioAreaO) listSeleccion.getSelectionModel().getSelectedItem();
                if (usuarioAreaCtl.removeUserAreaCTL(auxUserArea.getUsuario_id(), auxUserArea.getArea_id())) {
                    msj.setText("Usuario Eliminado Exitosamente");
                    listSeleccion.setItems(usuarioAreaCtl.getUsuarioAreasByAreaIdFX(idArea));
                    listSeleccion.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                                setText(auxUser.getRut());
                            }
                        }
                    });
                    listDisponible.setItems(usuarioAreaCtl.getUsuariosDisponiblesByAreaFX(idArea));
                    listDisponible.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
                        @Override
                        protected void updateItem(UsuarioAreaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxUser = userCtl.getUsuarioById(item.getUsuario_id());
                                setText(auxUser.getRut());
                            }
                        }
                    });
                    listSeleccion.getSelectionModel().clearSelection();
                    listDisponible.getSelectionModel().clearSelection();
                } else {
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setText("Seleccione un Usuario");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearAreaUsuario.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

        return vb;
    }
}
