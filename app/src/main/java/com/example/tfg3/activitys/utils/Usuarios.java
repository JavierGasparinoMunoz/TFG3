package com.example.tfg3.activitys.utils;

public class Usuarios {
    private String email,nombre,apellido,perfil,ciclo;

    public Usuarios() {
    }

    public Usuarios(String apellido,String ciclo,String email, String nombre, String perfil ) {
        this.apellido = apellido;
        this.ciclo = ciclo;
        this.email = email;
        this.nombre = nombre;
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellido;
    }

    public void setApellidos(String apellido) {
        this.apellido = apellido;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                ", apellido='" + apellido + '\'' +
                ", ciclo='" + ciclo + '\'' +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
