/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.SeleccionO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class SeleccionDAL {

    public static ArrayList<SeleccionO> getSelecciones() {
        return AInitLoad.getSelecciones();
    }

    public static boolean addSeleccion(SeleccionO obj) {
        return AInitLoad.addSeleccion(obj);
    }

    public static SeleccionO getSeleccionById(int id) {
        for (SeleccionO item : AInitLoad.getSelecciones()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
