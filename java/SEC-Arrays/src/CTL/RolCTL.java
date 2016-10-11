/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.RolDAL;
import O.RolO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class RolCTL {

    private final RolDAL rd = new RolDAL();

    public RolO getRolById(int id) {
        return rd.getRolById(id);
    }

    public ObservableList<RolO> getRolesFX() {
        return FXCollections.observableArrayList(rd.getRoles());
    }

}
