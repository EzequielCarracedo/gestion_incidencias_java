package app.modelo;

public record Usuario(int id, String nom, String email) {

    public Usuario {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
    }

}
