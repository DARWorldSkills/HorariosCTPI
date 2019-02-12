package com.aprendiz.ragp.horariosctpi.controllers;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterHorarios;
import com.aprendiz.ragp.horariosctpi.models.AdapterInstructorFicha;
import com.aprendiz.ragp.horariosctpi.models.Constants;
import com.aprendiz.ragp.horariosctpi.models.Horario;
import com.aprendiz.ragp.horariosctpi.models.InstructorHorario;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class HorarioTarde extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txtInstructorLider, txtPrograma,txtInformacionPrograma,txtFicha, txtComentarios;
    Button btnAtras;
    ImageView imgTarde;
    Boolean bandera;
    List<Horario> horarioList = new ArrayList<>();
    List<Horario> tmpHorarioList = new ArrayList<>();
    List<InstructorHorario> instructorHorarios = new ArrayList<>();
    String[] horarioOrganizado= new String[35];
    String horarioSabado="";
    int tmp=0;
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
            horarioList = MenuPrincipal.horarioList.subList(6,12);

            AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList,this,getResources().getColor(R.color.naranja));
            adapterHorarios.setOnItemCLickListener1(new AdapterHorarios.OnItemCLickListener() {
                @Override
                public void itemClick(String txtNombre, int position) {
                    int tmp =0;
                    for (int i=0;i<MenuPrincipal.abreInstructorList.size();i++){
                        String split[] = MenuPrincipal.abreInstructorList.get(i).getNombre().split(" ");
                        String nombre = split[0]+" "+split[1].substring(0,1);
                        if (nombre.equals(txtNombre)){
                            llamarHorarios(txtNombre,MenuPrincipal.abreInstructorList.get(i).getAbreviacion());
                            tmp=1;

                        }

                    }

                    if (MenuPrincipal.abreInstructorList.size()<1 || tmp==0){
                        llamarHorarios(txtNombre,"No disponible");
                    }
                }
            });

            adapterHorarios.setOnItemCLickListener2(new AdapterHorarios.OnItemCLickListener() {
                @Override
                public void itemClick(String txtNombre, int position) {
                    int tmp =0;
                    for (int i=0;i<MenuPrincipal.abreInstructorList.size();i++){
                        String split[] = MenuPrincipal.abreInstructorList.get(i).getNombre().split(" ");
                        String nombre = split[0]+" "+split[1].substring(0,1);
                        if (nombre.equals(txtNombre)){
                            llamarHorarios(txtNombre,MenuPrincipal.abreInstructorList.get(i).getAbreviacion());
                            tmp=1;

                        }

                    }

                    if (MenuPrincipal.abreInstructorList.size()<1 || tmp==0){
                        llamarHorarios(txtNombre,"No disponible");
                    }
                }
            });

            adapterHorarios.setOnItemCLickListener3(new AdapterHorarios.OnItemCLickListener() {
                @Override
                public void itemClick(String txtNombre, int position) {
                    int tmp =0;
                    for (int i=0;i<MenuPrincipal.abreInstructorList.size();i++){
                        String split[] = MenuPrincipal.abreInstructorList.get(i).getNombre().split(" ");
                        String nombre = split[0]+" "+split[1].substring(0,1);
                        if (nombre.equals(txtNombre)){
                            llamarHorarios(txtNombre,MenuPrincipal.abreInstructorList.get(i).getAbreviacion());
                            tmp=1;

                        }

                    }

                    if (MenuPrincipal.abreInstructorList.size()<1 || tmp==0){
                        llamarHorarios(txtNombre,"No disponible");
                    }
                }
            });


            adapterHorarios.setOnItemCLickListener4(new AdapterHorarios.OnItemCLickListener() {
                @Override
                public void itemClick(String txtNombre, int position) {
                    int tmp =0;
                    for (int i=0;i<MenuPrincipal.abreInstructorList.size();i++){
                        String split[] = MenuPrincipal.abreInstructorList.get(i).getNombre().split(" ");
                        String nombre = split[0]+" "+split[1].substring(0,1);
                        if (nombre.equals(txtNombre)){
                            llamarHorarios(txtNombre,MenuPrincipal.abreInstructorList.get(i).getAbreviacion());
                            tmp=1;

                        }

                    }

                    if (MenuPrincipal.abreInstructorList.size()<1 || tmp==0){
                        llamarHorarios(txtNombre,"No disponible");
                    }
                }
            });


            adapterHorarios.setOnItemCLickListener5(new AdapterHorarios.OnItemCLickListener() {
                @Override
                public void itemClick(String txtNombre, int position) {
                    int tmp =0;
                    for (int i=0;i<MenuPrincipal.abreInstructorList.size();i++){
                        String split[] = MenuPrincipal.abreInstructorList.get(i).getNombre().split(" ");
                        String nombre = split[0]+" "+split[1].substring(0,1);
                        if (nombre.equals(txtNombre)){
                            llamarHorarios(txtNombre,MenuPrincipal.abreInstructorList.get(i).getAbreviacion());
                            tmp=1;

                        }

                    }

                    if (MenuPrincipal.abreInstructorList.size()<1 || tmp==0){
                        llamarHorarios(txtNombre,"No disponible");
                    }
                }
            });

            recyclerView.setAdapter(adapterHorarios);
            recyclerView.setLayoutManager(new LinearLayoutManager(HorarioTarde.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);

            if(horarioList.get(0).getComentarios().length()<1){
                txtComentarios.setText("No hay comentarios");
            }else {
                txtComentarios.setText(horarioList.get(0).getComentarios());
            }

            try {
                Glide.with(HorarioTarde.this).load(MenuPrincipal.iconosM.getVerde()).crossFade().into(imgTarde);

            }catch (Exception e){

            }
        }catch (Exception e){

        }

    }

    private void llamarHorarios(String txtNombre,String abreviacion) {
        Dialog dialog = new Dialog(HorarioTarde.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.item_instructor);
        consultarInstructor(txtNombre,abreviacion);
        AdapterInstructorFicha adapterInstructorFicha = new AdapterInstructorFicha(horarioOrganizado);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapterInstructorFicha);
        recyclerView.setLayoutManager(new GridLayoutManager(HorarioTarde.this,5,GridLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                AdapterHorarios.bandera=true;
            }
        });
        dialog.show();


    }

    private void consultarInstructor(String txtNombre,String abreviacion) {
        instructorHorarios = new ArrayList<>();
        List<InstructorHorario> hoInstructorNo = MenuPrincipal.hoInstructorNo;
        for (int i=0; i<hoInstructorNo.size();i++){
            if (hoInstructorNo.get(i).getNombre().equals(txtNombre)){
                instructorHorarios.add(hoInstructorNo.get(i));
            }

            try {
                String [] strings = hoInstructorNo.get(i).getNombre().split("/");
                if (strings[0].equals("F") || strings[1].equals("F")){
                    Log.e("Horario:" + i, "Flor Hernandez"+ " " + hoInstructorNo.get(i).getFicha() + " " + hoInstructorNo.get(i).getDia() + " " + hoInstructorNo.get(i).getHora()+" "+hoInstructorNo.get(i).getAmbiente());
                }

            }catch (Exception ignored){

            }

        }

        horarioOrganizado= new String[35];

        for (int tmp=0;tmp<instructorHorarios.size();tmp++) {
            int contador =0;
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 5; j++) {
                    if (Constants.dia[j].equals(instructorHorarios.get(tmp).getDia()) && Constants.hora[i].equals(instructorHorarios.get(tmp).getHora())){
                        horarioOrganizado[contador]=instructorHorarios.get(tmp).getFicha()+"\n"+instructorHorarios.get(tmp).getAmbiente();
                    }
                    contador++;
                }

            }

            if (instructorHorarios.get(tmp).getDia().equals("Sábado")){
                horarioSabado = instructorHorarios.get(tmp).getFicha();
            }
        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }

}
