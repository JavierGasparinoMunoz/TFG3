package com.example.tfg3.activitys.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;
import com.example.tfg3.activitys.utils.Ciclos;

import java.util.ArrayList;

public class AdaptadorAsignaturas extends RecyclerView.Adapter<AdaptadorAsignaturas.MiHolder> {

    private Context context;
    private ArrayList<Ciclos> listaCiclos;
    private OnRecyclerListener listener;

    public AdaptadorAsignaturas(Context context, ArrayList<Ciclos> listaCiclos) {
        this.context = context;
        this.listaCiclos = listaCiclos;
        try {
            listener = (OnRecyclerListener) context;
        } catch (ClassCastException e) {

        }
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ciclo_layout, parent, false);
        return new MiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        final Ciclos ciclos = listaCiclos.get(position);
        holder.getAsignaturas().setText(ciclos.getNombre());


    }

    @Override
    public int getItemCount() {
        return listaCiclos.size();
    }

    public interface OnRecyclerListener {
        void onCicloSelected(Ciclos ciclos);
    }

    class MiHolder extends RecyclerView.ViewHolder {

        private TextView asignaturas,notas;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            asignaturas = itemView.findViewById(R.id.nombreHolder);
            notas = itemView.findViewById(R.id.notaHolder);
        }

        public TextView getAsignaturas() {
            return asignaturas;
        }

        public void setAsignaturas(TextView asignaturas) {
            this.asignaturas = asignaturas;
        }

        public TextView getNotas() {
            return notas;
        }

        public void setNotas(TextView notas) {
            this.notas = notas;
        }
    }

}

