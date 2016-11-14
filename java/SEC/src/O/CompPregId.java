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
public class CompPregId {

    private final int compId;
    private final int pregId;

    public CompPregId(int compId, int pregId) {
        this.compId = compId;
        this.pregId = pregId;
    }

    public int getCompId() {
        return compId;
    }

    public int getPregId() {
        return pregId;
    }
}
