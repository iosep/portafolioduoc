/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.EncuestaO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class EncuestaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<EncuestaO> getEncuestas() {
        ArrayList<EncuestaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("encuesta/json/read_all", jsonPost);
            System.out.println("getEncuestas response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("encuesta");
                for (int i = 0; i < jsonArray.length(); i++) {
                    EncuestaO obj = new EncuestaO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setUsuario_id(jsonArray.getJSONObject(i).getInt("USUARIO_ID"));
                    obj.setEvaluado_id(jsonArray.getJSONObject(i).getInt("EVALUADO_ID"));
                    obj.setPeriodo_id(jsonArray.getJSONObject(i).getInt("PERIODO_ID"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getEncuestasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public EncuestaO getEncuestaById(int id) {
        EncuestaO obj = new EncuestaO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("encuesta/json/read_id", jsonPost);
            //System.out.println("encuestaById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("encuesta");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setUsuario_id(jsonArray.getJSONObject(0).getInt("USUARIO_ID"));
                obj.setEvaluado_id(jsonArray.getJSONObject(0).getInt("EVALUADO_ID"));
                obj.setPeriodo_id(jsonArray.getJSONObject(0).getInt("PERIODO_ID"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getEncuestaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public int addEncuesta(EncuestaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("usuarioid", obj.getUsuario_id());
        jsonPost.put("evaluadoid", obj.getEvaluado_id());
        jsonPost.put("periodoid", obj.getPeriodo_id());
        jsonPost.put("fecha", Formato.dateToString(obj.getFecha()));
        System.out.println("addEncuesta post: " + jsonPost);
        try {
            String response = cx.post("encuesta/json/create", jsonPost);
            System.out.println("addEncuesta response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                return jsonResponse.getJSONArray("encuesta").getJSONObject(0).getInt("ID");
            }
        } catch (Exception e) {
            System.out.println("addEncuestaDAL catch: " + e.getMessage());
            return 0;
        }
        return 0;
    }

    public boolean deleteEncuesta(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        try {
            String response = cx.delete("encuesta/json/delete", jsonDelete);
            System.out.println("jsonReponse deleteEncuesta: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteEncuestaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public ArrayList<EncuestaO> getEncuestasByPeriodo(int idPeriodo) {
        ArrayList<EncuestaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("periodoid", idPeriodo);
        try {
            String response = cx.post("encuesta/json/read_periodo", jsonPost);
            System.out.println("getEncuestasByPeriodo response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("encuesta");
                for (int i = 0; i < jsonArray.length(); i++) {
                    EncuestaO obj = new EncuestaO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setUsuario_id(jsonArray.getJSONObject(i).getInt("USUARIO_ID"));
                    obj.setEvaluado_id(jsonArray.getJSONObject(i).getInt("EVALUADO_ID"));
                    obj.setPeriodo_id(jsonArray.getJSONObject(i).getInt("PERIODO_ID"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getEncuestasByPeriodoDAL catch: " + e.getMessage());
        }
        return list;
    }

}
