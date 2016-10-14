/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.UsuarioO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioDAL {

    private final AInitLoad load = new AInitLoad();
    private static UsuarioO uo;

    public ArrayList<UsuarioO> getUsuarios() {
        return load.mostrarUsuarios();
    }

    public UsuarioO getUsuarioById(int id) {
        load.mostrarUsuarios().stream().forEach((item) -> {
            if (item.getId() == id) {
                uo = item;
            }
        });
        return uo;
    }

    public UsuarioO getUsuarioByRut(String rut) {
        load.mostrarUsuarios().stream().forEach((u) -> {
            if (u.getRut().equals(rut)) {
                uo = u;
            }
        });
        return uo;
    }

    public ObservableList<UsuarioO> getUsuariosByRol(int rol) {
        ObservableList<UsuarioO> usuariosRol = FXCollections.observableArrayList();
        load.mostrarUsuarios().stream().forEach((u) -> {
            if (u.getRol() == rol) {
                usuariosRol.add(u);
            }
        });
        return usuariosRol;
    }

    public boolean addUsuario(UsuarioO ufx) {
        return load.agregarUsuario(ufx);
    }
}
