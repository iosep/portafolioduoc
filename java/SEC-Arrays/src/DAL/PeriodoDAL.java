/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.PeriodoO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class PeriodoDAL {

    private final AaaInitialLoad aaa = new AaaInitialLoad();
    private static PeriodoO periodoObj;

    public ArrayList<PeriodoO> getPeriodos() {
        return aaa.mostrarPeriodos();
    }

    public PeriodoO getPeriodoById(int id) {
        aaa.mostrarPeriodos().stream().forEach((each) -> {
            if (each.getId() == id) {
                periodoObj = each;
            }
        });
        return periodoObj;
    }

    public boolean addPeriodo(PeriodoO obj) {
        return aaa.agregarPeriodo(obj);
    }

}
