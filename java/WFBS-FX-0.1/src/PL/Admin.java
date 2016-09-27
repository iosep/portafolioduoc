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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Admin extends Application {

//variables reutilizables
    private final VBox display = new VBox();
    private final Text displayTitle = new Text();
    private final TextField filterField = new TextField();
    private final Label filterLabel = new Label();
    private final HBox bottomBox = new HBox();
    private final Button crearBtn = new Button();

//variables mantenedor usuario
    private final UsuarioCTL uc = new UsuarioCTL();
    private final TableView<UsuarioO> tableUsers = new TableView<>();
    private TableColumn<UsuarioO, String> rutColumn;
    private TableColumn<UsuarioO, String> nombreColumn;
    private TableColumn<UsuarioO, String> apellidoColumn;
    private TableColumn<UsuarioO, String> fonoColumn;
    private TableColumn<UsuarioO, String> emailColumn;
    private TableColumn<UsuarioO, String> sexoColumn;
    private TableColumn<UsuarioO, String> rolColumn;
    private TableColumn<UsuarioO, String> jefeColumn;
    private TableColumn<UsuarioO, String> creadoColumn;

    @Override
    public void start(Stage primaryStage) {

        HBox topMenu = new HBox();
        Button btnUsuario = new Button("Mantenedor Usuario");
        Button btnArea = new Button("Mantenedor Area");
        topMenu.getChildren().addAll(btnUsuario, btnArea);
        topMenu.getStyleClass().add("hbox");

        displayTitle.getStyleClass().add("title");
        display.getStyleClass().add("vbox");

        BorderPane bp = new BorderPane();
        bp.setTop(topMenu);

//display mantenedor usuario button
        btnUsuario.setOnAction((ActionEvent e) -> {
            displayTitle.setText("Mantenedor Usuario");

            tableUsers.getColumns().clear();
            tableUsers.setItems(uc.getUsuariosFX());
            tableUsers.getColumns().addAll(rutColumn, nombreColumn, apellidoColumn,
                    fonoColumn, emailColumn, sexoColumn, rolColumn, jefeColumn, creadoColumn);
            //filtrar por nombre, apellido o rut
            // 1. Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<UsuarioO> filteredData = new FilteredList<>(uc.getUsuariosFX(), p -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filterUser -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (filterUser.getRut().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } else if (filterUser.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    } else if (filterUser.getApellido().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    }
                    return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<UsuarioO> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(tableUsers.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            tableUsers.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nuevo Usuario");
            crearBtn.setOnAction(ev -> {
                CrearUsuario cuw = new CrearUsuario();
                boolean crearVB = cuw.display();
                if (crearVB) {
                    tableUsers.refresh();
                    System.out.println("llega la respuesta de creacion de usuario al main admin");
                }
            });
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, tableUsers, bottomBox);
        });

//setting table users context menu
        tableUsers.setEditable(true);
        tableUsers.setRowFactory((TableView<UsuarioO> tableView) -> {
            final TableRow<UsuarioO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Eliminar");
            //modificar            
            modificarMenuItem.setOnAction((ActionEvent event) -> {
                System.out.println("Modificar usuario rut: " + row.getItem().getRut());
            });
            //eliminar            
            eliminarMenuItem.setOnAction((ActionEvent event) -> {
                //tableUsers.getItems().remove(row.getItem());
                System.out.println("Eliminar usuario rut: " + row.getItem().getRut());
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

//setting users table columns data and headers
        rutColumn = new TableColumn<>("Rut");
        rutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));
        //idColumn.setMinWidth(10);

        nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        apellidoColumn = new TableColumn<>("Apellido");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        fonoColumn = new TableColumn<>("Teléfono");
        fonoColumn.setCellValueFactory(new PropertyValueFactory<>("fono"));

        emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        sexoColumn = new TableColumn<>("Sexo");
        sexoColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        rolColumn = new TableColumn<>("Rol");
        rolColumn.setCellValueFactory(new PropertyValueFactory<>("rol"));

        jefeColumn = new TableColumn<>("Jefa");
        jefeColumn.setCellValueFactory(new PropertyValueFactory<>("rut_jefe"));

        creadoColumn = new TableColumn<>("Creado");
        creadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));

//load display in center border pane
        bp.setCenter(display);

//setting scene
        Scene admin = new Scene(bp, 800, 600);

        primaryStage.setTitle("SEC - Mantenedores");
        primaryStage.setScene(admin);
        admin.getStylesheets().add(Admin.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }

}
