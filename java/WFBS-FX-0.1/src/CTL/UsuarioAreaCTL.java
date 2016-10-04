/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.AreaDAL;
import DAL.UsuarioAreaDAL;
import O.AreaO;
import O.UsuarioAreaO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioAreaCTL {

    private final UsuarioAreaDAL usuarioAreaDal = new UsuarioAreaDAL();
    private final AreaDAL areaDal = new AreaDAL();

    public ArrayList<UsuarioAreaO> getUsuarioAreas() {
        return usuarioAreaDal.getUserAreas();
    }

    public ArrayList<UsuarioAreaO> getUsuarioAreaByUserId(int id) {
        return usuarioAreaDal.getUserAreasByUserId(id);
    }

    public ArrayList<UsuarioAreaO> getUsuarioAreaByAreaId(int id) {
        return usuarioAreaDal.getUserAreasByAreaId(id);
    }

    public ObservableList<UsuarioAreaO> getUsuarioAreasFX() {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        usuarioAreaDal.getUserAreas().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public ObservableList<UsuarioAreaO> getAreasDisponiblesByUserFX(int userId) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        areaDal.getAreas().stream().forEach((area) -> {
            boolean add = true;
            for (UsuarioAreaO userArea : usuarioAreaDal.getUserAreasByUserId(userId)) {
                if (userArea.getArea_id() == area.getId()) {
                    add = false;
                }
            }
            if (add) {
                fxList.add(new UsuarioAreaO(userId, area.getId(), area.getNombre()));
            }
        });
        return fxList;
    }

    public boolean addUsuarioAreaCTL(UsuarioAreaO obj) {
        return usuarioAreaDal.addUsuarioArea(obj);
    }

}
