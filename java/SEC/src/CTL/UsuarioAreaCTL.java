/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.UsuarioAreaDAL;
import O.CompetenciaNivelO;
import O.UsuarioAreaO;
import O.UsuarioO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioAreaCTL {

    private final UsuarioAreaDAL usuarioAreaDal = new UsuarioAreaDAL();
    private final AreaCTL areaCtl = new AreaCTL();
    private final UsuarioCTL userCtl = new UsuarioCTL();

    public ObservableList<UsuarioAreaO> getUsuarioAreasByUserIdFX(int id) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        usuarioAreaDal.getUserAreasByUserId(id).stream().forEach((each) -> {
            if (each.getActivo() == 1) {
                fxList.add(each);
            }
        });
        return fxList;
    }

    public ObservableList<UsuarioAreaO> getUsuarioAreasByAreaIdFX(int id) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        usuarioAreaDal.getUserAreasByAreaId(id).stream().forEach((each) -> {
            if (each.getActivo() == 1) {
                fxList.add(each);
            }
        });
        return fxList;
    }

    public ObservableList<UsuarioAreaO> getAreasDisponiblesByUserFX(int userId) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        ArrayList<UsuarioAreaO> uAs = usuarioAreaDal.getUserAreas();
        areaCtl.getAreasFX().stream().filter((area) -> (area.getActivo() == 1)).forEach((area) -> {
            boolean add = true;
            for (UsuarioAreaO userArea : uAs) {
                if (userArea.getActivo() == 1 && userArea.getUsuario_id() == userId && userArea.getArea_id() == area.getId()) {
                    add = false;
                    break;
                }
            }
            if (add) {
                UsuarioAreaO ua1 = new UsuarioAreaO(userId, area.getId());
                ua1.setAreaNombre(area.getNombre());
                fxList.add(ua1);
            }
        });
        return fxList;
    }

    public ObservableList<UsuarioAreaO> getFuncionariosDisponiblesByAreaFX(int areaId) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        ArrayList<UsuarioAreaO> uAs = usuarioAreaDal.getUserAreas();
        userCtl.getUsuariosByRolFX(3).stream().forEach((user) -> {
            boolean add = true;
            for (UsuarioAreaO userArea : uAs) {
                if (userArea.getActivo() == 1 && userArea.getArea_id() == areaId && userArea.getUsuario_id() == user.getId()) {
                    add = false;
                    break;
                }
            }
            if (add) {
                UsuarioAreaO ua1 = new UsuarioAreaO(user.getId(), areaId);
                ua1.setUsuarioRut(user.getRut());
                fxList.add(ua1);
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

    public ArrayList<UsuarioAreaO> getUserAreaActivas() {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        usuarioAreaDal.getUserAreas().stream().filter((ua) -> (ua.getActivo() == 1)).forEach((ua) -> {
            list.add(ua);
        });
        return list;
    }

    public ObservableList<UsuarioAreaO> getFuncionarioUserAreaByRutJefeFX(String rutJefe) {
        ObservableList<UsuarioAreaO> fxList = FXCollections.observableArrayList();
        ArrayList<UsuarioAreaO> uAs = this.getUserAreaActivas();
        ObservableList<UsuarioO> fRJs = userCtl.getFuncionariosByRutJefeFX(rutJefe);
        if (!fRJs.isEmpty()) {
            fRJs.stream().filter((funcionario) -> (!uAs.isEmpty())).forEach((funcionario) -> {
                uAs.stream().filter((usAr) -> (funcionario.getId() == usAr.getUsuario_id())).forEach((usAr) -> {
                    fxList.add(usAr);
                });
            });
        }
        return fxList;
    }

    public boolean deleteByUser(int userId) {
        boolean del = false;
        for (UsuarioAreaO item : this.getUserAreaActivas()) {
            if (userId == item.getUsuario_id()) {
                del = this.removeUserAreaCTL(item.getUsuario_id(), item.getArea_id());
            }
        }
        return del;
    }

    public boolean deleteByArea(int areaId) {
        boolean del = false;
        for (UsuarioAreaO item : this.getUserAreaActivas()) {
            if (areaId == item.getArea_id()) {
                del = this.removeUserAreaCTL(item.getUsuario_id(), item.getArea_id());
            }
        }
        return del;
    }
    
}
