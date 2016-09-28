/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.CompetenciaCTL;
import CTL.NivelCTL;
import CTL.PreguntaCTL;
import CTL.RespuestaCTL;
import CTL.UsuarioCTL;
import O.AreaO;
import O.CompetenciaO;
import O.NivelO;
import O.PreguntaO;
import O.RespuestaO;
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
//variables mantenedor COMPETENCIAS
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final TableView<CompetenciaO> compTable = new TableView<>();
    private TableColumn<CompetenciaO, String> compIdCol;
    private TableColumn<CompetenciaO, String> compNombreCol;
    private TableColumn<CompetenciaO, String> compDescCol;
    private TableColumn<CompetenciaO, String> compSiglaCol;
    private TableColumn<CompetenciaO, String> compActivoCol;
    private TableColumn<CompetenciaO, String> compCreadoCol;
    private TableColumn<CompetenciaO, String> compModificadoCol;
    private TableColumn<CompetenciaO, String> compDesactivadoCol;
//variables mantenedor NIVEL //system manager variables LEVEL
    private final NivelCTL nivelCtl = new NivelCTL();
    private final TableView<NivelO> nivelTable = new TableView<>();
    private TableColumn<NivelO, String> idNivelCol;
    private TableColumn<NivelO, String> nombreNivelCol;
    private TableColumn<NivelO, String> notaNivelCol;
    private TableColumn<NivelO, String> activoNivelCol;
    private TableColumn<NivelO, String> creadoNivelCol;
    private TableColumn<NivelO, String> modificadoNivelCol;
    private TableColumn<NivelO, String> desactivadoNivelCol;
//variables mantenedor PREGUNTA //system manager variables QUESTION
    private final PreguntaCTL preguntaCtl = new PreguntaCTL();
    private final TableView<PreguntaO> preguntaTable = new TableView<>();
    private TableColumn<PreguntaO, String> qstIdCol;
    private TableColumn<PreguntaO, String> qstPreguntaCol;
    private TableColumn<PreguntaO, String> qstCompetenciaCol;
    private TableColumn<PreguntaO, String> qstActivoCol;
    private TableColumn<PreguntaO, String> qstCreadoCol;
    private TableColumn<PreguntaO, String> qstModificadoCol;
    private TableColumn<PreguntaO, String> qstDesactivadoCol;
