package com.example.tfg3.activitys.adaptadores;


import android.content.Context;

import com.example.tfg3.activitys.holders.CicloHolder;
import com.example.tfg3.activitys.utils.Ciclos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class AdaptadorFirebase extends FirebaseRecyclerAdapter<Ciclos, CicloHolder> {

    Context context;

    public AdaptadorFirebase(Class<Ciclos> modelClass, int modelLayout, Class<CicloHolder> viewHolderClass, DatabaseReference ref, Context c)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context = c;
    }

    @Override
    protected void populateViewHolder(CicloHolder viewHolder, final Ciclos model, int position) {
        viewHolder.getNombre().setText(model.getNombre());
    }
}
