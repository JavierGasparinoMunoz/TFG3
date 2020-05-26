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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tfg3.R;
import com.example.tfg3.activitys.utils.Informacion;

import java.util.Calendar;

public class DialogoCalendario extends DialogFragment {

    private EditText editText;
    private Button botonInfo;
    private ImageButton fecha;
    TextView horas;
    private View vista;
    private Informacion informacion;
    private OnDialogoPersoListener listener;
    private int hora,mins;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnDialogoPersoListener) context;
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
                listener.onDilagoloSelected(informacion.toString());
                dismiss();
            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                mins = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horas.setText(hourOfDay + ":" + minute);
                    }
                },hora,mins,false);
                timePickerDialog.show();
            }
        });
    }

    private void instancias() {
        editText =  vista.findViewById(R.id.edit_evento);
        botonInfo = vista.findViewById(R.id.btn_añadir_evento);
        horas = vista.findViewById(R.id.time_event);
        fecha = vista.findViewById(R.id.set_time);
    }

    public interface OnDialogoPersoListener{
        void onDilagoloSelected(String informacion);
    }
}
