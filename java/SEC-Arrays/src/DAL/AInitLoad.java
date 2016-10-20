/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.AreaCompetenciaO;
import O.AreaO;
import O.CompetenciaNivelO;
import O.CompetenciaO;
import O.NivelO;
import O.ObservacionO;
import O.PeriodoO;
import O.PreguntaO;
import O.RespuestaO;
import O.RolO;
import O.UsuarioAreaO;
import O.UsuarioO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author iosep
 */
public class AInitLoad {

//fecha
    final Date fecha = new Date();
//usuario    
    final UsuarioO user1 = new UsuarioO("1-9",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            1, "", "José", "Feliz", "ono@ono.com", "H", 999887766, fecha, null);
    final UsuarioO user2 = new UsuarioO("12312312-3",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            2, "", "Andrés", "Piano", "ono@ono.com", "H", 988776655, fecha, null);
    final UsuarioO user3 = new UsuarioO("11111111-1",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            2, "", "Amelia", "López", "ono@ono.com", "M", 977665544, fecha, null);
    final UsuarioO user4 = new UsuarioO("13131313-6",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            3, "12312312-3", "Ana", "Zar", "ono@ono.com", "M", 977665544, fecha, null);
    final UsuarioO user5 = new UsuarioO("77777777-7",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            3, "11111111-1", "Thor", "Aven", "ono@ono.com", "H", 977665544, fecha, null);
    final UsuarioO user6 = new UsuarioO("12345678-5",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            3, "12312312-3", "Raven", "Mortal", "ono@ono.com", "H", 977665544, fecha, null);
    final UsuarioO user7 = new UsuarioO("98765432-5",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            3, "11111111-1", "Mia", "Walrus", "ono@ono.com", "M", 977665544, fecha, null);
    final UsuarioO user8 = new UsuarioO("14566421-7",
            "b25hdGUsLTU3NjEwMjA3NA==,-576102074",
            3, "12312312-3", "Totoro", "Oso", "ono@ono.com", "H", 977665544, fecha, null);
    final ArrayList<UsuarioO> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8));
    static ArrayList<UsuarioO> usersAdd;
//rol
    final RolO rol1 = new RolO("Administrador");
    final RolO rol2 = new RolO("Jefe");
    final RolO rol3 = new RolO("Funcionario");
    final ArrayList<RolO> rolesFinal = new ArrayList<>(Arrays.asList(rol1, rol2, rol3));
    static ArrayList<RolO> roles;
//area
    final AreaO area1 = new AreaO("Homicidios", "hmc", "capturar a homicidas", 1, fecha, null, null);
    final AreaO area2 = new AreaO("Delitos sexuales", "dsx", "capturar a sexópatas", 1, fecha, null, null);
    final AreaO area3 = new AreaO("Robos", "rbs", "capturar a ladrones", 1, fecha, null, null);
    final AreaO area4 = new AreaO("Delitos económicos", "dec", "capturar a estafadores", 1, fecha, null, null);
    final AreaO area5 = new AreaO("Delitos contra la propiedad intelectual", "dcpi", "capturar a usurpadores", 1, fecha, null, null);
    final AreaO area6 = new AreaO("Ubicación de personas", "udp", "encontrar personas desaparecidas", 1, fecha, null, null);
    final ArrayList<AreaO> areasFinal = new ArrayList<>(Arrays.asList(area1, area2, area3, area4, area5, area6));
    static ArrayList<AreaO> areas;
