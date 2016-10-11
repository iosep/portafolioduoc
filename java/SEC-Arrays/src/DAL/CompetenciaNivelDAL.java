/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.CompetenciaNivelO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class CompetenciaNivelDAL {

    private final AaaInitialLoad aaa = new AaaInitialLoad();

    public ArrayList<CompetenciaNivelO> getCompetenciaNiveles() {
        return aaa.mostrarCompetenciaNiveles();
    }

    public ArrayList<CompetenciaNivelO> getCompetenciaNivelesByCompetenciaId(int id) {
        ArrayList<CompetenciaNivelO> list = new ArrayList<>();
        aaa.mostrarCompetenciaNiveles().stream().forEach((item) -> {
            if (item.getCompetencia_id() == id) {
                list.add(item);
            }
        });
        return list;
    }

    public ArrayList<CompetenciaNivelO> getCompetenciaNivelesByNivelId(int id) {
        ArrayList<CompetenciaNivelO> list = new ArrayList<>();
        aaa.mostrarCompetenciaNiveles().stream().forEach((item) -> {
            if (item.getNivel_id() == id) {
                list.add(item);
            }
        });
        return list;
    }

    public boolean addCompetenciaNivel(CompetenciaNivelO obj) {
        return aaa.agregarCompetenciaNivel(obj);
    }

    public boolean removeCompetenciaNivel(int idCompetencia, int idNivel) {
        return aaa.eliminarCompetenciaNivel(idCompetencia, idNivel);
    }

}
