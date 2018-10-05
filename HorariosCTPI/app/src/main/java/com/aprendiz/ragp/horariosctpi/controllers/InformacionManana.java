package com.aprendiz.ragp.horariosctpi.controllers;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
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
        String pdf = "https://drive.google.com/file/d/1Xw14bcieRabwfEkMu1DadEyEfmKB1vzb/view";
        webView.getSettings().setJavaScriptEnabled(true);
        //Carga url de .PDF en WebView  mediante Google Drive Viewer.
        webView.loadUrl(pdf);


    }

    private void watchVideo(){
        Dialog dialog = new Dialog(InformacionManana.this);
        dialog.setContentView(R.layout.item_opaco);
        VideoView webView = dialog.findViewById(R.id.webVideo);
        Uri uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");
        webView.setMediaController(new MediaController(this));
        webView.setVideoURI(uri);
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
