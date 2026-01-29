package app;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import app.gestor.GestorIncidencias;
import app.modelo.Incidencia;
import app.modelo.Usuario;
import app.util.GestorFicheros;
import app.util.Utils;

class Main {

    private GestorIncidencias gestor;
    private Utils utilitats;

    public Main() {
        gestor = new GestorIncidencias();
        utilitats = new Utils();
    }

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        Utils utilitats = new Utils();
        int eleccio = 0;
        List<Incidencia> incidencies = GestorIncidencias.obrirLlistat();
        File ficheroIncidencias = new File("data\\incidencias.txt");
        File usuarios = new File("data\\usuarios.txt");
        try {
            ficheroIncidencias.createNewFile();
            usuarios.createNewFile();

            System.out.println("CARGANDO DATOS FICHERO.");
            System.out.println();

        } catch (IOException e) {
            System.out.println("ERROR FATAL.");
        }

        do {
            mostrarMenu();
            eleccio = utilitats.demanarEnter("", 0, 4);

            if (eleccio != 0) {
                app.gestionarEleccion(eleccio, incidencies);
            }

        } while (eleccio != 0);

        GestorFicheros.grabarIncidencias(incidencies, ficheroIncidencias, usuarios);
    }

    public static void mostrarMenu() {
        System.out.println("""
                **************************************************
                1) - CARGAR NUEVA INCIDENCIA
                2) - LISTAR INCIDENCIAS
                3) - BUSCAR INDICENCIA POR ID
                4) - MODIFICAR INCIDENCIA
                0) - SALIR

                    """);
    }

    public void gestionarEleccion(int eleccion, List<Incidencia> incidencias) {

        switch (eleccion) {
            case 1:
                gestor.crearIncidencia(incidencias);

                break;

            case 2:
                gestor.listarIncidencias(incidencias);
                break;

            case 3: {
                int id = utilitats.demanarEnter("Ingresa el id de la incidencia: ", Integer.MIN_VALUE,
                        Integer.MAX_VALUE);
                gestor.buscarPorId(id, incidencias);
            }
                break;

            case 4: {
                int id = utilitats.demanarEnter("INGRESA EL ID DE LA INCIDENCIA QUE QUIERES MODIFICAR: ",
                        Integer.MIN_VALUE, Integer.MAX_VALUE);
                int index = gestor.buscarPorId(id, incidencias);
                if (index != Integer.MIN_VALUE) {
                    gestor.modificarIncidencia(index, incidencias);
                }

            }
                break;

            default:
                break;
        }

    }

}