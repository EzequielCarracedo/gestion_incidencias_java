package app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;


import app.gestor.GestorIncidencias;
import app.modelo.Incidencia;
import app.modelo.Usuario;

public class GestorFicheros {
    Utils utilitats = new Utils();

    public static void grabarIncidencias(GestorIncidencias gestor, File ficheroIncidencias, File ficheroClientes)
            throws IOException {

        if (gestor == null || ficheroIncidencias == null || ficheroClientes == null) {
            throw new IllegalArgumentException("Ninguno de los parámetros puede ser nulo.");
        }

        if (gestor.getLlistat().isEmpty()) {
            throw new RuntimeException("No hay incidencias para grabar en los ficheros.");
        }

        BufferedWriter escriptorClientes = Utils.obrirEscriptors(ficheroClientes);
        BufferedWriter escriptor = Utils.obrirEscriptors(ficheroIncidencias);

        for (int it = 0; it < gestor.getLlistat().size(); it++) {
            escriptor.write(integridadEspais(Integer.toString(gestor.getLlistat().get(it).getId()), 5) + ";");
            escriptor.write(integridadEspais(gestor.getLlistat().get(it).getDescripcion(), 100) + ";");
            escriptor.write(integridadEspais(String.valueOf(gestor.getLlistat().get(it).getEstado()), 15) + ";");
            escriptor.write(integridadEspais(Integer.toString(gestor.getLlistat().get(it).getUser().id()), 5) + ";");
            escriptor.write(integridadEspais(gestor.getLlistat().get(it).getUser().nom(), 30) + ";");
            escriptor.flush();
            escriptor.newLine();

            escriptorClientes
                    .write(integridadEspais(Integer.toString(gestor.getLlistat().get(it).getUser().id()), 5) + ";");
            escriptorClientes.write(integridadEspais(gestor.getLlistat().get(it).getUser().nom(), 30) + ";");
            escriptorClientes.write(integridadEspais(gestor.getLlistat().get(it).getUser().email(), 20) + ";");
            escriptorClientes.flush();
            escriptorClientes.newLine();

        }
        escriptor.close();
        escriptorClientes.close();

    }

    public static void cargarFichero(GestorIncidencias gestor, File ficheroIncidencias,
            File ficheroClientes) throws IOException {

        if (gestor == null || ficheroIncidencias == null || ficheroClientes == null) {
            throw new IllegalArgumentException("Ninguno de los parámetros puede ser nulo.");
        }

        BufferedReader lectorClientes = Utils.obrirLectors(ficheroClientes);
        BufferedReader lectorIncidencias = Utils.obrirLectors(ficheroIncidencias);

        do {

            String liniaBufferCliente = lectorClientes.readLine();
            String liniaBufferIncidencia = lectorIncidencias.readLine();
            if (liniaBufferCliente == null && liniaBufferIncidencia == null)
                break;

            else {
                Incidencia incidenciaTemporal = trocejarString(liniaBufferIncidencia,
                        liniaBufferCliente);
                gestor.getLlistat().add(incidenciaTemporal);
            }

        } while (true);

        lectorClientes.close();
        lectorIncidencias.close();
    }


    private static Incidencia trocejarString(String lineaIncidencia, String lineaCliente) {

        String dadesIncidencia[] = lineaIncidencia.split(";");
        String dadesClient[] = lineaCliente.split(";");

        Incidencia incidencia = new Incidencia(Integer.parseInt(dadesIncidencia[0].trim()), dadesIncidencia[1].trim(),
                new Usuario(Integer.parseInt(dadesClient[0].trim()),
                        dadesClient[1].trim(),
                        dadesClient[2].trim()));

        return incidencia;
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
