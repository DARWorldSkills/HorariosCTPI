package com.aprendiz.ragp.horariosctpi.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
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

public class HorarioManana extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha,txtComentarios, txtInstructor1;
    TextView txtInstructor2, txtInstructor3, txtInstructor4, txtInstructor5, txtInstructor6;
    Boolean bandera;
    Button btnAtras;
    ImageView imgManana;
    String abreviaciones="";
    List<Horario> horarioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_manana);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bandera = true;
        inizialite();
        inputValues();
    }

    private void inizialite() {
        txtInformacionPrograma = findViewById(R.id.txtInformacionP);
        txtPrograma = findViewById(R.id.txtNombreTecnologo);
        txtInstructorLider = findViewById(R.id.txtInstructorManana);
        txtFicha = findViewById(R.id.txtFichaManana);
        txtComentarios = findViewById(R.id.txtComentarios);
        txtInstructor1 = findViewById(R.id.txtInstructor1);
        txtInstructor2 = findViewById(R.id.txtInstrictor2);
        txtInstructor3 = findViewById(R.id.txtInstructor3);
        txtInstructor4 = findViewById(R.id.txtIntructor4);
        txtInstructor5 = findViewById(R.id.txtInstructor5);
        txtInstructor6 = findViewById(R.id.txtInstructor6);
        recyclerView = findViewById(R.id.recyclerViewManana);
        btnAtras = findViewById(R.id.btnAtrasManana);
        imgManana = findViewById(R.id.imgManana);

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
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        try {
            horarioList = MenuPrincipal.horarioList.subList(0,3);
            AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList,this,getResources().getColor(R.color.verde));
            recyclerView.setAdapter(adapterHorarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(HorarioManana.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);

            if(horarioList.get(0).getComentarios().length()<1){
                txtComentarios.setText("No hay comentarios");
            }else {
                txtComentarios.setText(horarioList.get(0).getComentarios());
            }
            inputAbreviacion();
            try {
                Glide.with(HorarioManana.this).load(MenuPrincipal.iconosM.getVerde()).crossFade().into(imgManana);

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
        txtInstructor6.setText(tmp[5]);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
