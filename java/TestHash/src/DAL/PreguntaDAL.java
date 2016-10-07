/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.PreguntaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class PreguntaDAL {

    private final AaaInitialLoad aaa = new AaaInitialLoad();
    private static PreguntaO preguntaObj;

    public ArrayList<PreguntaO> getPreguntas() {
        return aaa.mostrarPreguntas();
    }

    public PreguntaO getPreguntaById(int id) {
        aaa.mostrarPreguntas().stream().forEach((each) -> {
            if (each.getId() == id) {
                preguntaObj = each;
            }
        });
        return preguntaObj;
    }

    public boolean addPregunta(PreguntaO obj) {
        return aaa.agregarPregunta(obj);
    }

}
