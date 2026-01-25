package app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import main.java.app.modelo.Incidencia;
import main.java.app.modelo.Incidencia;
import main.java.app.modelo.Usuario;

public class GestorFicheros {
    Utils utilitats = new Utils();

    public void grabarIncidencias(ArrayList<Incidencia> llistatIncidencies, File ficheroIncidencias,
            File ficheroClientes) throws IOException {
        BufferedWriter escriptorClientes = utilitats.obrirEscriptors(ficheroClientes);
        BufferedWriter escriptor = utilitats.obrirEscriptors(ficheroIncidencias);

        for (int it = 0; it < llistatIncidencies.size(); it++) {
            escriptor.write(llistatIncidencies.get(it).getId() + ";");
            escriptor.write(llistatIncidencies.get(it).getDescripcion() + ";");
            escriptor.write(String.valueOf(llistatIncidencies.get(it).getEstado()) + ";");
            escriptor.write(llistatIncidencies.get(it).getUser().id() + ";");
            escriptor.write(llistatIncidencies.get(it).getUser().nom() + ";");
            escriptor.flush();

            escriptorClientes.write(llistatIncidencies.get(it).getUser().id() + ";");
            escriptorClientes.write(llistatIncidencies.get(it).getUser().nom() + ";");
            escriptorClientes.write(llistatIncidencies.get(it).getUser().email() + ";");
            escriptorClientes.flush();
        }
    }

    public void cargarFichero(ArrayList<Incidencia> llistatIncidencies, File ficheroIncidencias,
            File ficheroClientes) throws IOException {
                Incidencia incidenciaTemporal = new Incidencia(null, null, new Usuario(null, null, null));

                BufferedReader lectorClientes = utilitats.obrirLectors(ficheroClientes);
                BufferedReader lectorIncidencias = utilitats.obrirLectors(ficheroIncidencias);

                do{
                    String liniaBufferCliente = lectorClientes.readLine();
                    String liniaBufferIncidencia = lectorIncidencias.readLine();
                    if (liniaBufferCliente == null && liniaBufferIncidencia == null) break;                       
                    
                    else incidenciaTemporal.trocejarString(incidenciaTemporal, liniaBufferIncidencia, liniaBufferCliente);

                    llistatIncidencies.add(incidenciaTemporal);
                }while(true);




    }

}