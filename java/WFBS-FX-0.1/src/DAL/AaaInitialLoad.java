/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.AreaCompetenciaO;
import O.AreaO;
import O.CompetenciaO;
import O.NivelO;
import O.ObservacionO;
import O.PeriodoO;
import O.PreguntaO;
import O.RespuestaO;
import O.UsuarioAreaO;
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
    private static UsuarioO user6;
    private static UsuarioO user7;
    private static UsuarioO user8;
    private static ArrayList<UsuarioO> users;
    private static AreaO area1;
    private static AreaO area2;
    private static AreaO area3;
    private static AreaO area4;
    private static AreaO area5;
    private static AreaO area6;
    private static ArrayList<AreaO> areas;
    private static CompetenciaO competencia1;
    private static CompetenciaO competencia2;
    private static CompetenciaO competencia3;
    private static CompetenciaO competencia4;
    private static CompetenciaO competencia5;
    private static CompetenciaO competencia6;
    private static CompetenciaO competencia7;
    private static CompetenciaO competencia8;
    private static CompetenciaO competencia9;
    private static CompetenciaO competencia10;
    private static CompetenciaO competencia11;
    private static CompetenciaO competencia12;
    private static CompetenciaO competencia13;
    private static ArrayList<CompetenciaO> competencias;
    private static ArrayList<NivelO> niveles;
    private static ArrayList<PreguntaO> preguntas;
    private static ArrayList<RespuestaO> respuestas;
    private static ArrayList<ObservacionO> observaciones;
    private static ArrayList<PeriodoO> periodos;
    private static ArrayList<UsuarioAreaO> rUserAreas;
    private static ArrayList<AreaCompetenciaO> rAreaCompetencias;

    public void cargar() {
        users = new ArrayList<>();
        areas = new ArrayList<>();
        competencias = new ArrayList<>();
        niveles = new ArrayList<>();
        preguntas = new ArrayList<>();
        respuestas = new ArrayList<>();
        observaciones = new ArrayList<>();
        periodos = new ArrayList<>();
        rUserAreas = new ArrayList<>();
        rAreaCompetencias = new ArrayList<>();

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
        user6 = new UsuarioO("12345678-5",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                3, "11111111-1", "Raven", "Mortal", "ono@ono.com", "H", 977665544, 1, fecha, null, null);
        user7 = new UsuarioO("98765432-5",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                3, "11111111-1", "Mia", "Walrus", "ono@ono.com", "M", 977665544, 1, fecha, null, null);
        user8 = new UsuarioO("14566421-7",
                "77+9Qe+/vUdm77+9X1MQZe+/vV0177+9aGfvv70677+9cu+/ve+/ve+/vT/vv73vv70677+977+9De+/vSQ=,-576102074",
                3, "11111111-1", "Totoro", "Oso", "ono@ono.com", "H", 977665544, 1, fecha, null, null);
        
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);

        area1 = new AreaO("Homicidios", "hmc", 1, fecha, null, null);
        area2 = new AreaO("Delitos sexuales", "dsx", 1, fecha, null, null);
        area3 = new AreaO("Robos", "rbs", 1, fecha, null, null);
        area4 = new AreaO("Delitos económicos", "dec", 1, fecha, null, null);
        area5 = new AreaO("Delitos contra la propiedad intelectual", "dcpi", 1, fecha, null, null);
        area6 = new AreaO("Ubicación de personas", "udp", 1, fecha, null, null);

        areas.add(area1);
        areas.add(area2);
        areas.add(area3);
        areas.add(area4);
        areas.add(area5);
        areas.add(area6);

        competencia1 = new CompetenciaO("Liderazgo", "Ser líder", "ldr", 4, 1, fecha, null, null);
        competencia2 = new CompetenciaO("Cognitiva", "Ser cognitivo", "cgn", 4, 1, fecha, null, null);
        competencia3 = new CompetenciaO("Pensamiento científico", "Ser científico", "pcn", 4, 1, fecha, null, null);
        competencia4 = new CompetenciaO("Perspicacia", "Ser perspicaz", "ppc", 4, 1, fecha, null, null);
        competencia5 = new CompetenciaO("Orientación a las personas", "Ser personal", "oap", 4, 1, fecha, null, null);
        competencia6 = new CompetenciaO("Trabajo en equipo", "Ser equipo", "tee", 4, 1, fecha, null, null);
        competencia7 = new CompetenciaO("Comunicación", "Ser comunicativo", "cmc", 4, 1, fecha, null, null);
        competencia8 = new CompetenciaO("Autocontrol", "Ser zen", "atc", 4, 1, fecha, null, null);
        competencia9 = new CompetenciaO("Evaluación", "Ser evaluativo", "evl", 4, 1, fecha, null, null);
        competencia10 = new CompetenciaO("Análisis y síntesis", "Ser analítico", "ays", 4, 1, fecha, null, null);
        competencia11 = new CompetenciaO("Habilidad descriptiva", "Ser descriptivo", "hdc", 4, 1, fecha, null, null);
        competencia12 = new CompetenciaO("Concentración", "Ser concentrado", "cnt", 4, 1, fecha, null, null);
        competencia13 = new CompetenciaO("Organización y planificación", "Ser organizado", "oyp", 4, 1, fecha, null, null);

        competencias.add(competencia1);
        competencias.add(competencia2);
        competencias.add(competencia3);
        competencias.add(competencia4);
        competencias.add(competencia5);
        competencias.add(competencia6);
        competencias.add(competencia7);
        competencias.add(competencia8);
        competencias.add(competencia9);
        competencias.add(competencia10);
        competencias.add(competencia11);
        competencias.add(competencia12);
        competencias.add(competencia13);

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

    public ArrayList<UsuarioAreaO> mostrarUsuarioAreas() {
        return rUserAreas;
    }

    public boolean agregarUsuarioArea(UsuarioAreaO obj) {
        return rUserAreas.add(obj);
    }

    public boolean eliminarUsuarioArea(int idUser, int idArea) {
        for (UsuarioAreaO item : rUserAreas) {
            if (item.getArea_id() == idArea && item.getUsuario_id() == idUser) {
                rUserAreas.remove(item);
                return true;
            }
        }
        return false;
    }

    public ArrayList<AreaCompetenciaO> mostrarAreaCompetencias() {
        return rAreaCompetencias;
    }

    public boolean agregarAreaCompetencia(AreaCompetenciaO obj) {
        return rAreaCompetencias.add(obj);
    }

    public boolean eliminarAreaCompetencia(int idArea, int idCompetencia) {
        for (AreaCompetenciaO item : rAreaCompetencias) {
            if (item.getArea_id() == idArea & item.getCompetencia_id() == idCompetencia) {
                rAreaCompetencias.remove(item);
                return true;
            }
        }
        return false;
    }

}
