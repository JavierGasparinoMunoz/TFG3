package com.example.tfg3.activitys.adaptadores;

import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tfg3.R;

import com.example.tfg3.activitys.holders.UsuarioHolder;
import com.example.tfg3.activitys.utils.Usuarios;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.MiHolder> {


    private  Context context;
    private ArrayList<Usuarios> listaUsuarios;
    private OnUsuarioListener listener;

    public AdaptadorUsuarios(Context context,ArrayList<Usuarios> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
        try {
            listener = (OnUsuarioListener) context;
        } catch (ClassCastException e) {

        }
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usuario_layout,parent,false);
        return new MiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
    final Usuarios usuarios = listaUsuarios.get(position);
    holder.getNombre().setText(usuarios.getNombre() + " " + usuarios.getApellido());
    holder.getCiclo().setText(usuarios.getCiclo());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    class MiHolder extends RecyclerView.ViewHolder{

        private TextView nombre,ciclo;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.texto_nombre);
            ciclo = itemView.findViewById(R.id.texto_ciclo);
        }

        public TextView getNombre() {
            return nombre;
        }

        public void setNombre(TextView nombre) {
            this.nombre = nombre;
        }

        public TextView getCiclo() {
            return ciclo;
        }

        public void setCiclo(TextView ciclo) {
            this.ciclo = ciclo;
        }
    }
    /*Context context;
    OnUsuarioListener listener;

    public AdaptadorUsuarios(Context context, Arr) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
        listener = (OnUsuarioListener) context;
    }

    @Override
    protected void populateViewHolder(UsuarioHolder viewHolder, final Usuarios model, int position) {
        viewHolder.getNombre().setText(model.getNombre() + " " + model.getApellido());
        viewHolder.getCiclo().setText(model.getCiclo());
        viewHolder.getNombre().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUsuarioSelected(model);
            }
        });
    }
*/
    public interface OnUsuarioListener{
        void onUsuarioSelected(Usuarios usuario);
    }

}
