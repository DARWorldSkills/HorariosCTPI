package com.aprendiz.ragp.horariosctpi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.horariosctpi.controllers.CapsulasInfo;
import com.aprendiz.ragp.horariosctpi.controllers.HorarioManana;
import com.aprendiz.ragp.horariosctpi.controllers.HorarioNoche;
import com.aprendiz.ragp.horariosctpi.controllers.HorarioTarde;
import com.aprendiz.ragp.horariosctpi.controllers.IniciarSesion;
import com.aprendiz.ragp.horariosctpi.models.Ambiente;
import com.aprendiz.ragp.horariosctpi.models.Ficha;
import com.aprendiz.ragp.horariosctpi.models.Horario;
import com.aprendiz.ragp.horariosctpi.models.Iconos;
import com.aprendiz.ragp.horariosctpi.models.Instructor;
import com.aprendiz.ragp.horariosctpi.models.Programa;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MenuPrincipal extends AppCompatActivity implements OnClickListener {

    ImageView btnManana, btnTarde, btnNoche, imvLogo;
    TextView txtAmbiente, txtNumeroAmbiente,txtReloj, txtHora, txtManana,txtTarde, txtNoche, txtArea;
    Thread reloj;
    Button btnLogin,btnCapsulas;

    public static List<Ambiente> ambienteList = new ArrayList<>();
    public static List<Horario> horarioList = new ArrayList<>();
    public static List<Ficha> fichaList = new ArrayList<>();
    public static List<Programa> programaList = new ArrayList<>();
    public static List<Iconos> iconosList = new ArrayList<>();
    public static List<Instructor> instructorList = new ArrayList<>();
    public static Activity fa;
    String ficha[] =new String[3];
    String [] programa =new String[3];
    String [] iconos =new String[3];
    String [] iconosManana =new String[3];
    String [] iconosTarde =new String[3];
    String [] iconosNoche =new String[3];
    String apodoAmbiente = "TBT";
    Ambiente ambienteObj = new Ambiente();
    SharedPreferences preferences;
    public static Ficha fichaObjM = new Ficha();
    public static Ficha fichaObjT = new Ficha();
    public static Ficha fichaObjN = new Ficha();
    public static Iconos iconosM = new Iconos();
    public static Iconos iconosT = new Iconos();
    public static Iconos iconosN = new Iconos();
    public static Programa programaM = new Programa();
    public static Programa programaT = new Programa();
    public static Programa programaN = new Programa();
    public static Programa programaNecesario = new Programa();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        try {FirebaseDatabase.getInstance().setPersistenceEnabled(true);}catch (Exception e){}
        fa = this;
        preferences = getSharedPreferences("horarios",MODE_PRIVATE);
        inizialite();
        apodoAmbiente =preferences.getString("elegido","TBT");
        obtenerHorario();
        obtenerAmbiente();
        obtenerHora();
        obtenerInstructor();
    }

    private void obtenerHora() {

        reloj = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Date date = new Date();

                            SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
                            txtReloj.setText(dateForma.format(date));
                            txtHora.setText(dateFormat.format(date));
                        }
                    });
                }
            }
        });
        reloj.start();
    }

    private void inizialite() {
        txtAmbiente = findViewById(R.id.txtAmbiente);
        txtNumeroAmbiente = findViewById(R.id.txtNumeroAmbiente);
        txtManana = findViewById(R.id.txtHorarioManana);
        txtTarde = findViewById(R.id.txtHorarioTarde);
        txtNoche = findViewById(R.id.txtHorarioNoche);
        txtArea = findViewById(R.id.txtArea);
        btnManana = findViewById(R.id.btnManana);
        btnTarde = findViewById(R.id.btnTarde);
        btnNoche = findViewById(R.id.btnNoche);
        btnCapsulas = findViewById(R.id.btnCapsulas);
        imvLogo = findViewById(R.id.imvLogo);
        btnLogin = findViewById(R.id.btnLogin);
        txtReloj = findViewById(R.id.txtReloj);
        txtHora = findViewById(R.id.txtHora);
        btnManana.setOnClickListener(this);
        btnTarde.setOnClickListener(this);
        btnNoche.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnCapsulas.setOnClickListener(this);
    }

    private void obtenerAmbiente(){
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ambiente = reference.child("Ambientes");
        ambiente.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Ambiente>> t = new GenericTypeIndicator<ArrayList<Ambiente>>(){};
                ambienteList = dataSnapshot.getValue(t);
                for (int i=0; i<ambienteList.size();i++){
                    if (ambienteList.get(i).getApodo().equals(apodoAmbiente)){
                        ambienteObj = ambienteList.get(i);

                        try {
                            try {

                                Glide.with(MenuPrincipal.this).load(ambienteObj.getIcono()).crossFade().into(imvLogo);
                            }catch (Exception e){

                            }

                        }catch (Exception e){

                        }

                        String tmp = ambienteObj.getNombre();
                        try {
                            if (Integer.parseInt(tmp.substring(tmp.length()-1,tmp.length()))==1){
                                txtAmbiente.setText(tmp.substring(0,tmp.length()-1));
                                txtNumeroAmbiente.setText(Integer.toString(1));
                                txtArea.setText(tmp.substring(0,tmp.length()-1));
                            }

                            if (Integer.parseInt(tmp.substring(tmp.length()-1,tmp.length()))==2){
                                txtAmbiente.setText(tmp.substring(0,tmp.length()-1));
                                txtNumeroAmbiente.setText(Integer.toString(2));
                                txtArea.setText(tmp.substring(0,tmp.length()-1));
                            }

                        }catch (Exception e){
                            txtAmbiente.setText(tmp);
                            txtArea.setText(tmp);
                        }

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void obtenerHorario() {
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference horario = reference.child(apodoAmbiente);
        horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Horario>> t = new GenericTypeIndicator<ArrayList<Horario>>(){};
                horarioList = dataSnapshot.getValue(t);
                ficha[0] = horarioList.get(0).getFicha();
                ficha[1] = horarioList.get(3).getFicha();
                ficha[2] = horarioList.get(6).getFicha();
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
                    if (fichaList.get(i).getNumero().equals(ficha[0])) {
                        fichaObjM = fichaList.get(i);
                        programa[0] = fichaList.get(i).getPrograma();
                        txtManana.setText(programa[0]);
                    }

                    if (fichaList.get(i).getNumero().equals(ficha[1])) {
                        fichaObjT = fichaList.get(i);
                        programa[1] = fichaList.get(i).getPrograma();
                        txtTarde.setText(programa[1]);
                    }

                    if (fichaList.get(i).getNumero().equals(ficha[2])) {
                        programa[2] = fichaList.get(i).getPrograma();
                        fichaObjN = fichaList.get(i);
                        txtNoche.setText(programa[2]);

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
                        programaM = programaList.get(i);

                    }

                    if (programaList.get(i).getNombre().equals(programa[1])) {
                        iconos[1] = programaList.get(i).getIcono();
                        programaT = programaList.get(i);
                    }

                    if (programaList.get(i).getNombre().equals(programa[2])) {
                        iconos[2] = programaList.get(i).getIcono();
                        programaN = programaList.get(i);

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

                for (int i=0; i<iconosList.size();i++){
                    if (iconosList.get(i).getNombre().equals(iconos[0])) {
                        try {
                            iconosM = iconosList.get(i);
                            iconosManana[0] = iconosList.get(i).getManana();
                            Glide.with(MenuPrincipal.this).load(iconosManana[0]).crossFade().into(btnManana);

                        }catch (Exception e){

                        }

                    }

                    if (iconosList.get(i).getNombre().equals(iconos[1])) {
                        try {
                            iconosT = iconosList.get(i);
                            iconosTarde[1] = iconosList.get(i).getTarde();
                            Glide.with(MenuPrincipal.this).load(iconosTarde[1]).crossFade().into(btnTarde);
                        }catch (Exception e){

                        }

                    }

                    if (iconosList.get(i).getNombre().equals(iconos[2])) {
                        try {
                            iconosN = iconosList.get(i);
                            iconosNoche[2] = iconosList.get(i).getNoche();
                            Glide.with(MenuPrincipal.this).load(iconosNoche[2]).crossFade().into(btnNoche);
                        }catch (Exception e){

                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void obtenerInstructor(){
        FirebaseApp.initializeApp(this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference horario = reference.child("Instructores");
        horario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Instructor>> t = new GenericTypeIndicator<ArrayList<Instructor>>(){};
                instructorList = dataSnapshot.getValue(t);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btnLogin:
                intent = new Intent(MenuPrincipal.this,IniciarSesion.class);
                startActivity(intent);
                break;

            case R.id.btnManana:
                intent = new Intent(MenuPrincipal.this,HorarioManana.class);
                programaNecesario = programaM;
                startActivity(intent);
                break;

            case R.id.btnTarde:
                intent = new Intent(MenuPrincipal.this,HorarioTarde.class);
                programaNecesario = programaT;
                startActivity(intent);
                break;

            case R.id.btnNoche:
                intent = new Intent(MenuPrincipal.this,HorarioNoche.class);
                programaNecesario = programaN;
                startActivity(intent);
                break;

            case R.id.btnCapsulas:
                intent = new Intent(MenuPrincipal.this,CapsulasInfo.class);
                startActivity(intent);
                break;

        }
    }
}