//competencia
    final CompetenciaO competencia1 = new CompetenciaO("Liderazgo", "Ser líder", "ldr", 4, 1, fecha, null, null);
    final CompetenciaO competencia2 = new CompetenciaO("Cognitiva", "Ser cognitivo", "cgn", 4, 1, fecha, null, null);
    final CompetenciaO competencia3 = new CompetenciaO("Pensamiento científico", "Ser científico", "pcn", 4, 1, fecha, null, null);
    final CompetenciaO competencia4 = new CompetenciaO("Perspicacia", "Ser perspicaz", "ppc", 4, 1, fecha, null, null);
    final CompetenciaO competencia5 = new CompetenciaO("Orientación a las personas", "Ser personal", "oap", 4, 1, fecha, null, null);
    final CompetenciaO competencia6 = new CompetenciaO("Trabajo en equipo", "Ser equipo", "tee", 4, 1, fecha, null, null);
    final CompetenciaO competencia7 = new CompetenciaO("Comunicación", "Ser comunicativo", "cmc", 4, 1, fecha, null, null);
    final CompetenciaO competencia8 = new CompetenciaO("Autocontrol", "Ser zen", "atc", 4, 1, fecha, null, null);
    final CompetenciaO competencia9 = new CompetenciaO("Evaluación", "Ser evaluativo", "evl", 4, 1, fecha, null, null);
    final CompetenciaO competencia10 = new CompetenciaO("Análisis y síntesis", "Ser analítico", "ays", 4, 1, fecha, null, null);
    final CompetenciaO competencia11 = new CompetenciaO("Habilidad descriptiva", "Ser descriptivo", "hdc", 4, 1, fecha, null, null);
    final CompetenciaO competencia12 = new CompetenciaO("Concentración", "Ser concentrado", "cnt", 4, 1, fecha, null, null);
    final CompetenciaO competencia13 = new CompetenciaO("Organización y planificación", "Ser organizado", "oyp", 4, 1, fecha, null, null);
    final ArrayList<CompetenciaO> competenciasFinal = new ArrayList<>(Arrays.asList(competencia1, competencia2, competencia3, competencia4, competencia5, competencia6, competencia7, competencia8, competencia9, competencia10, competencia11, competencia12, competencia13));
    static ArrayList<CompetenciaO> competencias;
//nivel    
    final NivelO nivel1 = new NivelO("Nulo", 0, "Todo malo", fecha, null);
    final NivelO nivel2 = new NivelO("Muy Malo", 1, "Casi todo malo", fecha, null);
    final NivelO nivel3 = new NivelO("Malo", 2, "Muchas malas", fecha, null);
    final NivelO nivel4 = new NivelO("Regular", 3, "Algunas malas", fecha, null);
    final NivelO nivel5 = new NivelO("Bien", 4, "Casi todo bien", fecha, null);
    final NivelO nivel6 = new NivelO("Muy Bien", 5, "Todo bien", fecha, null);
    final ArrayList<NivelO> nivelFinal = new ArrayList<>(Arrays.asList(nivel1, nivel2, nivel3, nivel4, nivel5, nivel6));
    static ArrayList<NivelO> niveles;
//pregunta
    private static ArrayList<PreguntaO> preguntas;
    final PreguntaO pre1 = new PreguntaO("¿qué es liderazgo?", 1, fecha, null);
    final PreguntaO pre2 = new PreguntaO("¿qué es cognitivo?", 2, fecha, null);
    final PreguntaO pre3 = new PreguntaO("¿qué es pensamiento científico?", 3, fecha, null);
    final PreguntaO pre4 = new PreguntaO("¿qué es perspicacia?", 4, fecha, null);
    final PreguntaO pre5 = new PreguntaO("¿qué es orientación a las personas?", 5, fecha, null);
    final PreguntaO pre6 = new PreguntaO("¿qué es trabajo en equipo?", 6, fecha, null);
    final PreguntaO pre7 = new PreguntaO("¿qué es comunicación?", 7, fecha, null);
    final PreguntaO pre8 = new PreguntaO("¿qué es autocontrol?", 8, fecha, null);
    final PreguntaO pre9 = new PreguntaO("¿qué es evaluación?", 9, fecha, null);
    final PreguntaO pre10 = new PreguntaO("¿qué es análisis y síntesis?", 10, fecha, null);
    final PreguntaO pre11 = new PreguntaO("¿qué es habilidad descriptiva?", 11, fecha, null);
    final PreguntaO pre12 = new PreguntaO("¿qué es concentración?", 12, fecha, null);
    final PreguntaO pre13 = new PreguntaO("¿qué es organización y planificación?", 13, fecha, null);
    final ArrayList<PreguntaO> preguntasFinal = new ArrayList<>(Arrays.asList(pre1, pre2, pre3, pre4, pre5, pre6, pre7, pre8, pre9, pre10, pre11, pre12, pre13));
