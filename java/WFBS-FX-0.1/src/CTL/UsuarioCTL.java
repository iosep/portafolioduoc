/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.UsuarioDAO;
import O.UsuarioO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class UsuarioCTL {

    private final UsuarioDAO ud = new UsuarioDAO();

    public ArrayList<UsuarioO> getUsuarios() {
        ArrayList<UsuarioO> alu = new ArrayList<>();
        return alu;
    }

    public UsuarioO getUsuarioByRut(int rut) {
        return ud.getUsuarioByRut(rut);
    }

    public ObservableList<UsuarioO> getUsuariosFX() {
        ObservableList<UsuarioO> users = FXCollections.observableArrayList();
        ud.getUsuarios().stream().forEach((user) -> {
            users.add(user);
        });
        return users;
    }

}
