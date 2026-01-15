package main.java.app.modelo;

public class Incidencia {
    private String id;
    private String descripcion;
    private Usuario user;
    private EstatIncidencia estado;



    //BUILDER
    public Incidencia(String id, String descripcion, Usuario user){

        this.id = id;
        this.descripcion = descripcion;
        this.user = user;
        this.estado = EstatIncidencia.ABIERTA;
    }


    //GETTERS

    public String getId(){
        return id;
    }

     public String getDescripcion(){
        return descripcion;
    }
     public Usuario getUser(){
        return user;
    }
     public EstatIncidencia getEstado(){
        return estado;
    }

    

    //CAMBIAR ESTADO 
    public void cambiarEstado(EstatIncidencia nuevoEstado){
        this.estado = nuevoEstado;

    }



    

}