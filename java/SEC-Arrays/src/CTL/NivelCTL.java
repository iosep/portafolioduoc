/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.NivelDAL;
import O.NivelO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class NivelCTL {

    private final NivelDAL nivelDal = new NivelDAL();

    public ArrayList<NivelO> getNiveles() {
        ArrayList<NivelO> array = new ArrayList<>();
        return array;
    }

    public NivelO getNivelById(int id) {
        return nivelDal.getNivelById(id);
    }

    public ObservableList<NivelO> getNivelesFX() {
        ObservableList<NivelO> fxList = FXCollections.observableArrayList();
        nivelDal.getNiveles().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addNivelCTL(NivelO obj) {
        return nivelDal.addNivel(obj);
    }

}
