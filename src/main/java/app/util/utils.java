package app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
    Scanner scan = new Scanner(System.in);

  

    public String demanarString(String missatge){ 
        System.out.println(missatge);
        String dada = scan.nextLine();

        return dada;
    }


    public BufferedReader obrirLectors(File ficheroClientes)throws FileNotFoundException{
        FileReader lector = new FileReader(ficheroClientes);

        return new BufferedReader(lector);
    }

    public BufferedWriter obrirEscriptors(File ficheroClientes) throws IOException{
        FileWriter escriptor = new FileWriter(ficheroClientes);

        return new BufferedWriter(escriptor);
    }

    




}
