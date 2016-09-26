/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.UsuarioCTL;
import O.UsuarioO;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Admin extends Application {

    private final UsuarioCTL uc = new UsuarioCTL();
    private static TableView<UsuarioO> tableUsers;
    private static StackPane sp = new StackPane();

    @Override
    public void start(Stage primaryStage) {

        HBox topMenu = new HBox();
        Button btnUsuario = new Button("Mantenedor Usuario");
        Button btnArea = new Button("Mantenedor Area");
        topMenu.getChildren().addAll(btnUsuario, btnArea);
        topMenu.getStyleClass().add("hbox");

        BorderPane bp = new BorderPane();
        bp.setTop(topMenu);

//button mantenedor usuario
        btnUsuario.setOnAction((ActionEvent e) -> {
            sp.getChildren().add(tableUsers);
        });

//setting table users data        
        TableColumn<UsuarioO, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //idColumn.setMinWidth(10);

        TableColumn<UsuarioO, String> idjefeColumn = new TableColumn<>("Id Jefe/a");
        idjefeColumn.setCellValueFactory(new PropertyValueFactory<>("jefe_id"));

        TableColumn<UsuarioO, String> rutColumn = new TableColumn<>("Rut");
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));

        TableColumn<UsuarioO, String> dvColumn = new TableColumn<>("Dv");
        dvColumn.setCellValueFactory(new PropertyValueFactory<>("dv"));

        TableColumn<UsuarioO, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<UsuarioO, String> apellidoColumn = new TableColumn<>("Apellido");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<UsuarioO, String> fonoColumn = new TableColumn<>("Teléfono");
        fonoColumn.setCellValueFactory(new PropertyValueFactory<>("fono"));

        TableColumn<UsuarioO, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<UsuarioO, String> sexoColumn = new TableColumn<>("Sexo");
        sexoColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        TableColumn<UsuarioO, String> rolColumn = new TableColumn<>("Rol");
        rolColumn.setCellValueFactory(new PropertyValueFactory<>("rol_id"));

        TableColumn<UsuarioO, String> creadoColumn = new TableColumn<>("Creado");
        creadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));

        tableUsers = new TableView<>();
        tableUsers.setItems(uc.getUsuariosFX());
        tableUsers.getColumns().addAll(idColumn, idjefeColumn, rutColumn, dvColumn, nombreColumn, apellidoColumn,
                fonoColumn, emailColumn, sexoColumn, rolColumn, creadoColumn);
        tableUsers.setEditable(true);

//setting context menu        
        tableUsers.setRowFactory((TableView<UsuarioO> tableView) -> {
            final TableRow<UsuarioO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Eliminar");
//modificar            
            modificarMenuItem.setOnAction((ActionEvent event) -> {
                
            });
//eliminar            
            eliminarMenuItem.setOnAction((ActionEvent event) -> {
                tableUsers.getItems().remove(row.getItem());
            });  
            contextMenu.getItems().add(modificarMenuItem);
            contextMenu.getItems().add(eliminarMenuItem);
            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });

//general settings for stack pane        
        sp.setAlignment(Pos.CENTER);
        sp.getStyleClass().add("stack-pane");
        bp.setCenter(sp);

//setting scene
        Scene admin = new Scene(bp, 800, 600);

        primaryStage.setTitle("SEC - Mantenedores");
        primaryStage.setScene(admin);
        admin.getStylesheets().add(Admin.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }

}
