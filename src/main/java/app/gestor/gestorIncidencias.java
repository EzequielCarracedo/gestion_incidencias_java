package app.gestor;

import java.util.ArrayList;
import java.util.List;

import app.modelo.EstatIncidencia;
import app.modelo.Incidencia;
import app.modelo.Usuario;
import app.util.Utils;

public class GestorIncidencias {

    Utils utilitats = new Utils();

    // Lista donde guardamos las incidencias.
    private List<Incidencia> incidencias;

    public void gestor() {
        this.incidencias = new ArrayList<>();

    }


    //CREAR REGLA DE TAMAÑO 
    public void crearIncidencia() {
        Usuario usuarioTemporal = new Usuario(null, utilitats.demanarString("Ingresa tu nombre: "),
                utilitats.demanarString("Ingresa tu email: "));
        Incidencia novaIncidencia = new Incidencia(null, utilitats.demanarString("Describe la incidencia"),
                usuarioTemporal);

        incidencias.add(novaIncidencia);
    }

    public void listarIncidencias() {

        if (incidencias.size() != 0) {
            for (int it = 0; it < incidencias.size(); it++) {
                System.out.println(incidencias.get(it).toString());
            }
        } else
            System.out.println("No hay incidencias.");
    }

    public int buscarPorId(String id) {
        int result = 0;
        for (int it = 0; it < incidencias.size(); it++) {
            String idElemento = incidencias.get(it).getId();
            if (idElemento.equals(id)) {
                System.out.println(incidencias.get(it).toString());
                return result;
            }
        }
        System.out.println("No se ha encontrado ninguna incidencia con el id: " + id);
        return result;
    }

    public void modificarIncidencia(String id, boolean cambiarDescripcion, int eleccioEstado, boolean cambiarEstado) {
        int index = buscarPorId(id);
        if (cambiarEstado) {
            switch (eleccioEstado) {
                case 1:
                    incidencias.get(index).cambiarEstado(EstatIncidencia.EN_PROCESO);
                    break;
                case 2:
                    incidencias.get(index).cambiarEstado(EstatIncidencia.CERRADA);
                    break;
                default:
                    break;
            }

        }
        if (cambiarDescripcion) {
            incidencias.get(index)
                    .setDescripcion(utilitats.demanarString("Ingresa la nueva descripción."));
        }

    }

}
