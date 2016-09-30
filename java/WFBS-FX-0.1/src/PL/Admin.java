/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.CompetenciaCTL;
import CTL.NivelCTL;
import CTL.ObservacionCTL;
import CTL.PeriodoCTL;
import CTL.PreguntaCTL;
import CTL.RespuestaCTL;
import CTL.UsuarioCTL;
import O.AreaO;
import O.CompetenciaO;
import O.NivelO;
import O.ObservacionO;
import O.PeriodoO;
import O.PreguntaO;
import O.RespuestaO;
import O.UsuarioO;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
//    private final Text displayTitle = new Text();
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
//variables mantenedor NIVEL //maintainer variables LEVEL
    private final NivelCTL nivelCtl = new NivelCTL();
    private final TableView<NivelO> nivelTable = new TableView<>();
    private TableColumn<NivelO, String> idNivelCol;
    private TableColumn<NivelO, String> nombreNivelCol;
    private TableColumn<NivelO, String> notaNivelCol;
    private TableColumn<NivelO, String> activoNivelCol;
    private TableColumn<NivelO, String> creadoNivelCol;
    private TableColumn<NivelO, String> modificadoNivelCol;
    private TableColumn<NivelO, String> desactivadoNivelCol;
//variables mantenedor PREGUNTA //maintainer variables QUESTION
    private final PreguntaCTL questionCtl = new PreguntaCTL();
    private final TableView<PreguntaO> questionTable = new TableView<>();
    private TableColumn<PreguntaO, String> questionIdCol;
    private TableColumn<PreguntaO, String> questionPreguntaCol;
    private TableColumn<PreguntaO, String> questionCompetenciaCol;
    private TableColumn<PreguntaO, String> questionActivoCol;
    private TableColumn<PreguntaO, String> questionCreadoCol;
    private TableColumn<PreguntaO, String> questionModificadoCol;
    private TableColumn<PreguntaO, String> questionDesactivadoCol;
//variables mantenedor RESPUESTA //maintainer variables ANSWER
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
//variables mantenedor OBSERVACION //maintainer variables COMMENT
    private final ObservacionCTL commentCtl = new ObservacionCTL();
    private final TableView<ObservacionO> commentTable = new TableView<>();
    private TableColumn<ObservacionO, String> commentIdCol;
    private TableColumn<ObservacionO, String> commentNivelInfCol;
    private TableColumn<ObservacionO, String> commentNivelSupCol;
    private TableColumn<ObservacionO, String> commentMsjInfCol;
    private TableColumn<ObservacionO, String> commentMsjSupCol;
    private TableColumn<ObservacionO, String> commentCompetenciaCol;
    private TableColumn<ObservacionO, String> commentActivoCol;
    private TableColumn<ObservacionO, String> commentCreadoCol;
    private TableColumn<ObservacionO, String> commentModificadoCol;
    private TableColumn<ObservacionO, String> commentDesactivadoCol;
