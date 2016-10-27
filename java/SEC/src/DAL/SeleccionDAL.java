/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.SeleccionO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class SeleccionDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<SeleccionO> getSelecciones() {
        ArrayList<SeleccionO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("seleccion/json/read_all", jsonPost);
            System.out.println("getSelecciones response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("seleccion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("seleccion");
                for (int i = 0; i < jsonArray.length(); i++) {
                    SeleccionO obj = new SeleccionO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));

                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getSeleccionesDAL catch: " + e.getMessage());
        }
        return list;
    }

    public SeleccionO getSeleccionById(int id) {
        SeleccionO obj = new SeleccionO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("seleccion/json/read_id", jsonPost);
            System.out.println("seleccionById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("seleccion").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("seleccion");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));

                return obj;
            }
        } catch (Exception e) {
            System.out.println("getSeleccionByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addSeleccion(SeleccionO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("encuestaid", obj.getEncuesta_id());
        jsonPost.put("respuestaid", obj.getRespuesta_id());
        System.out.println("addSeleccion post: " + jsonPost);
        try {
            String response = cx.post("seleccion/json/create", jsonPost);
            System.out.println("addSeleccion response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("seleccion").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addSeleccionDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
