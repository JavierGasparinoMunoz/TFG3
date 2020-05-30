package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;
import com.example.tfg3.activitys.MainActivity;
import com.example.tfg3.activitys.adaptadores.AdaptadorComunicados;
import com.example.tfg3.activitys.adaptadores.AdaptadorMensajes;
import com.example.tfg3.activitys.utils.Comunicados.ComunicadoRecibir;
import com.example.tfg3.activitys.utils.Mensajes.MensajeEnviar;
import com.example.tfg3.activitys.utils.Mensajes.MensajeRecibir;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InfoFragmentRecibir extends Fragment {
    private RecyclerView recyclerComunicado;
    private AdaptadorComunicados adaptadorComunicado;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    public InfoFragmentRecibir() {
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
        View view = inflater.inflate(R.layout.fragment_info_recibir,container,false);
        recyclerComunicado = view.findViewById(R.id.id_recycler_comunicados);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("comunicados");

        adaptadorComunicado = new AdaptadorComunicados(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerComunicado.setAdapter(adaptadorComunicado);
        recyclerComunicado.setLayoutManager(linearLayoutManager);


        adaptadorComunicado.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ComunicadoRecibir cr = dataSnapshot.getValue(ComunicadoRecibir.class);
                adaptadorComunicado.addComunicado(cr);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setScrollBar(){
        recyclerComunicado.scrollToPosition(adaptadorComunicado.getItemCount()-1);
    }

}

