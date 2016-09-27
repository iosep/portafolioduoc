/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.UsuarioCTL;
import O.AreaO;
import O.UsuarioO;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

//variables mantenedor usuarios
    private final UsuarioCTL usersCtl = new UsuarioCTL();
    private final TableView<UsuarioO> usersTable = new TableView<>();
    private TableColumn<UsuarioO, String> rutColumn;
    private TableColumn<UsuarioO, String> nombreColumn;
    private TableColumn<UsuarioO, String> apellidoColumn;
    private TableColumn<UsuarioO, String> fonoColumn;
    private TableColumn<UsuarioO, String> emailColumn;
    private TableColumn<UsuarioO, String> sexoColumn;
    private TableColumn<UsuarioO, String> rolColumn;
    private TableColumn<UsuarioO, String> jefeColumn;
    private TableColumn<UsuarioO, String> activoColumn;
    private TableColumn<UsuarioO, String> creadoColumn;
    private TableColumn<UsuarioO, String> modificadoColumn;
    private TableColumn<UsuarioO, String> desactivadoColumn;

//variables mantenedor areas
    private final AreaCTL areasCtl = new AreaCTL();
    private final TableView<AreaO> areasTable = new TableView<>();
    private TableColumn<AreaO, String> areaIdColumn;
    private TableColumn<AreaO, String> areaNombreColumn;
    private TableColumn<AreaO, String> areaSiglaColumn;
    private TableColumn<AreaO, String> areaActivoColumn;
    private TableColumn<AreaO, String> areaCreadoColumn;
    private TableColumn<AreaO, String> areaModificadoColumn;
    private TableColumn<AreaO, String> areaDesactivadoColumn;

    @Override
    public void start(Stage primaryStage) {

//
//GENERAL SETTINGS
//
        HBox topMenu = new HBox();
        Button btnUsuario = new Button("Mantenedor Usuario");
        Button btnArea = new Button("Mantenedor Area");
        Button btnCompetencia = new Button("Mantenedor Competencia");
        topMenu.getChildren().addAll(btnUsuario, btnArea, btnCompetencia);
        topMenu.getStyleClass().add("hbox");
        displayTitle.getStyleClass().add("title");
        display.getStyleClass().add("vbox");
        BorderPane bp = new BorderPane();
        bp.setTop(topMenu);

//
//MANTENEDOR USUARIOS
//
//display mantenedor usuario button
        btnUsuario.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Usuario");
            usersTable.setItems(usersCtl.getUsuariosFX());
            //filtrar por nombre, apellido o rut
            // 1. Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<UsuarioO> filteredData = new FilteredList<>(usersCtl.getUsuariosFX(), p -> true);
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
            sortedData.comparatorProperty().bind(usersTable.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            usersTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nuevo Usuario");
            crearBtn.setOnAction(ev -> {
                CrearUsuario cuw = new CrearUsuario();
                boolean crearVB = cuw.display();
                if (crearVB) {
                    usersTable.setItems(usersCtl.getUsuariosFX());
                    System.out.println("llega la respuesta de creacion de usuario al main admin");
                }
            });
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, usersTable, bottomBox);
        });
//setting table users context menu
        usersTable.setEditable(true);
        usersTable.setRowFactory((TableView<UsuarioO> tableView) -> {
            final TableRow<UsuarioO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar usuario rut: " + row.getItem().getRut());
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                //tableUsers.getItems().remove(row.getItem());
                System.out.println("Desactivar usuario rut: " + row.getItem().getRut());
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
        activoColumn = new TableColumn<>("Activo");
        activoColumn.setCellValueFactory(new PropertyValueFactory<>("activo"));
        creadoColumn = new TableColumn<>("Creado");
        creadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));
        modificadoColumn = new TableColumn<>("Modificado");
        modificadoColumn.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        desactivadoColumn = new TableColumn<>("Desactivado");
        desactivadoColumn.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load USERS columns
        usersTable.getColumns().addAll(rutColumn, nombreColumn, apellidoColumn, fonoColumn, emailColumn, sexoColumn,
                rolColumn, jefeColumn, activoColumn, creadoColumn, modificadoColumn, desactivadoColumn);

