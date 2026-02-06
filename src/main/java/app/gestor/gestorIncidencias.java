package app.gestor;

import java.util.ArrayList;
import java.util.List;

import app.modelo.EstatIncidencia;
import app.modelo.Incidencia;
import app.modelo.Usuario;
import app.util.Utils;

public class GestorIncidencias {

    Utils utilitats = new Utils();

    private List<Incidencia> llistatIncidencies;

    public GestorIncidencias() {
        this.llistatIncidencies = new ArrayList<Incidencia>();
    }

    public List<Incidencia> getLlistat() {
        return llistatIncidencies;
    }

    public void crearIncidencia() {
        Usuario usuarioTemporal = crearUsuario();

        while (true) {
            try {
                Incidencia novaIncidencia = new Incidencia(utilitats.idIncrementIncidencia(llistatIncidencies),
                        utilitats.demanarString("Describe la incidencia"),
                        usuarioTemporal);
                llistatIncidencies.add(novaIncidencia);
                System.out.println("INCIDENCIA CREADA CORRECTAMENTE.\n");
                break;
            } catch (IllegalArgumentException a) {
                System.out.println("ERROR: " + a.getMessage());
                System.out.println("Vuelve a introducir la descripción: \n");
            }

        }

    }

    public Usuario crearUsuario() {
        while (true) {
            try {
                String nom = utilitats.demanarString("Nombre:");
                String email = utilitats.demanarString("Email:");

                return new Usuario(
                        utilitats.idIncrementUsuari(llistatIncidencies),
                        nom,
                        email);

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Vuelve a introducir los datos: \n");
            }
        }
    }

    public void listarIncidencias() {

        if (llistatIncidencies.size() != 0) {
            for (int it = 0; it < llistatIncidencies.size(); it++) {
                System.out.println("ID INCIDENCIA: " + llistatIncidencies.get(it).getId() + ", DESCRIPCION: "
                        + llistatIncidencies.get(it).getDescripcion() + ", ESTADO: "
                        + llistatIncidencies.get(it).getEstado() + ", USUARIO: "
                        + llistatIncidencies.get(it).getUser().nom() + ", ID USUARIO: "
                        + llistatIncidencies.get(it).getUser().id());

            }
        } else
            System.out.println("NO HAY INCIDENCIAS.");
    }

    public int buscarPorId(int id) {
        int result = 0;
        for (int it = 0; it < llistatIncidencies.size(); it++) {
            int idElemento = llistatIncidencies.get(it).getId();
            if (idElemento == id) {
                System.out.println(llistatIncidencies.get(it).imprimirIncidencia());
                result = it;
                return result;
            }
        }
        System.out.println("\nNO SE HA ENCONTRADO NINGUNA INCIDENCIA CON EL ID: " + id + "\n");
        result = Integer.MIN_VALUE;
        return result;
    }

    public void modificarIncidencia(int index) {
        boolean cambiarDescripcion = false;
        System.out.println("¿QUIERES CAMBIAR LA DESCRIPCION? SI/NO");
        while (true) {
            String elec = utilitats.demanarString("").toUpperCase();
            if (elec.equals("SI") || elec.equals("NO")) {
                cambiarDescripcion = elec.toUpperCase().equals("SI") ? true : false;
                break;
            } else
                System.out.println("OPCION NO VALIDA, VUELVE A INGRESAR LA RESPUESTA: ");
        }

        while (true) {
            try {
                if (cambiarDescripcion) {
                    llistatIncidencies.get(index)
                            .setDescripcion(utilitats.demanarString("Ingresa la nueva descripción."));
                    System.out.println("descripcion modificada con exito!");
                    break;
                } else
                    break;
            } catch (IllegalArgumentException modificar) {
                System.out.println("ERROR: " + modificar.getMessage());
                System.out.println("Vuelve a introducir los datos: \n");
            }
        }

        System.out.println("¿QUIERES CAMBIAR EL ESTADO? SI/NO");

        boolean cambiarEstado;
        while (true) {
            String eleccio = utilitats.demanarString("").toUpperCase();
            if (eleccio.equals("SI") || eleccio.equals("NO")) {
                cambiarEstado = eleccio.toUpperCase().equals("SI") ? true : false;
                break;
            } else
                System.out.println("OPCION NO VALIDA, VUELVE A INGRESAR LA RESPUESTA: ");
        }

        if (cambiarEstado) {
            boolean estatModificat = false;
            while (!estatModificat) {

                System.out.println("""
                        1-EN PROCESO
                        2-CERRADA
                        """);

                int eleccioEstado = utilitats.demanarEnter("", 1, 2);

                switch (eleccioEstado) {
                    case 1:
                        estatModificat = llistatIncidencies.get(index).cambiarEstado(EstatIncidencia.EN_PROCESO);
                        break;

                    case 2:
                        estatModificat = llistatIncidencies.get(index).cambiarEstado(EstatIncidencia.CERRADA);
                        break;

                    default:
                        break;
                }
            }

        }

    }

    public void eliminarIncidencia(int index) {
        llistatIncidencies.remove(index);
    }

}
