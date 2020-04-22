package com.example.tfg3.activitys.adaptadores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdaptadorFragmentsAlumno extends FragmentPagerAdapter {
    private ArrayList<Fragment> listaFragments;
    private String[] nombres = new String[]{"EV1","EV2","EV3","Calendario","Formulario"};

    public AdaptadorFragmentsAlumno(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> listaFragments) {
        super(fm, behavior);
        this.listaFragments = listaFragments;
    }





    public void agregarFragment(Fragment fragment){
        this.listaFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nombres[position];
    }


}

