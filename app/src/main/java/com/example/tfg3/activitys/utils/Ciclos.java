package com.example.tfg3.activitys.utils;

public class Ciclos {
    String nombre,notas;

    public Ciclos() {
    }

    public Ciclos(String nombre, String notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Ciclos{" +
                "nombre='" + nombre + '\'' +
                ", notas='" + notas + '\'' +
                '}';
    }
}
