/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UsuarioModel;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioController {

    private static UsuarioModel um;

    public ArrayList<Jefes> listaJefesController() {
        um = new UsuarioModel();
        return um.listaJefes();
    }

    public int getIdJefe(String nombre) {
        int id = 0;
        for (Jefes jefe : um.listaJefes()) {
            if (nombre.equals(jefe.getNombre())) {
                id = jefe.getId();
            }
        }
        return id;
    }

    public Usuario findUserController(int rut) {
        um = new UsuarioModel();
        ArrayList<Usuario> users = um.findUser(rut);
        Usuario foundUser = null;
        for (Usuario user : users) {
            if (user.getRol() == 1) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    public boolean crearUsuarioController(int rut, String dv, String sexo, String clave) {
        um = new UsuarioModel();
        return um.crearUsuario(rut, dv, sexo, clave);
    }
}
