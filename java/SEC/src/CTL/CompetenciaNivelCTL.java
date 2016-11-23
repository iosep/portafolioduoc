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

    public ArrayList<CompetenciaNivelO> getCompetenciaNivelActivos() {
        ArrayList<CompetenciaNivelO> list = new ArrayList<>();
        this.getCompetenciaNiveles().stream().filter((cn) -> (cn.getActivo() == 1)).forEach((cn) -> {
            list.add(cn);
        });
        return list;
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
        ArrayList<CompetenciaNivelO> cNs = this.getCompetenciaNiveles();
        competenciaCtl.getCompetenciasFX().stream().filter((each) -> (each.getActivo() == 1)).forEach((competencia) -> {
            boolean add = true;
            for (CompetenciaNivelO compNivel : cNs) {
                if (compNivel.getActivo() == 1 && compNivel.getNivel_id() == nivelId && compNivel.getCompetencia_id() == competencia.getId()) {
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
        ArrayList<CompetenciaNivelO> cNs = this.getCompetenciaNiveles();
        nivelCtl.getNiveles().stream().forEach((nivel) -> {
            boolean add = true;
            for (CompetenciaNivelO compNivel : cNs) {
                if (compNivel.getActivo() == 1 && compNivel.getCompetencia_id() == compId && compNivel.getNivel_id() == nivel.getId()) {
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
