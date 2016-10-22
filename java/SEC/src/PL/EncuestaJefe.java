/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCompetenciaCTL;
import CTL.EncuestaCTL;
import CTL.PreguntaCTL;
import CTL.SeleccionCTL;
import CTL.UsuarioAreaCTL;
import CTL.UsuarioCTL;
import O.AreaCompetenciaO;
import O.EncuestaO;
import O.PreguntaO;
import O.PreguntaO.ObjQuestion;
import O.RespuestaO;
import O.SeleccionO;
import O.UsuarioAreaO;
import O.UsuarioO;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
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
public class EncuestaJefe {

    private final UsuarioAreaCTL usArCtl = new UsuarioAreaCTL();
    private final AreaCompetenciaCTL arComCtl = new AreaCompetenciaCTL();
    private final PreguntaCTL preCtl = new PreguntaCTL();
    private final UsuarioCTL usCtl = new UsuarioCTL();
    private final EncuestaCTL enCtl = new EncuestaCTL();
    private final SeleccionCTL seCtl = new SeleccionCTL();

    Stage window = new Stage();
    BorderPane borderPane = new BorderPane();
    Text tTitle = new Text("SEC - Encuesta");
    Label lblActiveUser = new Label("Usuario Activo:");
    Label lblActiveUserRut;
//    Hyperlink hlExit = new Hyperlink("Cerrar Sesión");
    GridPane gpSesion = new GridPane();
    HBox hbTop0 = new HBox();
    VBox vbTop = new VBox();
//    HBox hbCopyright = new HBox();
//    Text tCopyright = new Text("©copyright @iosep (José Oñate)");
    VBox vbCenter = new VBox();
    Text tEvaluado = new Text();
    Button bGuardar = new Button("Guardar Encuesta");
    boolean well = false;

    /**
     * launch main window jefe
     *
     * @param userRut
     * @param funcId
     */
    public void start(String userRut, int funcId, int idPer) {
        window.initModality(Modality.APPLICATION_MODAL);
        tTitle = new Text("SEC - Encuesta");
        tTitle.getStyleClass().add("title");
        lblActiveUser = new Label("Usuario Activo:");
        lblActiveUserRut = new Label("  " + userRut);
        /* 
        hlExit = new Hyperlink("Cerrar Sesión");
        hlExit.setBorder(Border.EMPTY);
        hlExit.setOnAction(e -> {
            Login login = new Login();
            login.start(window);
        });
         */
        gpSesion = new GridPane();
        gpSesion.setAlignment(Pos.TOP_RIGHT);
        gpSesion.add(lblActiveUser, 0, 0);
        gpSesion.add(lblActiveUserRut, 1, 0);
//        gpSesion.add(hlExit, 1, 1);
        gpSesion.getStyleClass().add("logout");
        hbTop0 = new HBox();
        hbTop0.getChildren().addAll(tTitle, gpSesion);
        HBox.setHgrow(gpSesion, Priority.ALWAYS);
        vbTop = new VBox();
        vbTop.getStyleClass().add("vbox");
        tEvaluado.setText("Funcionario Evaluado:  " + usCtl.getUsuarioById(funcId).toString());
        tEvaluado.getStyleClass().add("subtitle");
//add top vbox children
        vbTop.getChildren().addAll(hbTop0, tEvaluado);
//copyright
/*
        hbCopyright.getStyleClass().add("hboxbottom");
        tCopyright.setFill(Color.DARKCYAN);
        hbCopyright.getChildren().addAll(tCopyright);
        hbCopyright.setAlignment(Pos.CENTER);
         */
        ArrayList<PreguntaO> preguntas = new ArrayList<>();
        for (UsuarioAreaO ua : usArCtl.getUsuarioAreasByUserIdFX(funcId)) {
            for (AreaCompetenciaO ac : arComCtl.getAreaCompetenciasByAreaIdFX(ua.getArea_id())) {
                for (PreguntaO p : preCtl.getPreguntasByCompetenciaId(ac.getCompetencia_id())) {
                    preguntas.add(p);
                }
            }
        }
        ArrayList<RespuestaO> respuestas = new ArrayList<>();
        if (preguntas.size() > 0) {
            for (ObjQuestion oq : PreguntaO.createEncuesta(preguntas)) {
                RespuestaO r0 = new RespuestaO();
                r0.setPregunta_id(((PreguntaO) oq.getTg().getUserData()).getId());
                respuestas.add(r0);
                oq.getTg().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                        if (oq.getTg().getSelectedToggle() != null) {
                            RespuestaO r = (RespuestaO) oq.getTg().getSelectedToggle().getUserData();
                            for (RespuestaO re : respuestas) {
                                if (re.getPregunta_id() == r.getPregunta_id()) {
                                    re.setId(r.getId());
                                }
                            }
                        }
                    }
                });
                vbCenter.getChildren().add(oq.getT());
                for (RadioButton rb : oq.getRb()) {
                    vbCenter.getChildren().add(rb);
                }
            }
        }
        vbCenter.getChildren().add(bGuardar);
        vbCenter.getStyleClass().add("vcenter");
        bGuardar.setOnAction(v -> {
            well = false;
            String missing = "";
            for (int i = 0; i < respuestas.size(); i++) {
                if (respuestas.get(i).getId() == 0) {
                    missing += (i + 1) + ", ";
                }
            }
            if (missing.length() > 0) {
                missing = missing.substring(0, missing.length() - 2);
                missing += ".";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(window);
                alert.setTitle("Encuesta Incompleta");
                alert.setHeaderText(null);
                alert.setContentText("Faltan por responder las preguntas N°: " + missing);
                alert.showAndWait();
            } else {
                UsuarioO jefe = usCtl.getUsuarioByRut(userRut);
                EncuestaO enc = new EncuestaO(jefe.getId(), funcId, idPer);
                if (enCtl.addEncuesta(enc)) {
                    for (RespuestaO r : respuestas) {
                        if (SeleccionCTL.addSeleccion(new SeleccionO(enc.getId(), r.getId()))) {
                            well = true;
                        } else {
                            well = false;
                        }
                    }
                }
                if (well) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Éxito Encuesta");
                    alert.setHeaderText(null);
                    alert.setContentText("Encuesta guardada con éxito");
                    alert.showAndWait();
                    window.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("ERROR Encuesta");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR al guardar Encuesta");
                    alert.showAndWait();
                    window.close();
                }
            }
        });
//borderPane
        borderPane = new BorderPane();
        borderPane.setTop(vbTop);
//        borderPane.setBottom(hbCopyright);
        borderPane.setCenter(vbCenter);
//scene & window
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(EncuestaJefe.class.getResource("Style.css").toExternalForm());
        this.window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        this.window.setTitle("SEC");
        this.window.setScene(scene);
//        this.window.setMaximized(!window.isMaximized());
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