/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GRUPO2
 */
public class LoginModel {

    /**Login Model Method
     * 
     * @param rut
     * @param password
     * @return userId
     */
    public int userLogin(int rut, String password) {
        int userId = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:WFBS/wfbs@localhost");
            stmt = con.prepareStatement("SELECT clave, id FROM usuario WHERE rut = ?");
            stmt.setInt(1, rut);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Sin coincidencias");
                stmt.close();
                con.close();
                return userId;
            } else {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    if (rs.getString(1).equals(password)) {//password
                        userId = rs.getInt(2);//id_usuario
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return userId;
    }

    /**GetRUT method
     * 
     * @param userId
     * @return RUT
     */
    public String getRut(int userId) {
        String rut = "";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:dotnet/dotnet@localhost");
            stmt = con.prepareStatement("SELECT rut FROM usuario WHERE nombre = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Sin coincidencias");
                stmt.close();
                con.close();
            } else {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    rut = rs.getString(1);//rut
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return rut;
    }

}
