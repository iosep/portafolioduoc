/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.ObservacionDAL;
import O.ObservacionO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class ObservacionCTL {

    private final ObservacionDAL obDal = new ObservacionDAL();

    public ObservacionO getObservacionById(int id) {
        return obDal.getObservacionById(id);
    }

    public ObservableList<ObservacionO> getObservacionesFX() {
        ObservableList<ObservacionO> fxList = FXCollections.observableArrayList();
        obDal.getObservaciones().stream().filter((each) -> (each.getActivo() == 1)).forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addObservacionCTL(ObservacionO obj) {
        return obDal.addObservacion(obj);
    }

    public boolean modificarObservacionCTL(ObservacionO obj) {
        return obDal.updateObservacion(obj);
    }

    public boolean eliminarObservacionCTL(int id) {
        return obDal.deleteObservacion(id);
    }

    public ObservacionO getObservacionByComp(int id) {
        return obDal.getObservacionByCompetencia(id);
    }
    
}
