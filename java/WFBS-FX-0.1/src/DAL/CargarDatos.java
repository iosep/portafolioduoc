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
    private final ArrayList<UsuarioO> users = new ArrayList<>();

    public void cargarUsuarios() {
        Date fecha = new Date();
        user1 = new UsuarioO(1, 15424261, "9",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "José", "Oñate", 99887766, "ono@ono.com", "H", 1, fecha);
        user2 = new UsuarioO(1, 12312312, "3",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Jano", "Yáñez", 88776655, "ono@ono.com", "H", 2, fecha);
        user3 = new UsuarioO(1, 11111111, "1",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Amelia", "López", 77665544, "ono@ono.com", "M", 2, fecha);
        user4 = new UsuarioO(1, 13131313, "6",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Ana", "Zar", 77665544, "ono@ono.com", "M", 3, fecha);
        user5 = new UsuarioO(1, 77777777, "7",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                "Thor", "Aven", 77665544, "ono@ono.com", "H", 3, fecha);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    public ArrayList<UsuarioO> mostrarUsuarios() {
        cargarUsuarios();
        return users;
    }
}
