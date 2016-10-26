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
import O.EncuestaO;
import O.NivelO;
import O.ObservacionO;
import O.PeriodoO;
import O.PreguntaO;
import O.RespuestaO;
import O.RolO;
import O.SeleccionO;
import O.UsuarioAreaO;
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
//rol
    final RolO rol1 = new RolO("Administrador");
    final RolO rol2 = new RolO("Jefe");
    final RolO rol3 = new RolO("Funcionario");
    final ArrayList<RolO> rolesFinal = new ArrayList<>(Arrays.asList(rol1, rol2, rol3));
    static ArrayList<RolO> roles;
//area
    static ArrayList<AreaO> areas;
//competencia
    static ArrayList<CompetenciaO> competencias;
//nivel    
    
    static ArrayList<NivelO> niveles;
//pregunta
    private static ArrayList<PreguntaO> preguntas;
    //respuesta
    private static ArrayList<RespuestaO> respuestas;
//observacion
    private static ArrayList<ObservacionO> observaciones;
//periodo
    private static ArrayList<PeriodoO> periodos;
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
//carga inicial    
    private static boolean load = true;
//encuesta
    static ArrayList<EncuestaO> encuestas;
    static ArrayList<SeleccionO> selections;

    public AInitLoad() {
        if (load) {
            this.cargar();
            load = false;
            System.out.println("carga de datos de prueba");
        }
    }

    private void cargar() {
        encuestas = new ArrayList<>();
        selections = new ArrayList<>();
//final + static
        
        roles = new ArrayList<>();
//        roles.removeAll(rolesFinal);
        roles.addAll(rolesFinal);
        areas = new ArrayList<>();
//        areas.removeAll(areasFinal);

        competencias = new ArrayList<>();
//        competencias.removeAll(competenciasFinal);
        niveles = new ArrayList<>();
//        niveles.removeAll(nivelFinal);
        
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
        respuestas = new ArrayList<>();
        observaciones = new ArrayList<>();
        periodos = new ArrayList<>();

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

    public static boolean addEncuesta(EncuestaO e) {
        return encuestas.add(e);
    }

    public static ArrayList<EncuestaO> showEncuestas() {
        return encuestas;
    }

    public static boolean addSeleccion(SeleccionO obj) {
        return selections.add(obj);
    }

    public static ArrayList<SeleccionO> getSelecciones() {
        return selections;
    }

}
