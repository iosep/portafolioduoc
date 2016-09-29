/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UsuarioModel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author iosep
 */
public class UsuarioController {

    private static UsuarioModel um;

    public ArrayList<Jefes> listaJefesController() {
        um = new UsuarioModel();
        return um.listaJefes();
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

    public Usuario findUserController(int rut) {
        um = new UsuarioModel();
        Usuario foundUser = um.findUser(rut);
        return foundUser;
    }

    public boolean crearUsuarioController(int rut, String dv, String nombres, String apaterno, String amaterno,
            String sexo, String direccion, int fono, String email, String clave, int idjefe, int rol, int estado) {
        um = new UsuarioModel();
        return um.crearUsuario(rut, dv, nombres, apaterno, amaterno, sexo, direccion, fono, email, clave, idjefe, rol, estado);
    }

    private static final String PATTERN_EMAIL
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate given email with regular expression.
     *
     * @param email email for validation
     * @return true valid email, otherwise false
     */
    public boolean validateEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
