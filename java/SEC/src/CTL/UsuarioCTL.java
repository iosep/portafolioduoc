/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.UsuarioDAL;
import O.UsuarioO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioCTL {

    private final UsuarioDAL ud = new UsuarioDAL();

    public ArrayList<UsuarioO> getUsuarios() {
        ArrayList<UsuarioO> alu = new ArrayList<>();
        return alu;
    }

    public UsuarioO getUsuarioByRut(String rut) {
        return ud.getUsuarioByRut(rut);
    }

    public UsuarioO getUsuarioById(int id) {
        return ud.getUsuarioById(id);
    }

    public ObservableList<UsuarioO> getUsuariosByRol(int rol) {
        return ud.getUsuariosByRol(rol);
    }

    public ObservableList<UsuarioO> getUsuariosFX() {
        ObservableList<UsuarioO> users = FXCollections.observableArrayList();
        ud.getUsuarios().stream().forEach((user) -> {
            users.add(user);
        });
        return users;
    }

    public boolean addUsuarioCTL(UsuarioO ufx) {
        return ud.addUsuario(ufx);
    }

    public ObservableList<UsuarioO> getFuncionariosByRutJefe(String rutJefe) {
        ObservableList<UsuarioO> funcionarios = FXCollections.observableArrayList();
        ud.getUsuariosByRol(3).stream().filter((user) -> (user.getRut_jefe().equals(rutJefe))).forEach((user) -> {
            funcionarios.add(user);
        });
        return funcionarios;
    }

}