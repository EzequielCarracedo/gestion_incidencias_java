package main.java.app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import main.java.app.modelo.Incidencia;
import main.java.app.modelo.Usuario;

public class gestorFicheros {
    utils utilitats = new utils();

    public void grabarClientes(ArrayList<Incidencia> llistatIncidencies, File ficheroClientes) throws IOException {
        BufferedReader buffer = utilitats.obrirLectors(ficheroClientes);
        BufferedWriter escriptor = utilitats.obrirEscriptors(ficheroClientes);

        for (int it = 0; it < llistatIncidencies.size(); it++) {
            escriptor.write(llistatIncidencies.get(it).getId());
            escriptor.write(llistatIncidencies.get(it).getDescripcion());
            escriptor.write(String.valueOf(llistatIncidencies.get(it).getEstado()));
            escriptor.write(llistatIncidencies.get(it).getUser().id());
            escriptor.write(llistatIncidencies.get(it).getUser().nom());
            escriptor.write(llistatIncidencies.get(it).getUser().email());
            escriptor.flush();
        }

    }

    public void cargarFichero() {

    }

}