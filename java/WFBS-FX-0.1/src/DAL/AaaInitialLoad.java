/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.AreaO;
import O.CompetenciaO;
import O.NivelO;
import O.ObservacionO;
import O.PeriodoO;
import O.PreguntaO;
import O.RespuestaO;
import O.UsuarioO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author iosep
 */
public class AaaInitialLoad {

    private static UsuarioO user1;
    private static UsuarioO user2;
    private static UsuarioO user3;
    private static UsuarioO user4;
    private static UsuarioO user5;
    private static ArrayList<UsuarioO> users;
    private static ArrayList<AreaO> areas;
    private static ArrayList<CompetenciaO> competencias;
    private static ArrayList<NivelO> niveles;
    private static ArrayList<PreguntaO> preguntas;
    private static ArrayList<RespuestaO> respuestas;
    private static ArrayList<ObservacionO> observaciones;
    private static ArrayList<PeriodoO> periodos;

    public void cargar() {
        users = new ArrayList<>();
        areas = new ArrayList<>();
        competencias = new ArrayList<>();
        niveles = new ArrayList<>();
        preguntas = new ArrayList<>();
        respuestas = new ArrayList<>();
        observaciones = new ArrayList<>();
        periodos = new ArrayList<>();

        Date fecha = new Date();
        user1 = new UsuarioO("1-9",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                1, "", "José", "Feliz", "ono@ono.com", "H", 999887766, 1, fecha, null, null);
        user2 = new UsuarioO("12312312-3",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                2, "", "Andrés", "Piano", "ono@ono.com", "H", 988776655, 1, fecha, null, null);
        user3 = new UsuarioO("11111111-1",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                2, "", "Amelia", "López", "ono@ono.com", "M", 977665544, 1, fecha, null, null);
        user4 = new UsuarioO("13131313-6",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                3, "12312312-3", "Ana", "Zar", "ono@ono.com", "M", 977665544, 1, fecha, null, null);
        user5 = new UsuarioO("77777777-7",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                3, "11111111-1", "Thor", "Aven", "ono@ono.com", "H", 977665544, 1, fecha, null, null);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    public ArrayList<UsuarioO> mostrarUsuarios() {
        return users;
    }

    public boolean agregarUsuario(UsuarioO ufx) {
        return users.add(ufx);
    }

    public ArrayList<AreaO> mostrarAreas() {
        return areas;
    }

    public boolean agregarArea(AreaO a) {
        return areas.add(a);
    }

    public ArrayList<CompetenciaO> mostrarCompetencias() {
        return competencias;
    }

    public boolean agregarCompetencia(CompetenciaO c) {
        return competencias.add(c);
    }

    public ArrayList<NivelO> mostrarNiveles() {
        return niveles;
    }

    public boolean agregarNivel(NivelO obj) {
        return niveles.add(obj);
    }

    public ArrayList<PreguntaO> mostrarPreguntas() {
        return preguntas;
    }

    public boolean agregarPregunta(PreguntaO obj) {
        return preguntas.add(obj);
    }

    public ArrayList<RespuestaO> mostrarRespuestas() {
        return respuestas;
    }

    public boolean agregarRespuesta(RespuestaO obj) {
        return respuestas.add(obj);
    }

    public ArrayList<ObservacionO> mostrarObservaciones() {
        return observaciones;
    }

    public boolean agregarObservacion(ObservacionO obj) {
        return observaciones.add(obj);
    }

    public ArrayList<PeriodoO> mostrarPeriodos() {
        return periodos;
    }

    public boolean agregarPeriodo(PeriodoO obj) {
        return periodos.add(obj);
    }

}
