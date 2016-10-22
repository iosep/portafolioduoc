/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.EncuestaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class EncuestaDAL {

    public static ArrayList<EncuestaO> getEncuestas() {
        return AInitLoad.showEncuestas();
    }

    public static boolean addEncuesta(EncuestaO obj) {
        return AInitLoad.addEncuesta(obj);
    }

    public static EncuestaO findEncuestaById(int id) {
        for (EncuestaO e : AInitLoad.showEncuestas()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

}
