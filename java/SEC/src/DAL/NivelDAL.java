/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.NivelO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class NivelDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<NivelO> getNiveles() {
        ArrayList<NivelO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("nivel/json/read_all", jsonPost);
            //System.out.println("getNiveles response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("nivel").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("nivel");
                for (int i = 0; i < jsonArray.length(); i++) {
                    NivelO obj = new NivelO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setNota(jsonArray.getJSONObject(i).getInt("NOTA"));
                    obj.setNombre(jsonArray.getJSONObject(i).getString("NOMBRE"));
                    obj.setDescripcion(jsonArray.getJSONObject(i).getString("DESCRIPCION"));
                    obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getNivelesDAL catch: " + e.getMessage());
        }
        return list;
    }

    public NivelO getNivelById(int id) {
        NivelO obj = new NivelO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("nivel/json/read_id", jsonPost);
            //System.out.println("nivelById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("nivel").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("nivel");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setNota(jsonArray.getJSONObject(0).getInt("NOTA"));
                obj.setNombre(jsonArray.getJSONObject(0).getString("NOMBRE"));
                obj.setDescripcion(jsonArray.getJSONObject(0).getString("DESCRIPCION"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getNivelByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addNivel(NivelO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("nota", obj.getNota());
        jsonPost.put("nombre", obj.getNombre());
        jsonPost.put("descrip", obj.getDescripcion());
        System.out.println("addNivel post: " + jsonPost);
        try {
            String response = cx.post("nivel/json/create", jsonPost);
            System.out.println("addNivel response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("nivel").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addNivelDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateNivel(NivelO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("nota", obj.getNota());
        jsonPut.put("nombre", obj.getNombre());
        jsonPut.put("descrip", obj.getDescripcion());
        //System.out.println("updateNivel put: " + jsonPut);
        try {
            String response = cx.put("nivel/json/update", jsonPut);
            //System.out.println("updateNivel response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("nivel").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateNivelDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteNivel(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        //System.out.println("jsonPost deleteNivel: " + jsonDelete);
        try {
            String response = cx.delete("nivel/json/delete", jsonDelete);
            //System.out.println("jsonReponse deleteNivel: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("nivel").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteNivelDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
