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
public class PeriodoO {

    private static int fakeId = 0;

    private int id;
    private Date inicio;
    private Date fin;
    private int jefe_porc;
    private int auto_porc;
    private int activo;
    private Date creado;
    private Date modificado;
    private Date desactivado;

    public PeriodoO() {
    }

    public PeriodoO(Date inicio, Date fin, int jefe_porc, int auto_porc, int activo, Date creado, Date modificado, Date desactivado) {

        this.id = ++fakeId;

        this.inicio = inicio;
        this.fin = fin;
        this.jefe_porc = jefe_porc;
        this.auto_porc = auto_porc;
        this.activo = activo;
        this.creado = creado;
        this.modificado = modificado;
        this.desactivado = desactivado;
    }

    public static int getFakeId() {
        return fakeId;
    }

    public static void setFakeId(int fakeId) {
        PeriodoO.fakeId = fakeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getJefe_porc() {
        return jefe_porc;
    }

    public void setJefe_porc(int jefe_porc) {
        this.jefe_porc = jefe_porc;
    }

    public int getAuto_porc() {
        return auto_porc;
    }

    public void setAuto_porc(int auto_porc) {
        this.auto_porc = auto_porc;
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