//variables mantenedor PERIODO
    private final PeriodoCTL periodoCtl = new PeriodoCTL();
    private final TableView<PeriodoO> periodoTable = new TableView<>();
    private TableColumn<PeriodoO, String> periodoIdCol;
    private TableColumn<PeriodoO, String> periodoInicioCol;
    private TableColumn<PeriodoO, String> periodoFinCol;
    private TableColumn<PeriodoO, String> periodoPorcJefeCol;
    private TableColumn<PeriodoO, String> periodoPorcAutoCol;
    private TableColumn<PeriodoO, String> periodoActivoCol;
    private TableColumn<PeriodoO, String> periodoCreadoCol;
    private TableColumn<PeriodoO, String> periodoModificadoCol;
    private TableColumn<PeriodoO, String> periodoDesactivadoCol;

    @Override
    public void start(Stage primaryStage) {
//
//GENERAL SETTINGS
//
        VBox topOrder = new VBox();
        HBox topBox = new HBox();
        Text titleMantenedores = new Text("SEC - Mantenedores");
        titleMantenedores.getStyleClass().add("title");
        StackPane logOut = new StackPane();
        Hyperlink cerrarSesion = new Hyperlink("Cerrar Sesión");
        cerrarSesion.setBorder(Border.EMPTY);
        cerrarSesion.getStyleClass().add("subtitle");
        cerrarSesion.setOnAction(e -> {
            Login login = new Login();
            login.start(primaryStage);
        });
        logOut.getChildren().add(cerrarSesion);
        logOut.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(logOut, Priority.ALWAYS);
        topBox.getChildren().addAll(titleMantenedores, logOut);
        HBox topButtons = new HBox();
        Button btnUsuario = new Button("Usuarios");
        Button btnArea = new Button("Áreas");
        Button btnCompetencia = new Button("Competencias");
        Button btnNivel = new Button("Niveles");
        Button btnPregunta = new Button("Preguntas");
        Button btnRespuesta = new Button("Respuestas");
        Button btnComment = new Button("Observaciones");
        Button btnPeriodo = new Button("Periodos");
        topButtons.getChildren().addAll(btnUsuario, btnArea, btnCompetencia, btnNivel, btnPregunta, btnRespuesta, btnComment, btnPeriodo);
        topButtons.getStyleClass().add("hboxtop");
        topOrder.getChildren().addAll(topBox, topButtons);
        topOrder.getStyleClass().add("vbox");
        display.getStyleClass().add("vbox");
        BorderPane bp = new BorderPane();
        bp.setTop(topOrder);
//
//MANTENEDOR USUARIOS //USER MAINTAINER
//
//display mantenedor usuario button
        btnUsuario.setOnAction(e -> {
//            displayTitle.setText("Usuario");
            titleMantenedores.setText("SEC - Mantenedor Usuarios");
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
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(usersTable, bottomBox);
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
//MANTENEDOR AREAS //AREAS MAINTAINER
//
//display mantenedor AREAS button
        btnArea.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Áreas");
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
                        return true;
                    } else if (filterUser.getSigla().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<AreaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(areasTable.comparatorProperty());
            areasTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nueva Área");
            crearBtn.setOnAction(ev -> {
                CrearArea caw = new CrearArea();
                if (caw.display()) {
                    areasTable.setItems(areasCtl.getAreasFX());
                }
            });
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(areasTable, bottomBox);
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
//MANTENEDOR COMPETENCIAS //CAPACITY MAINTAINER
//
//display mantenedor COMPETENCIAS button
        btnCompetencia.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Competencias");
            compTable.setItems(compCtl.getCompetenciasFX());
            //filtrar por nombre o sigla
            FilteredList<CompetenciaO> filteredData = new FilteredList<>(compCtl.getCompetenciasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getNombre().toLowerCase().contains(lowerCaseFilter) || filter.getSigla().toLowerCase().contains(lowerCaseFilter)
                            || filter.getDescripcion().toLowerCase().contains(lowerCaseFilter);
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
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(compTable, bottomBox);
        });
//setting table COMPETENCIAS context menu
        compTable.setEditable(true);
        compTable.setRowFactory((TableView<CompetenciaO> tableView) -> {
            final TableRow<CompetenciaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            final MenuItem crearPreguntaMenu = new MenuItem("Crear Pregunta");
            final MenuItem crearCommentMenu = new MenuItem("Crear Observación");
            //modificar
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Competencia Id: " + row.getItem().getId());
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Competencia Id: " + row.getItem().getId());
            });
            crearPreguntaMenu.setOnAction(event -> {
                int id_competencia = row.getItem().getId();
                CrearPregunta cpw = new CrearPregunta();
                if (cpw.display(id_competencia)) {
                    btnPregunta.fire();
                }
            });
            crearCommentMenu.setOnAction(event -> {
                int id_competencia = row.getItem().getId();
                CrearObservacion cow = new CrearObservacion();
                if (cow.display(id_competencia)) {
                    btnComment.fire();
                }
            });
            contextMenu.getItems().addAll(modificarMenuItem, desactivarMenuItem, crearPreguntaMenu, crearCommentMenu);
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
//MANTENEDOR NIVEL //LEVERL MAINTAINER
//
//display mantenedor NIVEL button
        btnNivel.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Niveles");
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
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(nivelTable, bottomBox);
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
//MANTENEDOR PREGUNTA //QUESTION MAINTAINER
//
//display mantenedor PREGUNTA button
        btnPregunta.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Preguntas");
            questionTable.setItems(questionCtl.getPreguntasFX());
            //filtrar por nombre o sigla
            FilteredList<PreguntaO> filteredData = new FilteredList<>(questionCtl.getPreguntasFX(), p -> true);
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
            sortedData.comparatorProperty().bind(questionTable.comparatorProperty());
            questionTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(questionTable, bottomBox);
        });
