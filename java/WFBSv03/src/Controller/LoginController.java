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

    public boolean usuarioLogin(int rut, String clave) {
        boolean vb = false;
        LoginModel lm = new LoginModel();
        userId = lm.userLogin(rut, clave);
        if (userId != 0) {
            vb = true;
        }
        return vb;
    }

    public int activeUser() {
        return userId;
    }

    public void logOut() {
        userId = 0;
    }

    public boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
}
