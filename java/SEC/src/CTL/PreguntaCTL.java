/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.PreguntaDAL;
import O.PreguntaO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class PreguntaCTL {

    private final PreguntaDAL preguntaDal = new PreguntaDAL();

    public PreguntaO getPreguntaById(int id) {
        return preguntaDal.getPreguntaById(id);
    }

    public ObservableList<PreguntaO> getPreguntasFX() {
        ObservableList<PreguntaO> obList = FXCollections.observableArrayList();
        preguntaDal.getPreguntas().stream().filter((each) -> (each.getActivo() == 1)).forEach((each) -> {
            obList.add(each);
        });
        return obList;
    }

    public boolean addPreguntaCTL(PreguntaO obj) {
        return preguntaDal.addPregunta(obj);
    }

    public boolean modificarPreguntaCTL(PreguntaO obj) {
        return preguntaDal.updatePregunta(obj);
    }
    
    public ArrayList<PreguntaO> getPreguntasByCompetenciaId(int idComp) {
        ArrayList<PreguntaO> preByComp = new ArrayList<>();
        preguntaDal.getPreguntas().stream().filter((p) -> (p.getActivo() == 1 && p.getCompetencia_id() == idComp)).forEach((p) -> {
            preByComp.add(p);
        });
        return preByComp;
    }

    public boolean eliminarPregunta(int id) {
        return preguntaDal.deletePregunta(id);
    }
    
}
