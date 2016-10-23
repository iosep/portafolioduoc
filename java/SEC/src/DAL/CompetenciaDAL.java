/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import FN.Formato;
import O.CompetenciaO;
import RESTful.Conexion;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author iosep
 */
public class CompetenciaDAL {

    private final Conexion cx = new Conexion();

    public ArrayList<CompetenciaO> getCompetencias() {
        ArrayList<CompetenciaO> list = new ArrayList<>();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        //System.out.println("getCompetencias post: " + jsonPost);
        try {
            String response = cx.post("competencia/json/read_all", jsonPost);
            //System.out.println("getCompetencias response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencias").length() > 0) {
                for (int i = 0; i < jsonResponse.getJSONArray("competencias").length(); i++) {
                    CompetenciaO obj = new CompetenciaO();
                    obj.setId(jsonResponse.getJSONArray("competencias").getJSONObject(i).getInt("ID"));
                    obj.setNombre(jsonResponse.getJSONArray("competencias").getJSONObject(i).getString("NOMBRE"));
                    obj.setSigla(jsonResponse.getJSONArray("competencias").getJSONObject(i).getString("SIGLA"));
                    obj.setDescripcion(jsonResponse.getJSONArray("competencias").getJSONObject(i).getString("DESCRIPCION"));
                    obj.setNivelOptimo(jsonResponse.getJSONArray("competencias").getJSONObject(i).getInt("N_OPTIMO"));
                    obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("competencias").getJSONObject(i).getString("CREADO")));
                    obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("competencias").getJSONObject(i).getString("MODIFICADO")));
                    obj.setActivo(jsonResponse.getJSONArray("competencias").getJSONObject(i).getInt("ACTIVO"));
                    list.add(obj);
                }
                return list;
            }
        } catch (Exception e) {
            System.out.println("getCompetenciasDAL catch: " + e.getMessage());
        }
        return list;
    }

    public CompetenciaO getCompetenciaById(int id) {
        CompetenciaO obj = new CompetenciaO();
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("id", id);
        try {
            String response = cx.post("competencia/json/read_id", jsonPost);
            System.out.println("competenciaById response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia").length() > 0) {
                obj.setId(jsonResponse.getJSONArray("competencia").getJSONObject(0).getInt("ID"));
                obj.setNombre(jsonResponse.getJSONArray("competencia").getJSONObject(0).getString("NOMBRE"));
                obj.setSigla(jsonResponse.getJSONArray("competencia").getJSONObject(0).getString("SIGLA"));
                obj.setDescripcion(jsonResponse.getJSONArray("competencia").getJSONObject(0).getString("DESCRIPCION"));
                obj.setNivelOptimo(jsonResponse.getJSONArray("competencia").getJSONObject(0).getInt("N_OPTIMO"));
                obj.setCreado(Formato.stringToDate(jsonResponse.getJSONArray("competencia").getJSONObject(0).getString("CREADO")));
                obj.setModificado(Formato.stringToDate(jsonResponse.getJSONArray("competencia").getJSONObject(0).getString("MODIFICADO")));
                obj.setActivo(jsonResponse.getJSONArray("competencia").getJSONObject(0).getInt("ACTIVO"));
                return obj;
            }
        } catch (Exception e) {
            System.out.println("getCompetenciaByIdDAL catch: " + e.getMessage());
        }
        return obj;
    }

    public boolean addCompetencia(CompetenciaO obj) {
        JSONObject jsonPost = new JSONObject();
        jsonPost.put("idusuario", VariablesDAL.idUsuario);
        jsonPost.put("token", VariablesDAL.token);
        jsonPost.put("nombre", obj.getNombre());
        jsonPost.put("sigla", obj.getSigla());
        jsonPost.put("descripcion", obj.getDescripcion());
        jsonPost.put("noptimo", obj.getNivelOptimo());
        System.out.println("addCompetencia post: " + jsonPost);
        try {
            String response = cx.post("competencia/json/create", jsonPost);
            System.out.println("addCompetencia response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencias").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("addCompetenciaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean updateArea(CompetenciaO obj) {
        JSONObject jsonPut = new JSONObject();
        jsonPut.put("idusuario", VariablesDAL.idUsuario);
        jsonPut.put("token", VariablesDAL.token);
        jsonPut.put("id", obj.getId());
        jsonPut.put("nombre", obj.getNombre());
        jsonPut.put("sigla", obj.getSigla());
        //add desc
        System.out.println("updateArea put: " + jsonPut);
        try {
            String response = cx.put("competencia/json/update", jsonPut);
            System.out.println("updateArea response: " + response);
            JSONObject jsonResponse = new JSONObject(response.trim());
            if (jsonResponse.getJSONArray("competencia").length() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateCompetenciaDAL catch: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteArea(int id) {
        JSONObject jsonDelete = new JSONObject();
        jsonDelete.put("idusuario", VariablesDAL.idUsuario);
        jsonDelete.put("token", VariablesDAL.token);
        jsonDelete.put("id", id);
        System.out.println("jsonPost deleteArea: " + jsonDelete);
        try {
            String response = cx.delete("area/json/delete", jsonDelete);
            System.out.println("jsonReponse deleteArea: " + response);
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

}
