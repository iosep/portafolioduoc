/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.AreaO;
import RESTful.Conexion;
import java.util.ArrayList;
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
            if (jsonResponse.getJSONArray("areas").length() > 0) {
                for (int i = 0; i < jsonResponse.getJSONArray("areas").length(); i++) {
                    AreaO obj = new AreaO();
                    obj.setId(jsonResponse.getJSONArray("areas").getJSONObject(i).getInt("ID"));
                    obj.setNombre(jsonResponse.getJSONArray("areas").getJSONObject(i).getString("NOMBRE"));
                    obj.setSigla(jsonResponse.getJSONArray("areas").getJSONObject(i).getString("SIGLA"));
                    obj.setDescripcion(jsonResponse.getJSONArray("areas").getJSONObject(i).getString("DESCRIPCION"));
                    obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("areas").getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("areas").getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonResponse.getJSONArray("areas").getJSONObject(i).getInt("ACTIVO"));
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
            System.out.println("area by id: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("areas").length() > 0) {
                obj.setId(jsonResponse.getJSONArray("areas").getJSONObject(0).getInt("ID"));
                obj.setNombre(jsonResponse.getJSONArray("areas").getJSONObject(0).getString("NOMBRE"));
                obj.setSigla(jsonResponse.getJSONArray("areas").getJSONObject(0).getString("SIGLA"));
                obj.setDescripcion(jsonResponse.getJSONArray("areas").getJSONObject(0).getString("DESCRIPCION"));
                obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("areas").getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("areas").getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonResponse.getJSONArray("areas").getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getAreaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addArea(AreaO afx) {
        return false;
    }

}
