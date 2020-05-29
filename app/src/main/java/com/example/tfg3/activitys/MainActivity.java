package com.example.tfg3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.fragments.EvaluacionesFragment;
import com.example.tfg3.activitys.utils.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Inicializo las variables correspondientes al layout
    private EditText nombreLog, passLog;
    private Button btnLog,registro;

    //Inicializo una clase propia de firebase para la autetificacion mediante ella
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        admin();
        acciones();
    }

    // Metodo donde se le añade una accion a variables, ya sea al pasar por encima arrastrar escuchar un cambio, etc.
    private void acciones() {
        btnLog.setOnClickListener(this);
        registro.setOnClickListener(this);
    }

    private void instancias() {
        mAuth = FirebaseAuth.getInstance();
        nombreLog = findViewById(R.id.edit_usuario_log);
        passLog = findViewById(R.id.edit_pass_log);
        btnLog = findViewById(R.id.button_log);
        registro = findViewById(R.id.button_registro);
    }

    private void admin(){
        if(nombreLog.getText().toString().equals("administrador@gmail.com") && passLog.getText()
                .toString().equals("administrador")){
            Intent intent = new Intent(getApplicationContext(),AdministradorActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    //Metodo para comprobar el perfil del usuario, dependiendo del tipo carga Alumno Activity o UsuariosActivity
    public void comprobarTipo(String uid) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
        DatabaseReference referenciaTipo = FirebaseDatabase.getInstance().getReference().child("usuarios")
                .child(uid);
        final String finalUid = uid;
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
                                Intent intentAl = new Intent(getApplicationContext(), MenuActivity.class);
                                intentAl.putExtra("user", nombreLog.getText().toString());
                                intentAl.putExtra("uid", finalUid);
                                startActivity(intentAl);
                                break;
                            case "Padre":
                                Intent intentPad = new Intent(getApplicationContext(), MenuActivity.class);
                                intentPad.putExtra("user", nombreLog.getText().toString());
                                intentPad.putExtra("uid", finalUid);
                                startActivity(intentPad);
                                break;
                            case "Profesor":
                                Intent intentProf = new Intent(getApplicationContext(), UsuariosActivity.class);
                                intentProf.putExtra("user", nombreLog.getText().toString());
                                intentProf.putExtra("uid", finalUid);
                                startActivity(intentProf);
                                break;
                            default:
                                admin();
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // Si se pulsa el boton de login se mete dentro de este caso y mediante la varaiable mAuth
            // de la clase de autetificacion de firebase se intenta logear al usuario mediante el email y la contraseña introducidos
            case R.id.button_log:

                mAuth.signInWithEmailAndPassword(nombreLog.getText().toString(), passLog.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful() && !nombreLog.getText().toString().isEmpty()
                                        && !passLog.getText().toString().isEmpty()) {
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    String uid = currentUser.getUid();
                                    Log.d("login", "signInWithEmail:success");
                                    Toast.makeText(getApplicationContext(),"Logueo satisfactorio",
                                            Toast.LENGTH_SHORT).show();

                                    // Se llama al metodo creado arriba para que si existe el usuario, se cargue una u otra pantalla dependiendo del perfil
                                    comprobarTipo(uid);


                                //Si se ha introducido mal la contraseña o el email o estan vacios o
                                    // no existe ese usaurio salta un aviso de que la autetificacion ha fallado
                                } else {
                                    Log.w("login", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;

            //Si se pulsa el boton de crear cuenta se pasa a una actividad donde el usuario se registrara
            case R.id.button_registro:
                    Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
                    startActivity(i);
                break;
        }
    }
}
