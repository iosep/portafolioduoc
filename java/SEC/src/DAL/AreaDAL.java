/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.AreaO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class AreaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<AreaO> getAreas() {
        ArrayList<AreaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("area/json/read_all", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            //System.out.println("getAreas response: " + response);
            if (jsonResponse.getJSONArray("area").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("area");
                for (int i = 0; i < jsonArray.length(); i++) {
                    AreaO obj = new AreaO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setNombre(jsonArray.getJSONObject(i).getString("NOMBRE"));
                    obj.setSigla(jsonArray.getJSONObject(i).getString("SIGLA"));
                    obj.setDescripcion(jsonArray.getJSONObject(i).getString("DESCRIPCION"));
                    obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("MODIFICADO")));
                    obj.setDesactivado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("DESACTIVADO")));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getAreasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public AreaO getAreaById(int id) {
        AreaO obj = new AreaO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("area/json/read_id", jsonPost);
            //System.out.println("areaById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("area");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setNombre(jsonArray.getJSONObject(0).getString("NOMBRE"));
                obj.setSigla(jsonArray.getJSONObject(0).getString("SIGLA"));
                obj.setDescripcion(jsonArray.getJSONObject(0).getString("DESCRIPCION"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getAreaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addArea(AreaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("nombre", obj.getNombre());
        jsonPost.put("sigla", obj.getSigla());
        jsonPost.put("descripcion", obj.getDescripcion());
        //System.out.println("addArea post: " + jsonPost);
        try {
            String response = cx.post("area/json/create", jsonPost);
            //System.out.println("addArea response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addAreaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateArea(AreaO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("nombre", obj.getNombre());
        jsonPut.put("sigla", obj.getSigla());
        jsonPut.put("descripcion", obj.getDescripcion());
        System.out.println("updateArea put: " + jsonPut);
        try {
            String response = cx.put("area/json/update", jsonPut);
            System.out.println("updateArea response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateAreaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteArea(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        //System.out.println("jsonDelete deleteArea: " + jsonDelete);
        try {
            String response = cx.delete("area/json/delete", jsonDelete);
            //System.out.println("jsonReponse deleteArea: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteAreaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean activaArea(int id) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", id);
        //System.out.println("jsonPut activaArea: " + jsonPut);
        try {
            String response = cx.put("area/json/activa", jsonPut);
            //System.out.println("jsonReponse activaArea: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("area").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("activaAreaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }
    
}
