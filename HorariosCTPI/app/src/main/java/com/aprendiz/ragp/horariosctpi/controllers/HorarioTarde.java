package com.aprendiz.ragp.horariosctpi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterHorarios;
import com.aprendiz.ragp.horariosctpi.models.Horario;

import java.util.List;

public class HorarioTarde extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha;
    Boolean bandera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_tarde);
        bandera = true;
        inizialite();
        inputValues();
    }

    private void inizialite() {
        txtInformacionPrograma = findViewById(R.id.txtInformacionP3);
        txtPrograma = findViewById(R.id.txtNombreProgramaTarde);
        txtInstructorLider = findViewById(R.id.txtInstructorTarde);
        txtFicha = findViewById(R.id.txtFichaTarde);
        recyclerView = findViewById(R.id.recyclerViewTarde);
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
                Intent intent = new Intent(HorarioTarde.this,InformacionTarde.class);
                startActivity(intent);
            }
        });


    }

    private void inputData() {
        String instructor = MenuPrincipal.fichaObjT.getInstructor();
        String nombrePrograma = MenuPrincipal.fichaObjT.getPrograma();
        String ficha = MenuPrincipal.fichaObjT.getNumero();
        txtInstructorLider.setText(instructor);
        txtPrograma.setText(nombrePrograma);
        txtFicha.setText(ficha);
        List<Horario> horarioList = MenuPrincipal.horarioList.subList(2,4);
        AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList);
        recyclerView.setAdapter(adapterHorarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(HorarioTarde.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
