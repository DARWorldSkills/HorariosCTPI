package com.aprendiz.ragp.horariosctpi.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aprendiz.ragp.horariosctpi.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class ControlarHorario extends AppCompatActivity {
    MaterialSpinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlar_horario);
        spinner = findViewById(R.id.spinnerHorarios);
        
    }
}
