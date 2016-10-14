/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.RespuestaDAL;
import O.RespuestaO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class RespuestaCTL {

    private final RespuestaDAL respuestaDal = new RespuestaDAL();

    public ArrayList<RespuestaO> getRespuestas() {
        return respuestaDal.getRespuestas();
    }

    public RespuestaO getRespuestaById(int id) {
        return respuestaDal.getRespuestaById(id);
    }

    public ObservableList<RespuestaO> getRespuestasFX() {
        ObservableList<RespuestaO> fxList = FXCollections.observableArrayList();
        respuestaDal.getRespuestas().stream().forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addRespuestaCTL(RespuestaO obj) {
        return respuestaDal.addRespuesta(obj);
    }

}
