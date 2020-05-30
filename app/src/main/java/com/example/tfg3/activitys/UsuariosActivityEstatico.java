package com.example.tfg3.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg3.R;
import com.example.tfg3.activitys.adaptadores.AdaptadorUsuariosEstaico;
import com.example.tfg3.activitys.holders.UsuarioHolder;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuariosActivityEstatico extends AppCompatActivity implements AdaptadorUsuariosEstaico.OnUsuarioListener {

    // Inicializo las variables necesarias
    RecyclerView recyclerView;
    String currentUser;
    AdaptadorUsuariosEstaico adaptadorUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        recyclerView = findViewById(R.id.recycler_usuarios);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference referencia = database.getReference("usuarios");


       /* currentUser = (String) getIntent().getExtras().get("uid");
        final DatabaseReference referenciaTipo = FirebaseDatabase.getInstance().getReference().child("usuarios").child(currentUser);
        referenciaTipo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot dataSnapshot1 = iterator.next();
                    if (dataSnapshot1.getKey().equals("perfil")) {
                        String tipo = (String) dataSnapshot1.getValue();
                        switch (tipo) {
                            case "Alumno":
                                 adaptadorUsuarios = new AdaptadorUsuarios(Usuarios.class,R.layout.item_usuario_layout, UsuarioHolder.class,referencia,UsuariosActivity.this);
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
        });*/

        // Le doy un valor al adaptador creado arriba, se le pasa la referencia

        adaptadorUsuarios = new AdaptadorUsuariosEstaico(Usuarios.class,R.layout.item_usuario_layout,
                UsuarioHolder.class,referencia,UsuariosActivityEstatico.this);

        // Se le setea el adaptador al recycler con lo que se rellenara con los datos que recoja
        recyclerView.setAdapter(adaptadorUsuarios);

        // Se establece de que manera se veran los usuarios del recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(UsuariosActivityEstatico.this));

    }

    // Se añaden los metodos de las interfaces necesarias
    @Override
    public void onUsuarioSelected(Usuarios usuario) {
        // Se inicia la actividad de alumno (AlumnoActivity) pasandole el uid que recoges mediante de la clase MainActivity
        Intent intent = new Intent(getApplicationContext(), MenuActivityProfesor.class);
        intent.putExtra("uid", (String) getIntent().getExtras().get("uid"));
        startActivity(intent);
    }
}
