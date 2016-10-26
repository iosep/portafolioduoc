/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.CompetenciaNivelO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class CompetenciaNivelDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<CompetenciaNivelO> getCompetenciaNiveles() {
        ArrayList<CompetenciaNivelO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("competencia_nivel/json/read_all", jsonPost);
            System.out.println("getCompetenciaNiveles response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia_nivel").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("competencia_nivel");
                for (int i = 0; i < jsonArray.length(); i++) {
                    CompetenciaNivelO obj = new CompetenciaNivelO();
                    obj.setNivel_id(jsonArray.getJSONObject(i).getInt("NIVEL_ID"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setNivelNombre(jsonArray.getJSONObject(i).getString("NIVEL_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getCompetenciaNivelesDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<CompetenciaNivelO> getCompetenciaNivelesByCompetenciaId(int id) {
        ArrayList<CompetenciaNivelO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("comptid", id);
        try {
            String response = cx.post("competencia_nivel/json/read_competencia", jsonPost);
            System.out.println("getCompetenciaNivelesByCompetenciaId response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia_nivel").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("competencia_nivel");
                for (int i = 0; i < jsonArray.length(); i++) {
                    CompetenciaNivelO obj = new CompetenciaNivelO();
                    obj.setNivel_id(jsonArray.getJSONObject(i).getInt("NIVEL_ID"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setNivelNombre(jsonArray.getJSONObject(i).getString("NIVEL_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getCompetenciaNivelesByCompetenciaIdDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<CompetenciaNivelO> getCompetenciaNivelesByNivelId(int id) {
        ArrayList<CompetenciaNivelO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("nivelid", id);
        try {
            String response = cx.post("competencia_nivel/json/read_nivel", jsonPost);
            System.out.println("getCompetenciaNivelesByNivelId response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia_nivel").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("competencia_nivel");
                for (int i = 0; i < jsonArray.length(); i++) {
                    CompetenciaNivelO obj = new CompetenciaNivelO();
                    obj.setNivel_id(jsonArray.getJSONObject(i).getInt("NIVEL_ID"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setNivelNombre(jsonArray.getJSONObject(i).getString("NIVEL_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getCompetenciaNivelesByNivelIdDAL catch: " + e.getMessage());
        }
        return list;
    }

    public boolean addCompetenciaNivel(CompetenciaNivelO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("comptid", obj.getCompetencia_id());
        jsonPost.put("nivelid", obj.getNivel_id());
        //System.out.println("addCompetenciaNivel post: " + jsonPost);
        try {
            String response = cx.post("competencia_nivel/json/create", jsonPost);
            System.out.println("addCompetenciaNivel response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia_nivel").length() > 0) {
                return true;
            } else {
                System.out.println("activar nivelComp");
            }
        } catch (Exception e) {
            System.out.println("addCompetenciaNivelDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateCompetenciaNivel(CompetenciaNivelO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("comptid", obj.getCompetencia_id());
        jsonPut.put("nivelid", obj.getNivel_id());
        //System.out.println("updateCompetenciaNivel put: " + jsonPut);
        try {
            String response = cx.put("competencia_nivel/json/update", jsonPut);
            System.out.println("updateCompetenciaNivel response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia_nivel").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateCompetenciaNivelDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean removeCompetenciaNivel(int idComp, int idNivel) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("comptid", idComp);
        jsonDelete.put("nivelid", idNivel);
        //System.out.println("jsonPost removeCompetenciaNivel: " + jsonDelete);
        try {
            String response = cx.delete("competencia_nivel/json/delete", jsonDelete);
            System.out.println("jsonReponse removeCompetenciaNivel: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia_nivel").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("removeCompetenciaNivelDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
