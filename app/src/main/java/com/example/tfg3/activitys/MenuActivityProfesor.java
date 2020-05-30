package com.example.tfg3.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorFirebase;
import com.example.tfg3.activitys.dialogos.DialogoCalendario;
import com.example.tfg3.activitys.dialogos.DialogoNotas;
import com.example.tfg3.activitys.fragments.CalendarioAlumnoFragment;
import com.example.tfg3.activitys.fragments.EvaluacionesFragment;
import com.example.tfg3.activitys.fragments.FormularioAlumnosFragment;
import com.example.tfg3.activitys.fragments.InfoFragmentEnviar;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivityProfesor extends AppCompatActivity implements AdaptadorFirebase.OnAdaptadorListener, DialogoCalendario.OnDialogoPersoListener, NavigationView.OnNavigationItemSelectedListener {

    String currentUser, currentEmail;
    TextView nombreEdit, emailEdit;
    TextView nombre;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesor);

        toolbar = findViewById(R.id.toolbar);
        nombre = findViewById(R.id.id_nombre_apellido_perfil);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_profesor);
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);
        nombreEdit = headView.findViewById(R.id.id_nombre_apellido_perfil);
        emailEdit = headView.findViewById(R.id.email_Perfil);

        cambiarNombre();
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
        getMenuInflater().inflate(R.menu.menu_profesor, menu);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_evaluaciones) {
            cargarFragment(new EvaluacionesFragment());
        } else if (id == R.id.nav_info){
            cargarFragment(new InfoFragmentEnviar());
        } else if (id == R.id.nav_calendar){
            cargarFragment(new CalendarioAlumnoFragment());
        } else if (id == R.id.nav_chat){
            cargarFragment(FormularioAlumnosFragment.newInstance((String) getIntent().getExtras().get("uid")));
        } else if (id == R.id.nav_cerrarSesion) {
            FirebaseAuth.getInstance().signOut();
            returnLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_evaluaciones) {
            cargarFragment(EvaluacionesFragment.newInstance((String) getIntent().getExtras().get("uid")));
        } else if (id == R.id.nav_info){
            cargarFragment(new InfoFragmentEnviar());
        } else if (id == R.id.nav_calendar) {
            cargarFragment(new CalendarioAlumnoFragment());
        } else if (id == R.id.nav_chat) {
            cargarFragment(FormularioAlumnosFragment.newInstance((String) getIntent().getExtras().get("uid")));
        } else if (id == R.id.nav_cerrarSesion) {
            FirebaseAuth.getInstance().signOut();
            returnLogin();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void cargarFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor_fragments, fragment).commit();
    }

    // Cuando se llama a este metodo se regresa a MainActivity
    private void returnLogin() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void cambiarNombre() {
        final String uid = getIntent().getExtras().getString("uid");
        DatabaseReference referenciaTipo = FirebaseDatabase.getInstance().getReference().child("usuarios").child(uid);
        referenciaTipo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);
                currentUser = usuarios.getNombre();
                currentEmail = usuarios.getEmail();

                nombreEdit.setText(currentUser);
                emailEdit.setText(currentEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
