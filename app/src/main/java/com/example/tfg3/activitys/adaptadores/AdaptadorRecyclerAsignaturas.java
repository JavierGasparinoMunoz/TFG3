package com.example.tfg3.activitys.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;
import com.example.tfg3.activitys.utils.Asignatura;
import com.example.tfg3.activitys.utils.Usuarios;

import java.util.ArrayList;

public class AdaptadorRecyclerAsignaturas extends RecyclerView.Adapter {
    ArrayList<Asignatura> listaAsignaturas;
    Context context;
    OnAsignaturaListener listener;

    public AdaptadorRecyclerAsignaturas(Context context,ArrayList listaAsignaturas) {
        this.context = context;
        this.listaAsignaturas = listaAsignaturas;
        try {
            listener = (OnAsignaturaListener) context;
        } catch (ClassCastException e) {

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ciclo_layout,parent,false);
        return new MiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Asignatura asignatura = listaAsignaturas.get(position);
    }

    public interface OnAsignaturaListener{
        void onAsignaturaSelected(Usuarios usuario);
    }

    @Override
    public int getItemCount() {
        return listaAsignaturas.size();
    }

    class MiHolder extends RecyclerView.ViewHolder{

        public MiHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

