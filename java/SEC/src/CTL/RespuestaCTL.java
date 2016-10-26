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

    public RespuestaO getRespuestaById(int id) {
        return respuestaDal.getRespuestaById(id);
    }

    public ObservableList<RespuestaO> getRespuestasFX() {
        ObservableList<RespuestaO> fxList = FXCollections.observableArrayList();
        respuestaDal.getRespuestas().stream().filter((each) -> (each.getActivo() == 1)).forEach((each) -> {
            fxList.add(each);
        });
        return fxList;
    }

    public boolean addRespuestaCTL(RespuestaO obj) {
        return respuestaDal.addRespuesta(obj);
    }

    public boolean modificarRespuestaCTL(RespuestaO obj) {
        return respuestaDal.updateRespuesta(obj);
    }

    public ArrayList<RespuestaO> getRespuestasByPreguntaId(int idPregunta) {
        ArrayList<RespuestaO> respuestas = new ArrayList<>();
        respuestaDal.getRespuestas().stream().filter((r) -> (r.getActivo() == 1 && r.getPregunta_id() == idPregunta)).forEach((r) -> {
            respuestas.add(r);
        });
        return respuestas;
    }

    public boolean eliminaRespuesta(int id) {
        return respuestaDal.deleteRespuesta(id);
    }
}
