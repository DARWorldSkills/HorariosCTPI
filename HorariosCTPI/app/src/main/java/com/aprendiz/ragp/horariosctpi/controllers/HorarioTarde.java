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

import java.util.ArrayList;
import java.util.List;

public class HorarioTarde extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha, txtComentarios;
    TextView txtInstructor1, txtInstructor2, txtInstructor3, txtInstructor4, txtInstructor5, txtInstructor6;
    Button btnAtras;
    ImageView imgTarde;
    Boolean bandera;
    List<Horario> horarioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_tarde);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bandera = true;
        inizialite();
        inputValues();
    }

    private void inizialite() {
        txtInformacionPrograma = findViewById(R.id.txtInformacionP3);
        txtPrograma = findViewById(R.id.txtNombreProgramaTarde);
        txtInstructorLider = findViewById(R.id.txtInstructorTarde);
        txtFicha = findViewById(R.id.txtFichaTarde);
        txtComentarios = findViewById(R.id.txtComentarios2);
        txtInstructor1 = findViewById(R.id.txtInstrucor1);
        txtInstructor2 = findViewById(R.id.txtInstructor2);
        txtInstructor3 = findViewById(R.id.txtInstructores3);
        txtInstructor4 = findViewById(R.id.txtInstructor4);
        txtInstructor5 = findViewById(R.id.txtInstructor5);
        recyclerView = findViewById(R.id.recyclerViewTarde);
        btnAtras = findViewById(R.id.btnAtrasTarde);
        imgTarde = findViewById(R.id.imgTarde);
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

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        try {
            horarioList = MenuPrincipal.horarioList.subList(3,6);
            AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList,this,getResources().getColor(R.color.naranja));
            recyclerView.setAdapter(adapterHorarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(HorarioTarde.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);
            txtComentarios.setText(horarioList.get(0).getComentarios());
            inputAbreviacion();
            try {
                Glide.with(HorarioTarde.this).load(MenuPrincipal.iconosT.getNaranja()).crossFade().into(imgTarde);

            }catch (Exception e){

            }

        }catch (Exception e) {

        }

    }

    public void inputAbreviacion(){
        String[] tmp =horarioList.get(0).getAbreviaciones().split(";");

        txtInstructor1.setText(tmp[0]);
        txtInstructor2.setText(tmp[1]);
        txtInstructor3.setText(tmp[2]);
        txtInstructor4.setText(tmp[3]);
        txtInstructor5.setText(tmp[4]);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}

