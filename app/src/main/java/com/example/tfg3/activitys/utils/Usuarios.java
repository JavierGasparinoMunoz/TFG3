package com.example.tfg3.activitys.utils;

public class Usuarios {
    private String uid,email,nombre,apellido,perfil,ciclo;

    public Usuarios() {
    }

    public Usuarios(String uid) {
        this.uid = uid;
    }

    /*public Usuarios(String ciclo) {
        this.ciclo = ciclo;
    }*/


    public Usuarios(String uid,String email, String nombre, String apellido, String perfil, String ciclo) {
        this.uid = uid;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.perfil = perfil;
        this.ciclo = ciclo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
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
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", perfil='" + perfil + '\'' +
                "uid='" + uid + '\'' +
                '}';
    }
}
