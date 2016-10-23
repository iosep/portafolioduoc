/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.UsuarioO;
import RESTful.Conexion;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class UsuarioDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<UsuarioO> getUsuarios() {
        ArrayList<UsuarioO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("usuario/json/read_all", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuarios").length() > 0) {
                for (int i = 0; i < jsonResponse.getJSONArray("usuarios").length(); i++) {
                    UsuarioO obj = new UsuarioO();
                    obj.setId(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getInt("ID"));
                    obj.setRut(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("RUT"));
                    obj.setRutjefe(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("RUT_JEFE"));
                    obj.setNombre(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("NOMBRE"));
                    obj.setApellido(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("APELLIDO"));
                    obj.setEmail(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("EMAIL"));
                    obj.setSexo(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("SEXO"));
                    obj.setFono(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getInt("FONO"));
                    obj.setRolid(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getInt("ROL_ID"));
                    obj.setRol_nombre(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("ROL_NOMBRE"));
                    obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonResponse.getJSONArray("usuarios").getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getUsuariosDAL catch: " + e.getMessage());
        }
        return list;
    }

    public UsuarioO getUsuarioById(int id) {
        UsuarioO obj = new UsuarioO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("usuario/json/read_id", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario").length() > 0) {
                obj.setId(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("ID"));
                obj.setRut(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("RUT"));
                obj.setRutjefe(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("RUT_JEFE"));
                obj.setNombre(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("NOMBRE"));
                obj.setApellido(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("APELLIDO"));
                obj.setEmail(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("EMAIL"));
                obj.setSexo(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("SEXO"));
                obj.setFono(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("FONO"));
                obj.setRolid(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("ROL_ID"));
                obj.setRol_nombre(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("ROL_NOMBRE"));
                obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getUsuarioByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public UsuarioO getUsuarioByRut(String rut) {
        UsuarioO obj = new UsuarioO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("rut", rut);
        try {
            String response = cx.post("usuario/json/read_rut", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario").length() > 0) {
                obj.setId(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("ID"));
                obj.setRut(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("RUT"));
                obj.setRutjefe(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("RUT_JEFE"));
                obj.setNombre(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("NOMBRE"));
                obj.setApellido(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("APELLIDO"));
                obj.setEmail(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("EMAIL"));
                obj.setSexo(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("SEXO"));
                obj.setFono(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("FONO"));
                obj.setRolid(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("ROL_ID"));
                obj.setRol_nombre(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("ROL_NOMBRE"));
                obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("usuario").getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonResponse.getJSONArray("usuario").getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getUsuarioByRutDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addUsuario(UsuarioO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("rut", obj.getRut());
        jsonPost.put("rutjefe", obj.getRutjefe());
        jsonPost.put("clave", obj.getClave());
        jsonPost.put("nombre", obj.getNombre());
        jsonPost.put("apellido", obj.getApellido());
        jsonPost.put("email", obj.getEmail());
        jsonPost.put("sexo", obj.getSexo());
        jsonPost.put("fono", obj.getFono());
        jsonPost.put("rolid", obj.getRolid());
        try {
            String response = cx.post("usuario/json/create", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addUsuarioDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateUser(UsuarioO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("rut", obj.getRut());
        jsonPut.put("rutjefe", obj.getRutjefe());
        jsonPut.put("clave", obj.getClave());
        jsonPut.put("nombre", obj.getNombre());
        jsonPut.put("apellido", obj.getApellido());
        jsonPut.put("email", obj.getEmail());
        jsonPut.put("sexo", obj.getSexo());
        jsonPut.put("fono", obj.getFono());
        jsonPut.put("rolid", obj.getRolid());
        try {
            String response = cx.put("usuario/json/update", jsonPut);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateUsuarioDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        System.out.println("jsonPost deleteUser: " + jsonDelete);
        try {
            String response = cx.delete("usuario/json/delete", jsonDelete);
            System.out.println("jsonReponse deleteUser: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("usuario").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteUserDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public String logIn(String rut, String clave) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("rut", rut);
        jsonPost.put("clave", clave);
        String response = cx.post("usuario/json/login", jsonPost);
        try {
            JSONObject jsonResp = new JSONObject(response.trim());
            Iterator<?> keys = jsonResp.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (key.equals("login")) {
                    VariablesDAL.setToken(jsonResp.getJSONArray("login").getJSONObject(0).getString("TOKEN"));
                    VariablesDAL.setIdUsuario(jsonResp.getJSONArray("login").getJSONObject(0).getInt("ID"));
                    return "login exitoso";
                } else if (key.equals("RESPUESTA")) {
                    return jsonResp.getJSONArray("RESPUESTA").getJSONObject(0).getString("ADVERTENCIA");
                }
            }
        } catch (Exception e) {
            System.out.println("logInDAL catch: " + e.getMessage());
            return "Error de conexión, inténtelo nuevamente";
        }
        return "Error de conexión, inténtelo nuevamente";
    }

    public ArrayList<UsuarioO> getJefes() {
        ArrayList<UsuarioO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        try {
            String response = cx.post("usuario/json/read_jefes", jsonPost);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("jefes").length() > 0) {
                for (int i = 0; i < jsonResponse.getJSONArray("jefes").length(); i++) {
                    UsuarioO obj = new UsuarioO();
                    obj.setId(jsonResponse.getJSONArray("jefes").getJSONObject(i).getInt("ID"));
                    obj.setRut(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("RUT"));
                    obj.setRutjefe(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("RUT_JEFE"));
                    obj.setNombre(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("NOMBRE"));
                    obj.setApellido(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("APELLIDO"));
                    obj.setEmail(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("EMAIL"));
                    obj.setSexo(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("SEXO"));
                    obj.setFono(jsonResponse.getJSONArray("jefes").getJSONObject(i).getInt("FONO"));
                    obj.setRolid(jsonResponse.getJSONArray("jefes").getJSONObject(i).getInt("ROL_ID"));
                    obj.setRol_nombre(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("ROL_NOMBRE"));
                    obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("jefes").getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonResponse.getJSONArray("jefes").getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getJefesDAL catch: " + e.getMessage());
        }
        return list;
    }
}
