/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.CompetenciaCTL;
import CTL.CompetenciaNivelCTL;
import CTL.NivelCTL;
import O.CompetenciaNivelO;
import O.CompetenciaO;
import O.NivelO;
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
public class CrearCompetenciaNivel {

    private final CompetenciaNivelCTL compNivelCtl = new CompetenciaNivelCTL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final NivelCTL nivelCtl = new NivelCTL();
    private static CompetenciaO auxComp;
    private static NivelO auxNivel;
    private static CompetenciaNivelO auxCompNivel;

    public void display(int compId) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Nivel a Competencia");

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

        Label lblListaDisponible = new Label("Niveles disponibles:");
        grid.add(lblListaDisponible, 0, 3);
        ListView listaDisponible = new ListView();
        listaDisponible.setItems(compNivelCtl.getNivelesDisponiblesByCompetenciaFX(compId));
        listaDisponible.setCellFactory(param -> new ListCell<CompetenciaNivelO>() {
            @Override
            protected void updateItem(CompetenciaNivelO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNivelNombre());
                    /*
                    auxNivel = nivelCtl.getNivelById(item.getNivel_id());
                    setText(auxNivel.getNombre());*/
                }
            }
        });
        grid.add(listaDisponible, 0, 4);
        listaDisponible.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        Label lblSeleccionadas = new Label("Niveles de la Competencia:");
        grid.add(lblSeleccionadas, 3, 3);
        ListView listSeleccionadas = new ListView();
        listSeleccionadas.setItems(compNivelCtl.getCompetenciaNivelesByCompetenciaIdFX(compId));
        listSeleccionadas.setCellFactory(param -> new ListCell<CompetenciaNivelO>() {
            @Override
            protected void updateItem(CompetenciaNivelO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNivelNombre());
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
                auxCompNivel = (CompetenciaNivelO) listaDisponible.getSelectionModel().getSelectedItem();
                if (compNivelCtl.addCompetenciaNivelCTL(auxCompNivel)) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Nivel Agregado Exitosamente");
                    listSeleccionadas.setItems(compNivelCtl.getCompetenciaNivelesByCompetenciaIdFX(compId));
                    listSeleccionadas.setCellFactory(param -> new ListCell<CompetenciaNivelO>() {
                        @Override
                        protected void updateItem(CompetenciaNivelO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getNivelNombre());
                            }
                        }
                    });
                    listaDisponible.setItems(compNivelCtl.getNivelesDisponiblesByCompetenciaFX(compId));
                    listaDisponible.setCellFactory(param -> new ListCell<CompetenciaNivelO>() {
                        @Override
                        protected void updateItem(CompetenciaNivelO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getNivelNombre());
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
                msj.setText("Seleccione un Nivel");
            }
        });

        btnQuitar.setOnAction(e -> {
            if (listSeleccionadas.getSelectionModel().getSelectedItem() != null) {
                auxCompNivel = (CompetenciaNivelO) listSeleccionadas.getSelectionModel().getSelectedItem();
                if (compNivelCtl.removeAreaCompCTL(compId, auxCompNivel.getNivel_id())) {
                    msj.setFill(Color.GREEN);
                    msj.setText("Nivel Eliminado Exitosamente");
                    listSeleccionadas.setItems(compNivelCtl.getCompetenciaNivelesByCompetenciaIdFX(compId));
                    listSeleccionadas.setCellFactory(param -> new ListCell<CompetenciaNivelO>() {
                        @Override
                        protected void updateItem(CompetenciaNivelO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getNivelNombre());
                            }
                        }
                    });
                    listaDisponible.setItems(compNivelCtl.getNivelesDisponiblesByCompetenciaFX(compId));
                    listaDisponible.setCellFactory(param -> new ListCell<CompetenciaNivelO>() {
                        @Override
                        protected void updateItem(CompetenciaNivelO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.getNivelNombre());
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
                msj.setText("Seleccione un Nivel");
            }
        });

        Scene display = new Scene(grid, 650, 500);
        window.setScene(display);
        display.getStylesheets().add(CrearCompetenciaNivel.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
