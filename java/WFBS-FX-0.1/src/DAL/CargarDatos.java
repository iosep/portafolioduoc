/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.UsuarioO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author iosep
 */
public class CargarDatos {

    private static UsuarioO user1;
    private static UsuarioO user2;
    private static UsuarioO user3;
    private static UsuarioO user4;
    private static UsuarioO user5;
    private static ArrayList<UsuarioO> users;

    public void cargarUsuarios() {
        users = new ArrayList<>();

        Date fecha = new Date();
        user1 = new UsuarioO("15424261-9",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Admin", "", "José", "Oñate", "ono@ono.com", "H", 99887766, 1, fecha, null, null);
        user2 = new UsuarioO("12312312-3",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Jefa", "", "Jano", "Yáñez", "ono@ono.com", "H", 88776655, 1, fecha, null, null);
        user3 = new UsuarioO("11111111-1",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Jefa", "", "Amelia", "López", "ono@ono.com", "M", 77665544, 1, fecha, null, null);
        user4 = new UsuarioO("13131313-6",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Funcionaria", "12312312-3", "Ana", "Zar", "ono@ono.com", "M", 77665544, 1, fecha, null, null);
        user5 = new UsuarioO("77777777-7",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Funcionaria", "11111111-1", "Thor", "Aven", "ono@ono.com", "H", 77665544, 1, fecha, null, null);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    public ArrayList<UsuarioO> mostrarUsuarios() {
        return users;
    }

    public boolean agregarUsuario(UsuarioO ufx) {
        return users.add(ufx);
    }
}
