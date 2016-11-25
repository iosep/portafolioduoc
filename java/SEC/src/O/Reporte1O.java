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
public class Reporte1O {
    private final String comp;
    private final int result;
    private final int nopt;
    private final String brecha;

    public Reporte1O(String comp, int result, int nopt, String brecha) {
        this.comp = comp;
        this.result = result;
        this.nopt = nopt;
        this.brecha = brecha;
    }

    public String getComp() {
        return comp;
    }

    public int getResult() {
        return result;
    }

    public int getNopt() {
        return nopt;
    }

    public String getBrecha() {
        return brecha;
    }
    
}
