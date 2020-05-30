package com.example.tfg3.activitys.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;

public class ComunicadoHolder extends RecyclerView.ViewHolder {

    private TextView titulo,comunicado,hora;
    private ImageView fotoComunicado;

    public ComunicadoHolder(@NonNull View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.titulo_comunicado);
        comunicado = itemView.findViewById(R.id.comunicado);
        hora = itemView.findViewById(R.id.hora_comunicado);
        fotoComunicado = itemView.findViewById(R.id.foto_perfil_comunicado);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getComunicado() {
        return comunicado;
    }

    public void setComunicado(TextView comunicado) {
        this.comunicado = comunicado;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public ImageView getFotoComunicado() {
        return fotoComunicado;
    }

    public void setFotoComunicado(ImageView fotoComunicado) {
        this.fotoComunicado = fotoComunicado;
    }
}
