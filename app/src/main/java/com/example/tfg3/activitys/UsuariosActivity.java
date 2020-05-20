package com.example.tfg3.activitys;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsuariosActivity extends AppCompatActivity implements AdaptadorUsuarios.OnUsuarioListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        recyclerView = findViewById(R.id.recycler_usuarios);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("usuarios");

        AdaptadorUsuarios adaptadorUsuarios = new AdaptadorUsuarios(Usuarios.class,R.layout.item_usuario_layout, UsuarioHolder.class,referencia,UsuariosActivity.this);

        recyclerView.setAdapter(adaptadorUsuarios);
        recyclerView.setLayoutManager(new GridLayoutManager(UsuariosActivity.this,1, LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void onUsuarioSelected(Usuarios usuario) {
        Intent intent = new Intent(getApplicationContext(), ProfesorActivity.class);
        startActivity(intent);
    }
}
