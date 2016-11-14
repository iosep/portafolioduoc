/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.SeleccionDAL;
import O.SeleccionO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class SeleccionCTL {

    private final SeleccionDAL selDal = new SeleccionDAL();

    public ArrayList<SeleccionO> getSelecciones() {
        return selDal.getSelecciones();
    }

    public boolean addSeleccion(SeleccionO obj) {
        return selDal.addSeleccion(obj);
    }

    public SeleccionO getSeleccionById(int id) {
        return selDal.getSeleccionById(id);
    }

    public ArrayList<SeleccionO> getSeleccionesByEncuestaId(int id) {
        return selDal.getSeleccionesByEncuestaId(id);
    }

}
