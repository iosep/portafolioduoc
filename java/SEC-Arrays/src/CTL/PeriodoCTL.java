/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.PeriodoDAL;
import O.PeriodoO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class PeriodoCTL {

    private final PeriodoDAL periodoDal = new PeriodoDAL();

    public ArrayList<PeriodoO> getPeriodos() {
        return periodoDal.getPeriodos();
    }

    public PeriodoO getPeriodoById(int id) {
        return periodoDal.getPeriodoById(id);
    }

    public ObservableList<PeriodoO> getPeriodosFX() {
        ObservableList<PeriodoO> fxList = FXCollections.observableArrayList();
        periodoDal.getPeriodos().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addPeriodoCTL(PeriodoO obj) {
        return periodoDal.addPeriodo(obj);
    }

}
