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
import O.UsuarioAreaO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class CrearAreaCompetencia {

    private final AreaCompetenciaCTL areaCompetenciaCtl = new AreaCompetenciaCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
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

        Label lblUser = new Label("Área ID: " + areaId);
        grid.add(lblUser, 0, 1, 3, 1);

        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 4, 2, 1);

        Label lblListaDisponible = new Label("Competencias disponibles:");
        lblListaDisponible.getStyleClass().add("subtitle");
        grid.add(lblListaDisponible, 0, 2);
        ListView listaDisponible = new ListView();
        listaDisponible.setItems(areaCompetenciaCtl.getCompetenciasDisponiblesByAreaFX(areaId));
        grid.add(listaDisponible, 0, 3);
        listaDisponible.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                msj.setText("");
            }
        });
        Label lblSeleccionadas = new Label("Competencias del Área:");
        lblSeleccionadas.getStyleClass().add("subtitle");
        grid.add(lblSeleccionadas, 3, 2);
        ListView listSeleccionadas = new ListView();
        listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByAreaIdFX(areaId));
        grid.add(listSeleccionadas, 3, 3);
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
        grid.add(vbButtons, 2, 3);

        btnAgregar.setOnAction(e -> {
            if (listaDisponible.getSelectionModel().getSelectedItem() != null) {
                String select = listaDisponible.getSelectionModel().getSelectedItem().toString();
                CompetenciaO compO = compCtl.getCompetenciaByNombre(select);
                if (areaCompetenciaCtl.addAreaCompetenciaCTL(new AreaCompetenciaO(areaId, compO.getId(), compO.getNombre()))) {
                    msj.setText("Competencia Agregada Exitosamente");
                    listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByAreaIdFX(areaId));
                    listaDisponible.setItems(areaCompetenciaCtl.getCompetenciasDisponiblesByAreaFX(areaId));
                    listSeleccionadas.getSelectionModel().clearSelection();
                    listaDisponible.getSelectionModel().clearSelection();
                } else {
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setText("Seleccione una Competencia");
            }
        });

        btnQuitar.setOnAction(e -> {
            if (listSeleccionadas.getSelectionModel().getSelectedItem() != null) {
                String select = listSeleccionadas.getSelectionModel().getSelectedItem().toString();
                CompetenciaO compO = compCtl.getCompetenciaByNombre(select);
                if (areaCompetenciaCtl.removeAreaCompCTL(areaId, compO.getId())) {
                    msj.setText("Competencia Eliminada Exitosamente");
                    listSeleccionadas.setItems(areaCompetenciaCtl.getAreaCompetenciasByAreaIdFX(areaId));
                    listaDisponible.setItems(areaCompetenciaCtl.getCompetenciasDisponiblesByAreaFX(areaId));
                    listSeleccionadas.getSelectionModel().clearSelection();
                    listaDisponible.getSelectionModel().clearSelection();
                } else {
                    msj.setText("Ha ocurrido un error");
                }
            } else {
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
