/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.PeriodoO;
import REST.Conexion;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class PeriodoDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<PeriodoO> getPeriodos() {
        ArrayList<PeriodoO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        //System.out.println("getPeriodos post: " + jsonPost);
        try {
            String response = cx.post("periodo/json/read_all", jsonPost);
            //System.out.println("getPeriodos response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("periodo").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("periodo");
                for (int i = 0; i < jsonArray.length(); i++) {
                    PeriodoO obj = new PeriodoO();
                    obj.setId(jsonArray.getJSONObject(i).getInt("ID"));
                    obj.setInicio(Formato.stringToDate(jsonArray.getJSONObject(i).getString("INICIO")));
                    obj.setFin(Formato.stringToDate(jsonArray.getJSONObject(i).getString("FINAL")));
                    obj.setJefe_porc(jsonArray.getJSONObject(i).getInt("JEFE_PORC"));
                    obj.setAuto_porc(jsonArray.getJSONObject(i).getInt("AUTO_PORC"));
                    obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("MODIFICADO")));
                    obj.setDesactivado(Formato.stringToDate(jsonArray.getJSONObject(i).getString("DESACTIVADO")));
                    obj.setActivo(jsonArray.getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getPeriodosDAL catch: " + e.getMessage());
        }
        return list;
    }

    public PeriodoO getPeriodoById(int id) {
        PeriodoO obj = new PeriodoO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("periodo/json/read_id", jsonPost);
            //System.out.println("periodoById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("periodo").length() > 0) {
                JSONArray jsonArray = jsonResponse.getJSONArray("periodo");
                obj.setId(jsonArray.getJSONObject(0).getInt("ID"));
                obj.setInicio(Formato.stringToDate(jsonArray.getJSONObject(0).getString("INICIO")));
                obj.setFin(Formato.stringToDate(jsonArray.getJSONObject(0).getString("FINAL")));
                obj.setJefe_porc(jsonArray.getJSONObject(0).getInt("JEFE_PORC"));
                obj.setAuto_porc(jsonArray.getJSONObject(0).getInt("AUTO_PORC"));
                obj.setCreado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("MODIFICADO")));
                obj.setDesactivado(Formato.stringToDate(jsonArray.getJSONObject(0).getString("DESACTIVADO")));
                obj.setActivo(jsonArray.getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getPeriodoByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addPeriodo(PeriodoO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("inicio", Formato.dateToString(obj.getInicio()));
        jsonPost.put("final", Formato.dateToString(obj.getFin()));
        jsonPost.put("jefeporc", obj.getJefe_porc());
        jsonPost.put("autoporc", obj.getAuto_porc());
        //System.out.println("addPeriodo post: " + jsonPost);
        try {
            String response = cx.post("periodo/json/create", jsonPost);
            //System.out.println("addPeriodo response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("periodo").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addPeriodoDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updatePeriodo(PeriodoO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("inicio", Formato.dateToString(obj.getInicio()));
        jsonPut.put("final", Formato.dateToString(obj.getFin()));
        jsonPut.put("jefeporc", obj.getJefe_porc());
        jsonPut.put("autoporc", obj.getAuto_porc());
        //System.out.println("updatePeriodo put: " + jsonPut);
        try {
            String response = cx.put("periodo/json/update", jsonPut);
            //System.out.println("updatePeriodo response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("periodo").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updatePeriodoDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deletePeriodo(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        //System.out.println("jsonPost deletePeriodo: " + jsonDelete);
        try {
            String response = cx.delete("periodo/json/delete", jsonDelete);
            //System.out.println("jsonReponse deletePeriodo: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("periodo").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deletePeriodoDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean activaPeriodo(int id) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", id);
        //System.out.println("jsonPut activaPeriodo: " + jsonPut);
        try {
            String response = cx.put("periodo/json/activa", jsonPut);
            //System.out.println("jsonReponse activaPeriodo: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("periodo").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("activaPeriodoDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }
}
