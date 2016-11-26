/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.AreaCTL;
import CTL.CompetenciaCTL;
import CTL.EvaluacionCTL;
import CTL.NivelCTL;
import CTL.ObservacionCTL;
import CTL.PeriodoCTL;
import CTL.PreguntaCTL;
import CTL.RespuestaCTL;
import CTL.UsuarioCTL;
import DAL.VariablesDAL;
import FN.Excel;
import FN.Formato;
import O.AreaO;
import O.CompetenciaO;
import O.EvaluacionO;
import O.NivelO;
import O.ObservacionO;
import O.PeriodoO;
import O.PreguntaO;
import O.RespuestaO;
import O.UsuarioO;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import static javafx.scene.input.KeyCode.F11;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author iosep
 */
public class Admin {

//variables reutilizables
    private final VBox vbDisplay = new VBox();
    private final TextField filterField = new TextField();
    private final Label filterLabel = new Label();
    private final HBox hbBottomBox = new HBox();
    private final Button btCrear = new Button();
    private final Tooltip tooltipFilter = new Tooltip();
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//variables mantenedor usuarios
    private final UsuarioCTL usersCtl = new UsuarioCTL();
    private final TableView<UsuarioO> usersTable = new TableView<>();
    private TableColumn<UsuarioO, String> rutColumn;
    private TableColumn<UsuarioO, String> nombreColumn;
    private TableColumn<UsuarioO, String> apellidoColumn;
    private TableColumn<UsuarioO, String> fonoColumn;
    private TableColumn<UsuarioO, String> emailColumn;
    private TableColumn<UsuarioO, String> sexoColumn;
    private TableColumn<UsuarioO, Integer> rolColumn;
    private TableColumn<UsuarioO, String> jefeColumn;
    private TableColumn<UsuarioO, Date> creadoColumn;
    private TableColumn<UsuarioO, Date> modificadoColumn;
//variables mantenedor areas
    private final AreaCTL areasCtl = new AreaCTL();
    private final TableView<AreaO> areasTable = new TableView<>();
    private TableColumn<AreaO, String> areaNombreColumn;
    private TableColumn<AreaO, String> areaSiglaColumn;
    private TableColumn<AreaO, String> areaDescColumn;
    private TableColumn<AreaO, Integer> areaActivoColumn;
    private TableColumn<AreaO, Date> areaCreadoColumn;
    private TableColumn<AreaO, Date> areaModificadoColumn;
    private TableColumn<AreaO, Date> areaDesactivadoColumn;
//variables mantenedor COMPETENCIAS
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final TableView<CompetenciaO> compTable = new TableView<>();
    private TableColumn<CompetenciaO, String> compNombreCol;
    private TableColumn<CompetenciaO, String> compDescCol;
    private TableColumn<CompetenciaO, String> compSiglaCol;
    private TableColumn<CompetenciaO, String> compNiOpCol;
    private TableColumn<CompetenciaO, Integer> compActivoCol;
    private TableColumn<CompetenciaO, Date> compCreadoCol;
    private TableColumn<CompetenciaO, Date> compModificadoCol;
    private TableColumn<CompetenciaO, Date> compDesactivadoCol;
//variables mantenedor NIVEL //maintainer variables LEVEL
    private final NivelCTL nivelCtl = new NivelCTL();
    private final TableView<NivelO> nivelTable = new TableView<>();
    private TableColumn<NivelO, String> nombreNivelCol;
    private TableColumn<NivelO, String> notaNivelCol;
    private TableColumn<NivelO, String> descNivelCol;
    private TableColumn<NivelO, Date> creadoNivelCol;
    private TableColumn<NivelO, Date> modificadoNivelCol;
//variables mantenedor PREGUNTA //maintainer variables QUESTION
    private final PreguntaCTL questionCtl = new PreguntaCTL();
    private final TableView<PreguntaO> questionTable = new TableView<>();
    private TableColumn<PreguntaO, String> questionPreguntaCol;
    private TableColumn<PreguntaO, Integer> questionCompetenciaCol;
    private TableColumn<PreguntaO, Date> questionCreadoCol;
    private TableColumn<PreguntaO, Date> questionModificadoCol;
//variables mantenedor RESPUESTA //maintainer variables ANSWER
    private final RespuestaCTL answerCtl = new RespuestaCTL();
    private final TableView<RespuestaO> answerTable = new TableView<>();
    private TableColumn<RespuestaO, String> answerRespuestaCol;
    private TableColumn<RespuestaO, String> answerPuntosCol;
    private TableColumn<RespuestaO, Integer> answerPreguntaCol;
    private TableColumn<RespuestaO, Date> answerCreadoCol;
    private TableColumn<RespuestaO, Date> answerModificadoCol;
//variables mantenedor OBSERVACION //maintainer variables COMMENT
    private final ObservacionCTL commentCtl = new ObservacionCTL();
    private final TableView<ObservacionO> commentTable = new TableView<>();
    private TableColumn<ObservacionO, String> commentNivelInfCol;
    private TableColumn<ObservacionO, String> commentNivelSupCol;
    private TableColumn<ObservacionO, String> commentMsjInfCol;
    private TableColumn<ObservacionO, String> commentMsjSupCol;
    private TableColumn<ObservacionO, Integer> commentCompetenciaCol;
    private TableColumn<ObservacionO, Date> commentCreadoCol;
    private TableColumn<ObservacionO, Date> commentModificadoCol;
//variables mantenedor PERIODO
    private final PeriodoCTL periodoCtl = new PeriodoCTL();
    private final TableView<PeriodoO> periodoTable = new TableView<>();
    private TableColumn<PeriodoO, Date> periodoInicioCol;
    private TableColumn<PeriodoO, Date> periodoFinCol;
    private TableColumn<PeriodoO, String> periodoPorcJefeCol;
    private TableColumn<PeriodoO, String> periodoPorcAutoCol;
    private TableColumn<PeriodoO, Integer> periodoActivoCol;
    private TableColumn<PeriodoO, Date> periodoCreadoCol;
    private TableColumn<PeriodoO, Date> periodoModificadoCol;
    private TableColumn<PeriodoO, Date> periodoDesactivadoCol;
//variables REPORTE final
    private final EvaluacionCTL evaluacionCtl = new EvaluacionCTL();
    String per = "";
    int perIdRep = 0;
    Button bExportarExcel = new Button("Exportar a Excel");
//stage    
    Stage primaryStage = new Stage();

