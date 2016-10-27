/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.AreaCompetenciaDAL;
import O.AreaCompetenciaO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class AreaCompetenciaCTL {

    private final AreaCompetenciaDAL areaCompDal = new AreaCompetenciaDAL();
    private final CompetenciaCTL compCtl = new CompetenciaCTL();
    private final AreaCTL areaCtl = new AreaCTL();

    public ObservableList<AreaCompetenciaO> getAreaCompetenciasByAreaIdFX(int id) {
        ObservableList<AreaCompetenciaO> fxList = FXCollections.observableArrayList();
        areaCompDal.getAreaCompetenciasByAreaId(id).stream().forEach((each) -> {
            if (each.getActivo() == 1) {
                fxList.add(each);
            }
        });
        return fxList;
    }

    public ObservableList<AreaCompetenciaO> getAreaCompetenciasByCompetenciaIdFX(int id) {
        ObservableList<AreaCompetenciaO> fxList = FXCollections.observableArrayList();
        areaCompDal.getAreaCompetenciasByCompetenciaId(id).stream().forEach((each) -> {
            if (each.getActivo() == 1) {
                fxList.add(each);
            }
        });
        return fxList;
    }

    public ObservableList<AreaCompetenciaO> getCompetenciasDisponiblesByAreaFX(int areaId) {
        ObservableList<AreaCompetenciaO> fxList = FXCollections.observableArrayList();
        compCtl.getCompetenciasFX().stream().filter((each) -> (each.getActivo() == 1)).forEach((competencia) -> {
            boolean add = true;
            for (AreaCompetenciaO areaComp : this.getAreaCompetenciasByAreaIdFX(areaId)) {
                if (areaComp.getCompetencia_id() == competencia.getId()) {
                    add = false;
                    break;
                }
            }
            if (add) {
                AreaCompetenciaO ac1 = new AreaCompetenciaO(areaId, competencia.getId());
                ac1.setCompNombre(competencia.getNombre());
                fxList.add(ac1);
            }
        });
        return fxList;
    }

    public ObservableList<AreaCompetenciaO> getAreasDisponiblesByCompetenciaFX(int compId) {
        ObservableList<AreaCompetenciaO> fxList = FXCollections.observableArrayList();
        areaCtl.getAreasFX().stream().filter((each) -> (each.getActivo() == 1)).forEach((area) -> {
            boolean add = true;
            for (AreaCompetenciaO areaComp : this.getAreaCompetenciasByCompetenciaIdFX(compId)) {
                if (areaComp.getArea_id() == area.getId()) {
                    add = false;
                    break;
                }
            }
            if (add) {
                AreaCompetenciaO ac1 = new AreaCompetenciaO(area.getId(), compId);
                ac1.setAreaNombre(area.getNombre());
                fxList.add(ac1);
            }
        });
        return fxList;
    }

    public boolean addAreaCompetenciaCTL(AreaCompetenciaO obj) {
        return areaCompDal.addAreaCompetencia(obj);
    }

    public boolean removeAreaCompCTL(int idArea, int idCompetencia) {
        return areaCompDal.removeAreaCompetencia(idArea, idCompetencia);
    }

}
