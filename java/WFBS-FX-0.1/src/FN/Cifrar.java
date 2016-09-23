/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FN;

import CTL.UsuarioCTL;
import O.UsuarioO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author iosep
 */
public class Cifrar {

    private final ThreadLocal<Random> random = new ThreadLocal<>();
    private final UsuarioCTL uc = new UsuarioCTL();

    public boolean validatePassword(int rut, String password) {
        boolean vb = false;
        try {
            UsuarioO uo = uc.getUsuarioByRut(rut);
            String hashedAndSalted = uo.getClave();
            String salt = hashedAndSalted.split(",")[1];

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
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(saltedAndHashed.getBytes());
            //BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            //return encoder.encode(hashedBytes) + "," + salt;
            return DatatypeConverter.printBase64Binary(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no está disponible", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("¿UTF-8?", e);
        }
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
