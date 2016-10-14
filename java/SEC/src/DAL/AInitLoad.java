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
    final AreaO area1 = new AreaO("Homicidios", "hmc", 1, fecha, null, null);
    final AreaO area2 = new AreaO("Delitos sexuales", "dsx", 1, fecha, null, null);
    final AreaO area3 = new AreaO("Robos", "rbs", 1, fecha, null, null);
    final AreaO area4 = new AreaO("Delitos económicos", "dec", 1, fecha, null, null);
    final AreaO area5 = new AreaO("Delitos contra la propiedad intelectual", "dcpi", 1, fecha, null, null);
    final AreaO area6 = new AreaO("Ubicación de personas", "udp", 1, fecha, null, null);
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
//
    private static ArrayList<PreguntaO> preguntas;
    private static ArrayList<RespuestaO> respuestas;
    private static ArrayList<ObservacionO> observaciones;
    private static ArrayList<PeriodoO> periodos;
//usuarioArea
    static ArrayList<UsuarioAreaO> rUserAreas;
    final UsuarioAreaO usAr1 = new UsuarioAreaO(4, 1);
    final UsuarioAreaO usAr2 = new UsuarioAreaO(5, 2);
    final UsuarioAreaO usAr3 = new UsuarioAreaO(6, 3);
    final ArrayList<UsuarioAreaO> userAreaFinal = new ArrayList<>(Arrays.asList(usAr1, usAr2, usAr3));
//
    private static ArrayList<AreaCompetenciaO> rAreaCompetencias;
    private static ArrayList<CompetenciaNivelO> rCompetenciaNiveles;
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
        usersAdd.removeAll(users);
        usersAdd.addAll(users);
        roles = new ArrayList<>();
        roles.removeAll(rolesFinal);
        roles.addAll(rolesFinal);
        areas = new ArrayList<>();
        areas.removeAll(areasFinal);
        areas.addAll(areasFinal);
        competencias = new ArrayList<>();
        competencias.removeAll(competenciasFinal);
        competencias.addAll(competenciasFinal);
        niveles = new ArrayList<>();
        niveles.removeAll(nivelFinal);
        niveles.addAll(nivelFinal);
        rUserAreas = new ArrayList<>();
        rUserAreas.removeAll(userAreaFinal);
        rUserAreas.addAll(userAreaFinal);
//        
        preguntas = new ArrayList<>();
        respuestas = new ArrayList<>();
        observaciones = new ArrayList<>();
        periodos = new ArrayList<>();
        rAreaCompetencias = new ArrayList<>();
        rCompetenciaNiveles = new ArrayList<>();
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
