package com.aprendiz.ragp.horariosctpi.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.R;

public class InformacionManana extends AppCompatActivity {
    ImageView imgManana;
    TextView txtPrograma;
    Button btnAtras, btnManana;
    boolean bandera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_manana);
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
    }

    private void inputData() {
    }


}
