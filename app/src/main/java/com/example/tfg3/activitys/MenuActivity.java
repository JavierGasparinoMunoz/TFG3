package com.example.tfg3.activitys;

import android.os.Bundle;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.dialogos.DialogoCalendario;
import com.example.tfg3.activitys.dialogos.DialogoNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tfg3.activitys.fragments.CalendarioAlumnoFragment;
import com.example.tfg3.activitys.fragments.EvaluacionesFragment;
import com.example.tfg3.activitys.fragments.FormularioAlumnosFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements AdaptadorFirebase.OnAdaptadorListener, DialogoNotas.OnDialogoNotaListener, DialogoCalendario.OnDialogoPersoListener, NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    String currentUser;
    TextView nombre;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = findViewById(R.id.toolbar);
        nombre = findViewById(R.id.id_nombre_apellido_perfil);
        toolbar.setTitle("Evaluaciones");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        currentUser = (String) getIntent().getExtras().get("user");
        //nombre.setText(currentUser);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_evs, R.id.nav_calendar, R.id.nav_chat)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_evaluaciones2_drawer, menu);
        return true;

    }

    @Override
    public void onAdaptadorSelected() {
        DialogoNotas dialogoNotas = new DialogoNotas();
        dialogoNotas.show(getSupportFragmentManager(),"perso");
    }

    @Override
    public void onDilagoloSelected(String informacion) {
        Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNotaSelected(int nota) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_evaluaciones) {
            cargarFragment(new EvaluacionesFragment());;
        } else if (id == R.id.nav_calendar){
            cargarFragment(new CalendarioAlumnoFragment());
        } else if (id == R.id.nav_chat){
            cargarFragment(FormularioAlumnosFragment.newInstance((String) getIntent().getExtras().get("uid")));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_evaluaciones) {
            cargarFragment(new EvaluacionesFragment());
        } else if (id == R.id.nav_calendar) {
            cargarFragment(new CalendarioAlumnoFragment());
        } else if (id == R.id.nav_chat) {
            cargarFragment(FormularioAlumnosFragment.newInstance((String) getIntent().getExtras().get("uid")));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void cargarFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor_fragments, fragment).commit();
    }


}
