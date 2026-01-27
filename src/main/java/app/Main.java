package app;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

import app.gestor.GestorIncidencias;
import app.modelo.Usuario;
import app.util.Utils;

class Main {

    private GestorIncidencias gestor;
    private Utils utilitats;

    public Main() {
        gestor = new GestorIncidencias();
        utilitats = new Utils();
    }

    public static void main(String[] args) {
        Main app = new Main();
        Utils utilitats = new Utils();
        int eleccio = 0;
        try {
            File incidencias = new File("data\\incidencias.txt");
            File usuarios = new File("data\\usuarios.txt");
            incidencias.createNewFile();
            usuarios.createNewFile();

            System.out.println("CARGANDO DATOS FICHERO.");

        } catch (IOException e) {
            System.out.println("ERROR FATAL.");
        }

        do {
            mostrarMenu();
            eleccio = utilitats.demanarEnter("", 0, 4);

            if (eleccio != 0) {
                app.gestionarEleccion(eleccio);
            }

        } while (eleccio != 0);

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

    public void gestionarEleccion(int eleccion) {

        switch (eleccion) {
            case 1:
                gestor.crearIncidencia();

                break;

            case 2:
                gestor.listarIncidencias();

            default:
                break;
        }

    }

}