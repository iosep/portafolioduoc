/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.CompetenciaNivelDAL;
import DAL.CompetenciaDAL;
import DAL.NivelDAL;
import O.CompetenciaNivelO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class CompetenciaNivelCTL {

    private final CompetenciaNivelDAL compNivelDal = new CompetenciaNivelDAL();
    private final CompetenciaDAL competenciaDal = new CompetenciaDAL();
    private final NivelDAL nivelDal = new NivelDAL();

    public ObservableList<CompetenciaNivelO> getCompetenciaNivelesByNivelIdFX(int id) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        compNivelDal.getCompetenciaNivelesByNivelId(id).stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public ObservableList<CompetenciaNivelO> getCompetenciaNivelesByCompetenciaIdFX(int id) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        compNivelDal.getCompetenciaNivelesByCompetenciaId(id).stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public ObservableList<CompetenciaNivelO> getCompetenciasDisponiblesByNivelFX(int nivelId) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        competenciaDal.getCompetencias().stream().forEach((competencia) -> {
            boolean add = true;
            for (CompetenciaNivelO compNivel : compNivelDal.getCompetenciaNivelesByNivelId(nivelId)) {
                if (compNivel.getCompetencia_id() == competencia.getId()) {
                    add = false;
                }
            }
            if (add) {
                fxList.add(new CompetenciaNivelO(competencia.getId(), nivelId));
            }
        });
        return fxList;
    }

    public ObservableList<CompetenciaNivelO> getNivelesDisponiblesByCompetenciaFX(int compId) {
        ObservableList<CompetenciaNivelO> fxList = FXCollections.observableArrayList();
        nivelDal.getNiveles().stream().forEach((nivel) -> {
            boolean add = true;
            for (CompetenciaNivelO compNivel : compNivelDal.getCompetenciaNivelesByCompetenciaId(compId)) {
                if (compNivel.getNivel_id() == nivel.getId()) {
                    add = false;
                }
            }
            if (add) {
                fxList.add(new CompetenciaNivelO(compId, nivel.getId()));
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
