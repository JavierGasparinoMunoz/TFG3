package com.example.tfg3.activitys.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.LoginActivity;
import com.example.tfg3.activitys.adaptadores.AdaptadorMensajes;
import com.example.tfg3.activitys.utils.Mensaje;
import com.example.tfg3.activitys.utils.MensajeEnviar;
import com.example.tfg3.activitys.utils.MensajeRecibir;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.Executor;

import static android.app.Activity.RESULT_OK;

public class FormularioAlumnosFragment extends Fragment {

    private TextView textNombre;
    private Button btnEnviar,cerrarSesion;
    private EditText editMensaje;
    private RecyclerView recyclerMensaje;
    private ImageButton btnEnviarFoto;

    private AdaptadorMensajes adaptadorMensajes;
    private LinearLayoutManager linearLayoutManager;

    private FirebaseDatabase database;
    FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    //private static final int PHOTO_SEND = 1;

    private String nombreUsuarioLog;

    public FormularioAlumnosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario_alumnos,container,false);
        textNombre = view.findViewById(R.id.id_nombre);
        btnEnviar = view.findViewById(R.id.btn_enviar);
        editMensaje = view.findViewById(R.id.txt_mensajes);
        recyclerMensaje = view.findViewById(R.id.rv_mensajes);
        cerrarSesion = view.findViewById(R.id.btn_cerrar_sesion);
        //btnEnviarFoto = view.findViewById(R.id.enviar_foto);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");
        storage = FirebaseStorage.getInstance();

        adaptadorMensajes = new AdaptadorMensajes(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerMensaje.setLayoutManager(linearLayoutManager);
        recyclerMensaje.setAdapter(adaptadorMensajes);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new MensajeEnviar(nombreUsuarioLog,editMensaje.getText().toString(),"1", ServerValue.TIMESTAMP));
                editMensaje.setText("");
            }
        });

        /*btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_SEND);
            }
        });*/

        adaptadorMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                returnLogin();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                    adaptadorMensajes.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setScrollBar(){
        recyclerMensaje.scrollToPosition(adaptadorMensajes.getItemCount()-1);
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes_chat");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener((Executor) this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri u = taskSnapshot.getUploadSessionUri();
                        Mensaje m = new Mensaje("Kevin te ha enviado una foto",u.toString(),textNombre.getText().toString(),"","2","00:00");
                        databaseReference.push().setValue(m);
                }
            });
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            btnEnviar.setEnabled(false);
            DatabaseReference referenciaTipo =  FirebaseDatabase.getInstance().getReference().child("usuarios").child(currentUser.getUid());
            referenciaTipo.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);
                    nombreUsuarioLog = usuarios.getNombre();
                    textNombre.setText(nombreUsuarioLog);
                    btnEnviar.setEnabled(true);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
           returnLogin();
        }
    }

    private void returnLogin(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
