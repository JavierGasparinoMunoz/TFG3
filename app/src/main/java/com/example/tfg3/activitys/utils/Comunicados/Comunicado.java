package com.example.tfg3.activitys.utils.Comunicados;

public class Comunicado {
    private String titulo;
    private String comunicado;
    private String descripcion;

    public Comunicado() {
    }

    public Comunicado(String titulo, String comunicado, String descripcion) {
        this.titulo = titulo;
        this.comunicado = comunicado;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComunicado() {
        return comunicado;
    }

    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
