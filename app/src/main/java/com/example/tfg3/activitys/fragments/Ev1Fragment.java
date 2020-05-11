package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg3.R;
import com.example.tfg3.activitys.MainActivity;
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.adaptadores.AdaptadorRecycler;
import com.example.tfg3.activitys.utils.CicloHolder;
import com.example.tfg3.activitys.utils.Ciclos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Ev1Fragment extends Fragment {

    private RecyclerView recyclerView;
    AdaptadorFirebase adaptadorFirebase;
    private ArrayList listaNotas;

    public Ev1Fragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ev1,container,false);
        recyclerView = view.findViewById(R.id.recycler_ciclos);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("ciclos");
         adaptadorFirebase = new AdaptadorFirebase(Ciclos.class,R.layout.item_ciclo_layout
                , CicloHolder.class,referencia, getContext());

        listaNotas = new ArrayList();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(adaptadorFirebase);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));
    }
}
