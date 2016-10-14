/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.RespuestaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class RespuestaDAL {

    private final AInitLoad aaa = new AInitLoad();
    private static RespuestaO respuestaObj;

    public ArrayList<RespuestaO> getRespuestas() {
        return aaa.mostrarRespuestas();
    }

    public RespuestaO getRespuestaById(int id) {
        aaa.mostrarRespuestas().stream().forEach((each) -> {
            if (each.getId() == id) {
                respuestaObj = each;
            }
        });
        return respuestaObj;
    }

    public boolean addRespuesta(RespuestaO obj) {
        return aaa.agregarRespuesta(obj);
    }

}
