/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.ObservacionDAL;
import O.ObservacionO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class ObservacionCTL {

    private final ObservacionDAL observacionDal = new ObservacionDAL();

    public ArrayList<ObservacionO> getObservaciones() {
        return observacionDal.getObservaciones();
    }

    public ObservacionO getObservacionById(int id) {
        return observacionDal.getObservacionById(id);
    }

    public ObservableList<ObservacionO> getObservacionesFX() {
        ObservableList<ObservacionO> fxList = FXCollections.observableArrayList();
        observacionDal.getObservaciones().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addObservacionCTL(ObservacionO obj) {
        return observacionDal.addObservacion(obj);
    }

}
