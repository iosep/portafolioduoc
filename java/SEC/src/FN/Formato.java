/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FN;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author iosep
 */
public class Formato {

    public static String formatoRut(String rut) {
        rut = rut.trim();
        rut = rut.replace(" ", "");
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        String dv = rut.substring(rut.length() - 1, rut.length()).toUpperCase();
        rut = rut.substring(0, rut.length() - 1);
        return rut + "-" + dv;
    }

    public static Date stringToDate(String fecha) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String part[] = fecha.split("T");
            Date d = df.parse(part[0]);
            return d;
        } catch (Exception e) {
            System.out.println("stringToDateFormato catch: " + e.getMessage());
        }
        return null;
    }
}
