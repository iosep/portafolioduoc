/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.UsuarioAreaO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class UsuarioAreaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<UsuarioAreaO> getUserAreas() {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("usuario_area/json/read_all", jsonPost);
            //System.out.println("getUserAreas response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario_area").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("usuario_area");
                for (int i = 0; i < jsonArray.length(); i++) {
                    UsuarioAreaO obj = new UsuarioAreaO();
                    obj.setArea_id(jsonArray.getJSONObject(i).getInt("AREA_ID"));
                    obj.setUsuario_id(jsonArray.getJSONObject(i).getInt("USUARIO_ID"));
                    obj.setUsuarioRut(jsonArray.getJSONObject(i).getString("USUARIO_RUT"));
                    obj.setAreaNombre(jsonArray.getJSONObject(i).getString("AREA_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e ) {
            System.out.println("getUserAreasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<UsuarioAreaO> getUserAreasByUserId(int id) {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("usuarioid", id);
        try {
            String response = cx.post("usuario_area/json/read_usuario", jsonPost);
            //System.out.println("getUserAreasByUserId response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario_area").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("usuario_area");
                for (int i = 0; i < jsonArray.length(); i++) {
                    UsuarioAreaO obj = new UsuarioAreaO();
                    obj.setArea_id(jsonArray.getJSONObject(i).getInt("AREA_ID"));
                    obj.setUsuario_id(jsonArray.getJSONObject(i).getInt("USUARIO_ID"));
                    obj.setUsuarioRut(jsonArray.getJSONObject(i).getString("USUARIO_RUT"));
                    obj.setAreaNombre(jsonArray.getJSONObject(i).getString("AREA_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e ) {
            System.out.println("getUserAreasByUserIdDAL catch: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<UsuarioAreaO> getUserAreasByAreaId(int id) {
        ArrayList<UsuarioAreaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("areaid", id);
        try {
            String response = cx.post("usuario_area/json/read_area", jsonPost);
            //System.out.println("getUserAreasByAreaId response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario_area").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("usuario_area");
                for (int i = 0; i < jsonArray.length(); i++) {
                    UsuarioAreaO obj = new UsuarioAreaO();
                    obj.setArea_id(jsonArray.getJSONObject(i).getInt("AREA_ID"));
                    obj.setUsuario_id(jsonArray.getJSONObject(i).getInt("USUARIO_ID"));
                    obj.setUsuarioRut(jsonArray.getJSONObject(i).getString("USUARIO_RUT"));
                    obj.setAreaNombre(jsonArray.getJSONObject(i).getString("AREA_NOMBRE"));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e ) {
            System.out.println("getUserAreasByAreaIdDAL catch: " + e.getMessage());
        }
        return list;
    }

    public boolean addUsuarioArea(UsuarioAreaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("usuarioid", obj.getUsuario_id());
        jsonPost.put("areaid", obj.getArea_id());
        //System.out.println("addUsuarioArea post: " + jsonPost);
        try {
            String response = cx.post("usuario_area/json/create", jsonPost);
            //System.out.println("addUsuarioArea response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario_area").length() > 0) {
                return true;
            }else{
                return this.activaUsuarioArea(obj);
            }
        } catch (Exception e ) {
            System.out.println("addUsuarioAreaDAL catch: " + e.getMessage());
            return false;
        }
    }

    public boolean activaUsuarioArea(UsuarioAreaO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("usuarioid", obj.getUsuario_id());
        jsonPut.put("areaid", obj.getArea_id());
        //System.out.println("activaUsuarioArea put: " + jsonPut);
        try {
            String response = cx.put("usuario_area/json/activa", jsonPut);
            //System.out.println("activaUsuarioArea response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario_area").length() > 0) {
                return true;
            }
        } catch (Exception e ) {
            System.out.println("activaUsuarioAreaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean removeUsuarioArea(int idUser, int idArea) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("usuarioid", idUser);
        jsonDelete.put("areaid", idArea);
        //System.out.println("jsonPost removeUsuarioArea: " + jsonDelete);
        try {
            String response = cx.delete("usuario_area/json/delete", jsonDelete);
            //System.out.println("jsonReponse removeUsuarioArea: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario_area").length() > 0) {
                return true;
            }
        } catch (Exception e ) {
            System.out.println("removeUsuarioAreaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
