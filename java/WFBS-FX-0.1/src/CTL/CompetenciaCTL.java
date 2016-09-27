/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.CompetenciaDAL;
import O.CompetenciaO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class CompetenciaCTL {

    private final CompetenciaDAL compDal = new CompetenciaDAL();

    public ArrayList<CompetenciaO> getCompetencias() {
        ArrayList<CompetenciaO> array = new ArrayList<>();
        return array;
    }

    public CompetenciaO getCompetenciaById(int id) {
        return compDal.getCompetenciaById(id);
    }

    public ObservableList<CompetenciaO> getCompetenciasFX() {
        ObservableList<CompetenciaO> fxList = FXCollections.observableArrayList();
        compDal.getCompetencias().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addCompetenciaCTL(CompetenciaO obj) {
        return compDal.addCompetencia(obj);
    }

}
