/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author GRUPO2
 */
public class LoginController {

    private static int userRUT = 0;
    private static int userRol = 0;
    private final UsuarioController uc = new UsuarioController();
    private final ShaSalt ss = new ShaSalt();

    public boolean usuarioLogin(int rut, String clave) {
        boolean vb = false;
        userRUT = ss.validatePass(rut, clave);
        if (userRUT != 0) {
            vb = true;
        }
        return vb;
    }

    public int activeUserRUT() {
        return userRUT;
    }

    public int activeUserRol() {
        Usuario activeUser = uc.findUserController(userRUT);
        userRol = activeUser.getRol();
        return userRol;
    }

    public void setActiveUserRol(int ur) {
        userRol = ur;
    }

    public void logOut() {
        userRUT = 0;
        userRol = 0;
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
