/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FN;

import CTL.UsuarioCTL;
import O.UsuarioO;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author iosep
 */
public class Cifrar {

    private final ThreadLocal<Random> random = new ThreadLocal<>();
    private final UsuarioCTL uc = new UsuarioCTL();

    public boolean validatePassword(String rut, String password) {
        boolean vb = false;
        try {
            //System.out.println("rut: " + rut);
            //System.out.println("password: " + password);
            rut = rut.trim();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            String dv = rut.substring(rut.length() - 1);
            rut = rut.substring(0, rut.length() - 1);
            rut = rut + "-" + dv;
            UsuarioO uo = uc.getUsuarioByRut(rut);
            String hashedAndSalted = uo.getClave();
            String salt = hashedAndSalted.split(",")[1];
            //System.out.println("hash&salt: " + hashedAndSalted);
            //System.out.println("salt: " + salt);
            //System.out.println("makePassHash: " + makePasswordHash(password, salt));

            if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
                System.out.println("Password no corresponde");
            } else {
                vb = true;
            }
        } catch (Exception e) {
            System.out.println("validatePassword catch: " + e.getMessage());
        }
        return vb;
    }

    public String hashPassword(String password) {
        return makePasswordHash(password, Integer.toString(getRandom().nextInt()));
    }

    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            byte hashedBytes[];
            hashedBytes = saltedAndHashed.getBytes();
            return DatatypeConverter.printBase64Binary(hashedBytes) + "," + salt;
        } catch (Exception e) {
            System.out.println("makePasswordHash catch: " + e.getMessage());
        }
        return "makePasswordHash=null";
    }

    private Random getRandom() {
        Random result = random.get();
        if (result == null) {
            result = new Random();
            random.set(result);
        }
        return result;
    }

}
