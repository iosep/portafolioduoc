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
import java.util.ArrayList;

/**
 *
 * @author iosep
 */
public class UsuarioModel {

    /**
     * Method Jefes List
     *
     * @return ArrayList Jefes List
     */
    public ArrayList<Jefes> listaJefes() {
        ArrayList<Jefes> jefes = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:WFBS/wfbs@localhost");
            stmt = con.prepareStatement("SELECT nombres, apaterno, amaterno, id FROM usuario WHERE rol_id = 2");
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Sin coincidencias");
                stmt.close();
                con.close();
                return jefes;
            } else {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String[] nombres = rs.getString(1).split(" ");
                    String apaterno = rs.getString(2);
                    String amaterno = rs.getString(3);
                    Jefes jefe = new Jefes(rs.getInt(4), nombres[0] + " " + apaterno + " " + amaterno);
                    jefes.add(jefe);
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
        return jefes;
    }
}
