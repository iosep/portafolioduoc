/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.EvaluacionDAL;
import O.CompPregId;
import O.CompetenciaO;
import O.EncuestaO;
import O.EvaluacionO;
import O.PeriodoO;
import O.SeleccionO;
import O.UsuarioO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.ObservableList;

/**
 *
 * @author iosep
 */
public class EvaluacionCTL {

    private final EvaluacionDAL evaDal = new EvaluacionDAL();

    public ArrayList<EvaluacionO> getEvaluaciones() {
        return evaDal.getEvaluaciones();
    }

    public boolean addEvaluacion(EvaluacionO obj) {
        return evaDal.addEvaluacion(obj);
    }

    public EvaluacionO findEvaluacionById(int id) {
        return evaDal.getEvaluacionById(id);
    }

    public ArrayList<EvaluacionO> findEvaluacionesByPeriodoId(int id) {
        return evaDal.getEvaluacionesByPeriodo(id);
    }

    public ArrayList<EvaluacionO> findEvaluacionesByRut(String rut) {
        ArrayList<EvaluacionO> list = new ArrayList<>();
        this.getEvaluaciones().stream().filter((e) -> (e.getRut().equals(rut))).forEach((e) -> {
            list.add(e);
        });
        return list;
    }

    public boolean eliminarEvaluacion(int id) {
        return evaDal.deleteEvaluacion(id);
    }

    public boolean crearEvaluacion(int perId, int funcId, ArrayList<Integer> compIds, ArrayList<CompPregId> compPre) {
        int jefeEncId = 0;
        int autoEncId = 0;
        int autoId = 0;
        EncuestaCTL enCtl = new EncuestaCTL();
        for (EncuestaO en : enCtl.findEncuestasByPeriodoId(perId)) {
            if (en.getEvaluado_id() == funcId) {
                if (en.getEvaluado_id() == en.getUsuario_id()) {
                    autoEncId = en.getId();
                    autoId = en.getUsuario_id();
                } else {
                    jefeEncId = en.getId();
                }
            }
        }
        if (jefeEncId > 0 && autoEncId > 0) {
            UsuarioCTL usCtl = new UsuarioCTL();
            UsuarioO evaluado = usCtl.getUsuarioById(autoId);
            PeriodoCTL perCtl = new PeriodoCTL();
            PeriodoO per = perCtl.getPeriodoById(perId);
            SeleccionCTL selCtl = new SeleccionCTL();
            CompetenciaCTL comCtl = new CompetenciaCTL();
            /*Collections.sort(compPre, new Comparator<CompPregId>() {
                @Override
                public int compare(CompPregId cp1, CompPregId cp2) {
                    return Integer.compare(cp1.getCompId(), cp2.getCompId());
                }
            });*/
            compPre.add(new CompPregId(-5, -5));
            ArrayList<SeleccionO> seleccionesJefe = selCtl.getSeleccionesByEncuestaId(jefeEncId);
            ArrayList<SeleccionO> seleccionesAuto = selCtl.getSeleccionesByEncuestaId(autoEncId);
            boolean add = false;
            ObservableList<CompetenciaO> comps = comCtl.getCompetenciasFX();
            /*Collections.sort(comps, new Comparator<CompetenciaO>() {
                @Override
                public int compare(CompetenciaO c1, CompetenciaO c2) {
                    return Integer.compare(c1.getId(), c2.getId());
                }
            });*/
            for (CompetenciaO c : comps) {
                System.out.println("competencia: " + c.getNombre() + " id: " + c.getId());
                if (compIds.contains(c.getId())) {
                    //System.out.println("competencia en compIds: " + c.getNombre());
                    int sumAuto = 0;
                    float canAuto = 0.00f;
                    int sumJefe = 0;
                    float canJefe = 0.00f;
                    boolean match = false;
                    for (CompPregId cp : compPre) {
                        //System.out.println("compPre compId: " + cp.getCompId());
                        if (cp.getCompId() == c.getId()) {
                            match = true;
                            for (SeleccionO s : seleccionesAuto) {
                                if (s.getPregunta_id() == cp.getPregId()) {
                                    sumAuto += s.getPuntos();
                                    canAuto++;
                                    //System.out.println("sum auto: " + sumAuto);
                                }
                            }
                            for (SeleccionO s : seleccionesJefe) {
                                if (s.getPregunta_id() == cp.getPregId()) {
                                    sumJefe += s.getPuntos();
                                    canJefe++;
                                    //System.out.println("sum jefe: " + sumJefe);
                                }
                            }
                            //System.out.println("canauto: " + canAuto);
                            //System.out.println("canjefe: " + canJefe);
                        } else if (canAuto > 0 && canJefe > 0 && match) {
                            int notaAuto = Math.round(sumAuto / canAuto);
                            int notaJefe = Math.round(sumJefe / canJefe);
                            int nota = Math.round((notaAuto * (per.getAuto_porc() / 100.00f)) + (notaJefe * (per.getJefe_porc() / 100.00f)));
                            int brecha = nota - c.getNivelOptimo();
                            match = false;
                            add = this.addEvaluacion(new EvaluacionO(evaluado.getRut(), evaluado.getRutjefe(), notaAuto, notaJefe, nota, brecha, perId, c.getId()));
                        }
                    }
                }
            }
            if (add) {
                System.out.println("Agregadas evaluación por competencias");
            } else {
                System.out.println("Error! al agregar evaluación por competencias");
            }
            return add;
        }
        return false;
    }

}
