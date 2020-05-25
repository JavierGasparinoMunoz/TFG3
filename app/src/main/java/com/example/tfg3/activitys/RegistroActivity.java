package com.example.tfg3.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailSign, passSign, nameSign, surnameSign,confirmPassSign;
    private Button btnSign,btnLog;
    private FirebaseAuth mAuth;
    private Spinner estadoSign, cicloSign;
    private ArrayList listaEstados, listaCiclos;
    private ArrayAdapter adaptadorEstados;
    private ArrayAdapter adaptadorCiclos;
    FirebaseUser currentUser;
    Usuarios usuario;

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
                R.layout.layout_spinner_perfil, listaEstados);
        estadoSign.setAdapter(adaptadorEstados);
        adaptadorEstados.getItem(estadoSign.getSelectedItemPosition());
    }

    private void rellenarSpinnerCiclos() {
        listaCiclos.add("dam");
        listaCiclos.add("bachillerato");

        adaptadorCiclos = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.layout_spinner_ciclo, listaCiclos);
        cicloSign.setAdapter(adaptadorCiclos);
        adaptadorCiclos.getItem(cicloSign.getSelectedItemPosition());
    }

    private void acciones() {
        btnSign.setOnClickListener(this);
        btnLog.setOnClickListener(this);
    }

    private void instancias() {
        mAuth = FirebaseAuth.getInstance();
        emailSign = findViewById(R.id.edit_email_sig);
        passSign = findViewById(R.id.edit_pass_sig);
        nameSign = findViewById(R.id.edit_name_sig);
        surnameSign = findViewById(R.id.edit_lastnames_sig);
        confirmPassSign = findViewById(R.id.edit_confirm_pass_sig);
        cicloSign = findViewById(R.id.spinner_ciclo_sig);
        estadoSign = findViewById(R.id.spinner_estado_sig);
        listaCiclos = new ArrayList();
        listaEstados = new ArrayList();
        btnSign = findViewById(R.id.button_sign);
        btnLog = findViewById(R.id.button_log_sign);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }

    private void comprobarUsuario(final String uid){
        usuario = new Usuarios(uid,emailSign.getText().toString(),nameSign.getText().toString(),surnameSign.getText().toString(),estadoSign.getSelectedItem().toString(),cicloSign.getSelectedItem().toString());
        DatabaseReference referenciaTipo =  FirebaseDatabase.getInstance().getReference().child("usuarios").child(usuario.getUid());
        referenciaTipo.setValue(usuario);
        //usuario = new Usuarios(uid,)
        //referenciaTipo.child("usuarios").child(usuario.getUid());
    }

    private void vaciarTexto(){
        emailSign.setText("");
        passSign.setText("");
        nameSign.setText("");
        surnameSign.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign:

                if(passSign.getText().toString().equals(confirmPassSign.getText().toString())) {

                    mAuth.createUserWithEmailAndPassword(emailSign.getText().toString(), passSign.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful() && !emailSign.getText().toString().isEmpty() && !passSign.getText().toString().isEmpty()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("login", "createUserWithEmail:success");
                                        //Toast.makeText(getApplicationContext(), "Registro satisfactorio", Toast.LENGTH_SHORT);
                                        String uid = currentUser.getUid();
                                        comprobarUsuario(uid);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                        builder.setTitle("Dialogo de registro");
                                        builder.setMessage("Registro creado correctamente");

                                        vaciarTexto();

                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("login", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(),"Las contaseñas no coinciden",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.button_log_sign:
                finish();
                break;
        }
    }
}
