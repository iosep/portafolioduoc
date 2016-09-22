/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Jefes;
import Controller.Usuario;
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
                    Jefes jefe = null;
                    if (rs.getString(3) != null) {
                        jefe = new Jefes(rs.getInt(4), nombres[0] + " " + rs.getString(2) + " " + rs.getString(3));
                    } else {
                        jefe = new Jefes(rs.getInt(4), nombres[0] + " " + rs.getString(2));
                    }

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

    public Usuario findUser(int rut) {
        Usuario userFound = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:WFBS/wfbs@localhost");
            stmt = con.prepareStatement(
                    "SELECT id, rut, dv, nombres, apaterno, amaterno, sexo, direccion, fono, email, clave, jefe, rol_id, activo "
                    + "FROM usuario WHERE rut = ? AND rol_id != 1");
            stmt.setInt(1, rut);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Sin coincidencias");
                stmt.close();
                con.close();
            } else {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    userFound = new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3).charAt(0),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).charAt(0),
                            rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12),
                            rs.getInt(13), rs.getInt(14));
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
        return userFound;
    }

    public boolean crearUsuario(int rut, String dv, String sexo, String clave) {
        boolean vb = false;
        Connection con = null;
        PreparedStatement stmt = null;
        //ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:WFBS/wfbs@localhost");
            stmt = con.prepareStatement("INSERT INTO usuario(rut,dv,sexo,clave,rol_id) VALUES(?,?,?,?,3)");
            stmt.setInt(1, rut);
            stmt.setString(2, dv);
            stmt.setString(3, sexo);
            stmt.setString(4, clave);
            stmt.executeUpdate();
            vb = true;
            /*
            rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Sin coincidencias");
                stmt.close();
                con.close();
            } else {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    vb = true;
                }
            }*/
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                //rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return vb;
    }

}
