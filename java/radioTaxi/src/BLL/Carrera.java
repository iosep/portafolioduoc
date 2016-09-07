package BLL;

/**
 *
 * @author JoseOnate
 */
public class Carrera {

    private int id_carrera;
    private String fecha;
    private String fecha_llegada;
    private String dire_destino;
    private String dire_inicio;
    private String hora_salida;
    private String hora_llegada;
    private String rut_cliente;
    private String patente_taxi;

    public Carrera() {
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getDire_destino() {
        return dire_destino;
    }

    public void setDire_destino(String dire_destino) {
        this.dire_destino = dire_destino;
    }

    public String getDire_inicio() {
        return dire_inicio;
    }

    public void setDire_inicio(String dire_inicio) {
        this.dire_inicio = dire_inicio;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getPatente_taxi() {
        return patente_taxi;
    }

    public void setPatente_taxi(String patente_taxi) {
        this.patente_taxi = patente_taxi;
    }

}
