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
import java.awt.PageAttributes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class CrearAreaCompetencia {

    private final AreaCompetenciaCTL areaCompetenciaCtl = new AreaCompetenciaCTL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private static CompetenciaO auxComp;
    private static AreaO auxArea;
    private static AreaCompetenciaO auxAreaComp;
    static boolean vb = false;

    public boolean display(int areaId) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Competencia a Área");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC - Asignar");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 4, 1);

        auxArea = areaCtl.getAreaById(areaId);
        Label lblUser = new Label("Área: " + auxArea.getNombre());
        lblUser.getStyleClass().add("subtitle");
        grid.add(lblUser, 0, 1, 3, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 5, 2, 1);

        Label lblListaDisponible = new Label("Competencias disponibles:");
        //lblListaDisponible.getStyleClass().add("subtitle");
        grid.add(lblListaDisponible, 0, 3);
        ListView listaDisponible = new ListView();
        listaDisponible.setItems(areaCompetenciaCtl.getCompetenciasDisponiblesByAreaFX(areaId));
        listaDisponible.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
            @Override
            protected void updateItem(AreaCompetenciaO item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    auxComp = compCtl.getCompetenciaById(item.getCompetencia_id());
                    setText(auxComp.getNombre());
                }
            }
        });
        grid.add(listaDisponible, 0, 4);
        listaDisponible.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        Label lblSeleccionadas = new Label("Competencias del Área:");
        //lblSeleccionadas.getStyleClass().add("subtitle");
        grid.add(lblSeleccionadas, 3, 3);
        ListView listSeleccionadas = new ListView();
        listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByAreaIdFX(areaId));
        listSeleccionadas.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
            @Override
            protected void updateItem(AreaCompetenciaO item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    auxComp = compCtl.getCompetenciaById(item.getCompetencia_id());
                    setText(auxComp.getNombre());
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
                    msj.setText("Competencia Agregada Exitosamente");
                    listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByAreaIdFX(areaId));
                    listSeleccionadas.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxComp = compCtl.getCompetenciaById(item.getCompetencia_id());
                                setText(auxComp.getNombre());
                            }
                        }
                    });
                    listaDisponible.setItems(areaCompetenciaCtl.getCompetenciasDisponiblesByAreaFX(areaId));
                    listaDisponible.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxComp = compCtl.getCompetenciaById(item.getCompetencia_id());
                                setText(auxComp.getNombre());
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
                msj.setText("Seleccione una Competencia");
            }
        });

        btnQuitar.setOnAction(e -> {
            if (listSeleccionadas.getSelectionModel().getSelectedItem() != null) {
                auxAreaComp = (AreaCompetenciaO) listSeleccionadas.getSelectionModel().getSelectedItem();
                if (areaCompetenciaCtl.removeAreaCompCTL(areaId, auxAreaComp.getCompetencia_id())) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Competencia Eliminada Exitosamente");
                    listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByAreaIdFX(areaId));
                    listSeleccionadas.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxComp = compCtl.getCompetenciaById(item.getCompetencia_id());
                                setText(auxComp.getNombre());
                            }
                        }
                    });
                    listaDisponible.setItems(areaCompetenciaCtl.getCompetenciasDisponiblesByAreaFX(areaId));
                    listaDisponible.setCellFactory(param -> new ListCell<AreaCompetenciaO>() {
                        @Override
                        protected void updateItem(AreaCompetenciaO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                auxComp = compCtl.getCompetenciaById(item.getCompetencia_id());
                                setText(auxComp.getNombre());
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
                msj.setText("Seleccione una Competencia");
            }
        });

        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearAreaCompetencia.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

        return vb;
    }
}
