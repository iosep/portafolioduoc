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
import O.RespuestaO;
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
            RespuestaCTL resCtl = new RespuestaCTL();
            CompetenciaCTL comCtl = new CompetenciaCTL();
            Collections.sort(compPre, new Comparator<CompPregId>() {
                @Override
                public int compare(CompPregId cp1, CompPregId cp2) {
                    return Integer.compare(cp1.getCompId(), cp2.getCompId());
                }
            });
            compPre.add(new CompPregId(-5, -5));
            ArrayList<SeleccionO> seleccionesJefe = selCtl.getSeleccionesByEncuestaId(jefeEncId);
            ArrayList<SeleccionO> seleccionesAuto = selCtl.getSeleccionesByEncuestaId(autoEncId);
            boolean add = false;
            for (CompetenciaO c : comCtl.getCompetenciasFX()) {
                System.out.println("competencias: " + c.getNombre());
                if (compIds.contains(c.getId())) {
                    System.out.println("competencia en compIds: " + c.getNombre());
                    int sumAuto = 0;
                    int canAuto = 0;
                    int sumJefe = 0;
                    int canJefe = 0;
                    for (CompPregId cp : compPre) {
                        System.out.println("compPre compId: " + cp.getCompId());
                        if (cp.getCompId() == c.getId()) {
                            for (SeleccionO s : seleccionesAuto) {
                                RespuestaO r = resCtl.getRespuestaById(s.getRespuesta_id());
                                System.out.println("respuesta auto: " + r.getRespuesta());
                                if (r.getPregunta_id() == cp.getPregId()) {
                                    sumAuto += r.getPuntos();
                                    canAuto++;
                                    System.out.println("sum auto: " + sumAuto);
                                }
                            }
                            for (SeleccionO s : seleccionesJefe) {
                                RespuestaO r = resCtl.getRespuestaById(s.getRespuesta_id());
                                System.out.println("respuesta jefe: " + r.getRespuesta());
                                if (r.getPregunta_id() == cp.getPregId()) {
                                    sumJefe += r.getPuntos();
                                    canJefe++;
                                    System.out.println("sum jefe: " + sumJefe);
                                }
                            }
                        } else if (canAuto > 0 && canJefe > 0) {
                            int notaAuto = Math.round(sumAuto / canAuto);
                            int notaJefe = Math.round(sumJefe / canJefe);
                            int nota = Math.round((notaAuto * (per.getAuto_porc() / 100)) + (notaJefe * (per.getJefe_porc() / 100)));
                            int brecha = nota - c.getNivelOptimo();
                            System.out.println("nivel optimo: " + c.getNivelOptimo());
                            add = this.addEvaluacion(new EvaluacionO(evaluado.getRut(), evaluado.getRutjefe(), notaAuto, notaJefe, nota, brecha, perId, c.getId()));
                        }
                    }
                }
            }
            System.out.println("agregadas evaluaciones por competencias");
            return add;
        }
        return false;
    }

}