    /**
     * starts primary stage ADMIN
     *
     * @param logRut
     */
    public void display(String logRut) {
//
//GENERAL SETTINGS
//
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        Text titleMantenedores = new Text("SEC - Administrador");
        titleMantenedores.getStyleClass().add("title");
        Label lblActiveUser = new Label("Usuario Activo:");
        Label lblLogRutUser = new Label("  " + logRut);
        Hyperlink hlCerrarSesion = new Hyperlink("Cerrar Sesión");
        hlCerrarSesion.setBorder(Border.EMPTY);
        hlCerrarSesion.setOnAction(e -> {
            Login login = new Login();
            login.start(primaryStage);
        });
        GridPane gridSesion = new GridPane();
        gridSesion.setAlignment(Pos.TOP_RIGHT);
        gridSesion.add(lblActiveUser, 0, 0);
        gridSesion.add(lblLogRutUser, 1, 0);
        gridSesion.add(hlCerrarSesion, 1, 1);
        gridSesion.getStyleClass().add("logout");
        HBox hbTopBox = new HBox();
        hbTopBox.getChildren().addAll(titleMantenedores, gridSesion);
        HBox.setHgrow(gridSesion, Priority.ALWAYS);
//botones
        HBox hbTopButtons = new HBox();
        Button btnUsuario = new Button("Usuarios");
        Button btnArea = new Button("Áreas");
        Button btnCompetencia = new Button("Competencias");
        Button btnNivel = new Button("Niveles");
        Button btnPregunta = new Button("Preguntas");
        Button btnRespuesta = new Button("Respuestas");
        Button btnComment = new Button("Observaciones");
        Button btnPeriodo = new Button("Periodos");
        Button btnReportes = new Button("Reportes");
        Arrays.asList(btnUsuario, btnArea, btnCompetencia, btnNivel, btnPregunta, btnRespuesta, btnComment, btnPeriodo, btnReportes).stream().forEach((btn) -> {
            btn.getStyleClass().add("topbutton");
        });
        hbTopButtons.getChildren().addAll(btnUsuario, btnArea, btnCompetencia, btnNivel, btnPregunta, btnRespuesta, btnComment, btnPeriodo, btnReportes);
        hbTopButtons.getStyleClass().add("hboxtop");
        VBox vbTopOrder = new VBox();
        vbTopOrder.getChildren().addAll(hbTopBox, hbTopButtons);
        vbTopOrder.getStyleClass().add("vbox");
        vbDisplay.getStyleClass().add("vbox");
        BorderPane bp = new BorderPane();
        bp.setTop(vbTopOrder);
        HBox hbCopyright = new HBox();
        hbCopyright.getStyleClass().add("hboxbottom");
        Text tCopyright = new Text("©copyright @iosep (José Oñate)");
        tCopyright.setFill(Color.DARKCYAN);
        hbCopyright.getChildren().addAll(tCopyright);
        hbCopyright.setAlignment(Pos.CENTER);
        bp.setBottom(hbCopyright);
        hbBottomBox.setPrefHeight(130);
        btCrear.setAlignment(Pos.TOP_RIGHT);
        //btCrear.getStyleClass().add("slimbutton");
//
//MANTENEDOR USUARIOS //USER MAINTAINER
//
//display mantenedor usuario button
        btnUsuario.setOnAction(e -> {
            filterField.clear();
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
                        return true; // Filter matches rut
                    } else if (filterUser.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
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
            btCrear.setText("Crear Nuevo Usuario");
            btCrear.setOnAction(ev -> {
                CrearUsuario cuw = new CrearUsuario();
                cuw.display();
                btnUsuario.fire();
            });
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Rut, Nombre o Apellido");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField, btCrear);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(usersTable, hbBottomBox);
        });
//setting table users context menu
        usersTable.setEditable(true);
        usersTable.setRowFactory((TableView<UsuarioO> tableView) -> {
            final TableRow<UsuarioO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Eliminar");
            final MenuItem agregarAreaMenu = new MenuItem("Ver Áreas");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarUsuario mu = new ModificarUsuario();
                mu.display(row.getItem().getId());
                btnUsuario.fire();
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                if (row.getItem().getId() == VariablesDAL.getIdUsuario()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("No se permite Auto-Eliminación");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Eliminar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Eliminar Usuario " + row.getItem().getRut());
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        usersCtl.deleteUser(row.getItem().getId());
                        btnUsuario.fire();
                    }
                }
            });
            agregarAreaMenu.setOnAction(ev -> {
                if (row.getItem().getRolid() == 3) {
                    CrearUsuarioArea cuaw = new CrearUsuarioArea();
                    cuaw.display(row.getItem().getRut());
                    btnUsuario.fire();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Sólo Funcionarios tienen Áreas asociadas");
                    alert.showAndWait();
                }
            });
            contextMenu.getItems().addAll(modificarMenuItem, eliminarMenuItem, new SeparatorMenuItem(), agregarAreaMenu);
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
        sexoColumn.setCellFactory(column -> {
            return new TableCell<UsuarioO, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else if (item.equals("M")) {
                        setText("MASCULINO");
                    } else if (item.equals("F")) {
                        setText("FEMENINO");
                    }
                }
            };
        });
        rolColumn = new TableColumn<>("Rol");
        rolColumn.setCellValueFactory(new PropertyValueFactory<>("rol_nombre"));
        jefeColumn = new TableColumn<>("Jefe");
        jefeColumn.setCellValueFactory(new PropertyValueFactory<>("rutjefe"));
        creadoColumn = new TableColumn<>("Creado");
        creadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));
        creadoColumn.setCellFactory(column -> {
            return new TableCell<UsuarioO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        modificadoColumn = new TableColumn<>("Modificado");
        modificadoColumn.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        modificadoColumn.setCellFactory(column -> {
            return new TableCell<UsuarioO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        //desactivadoColumn = new TableColumn<>("Desactivado");
        //desactivadoColumn.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load USERS columns
        usersTable.getColumns().addAll(rutColumn, nombreColumn, apellidoColumn, fonoColumn, emailColumn, sexoColumn,
                rolColumn, jefeColumn, creadoColumn, modificadoColumn);
//
//MANTENEDOR AREAS //AREAS MAINTAINER
//
//display mantenedor AREAS button
        btnArea.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Áreas");
            areasTable.setItems(areasCtl.getAreasFX());
            //filtrar por nombre o sigla
            FilteredList<AreaO> filteredData = new FilteredList<>(areasCtl.getAreasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filterArea -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    return filterArea.getNombre().toLowerCase().contains(newValue.toLowerCase())
                            || filterArea.getSigla().toLowerCase().contains(newValue.toLowerCase());
                });
            });
            SortedList<AreaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(areasTable.comparatorProperty());
            areasTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            btCrear.setText("Crear Nueva Área");
            btCrear.setOnAction(ev -> {
                CrearArea caw = new CrearArea();
                caw.display();
                btnArea.fire();
            });
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Nombre o Sigla");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField, btCrear);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(areasTable, hbBottomBox);
        });
