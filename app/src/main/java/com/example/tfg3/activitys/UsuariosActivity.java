package com.example.tfg3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorUsuarios;
import com.example.tfg3.activitys.holders.UsuarioHolder;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class UsuariosActivity extends AppCompatActivity implements AdaptadorUsuarios.OnUsuarioListener {

    // Inicializo las variables necesarias
    RecyclerView recyclerView;
    String currentUser;
    AdaptadorUsuarios adaptadorUsuarios;
    ArrayList listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        recyclerView = findViewById(R.id.recycler_usuarios);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
       // final DatabaseReference referencia = database.getReference("usuarios");


        final Usuarios usuarios = (Usuarios) getIntent().getExtras().get("user");

        final DatabaseReference referencia = database.getReference().child("usuarios");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    if (dataSnapshot1.getKey().equals("perfil")) {
                        String tipo = (String) dataSnapshot1.getValue();
                        switch (tipo) {
                            case "Alumno":

                                String nombre = usuarios.getNombre();
                                String ciclo = usuarios.getCiclo();
                                String apellido = usuarios.getApellido();

                                Usuarios usuariosNew = new Usuarios(nombre,apellido,ciclo);

                                listaUsuarios.add(usuariosNew);

                                break;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"no va",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       // Le doy un valor al adaptador creado arriba, se le pasa la referencia

        adaptadorUsuarios = new AdaptadorUsuarios(getApplicationContext(),listaUsuarios);

        // Se le setea el adaptador al recycler con lo que se rellenara con los datos que recoja
        recyclerView.setAdapter(adaptadorUsuarios);

        // Se establece de que manera se veran los usuarios del recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(UsuariosActivity.this));

    }

    // Se añaden los metodos de las interfaces necesarias
    @Override
    public void onUsuarioSelected(Usuarios usuario) {
        // Se inicia la actividad de alumno (AlumnoActivity) pasandole el uid que recoges mediante de la clase MainActivity
        Intent intent = new Intent(getApplicationContext(), AlumnoActivity.class);
        intent.putExtra("uid", (String) getIntent().getExtras().get("uid"));
        startActivity(intent);
    }
}
