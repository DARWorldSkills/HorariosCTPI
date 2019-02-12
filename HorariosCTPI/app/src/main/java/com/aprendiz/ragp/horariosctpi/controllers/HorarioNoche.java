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
import android.widget.Toast;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterHorarios;
import com.aprendiz.ragp.horariosctpi.models.AdapterInstructorFicha;
import com.aprendiz.ragp.horariosctpi.models.Constants;
import com.aprendiz.ragp.horariosctpi.models.Horario;
import com.aprendiz.ragp.horariosctpi.models.InstructorHorario;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

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
    List<InstructorHorario> instructorHorarios = new ArrayList<>();
    String[] horarioOrganizado= new String[35];
    String horarioSabado="";

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
        txtInstructor6 = findViewById(R.id.txtInstructor6);
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
            horarioList = MenuPrincipal.horarioList.subList(12,13);

            AdapterHorarios adapterHorarios = new AdapterHorarios(horarioList,this,getResources().getColor(R.color.azul));
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
            recyclerView.setLayoutManager(new LinearLayoutManager(HorarioNoche.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);

            txtProfeSbado.setText(horarioList.get(0).getSabado());
            if(horarioList.get(0).getComentarios().length()<1){
                txtComentarios.setText("No hay comentarios");
            }else {
                txtComentarios.setText(horarioList.get(0).getComentarios());
            }

            inputAbreviacion();
            try {
                Glide.with(HorarioNoche.this).load(MenuPrincipal.iconosM.getVerde()).crossFade().into(imgNoche);

            }catch (Exception e){

            }
        }catch (Exception e){

        }

    }

    private void llamarHorarios(String txtNombre,String abreviacion) {
        Dialog dialog = new Dialog(HorarioNoche.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.item_instructor);
        consultarInstructor(txtNombre,abreviacion);
        AdapterInstructorFicha adapterInstructorFicha = new AdapterInstructorFicha(horarioOrganizado);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapterInstructorFicha);
        recyclerView.setLayoutManager(new GridLayoutManager(HorarioNoche.this,5,GridLayoutManager.VERTICAL,false));
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
