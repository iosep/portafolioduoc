/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.AreaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class AreaDAL {

    private final AInitLoad aaa = new AInitLoad();
    private static AreaO ao;

    public ArrayList<AreaO> getAreas() {
        return aaa.mostrarAreas();
    }

    public AreaO getAreaById(int id) {
        aaa.mostrarAreas().stream().forEach((u) -> {
            if (u.getId() == id) {
                ao = u;
            }
        });
        return ao;
    }

    public boolean addArea(AreaO afx) {
        return aaa.agregarArea(afx);
    }

}
