/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

import CTL.AreaCTL;
import CTL.CompetenciaCTL;

/**
 *
 * @author iosep
 */
public class AreaCompetenciaO {

    private int area_id;
    private int competencia_id;

    public AreaCompetenciaO() {
    }

    public AreaCompetenciaO(int area_id, int competencia_id) {
        this.area_id = area_id;
        this.competencia_id = competencia_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getCompetencia_id() {
        return competencia_id;
    }

    public void setCompetencia_id(int competencia_id) {
        this.competencia_id = competencia_id;
    }

    public String getAreaNombre() {
        final AreaCTL ac = new AreaCTL();
        return ac.getAreaById(area_id).getNombre();
    }

    public String getCompNombre() {
        final CompetenciaCTL cc = new CompetenciaCTL();
        return cc.getCompetenciaById(competencia_id).getNombre();
    }

}
