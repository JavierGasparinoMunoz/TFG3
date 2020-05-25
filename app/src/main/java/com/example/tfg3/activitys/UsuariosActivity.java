package com.example.tfg3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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

import java.util.Iterator;

public class UsuariosActivity extends AppCompatActivity implements AdaptadorUsuarios.OnUsuarioListener {

    RecyclerView recyclerView;
    String currentUser;
    AdaptadorUsuarios adaptadorUsuarios;

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
                                 adaptadorUsuarios = new AdaptadorUsuarios(Usuarios.class,R.layout.item_usuario_layout, UsuarioHolder.class,referenciaTipo,UsuariosActivity.this);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        adaptadorUsuarios = new AdaptadorUsuarios(Usuarios.class,R.layout.item_usuario_layout, UsuarioHolder.class,referencia,UsuariosActivity.this);

        recyclerView.setAdapter(adaptadorUsuarios);
        recyclerView.setLayoutManager(new GridLayoutManager(UsuariosActivity.this,1, LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void onUsuarioSelected(Usuarios usuario) {
        Intent intent = new Intent(getApplicationContext(), AlumnoActivity.class);
        intent.putExtra("uid", (String) getIntent().getExtras().get("uid"));
        startActivity(intent);
    }
}
