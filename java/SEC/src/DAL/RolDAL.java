/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.RolO;
import O.UsuarioO;
import RESTful.Conexion;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class RolDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<RolO> getRoles() {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("rol/json/read_all", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("roles").length() > 0) {
                ArrayList<RolO> list = new ArrayList<>();
                for (int i = 0; i < jsonResponse.getJSONArray("roles").length(); i++) {
                    RolO obj = new RolO();
                    obj.setId(jsonResponse.getJSONArray("roles").getJSONObject(i).getInt("ID"));
                    obj.setNombre(jsonResponse.getJSONArray("roles").getJSONObject(i).getString("NOMBRE"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getRolesDAL catch: " + e.getMessage());
        }
        return null;
    }

}
