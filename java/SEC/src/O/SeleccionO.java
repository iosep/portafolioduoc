/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

/**
 *
 * @author iosep
 */
public class SeleccionO {

    static int fakeId = 0;
    private final int id;
    private final int encuesta_id;
    private final int respuesta_id;

    public SeleccionO(int encuesta_id, int respuesta_id) {
        this.encuesta_id = encuesta_id;
        this.respuesta_id = respuesta_id;
        this.id = ++fakeId;
    }

    public int getId() {
        return id;
    }

    public int getEncuesta_id() {
        return encuesta_id;
    }

    public int getRespuesta_id() {
        return respuesta_id;
    }

}
