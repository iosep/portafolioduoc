/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.RespuestaO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class RespuestaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<RespuestaO> getRespuestas() {
        ArrayList<RespuestaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("respuesta/json/read_all", jsonPost);
            //System.out.println("getRespuestas response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("respuesta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("respuesta");
                for (int i = 0; i < jsonArray.length(); i++) {
                    RespuestaO obj = new RespuestaO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setRespuesta(jsonArray.getJSONObject(i).getString("RESPUESTA"));
                    obj.setPuntos(jsonArray.getJSONObject(i).getInt("PUNTOS"));
                    obj.setPregunta_id(jsonArray.getJSONObject(i).getInt("PREGUNTA_ID"));
                    obj.setPreguntaNombre(jsonArray.getJSONObject(i).getString("PREGUNTA_PREGUNTA"));
                    obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getRespuestasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public RespuestaO getRespuestaById(int id) {
        RespuestaO obj = new RespuestaO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        System.out.println("getRespuestaById post: " + jsonPost);
        try {
            String response = cx.post("respuesta/json/read_id", jsonPost);
            System.out.println("respuestaById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("respuesta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("respuesta");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setRespuesta(jsonArray.getJSONObject(0).getString("RESPUESTA"));
                obj.setPuntos(jsonArray.getJSONObject(0).getInt("PUNTOS"));
                obj.setPregunta_id(jsonArray.getJSONObject(0).getInt("PREGUNTA_ID"));
                obj.setPreguntaNombre(jsonArray.getJSONObject(0).getString("PREGUNTA_PREGUNTA"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getRespuestaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addRespuesta(RespuestaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("respuesta", obj.getRespuesta());
        jsonPost.put("puntos", obj.getPuntos());
        jsonPost.put("preguntaid", obj.getPregunta_id());
        System.out.println("addRespuesta post: " + jsonPost);
        try {
            String response = cx.post("respuesta/json/create", jsonPost);
            System.out.println("addRespuesta response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("respuesta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addRespuestaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateRespuesta(RespuestaO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("respuesta", obj.getRespuesta());
        jsonPut.put("puntos", obj.getPuntos());
        jsonPut.put("preguntaid", obj.getPregunta_id());
        System.out.println("updateRespuesta put: " + jsonPut);
        try {
            String response = cx.put("respuesta/json/update", jsonPut);
            System.out.println("updateRespuesta response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("respuesta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateRespuestaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteRespuesta(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        //System.out.println("jsonPost deleteRespuesta: " + jsonDelete);
        try {
            String response = cx.delete("respuesta/json/delete", jsonDelete);
            //System.out.println("jsonReponse deleteRespuesta: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("respuesta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteRespuestaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
