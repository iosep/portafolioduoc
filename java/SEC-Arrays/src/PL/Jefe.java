/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.AreaCompetenciaCTL;
import CTL.CompetenciaCTL;
import CTL.CompetenciaNivelCTL;
import CTL.NivelCTL;
import CTL.UsuarioAreaCTL;
import CTL.UsuarioCTL;
import O.ArbolO;
import O.AreaCompetenciaO;
import O.AreaO;
import O.CompetenciaNivelO;
import O.CompetenciaO;
import O.EncuestaO;
import O.NivelO;
import O.UsuarioAreaO;
import O.UsuarioO;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Jefe {

    private final UsuarioAreaCTL usArCtl = new UsuarioAreaCTL();
    private final AreaCompetenciaCTL arComCtl = new AreaCompetenciaCTL();
    private final CompetenciaNivelCTL comNiCtl = new CompetenciaNivelCTL();
    private final AreaCTL areaCtl = new AreaCTL();
    private final UsuarioCTL userCtl = new UsuarioCTL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final NivelCTL nivelCtl = new NivelCTL();

    private final ArrayList<AreaO> areas = new ArrayList<>();
    private final ArrayList<UsuarioO> funcionarios = new ArrayList<>();
    private final ArrayList<CompetenciaO> competencias = new ArrayList<>();
    private final ArrayList<NivelO> niveles = new ArrayList<>();

    Stage window = new Stage();
    BorderPane borderPane = new BorderPane();
    Text tTitle = new Text("SEC - Jefe");
    Label lblActiveUser = new Label("Usuario Activo:");
    Label lblActiveUserRut;
    Hyperlink hlExit = new Hyperlink("Cerrar Sesión");
    GridPane gpSesion = new GridPane();
    HBox hbTop0 = new HBox();
    VBox vbTop = new VBox();
    HBox hbCopyright = new HBox();
    Text tCopyright = new Text("©copyright @iosep (José Oñate)");
    TreeItem<ArbolO> tiRoot = new TreeItem<>(new ArbolO("Áreas", 0));
    VBox vbCenter = new VBox();
    Text tArea = new Text();
    Text tTree1 = new Text();
    Text tTree2 = new Text();
    Text tDesc = new Text();
    Button bRealizarEncuesta = new Button("Realizar Encuesta");

    /**
     * launch main window jefe
     *
     * @param userRut
     */
    public void start(String userRut) {
        tTitle = new Text("SEC - Jefe");
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
//add top vbox children
        vbTop.getChildren().addAll(hbTop0);
//copyright
        hbCopyright.getStyleClass().add("hboxbottom");
        tCopyright.setFill(Color.DARKCYAN);
        hbCopyright.getChildren().addAll(tCopyright);
        hbCopyright.setAlignment(Pos.CENTER);

//jefe treeView
        tiRoot.setExpanded(true);
        TreeItem<ArbolO> tiLayer1;
        TreeItem<ArbolO> tiLayer2;
        TreeItem<ArbolO> tiLayer3;
        TreeItem<ArbolO> tiLayer4;
        TreeItem<ArbolO> tiLayer5;
        boolean found;
//Funcionarios By Áreas
        for (UsuarioAreaO usAr : usArCtl.getFuncionarioUserAreaByRutJefe(userRut)) {
            if (!funcionarios.contains(userCtl.getUsuarioById(usAr.getUsuario_id()))) {
                funcionarios.add(userCtl.getUsuarioById(usAr.getUsuario_id()));
                System.out.println("funcionariosList: " + usAr.getUsuarioString());
            }
            tiLayer3 = new TreeItem<>(new ArbolO(usAr.getUsuarioString(), usAr.getUsuario_id()));
            found = false;
            for (TreeItem<ArbolO> treeLayer1 : tiRoot.getChildren()) {
                if (treeLayer1.getValue().getTexto().contentEquals(usAr.getAreaString())) {
                    for (TreeItem<ArbolO> treeLayer2 : treeLayer1.getChildren()) {
                        if (treeLayer2.getValue().getTexto().contentEquals("Funcionarios")) {
                            treeLayer2.getChildren().add(tiLayer3);
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("areasList: " + usAr.getAreaString());
                areas.add(areaCtl.getAreaById(usAr.getArea_id()));
                tiLayer1 = new TreeItem<>(new ArbolO(usAr.getAreaString(), usAr.getArea_id()));
                tiLayer2 = new TreeItem<>(new ArbolO("Funcionarios", 0));
                tiLayer2.getChildren().add(tiLayer3);
                tiLayer1.getChildren().add(tiLayer2);
                tiRoot.getChildren().add(tiLayer1);
            }
        }
//Competencias By Área        
        for (TreeItem<ArbolO> treeLayer1 : tiRoot.getChildren()) {
            for (AreaCompetenciaO arCom : arComCtl.getAreaCompetenciasByAreaIdFX(treeLayer1.getValue().getId())) {
                if (!competencias.contains(compCtl.getCompetenciaById(arCom.getCompetencia_id()))) {
                    competencias.add(compCtl.getCompetenciaById(arCom.getCompetencia_id()));
//                    System.out.println("competenciasList: " + arCom.getCompNombre());
                }
                tiLayer3 = new TreeItem<>(new ArbolO(arCom.getCompNombre(), arCom.getCompetencia_id()));
                found = false;
                for (TreeItem<ArbolO> treeLayer2 : treeLayer1.getChildren()) {
                    if (treeLayer2.getValue().getTexto().contentEquals("Competencias")) {
                        treeLayer2.getChildren().add(tiLayer3);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tiLayer2 = new TreeItem<>(new ArbolO("Competencias", 0));
                    tiLayer2.getChildren().add(tiLayer3);
                    treeLayer1.getChildren().add(tiLayer2);
                }
            }
        }
//Niveles By Competencia
        for (TreeItem<ArbolO> treeLayer1 : tiRoot.getChildren()) {
            for (TreeItem<ArbolO> treeLayer2 : treeLayer1.getChildren()) {
                if (treeLayer2.getValue().getTexto().contentEquals("Competencias")) {
                    for (TreeItem<ArbolO> treeLayer3 : treeLayer2.getChildren()) {
                        for (CompetenciaNivelO comNi : comNiCtl.getCompetenciaNivelesByCompetenciaIdFX(treeLayer3.getValue().getId())) {
                            if (!niveles.contains(nivelCtl.getNivelById(comNi.getNivel_id()))) {
                                niveles.add(nivelCtl.getNivelById(comNi.getNivel_id()));
//                                System.out.println("nivelesList: " + comNi.getNivelNombre());
                            }
                            tiLayer5 = new TreeItem<>(new ArbolO(comNi.getNivelNombre(), comNi.getNivel_id()));
                            found = false;
                            for (TreeItem<ArbolO> treeLayer4 : treeLayer3.getChildren()) {
                                if (treeLayer4.getValue().getTexto().contentEquals("Niveles")) {
                                    treeLayer4.getChildren().add(tiLayer5);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                tiLayer4 = new TreeItem<>(new ArbolO("Niveles", 0));
                                tiLayer4.getChildren().add(tiLayer5);
                                treeLayer3.getChildren().add(tiLayer4);
                            }
                        }
                    }
                }
            }
        }
//create treeview
        TreeView<ArbolO> tvTree = new TreeView<>(tiRoot);
        tvTree.getStyleClass().add("tree");
        tvTree.getSelectionModel().selectedItemProperty().addListener((ob, ol, ne) -> {
            if (ne != null) {
                TreeItem<ArbolO> select = (TreeItem<ArbolO>) ne;
                System.out.println("Selected Text : " + select.getValue());
                if (select.getParent() != null) {
                    switch (select.getParent().getValue().getTexto()) {
                        case "Áreas":
                            for (AreaO a : areas) {
                                if (select.getValue().getId() == a.getId()) {
                                    tArea.setText("Área:   " + a.getNombre());
                                    tDesc.setText("Descripción del Área:   " + a.getDescripcion());
                                    vbCenter.getChildren().clear();
                                    vbCenter.getChildren().addAll(tArea, tDesc);
                                }
                            }
                            break;
                        case "Funcionarios":
                            for (UsuarioO f : funcionarios) {
                                if (select.getValue().getId() == f.getId()) {
                                    tArea.setText("Área:   " + select.getParent().getParent().getValue().getTexto());
                                    tTree1.setText("Funcionario:   " + f.toString());
                                    vbCenter.getChildren().clear();
                                    vbCenter.getChildren().addAll(tArea, tTree1, bRealizarEncuesta);
                                    bRealizarEncuesta.setOnAction(value -> {
                                        EncuestaJefe ej = new EncuestaJefe();
                                        EncuestaO enc = new EncuestaO(0, 0, 0);
                                        ej.start(userRut, f.getId());
                                    });
                                }
                            }
                            break;
                        case "Competencias":
                            for (CompetenciaO c : competencias) {
                                if (select.getValue().getId() == c.getId()) {
                                    tArea.setText("Área:   " + select.getParent().getParent().getValue().getTexto());
                                    tTree1.setText("Competencia:   " + c.getNombre());
                                    tDesc.setText("Descripción de la Competencia:   " + c.getDescripcion());
                                    vbCenter.getChildren().clear();
                                    vbCenter.getChildren().addAll(tArea, tTree1, tDesc);
                                }
                            }
                            break;
                        case "Niveles":
                            for (NivelO n : niveles) {
                                if (select.getValue().getId() == n.getId()) {
                                    tArea.setText("Área:   " + select.getParent().getParent().getParent().getParent().getValue().getTexto());
                                    tTree1.setText("Competencia:   " + select.getParent().getParent().getValue().getTexto());
                                    tTree2.setText("Nivel:   " + n.getNombre());
                                    tDesc.setText("Descripción del Nivel:   " + n.getDescripcion());
                                    vbCenter.getChildren().clear();
                                    vbCenter.getChildren().addAll(tArea, tTree1, tTree2, tDesc);
                                }
                            }
                            break;
                    }
                }
            }
        });
        tArea.getStyleClass().add("plane");
        tArea.setWrappingWidth(500);
        tTree1.getStyleClass().add("plane");
        tTree1.setWrappingWidth(500);
        tTree2.getStyleClass().add("plane");
        tTree2.setWrappingWidth(500);
        tDesc.getStyleClass().add("plane");
        tDesc.setWrappingWidth(500);
        vbCenter.getStyleClass().add("vcenter");
//borderPane
        borderPane = new BorderPane();
        borderPane.setTop(vbTop);
        borderPane.setLeft(tvTree);
        borderPane.setBottom(hbCopyright);
        borderPane.setCenter(vbCenter);
//scene & window
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(Jefe.class.getResource("Style.css").toExternalForm());
        this.window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        this.window.setTitle("SEC");
        this.window.setScene(scene);
        this.window.setMaximized(!window.isMaximized());
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
