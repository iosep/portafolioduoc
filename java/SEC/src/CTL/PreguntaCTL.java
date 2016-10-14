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

    public ArrayList<PreguntaO> getPreguntas() {
        return preguntaDal.getPreguntas();
    }

    public PreguntaO getPreguntaById(int id) {
        return preguntaDal.getPreguntaById(id);
    }

    public ObservableList<PreguntaO> getPreguntasFX() {
        ObservableList<PreguntaO> fxList = FXCollections.observableArrayList();
        preguntaDal.getPreguntas().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addPreguntaCTL(PreguntaO obj) {
        return preguntaDal.addPregunta(obj);
    }

}
