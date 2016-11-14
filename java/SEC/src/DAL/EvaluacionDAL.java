/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.EvaluacionO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class EvaluacionDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<EvaluacionO> getEvaluaciones() {
        ArrayList<EvaluacionO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("evaluacion/json/read_all", jsonPost);
            System.out.println("getEvaluaciones response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("evaluacion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("evaluacion");
                for (int i = 0; i < jsonArray.length(); i++) {
                    EvaluacionO obj = new EvaluacionO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));

                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getEvaluacionesDAL catch: " + e.getMessage());
        }
        return list;
    }

    public EvaluacionO getEvaluacionById(int id) {
        EvaluacionO obj = new EvaluacionO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("evaluacion/json/read_id", jsonPost);
            System.out.println("evaluacionById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("evaluacion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("evaluacion");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));

                return obj;
            }
        } catch (Exception e) {
            System.out.println("getEvaluacionByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addEvaluacion(EvaluacionO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("rut", obj.getRut());
        jsonPost.put("rutjefe", obj.getRutJefe());
        jsonPost.put("notaauto", obj.getNotaAuto());
        jsonPost.put("notajefe", obj.getNotaJefe());
        jsonPost.put("brecha", obj.getBrecha());
        jsonPost.put("periodoid", obj.getPeriodoId());
        jsonPost.put("competenciaid", obj.getCompId());
        System.out.println("addEvaluacion post: " + jsonPost);
        try {
            String response = cx.post("evaluacion/json/create", jsonPost);
            System.out.println("addEvaluacion response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("evaluacion").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addEvaluacionDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteEvaluacion(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        try {
            String response = cx.delete("evaluacion/json/delete", jsonDelete);
            System.out.println("jsonReponse deleteEvaluacion: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("evaluacion").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteEvaluacionDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public ArrayList<EvaluacionO> getEvaluacionesByPeriodo(int idPeriodo) {
        ArrayList<EvaluacionO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("periodoid", idPeriodo);
        try {
            String response = cx.post("evaluacion/json/read_periodo", jsonPost);
            System.out.println("getEvaluacionesByPeriodo response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("evaluacion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("evaluacion");
                for (int i = 0; i < jsonArray.length(); i++) {
                    EvaluacionO obj = new EvaluacionO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));

                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getEvaluacionesByPeriodoDAL catch: " + e.getMessage());
        }
        return list;
    }

}
