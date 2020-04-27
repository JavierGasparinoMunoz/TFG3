package com.example.tfg3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tfg3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText nombreLog, passLog, nombreSign, passSign;
    private Button btnLog, btnSign;
    private FirebaseAuth mAuth;
    Spinner estadoSign;
    private ArrayList listaEstados;
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        rellenarSpinner();
        acciones();
    }

    private void acciones() {
        btnLog.setOnClickListener(this);
        btnSign.setOnClickListener(this);
    }

    private void instancias() {
        mAuth = FirebaseAuth.getInstance();
        nombreLog = findViewById(R.id.edit_usuario_log);
        nombreSign = findViewById(R.id.edit_email_sig);
        passLog = findViewById(R.id.edit_pass_log);
        passSign = findViewById(R.id.edit_pass_sig);
        btnLog = findViewById(R.id.button_log);
        btnSign = findViewById(R.id.button_sign);
        estadoSign = findViewById(R.id.spinner_estado_sig);
        listaEstados = new ArrayList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void rellenarSpinner() {
        listaEstados.add("Alumno");
        listaEstados.add("Padre");
        listaEstados.add("Profesor");
        listaEstados.add("Administrador");

        adaptador = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, listaEstados);
        estadoSign.setAdapter(adaptador);
        adaptador.getItem(estadoSign.getSelectedItemPosition());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_log:
                mAuth.signInWithEmailAndPassword(nombreLog.getText().toString(), passLog.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("login", "signInWithEmail:success");
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("login", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.button_sign:

                mAuth.createUserWithEmailAndPassword(nombreSign.getText().toString(), passSign.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("login", "createUserWithEmail:success");
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("login", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
        }
    }
}