package com.aprendiz.ragp.horariosctpi.controllers;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
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

public class InformacionTarde extends AppCompatActivity {
    TextView txtPrograma;
    Button btnAtras, btnTarde;
    ImageView imgTarde;
    boolean bandera;
    Button btnCerrar;
    VideoFragment videoFragment;
    ConstraintLayout contenedor;
    ImageView imgBanner;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_tarde);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        videoFragment=new VideoFragment();
        MenuPrincipal.informacionTarde = this;
        bandera = true;
        inizialite();
        inputValues();
        c=0;
        btnTarde.setVisibility(View.INVISIBLE);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c==0){
                    c++;
                    imgBanner.setVisibility(View.INVISIBLE);
                    btnCerrar.setVisibility(View.INVISIBLE);
                    btnTarde.setVisibility(View.VISIBLE);

                }else {
                    cerrarVideo();
                }
            }
        });
    }

    private void inizialite() {
        txtPrograma = findViewById(R.id.txtNombrePorgramaTarde);
        imgBanner = findViewById(R.id.imgBanner);
        btnAtras = findViewById(R.id.btnAtrasTarde);
        btnTarde = findViewById(R.id.btnTarde);
        imgTarde = findViewById(R.id.imgVistaInfotarde);
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
        thread.start();

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnTarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchVideo();
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

        String pdf = MenuPrincipal.programaT.getDescripcion();
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(pdf);


    }

    private void watchVideo(){
        contenedor.setBackgroundColor(getResources().getColor(R.color.trasnsparencia));
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorV,videoFragment).commit();
        btnCerrar.setVisibility(View.VISIBLE);
    }

    public void cerrarVideo() {
        btnCerrar.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().beginTransaction().remove(videoFragment).commit();
        contenedor.setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}