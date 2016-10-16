/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.AreaDAL;
import O.AreaO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class AreaCTL {

    private final AreaDAL ad = new AreaDAL();

    public ArrayList<AreaO> getAreas() {
        ArrayList<AreaO> al = new ArrayList<>();
        return al;
    }

    public AreaO getAreaById(int id) {
        return ad.getAreaById(id);
    }

    public AreaO getAreaByNombre(String nombre) {
        AreaO result = null;
        for (AreaO item : ad.getAreas()) {
            if (item.getNombre().equals(nombre)) {
                result = item;
            }
        }
        return result;
    }

    public ObservableList<AreaO> getAreasFX() {
        ObservableList<AreaO> areas = FXCollections.observableArrayList();
        ad.getAreas().stream().forEach((a) -> {
            areas.add(a);
        });
        return areas;
    }

    public boolean addAreaCTL(AreaO afx) {
        return ad.addArea(afx);
    }

}
