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
public class EncuestaO {

    private final int id;
    private final int usuario_id;
    private final int jefe_id;
    private final int periodo_id;

    static int fakeId = 0;

    public EncuestaO(int usuario_id, int jefe_id, int periodo_id) {
        this.usuario_id = usuario_id;
        this.jefe_id = jefe_id;
        this.periodo_id = periodo_id;
        this.id = ++fakeId;
    }

    public int getId() {
        return id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public int getJefe_id() {
        return jefe_id;
    }

    public int getPeriodo_id() {
        return periodo_id;
    }

}
