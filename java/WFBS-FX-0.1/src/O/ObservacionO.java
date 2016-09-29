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
public class ObservacionO {

    private static int fakeId = 0;

    private int id;
    private int nivel_inf;
    private int nivel_sup;
    private String msj_inf;
    private String msj_sup;
    private int competencia_id;
    private int activo;
    private Date creado;
    private Date modificado;
    private Date desactivado;

    public ObservacionO() {
    }

    public ObservacionO(int nivel_inf, int nivel_sup, String msj_inf, String msj_sup, int competencia_id, int activo, Date creado, Date modificado, Date desactivado) {

        this.id = ++fakeId;

        this.nivel_inf = nivel_inf;
        this.nivel_sup = nivel_sup;
        this.msj_inf = msj_inf;
        this.msj_sup = msj_sup;
        this.competencia_id = competencia_id;
        this.activo = activo;
        this.creado = creado;
        this.modificado = modificado;
        this.desactivado = desactivado;
    }

    public static int getFakeId() {
        return fakeId;
    }

    public static void setFakeId(int fakeId) {
        ObservacionO.fakeId = fakeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivel_inf() {
        return nivel_inf;
    }

    public void setNivel_inf(int nivel_inf) {
        this.nivel_inf = nivel_inf;
    }

    public int getNivel_sup() {
        return nivel_sup;
    }

    public void setNivel_sup(int nivel_sup) {
        this.nivel_sup = nivel_sup;
    }

    public String getMsj_inf() {
        return msj_inf;
    }

    public void setMsj_inf(String msj_inf) {
        this.msj_inf = msj_inf;
    }

    public String getMsj_sup() {
        return msj_sup;
    }

    public void setMsj_sup(String msj_sup) {
        this.msj_sup = msj_sup;
    }

    public int getCompetencia_id() {
        return competencia_id;
    }

    public void setCompetencia_id(int competencia_id) {
        this.competencia_id = competencia_id;
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
