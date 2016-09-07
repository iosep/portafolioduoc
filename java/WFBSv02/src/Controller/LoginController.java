/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LoginModel;

/**
 *
 * @author GRUPO2
 */
public class LoginController {

    private static int userId = 0;

    public boolean usuarioLogin(String email, String clave) {
        boolean vb = false;
        LoginModel lm = new LoginModel();
        userId = lm.userLogin(email, clave);
        if (userId != 0) {
            vb = true;
        }
        return vb;
    }

    public int activeUser() {
        return userId;
    }
}
