/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Jefes;
import Model.UsuarioModel;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioController {

    private static UsuarioModel um;

    public ArrayList<String> listaJefesController() {
        um = new UsuarioModel();
        ArrayList<String> jefes = new ArrayList<>();
        um.listaJefes().forEach((jefe) -> {
            jefes.add(jefe.getNombre());
        });
        return jefes;
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
}
