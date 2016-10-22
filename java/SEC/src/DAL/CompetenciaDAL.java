/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.CompetenciaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class CompetenciaDAL {

    private final AInitLoad aaa = new AInitLoad();
    private static CompetenciaO competencia;

    public ArrayList<CompetenciaO> getCompetencias() {
        return aaa.mostrarCompetencias();
    }

    public CompetenciaO getCompetenciaById(int id) {
        aaa.mostrarCompetencias().stream().forEach((each) -> {
            if (each.getId() == id) {
                competencia = each;
            }
        });
        return competencia;
    }

    public boolean addCompetencia(CompetenciaO obj) {
        return aaa.agregarCompetencia(obj);
    }

}
