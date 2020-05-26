package com.example.tfg3.activitys.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.tfg3.R;
import com.example.tfg3.activitys.dialogos.DialogoCalendario;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.Calendar;
import java.util.Date;

public class CalendarioAlumnoFragment extends Fragment  {

    // SE instancias las variables
    CalendarView calendarView;
    Button comprobar;
    String  date,texto;

    public CalendarioAlumnoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Metodo donde se crea la vista del fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendario_alumno,container,false);

        //Se instancian los valores gracias a la vista creada
        calendarView = view.findViewById(R.id.calendarView);
        comprobar = view.findViewById(R.id.button_date);
        return view;
    }

    // Metodo donde una vez se haya creado la vista se establece que la variable date sera el dia,
    // el mes y el año seleccionados y se inicia el dialogo donde se pueden crear eventos
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month + 1) + "/" + year;
                DialogoCalendario dialogoCalendario = new DialogoCalendario();
                dialogoCalendario.show(getFragmentManager(), "perso");
            }
        });
    }
}
