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

    //private final AaaInitialLoad AaaInitialLoad = new AaaInitialLoad();
    private static UsuarioAreaO uao;

    public ArrayList<UsuarioAreaO> getUserAreas() {
        return AInitLoad.mostrarUsuarioAreas();
    }

    public ArrayList<UsuarioAreaO> getUserAreasByUserId(int id) {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        AInitLoad.mostrarUsuarioAreas().stream().forEach((u) -> {
            if (u.getUsuario_id() == id) {
                list.add(u);
            }
        });
        return list;
    }

    public ArrayList<UsuarioAreaO> getUserAreasByAreaId(int id) {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        AInitLoad.mostrarUsuarioAreas().stream().forEach((item) -> {
            if (item.getArea_id() == id) {
                list.add(item);
            }
        });
        return list;
    }

    public boolean addUsuarioArea(UsuarioAreaO obj) {
        return AInitLoad.agregarUsuarioArea(obj);
    }

    public boolean removeUsuarioArea(int idUser, int idArea) {
        return AInitLoad.eliminarUsuarioArea(idUser, idArea);
    }

}
