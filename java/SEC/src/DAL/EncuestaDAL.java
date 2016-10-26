/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

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
            //System.out.println("getEncuestas response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("encuesta");
                for (int i = 0; i < jsonArray.length(); i++) {
                    EncuestaO obj = new EncuestaO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    
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
                
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getEncuestaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addEncuesta(EncuestaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        
        System.out.println("addEncuesta post: " + jsonPost);
        try {
            String response = cx.post("encuesta/json/create", jsonPost);
            System.out.println("addEncuesta response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("encuesta").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addEncuestaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
