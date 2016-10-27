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
public class EncuestaO {

    private int id;
    private int usuario_id;
    private int evaluado_id;
    private int periodo_id;
    private Date fecha;

    public EncuestaO() {
    }

    public EncuestaO(int usuario_id, int evaluado_id, int periodo_id) {
        this.usuario_id = usuario_id;
        this.evaluado_id = evaluado_id;
        this.periodo_id = periodo_id;
        Date now = new Date();
        this.fecha = now;
    }

    public int getId() {
        return id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public int getEvaluado_id() {
        return evaluado_id;
    }

    public int getPeriodo_id() {
        return periodo_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setEvaluado_id(int evaluado_id) {
        this.evaluado_id = evaluado_id;
    }

    public void setPeriodo_id(int periodo_id) {
        this.periodo_id = periodo_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
