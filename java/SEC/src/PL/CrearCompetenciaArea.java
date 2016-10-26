/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.AreaCompetenciaCTL;
import CTL.CompetenciaCTL;
import O.AreaCompetenciaO;
import O.AreaO;
import O.CompetenciaO;
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
public class CrearCompetenciaArea {

    private final AreaCompetenciaCTL areaCompetenciaCtl = new AreaCompetenciaCTL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private static CompetenciaO auxComp;
    private static AreaO auxArea;
    private static AreaCompetenciaO auxAreaComp;

    public void display(int compId) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Área a Competencia");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setPadding(new Insets(0, 25, 5, 25));

        Text scenetitle = new Text("SEC - Asignar");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 4, 1);

        auxComp = compCtl.getCompetenciaById(compId);
        Label lblUser = new Label("Competencia: " + auxComp.getNombre());
        lblUser.getStyleClass().add("subtitle");
        grid.add(lblUser, 0, 1, 3, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 5, 2, 1);

        Label lblListaDisponible = new Label("Áreas disponibles:");
        grid.add(lblListaDisponible, 0, 3);
        ListView listaDisponible = new ListView();
        listaDisponible.setItems(areaCompetenciaCtl.getAreasDisponiblesByCompetenciaFX(compId));
        listaDisponible.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
            @Override
            protected void updateItem(AreaCompetenciaO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getAreaNombre());
                    /*
                    auxArea = areaCtl.getAreaById(item.getArea_id());
                    setText(auxArea.getNombre());*/
                }
            }
        });
        grid.add(listaDisponible, 0, 4);
        listaDisponible.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        Label lblSeleccionadas = new Label("Áreas de la Competencia:");
        grid.add(lblSeleccionadas, 3, 3);
        ListView listSeleccionadas = new ListView();
        listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByCompetenciaIdFX(compId));
        listSeleccionadas.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
            @Override
            protected void updateItem(AreaCompetenciaO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getAreaNombre());
                }
            }
        });
        grid.add(listSeleccionadas, 3, 4);
        listSeleccionadas.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
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
            if (listaDisponible.getSelectionModel().getSelectedItem() != null) {
                auxAreaComp = (AreaCompetenciaO) listaDisponible.getSelectionModel().getSelectedItem();
                if (areaCompetenciaCtl.addAreaCompetenciaCTL(auxAreaComp)) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Área Agregada Exitosamente");
                    listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByCompetenciaIdFX(compId));
                    listSeleccionadas.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getAreaNombre());
                            }
                        }
                    });
                    listaDisponible.setItems(areaCompetenciaCtl.getAreasDisponiblesByCompetenciaFX(compId));
                    listaDisponible.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getAreaNombre());
                            }
                        }
                    });
                    listSeleccionadas.getSelectionModel().clearSelection();
                    listaDisponible.getSelectionModel().clearSelection();
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
            if (listSeleccionadas.getSelectionModel().getSelectedItem() != null) {
                auxAreaComp = (AreaCompetenciaO) listSeleccionadas.getSelectionModel().getSelectedItem();
                if (areaCompetenciaCtl.removeAreaCompCTL(auxAreaComp.getArea_id(), compId)) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Área Eliminada Exitosamente");
                    listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByCompetenciaIdFX(compId));
                    listSeleccionadas.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getAreaNombre());
                            }
                        }
                    });
                    listaDisponible.setItems(areaCompetenciaCtl.getAreasDisponiblesByCompetenciaFX(compId));
                    listaDisponible.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getAreaNombre());
                            }
                        }
                    });
                    listSeleccionadas.getSelectionModel().clearSelection();
                    listaDisponible.getSelectionModel().clearSelection();
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Seleccione un Área");
            }
        });

        Scene display = new Scene(grid, 650, 500);
        window.setScene(display);
        display.getStylesheets().add(CrearCompetenciaArea.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
