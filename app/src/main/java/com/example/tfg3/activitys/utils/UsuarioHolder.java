package com.example.tfg3.activitys.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;

public class UsuarioHolder extends RecyclerView.ViewHolder {

    TextView nombre,apellido,ciclo;

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getApellido() {
        return apellido;
    }

    public void setApellido(TextView apellido) {
        this.apellido = apellido;
    }

    public TextView getCiclo() {
        return ciclo;
    }

    public void setCiclo(TextView ciclo) {
        this.ciclo = ciclo;
    }

    public UsuarioHolder(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.texto_nombre);
        apellido = itemView.findViewById(R.id.texto_apellido);
        ciclo = itemView.findViewById(R.id.texto_ciclo);


    }


}
