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
public class PreguntaO {

    private static int fakeId = 0;

    private int id;
    private String pregunta;
    private int competencia_id;
    private Date creado;
    private Date modificado;

    public PreguntaO() {
    }

    public PreguntaO(String pregunta, int competencia_id, Date creado, Date modificado) {

        this.id = ++fakeId;

        this.pregunta = pregunta;
        this.competencia_id = competencia_id;
        this.creado = creado;
        this.modificado = modificado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getCompetencia_id() {
        return competencia_id;
    }

    public void setCompetencia_id(int competencia_id) {
        this.competencia_id = competencia_id;
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

}
