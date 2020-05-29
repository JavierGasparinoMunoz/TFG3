package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorFragmentsAlumno;
import com.example.tfg3.activitys.adaptadores.AdaptadorFragmentsEvs;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class EvaluacionesFragment extends Fragment {

    //Iniacializo las variables del layout
    private ViewPager viewPager;
    private TabLayout tabLayout;

    //Inicializo un adaptador donde adpato los fragments que contendra la lista que se inicializa abajo
    private AdaptadorFragmentsEvs adaptadorFragments;
    private ArrayList<Fragment> listaFragments;

    public EvaluacionesFragment() {
    }



    // Metodo donde se le añade una accion a variables, ya sea al pasar por encima arrastrar escuchar un cambio, etc.
    private void acciones() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.v("scroll",String.valueOf(position));
                Fragment fragment = adaptadorFragments.getItem(position);
                Drawable drawable = fragment.getView().findViewById(R.id.principal).getBackground();
                tabLayout.setBackground(drawable);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.getCurrentItem();
    }

    // Metodo donde se inicia el viewPager, se le añaden los fragments instanciados a la lista,
    // se establece que el adaptador usara esa lista y se hace un set de este adaptador al viewPager
    private void iniciarPager() {
        listaFragments = new ArrayList();
        listaFragments.add(new Ev1Fragment());
        listaFragments.add(new Ev2Fragment());
        listaFragments.add(new Ev3Fragment());
        adaptadorFragments = new AdaptadorFragmentsEvs(getChildFragmentManager(),0,listaFragments);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluaciones,container,false);
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        iniciarPager();

        acciones();



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(adaptadorFragments);
    }
}
