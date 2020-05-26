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
import com.example.tfg3.activitys.utils.Informacion;

import java.util.Calendar;

public class DialogoNotas extends DialogFragment {
    private EditText editNota;
    private Button botonAñadir;
    private View vista;
    private OnDialogoNotaListener listener;
    private int nota;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnDialogoNotaListener) context;
        } catch (ClassCastException e){
            Log.v("cast","No se puede castear");
        }


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
                nota = Integer.parseInt(editNota.getText().toString());
                listener.onNotaSelected(nota);
                dismiss();
            }
        });
    }

    private void instancias() {
        editNota =  vista.findViewById(R.id.edit_nota_asignatura);
        botonAñadir = vista.findViewById(R.id.btn_añadir_nota);
    }

    public interface OnDialogoNotaListener{
        void onNotaSelected(int nota);
    }
}
