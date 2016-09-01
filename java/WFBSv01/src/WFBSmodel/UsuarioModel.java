/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WFBSmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author iosep
 */
public class UsuarioModel {

    @SuppressWarnings("null")
    public boolean findUserPass(String un, String pass) {
        boolean bul = false;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:iosep/iosep@localhost");
            stmt = con.prepareStatement("SELECT password FROM usuario WHERE username = ?");
            stmt.setString(1, un);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString(1));
                if (rs.getString(1).equals(pass)) {
                    bul = true;
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
        return bul;
    }
}
