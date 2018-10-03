package com.aprendiz.ragp.horariosctpi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.aprendiz.ragp.horariosctpi.models.Ficha;
import com.aprendiz.ragp.horariosctpi.models.Horario;
import com.aprendiz.ragp.horariosctpi.models.Iconos;
import com.aprendiz.ragp.horariosctpi.models.Programa;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends AppCompatActivity {
    List<Horario> horarioList = new ArrayList<>();
    List<Ficha> fichaList = new ArrayList<>();
    List<Programa> programaList = new ArrayList<>();
    List<Iconos> iconosList = new ArrayList<>();
    String ficha1 ="";
    String ficha2 ="";
    String ficha3 ="";
    String [] programa =new String[3];
    String [] iconos =new String[3];
    String [] iconosManana =new String[3];
    String [] iconosTarde =new String[3];
    String [] iconosNoche =new String[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        obtenerHorario();

    }

    private void obtenerHorario() {
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference horario = reference.child("TBT");
        horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Horario>> t = new GenericTypeIndicator<ArrayList<Horario>>(){};
                horarioList = dataSnapshot.getValue(t);
                ficha1 = horarioList.get(0).getFicha();
                ficha2 = horarioList.get(2).getFicha();
                ficha3 = horarioList.get(4).getFicha();
                obtenerFicha();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void obtenerFicha(){
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference horario = reference.child("Ficha");
        horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Ficha>> t = new GenericTypeIndicator<ArrayList<Ficha>>(){};
                fichaList = dataSnapshot.getValue(t);
                for (int i=0; i<fichaList.size();i++){
                    if (fichaList.get(i).getNumero().equals(ficha1)) {
                        programa[0] = fichaList.get(i).getPrograma();
                    }

                    if (fichaList.get(i).getNumero().equals(ficha2)) {
                        programa[1] = fichaList.get(i).getPrograma();
                    }

                    if (fichaList.get(i).getNumero().equals(ficha3)) {
                        programa[2] = fichaList.get(i).getPrograma();
                    }
                }
                obtenerPrograma();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void obtenerPrograma(){
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference horario = reference.child("Programa");
        horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Programa>> t = new GenericTypeIndicator<ArrayList<Programa>>(){};
                programaList = dataSnapshot.getValue(t);
                for (int i=0; i<programaList.size();i++){
                    if (programaList.get(i).getNombre().equals(programa[0])) {
                        iconos[0] = programaList.get(i).getIcono();
                    }

                    if (programaList.get(i).getNombre().equals(programa[1])) {
                        iconos[1] = programaList.get(i).getIcono();
                    }

                    if (programaList.get(i).getNombre().equals(programa[2])) {
                        iconos[2] = programaList.get(i).getIcono();
                    }

                }
                obtenerIconos();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void obtenerIconos(){
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference horario = reference.child("Iconos");
        horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Iconos>> t = new GenericTypeIndicator<ArrayList<Iconos>>(){};
                iconosList = dataSnapshot.getValue(t);
                Log.e("Hola",iconosList.get(0).getManana());
                for (int i=0; i<iconosList.size();i++){
                    if (iconosList.get(i).getNombre().equals(iconos[0])) {
                        iconosManana[0] = programaList.get(i).getIcono();
                        iconosTarde[0] = programaList.get(i).getIcono();
                        iconosNoche[0] = programaList.get(i).getIcono();

                    }

                    if (iconosList.get(i).getNombre().equals(iconos[1])) {
                        iconosManana[1] = programaList.get(i).getIcono();
                        iconosTarde[1] = programaList.get(i).getIcono();
                        iconosNoche[1] = programaList.get(i).getIcono();
                    }

                    if (iconosList.get(i).getNombre().equals(iconos[2])) {
                        iconosManana[2] = programaList.get(i).getIcono();
                        iconosTarde[2] = programaList.get(i).getIcono();
                        iconosNoche[2] = programaList.get(i).getIcono();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
