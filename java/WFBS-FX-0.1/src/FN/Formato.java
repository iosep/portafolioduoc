/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FN;

/**
 *
 * @author iosep
 */
public class Formato {
    
    public String formatoRut(String rut) {
        rut = rut.trim();
        rut = rut.replace(" ", "");
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        String dv = rut.substring(rut.length() - 1, rut.length());
        rut = rut.substring(0, rut.length() - 1);
        return rut + "-" + dv;
    }
    
}
