package com.aprendiz.ragp.horariosctpi.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.bumptech.glide.Glide;

public class InformacionManana extends AppCompatActivity {
    ImageView imgManana;
    TextView txtPrograma;
    Button btnAtras, btnManana;
    boolean bandera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_manana);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bandera = true;
        iniziliate();
        inputValues();
    }

    private void iniziliate() {
        imgManana = findViewById(R.id.imgVistaInfoManana);
        txtPrograma = findViewById(R.id.txtNombrePrograma);
        btnAtras = findViewById(R.id.btnAtras);
        btnManana = findViewById(R.id.btnManana);
    }

    private void inputValues() {
        inputData();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(3000);
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
    }

    private void inputData() {
        String programa = MenuPrincipal.fichaObjM.getPrograma();
        txtPrograma.setText(programa);
        try {
            Glide.with(this).load(MenuPrincipal.iconosM.getBlanco()).crossFade().into(imgManana);

        }catch (Exception e){

        }

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
