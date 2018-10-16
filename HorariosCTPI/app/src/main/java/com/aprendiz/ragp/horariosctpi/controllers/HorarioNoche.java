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
import android.widget.Toast;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterHorarios;
import com.aprendiz.ragp.horariosctpi.models.Horario;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HorarioNoche extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha,txtProfeSbado, txtComentarios;
    TextView txtInstructor1, txtInstructor2, txtInstructor3, txtInstructor4, txtInstructor5, txtInstructor6;
    Boolean bandera;
    ImageView imgNoche;
    Button btnAtras;
    List<Horario> horarioList = new ArrayList<>();
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
        txtProfeSbado = findViewById(R.id.txtProfeSbado);
        txtComentarios = findViewById(R.id.txtComentarios3);
        txtInstructor1 = findViewById(R.id.txtInstructore1);
        txtInstructor2 = findViewById(R.id.txtInstructor2);
        txtInstructor3 = findViewById(R.id.txtInstructor3);
        txtInstructor4 = findViewById(R.id.txtInstructor4);
        txtInstructor5 = findViewById(R.id.txtInstructor5);
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

            horarioList = MenuPrincipal.horarioList.subList(6,7);
            AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList,this,getResources().getColor(R.color.azul));
            recyclerView.setAdapter(adapterHorarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(HorarioNoche.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);
            txtProfeSbado.setText(horarioList.get(0).getSabado());
            txtComentarios.setText(horarioList.get(0).getComentarios());
            inputAbreviacion();
            try {
                Glide.with(HorarioNoche.this).load(MenuPrincipal.iconosN.getAzul()).crossFade().into(imgNoche);

            }catch (Exception e){

            }

        }catch (Exception e){

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
