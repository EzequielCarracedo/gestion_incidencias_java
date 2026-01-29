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
    public static List<Incidencia> obrirLlistat() {
        return new ArrayList<Incidencia>();
    }

    // CREAR REGLA DE TAMAÑO
    public void crearIncidencia(List<Incidencia> incidencias) {
        Usuario usuarioTemporal = new Usuario(utilitats.idIncrementUsuari(incidencias),
                utilitats.demanarString("Ingresa tu nombre: "),
                utilitats.demanarString("Ingresa tu email: "));
        Incidencia novaIncidencia = new Incidencia(utilitats.idIncrementIncidencia(incidencias),
                utilitats.demanarString("Describe la incidencia"),
                usuarioTemporal);

        incidencias.add(novaIncidencia);

    }

    public void listarIncidencias(List<Incidencia> incidencias) {

        if (incidencias.size() != 0) {
            for (int it = 0; it < incidencias.size(); it++) {
                System.out.println("ID INCIDENCIA: " + incidencias.get(it).getId() + ", DESCRIPCION: " + incidencias.get(it).getDescripcion()+", ESTADO: "+ incidencias.get(it).getEstado() + ", USUARIO: "
                        + incidencias.get(it).getUser().nom() + ", ID USUARIO: " + incidencias.get(it).getUser().id());
                        
            }
        } else
            System.out.println("NO HAY INCIDENCIAS.");
    }

    public int buscarPorId(int id, List<Incidencia> incidencias) {
        int result = 0;
        for (int it = 0; it < incidencias.size(); it++) {
            int idElemento = incidencias.get(it).getId();
            if (idElemento == id) {
                System.out.println(incidencias.get(it).imprimirIncidencia());
                result = it;
                return result;
            }
        }
        System.out.println("No se ha encontrado ninguna incidencia con el id: " + id);
        result = Integer.MIN_VALUE;
        return result;
    }

    public void modificarIncidencia(int index, List<Incidencia> incidencias) {

        System.out.println("¿QUIERES CAMBIAR LA DESCRIPCION? SI/NO");
        boolean cambiarDescripcion = utilitats.demanarString("").toUpperCase().equals("SI") ? true : false;

        if (cambiarDescripcion) {
            incidencias.get(index)
                    .setDescripcion(utilitats.demanarString("Ingresa la nueva descripción."));

        }

        System.out.println("¿QUIERES CAMBIAR EL ESTADO? SI/NO");
        boolean cambiarEstado = utilitats.demanarString("").toUpperCase().equals("SI") ? true : false;
        if (cambiarEstado) {
            System.out.println("""
                    1-EN PROCESO
                    2-CERRADA
                    """);
            int eleccioEstado = utilitats.demanarEnter("", 1, 2);
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

    }

}