//setting table PREGUNTA context menu
        questionTable.setEditable(true);
        questionTable.setRowFactory((TableView<PreguntaO> tableView) -> {
            final TableRow<PreguntaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            final MenuItem crearRespuestaMenu = new MenuItem("Crear Respuesta");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Pregunta Id: " + row.getItem().getId());
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Pregunta Id: " + row.getItem().getId());
            });
            //crear respuesta
            crearRespuestaMenu.setOnAction(event -> {
                int id_pregunta = row.getItem().getId();
                CrearRespuesta cpw = new CrearRespuesta();
                if (cpw.display(id_pregunta)) {
                    btnRespuesta.fire();
                }
            });
            contextMenu.getItems().addAll(modificarMenuItem, desactivarMenuItem, crearRespuestaMenu);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting PREGUNTA table columns data and headers
        questionIdCol = new TableColumn<>("Id");
        questionIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        questionPreguntaCol = new TableColumn<>("Pregunta");
        questionPreguntaCol.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
        questionCompetenciaCol = new TableColumn<>("Competencia");
        questionCompetenciaCol.setCellValueFactory(new PropertyValueFactory<>("competencia_id"));
        questionActivoCol = new TableColumn<>("Activo");
        questionActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        questionCreadoCol = new TableColumn<>("Creado");
        questionCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        questionModificadoCol = new TableColumn<>("Modificado");
        questionModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        questionDesactivadoCol = new TableColumn<>("Desactivado");
        questionDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load PREGUNTA columns
        questionTable.getColumns().addAll(questionIdCol, questionPreguntaCol, questionCompetenciaCol, questionActivoCol,
                questionCreadoCol, questionModificadoCol, questionDesactivadoCol);
//
//MANTENEDOR RESPUESTA //ANSWER MAINTAINER
//
//display mantenedor RESPUESTA button
        btnRespuesta.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Respuestas");
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
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(answerTable, bottomBox);
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
//MANTENEDOR OBSERVACIÓN //COMMENT MAINTAINER
//
//display mantenedor COMMENT button
        btnComment.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Observaciones");
            commentTable.setItems(commentCtl.getObservacionesFX());
            //filtrar por nombre o sigla
            FilteredList<ObservacionO> filteredData = new FilteredList<>(commentCtl.getObservacionesFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getMsj_inf().toLowerCase().contains(lowerCaseFilter) || filter.getMsj_sup().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<ObservacionO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(commentTable.comparatorProperty());
            commentTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(commentTable, bottomBox);
        });
