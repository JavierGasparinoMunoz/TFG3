package com.example.tfg3.activitys.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tfg3.R;
import com.example.tfg3.activitys.utils.Informacion;

public class DialogoCalendario extends DialogFragment {

    EditText editText;
    Button botonInfo;
    View vista;
    Context c;
    Informacion informacion;
    OnDialogoPersoListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.c = context;
            listener = (OnDialogoPersoListener) c;
        } catch (ClassCastException e){
            Log.v("cast","No se puede castear");
        }


        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_calendario,null);

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
        botonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 informacion = new Informacion(editText.getText().toString());
                 String infoString = informacion + editText.getText().toString();
                listener.onDilagoloSelected(infoString);
                dismiss();
            }
        });
    }

    private void instancias() {
        editText = vista.findViewById(R.id.edit_calendar);
        botonInfo =vista.findViewById(R.id.btn_info);
    }

    public interface OnDialogoPersoListener{
        void onDilagoloSelected(String informacion);
    }
}
