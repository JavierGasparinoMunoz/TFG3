package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.MainActivity;
import com.example.tfg3.activitys.adaptadores.AdaptadorMensajes;
import com.example.tfg3.activitys.utils.Comunicados.ComunicadoEnviar;
import com.example.tfg3.activitys.utils.Mensajes.MensajeEnviar;
import com.example.tfg3.activitys.utils.Mensajes.MensajeRecibir;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InfoFragmentEnviar extends Fragment {

    // Se inician las variables, esta clase es el fragment correspondiente a enviar comunicados
    private Button btnEnviar;
    private EditText editComunicado, editTitulo, editDescipcion;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public InfoFragmentEnviar() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se instancian las variables iniciadas arriba
        View view = inflater.inflate(R.layout.fragment_info_enviar, container, false);
        btnEnviar = view.findViewById(R.id.btn_añadir_comunicado);
        editComunicado = view.findViewById(R.id.edit_comunicado);
        editTitulo = view.findViewById(R.id.edit_titulo_comunicado);
        editDescipcion = view.findViewById(R.id.edit_descripcion_comunicado);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("comunicados");


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Al pulsar en el boton se crea un comunicado con los datos
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new ComunicadoEnviar(editTitulo.getText().toString()
                        , editComunicado.getText().toString(), editDescipcion.getText().toString(), ServerValue.TIMESTAMP));
                Toast.makeText(getContext(),"Comunicado emitido",Toast.LENGTH_SHORT).show();
                editComunicado.setText("");
                editTitulo.setText("");
                editDescipcion.setText("");
            }
        });

    }
}
