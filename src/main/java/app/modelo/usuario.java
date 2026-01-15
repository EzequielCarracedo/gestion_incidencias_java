package main.java.app.modelo;

public record Usuario(String id,String nom, String email) {
    public Usuario{
        if (id.isBlank() || id.isEmpty()) {
            throw new IllegalArgumentException("El id no puede estar vacio.");
        }
        if (nom.isEmpty() || nom.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacio.");
        }

    }

    public static void imprimirUsuario(){
        //Imprimir un usuario en una posicion

    }

    public static void listarUsuarios(){

        //utilizar imprimir usuario para e iterar una pos para pasarle


    }




}