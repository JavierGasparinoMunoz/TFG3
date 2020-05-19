package com.example.tfg3.activitys.utils;

import com.google.android.gms.common.internal.Objects;

public class Bachillerato {

    String asignat1, asignat2, asignat3, asignat4, asignat5, asignat6, asignat7, asignat8, asignat9;

    public Bachillerato() {
    }

    public Bachillerato(String asignat1, String asignat2, String asignat3, String asignat4, String asignat5, String asignat6, String asignat7, String asignat8, String asignat9) {
        this.asignat1 = asignat1;
        this.asignat2 = asignat2;
        this.asignat3 = asignat3;
        this.asignat4 = asignat4;
        this.asignat5 = asignat5;
        this.asignat6 = asignat6;
        this.asignat7 = asignat7;
        this.asignat8 = asignat8;
        this.asignat9 = asignat9;
    }

    public String getAsignat1() {
        return asignat1;
    }

    public void setAsignat1(String asignat1) {
        this.asignat1 = asignat1;
    }

    public String getAsignat2() {
        return asignat2;
    }

    public void setAsignat2(String asignat2) {
        this.asignat2 = asignat2;
    }

    public String getAsignat3() {
        return asignat3;
    }

    public void setAsignat3(String asignat3) {
        this.asignat3 = asignat3;
    }

    public String getAsignat4() {
        return asignat4;
    }

    public void setAsignat4(String asignat4) {
        this.asignat4 = asignat4;
    }

    public String getAsignat5() {
        return asignat5;
    }

    public void setAsignat5(String asignat5) {
        this.asignat5 = asignat5;
    }

    public String getAsignat6() {
        return asignat6;
    }

    public void setAsignat6(String asignat6) {
        this.asignat6 = asignat6;
    }

    public String getAsignat7() {
        return asignat7;
    }

    public void setAsignat7(String asignat7) {
        this.asignat7 = asignat7;
    }

    public String getAsignat8() {
        return asignat8;
    }

    public void setAsignat8(String asignat8) {
        this.asignat8 = asignat8;
    }

    public String getAsignat9() {
        return asignat9;
    }

    public void setAsignat9(String asignat9) {
        this.asignat9 = asignat9;
    }

    @Override
    public String toString() {
        return "Bachillerato{" +
                "asignat1='" + asignat1 + '\'' +
                ", asignat2='" + asignat2 + '\'' +
                ", asignat3='" + asignat3 + '\'' +
                ", asignat4='" + asignat4 + '\'' +
                ", asignat5='" + asignat5 + '\'' +
                ", asignat6='" + asignat6 + '\'' +
                ", asignat7='" + asignat7 + '\'' +
                ", asignat8='" + asignat8 + '\'' +
                ", asignat9='" + asignat9 + '\'' +
                '}';
    }
}