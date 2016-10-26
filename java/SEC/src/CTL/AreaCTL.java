/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.AreaDAL;
import O.AreaO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class AreaCTL {

    private final AreaDAL ad = new AreaDAL();

    public AreaO getAreaById(int id) {
        return ad.getAreaById(id);
    }

    public ObservableList<AreaO> getAreasFX() {
        ObservableList<AreaO> areas = FXCollections.observableArrayList(ad.getAreas());
        return areas;
    }

    public boolean addAreaCTL(AreaO obj) {
        return ad.addArea(obj);
    }

    public boolean modificarArea(AreaO obj) {
        return ad.updateArea(obj);
    }

    public boolean desactivarArea(int id) {
        return ad.deleteArea(id);
    }

    public boolean activaArea(int id) {
        return ad.activaArea(id);
    }
}
