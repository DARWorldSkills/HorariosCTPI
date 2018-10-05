package com.aprendiz.ragp.horariosctpi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterHorarios;
import com.aprendiz.ragp.horariosctpi.models.Horario;
import com.bumptech.glide.Glide;

import java.util.List;

public class HorarioNoche extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha;
    Boolean bandera;
    ImageView imgNoche;
    Button btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_noche);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bandera = true;
        inizialite();
        inputValues();
    }

    private void inizialite() {
        txtInformacionPrograma = findViewById(R.id.txtInformacionP2);
        txtPrograma = findViewById(R.id.txtProgramaNoche);
        txtInstructorLider = findViewById(R.id.txtnstrucotNoche);
        txtFicha = findViewById(R.id.txtFichaNoche);
        recyclerView = findViewById(R.id.recyclerViewNoche);
        imgNoche = findViewById(R.id.imgNoche);
        btnAtras = findViewById(R.id.btnAtrasNoche);
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
                Intent intent = new Intent(HorarioNoche.this,InformacionNoche.class);
                startActivity(intent);
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void inputData() {
        String instructor = MenuPrincipal.fichaObjN.getInstructor();
        String nombrePrograma = MenuPrincipal.fichaObjN.getPrograma();
        String ficha = MenuPrincipal.fichaObjN.getNumero();
        txtInstructorLider.setText(instructor);
        txtPrograma.setText(nombrePrograma);
        txtFicha.setText(ficha);
        try {

            List<Horario> horarioList = MenuPrincipal.horarioList.subList(6,7);
            AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList,this,getResources().getColor(R.color.azul));
            recyclerView.setAdapter(adapterHorarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(HorarioNoche.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);
            try {
                Glide.with(HorarioNoche.this).load(MenuPrincipal.iconosT.getAzul()).crossFade().into(imgNoche);

            }catch (Exception e){

            }

        }catch (Exception e){

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
