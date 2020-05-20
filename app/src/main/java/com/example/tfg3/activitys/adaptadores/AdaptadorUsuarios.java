package com.example.tfg3.activitys.adaptadores;

import android.content.Context;
import android.view.View;

import com.example.tfg3.activitys.holders.UsuarioHolder;
import com.example.tfg3.activitys.utils.Usuarios;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

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
