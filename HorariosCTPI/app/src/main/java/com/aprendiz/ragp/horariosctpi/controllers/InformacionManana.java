package com.aprendiz.ragp.horariosctpi.controllers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.fragments.VideoFragment;
import com.aprendiz.ragp.horariosctpi.models.Programa;
import com.bumptech.glide.Glide;


public class InformacionManana extends AppCompatActivity {
    ImageView imgManana;
    TextView txtPrograma;
    Button btnAtras, btnManana;
    boolean bandera;
    Button btnCerrar;
    VideoFragment videoFragment;
    ConstraintLayout contenedor;
    ImageView imgBanner;
    Programa programa= new Programa();
    int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_manana);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        videoFragment=new VideoFragment();
        MenuPrincipal.informacionManana=this;
        programa=MenuPrincipal.programaM;
        bandera = true;
        iniziliate();
        inputValues();
        c=0;
        btnManana.setVisibility(View.INVISIBLE);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c==0){
                    c++;
                    imgBanner.setVisibility(View.INVISIBLE);
                    btnCerrar.setVisibility(View.INVISIBLE);
                    btnManana.setVisibility(View.VISIBLE);

                }else {
                    cerrarVideo();
                }
            }
        });
    }

    private void iniziliate() {
        imgManana = findViewById(R.id.imgVistaInfoManana);
        imgBanner = findViewById(R.id.imgBanner);
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
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (MenuPrincipal.programaM!=programa){
                                inputData();
                                programa=MenuPrincipal.programaM;
                            }
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
        btnManana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchVideo();
            }
        });
    }

    private void inputData() {
        String programa = MenuPrincipal.fichaObjM.getPrograma();
        txtPrograma.setText(programa);
        try {
            Glide.with(this).load(MenuPrincipal.iconosM.getBlanco()).crossFade().into(imgManana);
            Glide.with(this).load(MenuPrincipal.programaM.getImagen()).crossFade().centerCrop().into(imgBanner);


        }catch (Exception e){
            Log.e("Error de imagen",e.getMessage());
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