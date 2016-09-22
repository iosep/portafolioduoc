/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;
//import sun.misc.BASE64Encoder;

/**
 *
 * @author iosep
 */
public class ShaSalt {

    private final ThreadLocal<Random> random = new ThreadLocal<>();
    private final UsuarioController uc = new UsuarioController();

    public String hashPassword(String password) {
        return makePasswordHash(password, Integer.toString(getRandom().nextInt()));
    }

    public int validatePass(int rut, String password) {
        try {
            Usuario user = uc.findUserController(rut);
            String hashedAndSalted = user.getClave();
            String salt = hashedAndSalted.split(",")[1];

            if (!hashedAndSalted.equals(makePasswordHash(password, salt))) {
                System.out.println("Password no corresponde");
                return 0;
            } else {
                return user.getId();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
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
