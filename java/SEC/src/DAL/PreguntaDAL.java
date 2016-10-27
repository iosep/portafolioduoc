/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.PreguntaO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class PreguntaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<PreguntaO> getPreguntas() {
        ArrayList<PreguntaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("pregunta/json/read_all", jsonPost);
            //System.out.println("getPreguntas response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("pregunta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("pregunta");
                for (int i = 0; i < jsonArray.length(); i++) {
                    PreguntaO obj = new PreguntaO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setPregunta(jsonArray.getJSONObject(i).getString("PREGUNTA"));
                    obj.setCompetencia_id(jsonArray.getJSONObject(i).getInt("COMPETENCIA_ID"));
                    obj.setCompetenciaNombre(jsonArray.getJSONObject(i).getString("COMPETENCIA_NOMBRE"));
                    obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getPreguntasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public PreguntaO getPreguntaById(int id) {
        PreguntaO obj = new PreguntaO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("pregunta/json/read_id", jsonPost);
            //System.out.println("preguntaById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("pregunta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("pregunta");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setPregunta(jsonArray.getJSONObject(0).getString("PREGUNTA"));
                obj.setCompetencia_id(jsonArray.getJSONObject(0).getInt("COMPETENCIA_ID"));
                obj.setCompetenciaNombre(jsonArray.getJSONObject(0).getString("COMPETENCIA_NOMBRE"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getPreguntaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addPregunta(PreguntaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("pregunta", obj.getPregunta());
        jsonPost.put("competenciaid", obj.getCompetencia_id());
        //System.out.println("addPregunta post: " + jsonPost);
        try {
            String response = cx.post("pregunta/json/create", jsonPost);
            //System.out.println("addPregunta response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("pregunta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addPreguntaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updatePregunta(PreguntaO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("pregunta", obj.getPregunta());
        jsonPut.put("competenciaid", obj.getCompetencia_id());
        //System.out.println("updatePregunta put: " + jsonPut);
        try {
            String response = cx.put("pregunta/json/update", jsonPut);
            //System.out.println("updatePregunta response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("pregunta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updatePreguntaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deletePregunta(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        //System.out.println("jsonPost deletePregunta: " + jsonDelete);
        try {
            String response = cx.delete("pregunta/json/delete", jsonDelete);
            //System.out.println("jsonReponse deletePregunta: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("pregunta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deletePreguntaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