//variables mantenedor RESPUESTA //system manager variables ANSWER
    private final RespuestaCTL answerCtl = new RespuestaCTL();
    private final TableView<RespuestaO> answerTable = new TableView<>();
    private TableColumn<RespuestaO, String> answerIdCol;
    private TableColumn<RespuestaO, String> answerRespuestaCol;
    private TableColumn<RespuestaO, String> answerPuntosCol;
    private TableColumn<RespuestaO, String> answerPreguntaCol;
    private TableColumn<RespuestaO, String> answerActivoCol;
    private TableColumn<RespuestaO, String> answerCreadoCol;
    private TableColumn<RespuestaO, String> answerModificadoCol;
    private TableColumn<RespuestaO, String> answerDesactivadoCol;

    @Override
    public void start(Stage primaryStage) {
//
//GENERAL SETTINGS
//
        HBox topMenu = new HBox();
        Button btnUsuario = new Button("Mantenedor Usuario");
        Button btnArea = new Button("Mantenedor Area");
        Button btnCompetencia = new Button("Mantenedor Competencia");
        Button btnNivel = new Button("Mantenedor Nivel");
        Button btnPregunta = new Button("Mantenedor Pregunta");
        Button btnRespuesta = new Button("Mantenedor Respuesta");
        topMenu.getChildren().addAll(btnUsuario, btnArea, btnCompetencia, btnNivel, btnPregunta, btnRespuesta);
        topMenu.getStyleClass().add("hbox");
        displayTitle.getStyleClass().add("title");
        display.getStyleClass().add("vbox");
        BorderPane bp = new BorderPane();
        bp.setTop(topMenu);
//
//MANTENEDOR USUARIOS //Users's System Manager
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
        btnCompetencia.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Competencia");
            compTable.setItems(compCtl.getCompetenciasFX());
            //filtrar por nombre o sigla
            FilteredList<CompetenciaO> filteredData = new FilteredList<>(compCtl.getCompetenciasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (filter.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (filter.getSigla().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (filter.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<CompetenciaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(compTable.comparatorProperty());
            compTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nueva Competencia");
            crearBtn.setOnAction(ev -> {
                CrearCompetencia ccw = new CrearCompetencia();
                if (ccw.display()) {
                    compTable.setItems(compCtl.getCompetenciasFX());
                }
            });
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, compTable, bottomBox);
        });
//setting table COMPETENCIAS context menu
        compTable.setEditable(true);
        compTable.setRowFactory((TableView<CompetenciaO> tableView) -> {
            final TableRow<CompetenciaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Desactivar");
            final MenuItem crearPreguntaMenu = new MenuItem("Crear Pregunta");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Competencia Id: " + row.getItem().getId());
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Competencia Id: " + row.getItem().getId());
            });
            crearPreguntaMenu.setOnAction(event -> {
                int id_competencia = row.getItem().getId();
                CrearPregunta cpw = new CrearPregunta();
                if (cpw.display(id_competencia)) {
                    btnPregunta.fire();
                }
            });
            contextMenu.getItems().addAll(modificarMenuItem, eliminarMenuItem, crearPreguntaMenu);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting COMPETENCIAS table columns data and headers
        compIdCol = new TableColumn<>("Id");
        compIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        compNombreCol = new TableColumn<>("Nombre");
        compNombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        compSiglaCol = new TableColumn<>("Sigla");
        compSiglaCol.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        compDescCol = new TableColumn<>("Descripción");
        compDescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        compActivoCol = new TableColumn<>("Activo");
        compActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        compCreadoCol = new TableColumn<>("Creado");
        compCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        compModificadoCol = new TableColumn<>("Modificado");
        compModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        compDesactivadoCol = new TableColumn<>("Desactivado");
        compDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load COMPETENCIAS columns
        compTable.getColumns().addAll(compIdCol, compNombreCol, compSiglaCol, compDescCol, compActivoCol,
                compCreadoCol, compModificadoCol, compDesactivadoCol);
//
//MANTENEDOR NIVEL
//
//display mantenedor NIVEL button
        btnNivel.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Nivel");
            nivelTable.setItems(nivelCtl.getNivelesFX());
            //filtrar por nombre o sigla
            FilteredList<NivelO> filteredData = new FilteredList<>(nivelCtl.getNivelesFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getNombre().toLowerCase().contains(lowerCaseFilter);
                    //agregar getNota() : integer.parseint
                });
            });
            SortedList<NivelO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(nivelTable.comparatorProperty());
            nivelTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nuevo Nivel");
            crearBtn.setOnAction(ev -> {
                CrearNivel cnw = new CrearNivel();
                if (cnw.display()) {
                    nivelTable.setItems(nivelCtl.getNivelesFX());
                }
            });
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, nivelTable, bottomBox);
        });
//setting table NIVEL context menu
        nivelTable.setEditable(true);
        nivelTable.setRowFactory((TableView<NivelO> tableView) -> {
            final TableRow<NivelO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Nivel Id: " + row.getItem().getId());
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Nivel Id: " + row.getItem().getId());
            });
            contextMenu.getItems().add(modificarMenuItem);
            contextMenu.getItems().add(eliminarMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting NIVEL table columns data and headers
        idNivelCol = new TableColumn<>("Id");
        idNivelCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreNivelCol = new TableColumn<>("Nombre");
        nombreNivelCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        notaNivelCol = new TableColumn<>("Nota");
        notaNivelCol.setCellValueFactory(new PropertyValueFactory<>("nota"));
        activoNivelCol = new TableColumn<>("Activo");
        activoNivelCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        creadoNivelCol = new TableColumn<>("Creado");
        creadoNivelCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        modificadoNivelCol = new TableColumn<>("Modificado");
        modificadoNivelCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        desactivadoNivelCol = new TableColumn<>("Desactivado");
        desactivadoNivelCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load NIVEL columns
        nivelTable.getColumns().addAll(idNivelCol, nombreNivelCol, notaNivelCol, activoNivelCol, creadoNivelCol, modificadoNivelCol, desactivadoNivelCol);

//
//MANTENEDOR PREGUNTA
//
//display mantenedor PREGUNTA button
        btnPregunta.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Pregunta");
            preguntaTable.setItems(preguntaCtl.getPreguntasFX());
            //filtrar por nombre o sigla
            FilteredList<PreguntaO> filteredData = new FilteredList<>(preguntaCtl.getPreguntasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getPregunta().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<PreguntaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(preguntaTable.comparatorProperty());
            preguntaTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, preguntaTable, bottomBox);
        });
