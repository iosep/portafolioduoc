/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCompetenciaCTL;
import CTL.PreguntaCTL;
import CTL.UsuarioAreaCTL;
import CTL.UsuarioCTL;
import O.AreaCompetenciaO;
import O.PreguntaO;
import O.PreguntaO.ObjQuestion;
import O.RespuestaO;
import O.UsuarioAreaO;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
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

    Stage window = new Stage();
    BorderPane borderPane = new BorderPane();
    Text tTitle = new Text("SEC - Encuesta");
    Label lblActiveUser = new Label("Usuario Activo:");
    Label lblActiveUserRut;
    Hyperlink hlExit = new Hyperlink("Cerrar Sesión");
    GridPane gpSesion = new GridPane();
    HBox hbTop0 = new HBox();
    VBox vbTop = new VBox();
//    HBox hbCopyright = new HBox();
//    Text tCopyright = new Text("©copyright @iosep (José Oñate)");
    VBox vbCenter = new VBox();
    Text tEvaluado = new Text();

    /**
     * launch main window jefe
     *
     * @param userRut
     * @param funcId
     */
    public void start(String userRut, int funcId) {
        window.initModality(Modality.APPLICATION_MODAL);
        tTitle = new Text("SEC - Encuesta");
        tTitle.getStyleClass().add("title");
        lblActiveUser = new Label("Usuario Activo:");
        lblActiveUserRut = new Label("  " + userRut);
        hlExit = new Hyperlink("Cerrar Sesión");
        hlExit.setBorder(Border.EMPTY);
        hlExit.setOnAction(e -> {
            Login login = new Login();
            login.start(window);
        });
        gpSesion = new GridPane();
        gpSesion.setAlignment(Pos.TOP_RIGHT);
        gpSesion.add(lblActiveUser, 0, 0);
        gpSesion.add(lblActiveUserRut, 1, 0);
        gpSesion.add(hlExit, 1, 1);
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

        if (preguntas.size() > 0) {
            for (ObjQuestion oq : PreguntaO.createEncuesta(preguntas)) {
                oq.getTg().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                        if (oq.getTg().getSelectedToggle() != null) {
                            System.out.println(oq.getTg().getSelectedToggle().getUserData().toString());
                            RespuestaO r = (RespuestaO) oq.getTg().getSelectedToggle().getUserData();
                            System.out.println(r.getPregunta_id() + r.getRespuesta() + r.getPuntos());
                            System.out.println(r.getPregunta_id() + r.getRespuesta() + r.getPuntos());
                        }
                    }
                });
                vbCenter.getChildren().add(oq.getT());
                for (RadioButton rb : oq.getRb()) {
                    vbCenter.getChildren().add(rb);
                }
            }
        }
        vbCenter.getStyleClass().add("vcenter");
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
        this.window.show();
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
