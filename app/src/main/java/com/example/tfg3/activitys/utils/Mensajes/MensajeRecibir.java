package com.example.tfg3.activitys.utils.Mensajes;

import com.example.tfg3.activitys.utils.Mensajes.Mensaje;

public class MensajeRecibir extends Mensaje {
    private Long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String nombre, String mensaje, String type_mensaje, Long hora) {
        super(nombre, mensaje, type_mensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
