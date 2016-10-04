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
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    static boolean vb = false;
    
    public boolean display(String userRut) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Asignar Área a Usuario");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 3, 1);
        
        Label lblUser = new Label("Usuario RUT: " + userRut);
        grid.add(lblUser, 0, 1, 3, 1);
        
        UsuarioO userO = userCtl.getUsuarioByRut(userRut);
        Label lblListaAreas = new Label("Áreas:");
        lblListaAreas.getStyleClass().add("subtitle");
        grid.add(lblListaAreas, 0, 2);
        ListView listaAreas = new ListView();
        listaAreas.setItems(usuarioAreaCtl.getAreasDisponiblesByUserFX(userO.getId()));
        grid.add(listaAreas, 0, 3);
        //listaAreas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //listaAreas.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {

        //});
        Label lblAreasSeleccionadas = new Label("Áreas del Usuario:");
        lblAreasSeleccionadas.getStyleClass().add("subtitle");
        grid.add(lblAreasSeleccionadas, 3, 2);
        ListView areasSeleccionadas = new ListView();
        areasSeleccionadas.setItems(usuarioAreaCtl.getUsuarioAreasFX());
        grid.add(areasSeleccionadas, 3, 3);
        
        Button btn = new Button("AGREGAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 3);
        
        final Text msj = new Text();
        msj.getStyleClass().add("action");
        grid.add(msj, 0, 4, 2, 1);
        
        btn.setOnAction(e -> {
            if (listaAreas.getSelectionModel().getSelectedItem() != null) {
                String areaSelec = listaAreas.getSelectionModel().getSelectedItem().toString();
                AreaO areaO = areaCtl.getAreaByNombre(areaSelec);
                
                if (usuarioAreaCtl.addUsuarioAreaCTL((new UsuarioAreaO(userO.getId(), areaO.getId(), areaO.getNombre())))) {
                    msj.setText("Área Agregada Exitosamente");
                    areasSeleccionadas.setItems(usuarioAreaCtl.getUsuarioAreasFX());
                    listaAreas.setItems(usuarioAreaCtl.getAreasDisponiblesByUserFX(userO.getId()));
                } else {
                    msj.setText("Ha ocurrido un error");
                }
            } else {
                msj.setText("Seleccione un Área");
            }
        });
        
        Scene display = new Scene(grid);
        window.setScene(display);
        display.getStylesheets().add(CrearUsuarioArea.class.getResource("Style.css").toExternalForm());
        window.showAndWait();
        
        return vb;
    }
}
