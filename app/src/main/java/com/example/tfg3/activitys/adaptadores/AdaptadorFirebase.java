package com.example.tfg3.activitys.adaptadores;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.tfg3.activitys.dialogos.DialogoNotas;
import com.example.tfg3.activitys.holders.CicloHolder;
import com.example.tfg3.activitys.utils.Ciclos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class AdaptadorFirebase extends FirebaseRecyclerAdapter<Ciclos, CicloHolder> {

    Context context;
    OnAdaptadorListener listener;
    int nota;

    public AdaptadorFirebase(Class<Ciclos> modelClass, int modelLayout, Class<CicloHolder> viewHolderClass, DatabaseReference ref, Context c)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context = c;
        try {
            listener = (OnAdaptadorListener) context;
        } catch (ClassCastException e){
            Log.v("cast","No se puede castear");
        }
    }

    @Override
    protected void populateViewHolder(final CicloHolder viewHolder, final Ciclos model, int position) {
        viewHolder.getNombre().setText(model.getNombre());
       // viewHolder.getNotas().setText(model.getNotas());
        viewHolder.getNotas().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nota = Integer.parseInt(viewHolder.getNotas().getText().toString());
               listener.onAdaptadorSelected();
            }
        });
        viewHolder.getNotas().setText(viewHolder.getNotas().getText().toString());
    }

    public interface OnAdaptadorListener{
        void onAdaptadorSelected();
    }
}
