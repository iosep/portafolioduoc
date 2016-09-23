/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.UsuarioDAO;
import O.UsuarioO;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioCTL {

    private UsuarioDAO ud;//add final

    public ArrayList<UsuarioO> getUsuarios() {
        ArrayList<UsuarioO> alu = new ArrayList<>();
        return alu;
    }

    public UsuarioO getUsuarioByRut(int rut) {
        Date d = new Date(2016, 9, 1);
        UsuarioO uo = new UsuarioO(15424261, "9", "Oñate", "Yáñez", "José A.", "H", "Calle 123, Comuna", 99887766, "ono@ono.com",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074", 1, 1, 1, d, null, null);
        return uo;
    }

}