//respuesta
    private static ArrayList<RespuestaO> respuestas;
    final RespuestaO res1 = new RespuestaO("liderar", 5, 1, fecha, null);
    final RespuestaO res2 = new RespuestaO("ser líder", 3, 1, fecha, null);
    final RespuestaO res3 = new RespuestaO("cognicear", 5, 2, fecha, null);
    final RespuestaO res4 = new RespuestaO("ser cognito", 3, 2, fecha, null);
    final RespuestaO res5 = new RespuestaO("pensar científicamente", 5, 3, fecha, null);
    final RespuestaO res6 = new RespuestaO("ser pensador científico", 3, 3, fecha, null);
    final RespuestaO res7 = new RespuestaO("perspicar", 5, 4, fecha, null);
    final RespuestaO res8 = new RespuestaO("ser perspicaz", 3, 4, fecha, null);
    final RespuestaO res9 = new RespuestaO("orientarse a las personas", 5, 5, fecha, null);
    final RespuestaO res10 = new RespuestaO("ser orientado a las personas", 3, 5, fecha, null);
    final RespuestaO res11 = new RespuestaO("trabajar en equipo", 5, 6, fecha, null);
    final RespuestaO res12 = new RespuestaO("ser trabajador de equipo", 3, 6, fecha, null);
    final RespuestaO res13 = new RespuestaO("comunicar", 5, 7, fecha, null);
    final RespuestaO res14 = new RespuestaO("ser comunicador", 3, 7, fecha, null);
    final RespuestaO res15 = new RespuestaO("autocontrolarse", 5, 8, fecha, null);
    final RespuestaO res16 = new RespuestaO("ser autocontrolado", 3, 8, fecha, null);
    final RespuestaO res17 = new RespuestaO("evaluar", 5, 9, fecha, null);
    final RespuestaO res18 = new RespuestaO("ser evaluado o evaluador", 3, 9, fecha, null);
    final RespuestaO res19 = new RespuestaO("analizar y sintetizar", 5, 10, fecha, null);
    final RespuestaO res20 = new RespuestaO("ser analítico y sintético", 3, 10, fecha, null);
    final RespuestaO res21 = new RespuestaO("describir habilmente", 5, 11, fecha, null);
    final RespuestaO res22 = new RespuestaO("ser un hábil descriptor", 3, 11, fecha, null);
    final RespuestaO res23 = new RespuestaO("concentrarse", 5, 12, fecha, null);
    final RespuestaO res24 = new RespuestaO("ser concentrado", 3, 12, fecha, null);
    final RespuestaO res25 = new RespuestaO("organizar y planificar", 5, 13, fecha, null);
    final RespuestaO res26 = new RespuestaO("ser organizado y planificado", 3, 13, fecha, null);
    final ArrayList<RespuestaO> respuestaFinal = new ArrayList<>(Arrays.asList(res1, res2, res3, res4, res5, res6, res7, res8, res9, res10, res11, res12, res13, res14, res15, res16, res17, res18, res19, res20, res21, res22, res23, res24, res25, res26));
//observacion
    private static ArrayList<ObservacionO> observaciones;
    final ObservacionO ob1 = new ObservacionO(3, 4, "muy mal", "muy bien", 1, fecha, null);
    final ObservacionO ob2 = new ObservacionO(3, 4, "muy mal", "muy bien", 2, fecha, null);
    final ObservacionO ob3 = new ObservacionO(3, 4, "muy mal", "muy bien", 3, fecha, null);
    final ObservacionO ob4 = new ObservacionO(3, 4, "muy mal", "muy bien", 4, fecha, null);
    final ObservacionO ob5 = new ObservacionO(3, 4, "muy mal", "muy bien", 5, fecha, null);
    final ObservacionO ob6 = new ObservacionO(3, 4, "muy mal", "muy bien", 6, fecha, null);
    final ObservacionO ob7 = new ObservacionO(3, 4, "muy mal", "muy bien", 7, fecha, null);
    final ObservacionO ob8 = new ObservacionO(3, 4, "muy mal", "muy bien", 8, fecha, null);
    final ObservacionO ob9 = new ObservacionO(3, 4, "muy mal", "muy bien", 9, fecha, null);
    final ObservacionO ob10 = new ObservacionO(3, 4, "muy mal", "muy bien", 10, fecha, null);
    final ObservacionO ob11 = new ObservacionO(3, 4, "muy mal", "muy bien", 11, fecha, null);
    final ObservacionO ob12 = new ObservacionO(3, 4, "muy mal", "muy bien", 12, fecha, null);
    final ObservacionO ob13 = new ObservacionO(3, 4, "muy mal", "muy bien", 13, fecha, null);
    final ArrayList<ObservacionO> obserFinal = new ArrayList<>(Arrays.asList(ob1, ob2, ob3, ob4, ob5, ob6, ob7, ob8, ob9, ob10, ob11, ob12, ob13));
