/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package O;

import CTL.RespuestaCTL;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

/**
 *
 * @author iosep
 */
public class PreguntaO {

    private static int fakeId = 0;

    private int id;
    private String pregunta;
    private int competencia_id;
    private Date creado;
    private Date modificado;

    public PreguntaO() {
    }

    public PreguntaO(String pregunta, int competencia_id, Date creado, Date modificado) {

        this.id = ++fakeId;

        this.pregunta = pregunta;
        this.competencia_id = competencia_id;
        this.creado = creado;
        this.modificado = modificado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getCompetencia_id() {
        return competencia_id;
    }

    public void setCompetencia_id(int competencia_id) {
        this.competencia_id = competencia_id;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    public static ArrayList<ObjQuestion> createEncuesta(ArrayList<PreguntaO> preguntas) {
        RespuestaCTL respCtl = new RespuestaCTL();
        ArrayList<ObjQuestion> questions = new ArrayList<>();
        int count = 0;
        for (PreguntaO p : preguntas) {
            ToggleGroup tg = new ToggleGroup();
            tg.setUserData(p);
            ArrayList<RadioButton> rbs = new ArrayList<>();
            for (RespuestaO r : respCtl.getRespuestasByPreguntaId(p.getId())) {
                RadioButton rb = new RadioButton(r.getRespuesta());
                rb.setUserData(r);
                rb.setToggleGroup(tg);
//                rb.getStyleClass().add("label");
                rbs.add(rb);
            }
            Text t = new Text(++count + ".- " + p.getPregunta());
            t.getStyleClass().add("label");
            questions.add(new ObjQuestion(t, tg, rbs));
        }
        return questions;
    }

    public static class ObjQuestion {

        private final Text t;
        private final ToggleGroup tg;
        private final ArrayList<RadioButton> rb;

        public ObjQuestion(Text t, ToggleGroup tg, ArrayList<RadioButton> rb) {
            this.t = t;
            this.tg = tg;
            this.rb = rb;
        }

        public Text getT() {
            return t;
        }

        public ToggleGroup getTg() {
            return tg;
        }

        public ArrayList<RadioButton> getRb() {
            return rb;
        }

    }

}
