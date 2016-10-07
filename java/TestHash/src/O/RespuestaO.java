/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

import java.util.Date;

/**
 *
 * @author iosep
 */
public class RespuestaO {

    private static int fakeId = 0;

    private int id;
    private String respuesta;
    private int puntos;
    private int pregunta_id;
    private int activo;
    private Date creado;
    private Date modificado;
    private Date desactivado;

    public RespuestaO() {
    }

    public RespuestaO(String respuesta, int puntos, int pregunta_id, int activo, Date creado, Date modificado, Date desactivado) {

        this.id = ++fakeId;

        this.respuesta = respuesta;
        this.puntos = puntos;
        this.pregunta_id = pregunta_id;
        this.activo = activo;
        this.creado = creado;
        this.modificado = modificado;
        this.desactivado = desactivado;
    }

    public static int getFakeId() {
        return fakeId;
    }

    public static void setFakeId(int fakeId) {
        RespuestaO.fakeId = fakeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public Date getDesactivado() {
        return desactivado;
    }

    public void setDesactivado(Date desactivado) {
        this.desactivado = desactivado;
    }

}
