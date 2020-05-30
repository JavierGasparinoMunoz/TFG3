package com.example.tfg3.activitys.utils.Comunicados;

public class ComunicadoRecibir extends Comunicado{
    private Long hora;

    public ComunicadoRecibir() {
    }

    public ComunicadoRecibir(Long hora) {
        this.hora = hora;
    }

    public ComunicadoRecibir(String titulo, String comunicado, String descripcion, Long hora) {
        super(titulo, comunicado, descripcion);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
