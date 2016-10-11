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

    private final AaaInitialLoad aaa = new AaaInitialLoad();
    private RolO r0;

    public RolO getRolById(int id) {
        aaa.mostrarRoles().stream().filter((object) -> (id == object.getId())).forEach((object) -> {
            r0 = object;
        });
        return r0;
    }

    public ArrayList<RolO> getRoles() {
        return aaa.mostrarRoles();
    }

}
