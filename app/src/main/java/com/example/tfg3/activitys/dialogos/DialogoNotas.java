package com.example.tfg3.activitys.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tfg3.R;
import com.example.tfg3.activitys.fragments.FormularioAlumnosFragment;
import com.example.tfg3.activitys.utils.Informacion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DialogoNotas extends DialogFragment {
    private EditText editNota;
    private Button botonAñadir;
    private View vista;
    //private OnDialogoNotaListener listener;
    String currentUser;
    DatabaseReference databaseReference;
    private int nota;

    public static DialogoNotas newInstance(String user) {

        Bundle args = new Bundle();
        args.putString("user",user);

        DialogoNotas fragment = new DialogoNotas();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*try {
            listener = (OnDialogoNotaListener) context;
        } catch (ClassCastException e){
            Log.v("cast","No se puede castear");
        }*/

        currentUser =  this.getArguments().getString("user");


        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_notas,null);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        instancias();
        acciones();
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
        dialogo.setView(vista);
        return dialogo.create();
    }

    private void acciones() {
        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nota = Integer.parseInt(editNota.getText().toString());
                //listener.onNotaSelected(nota);
                actDatos();
                dismiss();
            }
        });
    }

    private void actDatos() {
        final DatabaseReference actualizar = databaseReference.getDatabase().getReference().child("ciclos").child(currentUser);


        actualizar.child("nota").setValue(editNota.getText().toString());
    }

    private void instancias() {
        editNota =  vista.findViewById(R.id.edit_nota_asignatura);
        botonAñadir = vista.findViewById(R.id.btn_añadir_nota);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

   /* public interface OnDialogoNotaListener{
        void onNotaSelected(int nota);
    }*/
}
