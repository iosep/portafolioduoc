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
public class UsuarioAreaO {

    private int usuario_id;
    private int area_id;
    private String area_nombre;

    public UsuarioAreaO() {
    }

    public UsuarioAreaO(int usuario_id, int area_id, String area_nombre) {
        this.usuario_id = usuario_id;
        this.area_id = area_id;
        this.area_nombre = area_nombre;
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

    public String getArea_nombre() {
        return area_nombre;
    }

    public void setArea_nombre(String area_nombre) {
        this.area_nombre = area_nombre;
    }

    @Override
    public String toString() {
        return area_nombre;
    }
}
