package com.example.tfg3.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorFragmentsAlumno;
import com.example.tfg3.activitys.fragments.Ev1Fragment;
import com.example.tfg3.activitys.fragments.Ev2Fragment;
import com.example.tfg3.activitys.fragments.Ev3Fragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AlumnoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AdaptadorFragmentsAlumno adaptadorFragments;
    private ArrayList<Fragment> listaFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        instancias();
        iniciarPager();
        acciones();
    }

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
                //TabLayout.Tab seleccionada = tabLayout.getTabAt(position);
                //seleccionada.view.setBackground(drawable);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.getCurrentItem();
    }

    private void iniciarPager() {
        listaFragments = new ArrayList();
        listaFragments.add(new Ev1Fragment());
        listaFragments.add(new Ev2Fragment());
        listaFragments.add(new Ev3Fragment());
        adaptadorFragments = new AdaptadorFragmentsAlumno(getSupportFragmentManager(),0,listaFragments);
        Ev1Fragment listaFragment = new Ev1Fragment();
        //adaptadorFragments.agregarFragment(listaFragment);
        viewPager.setAdapter(adaptadorFragments);
    }

    private void instancias() {
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Alumno");
        tabLayout.setupWithViewPager(viewPager);
    }
}
