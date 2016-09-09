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

    public int userLogin(String usuario, String password) {
        int userId = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:dotnet/dotnet@localhost");
            stmt = con.prepareStatement("SELECT password, id_usuario FROM usuario WHERE nombre = ?");
            stmt.setString(1, usuario);
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

}
