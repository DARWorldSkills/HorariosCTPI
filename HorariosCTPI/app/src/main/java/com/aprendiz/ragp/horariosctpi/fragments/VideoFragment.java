package com.aprendiz.ragp.horariosctpi.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.aprendiz.ragp.horariosctpi.MenuPrincipal;
import com.aprendiz.ragp.horariosctpi.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    VideoView videoReal;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_video, container, false);
        videoReal = view.findViewById(R.id.videoReal);
        buscarVideo();
        return view;
    }

    private void buscarVideo(){
        File exportDir = new File(Environment.getExternalStorageDirectory(),"Videos");
        Uri uri = Uri.fromFile(new File(exportDir,"capsula1.mp4"));
        if (MenuPrincipal.programaNecesario.getVideo().equals("1")) {
            uri = Uri.fromFile(new File(exportDir, "capsula1.mp4"));
        }

        if (MenuPrincipal.programaNecesario.getVideo().equals("2")){
            uri = Uri.fromFile(new File(exportDir, "Cuaadrado TEA.mp4"));
        }

        if (MenuPrincipal.programaNecesario.getVideo().equals("3")){
            uri = Uri.fromFile(new File(exportDir, "Cuadrado Vj.mp4"));
        }

        if (MenuPrincipal.programaNecesario.getVideo().equals("4")){
            uri = Uri.fromFile(new File(exportDir, "Cuadrado AD.mp4"));
        }

        if (MenuPrincipal.programaNecesario.getVideo().equals("5")){
            uri = Uri.fromFile(new File(exportDir, "Cuadrado IL.mp4"));
        }

        if (MenuPrincipal.programaNecesario.getVideo().equals("6")){
            uri = Uri.fromFile(new File(exportDir, "Cuadrado Mm.mp4"));
        }

        inputVideos(uri);


    }

    private void inputVideos(Uri uri) {
        MediaController mediaController = new MediaController(getContext());

        videoReal.setMediaController(mediaController);
        try{
            videoReal.setVideoURI(uri);
            videoReal.requestFocus();
            videoReal.start();

        }catch (Exception e){
            Log.e("Error", e.getMessage());
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }




    }

}
