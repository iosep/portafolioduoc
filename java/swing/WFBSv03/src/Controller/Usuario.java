/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author iosep
 */
public class Usuario {
    private int id;
    private int rut;
    private char dv;
    private String nombres;
    private String apaterno;
    private String amaterno;
    private char sexo;
    private String direccion;
    private int fono;
    private String email;
    private String clave;
    private int idjefe;
    private int rol;
    private int activo;

    public Usuario() {
    }

    public Usuario(int id, int rut, char dv, String nombres, String apaterno, String amaterno, char sexo, String direccion, int fono, String email, String clave, int idjefe, int rol, int activo) {
        this.id = id;
        this.rut = rut;
        this.dv = dv;
        this.nombres = nombres;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.sexo = sexo;
        this.direccion = direccion;
        this.fono = fono;
        this.email = email;
        this.clave = clave;
        this.idjefe = idjefe;
        this.rol = rol;
        this.activo = activo;
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

    public char getDv() {
        return dv;
    }

    public void setDv(char dv) {
        this.dv = dv;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
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

    public int getIdjefe() {
        return idjefe;
    }

    public void setIdjefe(int idjefe) {
        this.idjefe = idjefe;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
}
