package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.holders.CicloHolder;
import com.example.tfg3.activitys.utils.Bachillerato;
import com.example.tfg3.activitys.utils.Ciclos;
import com.example.tfg3.activitys.utils.Dam;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Ev3Fragment extends Fragment {

    private RecyclerView recyclerViewAsignaturas,recyclerViewNotas;
    AdaptadorFirebase adaptadorFirebase;
    private ArrayList listaAsignaturas;
    Bachillerato bachillerato;
    Dam dam;

    public Ev3Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_ev2, container, false);
        recyclerViewAsignaturas = view.findViewById(R.id.recycler_ciclose_ev3);
        recyclerViewNotas = view.findViewById(R.id.recycler_notas_ev3);
        listaAsignaturas = new ArrayList();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference().child("ciclos");

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    if (dataSnapshot1.getKey().equals("ciclo")) {
                        String tipo = (String) dataSnapshot1.getValue();
                        switch (tipo) {
                            case "bachillerato":
                                Log.v("ejemplo", "bachillerato");

                                String asignat1 = "dibujoTecnico";
                                String asignat2 = "fisica";
                                String asignat3 = "historia";
                                String asignat4 = "informatica";
                                String asignat5 = "ingles";
                                String asignat6 = "lengua";
                                String asignat7 = "matematicas";
                                String asignat8 = "quimica";
                                String asignat9 = "tecnologiaIndustrial";

                                bachillerato = new Bachillerato(asignat1, asignat2, asignat3, asignat4, asignat5, asignat6, asignat7, asignat8, asignat9);

                                listaAsignaturas.add(bachillerato);
                                break;
                            case "dam":
                                Log.v("ejemplo", "dam");

                                String asig1 = "bbdd";
                                String asig2 = "ed";
                                String asig3 = "fol";
                                String asig4 = "ingles";
                                String asig5 = "lgdm";
                                String asig6 = "program";
                                String asig7 = "si";

                                dam = new Dam(asig1, asig2, asig3, asig4, asig5, asig6, asig7);

                                listaAsignaturas.add(dam);
                                break;
                        }
                    } else {
                        Toast.makeText(getContext(), "no entra", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adaptadorFirebase = new AdaptadorFirebase(Ciclos.class, R.layout.item_ciclo_layout
                , CicloHolder.class, referencia, getContext());

        //listaNotas = new ArrayList();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerViewAsignaturas.setAdapter(adaptadorFirebase);
        recyclerViewAsignaturas.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));
        //linearLayoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setHasFixedSize(true);
        //fetch();
    }
}