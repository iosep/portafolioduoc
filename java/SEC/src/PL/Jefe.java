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
import CTL.UsuarioAreaCTL;
import O.AreaCompetenciaO;
import O.AreaO;
import O.CompetenciaNivelO;
import O.CompetenciaO;
import O.UsuarioAreaO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private final CompetenciaCTL compCtl = new CompetenciaCTL();

    Stage window = new Stage();
    BorderPane borderPane = new BorderPane();
    Text tTitle = new Text("SEC - Funcionario");
    Label lblActiveUser = new Label("Usuario Activo:");
    Label lblActiveUserRut;
    Hyperlink hlExit = new Hyperlink("Cerrar Sesión");
    GridPane gpSesion = new GridPane();
    HBox hbTop0 = new HBox();
    VBox vbTop = new VBox();
    HBox hbCopyright = new HBox();
    Text tCopyright = new Text("©copyright @iosep (José Oñate)");
    TreeItem<String> tiRoot = new TreeItem<>("Áreas");

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
        TreeItem<String> tiLayer1;
        TreeItem<String> tiLayer2;
        TreeItem<String> tiLayer3;
        TreeItem<String> tiLayer4;
        TreeItem<String> tiLayer5;
        boolean found;
//Funcionarios By Área        
        for (UsuarioAreaO usAr : usArCtl.getFuncionarioUserAreaByRutJefe(userRut)) {
            tiLayer3 = new TreeItem<>(usAr.getUsuarioString());
            found = false;
            for (TreeItem<String> area : tiRoot.getChildren()) {
                if (area.getValue().contentEquals(usAr.getAreaString())) {
                    for (TreeItem<String> child : area.getChildren()) {
                        if (child.getValue().contentEquals("Funcionarios")) {
                            child.getChildren().add(tiLayer3);
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (!found) {
                tiLayer1 = new TreeItem<>(usAr.getAreaString());
                tiLayer2 = new TreeItem<>("Funcionarios");
                tiLayer2.getChildren().add(tiLayer3);
                tiLayer1.getChildren().add(tiLayer2);
                tiRoot.getChildren().add(tiLayer1);
            }
        }
//Competencias By Área        
        for (TreeItem<String> treeLayer1 : tiRoot.getChildren()) {
            AreaO a0 = areaCtl.getAreaByNombre(treeLayer1.getValue());
            for (AreaCompetenciaO arCom : arComCtl.getAreaCompetenciasByAreaIdFX(a0.getId())) {
                tiLayer3 = new TreeItem<>(arCom.getCompNombre());
                found = false;
                for (TreeItem<String> treeLayer2 : treeLayer1.getChildren()) {
                    if (treeLayer2.getValue().contentEquals("Competencias")) {
                        treeLayer2.getChildren().add(tiLayer3);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tiLayer2 = new TreeItem<>("Competencias");
                    tiLayer2.getChildren().add(tiLayer3);
                    treeLayer1.getChildren().add(tiLayer2);
                }
            }
        }
//Niveles By Competencia
        for (TreeItem<String> treeLayer1 : tiRoot.getChildren()) {
            for (TreeItem<String> treeLayer2 : treeLayer1.getChildren()) {
                if (treeLayer2.getValue().contentEquals("Competencias")) {
                    for (TreeItem<String> treeLayer3 : treeLayer2.getChildren()) {
                        CompetenciaO c0 = compCtl.getCompetenciaByNombre(treeLayer3.getValue());
                        for (CompetenciaNivelO comNi : comNiCtl.getCompetenciaNivelesByCompetenciaIdFX(c0.getId())) {
                            tiLayer5 = new TreeItem<>(comNi.getNivelNombre());
                            found = false;
                            for (TreeItem<String> treeLayer4 : treeLayer3.getChildren()) {
                                if (treeLayer4.getValue().contentEquals("Niveles")) {
                                    treeLayer4.getChildren().add(tiLayer5);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                tiLayer4 = new TreeItem<>("Niveles");
                                tiLayer4.getChildren().add(tiLayer5);
                                treeLayer3.getChildren().add(tiLayer4);
                            }
                        }
                    }
                }
            }
        }
//load tree
        TreeView<String> tvTree = new TreeView<>(tiRoot);

        tvTree.getStyleClass()
                .add("tree");
//borderPane
        borderPane = new BorderPane();

        borderPane.setTop(vbTop);

        borderPane.setLeft(tvTree);

        borderPane.setBottom(hbCopyright);
//scene & window
        Scene scene = new Scene(borderPane);

        scene.getStylesheets()
                .add(Jefe.class
                        .getResource(
                                "Style.css").toExternalForm());

        this.window.getIcons()
                .add(new Image(getClass().getResourceAsStream("desk.png")));

        this.window.setTitle(
                "SEC");

        this.window.setScene(scene);

        this.window.setMaximized(
                !window.isMaximized());

        this.window.addEventHandler(KeyEvent.KEY_PRESSED,
                this::handleKey);

        this.window.show();

    }

    private void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case F11:
                this.window.setFullScreen(!window.isFullScreen());
                break;
        }
    }

}
