package com.example.tfg3.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.adaptadores.AdaptadorFragmentsAlumno;
import com.example.tfg3.activitys.dialogos.DialogoCalendario;
import com.example.tfg3.activitys.dialogos.DialogoNotas;
import com.example.tfg3.activitys.fragments.CalendarioAlumnoFragment;
import com.example.tfg3.activitys.fragments.Ev1Fragment;
import com.example.tfg3.activitys.fragments.Ev2Fragment;
import com.example.tfg3.activitys.fragments.Ev3Fragment;
import com.example.tfg3.activitys.fragments.FormularioAlumnosFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AlumnoActivity extends AppCompatActivity implements DialogoCalendario.OnDialogoPersoListener,
        AdaptadorFirebase.OnAdaptadorListener , DialogoNotas.OnDialogoNotaListener {

    //Iniacializo las variables del layout
    private ViewPager viewPager;
    private TabLayout tabLayout;

    //Inicializo un adaptador donde adpato los fragments que contendra la lista que se inicializa abajo
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
        listaFragments.add(new CalendarioAlumnoFragment());
        listaFragments.add(FormularioAlumnosFragment.newInstance((String) getIntent().getExtras().get("uid")));
        adaptadorFragments = new AdaptadorFragmentsAlumno(getSupportFragmentManager(),0,listaFragments);
        viewPager.setAdapter(adaptadorFragments);
    }

    // Metodo donde se instancian las variables, se les da valor
    private void instancias() {
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Se implementan las interfaces necesarias
    @Override
    public void onDilagoloSelected(String informacion) {
        Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();
    }

    // Se implementan los metodos necesarios de las interfaces implementadas
    @Override
    public void onAdaptadorSelected() {
        DialogoNotas dialogoNotas = new DialogoNotas();
        dialogoNotas.show(getSupportFragmentManager(),"perso");
    }

    @Override
    public void onNotaSelected(int nota) {

    }
}