//setting table AREAS context menu
        areasTable.setEditable(true);
        areasTable.setRowFactory((TableView<AreaO> tableView) -> {
            final TableRow<AreaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            final MenuItem activarMenuItem = new MenuItem("Activar");
            final MenuItem verCompetenciasMenu = new MenuItem("Ver Competencias");
            final MenuItem verUsuariosMenu = new MenuItem("Ver Usuarios");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarArea ma = new ModificarArea();
                ma.display(row.getItem().getId());
                btnArea.fire();
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                if (row.getItem().getActivo() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Área ya se encuentra desactivada");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Desactivar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Desactivar?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        areasCtl.desactivarArea(row.getItem().getId());
                        btnArea.fire();
                    }
                }
            });
            activarMenuItem.setOnAction(event -> {
                if (row.getItem().getActivo() == 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Activar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Activar?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        areasCtl.activaArea(row.getItem().getId());
                        btnArea.fire();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Área ya se encuentra activada");
                    alert.showAndWait();
                }
            });
            verCompetenciasMenu.setOnAction(ee -> {
                CrearAreaCompetencia acw = new CrearAreaCompetencia();
                acw.display(row.getItem().getId());
                btnArea.fire();
            });
            verUsuariosMenu.setOnAction(ee -> {
                CrearAreaUsuario auw = new CrearAreaUsuario();
                auw.display(row.getItem().getId());
                btnArea.fire();
            });
            contextMenu.getItems().addAll(activarMenuItem, modificarMenuItem, desactivarMenuItem, new SeparatorMenuItem(), verCompetenciasMenu, verUsuariosMenu);
            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting AREAS table columns data and headers
        //areaIdColumn = new TableColumn<>("Id");
        //areaIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        areaNombreColumn = new TableColumn<>("Nombre");
        areaNombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        areaSiglaColumn = new TableColumn<>("Sigla");
        areaSiglaColumn.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        areaDescColumn = new TableColumn<>("Descripción");
        areaDescColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        areaDescColumn.setMaxWidth(250);
        areaActivoColumn = new TableColumn<>("Estado");
        areaActivoColumn.setCellValueFactory(new PropertyValueFactory<>("activo"));
        areaActivoColumn.setCellFactory(column -> {
            return new TableCell<AreaO, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        switch (item) {
                            case 0:
                                setText("INACTIVO");
                                break;
                            case 1:
                                setText("ACTIVO");
                                break;
                        }
                    }
                }
            };
        });
        areaCreadoColumn = new TableColumn<>("Creado");
        areaCreadoColumn.setCellValueFactory(new PropertyValueFactory<>("creado"));
        areaCreadoColumn.setCellFactory(column -> {
            return new TableCell<AreaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        areaModificadoColumn = new TableColumn<>("Modificado");
        areaModificadoColumn.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        areaModificadoColumn.setCellFactory(column -> {
            return new TableCell<AreaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        areaDesactivadoColumn = new TableColumn<>("Desactivado");
        areaDesactivadoColumn.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
        areaDesactivadoColumn.setCellFactory(column -> {
            return new TableCell<AreaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
//load AREAS columns
        areasTable.getColumns().addAll(areaNombreColumn, areaSiglaColumn, areaDescColumn, areaActivoColumn,
                areaCreadoColumn, areaModificadoColumn, areaDesactivadoColumn);
//
//MANTENEDOR COMPETENCIAS //CAPACITY MAINTAINER
//
//display mantenedor COMPETENCIAS button
        btnCompetencia.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Competencias");
            compTable.setItems(compCtl.getCompetenciasFX());
            //filtrar por nombre o sigla o descripción
            FilteredList<CompetenciaO> filteredData = new FilteredList<>(compCtl.getCompetenciasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getNombre().toLowerCase().contains(lowerCaseFilter)
                            || filter.getSigla().toLowerCase().contains(lowerCaseFilter)
                            || filter.getDescripcion().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<CompetenciaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(compTable.comparatorProperty());
            compTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            btCrear.setText("Crear Nueva Competencia");
            btCrear.setOnAction(ev -> {
                CrearCompetencia ccw = new CrearCompetencia();
                ccw.display();
                btnCompetencia.fire();
            });
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Nombre, Sigla o Descripción");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField, btCrear);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(compTable, hbBottomBox);
        });
//setting table COMPETENCIAS context menu
        compTable.setEditable(true);
        compTable.setRowFactory((TableView<CompetenciaO> tableView) -> {
            final TableRow<CompetenciaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            final MenuItem activarMenuItem = new MenuItem("Activar");
            final MenuItem crearPreguntaMenu = new MenuItem("Crear Pregunta");
            final MenuItem crearCommentMenu = new MenuItem("Crear Observación");
            final MenuItem verAreasMenu = new MenuItem("Ver Áreas");
            final MenuItem verNivelMenu = new MenuItem("Ver Niveles");
            //modificar
            modificarMenuItem.setOnAction(event -> {
                ModificarCompetencia mc = new ModificarCompetencia();
                mc.display(row.getItem().getId());
                btnCompetencia.fire();
            });
            //desactivar
            activarMenuItem.setOnAction(v -> {
                if (row.getItem().getActivo() == 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Activar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Activar?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        compCtl.activarCompetencia(row.getItem().getId());
                        btnCompetencia.fire();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Competencia ya se encuentra Activada");
                    alert.showAndWait();
                }
            });
            desactivarMenuItem.setOnAction(event -> {
                if (row.getItem().getActivo() == 1) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Desactivar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Desactivar?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        compCtl.desactivarCompetencia(row.getItem().getId());
                        btnCompetencia.fire();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Competencia ya se encuentra Desactivada");
                    alert.showAndWait();
                }
            });
            crearPreguntaMenu.setOnAction(event -> {
                int id_competencia = row.getItem().getId();
                CrearPregunta cpw = new CrearPregunta();
                cpw.display(id_competencia);
                btnPregunta.fire();
            });
            crearCommentMenu.setOnAction(event -> {
                int id_competencia = row.getItem().getId();
                CrearObservacion cow = new CrearObservacion();
                cow.display(id_competencia);
                btnComment.fire();
            });
            verAreasMenu.setOnAction(ev -> {
                int id_competencia = row.getItem().getId();
                CrearCompetenciaArea ccaw = new CrearCompetenciaArea();
                ccaw.display(id_competencia);
            });
            verNivelMenu.setOnAction(ev -> {
                CrearCompetenciaNivel ccnw = new CrearCompetenciaNivel();
                ccnw.display(row.getItem().getId());
            });
            contextMenu.getItems().addAll(activarMenuItem, modificarMenuItem, desactivarMenuItem, new SeparatorMenuItem(), crearPreguntaMenu, crearCommentMenu,
                    new SeparatorMenuItem(), verAreasMenu, verNivelMenu);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting COMPETENCIAS table columns data and headers
        //compIdCol = new TableColumn<>("Id");
        //compIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        compNombreCol = new TableColumn<>("Nombre");
        compNombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        compSiglaCol = new TableColumn<>("Sigla");
        compSiglaCol.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        compDescCol = new TableColumn<>("Descripción");
        compDescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        compDescCol.setMaxWidth(250);
        compNiOpCol = new TableColumn<>("Nivel Óptimo");
        compNiOpCol.setCellValueFactory(new PropertyValueFactory<>("nivelOptimo"));
        compActivoCol = new TableColumn<>("Estado");
        compActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        compActivoCol.setCellFactory(column -> {
            return new TableCell<CompetenciaO, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        switch (item) {
                            case 0:
                                setText("INACTIVO");
                                break;
                            case 1:
                                setText("ACTIVO");
                                break;
                            default:
                                setText("");
                                break;
                        }
                    }
                }
            };
        });
        compCreadoCol = new TableColumn<>("Creado");
        compCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        compCreadoCol.setCellFactory(column -> {
            return new TableCell<CompetenciaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        compModificadoCol = new TableColumn<>("Modificado");
        compModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        compModificadoCol.setCellFactory(column -> {
            return new TableCell<CompetenciaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        compDesactivadoCol = new TableColumn<>("Desactivado");
        compDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
        compDesactivadoCol.setCellFactory(column -> {
            return new TableCell<CompetenciaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
//load COMPETENCIAS columns
        compTable.getColumns().addAll(compNombreCol, compSiglaCol, compDescCol, compNiOpCol, compActivoCol,
                compCreadoCol, compModificadoCol, compDesactivadoCol);
//
//MANTENEDOR NIVEL //LEVEL MAINTAINER
//
//display mantenedor NIVEL button
        btnNivel.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Niveles");
            nivelTable.setItems(nivelCtl.getNivelesFX());
            //filtrar por nombre, nota o descripcion
            FilteredList<NivelO> filteredData = new FilteredList<>(nivelCtl.getNivelesFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String nota = "" + filter.getNota();
                    return filter.getNombre().toLowerCase().contains(newValue.toLowerCase())
                            || nota.contains(newValue) || filter.getDescripcion().toLowerCase().contains(newValue.toLowerCase());
                });
            });
            SortedList<NivelO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(nivelTable.comparatorProperty());
            nivelTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            btCrear.setText("Crear Nuevo Nivel");
            btCrear.setOnAction(ev -> {
                CrearNivel cnw = new CrearNivel();
                cnw.display();
                btnNivel.fire();
            });
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Nombre, Nota o Descripción");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField, btCrear);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(nivelTable, hbBottomBox);
        });
//setting table NIVEL context menu
        nivelTable.setEditable(true);
        nivelTable.setRowFactory((TableView<NivelO> tableView) -> {
            final TableRow<NivelO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Eliminar");
            final MenuItem verCompMenu = new MenuItem("Ver Competencias");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarNivel mn = new ModificarNivel();
                mn.display(row.getItem().getId());
                btnNivel.fire();
            });
            //desactivar
            eliminarMenuItem.setOnAction(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.initOwner(primaryStage);
                alert.setTitle("Confirmar Eliminar");
                alert.setHeaderText(null);
                alert.setContentText("Confirma Eliminar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    nivelCtl.eliminarNivel(row.getItem().getId());
                    btnNivel.fire();
                }
            });
            verCompMenu.setOnAction(ev -> {
                CrearNivelCompetencia cnc = new CrearNivelCompetencia();
                cnc.display(row.getItem().getId());
            });
            contextMenu.getItems().addAll(modificarMenuItem, eliminarMenuItem, new SeparatorMenuItem(), verCompMenu);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting NIVEL table columns data and headers
        nombreNivelCol = new TableColumn<>("Nombre");
        nombreNivelCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        notaNivelCol = new TableColumn<>("Nota");
        notaNivelCol.setCellValueFactory(new PropertyValueFactory<>("nota"));
        descNivelCol = new TableColumn<>("Descripción");
        descNivelCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descNivelCol.setMaxWidth(250);
        creadoNivelCol = new TableColumn<>("Creado");
        creadoNivelCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        creadoNivelCol.setCellFactory(column -> {
            return new TableCell<NivelO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        modificadoNivelCol = new TableColumn<>("Modificado");
        modificadoNivelCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        modificadoNivelCol.setCellFactory(column -> {
            return new TableCell<NivelO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        //desactivadoNivelCol = new TableColumn<>("Desactivado");
        //desactivadoNivelCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
//load NIVEL columns
        nivelTable.getColumns().addAll(nombreNivelCol, notaNivelCol, descNivelCol, creadoNivelCol, modificadoNivelCol);
//
//MANTENEDOR PREGUNTA //QUESTION MAINTAINER
//
//display mantenedor PREGUNTA button
        btnPregunta.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Preguntas");
            questionTable.setItems(questionCtl.getPreguntasFX());
            //filtrar por pregunta
            FilteredList<PreguntaO> filteredData = new FilteredList<>(questionCtl.getPreguntasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    return filter.getPregunta().toLowerCase().contains(newValue.toLowerCase())
                            || compCtl.getCompetenciaById(filter.getCompetencia_id()).getNombre().toLowerCase().contains(newValue.toLowerCase());
                });
            });
            SortedList<PreguntaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(questionTable.comparatorProperty());
            questionTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Pregunta o Competencia");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(questionTable, hbBottomBox);
        });
//setting table PREGUNTA context menu
        questionTable.setEditable(true);
        questionTable.setRowFactory((TableView<PreguntaO> tableView) -> {
            final TableRow<PreguntaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem eliminarMenuItem = new MenuItem("Eliminar");
            final MenuItem crearRespuestaMenu = new MenuItem("Crear Respuesta");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarPregunta mp = new ModificarPregunta();
                mp.display(row.getItem().getCompetencia_id(), row.getItem().getId());
                btnPregunta.fire();
            });
            eliminarMenuItem.setOnAction(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.initOwner(primaryStage);
                alert.setTitle("Confirmar Eliminar");
                alert.setHeaderText(null);
                alert.setContentText("Confirma Eliminar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    questionCtl.eliminarPregunta(row.getItem().getId());
                    btnPregunta.fire();
                }
            });
            //crear respuesta
            crearRespuestaMenu.setOnAction(event -> {
                int id_pregunta = row.getItem().getId();
                CrearRespuesta cpw = new CrearRespuesta();
                cpw.display(id_pregunta);
                btnRespuesta.fire();
            });
            contextMenu.getItems().addAll(modificarMenuItem, eliminarMenuItem, crearRespuestaMenu);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting PREGUNTA table columns data and headers
        questionPreguntaCol = new TableColumn<>("Pregunta");
        questionPreguntaCol.setCellValueFactory(new PropertyValueFactory<>("pregunta"));
        questionPreguntaCol.setMaxWidth(250);
        questionCompetenciaCol = new TableColumn<>("Competencia");
        questionCompetenciaCol.setCellValueFactory(new PropertyValueFactory<>("competenciaNombre"));
        /*questionCompetenciaCol.setCellFactory(column -> {
            return new TableCell<PreguntaO, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(compCtl.getCompetenciaById(item).getNombre());
                    }
                }
            };
        });*/
        questionCreadoCol = new TableColumn<>("Creado");
        questionCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        questionCreadoCol.setCellFactory(column -> {
            return new TableCell<PreguntaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        questionModificadoCol = new TableColumn<>("Modificado");
        questionModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        questionModificadoCol.setCellFactory(column -> {
            return new TableCell<PreguntaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
//load PREGUNTA columns
        questionTable.getColumns().addAll(questionPreguntaCol, questionCompetenciaCol,
                questionCreadoCol, questionModificadoCol);
//
//MANTENEDOR RESPUESTA //ANSWER MAINTAINER
//
//display mantenedor RESPUESTA button
        btnRespuesta.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Respuestas");
            answerTable.setItems(answerCtl.getRespuestasFX());

            //filtrar por respuesta
            FilteredList<RespuestaO> filteredData = new FilteredList<>(answerCtl.getRespuestasFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    return filter.getRespuesta().toLowerCase().contains(newValue.toLowerCase())
                            || questionCtl.getPreguntaById(filter.getPregunta_id()).getPregunta().toLowerCase().contains(newValue.toLowerCase());
                });
            });
            SortedList<RespuestaO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(answerTable.comparatorProperty());
            answerTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Respuesta o Pregunta");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(answerTable, hbBottomBox);
        });
