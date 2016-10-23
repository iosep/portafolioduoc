/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author iosep
 */
public class VariablesDAL {
    
    static String token;
    static int idUsuario;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        VariablesDAL.token = token;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        VariablesDAL.idUsuario = idUsuario;
    }
    
}