//
//MANTENEDOR AREAS
//
//display mantenedor AREAS button
        btnArea.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Area");
            areasTable.setItems(areasCtl.getAreasFX());
            //filtrar por nombre o sigla
            FilteredList<AreaO> filteredData = new FilteredList<>(areasCtl.getAreasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filterUser -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (filterUser.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    } else if (filterUser.getSigla().toLowerCase().contains(lowerCaseFilter)) {
                    }
                    return false;
                });
            });
            SortedList<AreaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(areasTable.comparatorProperty());
            areasTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nueva Area");
            crearBtn.setOnAction(ev -> {
                CrearArea caw = new CrearArea();
                if (caw.display()) {
                    areasTable.setItems(areasCtl.getAreasFX());
                    System.out.println("llega la respuesta de creacion de area al main admin");
                }
            });
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, areasTable, bottomBox);
        });
//setting table AREAS context menu
        areasTable.setEditable(true);
        areasTable.setRowFactory((TableView<AreaO> tableView) -> {
            final TableRow<AreaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar area id: " + row.getItem().getId());
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                //tableUsers.getItems().remove(row.getItem());
                System.out.println("Desactivar area id: " + row.getItem().getId());
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
//setting AREAS table columns data and headers
        areaIdColumn = new TableColumn<>("Id");
        areaIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        areaNombreColumn = new TableColumn<>("Nombre");
        areaNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        areaSiglaColumn = new TableColumn<>("Sigla");
        areaSiglaColumn.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        areaActivoColumn = new TableColumn<>("Activo");
        areaActivoColumn.setCellValueFactory(new PropertyValueFactory<>("activo"));
        areaCreadoColumn = new TableColumn<>("Creado");
        areaCreadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));
        areaModificadoColumn = new TableColumn<>("Modificado");
        areaModificadoColumn.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        areaDesactivadoColumn = new TableColumn<>("Desactivado");
        areaDesactivadoColumn.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load AREAS columns
        areasTable.getColumns().addAll(areaIdColumn, areaNombreColumn, areaSiglaColumn, areaActivoColumn,
                areaCreadoColumn, areaModificadoColumn, areaDesactivadoColumn);

//
//MANTENEDOR COMPETENCIAS
//
//display mantenedor COMPETENCIAS button
        btnArea.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Area");
            areasTable.setItems(areasCtl.getAreasFX());
            //filtrar por nombre o sigla
            FilteredList<AreaO> filteredData = new FilteredList<>(areasCtl.getAreasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filterUser -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (filterUser.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    } else if (filterUser.getSigla().toLowerCase().contains(lowerCaseFilter)) {
                    }
                    return false;
                });
            });
            SortedList<AreaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(areasTable.comparatorProperty());
            areasTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nueva Area");
            crearBtn.setOnAction(ev -> {
                CrearArea caw = new CrearArea();
                if (caw.display()) {
                    areasTable.setItems(areasCtl.getAreasFX());
                    System.out.println("llega la respuesta de creacion de area al main admin");
                }
            });
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, areasTable, bottomBox);
        });
//setting table COMPETENCIAS context menu
        areasTable.setEditable(true);
        areasTable.setRowFactory((TableView<AreaO> tableView) -> {
            final TableRow<AreaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar area id: " + row.getItem().getId());
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                //tableUsers.getItems().remove(row.getItem());
                System.out.println("Desactivar area id: " + row.getItem().getId());
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
//setting COMPETENCIAS table columns data and headers
        areaIdColumn = new TableColumn<>("Id");
        areaIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        areaNombreColumn = new TableColumn<>("Nombre");
        areaNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        areaSiglaColumn = new TableColumn<>("Sigla");
        areaSiglaColumn.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        areaActivoColumn = new TableColumn<>("Activo");
        areaActivoColumn.setCellValueFactory(new PropertyValueFactory<>("activo"));
        areaCreadoColumn = new TableColumn<>("Creado");
        areaCreadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));
        areaModificadoColumn = new TableColumn<>("Modificado");
        areaModificadoColumn.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        areaDesactivadoColumn = new TableColumn<>("Desactivado");
        areaDesactivadoColumn.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load COMPETENCIAS columns
        areasTable.getColumns().addAll(areaIdColumn, areaNombreColumn, areaSiglaColumn, areaActivoColumn,
                areaCreadoColumn, areaModificadoColumn, areaDesactivadoColumn);
        
//
//GENERAL LOAD
//
//load display in center border pane
        bp.setCenter(display);
//setting scene
        Scene admin = new Scene(bp, 1040, 640);
//loading stage scene and style
        primaryStage.setTitle("SEC - Mantenedores");
        primaryStage.setScene(admin);
        admin.getStylesheets().add(Admin.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }

}
