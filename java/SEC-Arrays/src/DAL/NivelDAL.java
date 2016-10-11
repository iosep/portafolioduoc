/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.NivelO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class NivelDAL {

    private final AaaInitialLoad aaa = new AaaInitialLoad();
    private static NivelO nivel;

    public ArrayList<NivelO> getNiveles() {
        return aaa.mostrarNiveles();
    }

    public NivelO getNivelById(int id) {
        aaa.mostrarNiveles().stream().forEach((each) -> {
            if (each.getId() == id) {
                nivel = each;
            }
        });
        return nivel;
    }

    public boolean addNivel(NivelO obj) {
        return aaa.agregarNivel(obj);
    }

}
