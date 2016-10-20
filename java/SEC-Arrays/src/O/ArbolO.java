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
public class ArbolO {

    private final String texto;
    private final int id;

    public ArbolO(String texto, int id) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return texto;
    }
}
