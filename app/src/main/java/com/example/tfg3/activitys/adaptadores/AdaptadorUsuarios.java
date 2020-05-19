package com.example.tfg3.activitys.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.tfg3.activitys.AlumnoActivity;
import com.example.tfg3.activitys.ProfesorActivity;
import com.example.tfg3.activitys.utils.CicloHolder;
import com.example.tfg3.activitys.utils.UsuarioHolder;
import com.example.tfg3.activitys.utils.Usuarios;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class AdaptadorUsuarios extends FirebaseRecyclerAdapter<Usuarios, UsuarioHolder> {

    Context context;
    OnUsuarioListener listener;

    public AdaptadorUsuarios(Class<Usuarios> modelClass, int modelLayout, Class<UsuarioHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
        listener = (OnUsuarioListener) context;
    }

    @Override
    protected void populateViewHolder(UsuarioHolder viewHolder, final Usuarios model, int position) {
        viewHolder.getNombre().setText(model.getNombre());
        viewHolder.getApellido().setText(model.getApellido());
        viewHolder.getCiclo().setText(model.getCiclo());
        viewHolder.getNombre().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUsuarioSelected(model);
            }
        });
    }

    public interface OnUsuarioListener{
        void onUsuarioSelected(Usuarios usuario);
    }

}
