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
public class RolO {

    private int id;
    private String nombre;

    private static int fakeId = 0;

    public RolO() {
    }

    public RolO(String nombre) {
        this.nombre = nombre;

        this.id = ++fakeId;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
