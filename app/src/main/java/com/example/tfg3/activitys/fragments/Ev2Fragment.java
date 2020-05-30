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
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.holders.CicloHolder;
import com.example.tfg3.activitys.utils.Ciclos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Ev2Fragment extends Fragment {

    // Se instancian las variables
    private RecyclerView recyclerView;
    AdaptadorFirebase adaptadorFirebaseDam,adaptadorFirebaseBachiller;
    String currentUser;

    public Ev2Fragment() {
    }

    public static Ev2Fragment newInstance(String user) {

        Bundle args = new Bundle();
        args.putString("user",user);

        Ev2Fragment fragment = new Ev2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        currentUser =  this.getArguments().getString("user");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ev2, container, false);
        recyclerView = view.findViewById(R.id.recycler_ciclosev2);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference referenciaDam = database.getReference("ciclos").child("dam");
        final DatabaseReference referenciaBachiller = database.getReference("ciclos").child("bachillerato");

        DatabaseReference referenciaTipo = FirebaseDatabase.getInstance().getReference().child("usuarios")
                .child(currentUser);
        referenciaTipo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    if (dataSnapshot1.getKey().equals("ciclo")) {
                        String tipo = (String) dataSnapshot1.getValue();
                        switch (tipo) {
                            case "dam":
                                adaptadorFirebaseDam = new AdaptadorFirebase(Ciclos.class, R.layout.item_ciclo_layout
                                        ,CicloHolder.class, referenciaDam,getContext());
                                recyclerView.setAdapter(adaptadorFirebaseDam);
                                break;
                            case "bachillerato":
                                adaptadorFirebaseBachiller = new AdaptadorFirebase(Ciclos.class,R.layout.item_ciclo_layout
                                        ,CicloHolder.class,referenciaBachiller,getContext());
                                recyclerView.setAdapter(adaptadorFirebaseBachiller);

                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //listaNotas = new ArrayList();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));
        //linearLayoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setHasFixedSize(true);
        //fetch();
    }
}