//periodo
    private static ArrayList<PeriodoO> periodos;
    final PeriodoO pe1 = new PeriodoO(fecha, Date.from(LocalDate.now().plusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), 70, 30, 1, fecha, null, null);
    final PeriodoO pe2 = new PeriodoO(fecha, Date.from(LocalDate.now().plusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant()), 70, 30, 1, fecha, null, null);
    final PeriodoO pe3 = new PeriodoO(Date.from(LocalDate.now().plusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(LocalDate.now().plusWeeks(3).atStartOfDay(ZoneId.systemDefault()).toInstant()), 70, 30, 1, fecha, null, null);
    final ArrayList<PeriodoO> perFinal = new ArrayList<>(Arrays.asList(pe1, pe2, pe3));
//usuarioArea
    static ArrayList<UsuarioAreaO> rUserAreas;
    final UsuarioAreaO usAr1 = new UsuarioAreaO(4, 1);
    final UsuarioAreaO usAr2 = new UsuarioAreaO(5, 2);
    final UsuarioAreaO usAr3 = new UsuarioAreaO(6, 3);
    final UsuarioAreaO usAr4 = new UsuarioAreaO(7, 4);
    final UsuarioAreaO usAr5 = new UsuarioAreaO(8, 5);
    final UsuarioAreaO usAr6 = new UsuarioAreaO(4, 6);
    final UsuarioAreaO usAr7 = new UsuarioAreaO(5, 1);
    final UsuarioAreaO usAr8 = new UsuarioAreaO(6, 2);
    final UsuarioAreaO usAr9 = new UsuarioAreaO(7, 3);
    final UsuarioAreaO usAr0 = new UsuarioAreaO(8, 4);
    final ArrayList<UsuarioAreaO> userAreaFinal = new ArrayList<>(Arrays.asList(usAr1, usAr2, usAr3, usAr4, usAr5, usAr6, usAr7, usAr8, usAr9, usAr0));
//areaCompetencia
    private static ArrayList<AreaCompetenciaO> rAreaCompetencias;
    final AreaCompetenciaO arCo1 = new AreaCompetenciaO(1, 1);
    final AreaCompetenciaO arCo2 = new AreaCompetenciaO(2, 2);
    final AreaCompetenciaO arCo3 = new AreaCompetenciaO(3, 3);
    final AreaCompetenciaO arCo4 = new AreaCompetenciaO(4, 4);
    final AreaCompetenciaO arCo5 = new AreaCompetenciaO(5, 5);
    final AreaCompetenciaO arCo6 = new AreaCompetenciaO(6, 6);
    final AreaCompetenciaO arCo7 = new AreaCompetenciaO(1, 7);
    final AreaCompetenciaO arCo8 = new AreaCompetenciaO(2, 8);
    final AreaCompetenciaO arCo9 = new AreaCompetenciaO(3, 9);
    final AreaCompetenciaO arCo0 = new AreaCompetenciaO(4, 10);
    final ArrayList<AreaCompetenciaO> areaCompFinal = new ArrayList<>(Arrays.asList(arCo1, arCo2, arCo3, arCo4, arCo5, arCo6, arCo7, arCo8, arCo9, arCo0));
//competenciaNivel    
    private static ArrayList<CompetenciaNivelO> rCompetenciaNiveles;
    final CompetenciaNivelO comNi1 = new CompetenciaNivelO(1, 1);
    final CompetenciaNivelO comNi2 = new CompetenciaNivelO(2, 2);
    final CompetenciaNivelO comNi3 = new CompetenciaNivelO(3, 3);
    final CompetenciaNivelO comNi4 = new CompetenciaNivelO(4, 4);
    final CompetenciaNivelO comNi5 = new CompetenciaNivelO(5, 5);
    final CompetenciaNivelO comNi6 = new CompetenciaNivelO(6, 6);
    final CompetenciaNivelO comNi7 = new CompetenciaNivelO(7, 1);
    final CompetenciaNivelO comNi8 = new CompetenciaNivelO(8, 2);
    final CompetenciaNivelO comNi9 = new CompetenciaNivelO(9, 3);
    final CompetenciaNivelO comNi0 = new CompetenciaNivelO(10, 4);
    final ArrayList<CompetenciaNivelO> comNiFinal = new ArrayList<>(Arrays.asList(comNi1, comNi2, comNi3, comNi4, comNi5, comNi6, comNi7, comNi8, comNi9, comNi0));
//    
    private static boolean load = true;

    public AInitLoad() {
        if (load) {
            this.cargar();
            load = false;
            System.out.println("carga de datos de prueba");
        }
    }

    private void cargar() {
        usersAdd = new ArrayList<>();
//        usersAdd.removeAll(users);
        usersAdd.addAll(users);
        roles = new ArrayList<>();
//        roles.removeAll(rolesFinal);
        roles.addAll(rolesFinal);
        areas = new ArrayList<>();
//        areas.removeAll(areasFinal);
        areas.addAll(areasFinal);
        competencias = new ArrayList<>();
//        competencias.removeAll(competenciasFinal);
        competencias.addAll(competenciasFinal);
        niveles = new ArrayList<>();
//        niveles.removeAll(nivelFinal);
        niveles.addAll(nivelFinal);
        rUserAreas = new ArrayList<>();
//        rUserAreas.removeAll(userAreaFinal);
        rUserAreas.addAll(userAreaFinal);
        rAreaCompetencias = new ArrayList<>();
//        rAreaCompetencias.removeAll(areaCompFinal);
        rAreaCompetencias.addAll(areaCompFinal);
        rCompetenciaNiveles = new ArrayList<>();
//        rCompetenciaNiveles.removeAll(comNiFinal);
        rCompetenciaNiveles.addAll(comNiFinal);
//        
        preguntas = new ArrayList<>();
        preguntas.addAll(preguntasFinal);
        respuestas = new ArrayList<>();
        respuestas.addAll(respuestaFinal);
        observaciones = new ArrayList<>();
        observaciones.addAll(obserFinal);
        periodos = new ArrayList<>();
        periodos.addAll(perFinal);
    }

    public ArrayList<UsuarioO> mostrarUsuarios() {
        return usersAdd;
    }

    public boolean agregarUsuario(UsuarioO ufx) {
        return usersAdd.add(ufx);
    }

    public static ArrayList<AreaO> mostrarAreas() {
        return areas;
    }

    public static boolean agregarArea(AreaO a) {
        return areas.add(a);
    }

    public static ArrayList<CompetenciaO> mostrarCompetencias() {
        return competencias;
    }

    public static boolean agregarCompetencia(CompetenciaO c) {
        return competencias.add(c);
    }

    public static ArrayList<NivelO> mostrarNiveles() {
        return niveles;
    }

    public static boolean agregarNivel(NivelO obj) {
        return niveles.add(obj);
    }

    public static ArrayList<PreguntaO> mostrarPreguntas() {
        return preguntas;
    }

    public static boolean agregarPregunta(PreguntaO obj) {
        return preguntas.add(obj);
    }

    public static ArrayList<RespuestaO> mostrarRespuestas() {
        return respuestas;
    }

    public static boolean agregarRespuesta(RespuestaO obj) {
        return respuestas.add(obj);
    }

    public static ArrayList<ObservacionO> mostrarObservaciones() {
        return observaciones;
    }

    public static boolean agregarObservacion(ObservacionO obj) {
        return observaciones.add(obj);
    }

    public static ArrayList<PeriodoO> mostrarPeriodos() {
        return periodos;
    }

    public static boolean agregarPeriodo(PeriodoO obj) {
        return periodos.add(obj);
    }

    public static ArrayList<UsuarioAreaO> mostrarUsuarioAreas() {
        return rUserAreas;
    }

    public static boolean agregarUsuarioArea(UsuarioAreaO obj) {
        return rUserAreas.add(obj);
    }

    public static boolean eliminarUsuarioArea(int idUser, int idArea) {
        for (UsuarioAreaO item : rUserAreas) {
            if (item.getArea_id() == idArea && item.getUsuario_id() == idUser) {
                return rUserAreas.remove(item);
            }
        }
        return false;
    }

    public static ArrayList<AreaCompetenciaO> mostrarAreaCompetencias() {
        return rAreaCompetencias;
    }

    public static boolean agregarAreaCompetencia(AreaCompetenciaO obj) {
        return rAreaCompetencias.add(obj);
    }

    public static boolean eliminarAreaCompetencia(int idArea, int idCompetencia) {
        for (AreaCompetenciaO item : rAreaCompetencias) {
            if (item.getArea_id() == idArea && item.getCompetencia_id() == idCompetencia) {
                return rAreaCompetencias.remove(item);
            }
        }
        return false;
    }

    public static ArrayList<CompetenciaNivelO> mostrarCompetenciaNiveles() {
        return rCompetenciaNiveles;
    }

    public static boolean agregarCompetenciaNivel(CompetenciaNivelO obj) {
        return rCompetenciaNiveles.add(obj);
    }

    public static boolean eliminarCompetenciaNivel(int idCompetencia, int idNivel) {
        for (CompetenciaNivelO item : rCompetenciaNiveles) {
            if (item.getCompetencia_id() == idCompetencia && item.getNivel_id() == idNivel) {
                return rCompetenciaNiveles.remove(item);
            }
        }
        return false;
    }

    public final ArrayList<RolO> mostrarRoles() {
        return roles;
    }

}
