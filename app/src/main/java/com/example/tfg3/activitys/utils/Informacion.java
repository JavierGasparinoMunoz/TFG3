package com.example.tfg3.activitys.utils;

public class Informacion {

    String info;

    public Informacion(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info;
    }
}
