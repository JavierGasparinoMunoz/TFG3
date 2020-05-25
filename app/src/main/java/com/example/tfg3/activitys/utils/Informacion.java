package com.example.tfg3.activitys.utils;

import java.io.Serializable;

public class Informacion implements Serializable {

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
