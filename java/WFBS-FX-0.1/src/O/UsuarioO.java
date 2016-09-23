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

    private int id;
    private int jefe_id;
    private int rut;
    private String dv;
    private String clave;
    private String nombre;
    private String apellido;
    private int fono;
    private String email;
    private String sexo;
    private int rol_id;
    private Date creado;

    public UsuarioO() {
    }

    public UsuarioO(int jefe_id, int rut, String dv, String clave, String nombre, String apellido, int fono, String email, String sexo, int rol_id, Date creado) {
        this.jefe_id = jefe_id;
        this.rut = rut;
        this.dv = dv;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fono = fono;
        this.email = email;
        this.sexo = sexo;
        this.rol_id = rol_id;
        this.creado = creado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJefe_id() {
        return jefe_id;
    }

    public void setJefe_id(int jefe_id) {
        this.jefe_id = jefe_id;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public int getFono() {
        return fono;
    }

    public void setFono(int fono) {
        this.fono = fono;
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

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

}
