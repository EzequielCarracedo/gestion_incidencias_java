package app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;

import app.modelo.Incidencia;

public class Utils {
    Scanner scan = new Scanner(System.in);

    public String demanarString(String missatge) {
        System.out.println(missatge);
        String dada = scan.nextLine();

        return dada;
    }

    public int demanarEnter(String missatge, int min, int max) {
        System.out.println(missatge);
        boolean esCorrecte = false;
        int resultat = 0;
        // Fem un bucle per només llegir si el número és un enter i si esta dins del
        // rang indicat.
        while (!esCorrecte) {
            if (scan.hasNextInt()) {
                resultat = scan.nextInt();
                if (resultat >= min && resultat <= max) {
                    esCorrecte = true;
                } else
                    System.out.println("La opcio introduida no es correcte.");
            } else {
                scan.next();
                System.out.println("Això no es un enter");
            }
        }
        return resultat;
    }

    public BufferedReader obrirLectors(File ficheroClientes) throws FileNotFoundException {
        FileReader lector = new FileReader(ficheroClientes);

        return new BufferedReader(lector);
    }

    public BufferedWriter obrirEscriptors(File ficheroClientes) throws IOException {
        FileWriter escriptor = new FileWriter(ficheroClientes);

        return new BufferedWriter(escriptor);
    }

    public int idIncrementIncidencia(List<Incidencia> llistatIncidencies) {
        int max = 999;
        if (llistatIncidencies != null) {
            for (int it = 0; it < llistatIncidencies.size(); it++) {
                if (llistatIncidencies.get(it).getId() > max) {
                    max = llistatIncidencies.get(it).getId();
                }
            }
        }

        return max++;
    }

    public int idIncrementUsuari(List<Incidencia> llistatIncidencies) {
        int max = 999;
        
        if (llistatIncidencies !=  null) {
            for (int it = 0; it < llistatIncidencies.size(); it++) {
                if (llistatIncidencies.get(it).getUser().id() > max) {
                    max = llistatIncidencies.get(it).getId();
                }
            }
        }

        return max++;
    }

}
