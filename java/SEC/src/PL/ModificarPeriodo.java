/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import CTL.PeriodoCTL;
import FN.Validar;
import O.PeriodoO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author iosep
 */
public class ModificarPeriodo {

    private final PeriodoCTL periodoCtl = new PeriodoCTL();
    private PeriodoO p0;
    static boolean vb = false;

    public void display(int idPeriodo) {
        Stage window = new Stage();
        window.getIcons().add(new Image(getClass().getResourceAsStream("desk.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("SEC - Modificar Periodo");
        p0 = periodoCtl.getPeriodoById(idPeriodo);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("SEC");
        scenetitle.getStyleClass().add("title");
        grid.add(scenetitle, 0, 0, 2, 1);
        Label subtitle = new Label("Modificar Periodo");
        subtitle.getStyleClass().add("subtitle");
        grid.add(subtitle, 0, 1, 2, 1);

        final Text msj = new Text();
        grid.add(msj, 0, 9, 2, 1);
        msj.getStyleClass().add("action");

        Label lblFechaInicio = new Label("Fecha Inicio:");
        grid.add(lblFechaInicio, 0, 4);
        DatePicker dpFechaInicio = new DatePicker(p0.getInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        final Callback<DatePicker, DateCell> cellFactoryFromToday = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        dpFechaInicio.setDayCellFactory(cellFactoryFromToday);
        grid.add(dpFechaInicio, 1, 4);

        Label lblFechaFinal = new Label("Fecha Final:");
        grid.add(lblFechaFinal, 0, 5);
        DatePicker dpFechaFinal = new DatePicker(p0.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        final Callback<DatePicker, DateCell> cellFactoryAfterInicio = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (dpFechaInicio.getValue() != null && item.isBefore(dpFechaInicio.getValue().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        grid.add(dpFechaFinal, 1, 5);
        dpFechaFinal.valueProperty().addListener((ob, ol, ne) -> {
            msj.setText("");
        });

        dpFechaFinal.setDayCellFactory(cellFactoryAfterInicio);
        dpFechaInicio.valueProperty().addListener((ob, ol, ne) -> {
            msj.setText("");
            if (ne != null && dpFechaFinal.getValue() != null && !dpFechaFinal.getValue().isAfter(ne)) {
                dpFechaFinal.setValue(dpFechaInicio.getValue().plusDays(1));
                msj.setFill(Color.GREEN);
                msj.setText("Fecha Final Ajustada Automáticamente");
            }
        });

        Label lblPorcJefe = new Label("Porcentaje Jefe Evaluación:");
        grid.add(lblPorcJefe, 0, 6);
        TextField txtPorcJefe = new TextField("" + p0.getJefe_porc());
        grid.add(txtPorcJefe, 1, 6);

        Label lblPorcAuto = new Label("Porcentaje Autoevaluación:");
        grid.add(lblPorcAuto, 0, 7);
        TextField txtPorcAuto = new TextField("" + p0.getAuto_porc());
        grid.add(txtPorcAuto, 1, 7);

        Validar v = new Validar();
        txtPorcJefe.textProperty().addListener((observable, oldValue, newValue) -> {
            if (v.validarInteger(newValue)) {
                if (Integer.parseInt(newValue) < 100 && Integer.parseInt(newValue) > 0) {
                    msj.setText("");
                    int auto = 100 - Integer.parseInt(newValue);
                    txtPorcAuto.setText("" + auto);
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Porcentaje > 0 y < 100");
                    txtPorcJefe.clear();
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Porcentaje Sólo Números");
                txtPorcJefe.clear();
            }
        });
        txtPorcAuto.textProperty().addListener((observable, oldValue, newValue) -> {
            if (v.validarInteger(newValue)) {
                if (Integer.parseInt(newValue) < 100 && Integer.parseInt(newValue) > 0) {
                    int jefe = 100 - Integer.parseInt(newValue);
                    txtPorcJefe.setText("" + jefe);
                } else {
                    msj.setFill(Color.FIREBRICK);
                    msj.setText("Porcentaje > 0 y < 100");
                    txtPorcAuto.clear();
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Porcentaje Sólo Números");
                txtPorcAuto.clear();
            }
        });

        Button btn = new Button("MODIFICAR");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 10, 1, 5);

        btn.setOnAction(e -> {
            if (dpFechaInicio.getValue() == null) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Seleccione Fecha Inicio");
            } else if (dpFechaFinal.getValue() == null) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Seleccione Fecha Final");
            } else if (txtPorcJefe.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Porcentaje Evaluación Jefe");
            } else if (txtPorcAuto.getText().isEmpty()) {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Ingrese Porcentaje Autoevaluación");
            } else if (dpFechaInicio.getValue().isBefore(dpFechaFinal.getValue())) {
                Date inicio = Date.from(dpFechaInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date fin = Date.from(dpFechaFinal.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                PeriodoO p1 =new PeriodoO(inicio, fin, Integer.parseInt(txtPorcJefe.getText()), Integer.parseInt(txtPorcAuto.getText()));
                p1.setId(idPeriodo);
                if (periodoCtl.modificarPeriodo(p1)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initOwner(window);
                    alert.setTitle("Periodo Modificado");
                    alert.setHeaderText(null);
                    alert.setContentText("Periodo Modificado Exitosamente");
                    alert.showAndWait();
                    window.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(window);
                    alert.setTitle("ERROR - Modificar Periodo");
                    alert.setHeaderText(null);
                    alert.setContentText("ERROR al Modificar Periodo");
                    alert.showAndWait();
                    window.close();
                }
            } else {
                msj.setFill(Color.FIREBRICK);
                msj.setText("Fecha Inicio Anterior a Fecha Final");
            }
        });

        Scene display = new Scene(grid, 450, 400);
        window.setScene(display);
        display.getStylesheets().add(ModificarPeriodo.class.getResource("Style.css").toExternalForm());
        window.showAndWait();

    }
}
