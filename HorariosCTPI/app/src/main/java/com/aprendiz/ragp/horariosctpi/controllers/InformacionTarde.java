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

public class InformacionTarde extends AppCompatActivity {
    TextView txtPrograma;
    Button btnAtras;
    ImageView imgTarde;
    boolean bandera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_tarde);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        bandera = true;
        inizialite();
        inputValues();
    }

    private void inizialite() {
        txtPrograma = findViewById(R.id.txtNombrePorgramaTarde);
        btnAtras = findViewById(R.id.btnAtrasTarde);
        imgTarde = findViewById(R.id.imgVistaInfotarde);

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

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputData() {
        String programa = MenuPrincipal.fichaObjT.getPrograma();
        txtPrograma.setText(programa);
        try {
            Glide.with(this).load(MenuPrincipal.iconosT.getBlanco()).crossFade().into(imgTarde);

        }catch (Exception e){

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
