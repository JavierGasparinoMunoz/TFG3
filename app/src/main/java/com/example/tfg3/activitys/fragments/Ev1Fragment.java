package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorAsignaturas;
import com.example.tfg3.activitys.adaptadores.AdaptadorAsignaturasDam;
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.utils.Bachillerato;
import com.example.tfg3.activitys.holders.CicloHolder;
import com.example.tfg3.activitys.utils.Ciclos;
import com.example.tfg3.activitys.utils.Dam;
import com.example.tfg3.activitys.utils.Usuarios;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class Ev1Fragment extends Fragment {

    // Se instancian las variables
    private RecyclerView recyclerView;
    AdaptadorFirebase adaptadorFirebase;
    AdaptadorAsignaturasDam adaptadorAsignaturasDam;
    private ArrayList  listaAsignaturas;
    Bachillerato bachillerato;
    Dam dam;

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
        View view = inflater.inflate(R.layout.fragment_ev1, container, false);
        recyclerView = view.findViewById(R.id.recycler_ciclos);
        listaAsignaturas = new ArrayList();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference referencia = database.getReference().child("ciclos");

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    String tipo = dataSnapshot1.getKey();

                    if(tipo.equals("bachillerato") ){
                        Log.v("ejemplo", "bachillerato");

                        bachillerato = new Bachillerato("dibujoTecnico", "fisica", "historia", "informatica", "ingles", "lengua", "matematicas", "quimica", "tecnologiaIndustrial");
                        Log.v("ejemplo", bachillerato.getAsignat1());

                        listaAsignaturas.add(bachillerato);

                    } else if (tipo.equals("dam")){
                        Log.v("ejemplo", "dam");

                        dam = new Dam();
                        Log.v("ejemplo", dam.getAsig1());


                        listaAsignaturas.add(dam);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adaptadorAsignaturasDam = new AdaptadorAsignaturasDam(getContext(), listaAsignaturas);

        //listaNotas = new ArrayList();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(adaptadorAsignaturasDam);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));
        //linearLayoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setHasFixedSize(true);
        //fetch();
    }
}
