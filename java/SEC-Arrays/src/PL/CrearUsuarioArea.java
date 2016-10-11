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
public class CrearUsuarioArea {

    private final UsuarioAreaCTL usuarioAreaCtl = new UsuarioAreaCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private final UsuarioCTL userCtl = new UsuarioCTL();
    private static AreaO auxArea;
    private static UsuarioAreaO auxUserArea;

    public void display(String userRut) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Área a Usuario");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC - Asignar");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 4, 1);

        Label lblUser = new Label("Usuario RUT: " + userRut);
        lblUser.getStyleClass().add("subtitle");
        grid.add(lblUser, 0, 1, 3, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 5, 2, 1);

        UsuarioO userO = userCtl.getUsuarioByRut(userRut);
        Label lblListaAreas = new Label("Áreas disponibles:");
//        lblListaAreas.getStyleClass().add("subtitle");
        grid.add(lblListaAreas, 0, 3);
        ListView listaAreas = new ListView();
        listaAreas.setItems(usuarioAreaCtl.getAreasDisponiblesByUserFX(userO.getId()));
        listaAreas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
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
        grid.add(listaAreas, 0, 4);
        listaAreas.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        Label lblAreasSeleccionadas = new Label("Áreas del Usuario:");
//        lblAreasSeleccionadas.getStyleClass().add("subtitle");
        grid.add(lblAreasSeleccionadas, 3, 3);
        ListView areasSeleccionadas = new ListView();
        areasSeleccionadas.setItems(usuarioAreaCtl.getUsuarioAreasByUserIdFX(userO.getId()));
        areasSeleccionadas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
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
        grid.add(areasSeleccionadas, 3, 4);
        areasSeleccionadas.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
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
            if (listaAreas.getSelectionModel().getSelectedItem() != null) {
                auxUserArea = (UsuarioAreaO) listaAreas.getSelectionModel().getSelectedItem();
                if (usuarioAreaCtl.addUsuarioAreaCTL(auxUserArea)) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Área Agregada Exitosamente");
                    areasSeleccionadas.setItems(usuarioAreaCtl.getUsuarioAreasByUserIdFX(userO.getId()));
                    areasSeleccionadas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
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
                    listaAreas.setItems(usuarioAreaCtl.getAreasDisponiblesByUserFX(userO.getId()));
                    listaAreas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
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
                    areasSeleccionadas.getSelectionModel().clearSelection();
                    listaAreas.getSelectionModel().clearSelection();
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Seleccione un Área");
            }
        });

        btnQuitar.setOnAction(e -> {
            if (areasSeleccionadas.getSelectionModel().getSelectedItem() != null) {
                auxUserArea = (UsuarioAreaO) areasSeleccionadas.getSelectionModel().getSelectedItem();
                if (usuarioAreaCtl.removeUserAreaCTL(auxUserArea.getUsuario_id(), auxUserArea.getArea_id())) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Área Eliminada Exitosamente");
                    areasSeleccionadas.setItems(usuarioAreaCtl.getUsuarioAreasByUserIdFX(userO.getId()));
                    areasSeleccionadas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
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
                    listaAreas.setItems(usuarioAreaCtl.getAreasDisponiblesByUserFX(userO.getId()));
                    listaAreas.setCellFactory(param -> new ListCell<UsuarioAreaO>() {
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
                    areasSeleccionadas.getSelectionModel().clearSelection();
                    listaAreas.getSelectionModel().clearSelection();
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Seleccione un Área");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearUsuarioArea.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
