package app;

import java.io.File;
import java.io.IOException;
import app.gestor.GestorIncidencias;
import app.util.GestorFicheros;
import app.util.Utils;






//PENDENTS 
//Regla de maxim caracters en demanar string
//validacion @ email regex


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
        File ficheroIncidencias = new File("data\\incidencias.txt");
        File usuarios = new File("data\\usuarios.txt");
        try {
            ficheroIncidencias.createNewFile();
            usuarios.createNewFile();

            System.out.println("\n*CARGANDO DATOS FICHERO*");

        } catch (IOException e) {
            System.out.println("ERROR FATAL.");
        }
        GestorFicheros.cargarFichero(app.gestor, ficheroIncidencias, usuarios);
        System.out.println("\n*FICHERO CARGADO CORRECTAMENTE* \n");
        do {
            mostrarMenu();
            eleccio = utilitats.demanarEnter("", 0, 5);

            if (eleccio != 0) {
                app.gestionarEleccion(eleccio);
            }

        } while (eleccio != 0);

        GestorFicheros.grabarIncidencias(app.gestor, ficheroIncidencias, usuarios);
    }

    public static void mostrarMenu() {
        System.out.println("""
                **************************************************
                1) - CARGAR NUEVA INCIDENCIA
                2) - LISTAR INCIDENCIAS
                3) - BUSCAR INDICENCIA POR ID
                4) - MODIFICAR INCIDENCIA
                5) - ELIMINAR INCIDENCIA
                0) - SALIR

                    """);
    }

    public void gestionarEleccion(int eleccion) {

        switch (eleccion) {
            case 1:
                gestor.crearIncidencia();

                break;

            case 2:
                gestor.listarIncidencias();
                break;

            case 3: {
                int id = utilitats.demanarEnter("Ingresa el id de la incidencia: ", Integer.MIN_VALUE,
                        Integer.MAX_VALUE);
                gestor.buscarPorId(id);
            }
                break;

            case 4: {
                int id = utilitats.demanarEnter("INGRESA EL ID DE LA INCIDENCIA QUE QUIERES MODIFICAR: ",
                        Integer.MIN_VALUE, Integer.MAX_VALUE);
                int index = gestor.buscarPorId(id);
                if (index != Integer.MIN_VALUE) {
                    gestor.modificarIncidencia(index);
                }
                break;
            }
            case 5: {
                int eleccio = utilitats.demanarEnter("INGRESA EL ID DE LA INCIDENCIA QUE QUIERES BORRAR: ",
                        Integer.MIN_VALUE, Integer.MAX_VALUE);
                int index = gestor.buscarPorId(eleccio);
                if (index != Integer.MIN_VALUE) {
                    gestor.eliminarIncidencia(index);
                    System.out.println("INCIDENCIA BORRADA CON EXITO");
                }
                break;
            }

            default:
                break;
        }

    }

}