//setting table PREGUNTA context menu
        preguntaTable.setEditable(true);
        preguntaTable.setRowFactory((TableView<PreguntaO> tableView) -> {
            final TableRow<PreguntaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Pregunta Id: " + row.getItem().getId());
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Pregunta Id: " + row.getItem().getId());
            });
            contextMenu.getItems().add(modificarMenuItem);
            contextMenu.getItems().add(desactivarMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting PREGUNTA table columns data and headers
        qstIdCol = new TableColumn<>("Id");
        qstIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        qstPreguntaCol = new TableColumn<>("Pregunta");
        qstPreguntaCol.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
        qstCompetenciaCol = new TableColumn<>("Competencia");
        qstCompetenciaCol.setCellValueFactory(new PropertyValueFactory<>("competencia_id"));
        qstActivoCol = new TableColumn<>("Activo");
        qstActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        qstCreadoCol = new TableColumn<>("Creado");
        qstCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        qstModificadoCol = new TableColumn<>("Modificado");
        qstModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        qstDesactivadoCol = new TableColumn<>("Desactivado");
        qstDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load PREGUNTA columns
        preguntaTable.getColumns().addAll(qstIdCol, qstPreguntaCol, qstCompetenciaCol, qstActivoCol, qstCreadoCol, qstModificadoCol, qstDesactivadoCol);

//
//MANTENEDOR RESPUESTA
//
//display mantenedor RESPUESTA button
        btnRespuesta.setOnAction(e -> {
            displayTitle.setText("SEC - Mantenedor Respuesta");
            answerTable.setItems(answerCtl.getRespuestasFX());
            //filtrar por nombre o sigla
            FilteredList<RespuestaO> filteredData = new FilteredList<>(answerCtl.getRespuestasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getRespuesta().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<RespuestaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(answerTable.comparatorProperty());
            answerTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            bottomBox.getStyleClass().add("vbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(displayTitle, answerTable, bottomBox);
        });
//setting table RESPUESTA context menu
        answerTable.setEditable(true);
        answerTable.setRowFactory((TableView<RespuestaO> tableView) -> {
            final TableRow<RespuestaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Respuesta Id: " + row.getItem().getId());
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Respuesta Id: " + row.getItem().getId());
            });
            contextMenu.getItems().add(modificarMenuItem);
            contextMenu.getItems().add(desactivarMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting RESPUESTA table columns data and headers
        answerIdCol = new TableColumn<>("Id");
        answerIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        answerRespuestaCol = new TableColumn<>("Respuesta");
        answerRespuestaCol.setCellValueFactory(new PropertyValueFactory<>("respuesta"));
        answerPuntosCol = new TableColumn<>("Puntos");
        answerPuntosCol.setCellValueFactory(new PropertyValueFactory<>("puntos"));
        answerPreguntaCol = new TableColumn<>("Pregunta");
        answerPreguntaCol.setCellValueFactory(new PropertyValueFactory<>("pregunta_id"));
        answerActivoCol = new TableColumn<>("Activo");
        answerActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        answerCreadoCol = new TableColumn<>("Creado");
        answerCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        answerModificadoCol = new TableColumn<>("Modificado");
        answerModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        answerDesactivadoCol = new TableColumn<>("Desactivado");
        answerDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load RESPUESTA columns
        answerTable.getColumns().addAll(answerIdCol, answerRespuestaCol, answerPuntosCol, answerPreguntaCol, answerActivoCol,
                answerCreadoCol, answerModificadoCol, answerDesactivadoCol);

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
