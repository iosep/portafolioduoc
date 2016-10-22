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

    public static ArrayList<SeleccionO> getSelecciones() {
        return SeleccionDAL.getSelecciones();
    }

    public static boolean addSeleccion(SeleccionO obj) {
        return SeleccionDAL.addSeleccion(obj);
    }

    public static SeleccionO getSeleccionById(int id) {
        for (SeleccionO item : SeleccionDAL.getSelecciones()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
