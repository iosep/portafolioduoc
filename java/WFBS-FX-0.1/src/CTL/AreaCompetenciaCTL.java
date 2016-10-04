/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.AreaCompetenciaDAL;
import DAL.CompetenciaDAL;
import O.AreaCompetenciaO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class AreaCompetenciaCTL {

    private final AreaCompetenciaDAL areaCompetenciaDal = new AreaCompetenciaDAL();
    private final CompetenciaDAL competenciaDal = new CompetenciaDAL();

    public ObservableList<AreaCompetenciaO> getAreaCompetenciasByAreaIdFX(int id) {
        ObservableList<AreaCompetenciaO> fxList = FXCollections.observableArrayList();
        areaCompetenciaDal.getAreaCompetencias().stream().forEach((each) -> {
            if (each.getArea_id() == id) {
                fxList.add(each);
            }
        });
        return fxList;
    }

    public ObservableList<AreaCompetenciaO> getCompetenciasDisponiblesByAreaFX(int areaId) {
        ObservableList<AreaCompetenciaO> fxList = FXCollections.observableArrayList();
        competenciaDal.getCompetencias().stream().forEach((competencia) -> {
            boolean add = true;
            for (AreaCompetenciaO areaComp : areaCompetenciaDal.getAreaCompetenciasByAreaId(areaId)) {
                if (areaComp.getCompetencia_id() == competencia.getId()) {
                    add = false;
                }
            }
            if (add) {
                fxList.add(new AreaCompetenciaO(areaId, competencia.getId(), competencia.getNombre()));
            }
        });
        return fxList;
    }

    public boolean addAreaCompetenciaCTL(AreaCompetenciaO obj) {
        return areaCompetenciaDal.addAreaCompetencia(obj);
    }

    public boolean removeAreaCompCTL(int idArea, int idCompetencia) {
        return areaCompetenciaDal.removeAreaCompetencia(idArea, idCompetencia);
    }

}
