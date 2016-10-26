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

    private int id;
    private String respuesta;
    private int puntos;
    private int pregunta_id;
    private String preguntaNombre;
    private Date creado;
    private Date modificado;
    private int activo;

    public RespuestaO() {
    }

    public RespuestaO(String respuesta, int puntos, int pregunta_id) {
        this.respuesta = respuesta;
        this.puntos = puntos;
        this.pregunta_id = pregunta_id;
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

    public String getPreguntaNombre() {
        return preguntaNombre;
    }

    public void setPreguntaNombre(String preguntaNombre) {
        this.preguntaNombre = preguntaNombre;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
