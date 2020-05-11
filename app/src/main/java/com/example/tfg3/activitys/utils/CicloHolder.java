package com.example.tfg3.activitys.utils;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;

public class CicloHolder extends RecyclerView.ViewHolder {

    TextView nombre;

    public CicloHolder(View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.nombreHolder);
    }
}
