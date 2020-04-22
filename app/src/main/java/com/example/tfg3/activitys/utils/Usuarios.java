package com.example.tfg3.activitys.utils;

public class Usuarios {
    String email,nombre,apellidos,perfil,ciclo;

    public Usuarios() {
    }

    public Usuarios(String email, String nombre, String apellidos, String perfil, String ciclo) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.perfil = perfil;
        this.ciclo = ciclo;
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
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", perfil='" + perfil + '\'' +
                ", ciclo='" + ciclo + '\'' +
                '}';
    }
}
