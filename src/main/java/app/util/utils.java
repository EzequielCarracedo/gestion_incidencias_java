package main.java.app.util;

import java.util.Scanner;

public class utils {
    Scanner scan = new Scanner(System.in);

    public String demanarString(String missatge){ 
        System.out.println(missatge);
        String dada = scan.nextLine();

        return dada;
    }

}
