/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.EncuestaDAL;
import O.EncuestaO;
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class EncuestaCTL {

    public ArrayList<EncuestaO> getEncuestas() {
        return EncuestaDAL.getEncuestas();
    }

    public boolean addEncuesta(EncuestaO obj) {
        return EncuestaDAL.addEncuesta(obj);
    }

    public EncuestaO findEncuestaById(int id) {
        for (EncuestaO e : EncuestaDAL.getEncuestas()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<EncuestaO> findEncuestasByPeriodoId(int id) {
        ArrayList<EncuestaO> ens = new ArrayList<>();
        for (EncuestaO e : EncuestaDAL.getEncuestas()) {
            if (e.getPeriodo_id() == id) {
                ens.add(e);
            }
        }
        return ens;
    }
}
