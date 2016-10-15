/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.CompetenciaCTL;
import CTL.UsuarioAreaCTL;
import CTL.UsuarioCTL;
import O.AreaO;
import O.UsuarioAreaO;
import O.UsuarioO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    Stage window = new Stage();
    BorderPane borderPane = new BorderPane();
    Text tTitle = new Text("SEC - Funcionario");
    Label lblActiveUser = new Label("Usuario Activo:");
    Label lblActiveUserRut;
    Hyperlink hlExit = new Hyperlink("Cerrar Sesión");
    GridPane gpSesion = new GridPane();
    HBox hbTop0 = new HBox();
    VBox vbTop = new VBox();
    VBox vbLeft = new VBox();
    HBox hbCopyright = new HBox();
    Text tCopyright = new Text("©copyright @iosep (José Oñate)");

    TreeItem<String> tiFuncByArea = new TreeItem<>("Funcionarios por Área");

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
        tiFuncByArea.setExpanded(true);
        for (UsuarioAreaO usAr : usArCtl.getFuncionarioUserAreaByRutJefe(userRut)) {
            TreeItem<String> tiFuncionario = new TreeItem<>(usAr.getUsuarioString());
            boolean found = false;
            for (TreeItem<String> area : tiFuncByArea.getChildren()) {
                if (area.getValue().contentEquals(usAr.getAreaString())) {
                    area.getChildren().add(tiFuncionario);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> tiArea = new TreeItem<>(usAr.getAreaString());
                tiFuncByArea.getChildren().add(tiArea);
                tiArea.getChildren().add(tiFuncionario);
            }
        }
        TreeView<String> tvTree = new TreeView<>(tiFuncByArea);
        vbLeft.getStyleClass().add("vbox");
        vbLeft.getChildren().add(tvTree);
//borderPane
        borderPane = new BorderPane();
        borderPane.setTop(vbTop);
        borderPane.setLeft(vbLeft);
        borderPane.setBottom(hbCopyright);
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

    private void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case F11:
                this.window.setFullScreen(!window.isFullScreen());
                break;
        }
    }

}
