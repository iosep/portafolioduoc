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

    private final AaaInitialLoad cd = new AaaInitialLoad();
    private static UsuarioO uo;

    public ArrayList<UsuarioO> getUsuarios() {
        return cd.mostrarUsuarios();
    }

    public UsuarioO getUsuarioById(int id) {
        cd.mostrarUsuarios().stream().forEach((item) -> {
            if (item.getId() == id) {
                uo = item;
            }
        });
        return uo;
    }

    public UsuarioO getUsuarioByRut(String rut) {
        cd.mostrarUsuarios().stream().forEach((u) -> {
            if (u.getRut().equals(rut)) {
                uo = u;
            }
        });
        return uo;
    }

    public ObservableList<UsuarioO> getUsuariosByRol(int rol) {
        ObservableList<UsuarioO> usuariosRol = FXCollections.observableArrayList();
        cd.mostrarUsuarios().stream().forEach((u) -> {
            if (u.getRol() == rol) {
                usuariosRol.add(u);
            }
        });
        return usuariosRol;
    }

    public boolean addUsuario(UsuarioO ufx) {
        return cd.agregarUsuario(ufx);
    }
}
