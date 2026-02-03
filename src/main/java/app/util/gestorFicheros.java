package app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.Main;

import app.gestor.GestorIncidencias;
import app.modelo.Incidencia;
import app.modelo.Usuario;

public class GestorFicheros {
    Utils utilitats = new Utils();

    Main main = new Main();

    public static void grabarIncidencias(GestorIncidencias gestor, File ficheroIncidencias,File ficheroClientes) throws IOException {
        BufferedWriter escriptorClientes = Utils.obrirEscriptors(ficheroClientes);
        BufferedWriter escriptor = Utils.obrirEscriptors(ficheroIncidencias);

        for (int it = 0; it < gestor.getLlistat().size(); it++) {
            escriptor.write(integridadEspais(Integer.toString(gestor.getLlistat().get(it).getId()), 5) + ";");
            escriptor.write(integridadEspais(gestor.getLlistat().get(it).getDescripcion(), 100) + ";");
            escriptor.write(String.valueOf(gestor.getLlistat().get(it).getEstado()) + ";");
            escriptor.write(integridadEspais(Integer.toString(gestor.getLlistat().get(it).getUser().id()), 5) + ";");
            escriptor.write(integridadEspais(gestor.getLlistat().get(it).getUser().nom(), 30) + ";");
            escriptor.flush();

            escriptorClientes
                    .write(integridadEspais(Integer.toString(gestor.getLlistat().get(it).getUser().id()), 5) + ";");
            escriptorClientes.write(integridadEspais(gestor.getLlistat().get(it).getUser().nom(), 30) + ";");
            escriptorClientes.write(integridadEspais(gestor.getLlistat().get(it).getUser().email(), 20) + ";");
            escriptorClientes.flush();
        }

    }

    public static void cargarFichero(GestorIncidencias gestor,File ficheroIncidencias,
            File ficheroClientes) throws IOException {
        Incidencia incidenciaTemporal = new Incidencia(0, "", new Usuario(0, "", ""));

        BufferedReader lectorClientes = Utils.obrirLectors(ficheroClientes);
        BufferedReader lectorIncidencias = Utils.obrirLectors(ficheroIncidencias);

        do {
            String liniaBufferCliente = lectorClientes.readLine();
            String liniaBufferIncidencia = lectorIncidencias.readLine();
            if (liniaBufferCliente == null && liniaBufferIncidencia == null)
                break;

            else
                trocejarString(incidenciaTemporal, liniaBufferIncidencia,
                        liniaBufferCliente);

            gestor.getLlistat().add(incidenciaTemporal);
        } while (true);

        lectorClientes.close();
        lectorIncidencias.close();
    }

    private static void trocejarString(Incidencia incidencia, String lineaIncidencia, String lineaCliente) {

        incidencia.setId(Integer.parseInt(lineaIncidencia.substring(0, 5).trim()));
        incidencia.setDescripcion(lineaIncidencia.substring(7, 107).trim());
        incidencia.setUser(
                new Usuario(Integer.parseInt(lineaCliente.substring(0, 5).trim()), lineaCliente.substring(7, 37),
                        lineaCliente.substring(38, 58)));
    }

    private static String integridadEspais(String frase, int longitud) {
        String resultat = frase;
        if (frase.length() < longitud) {
            for (int it = 0; it < (longitud - frase.length()); it++) {
                resultat += " ";
            }
        }
        return resultat;

    }

}
