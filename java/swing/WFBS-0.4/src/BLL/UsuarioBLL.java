/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.UsuarioDAO;
import O.UsuarioO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioBLL {

    private UsuarioDAO ud;//add final

    public ArrayList<UsuarioO> getUsuarios() {
        ArrayList<UsuarioO> alu = new ArrayList<>();
        return alu;
    }

    public UsuarioO getUsuarioByRut(int rut) {
        UsuarioO uo = new UsuarioO();
        return uo;
    }

}
