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

    private int id;
    private int encuesta_id;
    private int respuesta_id;
    private int puntos;
    private int pregunta_id;

    public SeleccionO() {
    }

    public SeleccionO(int encuesta_id, int respuesta_id) {
        this.encuesta_id = encuesta_id;
        this.respuesta_id = respuesta_id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }

    public void setRespuesta_id(int respuesta_id) {
        this.respuesta_id = respuesta_id;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

}
