package com.aprendiz.ragp.horariosctpi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.Ambiente;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class ControlarHorario extends AppCompatActivity {
    MaterialSpinner spinner;
    List<String> listaApodos= new ArrayList<>();
    List<Ambiente> ambienteList = new ArrayList<>();
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlar_horario);
        spinner = findViewById(R.id.spinnerHorarios);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlarHorario.this,MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
        listApodos();
        inputList();
    }

    private void listApodos() {
        listaApodos = new ArrayList<>();
        ambienteList = MenuPrincipal.ambienteList;
        for (int i=0; i<ambienteList.size(); i++){
            listaApodos.add(ambienteList.get(i).getApodo());
        }


    }

    private void inputList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,listaApodos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                MenuPrincipal.apodoAmbiente= item.toString();
            }
        });
    }
}