//setting table COMMENT context menu
        commentTable.setEditable(true);
        commentTable.setRowFactory((TableView<ObservacionO> tableView) -> {
            final TableRow<ObservacionO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Observacion Id: " + row.getItem().getId());
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Observacion Id: " + row.getItem().getId());
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
//setting COMMENT table columns data and headers
        commentIdCol = new TableColumn<>("Id");
        commentIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        commentNivelInfCol = new TableColumn<>("Nivel Inferior");
        commentNivelInfCol.setCellValueFactory(new PropertyValueFactory<>("nivel_inf"));
        commentNivelSupCol = new TableColumn<>("Nivel Superior");
        commentNivelSupCol.setCellValueFactory(new PropertyValueFactory<>("nivel_sup"));
        commentMsjInfCol = new TableColumn<>("Mensaje Nivel Inferior");
        commentMsjInfCol.setCellValueFactory(new PropertyValueFactory<>("msj_inf"));
        commentMsjSupCol = new TableColumn<>("Mensaje Nivel Superior");
        commentMsjSupCol.setCellValueFactory(new PropertyValueFactory<>("msj_sup"));
        commentCompetenciaCol = new TableColumn<>("Competencia");
        commentCompetenciaCol.setCellValueFactory(new PropertyValueFactory<>("competencia_id"));
        commentActivoCol = new TableColumn<>("Activo");
        commentActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        commentCreadoCol = new TableColumn<>("Creado");
        commentCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        commentModificadoCol = new TableColumn<>("Modificado");
        commentModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        commentDesactivadoCol = new TableColumn<>("Desactivado");
        commentDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load COMMENT columns
        commentTable.getColumns().addAll(commentIdCol, commentNivelInfCol, commentNivelSupCol, commentMsjInfCol, commentMsjSupCol,
                commentCompetenciaCol, commentActivoCol, commentCreadoCol, commentModificadoCol, commentDesactivadoCol);
//
//MANTENEDOR PERIODO
//
//display mantenedor PERIODO button
        btnPeriodo.setOnAction(e -> {
            titleMantenedores.setText("SEC - Mantenedor Periodos");
            periodoTable.setItems(periodoCtl.getPeriodosFX());
            //filtrar por nombre o sigla
            FilteredList<PeriodoO> filteredData = new FilteredList<>(periodoCtl.getPeriodosFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return false;
                });
            });
            SortedList<PeriodoO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(periodoTable.comparatorProperty());
            periodoTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            crearBtn.setText("Crear Nuevo Periodo");
            crearBtn.setOnAction(ev -> {
                CrearPeriodo cpw = new CrearPeriodo();
                if (cpw.display()) {
                    periodoTable.setItems(periodoCtl.getPeriodosFX());
                }
            });
            bottomBox.getStyleClass().add("hbox");
            bottomBox.getChildren().clear();
            bottomBox.getChildren().addAll(filterLabel, filterField, crearBtn);
            //load vbox display
            display.getChildren().clear();
            display.getChildren().addAll(periodoTable, bottomBox);
        });
//setting table PERIODO context menu
        periodoTable.setEditable(true);
        periodoTable.setRowFactory((TableView<PeriodoO> tableView) -> {
            final TableRow<PeriodoO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                System.out.println("Modificar Periodo Id: " + row.getItem().getId());
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                System.out.println("Desactivar Periodo Id: " + row.getItem().getId());
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
//setting PERIODO table columns data and headers
        periodoIdCol = new TableColumn<>("Id");
        periodoIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        periodoInicioCol = new TableColumn<>("Fecha Inicio");
        periodoInicioCol.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        periodoFinCol = new TableColumn<>("Fecha Final");
        periodoFinCol.setCellValueFactory(new PropertyValueFactory<>("fin"));
        periodoPorcJefeCol = new TableColumn<>("Porcentaje Evaluación Jefe");
        periodoPorcJefeCol.setCellValueFactory(new PropertyValueFactory<>("jefe_porc"));
        periodoPorcAutoCol = new TableColumn<>("Porcentaje Autoevaluación");
        periodoPorcAutoCol.setCellValueFactory(new PropertyValueFactory<>("auto_porc"));
        periodoActivoCol = new TableColumn<>("Activo");
        periodoActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        periodoCreadoCol = new TableColumn<>("Creado");
        periodoCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        periodoModificadoCol = new TableColumn<>("Modificado");
        periodoModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        periodoDesactivadoCol = new TableColumn<>("Desactivado");
        periodoDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load PERIODO columns
        periodoTable.getColumns().addAll(periodoIdCol, periodoInicioCol, periodoFinCol, periodoPorcJefeCol, periodoPorcAutoCol,
                periodoActivoCol, periodoCreadoCol, periodoModificadoCol, periodoDesactivadoCol);

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
