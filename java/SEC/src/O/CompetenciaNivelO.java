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
    private String compNombre;
    private String nivelNombre;
    private int activo;

    public CompetenciaNivelO() {
    }

    public CompetenciaNivelO(int competencia_id, int nivel_id) {
        this.competencia_id = competencia_id;
        this.nivel_id = nivel_id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getCompetencia_id() {
        return competencia_id;
    }

    public int getNivel_id() {
        return nivel_id;
    }

    public String getCompNombre() {
        return compNombre;
    }

    public void setCompNombre(String compNombre) {
        this.compNombre = compNombre;
    }

    public String getNivelNombre() {
        return nivelNombre;
    }

    public void setNivelNombre(String nivelNombre) {
        this.nivelNombre = nivelNombre;
    }

    public void setCompetencia_id(int competencia_id) {
        this.competencia_id = competencia_id;
    }

    public void setNivel_id(int nivel_id) {
        this.nivel_id = nivel_id;
    }

}
