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
public class UsuarioO {

    private String rut;
    private String clave;
    private int rol;
    private String rut_jefe;
    private String nombre;
    private String apellido;
    private String email;
    private String sexo;
    private int fono;
    private int activo;
    private Date creado;
    private Date modificado;
    private Date desactivado;
    private String rolString;

    public UsuarioO() {
    }

    public UsuarioO(String rut, String clave, int rol, String rut_jefe, String nombre, String apellido, String email, String sexo, int fono, int activo, Date creado, Date modificado, Date desactivado) {
        this.rut = rut;
        this.clave = clave;
        this.rol = rol;
        this.rut_jefe = rut_jefe;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.sexo = sexo;
        this.fono = fono;
        this.activo = activo;
        this.creado = creado;
        this.modificado = modificado;
        this.desactivado = desactivado;
        switch (rol) {
            case 1:
                this.rolString = "Admin";
                break;
            case 2:
                this.rolString = "Jefa";
                break;
            case 3:
                this.rolString = "Funcionaria";
                break;
            default:
                break;
        }
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getRut_jefe() {
        return rut_jefe;
    }

    public void setRut_jefe(String rut_jefe) {
        this.rut_jefe = rut_jefe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getFono() {
        return fono;
    }

    public void setFono(int fono) {
        this.fono = fono;
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

    public String getRolString() {
        return rolString;
    }

    public void setRolString(String rolString) {
        this.rolString = rolString;
    }

    @Override
    public String toString() {
        return rut;
    }
}
