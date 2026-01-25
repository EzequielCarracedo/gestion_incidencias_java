package app.modelo;

public class Incidencia {
    private String id;
    private String descripcion;
    private Usuario user;
    private EstatIncidencia estado;

    // BUILDER
    public Incidencia(String id, String descripcion, Usuario user) {

        this.id = id;
        this.descripcion = descripcion;
        this.user = user;
        this.estado = EstatIncidencia.ABIERTA;
    }

    // GETTERS

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getUser() {
        return user;
    }

    public EstatIncidencia getEstado() {
        return estado;
    }

    // SETTER

    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.isEmpty()) { // Validación
            this.descripcion += descripcion;
        }
    }

    // CAMBIAR ESTADO
    public boolean cambiarEstado(EstatIncidencia nuevoEstado) {

        if (estado.equals(EstatIncidencia.ABIERTA) && nuevoEstado.equals(EstatIncidencia.EN_PROCESO)) {
            this.estado = EstatIncidencia.EN_PROCESO;
            System.out.println("EL ESTADO DE HA CAMBIADO A 'EN PROCESO'.");
            return true;
        } else if (estado.equals(EstatIncidencia.EN_PROCESO) && nuevoEstado.equals(EstatIncidencia.CERRADA)) {
            this.estado = EstatIncidencia.CERRADA;
            System.out.println("EL ESTADO DE HA CAMBIADO A 'CERRADA'.");
            return true;
        } else {
            return false;
        }
    }

    public void trocejarString(Incidencia incidencia, String lineaIncidencia, String lineaCliente) {

        incidencia.id = lineaIncidencia.substring(0, 6);
        incidencia.descripcion = lineaIncidencia.substring(6, 107);
        incidencia.user = new Usuario(lineaIncidencia.substring(0, 6), lineaIncidencia.substring(6, 37),
                lineaCliente.substring(37, 58));
    }

}