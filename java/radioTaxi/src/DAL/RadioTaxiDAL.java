package DAL;

import BLL.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JoseOnate
 */
public class RadioTaxiDAL {

    private final ArrayList<Carrera> lsTrips;
    private final ArrayList<RaceTable> lsRace;
    private final ArrayList<String> lsDisponible;
    private final ArrayList<String> lsRutCliente;
    private ResultSet result;
    private final Conexion link;

    public RadioTaxiDAL() {
        this.lsTrips = new ArrayList();
        this.lsRace = new ArrayList();
        this.lsDisponible = new ArrayList();
        this.lsRutCliente = new ArrayList();
        link = new Conexion();
    }

    public boolean existeCliente(String rut) throws SQLException {
        result = link.crearDeclaracion().executeQuery(
                "select rut from cliente");
        try {
            while (result.next()) {
                this.lsRutCliente.add(result.getString(1));
            }
            for (String item : lsRutCliente) {
                if (item.compareTo(rut) == 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public ArrayList<String> disponibles() throws SQLException {
        result = link.crearDeclaracion().executeQuery(
                "select patente from taxi where estado = 'D'");
        try {
            while (result.next()) {
                this.lsDisponible.add(result.getString(1));
            }
            return this.lsDisponible;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ArrayList<String> enCarrera() throws SQLException {
        result = link.crearDeclaracion().executeQuery(
                "select patente from taxi where estado = 'C'");
        try {
            while (result.next()) {
                this.lsDisponible.add(result.getString(1));
            }
            return this.lsDisponible;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean saveCarrera(Carrera race) throws SQLException {
        try {
            int nrows = link.crearDeclaracion().executeUpdate(
                    "insert into carrera "
                    + "(fecha, direccion_destino, direccion_inicio, hora_salida, rut_cliente, patente_taxi)"
                    + "values "
                    + "('" + race.getFecha() + "', '" + race.getDire_destino() + "', '" + race.getDire_inicio() + "', "
                    + "'" + race.getHora_salida() + "', '" + race.getRut_cliente() + "', '" + race.getPatente_taxi() + "')");
            if (nrows > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            link.cerrar();
        }
        return false;
    }

    public boolean updateCarrera(Carrera trip) throws SQLException {
        try {
            int nrows = link.crearDeclaracion().executeUpdate(
                    "update carrera set "
                    + "fecha_llegada='" + trip.getFecha_llegada() + "', "
                    + "patente_taxi='" + trip.getPatente_taxi() + "', "
                    + "hora_llegada='" + trip.getHora_llegada() + "' "
                    + "where idCarrera='" + trip.getId_carrera() + "'");
            if (nrows > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            link.cerrar();
        }
        return false;
    }

    public boolean saveCliente(Cliente client) throws SQLException {
        try {
            int nrows = link.crearDeclaracion().executeUpdate(
                    "insert into cliente "
                    + "(rut, nombre, apellido)"
                    + "values "
                    + "('" + client.getRut() + "', '" + client.getNombre() + "', '" + client.getApellido() + "')");
            if (nrows > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            link.cerrar();
        }
        return false;
    }

    public boolean saveTaxi(Taxi tx) throws SQLException {
        try {
            int nrows = link.crearDeclaracion().executeUpdate(
                    "insert into taxi "
                    + "(patente, estado)"
                    + "values "
                    + "('" + tx.getPatente() + "', '" + tx.getEstado() + "')");
            if (nrows > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            link.cerrar();
        }
        return false;
    }
    
    public boolean updateTaxi(Taxi tx) throws SQLException {
        try {
            int nrows = link.crearDeclaracion().executeUpdate(
                    "update taxi set "
                    + "estado='" + tx.getEstado() + "' "
                    + "where patente='" + tx.getPatente() + "'");
            if (nrows > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            link.cerrar();
        }
        return false;
    }

    public ArrayList<RaceTable> listRace() throws SQLException {
        result = link.crearDeclaracion().executeQuery(
                "select count(idCarrera), extract(year from fecha), extract(month from fecha), patente_taxi "
                + "from carrera "
                + "group by fecha, patente_taxi");
        try {
            while (result.next()) {
                RaceTable rt = new RaceTable();
                rt.setRaceAmount(result.getInt(1));
                rt.setYear(result.getInt(2));
                rt.setMonth(result.getInt(3));
                rt.setPatente(result.getString(4));
                this.lsRace.add(rt);
            }
            return this.lsRace;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Carrera> listTrips() throws SQLException {
        result = link.crearDeclaracion().executeQuery(
                "select * from carrera ");
        try {
            while (result.next()) {
                Carrera trip = new Carrera();
                trip.setId_carrera(result.getInt("idCarrera"));
                this.lsTrips.add(trip);
            }
            return this.lsTrips;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
