/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.ObservacionO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class ObservacionDAL {

    private final AaaInitialLoad aaa = new AaaInitialLoad();
    private static ObservacionO observacionObj;

    public ArrayList<ObservacionO> getObservaciones() {
        return aaa.mostrarObservaciones();
    }

    public ObservacionO getObservacionById(int id) {
        aaa.mostrarObservaciones().stream().forEach((each) -> {
            if (each.getId() == id) {
                observacionObj = each;
            }
        });
        return observacionObj;
    }

    public boolean addObservacion(ObservacionO obj) {
        return aaa.agregarObservacion(obj);
    }

}
