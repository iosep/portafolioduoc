/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.CompetenciaDAL;
import O.CompetenciaO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class CompetenciaCTL {

    private final CompetenciaDAL compDal = new CompetenciaDAL();

    public CompetenciaO getCompetenciaById(int id) {
        return compDal.getCompetenciaById(id);
    }

    public CompetenciaO getCompetenciaByNombre(String nombreCompetencia) {
        for (CompetenciaO object : compDal.getCompetencias()) {
            if (object.getNombre().equals(nombreCompetencia)) {
                return object;
            }
        }
        return null;
    }

    public ObservableList<CompetenciaO> getCompetenciasFX() {
        ObservableList<CompetenciaO> fxList = FXCollections.observableArrayList(compDal.getCompetencias());
        return fxList;
    }

    public boolean addCompetenciaCTL(CompetenciaO obj) {
        return compDal.addCompetencia(obj);
    }

    public boolean modificarCompetencia(CompetenciaO obj) {
        return compDal.updateCompetencia(obj);
    }

    public boolean activarCompetencia(int id) {
        return compDal.activaCompetencia(id);
    }

    public boolean desactivarCompetencia(int id) {
        return compDal.deleteCompetencia(id);
    }

}
