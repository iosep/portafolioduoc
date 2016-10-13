/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.AreaDAL;
import DAL.UsuarioAreaDAL;
import DAL.UsuarioDAL;
import O.UsuarioAreaO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioAreaCTL {

    private final UsuarioAreaDAL usuarioAreaDal = new UsuarioAreaDAL();
    private final AreaDAL areaDal = new AreaDAL();
    private final UsuarioDAL userDal = new UsuarioDAL();
    private final UsuarioCTL userCtl = new UsuarioCTL();

    public ObservableList<UsuarioAreaO> getUsuarioAreasByUserIdFX(int id) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        usuarioAreaDal.getUserAreas().stream().forEach((each) -> {
            if (each.getUsuario_id() == id) {
                fxList.add(each);
            }
        });
        return fxList;
    }

    public ObservableList<UsuarioAreaO> getUsuarioAreasByAreaIdFX(int id) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        usuarioAreaDal.getUserAreas().stream().forEach((each) -> {
            if (each.getArea_id() == id) {
                fxList.add(each);
            }
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
                fxList.add(new UsuarioAreaO(userId, area.getId()));
            }
        });
        return fxList;
    }

    public ObservableList<UsuarioAreaO> getUsuariosDisponiblesByAreaFX(int areaId) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        userDal.getUsuariosByRol(3).stream().forEach((user) -> {
            boolean add = true;
            for (UsuarioAreaO userArea : usuarioAreaDal.getUserAreasByAreaId(areaId)) {
                if (userArea.getUsuario_id() == user.getId()) {
                    add = false;
                }
            }
            if (add) {
                fxList.add(new UsuarioAreaO(user.getId(), areaId));
            }
        });
        return fxList;
    }

    public boolean addUsuarioAreaCTL(UsuarioAreaO obj) {
        return usuarioAreaDal.addUsuarioArea(obj);
    }

    public boolean removeUserAreaCTL(int idUser, int idArea) {
        return usuarioAreaDal.removeUsuarioArea(idUser, idArea);
    }

    public ObservableList<UsuarioAreaO> getUserAreaByRutJefe(String rutJefe) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        if (!userCtl.getUsersByRutJefe(rutJefe).isEmpty()) {
            userCtl.getUsersByRutJefe(rutJefe).stream().forEach((usuario) -> {
                if (!usuarioAreaDal.getUserAreas().isEmpty()) {
                    usuarioAreaDal.getUserAreas().stream().filter((usuarioArea) -> (usuario.getId() == usuarioArea.getUsuario_id())).forEach((usuarioArea) -> {
                        fxList.add(usuarioArea);
                    });
                }
            });
        }
        return fxList;
    }
}
