/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.ObservacionO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class ObservacionDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<ObservacionO> getObservaciones() {
        ArrayList<ObservacionO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("observacion/json/read_all", jsonPost);
            //System.out.println("getObservaciones response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("observacion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("observacion");
                for (int i = 0; i < jsonArray.length(); i++) {
                    ObservacionO obj = new ObservacionO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setNivel_inf(jsonArray.getJSONObject(i).getInt("NIVEL_INF"));
                    obj.setNivel_sup(jsonArray.getJSONObject(i).getInt("NIVEL_SUP"));
                    obj.setMsj_inf(jsonArray.getJSONObject(i).getString("MSJ_INF"));
                    obj.setMsj_sup(jsonArray.getJSONObject(i).getString("MSJ_SUP"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getObservacionesDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ObservacionO getObservacionById(int id) {
        ObservacionO obj = new ObservacionO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("observacion/json/read_id", jsonPost);
            //System.out.println("observacionById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("observacion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("observacion");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setNivel_inf(jsonArray.getJSONObject(0).getInt("NIVEL_INF"));
                obj.setNivel_sup(jsonArray.getJSONObject(0).getInt("NIVEL_SUP"));
                obj.setMsj_inf(jsonArray.getJSONObject(0).getString("MSJ_INF"));
                obj.setMsj_sup(jsonArray.getJSONObject(0).getString("MSJ_SUP"));
                obj.setCompetencia_id(jsonArray.getJSONObject(0).getInt("COMPETENCIA_ID"));
                obj.setCompNombre(jsonArray.getJSONObject(0).getString("COMPETENCIA_NOMBRE"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getObservacionByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addObservacion(ObservacionO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("nivelinf", obj.getNivel_inf());
        jsonPost.put("nivelsup", obj.getNivel_sup());
        jsonPost.put("msginf", obj.getMsj_inf());
        jsonPost.put("msgsup", obj.getMsj_sup());
        jsonPost.put("idcomp", obj.getCompetencia_id());
        System.out.println("addObservacion post: " + jsonPost);
        try {
            String response = cx.post("observacion/json/create", jsonPost);
            System.out.println("addObservacion response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("observacion").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addObservacionDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateObservacion(ObservacionO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("nivelinf", obj.getNivel_inf());
        jsonPut.put("nivelsup", obj.getNivel_sup());
        jsonPut.put("msginf", obj.getMsj_inf());
        jsonPut.put("msgsup", obj.getMsj_sup());
        jsonPut.put("idcomp", obj.getCompetencia_id());
        //System.out.println("updateObservacion put: " + jsonPut);
        try {
            String response = cx.put("observacion/json/update", jsonPut);
            //System.out.println("updateObservacion response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("observacion").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateObservacionDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteObservacion(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        //System.out.println("jsonPost deleteObservacion: " + jsonDelete);
        try {
            String response = cx.delete("observacion/json/delete", jsonDelete);
            //System.out.println("jsonReponse deleteObservacion: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("observacion").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteObservacionDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public ObservacionO getObservacionByCompetencia(int id) {
        ObservacionO obj = new ObservacionO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("idcomp", id);
        try {
            String response = cx.post("observacion/json/read_competencia", jsonPost);
            System.out.println("observacionByCompetencia response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("observacion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("observacion");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setNivel_inf(jsonArray.getJSONObject(0).getInt("NIVEL_INF"));
                obj.setNivel_sup(jsonArray.getJSONObject(0).getInt("NIVEL_SUP"));
                obj.setMsj_inf(jsonArray.getJSONObject(0).getString("MSJ_INF"));
                obj.setMsj_sup(jsonArray.getJSONObject(0).getString("MSJ_SUP"));
                obj.setCompetencia_id(jsonArray.getJSONObject(0).getInt("COMPETENCIA_ID"));
                obj.setCompNombre(jsonArray.getJSONObject(0).getString("COMPETENCIA_NOMBRE"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getObservacionByCompetenciaDAL catch: " + e.getMessage());
        }
        return obj;
    }

}
