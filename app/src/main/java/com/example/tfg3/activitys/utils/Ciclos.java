package com.example.tfg3.activitys.utils;

public class Ciclos {
    String nombre;

    public Ciclos() {
    }

    public Ciclos(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ciclos{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
