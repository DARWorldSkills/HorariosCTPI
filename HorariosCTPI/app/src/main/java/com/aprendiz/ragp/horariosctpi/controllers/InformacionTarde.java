package com.aprendiz.ragp.horariosctpi.controllers;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
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
import com.bumptech.glide.Glide;

import java.io.File;

public class InformacionTarde extends AppCompatActivity {
    TextView txtPrograma;
    Button btnAtras, btnTarde;
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
        btnTarde = findViewById(R.id.btnTarde);
        imgTarde = findViewById(R.id.imgVistaInfotarde);

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
        File exportDir = new File(Environment.getExternalStorageDirectory(),"Videos");
        Dialog dialog = new Dialog(InformacionTarde.this);
        dialog.setContentView(R.layout.item_opaco);
        VideoView webView = dialog.findViewById(R.id.webVideo);
        Uri uri = Uri.fromFile(new File(exportDir,"capsula1.mp4"));
        webView.setMediaController(new MediaController(this));
        webView.setVideoURI(uri);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        webView.requestFocus();
        dialog.setCancelable(true);
        webView.start();
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }
}