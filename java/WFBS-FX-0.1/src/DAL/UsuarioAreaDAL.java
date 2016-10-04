/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.UsuarioAreaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioAreaDAL {

    private final AaaInitialLoad aaa = new AaaInitialLoad();
    private static UsuarioAreaO uao;

    public ArrayList<UsuarioAreaO> getUserAreas() {
        return aaa.mostrarUsuarioAreas();
    }

    public ArrayList<UsuarioAreaO> getUserAreasByUserId(int id) {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        aaa.mostrarUsuarioAreas().stream().forEach((u) -> {
            if (u.getUsuario_id() == id) {
                list.add(u);
            }
        });
        return list;
    }

    public boolean addUsuarioArea(UsuarioAreaO obj) {
        return aaa.agregarUsuarioArea(obj);
    }

    public boolean removeUsuarioArea(int idUser, int idArea) {
        return aaa.eliminarUsuarioArea(idUser, idArea);
    }

}
