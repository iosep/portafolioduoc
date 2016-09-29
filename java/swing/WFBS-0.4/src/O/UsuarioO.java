/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

import java.sql.Date;

/**
 *
 * @author iosep
 */
public class UsuarioO {

    private int id;
    private int rut;
    private String dv;
    private String apaterno;
    private String amaterno;
    private String nombres;
    private String sexo;
    private String direccion;
    private int fono;
    private String email;
    private String clave;
    private int jefe_id;
    private int rol_id;
    private int activo;
    private Date creado;
    private Date modificado;
    private Date desactivado;

    public UsuarioO() {
    }

    public UsuarioO(int rut, String dv, String apaterno, String amaterno, String nombres, String sexo, String direccion, int fono, String email, String clave, int jefe_id, int rol_id, int activo, Date creado, Date modificado, Date desactivado) {
        this.rut = rut;
        this.dv = dv;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.nombres = nombres;
        this.sexo = sexo;
        this.direccion = direccion;
        this.fono = fono;
        this.email = email;
        this.clave = clave;
        this.jefe_id = jefe_id;
        this.rol_id = rol_id;
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

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getJefe_id() {
        return jefe_id;
    }

    public void setJefe_id(int jefe_id) {
        this.jefe_id = jefe_id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
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
