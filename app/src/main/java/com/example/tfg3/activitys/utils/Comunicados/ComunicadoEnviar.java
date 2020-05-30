package com.example.tfg3.activitys.utils.Comunicados;

import java.util.Map;

public class ComunicadoEnviar extends Comunicado{
    private Map hora;

    public ComunicadoEnviar() {
    }

    public ComunicadoEnviar(Map hora) {
        this.hora = hora;
    }

    public ComunicadoEnviar(String titulo, String comunicado, String descripcion, Map hora) {
        super(titulo, comunicado, descripcion);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
