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

    private final EncuestaDAL encDal = new EncuestaDAL();

    public ArrayList<EncuestaO> getEncuestas() {
        return encDal.getEncuestas();
    }

    public boolean addEncuesta(EncuestaO obj) {
        return encDal.addEncuesta(obj);
    }

    public EncuestaO findEncuestaById(int id) {
        return encDal.getEncuestaById(id);
    }

    public ArrayList<EncuestaO> findEncuestasByPeriodoId(int id) {
        ArrayList<EncuestaO> ens = new ArrayList<>();
        encDal.getEncuestas().stream().filter((e) -> (e.getPeriodo_id() == id)).forEach((e) -> {
            ens.add(e);
        });
        return ens;
    }
}
