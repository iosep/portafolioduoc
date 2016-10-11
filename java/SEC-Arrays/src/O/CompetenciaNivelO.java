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
public class CompetenciaNivelO {

    private int competencia_id;
    private int nivel_id;

    public CompetenciaNivelO() {
    }

    public CompetenciaNivelO(int competencia_id, int nivel_id) {
        this.competencia_id = competencia_id;
        this.nivel_id = nivel_id;
    }

    public int getCompetencia_id() {
        return competencia_id;
    }

    public int getNivel_id() {
        return nivel_id;
    }

}
