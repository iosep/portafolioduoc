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
        nivelDal.getNiveles().stream().filter((n) -> (n.getActivo() == 1)).forEach((n) -> {
            array.add(n);
        });
        return array;
    }

    public NivelO getNivelById(int id) {
        return nivelDal.getNivelById(id);
    }

    public ObservableList<NivelO> getNivelesFX() {
        ObservableList<NivelO> obList = FXCollections.observableArrayList();
        nivelDal.getNiveles().stream().filter((n) -> (n.getActivo() == 1)).forEach((n) -> {
            obList.add(n);
        });
        return obList;
    }

    public boolean addNivelCTL(NivelO obj) {
        return nivelDal.addNivel(obj);
    }

    public boolean modificarNivel(NivelO obj) {
        return nivelDal.updateNivel(obj);
    }

    public boolean eliminarNivel(int id) {
        return nivelDal.deleteNivel(id);
    }
}
