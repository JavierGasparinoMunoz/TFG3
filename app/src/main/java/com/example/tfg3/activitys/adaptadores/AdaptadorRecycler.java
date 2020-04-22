package com.example.tfg3.activitys.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;
import com.example.tfg3.activitys.utils.Usuarios;

import java.util.ArrayList;

public class AdaptadorRecycler extends RecyclerView.Adapter {

    ArrayList<Usuarios> listaUsuarios;
    Context context;
    OnUsuarioListener listener;

    public AdaptadorRecycler(Context context,ArrayList<Usuarios> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
        try {
            listener = (OnUsuarioListener) context;
        } catch (ClassCastException e) {

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_usuario_layout,parent,false);
        return new MiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Usuarios usuario = listaUsuarios.get(position);
    }

    public interface OnUsuarioListener{
        void onUsuarioSelected(Usuarios usuario);
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    class MiHolder extends RecyclerView.ViewHolder{

        public MiHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
