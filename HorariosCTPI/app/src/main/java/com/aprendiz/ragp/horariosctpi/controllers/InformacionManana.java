package com.aprendiz.ragp.horariosctpi.controllers;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.fragments.VideoFragment;
import com.bumptech.glide.Glide;

import java.io.File;

public class InformacionManana extends AppCompatActivity {
    ImageView imgManana;
    TextView txtPrograma;
    Button btnAtras, btnManana;
    boolean bandera;
    Button btnCerrar;
    VideoFragment videoFragment;
    ConstraintLayout contenedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_manana);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        videoFragment=new VideoFragment();
        MenuPrincipal.informacionManana=this;
        bandera = true;
        iniziliate();
        inputValues();
        btnCerrar.setVisibility(View.INVISIBLE);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarVideo();
            }
        });
    }

    private void iniziliate() {
        imgManana = findViewById(R.id.imgVistaInfoManana);
        txtPrograma = findViewById(R.id.txtNombrePrograma);
        btnAtras = findViewById(R.id.btnAtras);
        btnManana = findViewById(R.id.btnManana);
        btnCerrar = findViewById(R.id.btnCerrar);
        contenedor = findViewById(R.id.contenedorV);
    }

    private void inputValues() {
        inputData();
        insertarPDF();
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
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnManana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchVideo();
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


    }

    private void insertarPDF(){

        WebView webView = findViewById(R.id.webViewPDF);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

        //Url Ejemplo:
        String pdf = MenuPrincipal.programaM.getDescripcion();
        webView.getSettings().setJavaScriptEnabled(true);
        //Carga url de .PDF en WebView  mediante Google Drive Viewer.
        webView.loadUrl(pdf);


    }

    public void cerrarVideo() {
        btnCerrar.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().beginTransaction().remove(videoFragment).commit();
        contenedor.setBackgroundColor(Color.TRANSPARENT);
    }

    private void watchVideo(){
        contenedor.setBackgroundColor(getResources().getColor(R.color.trasnsparencia));
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorV,videoFragment).commit();
        btnCerrar.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}
