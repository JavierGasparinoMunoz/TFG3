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
import com.example.tfg3.activitys.utils.Dam;

import java.util.ArrayList;

public class AdaptadorAsignaturasDam extends RecyclerView.Adapter<AdaptadorAsignaturasDam.MiHolder> {

    private Context context;
    private ArrayList<Dam> listaDam;
    private OnRecyclerListener listener;

    public AdaptadorAsignaturasDam(Context context, ArrayList<Dam> listaDam) {
        this.context = context;
        this.listaDam = listaDam;
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
        final Dam dam = listaDam.get(position);
        holder.getAsignaturas().setText(dam.toString());


    }

    @Override
    public int getItemCount() {
        return listaDam.size();
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

