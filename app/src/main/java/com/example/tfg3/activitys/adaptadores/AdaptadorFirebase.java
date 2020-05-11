package com.example.tfg3.activitys.adaptadores;


import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.tfg3.activitys.utils.CicloHolder;
import com.example.tfg3.activitys.utils.Ciclos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class AdaptadorFirebase extends FirebaseRecyclerAdapter<Ciclos, CicloHolder> {

    Context context;

    public AdaptadorFirebase(Class<Ciclos> modelClass, int modelLayout, Class<CicloHolder> viewHolderClass, DatabaseReference ref, Context c)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context = c;
    }

    @Override
    protected void populateViewHolder(CicloHolder viewHolder, final Ciclos model, int position) {
        //viewHolder.nombre.setText(model.getNombre());
    }
}
