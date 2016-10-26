/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

import CTL.AreaCTL;
import CTL.UsuarioCTL;

/**
 *
 * @author iosep
 */
public class UsuarioAreaO {

    private int usuario_id;
    private int area_id;
    private String usuarioRut;
    private String areaNombre;
    private int activo;

    public UsuarioAreaO() {
    }

    public UsuarioAreaO(int usuario_id, int area_id) {
        this.usuario_id = usuario_id;
        this.area_id = area_id;
    }

    public String getUsuarioRut() {
        return usuarioRut;
    }

    public void setUsuarioRut(String usuarioRut) {
        this.usuarioRut = usuarioRut;
    }

    public String getAreaNombre() {
        return areaNombre;
    }

    public void setAreaNombre(String areaNombre) {
        this.areaNombre = areaNombre;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

}
