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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombreSign, passSign, nameSign, surnameSign;
    private Button btnSign;
    private FirebaseAuth mAuth;
    private Spinner estadoSign, cicloSign;
    private ArrayList listaEstados, listaCiclos;
    private ArrayAdapter adaptadorEstados;
    private ArrayAdapter adaptadorCiclos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        instancias();
        rellenarSpinnerCiclos();
        rellenarSpinnerEstados();
        acciones();
    }

    private void rellenarSpinnerEstados() {
        listaEstados.add("Alumno");
        listaEstados.add("Padre");
        listaEstados.add("Profesor");

        adaptadorEstados = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, listaEstados);
        estadoSign.setAdapter(adaptadorEstados);
        adaptadorEstados.getItem(estadoSign.getSelectedItemPosition());
    }

    private void rellenarSpinnerCiclos() {
        listaCiclos.add("dam");
        listaCiclos.add("bachillerato");

        adaptadorCiclos = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, listaCiclos);
        cicloSign.setAdapter(adaptadorCiclos);
        adaptadorCiclos.getItem(cicloSign.getSelectedItemPosition());
    }

    private void acciones() {
        btnSign.setOnClickListener(this);
    }

    private void instancias() {
        mAuth = FirebaseAuth.getInstance();
        nombreSign = findViewById(R.id.edit_usuario_sig);
        passSign = findViewById(R.id.edit_pass_sig);
        nameSign = findViewById(R.id.edit_name_sig);
        surnameSign = findViewById(R.id.edit_lastnames_sig);
        cicloSign = findViewById(R.id.spinner_ciclo_sig);
        estadoSign = findViewById(R.id.spinner_estado_sig);
        listaCiclos = new ArrayList();
        listaEstados = new ArrayList();
        btnSign = findViewById(R.id.button_sign);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign:

                mAuth.createUserWithEmailAndPassword(nombreSign.getText().toString(), passSign.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful() && !nombreSign.getText().toString().isEmpty() && !passSign.getText().toString().isEmpty()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("login", "createUserWithEmail:success");
                                    Toast.makeText(getApplicationContext(), "Registro satisfactorio", Toast.LENGTH_SHORT);
                                    nombreSign.setText("");
                                    passSign.setText("");
                                    nameSign.setText("");
                                    surnameSign.setText("");

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