//setting table RESPUESTA context menu
        answerTable.setEditable(true);
        answerTable.setRowFactory((TableView<RespuestaO> tableView) -> {
            final TableRow<RespuestaO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Eliminar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarRespuesta mr = new ModificarRespuesta();
                mr.display(row.getItem().getPregunta_id(), row.getItem().getId());
                btnRespuesta.fire();
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.initOwner(primaryStage);
                alert.setTitle("Confirmar Eliminar");
                alert.setHeaderText(null);
                alert.setContentText("Confirma Eliminar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    answerCtl.eliminaRespuesta(row.getItem().getId());
                    btnRespuesta.fire();
                }
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
        answerRespuestaCol = new TableColumn<>("Respuesta");
        answerRespuestaCol.setCellValueFactory(new PropertyValueFactory<>("respuesta"));
        answerRespuestaCol.setMaxWidth(250);
        answerPuntosCol = new TableColumn<>("Puntos");
        answerPuntosCol.setCellValueFactory(new PropertyValueFactory<>("puntos"));
        answerPreguntaCol = new TableColumn<>("Pregunta");
        answerPreguntaCol.setCellValueFactory(new PropertyValueFactory<>("preguntaNombre"));
        answerPreguntaCol.setMaxWidth(250);
        /*answerPreguntaCol.setCellFactory(column -> {
            return new TableCell<RespuestaO, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(questionCtl.getPreguntaById(item).getPregunta());
                    }
                }
            };
        });*/
        answerCreadoCol = new TableColumn<>("Creado");
        answerCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        answerCreadoCol.setCellFactory(column -> {
            return new TableCell<RespuestaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        answerModificadoCol = new TableColumn<>("Modificado");
        answerModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        answerModificadoCol.setCellFactory(column -> {
            return new TableCell<RespuestaO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
//load RESPUESTA columns
        answerTable.getColumns().addAll(answerRespuestaCol, answerPuntosCol, answerPreguntaCol,
                answerCreadoCol, answerModificadoCol);
//
//MANTENEDOR OBSERVACIÓN //COMMENT MAINTAINER
//
//display mantenedor COMMENT button
        btnComment.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Observaciones");
            commentTable.setItems(commentCtl.getObservacionesFX());
            //filtrar por msj inferior y superior
            FilteredList<ObservacionO> filteredData = new FilteredList<>(commentCtl.getObservacionesFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return filter.getMsj_inf().toLowerCase().contains(lowerCaseFilter)
                            || filter.getMsj_sup().toLowerCase().contains(lowerCaseFilter)
                            || compCtl.getCompetenciaById(filter.getCompetencia_id()).getNombre().toLowerCase().contains(lowerCaseFilter);
                });
            });
            SortedList<ObservacionO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(commentTable.comparatorProperty());
            commentTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Mensaje Inferior, Mensaje Superior o Competencia");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(commentTable, hbBottomBox);
        });
//setting table COMMENT context menu
        commentTable.setEditable(true);
        commentTable.setRowFactory((TableView<ObservacionO> tableView) -> {
            final TableRow<ObservacionO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Eliminar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarObservacion mo = new ModificarObservacion();
                mo.display(row.getItem().getCompetencia_id(), row.getItem().getId());
                btnComment.fire();
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.initOwner(primaryStage);
                alert.setTitle("Confirmar Eliminar");
                alert.setHeaderText(null);
                alert.setContentText("Confirma Eliminar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    commentCtl.eliminarObservacionCTL(row.getItem().getId());
                    btnComment.fire();
                }
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
        commentNivelInfCol = new TableColumn<>("Nivel Inferior");
        commentNivelInfCol.setCellValueFactory(new PropertyValueFactory<>("nivel_inf"));
        commentNivelSupCol = new TableColumn<>("Nivel Superior");
        commentNivelSupCol.setCellValueFactory(new PropertyValueFactory<>("nivel_sup"));
        commentMsjInfCol = new TableColumn<>("Mensaje Nivel Inferior");
        commentMsjInfCol.setCellValueFactory(new PropertyValueFactory<>("msj_inf"));
        commentMsjInfCol.setMaxWidth(250);
        commentMsjSupCol = new TableColumn<>("Mensaje Nivel Superior");
        commentMsjSupCol.setCellValueFactory(new PropertyValueFactory<>("msj_sup"));
        commentMsjSupCol.setMaxWidth(250);
        commentCompetenciaCol = new TableColumn<>("Competencia");
        commentCompetenciaCol.setCellValueFactory(new PropertyValueFactory<>("compNombre"));
        /*commentCompetenciaCol.setCellFactory(column -> {
            return new TableCell<ObservacionO, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(compCtl.getCompetenciaById(item).getNombre());
                    }
                }
            };
        });*/
        commentCreadoCol = new TableColumn<>("Creado");
        commentCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        commentCreadoCol.setCellFactory(column -> {
            return new TableCell<ObservacionO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        commentModificadoCol = new TableColumn<>("Modificado");
        commentModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        commentModificadoCol.setCellFactory(column -> {
            return new TableCell<ObservacionO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
//load COMMENT columns
        commentTable.getColumns().addAll(commentNivelInfCol, commentNivelSupCol, commentMsjInfCol, commentMsjSupCol,
                commentCompetenciaCol, commentCreadoCol, commentModificadoCol);
//
//MANTENEDOR PERIODO
//
//display mantenedor PERIODO button
        btnPeriodo.setOnAction(e -> {
            filterField.clear();
            titleMantenedores.setText("SEC - Mantenedor Periodos");
            periodoTable.setItems(periodoCtl.getPeriodosFX());
            //filtro por porcentaje y fecha
            FilteredList<PeriodoO> filteredData = new FilteredList<>(periodoCtl.getPeriodosFX(), p -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(filter -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String inicio = df.format(filter.getInicio());
                    String fin = df.format(filter.getFin());
                    String porjefe = "" + filter.getJefe_porc();
                    String porauto = "" + filter.getAuto_porc();
                    return inicio.contains(newValue) || fin.contains(newValue) || porjefe.contains(newValue) || porauto.contains(newValue);
                });
            });
            SortedList<PeriodoO> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(periodoTable.comparatorProperty());
            periodoTable.setItems(sortedData);
            //filter hbox
            filterLabel.setText("Filtrar: ");
            btCrear.setText("Crear Nuevo Periodo");
            btCrear.setOnAction(ev -> {
                CrearPeriodo cpw = new CrearPeriodo();
                cpw.display();
                btnPeriodo.fire();
            });
            hbBottomBox.getStyleClass().add("hbox");
            hbBottomBox.getChildren().clear();
            tooltipFilter.setText("Fecha Inicio, Fecha Final, Porcentaje Jefe o Porcentaje Funcionario");
            filterField.setTooltip(tooltipFilter);
            hbBottomBox.getChildren().addAll(filterLabel, filterField, btCrear);
            //load vbox display
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(periodoTable, hbBottomBox);
        });
//setting table PERIODO context menu
        periodoTable.setEditable(true);
        periodoTable.setRowFactory((TableView<PeriodoO> tableView) -> {
            final TableRow<PeriodoO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem modificarMenuItem = new MenuItem("Modificar");
            final MenuItem desactivarMenuItem = new MenuItem("Desactivar");
            final MenuItem activarMenuItem = new MenuItem("Activar");
            //modificar            
            modificarMenuItem.setOnAction(event -> {
                ModificarPeriodo mp = new ModificarPeriodo();
                mp.display(row.getItem().getId());
                btnPeriodo.fire();
            });
            activarMenuItem.setOnAction(event -> {
                if (row.getItem().getActivo() == 0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Activar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Activar?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        periodoCtl.activarPeriodo(row.getItem().getId());
                        btnPeriodo.fire();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Periodo ya se encuentra Activado");
                    alert.showAndWait();
                }
            });
            //desactivar
            desactivarMenuItem.setOnAction(event -> {
                if (row.getItem().getActivo() == 1) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Confirmar Desactivar");
                    alert.setHeaderText(null);
                    alert.setContentText("Confirma Desactivar?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        periodoCtl.desactivarPeriodo(row.getItem().getId());
                        btnPeriodo.fire();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(primaryStage);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("Periodo ya se encuentra Desactivado");
                    alert.showAndWait();
                }
            });
            contextMenu.getItems().addAll(activarMenuItem, modificarMenuItem, desactivarMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );
            return row;
        });
//setting PERIODO table columns data and headers
        //periodoIdCol = new TableColumn<>("Id");
        //periodoIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        periodoInicioCol = new TableColumn<>("Inicio");
        periodoInicioCol.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        periodoInicioCol.setCellFactory(column -> {
            return new TableCell<PeriodoO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        periodoFinCol = new TableColumn<>("Final");
        periodoFinCol.setCellValueFactory(new PropertyValueFactory<>("fin"));
        periodoFinCol.setCellFactory(column -> {
            return new TableCell<PeriodoO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        periodoPorcJefeCol = new TableColumn<>("% Jefe");
        periodoPorcJefeCol.setCellValueFactory(new PropertyValueFactory<>("jefe_porc"));
        periodoPorcAutoCol = new TableColumn<>("% Funcionario");
        periodoPorcAutoCol.setCellValueFactory(new PropertyValueFactory<>("auto_porc"));
        periodoActivoCol = new TableColumn<>("Estado");
        periodoActivoCol.setCellValueFactory(new PropertyValueFactory<>("activo"));
        periodoActivoCol.setCellFactory(column -> {
            return new TableCell<PeriodoO, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        switch (item) {
                            case 0:
                                setText("INACTIVO");
                                break;
                            case 1:
                                setText("ACTIVO");
                                break;
                            default:
                                setText("");
                                break;
                        }
                    }
                }
            };
        });
        periodoCreadoCol = new TableColumn<>("Creado");
        periodoCreadoCol.setCellValueFactory(new PropertyValueFactory<>("creado"));
        periodoCreadoCol.setCellFactory(column -> {
            return new TableCell<PeriodoO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        periodoModificadoCol = new TableColumn<>("Modificado");
        periodoModificadoCol.setCellValueFactory(new PropertyValueFactory<>("modificado"));
        periodoModificadoCol.setCellFactory(column -> {
            return new TableCell<PeriodoO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
        periodoDesactivadoCol = new TableColumn<>("Desactivado");
        periodoDesactivadoCol.setCellValueFactory(new PropertyValueFactory<>("desactivado"));
        periodoDesactivadoCol.setCellFactory(column -> {
            return new TableCell<PeriodoO, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(df.format(item));
                    }
                }
            };
        });
//load PERIODO columns
        periodoTable.getColumns().addAll(periodoInicioCol, periodoFinCol, periodoPorcJefeCol, periodoPorcAutoCol,
                periodoActivoCol, periodoCreadoCol, periodoModificadoCol, periodoDesactivadoCol);
//reporte final button
        btnReportes.setOnAction(value -> {
            Label lPer = new Label("Periodos: ");
            ArrayList<PeriodoO> pers = new ArrayList<>();
            int perId = 0;
            ArrayList<EvaluacionO> evList = evaluacionCtl.getEvaluaciones();
            for (EvaluacionO e : evList) {
                if (perId != e.getPeriodoId()) {
                    pers.add(periodoCtl.getPeriodoById(e.getPeriodoId()));
                }
                perId = e.getPeriodoId();
            }
            ListView lvPer = new ListView(FXCollections.observableArrayList(pers));
            lvPer.setCellFactory(param -> new ListCell<PeriodoO>() {
                @Override
                protected void updateItem(PeriodoO item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        per = Formato.dateToString(item.getInicio()) + "  hasta  " + Formato.dateToString(item.getFin());
                        setText(per);
                        perIdRep = item.getId();
                    }
                }
            });
            VBox vbPerRep = new VBox(lPer, lvPer);
            vbPerRep.getStyleClass().add("vbox");
            Label l = new Label();
            VBox vbExport = new VBox(l, bExportarExcel);
            vbExport.getStyleClass().add("vbox");
            HBox hbReport = new HBox(vbPerRep, vbExport);
            hbReport.getStyleClass().add("hbox");
            vbDisplay.getChildren().clear();
            vbDisplay.getChildren().addAll(hbReport);
        });
        bExportarExcel.setOnAction(value -> {
            if (perIdRep == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(primaryStage);
                alert.setTitle("Exportar Error!");
                alert.setHeaderText(null);
                alert.setContentText("Seleccione Periodo");
                alert.showAndWait();
            } else {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xls)", "*.xls");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog(primaryStage);
                Excel xls = new Excel();
                if (file != null) {
                    try {
                        xls.reporteFinal(perIdRep, per, file.getAbsolutePath());
                    } catch (IOException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
//
//GENERAL LOAD
//
//load display in center border pane
        bp.setCenter(vbDisplay);
//setting scene
        Scene admin = new Scene(bp);
//loading stage scene and style
        primaryStage.setTitle("SEC - Mantenedores");
        primaryStage.setScene(admin);
        this.primaryStage.setMaximized(!primaryStage.isMaximized());
        this.primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKey);
        admin.getStylesheets().add(Admin.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }

    private void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case F11:
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
                break;
        }
    }

}
