/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author iosep
 */
public class Validar {

    private final String PATTERN_EMAIL
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final Pattern PATTERN_IPV4 = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public boolean validateIpv4(final String ip) {
        return PATTERN_IPV4.matcher(ip).matches();
    }

    public boolean validatePort(String port) {
        return this.validarInteger(port) && Integer.parseInt(port) < 65536;
    }

    /**
     * Validate given email with regular expression.
     *
     * @param email email for validation
     * @return true valid email, otherwise false
     */
    public boolean validarEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validate given rut with algorithm
     *
     * @param rut rut for validation
     * @return true valid, otherwise false
     */
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
        } catch (Exception e) {
            System.out.println("validarRut catch: " + e.getMessage());
        }
        return validacion;
    }

    public boolean validarInteger(String entero) {
        boolean vb = false;
        try {
            int x = Integer.parseInt(entero);
            vb = true;
        } catch (Exception e) {
            System.out.println("validarInteger catch: " + e.getMessage());
        }
        return vb;
    }

}
