/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.AreaCompetenciaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class AreaCompetenciaDAL {

    private final AInitLoad aaa = new AInitLoad();
    private static AreaCompetenciaO aco;

    public ArrayList<AreaCompetenciaO> getAreaCompetencias() {
        return aaa.mostrarAreaCompetencias();
    }

    public ArrayList<AreaCompetenciaO> getAreaCompetenciasByAreaId(int id) {
        ArrayList<AreaCompetenciaO> list = new ArrayList<>();
        aaa.mostrarAreaCompetencias().stream().forEach((item) -> {
            if (item.getArea_id() == id) {
                list.add(item);
            }
        });
        return list;
    }

    public ArrayList<AreaCompetenciaO> getAreaCompetenciasByCompetenciaId(int id) {
        ArrayList<AreaCompetenciaO> list = new ArrayList<>();
        aaa.mostrarAreaCompetencias().stream().forEach((item) -> {
            if (item.getCompetencia_id() == id) {
                list.add(item);
            }
        });
        return list;
    }

    public boolean addAreaCompetencia(AreaCompetenciaO obj) {
        return aaa.agregarAreaCompetencia(obj);
    }

    public boolean removeAreaCompetencia(int idArea, int idCompetencia) {
        return aaa.eliminarAreaCompetencia(idArea, idCompetencia);
    }

}
