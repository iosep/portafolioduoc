/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import O.RolO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class RolDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<RolO> getRoles() {
        ArrayList<RolO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("rol/json/read_all", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("rol").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("rol");
                for (int i = 0; i < jsonArray.length(); i++) {
                    RolO obj = new RolO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setNombre(jsonArray.getJSONObject(i).getString("NOMBRE"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getRolesDAL catch: " + e.getMessage());
        }
        return list;
    }

}
