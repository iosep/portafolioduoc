/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.AreaCompetenciaO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class AreaCompetenciaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<AreaCompetenciaO> getAreaCompetencias() {
        ArrayList<AreaCompetenciaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("area_competencia/json/read_all", jsonPost);
            System.out.println("getAreaCompetencias response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area_competencia").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("area_competencia");
                for (int i = 0; i < jsonArray.length(); i++) {
                    AreaCompetenciaO obj = new AreaCompetenciaO();
                    obj.setArea_id(jsonArray.getJSONObject(i).getInt("AREA_ID"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setAreaNombre(jsonArray.getJSONObject(i).getString("AREA_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getAreaCompetenciasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<AreaCompetenciaO> getAreaCompetenciasByCompetenciaId(int id) {
        ArrayList<AreaCompetenciaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("comptid", id);
        try {
            String response = cx.post("area_competencia/json/read_competencia", jsonPost);
            System.out.println("getAreaCompetenciasByCompetenciaId response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area_competencia").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("area_competencia");
                for (int i = 0; i < jsonArray.length(); i++) {
                    AreaCompetenciaO obj = new AreaCompetenciaO();
                    obj.setArea_id(jsonArray.getJSONObject(i).getInt("AREA_ID"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setAreaNombre(jsonArray.getJSONObject(i).getString("AREA_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getAreaCompetenciasByCompetenciaIdDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<AreaCompetenciaO> getAreaCompetenciasByAreaId(int id) {
        ArrayList<AreaCompetenciaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("areaid", id);
        try {
            String response = cx.post("area_competencia/json/read_area", jsonPost);
            System.out.println("getAreaCompetenciasByAreaId response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area_competencia").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("area_competencia");
                for (int i = 0; i < jsonArray.length(); i++) {
                    AreaCompetenciaO obj = new AreaCompetenciaO();
                    obj.setArea_id(jsonArray.getJSONObject(i).getInt("AREA_ID"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setAreaNombre(jsonArray.getJSONObject(i).getString("AREA_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getAreaCompetenciasByAreaIdDAL catch: " + e.getMessage());
        }
        return list;
    }

    public boolean addAreaCompetencia(AreaCompetenciaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("comptid", obj.getCompetencia_id());
        jsonPost.put("areaid", obj.getArea_id());
        //System.out.println("addAreaCompetencia post: " + jsonPost);
        try {
            String response = cx.post("area_competencia/json/create", jsonPost);
            System.out.println("addAreaCompetencia response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area_competencia").length() > 0) {
                return true;
            } else {
                System.out.println("activar areaComp");
            }
        } catch (Exception e) {
            System.out.println("addAreaCompetenciaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateAreaCompetencia(AreaCompetenciaO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("comptid", obj.getCompetencia_id());
        jsonPut.put("areaid", obj.getArea_id());
        //System.out.println("updateAreaCompetencia put: " + jsonPut);
        try {
            String response = cx.put("area_competencia/json/update", jsonPut);
            System.out.println("updateAreaCompetencia response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area_competencia").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateAreaCompetenciaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean removeAreaCompetencia(int idComp, int idArea) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("comptid", idComp);
        jsonDelete.put("areaid", idArea);
        //System.out.println("jsonPost removeAreaCompetencia: " + jsonDelete);
        try {
            String response = cx.delete("area_competencia/json/delete", jsonDelete);
            System.out.println("jsonReponse removeAreaCompetencia: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area_competencia").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("removeAreaCompetenciaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
