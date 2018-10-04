package com.aprendiz.ragp.horariosctpi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterHorarios;
import com.aprendiz.ragp.horariosctpi.models.Horario;

import java.util.List;

public class HorarioManana extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha;
    Boolean bandera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_manana);
        bandera = true;
        inizialite();
        inputValues();
    }

    private void inizialite() {
        txtInformacionPrograma = findViewById(R.id.txtInformacionP);
        txtPrograma = findViewById(R.id.txtNombreTecnologo);
        txtInstructorLider = findViewById(R.id.txtInstructorManana);
        txtFicha = findViewById(R.id.txtFichaManana);
        recyclerView = findViewById(R.id.recyclerViewManana);
    }

    private void inputValues(){
        inputData();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            inputData();
                        }
                    });
                }
            }
        });
        thread.start();
        txtInformacionPrograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HorarioManana.this,InformacionManana.class);
                startActivity(intent);
            }
        });


    }

    private void inputData() {
        String instructor = MenuPrincipal.fichaObjM.getInstructor();
        String nombrePrograma = MenuPrincipal.fichaObjM.getPrograma();
        String ficha = MenuPrincipal.fichaObjM.getNumero();
        txtInstructorLider.setText(instructor);
        txtPrograma.setText(nombrePrograma);
        txtFicha.setText(ficha);
        List<Horario> horarioList = MenuPrincipal.horarioList.subList(0,2);
        AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList);
        recyclerView.setAdapter(adapterHorarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(HorarioManana.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }
}
