package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JoseOnate
 */
public class Conexion {

    private Connection conexion;
    private Statement declaracion;

    public Conexion() {
    }

    private Connection conectar() {
        String controlador = "com.mysql.jdbc.Driver";
        try {
            Class.forName(controlador).newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/radiotaxi", "root", "");
            return conexion;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Statement crearDeclaracion() {
        try {
            declaracion = (Statement) conectar().createStatement();
            return declaracion;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void cerrar() throws SQLException {
        declaracion.close();
        conexion.close();
    }
}
