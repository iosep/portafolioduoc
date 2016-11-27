/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import REST.Conexion;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class LogDAL {

    private final Conexion cx = new Conexion();

    public boolean addLog(String ip) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("ip", ip);
        try {
            String response = cx.post("audit_log/json/create", jsonPost);
            System.out.println("addLog response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("audit_log").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addLogDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

}
