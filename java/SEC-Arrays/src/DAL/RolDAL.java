/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.RolO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class RolDAL {

    private final AInitLoad load = new AInitLoad();
    private static RolO rol0;

    public RolO getRolById(int id) {
        load.mostrarRoles().stream().filter((rol) -> (rol.getId() == id)).forEach((rol) -> {
            rol0 = rol;
        });
        return rol0;
    }

    public ArrayList<RolO> getRoles() {
        return load.mostrarRoles();
    }

}
