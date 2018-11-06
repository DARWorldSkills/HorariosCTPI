package com.aprendiz.ragp.horariosctpi.controllers;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.aprendiz.ragp.horariosctpi.R;
import com.aprendiz.ragp.horariosctpi.models.AdapterVideos;

import java.io.File;

public class CapsulasInfo extends AppCompatActivity {
    RecyclerView recyclerView;
    VideoView videoView;
    String [] lista;
    Button btnSalirCapsula;
    Uri uri;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capsulas_info);
        inizialite();
        inputList();
        adapterRecycler();
        finalizar();
        inputVideos(lista[0]);
    }

    private void finalizar() {
        btnSalirCapsula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inizialite() {
        recyclerView = findViewById(R.id.recyclerView);
        videoView = findViewById(R.id.videoView);
        btnSalirCapsula = findViewById(R.id.btnSalirCapsulas);
    }

    public void inputList(){
        File exportDir = new File(Environment.getExternalStorageDirectory(),"Videos");
        Uri capsula1 = Uri.fromFile(new File(exportDir,"capsula1.mp4"));
        Uri capsula2 = Uri.fromFile(new File(exportDir,"capsula2.mp4"));
        Uri capsula3 = Uri.fromFile(new File(exportDir,"capsula3.mp4"));
        Uri capsula4 = Uri.fromFile(new File(exportDir,"capsula4.mp4"));
        Uri capsula5 = Uri.fromFile(new File(exportDir,"capsula10.mp4"));
        lista = new String[]{capsula1.toString(), capsula2.toString(), capsula3.toString(), capsula4.toString(), capsula5.toString()};
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                i++;
                if (i>=lista.length){
                    i=0;
                }
                inputVideos(lista[i]);


            }
        });


    }

    private void adapterRecycler(){
        AdapterVideos adapterVideos = new AdapterVideos(lista,this);
        recyclerView.setAdapter(adapterVideos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterVideos.setMlistener(new AdapterVideos.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                if (!uri.toString().equals(lista[position])){
                    inputVideos(lista[position]);
                    i=position;
                }
            }
        });
    }

    private void inputVideos(String url) {
        uri = Uri.parse(url);

        MediaController mediaController = new MediaController(this);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(mediaController);
        try{
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();

        }catch (Exception e){
            Log.e("Error", e.getMessage());
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }




    }


}
