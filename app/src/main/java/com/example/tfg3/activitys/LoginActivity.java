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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombreLog, passLog;
    private Button btnLog,registro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();
    }


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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void comprobarTipo(final String uid){
        DatabaseReference referenciaTipo =  FirebaseDatabase.getInstance().getReference().child("usuarios").child(uid);
        referenciaTipo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String tipo = (String) dataSnapshot.getValue();
                Usuarios usuario = dataSnapshot.getValue(Usuarios.class);
                Toast.makeText(getApplicationContext(),String.valueOf(usuario.getPerfil()),Toast.LENGTH_SHORT).show();
                if (usuario.getPerfil().equals("alumno")){
                    // TODO lo que sea de alumno y cargo su pantalla
                    Intent intent = new Intent(getApplicationContext(),AlumnoActivity.class);
                    intent.putExtra("user",nombreLog.getText().toString());
                    intent.putExtra("uid",uid);
                    startActivity(intent);
                } else if (usuario.getPerfil().equals("padre")){
                    // TODO lo que sea de padre y cargo su pantalla
                    Intent intent = new Intent(getApplicationContext(),PadreActivity.class);
                    intent.putExtra("user",nombreLog.getText().toString());
                    intent.putExtra("uid",uid);
                    startActivity(intent);

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
            case R.id.button_log:

                mAuth.signInWithEmailAndPassword(nombreLog.getText().toString(), passLog.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful() && !nombreLog.getText().toString().isEmpty() && !passLog.getText().toString().isEmpty()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("login", "signInWithEmail:success");
                                    //Intent i = new Intent(LoginActivity.this,PrincipalActivity.class);
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    String uid = currentUser.getUid();
                                    //i.putExtra("user",nombreLog.getText().toString());
                                    //i.putExtra("uid",uid);
                                    //startActivity(i);

                                    comprobarTipo(uid);

                                    Toast.makeText(getApplicationContext(),"Logueo satisfactorio",Toast.LENGTH_SHORT);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("login", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.button_registro:
                    Intent i = new Intent(getApplicationContext(),RegistroActivity.class);
                    startActivity(i);
                break;
        }
    }
}
