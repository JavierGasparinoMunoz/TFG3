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
import com.example.tfg3.activitys.adaptadores.AdaptadorRecycler;

import java.util.ArrayList;


public class Ev1Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList listaNotas;
    private AdaptadorRecycler adaptadorRecycler;

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
        recyclerView = view.findViewById(R.id.recycler_notas_ev1);
        listaNotas = new ArrayList();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(adaptadorRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2,
                RecyclerView.VERTICAL, false));
    }
}
