/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCompetenciaCTL;
import CTL.CompetenciaCTL;
import CTL.EncuestaCTL;
import CTL.EvaluacionCTL;
import CTL.PeriodoCTL;
import CTL.PreguntaCTL;
import CTL.SeleccionCTL;
import CTL.UsuarioAreaCTL;
import FN.Formato;
import O.CompetenciaO;
import O.EvaluacionO;
import O.ObservacionO;
import O.PeriodoO;
import O.Reporte1O;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class EvaluacionesFuncionario {

    private final UsuarioAreaCTL usArCtl = new UsuarioAreaCTL();
    private final AreaCompetenciaCTL arComCtl = new AreaCompetenciaCTL();
    private final PreguntaCTL preCtl = new PreguntaCTL();
    private final EncuestaCTL enCtl = new EncuestaCTL();
    private final SeleccionCTL seCtl = new SeleccionCTL();
    private final EvaluacionCTL evCtl = new EvaluacionCTL();
    private final PeriodoCTL perCtl = new PeriodoCTL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();

    Stage window = new Stage();
    BorderPane borderPane = new BorderPane();
    Text tTitle = new Text("SEC - Encuesta");
    Label lblActiveUser = new Label("Usuario Activo:");
    Label lblActiveUserRut;
    GridPane gpSesion = new GridPane();
    HBox hbTop0 = new HBox();
    VBox vbTop = new VBox();

    /**
     * launch main window jefe
     *
     * @param evList
     * @param userRut
     */
    public void start(ArrayList<EvaluacionO> evList, String userRut) {
        window.initModality(Modality.APPLICATION_MODAL);
        tTitle = new Text("SEC - Evaluaciones");
        tTitle.getStyleClass().add("title");
        lblActiveUser = new Label("Usuario Activo:");
        lblActiveUserRut = new Label("  " + userRut);
        gpSesion = new GridPane();
        gpSesion.setAlignment(Pos.TOP_RIGHT);
        gpSesion.add(lblActiveUser, 0, 0);
        gpSesion.add(lblActiveUserRut, 1, 0);
        gpSesion.getStyleClass().add("logout");
        hbTop0 = new HBox();
        hbTop0.getChildren().addAll(tTitle, gpSesion);
        HBox.setHgrow(gpSesion, Priority.ALWAYS);
        vbTop = new VBox();
        vbTop.getStyleClass().add("vbox");
//add top vbox children
        vbTop.getChildren().addAll(hbTop0);
//leftPane
        Label lPer = new Label("Periodos: ");
        ArrayList<PeriodoO> pers = new ArrayList<>();
        int perId = 0;
        for (EvaluacionO e : evList) {
            if (perId != e.getPeriodoId()) {
                pers.add(perCtl.getPeriodoById(e.getPeriodoId()));
            }
            perId = e.getPeriodoId();
        }
        ListView lvPer = new ListView(FXCollections.observableArrayList(pers));
        lvPer.setCellFactory(param -> new ListCell<PeriodoO>() {
            @Override
            protected void updateItem(PeriodoO item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(Formato.dateToString(item.getInicio()) + "  hasta  " + Formato.dateToString(item.getFin()));
                }
            }
        });
        VBox vbLeft = new VBox(lPer, lvPer);
        vbLeft.setMaxWidth(200);
        vbLeft.getStyleClass().add("vbox");
//centerPane
        ArrayList<Reporte1O> rep1 = new ArrayList<>();
        Label lEv = new Label("Resultados Evaluación: ");
        TableView tvEvComp = new TableView();
        tvEvComp.setMinWidth(400);
        TableColumn<Reporte1O, String> rep1Comp = new TableColumn<>("Competencia");
        rep1Comp.setCellValueFactory(new PropertyValueFactory<>("comp"));
        rep1Comp.setMinWidth(150);
        TableColumn<Reporte1O, Integer> rep1Result = new TableColumn<>("Resultado");
        rep1Result.setCellValueFactory(new PropertyValueFactory<>("result"));
        TableColumn<Reporte1O, Integer> rep1Req = new TableColumn<>("Requerido");
        rep1Req.setCellValueFactory(new PropertyValueFactory<>("nopt"));
        TableColumn<Reporte1O, Integer> rep1Brecha = new TableColumn<>("Brecha");
        rep1Brecha.setCellValueFactory(new PropertyValueFactory<>("brecha"));
        tvEvComp.getColumns().addAll(rep1Comp, rep1Result, rep1Req, rep1Brecha);
        Button bExport1 = new Button("Exportar a Excel");
        TableView tvEvObs = new TableView();
        tvEvObs.setMinWidth(400);
        Button bExport2 = new Button("Exportar a Excel");
        VBox vbTablas = new VBox(tvEvComp, bExport1, tvEvObs, bExport2);
        vbTablas.getStyleClass().add("vbox");
        ScrollPane sp = new ScrollPane();
        sp.setContent(vbTablas);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        VBox vbCenter = new VBox(lEv, sp);
        vbCenter.setMaxHeight(500);
        vbCenter.getStyleClass().add("vbox");
//listener
        lvPer.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<PeriodoO>() {
            public void changed(ObservableValue<? extends PeriodoO> ov,
                    PeriodoO old_val, PeriodoO new_val) {
                rep1.clear();
                for (EvaluacionO e : evList) {
                    if (e.getPeriodoId() == new_val.getId()) {
                        CompetenciaO auxComp = compCtl.getCompetenciaById(e.getCompId());
                        rep1.add(new Reporte1O(auxComp.getNombre(), e.getNota(), auxComp.getNivelOptimo(), e.getBrecha()));
                    }
                }
                tvEvComp.setItems(FXCollections.observableArrayList(rep1));
            }
        });
//borderPane
        borderPane = new BorderPane();
        borderPane.setTop(vbTop);
        borderPane.setCenter(vbCenter);
        borderPane.setLeft(vbLeft);
//scene & window
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(EvaluacionesFuncionario.class.getResource("Style.css").toExternalForm());
        this.window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        this.window.setTitle("SEC");
        this.window.setScene(scene);
        this.window.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKey);
        this.window.showAndWait();
    }

    /**
     * enables F11 full screen key
     *
     * @param keyEvent
     */
    private void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case F11:
                this.window.setFullScreen(!window.isFullScreen());
                break;
        }
    }

}
