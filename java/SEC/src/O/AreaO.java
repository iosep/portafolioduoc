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
public class AreaO {

    static int fakeId = 0;

    private int id;
    private String nombre;
    private String sigla;
    private int activo;
    private Date creado;
    private Date modificado;
    private Date desactivado;

    public AreaO() {
    }

    public AreaO(String nombre, String sigla, int activo, Date creado, Date modificado, Date desactivado) {
//fake area id
        this.id = ++fakeId;
        this.nombre = nombre;
        this.sigla = sigla;
        this.activo = activo;
        this.creado = creado;
        this.modificado = modificado;
        this.desactivado = desactivado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
