/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.UsuarioO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioDAO {

    private final CargarDatos cd = new CargarDatos();
    private static UsuarioO uo;

    public ArrayList<UsuarioO> getUsuarios() {
        return cd.mostrarUsuarios();
    }

    public UsuarioO getUsuarioByRut(int rut) {
        cd.mostrarUsuarios().stream().forEach((u) -> {
            if (u.getRut() == rut) {
                uo = u;
            }
        });
        return uo;
    }
}
