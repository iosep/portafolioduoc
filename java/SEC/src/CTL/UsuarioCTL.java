/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.UsuarioDAL;
import O.UsuarioO;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioCTL {

    private final UsuarioDAL ud = new UsuarioDAL();

    public UsuarioO getUsuarioByRut(String rut) {
        return ud.getUsuarioByRut(rut);
    }

    /*
    public boolean checkUsuarioByRut(String rut) {
        UsuarioO uRut = ud.getUsuarioByRut(rut);
        return uRut.getId() > 0;
    }
     */
    public UsuarioO getUsuarioById(int id) {
        /*UsuarioO u = ud.getUsuarioById(id);
        if (u.getActivo() == 1) {
            return u;
        } else {
            return null;
        }*/
        return ud.getUsuarioById(id);
    }

    public ObservableList<UsuarioO> getUsuariosByRolFX(int rol) {
        ObservableList<UsuarioO> obList = FXCollections.observableArrayList();
        ud.getUsuarios().stream().filter((u) -> (u.getActivo() == 1 && u.getRolid() == rol)).forEach((u) -> {
            obList.add(u);
        });
        return obList;
    }

    public ObservableList<UsuarioO> getUsuariosFX() {
        ObservableList<UsuarioO> obList = FXCollections.observableArrayList();
        ud.getUsuarios().stream().filter((u) -> (u.getActivo() == 1)).forEach((u) -> {
            obList.add(u);
        });
        return obList;
    }

    public boolean addUsuarioCTL(UsuarioO obj) {
        return ud.addUsuario(obj);
    }

    public ObservableList<UsuarioO> getFuncionariosByRutJefeFX(String rutJefe) {
        ObservableList<UsuarioO> funcionarios = FXCollections.observableArrayList();
        this.getUsuariosByRolFX(3).stream().filter((user) -> (user.getRutjefe().equals(rutJefe))).forEach((user) -> {
            funcionarios.add(user);
        });
        return funcionarios;
    }

    public String logInCtl(String rut, String clave) throws IOException {
        return ud.logIn(rut, clave);
    }

    public ObservableList<UsuarioO> getJefesFX() {
        ObservableList<UsuarioO> obList = FXCollections.observableArrayList();
        ud.getJefes().stream().filter((u) -> (u.getActivo() == 1)).forEach((u) -> {
            obList.add(u);
        });
        return obList;
    }

    public boolean deleteUser(int id) {
        return ud.deleteUser(id);
    }

    public boolean updateUser(UsuarioO obj) {
        return ud.updateUser(obj);
    }

    public boolean updatePass(UsuarioO obj) {
        return ud.updatePass(obj);
    }

    public boolean activaUsuario(int id) {
        return ud.activaUser(id);
    }
}
