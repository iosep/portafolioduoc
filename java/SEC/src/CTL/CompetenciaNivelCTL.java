/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.CompetenciaNivelDAL;
import O.CompetenciaNivelO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class CompetenciaNivelCTL {

    private final CompetenciaNivelDAL compNivelDal = new CompetenciaNivelDAL();
    private final CompetenciaCTL competenciaCtl = new CompetenciaCTL();
    private final NivelCTL nivelCtl = new NivelCTL();

    public ArrayList<CompetenciaNivelO> getCompetenciaNiveles() {
        return compNivelDal.getCompetenciaNiveles();
    }

    public ObservableList<CompetenciaNivelO> getCompetenciaNivelesByNivelIdFX(int id) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        compNivelDal.getCompetenciaNivelesByNivelId(id).stream().filter((each) -> (each.getActivo() == 1)).forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public ObservableList<CompetenciaNivelO> getCompetenciaNivelesByCompetenciaIdFX(int id) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        compNivelDal.getCompetenciaNivelesByCompetenciaId(id).stream().filter((each) -> (each.getActivo() == 1)).forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public ObservableList<CompetenciaNivelO> getCompetenciasDisponiblesByNivelFX(int nivelId) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        competenciaCtl.getCompetenciasFX().stream().filter((each) -> (each.getActivo() == 1)).forEach((competencia) -> {
            boolean add = true;
            for (CompetenciaNivelO compNivel : compNivelDal.getCompetenciaNivelesByNivelId(nivelId)) {
                if (compNivel.getCompetencia_id() == competencia.getId()) {
                    add = false;
                    break;
                }
            }
            if (add) {
                CompetenciaNivelO cn1 = new CompetenciaNivelO(competencia.getId(), nivelId);
                cn1.setCompNombre(competencia.getNombre());
                fxList.add(cn1);
            }
        });
        return fxList;
    }

    public ObservableList<CompetenciaNivelO> getNivelesDisponiblesByCompetenciaFX(int compId) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        nivelCtl.getNiveles().stream().forEach((nivel) -> {
            boolean add = true;
            for (CompetenciaNivelO compNivel : compNivelDal.getCompetenciaNivelesByCompetenciaId(compId)) {
                if (compNivel.getNivel_id() == nivel.getId()) {
                    add = false;
                    break;
                }
            }
            if (add) {
                CompetenciaNivelO cn1 = new CompetenciaNivelO(compId, nivel.getId());
                cn1.setNivelNombre(nivel.getNombre());
                fxList.add(cn1);
            }
        });
        return fxList;
    }

    public boolean addCompetenciaNivelCTL(CompetenciaNivelO obj) {
        return compNivelDal.addCompetenciaNivel(obj);
    }

    public boolean removeAreaCompCTL(int idCompetencia, int idNivel) {
        return compNivelDal.removeCompetenciaNivel(idCompetencia, idNivel);
    }

}
