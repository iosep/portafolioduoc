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
public class EvaluacionO {
    private int id;
    private String rut;
    private String rutJefe;
    private int notaAuto;
    private int notaJefe;
    private int nota;
    private int brecha;
    private int periodoId;
    private int compId;

    public EvaluacionO() {
    }

    public EvaluacionO(String rut, String rutJefe, int notaAuto, int notaJefe, int nota, int brecha, int periodoId, int compId) {
        this.rut = rut;
        this.rutJefe = rutJefe;
        this.notaAuto = notaAuto;
        this.notaJefe = notaJefe;
        this.nota = nota;
        this.brecha = brecha;
        this.periodoId = periodoId;
        this.compId = compId;
    }

    public int getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRutJefe() {
        return rutJefe;
    }

    public void setRutJefe(String rutJefe) {
        this.rutJefe = rutJefe;
    }

    public int getNotaAuto() {
        return notaAuto;
    }

    public void setNotaAuto(int notaAuto) {
        this.notaAuto = notaAuto;
    }

    public int getNotaJefe() {
        return notaJefe;
    }

    public void setNotaJefe(int notaJefe) {
        this.notaJefe = notaJefe;
    }

    public int getBrecha() {
        return brecha;
    }

    public void setBrecha(int brecha) {
        this.brecha = brecha;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }
    
    
}
