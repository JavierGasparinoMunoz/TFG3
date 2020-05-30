package com.example.tfg3.activitys.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;
import com.example.tfg3.activitys.holders.ComunicadoHolder;
import com.example.tfg3.activitys.holders.MensajeHolder;
import com.example.tfg3.activitys.utils.Comunicados.ComunicadoRecibir;
import com.example.tfg3.activitys.utils.Mensajes.MensajeRecibir;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdaptadorComunicados extends RecyclerView.Adapter<ComunicadoHolder>{
    private List<ComunicadoRecibir> listaComunicados = new ArrayList();
    private Context c;

    public AdaptadorComunicados(Context c) {
        this.c = c;
    }

    public void addComunicado(ComunicadoRecibir com){
        listaComunicados.add(com);
        notifyItemInserted(listaComunicados.size());
    }

    @NonNull
    @Override
    public ComunicadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.card_view_comunicados,parent,false);
        return new ComunicadoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComunicadoHolder holder, int position) {
        holder.getTitulo().setText(listaComunicados.get(position).getTitulo());
        holder.getComunicado().setText(listaComunicados.get(position).getComunicado());
        holder.getComunicado().setVisibility(View.VISIBLE);

        Long codHora = listaComunicados.get(position).getHora();
        Date d = new Date(codHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        holder.getHora().setText(sdf.format(d));

    }

    @Override
    public int getItemCount() {
        return listaComunicados.size();
    }
}
